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
import com.sdcet.library.domain.Books;

/**
 * Servlet implementation class SelectBookServlet
 */
@WebServlet("/selectbookid")
public class SelectBookServletById extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ServletContext sc = request.getServletContext();
		BooksDao dao = (BooksDao) sc.getAttribute("booksDao");
		Books book = dao.findById(id);
		sc.setAttribute("selbook", book);
	
		response.sendRedirect(request.getContextPath() + "/manage/updatebook.jsp");
	}

}
