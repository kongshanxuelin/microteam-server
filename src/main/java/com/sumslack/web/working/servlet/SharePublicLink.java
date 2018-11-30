package com.sumslack.web.working.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sumslack.jsptagex.rest.TagRest;
import com.sumslack.jsptagex.util.IdWorker;
import com.sumslack.jsptagex.util.StrUtil;
import com.sumslack.jsptagex.web.servlet.AjaxServlet;
@TagRest("/view/*")
public class SharePublicLink extends AjaxServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String tmplId = StrUtil.formatNullStr(req.getAttribute("$1"));
		req.getSession().setAttribute("uid", "gst_"+IdWorker.getInstance().uuid());
		req.getRequestDispatcher("/r/page/eform-add/"+tmplId).forward(req, resp);
	}
}
