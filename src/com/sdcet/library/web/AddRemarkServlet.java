package com.sdcet.library.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sdcet.library.dao.BooksDao;
import com.sdcet.library.dao.RemarkDao;
import com.sdcet.library.dao.UserDao;
import com.sdcet.library.domain.Books;
import com.sdcet.library.domain.Remark;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/addremark")
public class AddRemarkServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		String nowdate = sdf.format(date);
		
		String content = request.getParameter("content");
		String author = request.getParameter("author");
		System.out.println(content);
		
		if ("".equals(author)) {
			System.out.println("".equals(author));
			response.sendRedirect("userlogin.html");
		}else{
		
			ServletContext sc = request.getServletContext();
			RemarkDao dao = (RemarkDao) sc.getAttribute("remarkDao");
			
			Remark remark = new Remark(content, author, nowdate);
			
			dao.add(remark);
			
			response.sendRedirect("forum.jsp");
		}
	}

}
