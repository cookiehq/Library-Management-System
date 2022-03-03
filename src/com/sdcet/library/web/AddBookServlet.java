package com.sdcet.library.web;

import java.io.IOException;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.BooksDao;
import com.sdcet.library.domain.Books;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/manage/addbook")
public class AddBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int borrows = Integer.parseInt(request.getParameter("borrows"));
		int categorieId = Integer.parseInt(request.getParameter("categorieId"));
		
		ServletContext sc = request.getServletContext();
		BooksDao dao = (BooksDao) sc.getAttribute("booksDao");
		
		Books book = new Books(null, name, author, publisher, price, stock, borrows,categorieId);
		
		dao.add(book);
		
		response.sendRedirect(request.getContextPath() + "/manage/book.jsp");
	}

}
