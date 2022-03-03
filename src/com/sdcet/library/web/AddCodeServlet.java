package com.sdcet.library.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.CodeDao;
import com.sdcet.library.dao.jdbc.CodeDaoJDBC;
import com.sdcet.library.domain.Code;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/addcode")
public class AddCodeServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookid = Integer.parseInt(request.getParameter("bookid").trim());
		String userid = request.getParameter("userid").trim();
		
		if (userid == null) {
			System.out.println(userid);
			response.sendRedirect("userlogin.html");
		}else{
		
			CodeDao dao = new CodeDaoJDBC();
			
			Code code = dao.add(bookid, Integer.parseInt(userid));
			
			request.setAttribute("code", code);
			request.setAttribute("num", 1);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("bookInfo.jsp?id="+bookid);
			dispatcher.forward(request, response);
		}
	}

}
