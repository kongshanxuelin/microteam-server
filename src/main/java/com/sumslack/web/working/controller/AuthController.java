package com.sumslack.web.working.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sumslack.app.jd.front.tool.HttpTools;
import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.web.servlet.BaseController;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.TeamsDAO;
import com.sumslack.web.working.dao.Teams_userDAO;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.service.UserService;
import com.sumslack.web.working.servlet.QrCodeLoginServlet;
@URIAlias("auth")
public class AuthController extends BaseController{
	public Map login() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		String username = StrUtil.formatNullStr(request.getParameter("username"));
		String pwd = StrUtil.formatNullStr(request.getParameter("pwd"));
		UsersDAO user = UsersDAO.dao.findFirst("select * from " + UsersDAO.dao.getTable().getName() + " where username=?",username);
		if(user!=null && user.getPwd().equals(pwd)){
			user.setToken(IdWorker.getInstance().uuid());
			user.save();
			request.getSession().setAttribute("uid", user.getUid());
			request.getSession().setAttribute("nick", user.getNick());
			request.getSession().setAttribute("teamId", user.getCompany_id());
			request.getSession().setAttribute("teamName", user.getCompany_name());
			request.getSession().setAttribute("user", UserService.getInstance().getUserByToken(user.getToken()));
			retMap.put("ret", true);
			return retMap;
		}
		retMap.put("msg", "用户名或密码错误！");
		return retMap;
	}
	
	public Map loginByToken() {
		Map retMap = new HashMap();
		retMap.put("ret", false);
		String uid = StrUtil.formatNullStr(Global.getUidByToken(StrUtil.formatNullStr(request.getParameter("token"))));
		if(!uid.equals("")){
			retMap.put("ret", true);
			request.getSession().setAttribute("uid", uid);
			request.getSession().setAttribute("nick", Global.getNick(uid));
		}
		return retMap;
	}
	public boolean logout() {
		request.getSession().removeAttribute("uid");
		request.getSession().removeAttribute("nick");
		return true;
	}
	
	public boolean loginByWxQrCode(){
		String loginId = StrUtil.formatNullStr(request.getParameter("loginId"));
		UserBean ub = QrCodeLoginServlet.qrcodeMap.get(loginId);
		if(ub!=null){
			request.getSession().setAttribute("uid", ub.getUid());
			request.getSession().setAttribute("nick", ub.getNick());
			return true;
		}
		return false;
	}
	
	/**
	 * 微信小程序登录服务
	 */
	public static Map<String,UserBean> users = new ConcurrentHashMap<String, UserBean>();
	
	protected UserBean getWxUser(String token){
		UserBean ub = UserService.getInstance().getUserByToken(token);
		if(ub == null) return null;
		return ub;
	}
	
	public Map wx_checkLogin() throws Exception{
		Map retMap = new HashMap();
		retMap.put("res", false);
		String token = StrUtil.formatNullStr(request.getParameter("token"));
		UserBean ub = getWxUser(token);
		if(ub!=null){
			retMap.put("res", true);			
			retMap.put("user", ub);
		}
		return retMap;
	}
	
	public Map wx_login() throws Exception{
		Map retMap = new HashMap();
		String code = StrUtil.formatNullStr(request.getParameter("code"));
		retMap.put("code", 200);
		String nickName = UserService.filterOffUtf8Mb4(StrUtil.formatNullStr(request.getParameter("nickName")), "我的");
		String avatarUrl = StrUtil.formatNullStr(request.getParameter("avatarUrl"));
		String token = IdWorker.getInstance().uuid();
		String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+StrUtil.formatNullStr(TagConst.globalMap.get("wx.appid"))+
				"&secret="+StrUtil.formatNullStr(TagConst.globalMap.get("wx.seckey"))+
				"&js_code="+code+"&grant_type=authorization_code";
		//{"session_key":"feqol0dpMS812xB9v518Gw==","expires_in":2592000,"openid":"opCDs0H1T08wjdQ-Xi9z2ALgvefI"}
		String retStr = StrUtil.formatNullStr(HttpTools.https(url));
		//{"errcode":40029,"errmsg":"invalid code, hints: [ req_id: U2wJxa0019s102 ]"}
		if(retStr.equals("")){
			System.out.println("[wx_login] retStr error: " + retStr);
			retMap.put("code", 500);
			retMap.put("info", "invoke wx https method error!");
			return retMap;
		}
		JSONObject obj = JSON.parseObject(retStr);
		String errcode = StrUtil.formatNullStr(obj.get("errcode"));
		if(!errcode.equals("")){
			System.out.println("[wx_login] errcode: " + retStr);
			retMap.put("code", errcode);
			retMap.put("info",StrUtil.formatNullStr(obj.get("errmsg")));
			return retMap;
		}
		String session_key = StrUtil.formatNullStr(obj.get("session_key"));
		String openUid =  StrUtil.formatNullStr(obj.get("openid"));
		UsersDAO _user = UserService.getInstance().getUser(openUid);
		TeamsDAO team = null;
		System.out.println("[wx_login] openUid: " + openUid);
		if(_user == null){
			System.out.println("[wx_login] create new user:" + nickName + "," + openUid+ "," +token+ "," +session_key+ "," +avatarUrl);
			String _uid = UserService.getInstance().addWxUser(openUid, nickName, avatarUrl, session_key,token);
			System.out.println("[wx_login] addWxUser:" + _uid);
			String teamid = StrUtil.formatNullStr(request.getParameter("teamid"));
			boolean isOwner = false;
			if(!teamid.equals("")){
				System.out.println("[wx_login] team is exist");
				team = TeamsDAO.dao.findById("*", teamid);
			}else{
				//查看当前用户是否有团队，如果没有创建一个
				System.out.println("[wx_login] create team");
				team = new TeamsDAO();
				String _teamId = IdWorker.getInstance().uuid();
				team.setId(_teamId);
				team.setTitle(StrUtil.formatNullStr(nickName, "我") + "的团队");
				team.setCreate_uid(_uid);
				team.setCreate_time(new Date());
				team.setDelflag("0");
				boolean isSucc = team.add();
				System.out.println("[wx_login] create team is succ:"+isSucc);
				isOwner = true;
			}
			
			if(team!=null){
				Teams_userDAO tuExist = Teams_userDAO.dao.findFirst("select * from " + Teams_userDAO.dao.getTable().getName() + " where team_id=? and uid=?",team.getId(),_uid);
				if(tuExist == null){
					Teams_userDAO tu = new Teams_userDAO();
					tu.setId(IdWorker.getInstance().uuid());
					tu.setTeam_id(team.getId());
					tu.setTeam_tid(team.getId());
					tu.setRole( isOwner?"owner":"member");
					tu.setUid(_uid);
					boolean isSucc = tu.add();
					System.out.println("[wx_login] create team_user is succ:"+isSucc);
					_user = UserService.getInstance().getUser(openUid);
					_user.setCompany_id(team.getId());
					_user.setCompany_name(team.getTitle());
					_user.save();
				}else {
					System.out.println("[wx_login] Teams_userDAO isexist");
				}
			}

		}else{
			System.out.println("[wx_login] saveWxUser:"+openUid+","+nickName);
			UserService.getInstance().saveWxUser(openUid, nickName, avatarUrl, session_key,token);
		}
		retMap.put("token", token);
		retMap.put("uid", openUid);
		UserBean ub = getWxUser(token);
		retMap.put("user", ub);
		return retMap;
	}
}
