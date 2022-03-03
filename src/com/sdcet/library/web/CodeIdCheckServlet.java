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

/**
 * Servlet implementation class CodeIdCheckServlet
 */
@WebServlet("/codeidcheck")
public class CodeIdCheckServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		
		CodeDao dao = new CodeDaoJDBC();
		
		if (dao.hasMatchCode(id)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("borrowbook");
			dispatcher.forward(request, response);
		}else {
			response.sendRedirect(request.getContextPath() + "/manage/borrowbook.jsp?codemessage=1");
		}
	}

}
