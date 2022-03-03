<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Categories"%>
<%@page import="com.sdcet.library.domain.Books"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>图书列表</title>
		<link rel="stylesheet" href="css/nav.css">
		<!--<link rel="stylesheet" href="../css/min.css">-->
	</head>

	<body>
		<jsp:useBean id="booksDao" class="com.sdcet.library.dao.jdbc.BooksDaoJDBC" scope="application"></jsp:useBean>
		<jsp:useBean id="cateDao" class="com.sdcet.library.dao.jdbc.CategorieDaoJDBC" scope="application"></jsp:useBean>
		<jsp:include page="nav.jsp"></jsp:include>
		
		<div class="ingredients">
			<div class="panel-heading">
				<div class="panel-title">图书列表</div>
			</div>
			<div class="panel-body">
				<div class="pull-left" ">
					<label>图书分类：</label>
					<% 
						List<Categories> cateList = cateDao.findAll();
						request.setAttribute("cateList", cateList);
					%>
					<select name="cid" onchange="window.location=this.value;">
						<c:forEach items="${cateList}" var="cate">
							<option value="?cid=${cate.id}" ${param.cid eq cate.id ? "selected" : ""}>${cate.name}</option>
						</c:forEach>
					</select>
				</div>
				<div class="pull-right "">
					<form action="searchbookname" method="post">
						<input type="text" name="name" placeholder="请输入图书名称" />
						<input type="submit" class="button" value="搜索" />
					</form>
				</div>
			</div>
			<hr>
			<ul class="ingredientslist">
			<% 
				int cid = 0;
				String sid = request.getParameter("cid");
					if (sid != null) {
						cid = Integer.parseInt(sid);
					}
            	List<Books> bookList = booksDao.findByCategorieId(cid);
            	request.setAttribute("bookList", bookList);
            %>
            <c:forEach items="${bookList}" var="book">
                <li>
					<a href="#">
						<div class="ingredientsimg">
							<a href="bookInfo.jsp?id=${book.id}">
								<img src="images/${book.id}.jpg" width="100" height="100">
							</a>
                    </div>
                    <div class="ingredientsdec ">
                        	<p>${book.name}</p>
                    </div>
                	</a>
                </li>
            </c:forEach>
				
			</ul>
		</div>

		<div class="copy">
			<p>版权所有：&copy;图书馆</p>
		</div>
	</body>

</html>