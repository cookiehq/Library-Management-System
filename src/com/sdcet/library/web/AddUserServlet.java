package com.sdcet.library.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sdcet.library.dao.UserDao;
import com.sdcet.library.dao.jdbc.UserDaoJDBC;
import com.sdcet.library.domain.Users;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/adduser")
public class AddUserServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("email");
		String mail = request.getParameter("mail");
		HttpSession session = request.getSession();
		
		String mailcode = (String) session.getAttribute("mailcode");
		System.out.println(mailcode);
		System.out.println(mail);
		
		if (mailcode == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("SendEmailServlet?email="+phone);
			dispatcher.forward(request, response);
		} else {
				
			
			if (mail.equals(mailcode)) {
				
				UserDao dao = new UserDaoJDBC();
				
				Users user = new Users(null, loginName,password, name, gender, phone);
				
				dao.add(user);
				
				session.setAttribute("user", user);
				
				response.sendRedirect("userlogin.html");
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("useradd.jsp");
				dispatcher.forward(request, response);
			}
			
		}
		
	}

}
