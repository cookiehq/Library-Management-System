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
@WebServlet("/selectbookname")
public class SelectBookServletByName extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");

		ServletContext sc = request.getServletContext();
		BooksDao dao = (BooksDao) sc.getAttribute("booksDao");
		List<Books> books = dao.findByName(name);
		sc.setAttribute("selbookList", books);
	
		response.sendRedirect(request.getContextPath() + "/manage/searchbook.jsp");
	}

}
