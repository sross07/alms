package org.web.action;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SecurityFilter implements Filter 
{	
		private static String loginView="/UserLogin.action";
		private static Set<String> publicUrls = new HashSet<String>();
		 
		static {
		    publicUrls.add("/login.jsp");
			publicUrls.add("/register.jsp");
			publicUrls.add("/forgotpassword.jsp");
			publicUrls.add("/index.jsp");
			publicUrls.add("/UserLogin.action");
			publicUrls.add("/Register.action");
			publicUrls.add("/ForgotPassword.action");
		}
    
	    /** Does nothing. */
	    public void init(FilterConfig filterConfig) throws ServletException { }	    
	    
	    public void doFilter(ServletRequest servletRequest,
                ServletResponse servletResponse,
                FilterChain filterChain) throws IOException, ServletException
        {	
	    	HttpServletRequest request = (HttpServletRequest) servletRequest;
			HttpServletResponse response = (HttpServletResponse) servletResponse;
			
			if (request.getSession().getAttribute("user") != null) {
				
				if (isPublicResource(request))
				{
					response.sendRedirect(request.getContextPath() + "/portal.action");
				}
				else
				{
					filterChain.doFilter(request, response);
				}
			   
			}
			else if ( isPublicResource(request) ) {
			   filterChain.doFilter(request, response);
			}
			else {
			   response.sendRedirect(request.getContextPath() + loginView);
			}
		}
			
		/**
		* Method that checks the request to see if it is for a publicly accessible resource
		*/
		protected boolean isPublicResource(HttpServletRequest request) 
		{
			String resource = request.getServletPath();
			
			return publicUrls.contains(request.getServletPath())
			       || (!resource.endsWith(".jsp") && !resource.endsWith(".action"));
		}
		
		/** Does nothing. */
		public void destroy() { }    
}
