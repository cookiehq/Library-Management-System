package com.sdcet.library.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.UserDao;


@WebServlet("/manage/userdelete")
public class UserDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		ServletContext sc = request.getServletContext();
		UserDao dao = (UserDao) sc.getAttribute("usersDao");
		dao.delete(Integer.parseInt(id));

		response.sendRedirect(request.getContextPath() + "/manage/user.jsp");
	}

}
