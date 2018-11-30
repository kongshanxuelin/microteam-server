package com.sumslack.web.working.controller;

import java.util.ArrayList;
import java.util.List;

import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.web.servlet.BaseController;
import com.sumslack.web.working.Global;
import com.sumslack.web.working.bean.PurviewUserTmpl;
import com.sumslack.web.working.bean.UserBean;
import com.sumslack.web.working.dao.UsersDAO;
import com.sumslack.web.working.dao.Users_tmpl_purviewDAO;
import com.sumslack.web.working.service.UserService;

public class CController extends BaseController{

	protected UserBean getSessUser(){
		String uid = StrUtil.formatNullStr(request.getSession().getAttribute("uid"));
		if(uid.equals("")){
			String token = StrUtil.formatNullStr(request.getParameter("token"));
			//uid = StrUtil.formatNullStr(Global.getUidByToken(token));
			if(!token.equals("")){
				return getWxUser(StrUtil.formatNullStr(request.getParameter("token")));
			}
		}
		if(!uid.equals("")){
			if(Global.users.get(uid)!=null){
				return Global.users.get(uid);
			}
			UsersDAO user = UsersDAO.dao.findById("*", uid);
			UserBean userBean = getUserBean(user);
			if(user!=null){
				Global.users.put(uid, userBean);
				return userBean;
			}
		}
		return null;
	}
	
	protected UserBean getWxUser(){
		return getWxUser(StrUtil.formatNullStr(request.getParameter("token")));
	}
	
	protected UserBean getWxUser(String token){
		UserBean ub = UserService.getInstance().getUserByToken(token);
		if(ub == null) return null;
		return ub;
	}
	
	protected String param(String param){
		return StrUtil.formatNullStr(request.getParameter(param));
	}
	protected String param(String param,String defaultValue){
		return StrUtil.formatNullStr(request.getParameter(param),defaultValue);
	}
	
	private UserBean getUserBean(UsersDAO user){
		UserBean userBean = new UserBean();
		if(user!=null){
			userBean.setUid(user.getUid());
			userBean.setNick(user.getNick());
			userBean.setTeamId(user.getCompany_id());
			userBean.setAvatar(user.getAvatar());
			userBean.setTmplList(getTmplList(user.getUid()));
			return userBean;
		}
		return null;
	}
	
	private List<PurviewUserTmpl> getTmplList(String uid){
		List<PurviewUserTmpl> tmplList = new ArrayList();
		List<Users_tmpl_purviewDAO> tmpls = Users_tmpl_purviewDAO.dao.find("select * from " + Users_tmpl_purviewDAO.dao.getTable().getName() + " where uid=?", uid);
		if(tmpls!=null && tmpls.size()>0){
			for(Users_tmpl_purviewDAO d : tmpls){
				PurviewUserTmpl ut = new PurviewUserTmpl();
				ut.setTmplId(d.getTmpl_id());
				ut.setCanView(StrUtil.formatNullStr(d.getIsview()).equals("Y"));
				ut.setCanEdit(StrUtil.formatNullStr(d.getIsedit()).equals("Y"));
				ut.setCanRemove(StrUtil.formatNullStr(d.getIsremove()).equals("Y"));
				tmplList.add(ut);
			}
		}
		return tmplList;
	}
}
