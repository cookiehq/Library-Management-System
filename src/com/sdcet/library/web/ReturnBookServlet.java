package com.sdcet.library.web;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.RecordDao;
import com.sdcet.library.dao.jdbc.RecordDaoJDBC;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/returnbook")
public class ReturnBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bookid = Integer.parseInt(request.getParameter("bookid"));
		int userid = Integer.parseInt(request.getParameter("userid"));
		
		RecordDao recordDao = new RecordDaoJDBC();
		
		recordDao.returnbook(bookid, userid);
		
		response.sendRedirect(request.getContextPath() + "/manage/record.jsp");
	}

}
