package com.sdcet.library.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdcet.library.dao.BooksDao;
import com.sdcet.library.dao.UserDao;
import com.sdcet.library.dao.jdbc.UserDaoJDBC;
import com.sdcet.library.domain.Books;
import com.sdcet.library.domain.Users;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/updateuser")
public class UpdateUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		
		if ("".equals(loginName)) {
			System.out.println("".equals(loginName));
			response.sendRedirect("userlogin.html");
		}else{
		
			UserDao dao = new UserDaoJDBC();
			
			Users user = new Users(id, loginName,password, name, gender, phone);
			
			dao.update(user);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			response.sendRedirect("userInfo.jsp");
		}
	}

}
