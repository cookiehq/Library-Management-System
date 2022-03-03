package com.sdcet.library.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.BooksDao;
import com.sdcet.library.dao.RecordDao;
import com.sdcet.library.domain.Books;
import com.sdcet.library.domain.Record;

/**
 * Servlet implementation class SelectBookServlet
 */
@WebServlet("/selectrecord")
public class SelectRecordServletByUserId extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("userid"));

		ServletContext sc = request.getServletContext();
		RecordDao dao = (RecordDao) sc.getAttribute("recordDao");
		List<Record> selrecordList = dao.findByUserId(id);
		sc.setAttribute("selrecordList", selrecordList);
	
		response.sendRedirect(request.getContextPath() + "/manage/searchrecord.jsp");
	}

}
