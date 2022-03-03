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
import com.sdcet.library.dao.CategorieDao;
import com.sdcet.library.domain.Books;
import com.sdcet.library.domain.Categories;

/**
 * Servlet implementation class SelectBookServlet
 */
@WebServlet("/selectcatename")
public class SelectCategoryServletByName extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");

		ServletContext sc = request.getServletContext();
		CategorieDao dao = (CategorieDao) sc.getAttribute("cateDao");
		List<Categories> cate = dao.findByName(name);
		sc.setAttribute("selcateList", cate);
	
		response.sendRedirect(request.getContextPath() + "/manage/searchcate.jsp");
	}

}
