package com.sumslack.web.working.interceptor;

import javax.servlet.http.HttpServletRequest;

import com.sumslack.jsptagex.aop.IAOPCallback;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.web.working.service.LogService;

public class ControllerInterceptor implements IAOPCallback{

	public boolean before(Object obj, Object[] args) {
		if(obj instanceof HttpServletRequest){
			HttpServletRequest controller = (HttpServletRequest)obj;
			System.out.println("===========visit url(before):"+controller.getRequestURI());
			//LogService.getInstance().addLog(controller);
		}
		return true;
	}

	public void after(Object obj, Object[] args) {
		if(obj instanceof HttpServletRequest){
			HttpServletRequest controller = (HttpServletRequest)obj;
			System.out.println("===========visit url(after):"+controller.getRequestURI());
		}
	}

}
