package com.sdcet.library.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;

import com.sdcet.library.dao.AdminDao;
import com.sdcet.library.dao.UserDao;
import com.sdcet.library.dao.jdbc.AdminDaoJDBC;
import com.sdcet.library.dao.jdbc.UserDaoJDBC;
import com.sdcet.library.domain.Admins;
import com.sdcet.library.domain.Users;
import com.sdcet.library.utils.Constans;



/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/userlogincheck")
public class UserLoginCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name = request.getParameter("name");
		String passwd = request.getParameter("password");
		passwd = DigestUtils.md5Hex(passwd + Constans.SALT);
		
		System.out.println("name = " + name);
		System.out.println("password = " + passwd);
		
		HttpSession session = request.getSession();
		UserDao dao = new UserDaoJDBC();
		System.out.println("返回值 = "+dao.hasMatchUser(name, passwd));
		if(dao.hasMatchUser(name, passwd)) {
			//用户登陆成功
			Users user = dao.findByLoginName(name);
			session.setAttribute("user", user);
			
			response.sendRedirect("index.jsp");
		} else {
			//用户登陆失败
			request.setAttribute("message", "用户名或密码不正确");
			RequestDispatcher dispatcher = request.getRequestDispatcher("userlogin.html");
			dispatcher.forward(request, response);
		}
	}

}
