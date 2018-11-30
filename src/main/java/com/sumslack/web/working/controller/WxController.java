package com.sumslack.web.working.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.sumslack.app.jd.front.tool.PayUtil;
import com.sumslack.app.jd.front.tool.UUIDHexGenerator;
import com.sumslack.app.jd.front.tool.WXTemplateMsgManager;
import com.sumslack.app.jd.front.tool.WeixinJSSDKManager;
import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.tag.TagConst;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.util.TagJDBCInstance;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.M_work_annoDAO;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.service.TaskService;
import com.sumslack.web.working.service.TencentAIService;
import com.sumslack.web.working.service.WorkFlowService;
import com.sumslack.web.working.service.WxService;

@URIAlias("wx/service")
public class WxController extends CController{
	public void teamQrcode(){
		String teamId = StrUtil.formatNullStr(request.getParameter("teamid"));
		if(teamId.equals("")) return;
		WxService.getInstance().genQrCode("s.team=" + teamId, 350, response);
	}
	
	public void qrcode(){
		String path = StrUtil.formatNullStr(request.getParameter("path"));
		
		String w = StrUtil.formatNullStr(request.getParameter("w"));
		
		if(path.equals("")) return;
		WxService.getInstance().genQrCode(path, w.equals("")?350:Integer.parseInt(w), response);
	}
	
	public List ai_thing() {
		String action = StrUtil.formatNullStr(request.getParameter("action"));
		return TencentAIService.getInstance().getSelectValue(action);
	}
	
	public Map js_config() {

		
		String url = StrUtil.formatNullStr(request.getParameter("url"));
		if(url.equals("")) {
			StringBuffer requestUrl = request.getRequestURL();
	        String queryString = request.getQueryString();
	        url = requestUrl +"?"+queryString;
		}else {
			try {
				url = URLDecoder.decode(url,"UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}

		Map retMap = WeixinJSSDKManager.sign(url);
		return retMap;
        
	}
	public Map myHome(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		
		//最近一条公告
		M_work_annoDAO anno = M_work_annoDAO.dao.findFirst("select title,content from " + 
				M_work_annoDAO.dao.getTable().getName() + " where company_id=? order by modify_time desc limit 0,1",ub.getTeamId());
		if(anno!=null)
			retMap.put("anno", anno);
		
		//显示分配给我的任务数量
		Map taskMap = TaskService.getInstance().getMyTaskNum(ub.getUid());
		retMap.put("task", taskMap);
		
		//我参与或审批的进行中的流程
		List processList = WorkFlowService.getIntance().myProcess(ub);
		retMap.put("processList", processList);
		return retMap;
	}
	public Map bindSumslackCheck() throws Exception{
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		UsersDAO _user = UsersDAO.dao.findById("*", ub.getUid());
		if(_user!=null){
			int sumslackUid = StrUtil.formatNullStrInt(_user.getSumslack_uid(),0);
			retMap.put("ret", (sumslackUid>0));
			Map userMap = TagJDBCInstance.getInstance().queryOne("sumslack","select id,name,nick from imuser where id=?", new Object[]{sumslackUid});
			if(userMap!=null)
				retMap.put("user", userMap);
		}
		return retMap;
	}
	public Map bindSumslack(){
		Map retMap = new HashMap();
		retMap.put("ret", false);
		UserBean ub = getWxUser();
		if(ub == null) return retMap;
		String u = StrUtil.formatNullStr(request.getParameter("u"));
		String p = StrUtil.formatNullStr(request.getParameter("p"));
		
		String md5 = StrUtil.md5(p).toLowerCase();
		String md52 = StrUtil.md5(md5);
		try {
			Map userMap = TagJDBCInstance.getInstance().queryOne("sumslack","select id from imuser where name=? and (password=? or password=?)", new Object[]{u,md5,md52});
			if(userMap!=null){
				UsersDAO _user = UsersDAO.dao.findById("*", ub.getUid());
				if(_user!=null){
					_user.setSumslack_uid(Integer.parseInt(StrUtil.formatNullStr(userMap.get("id"))));
					boolean res = _user.save();
					userMap.put("ret", res);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retMap;
	}
}
