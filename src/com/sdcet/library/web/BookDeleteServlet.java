package com.sdcet.library.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.BooksDao;


@WebServlet("/manage/bookdelete")
public class BookDeleteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		ServletContext sc = request.getServletContext();
		BooksDao dao = (BooksDao) sc.getAttribute("booksDao");
		dao.delete(id);
	

		response.sendRedirect(request.getContextPath() + "/manage/book.jsp");
	}

}
