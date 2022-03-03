<%@page import="com.sdcet.library.domain.Books"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>图书借阅</title>
		<link rel="stylesheet" href="css/bookInfo.css">
	</head>

	<body>
		<jsp:useBean id="booksDao" class="com.sdcet.library.dao.jdbc.BooksDaoJDBC" scope="application"></jsp:useBean>
		<jsp:include page="nav.jsp"></jsp:include>
		<div class="ingredients">
			<div class="main">
			<% 
				
				int id = Integer.parseInt(request.getParameter("id"));
				Books book = booksDao.findById(id);
            	request.setAttribute("book", book);
			%>
				<div class="left">
					<img src="images/${book.id}.jpg" width="230" height="300" />
				</div>
				<div class="right">
					<form action="addcode" method="post" class="basic-grey">
						<h1>${book.name}</h1>

						<label>
							<span>作者 </span>
							<input type="text" name="author" value="${book.author}"/>
						</label>
						<label>
							<span>出版社 </span>
							<input type="text" name="publisher" value="${book.publisher}"/>
						</label>
						<label>
							<span>价格 </span>
							<input type="text" name="price" value="${book.price}"/>
						</label>
						<label>
							<span>借阅次数 </span>
							<input type="text" name="borrows" value="${book.borrows}"/>
						</label>
						
						<label>
							<span>&nbsp;</span>
							<c:choose>
								<c:when test="${num==1}">
								</c:when>
								<c:otherwise>
									<input type="submit" class="button" value="借阅" />
								</c:otherwise>
							</c:choose>
							
						</label>
						<input type="hidden" name="bookid" value="${book.id}">
						<input type="hidden" name="userid" value="${user.id}">
						<c:if test="${code.id!=admin.id}">
							<script type="text/javascript">
                 				function myFunction(){
                          		alert("您的借阅码：${code.id}\n请用此借阅码到图书馆借书\n到期时间：${code.mdate}\n逾期无效${num}"); 
                				}
                				window.onload = myFunction;
         					</script>
						</c:if>
					</form>
				</div>
			</div>
		</div>
		<div class="copy">
			<p>版权所有：&copy;图书馆</p>
		</div>
	</body>

</html>