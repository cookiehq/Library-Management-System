<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Books"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>图书搜索</title>
		<link rel="stylesheet" href="css/nav.css">
	</head>

	<body>
		<jsp:include page="nav.jsp"></jsp:include>
		<div class="ingredients">
			<div class="panel-heading">
				<div class="panel-title">图书搜索</div>
			</div>
			<div class="panel-body">
				<div class="pull-main">
					<form action="searchbookname" method="post">
						<input type="text" name="name" placeholder="请输入图书名称" />
						<input type="submit" class="button" value="搜索" />
					</form>
				</div>
			</div>
			<hr>
			<ul class="ingredientslist">
			
            <c:forEach items="${selbooksList}" var="book">
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