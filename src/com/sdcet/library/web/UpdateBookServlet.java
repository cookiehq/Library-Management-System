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
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/manage/updatebook")
public class UpdateBookServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String publisher = request.getParameter("publisher");
		int price = Integer.parseInt(request.getParameter("price"));
		int stock = Integer.parseInt(request.getParameter("stock"));
		int borrows = Integer.parseInt(request.getParameter("borrows"));
		int categorieId = Integer.parseInt(request.getParameter("categorieId"));
		
		ServletContext sc = request.getServletContext();
		BooksDao dao = (BooksDao) sc.getAttribute("booksDao");
		
		Books book = new Books(id, name, author, publisher, price, stock, borrows,categorieId);
		
		dao.update(book);
		
		response.sendRedirect(request.getContextPath() + "/manage/book.jsp");
	}

}
