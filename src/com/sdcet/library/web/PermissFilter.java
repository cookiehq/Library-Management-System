package com.sdcet.library.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdcet.library.domain.Admins;
import com.sdcet.library.domain.Users;

/**
 * Servlet Filter implementation class PermissFilter
 */
@WebFilter("/manage/*")
public class PermissFilter implements Filter {

	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		HttpSession session = req.getSession();
		Admins admin = (Admins) session.getAttribute("admin");
		if(admin == null) {
			//用户未登陆
			resp.sendRedirect(req.getContextPath() + "/login.html");
		} else {
			//用户已登陆
			chain.doFilter(request, response);
		}
	}


	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
