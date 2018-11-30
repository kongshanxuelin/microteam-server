package com.sumslack.web.working.controller;

import com.sumslack.jsptagex.anno.rest.URIAlias;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.bean.UserBean;

@URIAlias("dubbo")
public class DubboController extends CController{
	public boolean sendCard(){
		UserBean ub = getSessUser();
		String appId = StrUtil.formatNullStr(request.getParameter("appid"));
		String userId = StrUtil.formatNullStr(request.getParameter("userId"));
		String cardTitle = StrUtil.formatNullStr(request.getParameter("t"));
		String cardContent = StrUtil.formatNullStr(request.getParameter("c"));
		String cardUrl = StrUtil.formatNullStr(request.getParameter("url"));
		//getRPCService().sendSysMsgCard(appId, 0, Integer.parseInt(userId), cardTitle, cardContent, cardUrl, "");
		return true;
	}
	
	public boolean sendShareLink(){
		UserBean ub = getSessUser();
		String appId = StrUtil.formatNullStr(request.getParameter("appid"));
		String userId = StrUtil.formatNullStr(request.getParameter("userId"));
		String cardTitle = StrUtil.formatNullStr(request.getParameter("t"));
		String cardContent = StrUtil.formatNullStr(request.getParameter("c"));
		String cardUrl = StrUtil.formatNullStr(request.getParameter("url"));
//		getRPCService().sendShareLink(52, Integer.parseInt(userId), 
//				appId, "任务", "姓名", cardTitle, cardContent, cardUrl);
		return true;
	}
}
