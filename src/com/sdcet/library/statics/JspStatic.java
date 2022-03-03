package com.sdcet.library.statics;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * 接收用户请求，生成或者直接返回html
 *
 */
@WebServlet("/JspStatic")
public class JspStatic extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public JspStatic() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=UTF-8");
    	if (request.getParameter("id") != null) {
            String fileName = request.getParameter("id") + ".html";
            String filePath = getServletContext().getRealPath("/") + fileName;
            File chapterFile = new File(filePath);
            if (chapterFile.exists()) {
                System.out.println("html页面存在，直接跳转");
                response.sendRedirect(fileName);
                return;
            }
            
            
            System.out.println("新生成html页面");
            //TODO 这里可调用service查询页面上需要的数据，然后封装到request里面
            request.setAttribute("time", new java.util.Date());
            request.setAttribute("id", request.getParameter("id"));
            new CreateStaticHTMLPage().create(request, response, getServletContext(), fileName, filePath,
                    "/index.jsp");
        }
    }

}