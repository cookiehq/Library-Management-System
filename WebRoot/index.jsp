<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Books"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>首页</title>
	    <link rel="stylesheet" href="css/nav.css">

	</head>
	<body>
		<jsp:useBean id="booksDao" class="com.sdcet.library.dao.jdbc.BooksDaoJDBC" scope="application"></jsp:useBean>
		<jsp:include page="nav.jsp"></jsp:include>
        <div class="h2"><h2>近期最受欢迎图书</h2></div>
        
        <div class="index">
            <ul class="indexlist">
            <% 
            	List<Books> bookList = booksDao.findByBorrows();
            	request.setAttribute("bookList", bookList);
            %>
            <c:forEach items="${bookList}" var="book">
                <li><a href="#">
                    <div class="indeximg">
                    	<a href="bookInfo.jsp?id=${book.id}">
							<img src="images/${book.id}.jpg" width="220" height="180" ">
						</a>
                    </div>
                    <div class="indexdec">
                        	${book.name}
                    </div>
                </a></li>
            </c:forEach>
			</ul>
		</div>
		<div class="copy">
			<p>版权所有：&copy;图书馆</p>
		</div>
	</body>
</html>
