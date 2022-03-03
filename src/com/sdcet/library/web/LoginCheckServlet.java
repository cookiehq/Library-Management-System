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
import com.sdcet.library.dao.jdbc.AdminDaoJDBC;
import com.sdcet.library.domain.Admins;
import com.sdcet.library.utils.Constans;



/**
 * Servlet implementation class LoginCheckServlet
 */
@WebServlet("/logincheck")
public class LoginCheckServlet extends HttpServlet {

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
		AdminDao dao = new AdminDaoJDBC();
		System.out.println("����ֵ = "+dao.hasMatchUser(name, passwd));
		if(dao.hasMatchUser(name, passwd)) {
			//�û���½�ɹ�
			Admins admin = dao.findByLoginName(name);
			session.setAttribute("admin", admin);
			
			response.sendRedirect(request.getContextPath() + "/manage/book.jsp");
		} else {
			//�û���½ʧ��
			request.setAttribute("message", "�û��������벻��ȷ");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
			dispatcher.forward(request, response);
		}
	}

}
