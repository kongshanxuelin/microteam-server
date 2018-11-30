package com.sumslack.web.working.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sumslack.jsptagex.util.StrUtil;
  
public class CORSFilter implements Filter{  
	private static String[] filterSecurityNone = new String[]{"/css/**","/js/**","/login.jsp","/r/auth/**","/r/**","/view/**","/upload/**"};
    @Override  
    public void destroy() {  
          
    }  
  
    @Override  
    public void doFilter(ServletRequest req, ServletResponse res,  
            FilterChain chain) throws IOException, ServletException {  
        HttpServletResponse response = (HttpServletResponse) res;  
        HttpServletRequest servletRequest = (HttpServletRequest) req;
        HttpSession session = servletRequest.getSession();
        String path = servletRequest.getRequestURI();
        response.setHeader("Access-Control-Allow-Origin", "*");  
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");  
        response.setHeader("Access-Control-Max-Age", "3600");  
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");  
        if(isInFilterSecNone(servletRequest.getContextPath(),path)){
        	chain.doFilter(req, res);  
		}else{
			String username = StrUtil.formatNullStr(session.getAttribute("uid"));
			String token = StrUtil.formatNullStr(servletRequest.getParameter("token"));
			if(!token.equals("")){
				chain.doFilter(servletRequest, response);
			}
			if(username.equals("")){
				if (servletRequest.getHeader("x-requested-with") != null && servletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){  
					response.setHeader("sessionstatus", "sessionOut");
					response.getWriter().print("sessionOut");  
				    return; 
	            }else{
	            	response.sendRedirect(servletRequest.getContextPath()+"/login.jsp");
	            }
			}else{
				chain.doFilter(servletRequest, response);
			}
		}
        
        
        
    }  
  
    @Override  
    public void init(FilterConfig arg0) throws ServletException {  
          
    }  
    
    
    private boolean isInFilterSecNone(String contextPath,String path){
		//根目录都放行
		if(path.equals("/")) return true;
		
		//忽略检查的
		for(String ss:filterSecurityNone){
			ss = contextPath + ss;
			if(path.equals(ss)){
				return true;
			}else if(ss.endsWith("/**")){
				ss = ss.substring(0, ss.length()-3);
				if(path.startsWith(ss)){
					return true;
				}
			}else if(ss.startsWith("*.")){
				ss = ss.substring(2);
				if(path.endsWith(ss)){
					return true;
				}
			}
		}
		return false;
	}
  
}  