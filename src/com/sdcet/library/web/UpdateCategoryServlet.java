package com.sdcet.library.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.BooksDao;
import com.sdcet.library.dao.CategorieDao;
import com.sdcet.library.domain.Books;
import com.sdcet.library.domain.Categories;

/**
 * Servlet implementation class UpdateBookServlet
 */
@WebServlet("/manage/updatecate")
public class UpdateCategoryServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String description = request.getParameter("description");
		
		ServletContext sc = request.getServletContext();
		CategorieDao dao = (CategorieDao) sc.getAttribute("cateDao");
		Categories cate = new Categories(id, name, description);
		
		dao.upate(cate);
		
		response.sendRedirect(request.getContextPath() + "/manage/cate.jsp");
	}

}
