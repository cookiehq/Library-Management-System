<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.utils.PageBean"%>
<%@page import="com.sdcet.library.domain.Books"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<link rel="stylesheet" type="text/css" href="../css/min.css">
</head>

<body>
	<jsp:useBean id="booksDao"
		class="com.sdcet.library.dao.jdbc.BooksDaoJDBC" scope="application"></jsp:useBean>
	<jsp:include page="../html/header.jsp"></jsp:include>
	<main>
		<article>
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">搜索结果</div>
				</div>
				<div class="panel-body">
					<div class="pull-left"">
						<a href="addbook.jsp"><input type="button" class="button"
							value="添加" /></a>
					</div>
					<div class="pull-right"">
						<form action="<%=request.getContextPath()%>/selectbookname"
							method="post">
							<input type="text" name="name" placeholder="请输入图书名称" /> <input
								type="submit" class="button" value="搜索" />
						</form>
					</div>
					<table class="bordered">
						<thead>
							<tr>
								<th>ID</th>
								<th>图书名称</th>
								<th>作者</th>
								<th>出版社</th>
								<th>图书类别</th>
								<th>单价(元)</th>
								<th>库存</th>
								<th>借阅数量</th>
								<th>操作</th>
							</tr>
						</thead>

						<c:forEach items="${selbookList}" var="book">
							<tr>
								<td>${book.id}</td>
								<td>${book.name}</td>
								<td>${book.author}</td>
								<td>${book.publisher}</td>
								<td>${book.categorie.name}</td>
								<td>${book.price}</td>
								<td>${book.stock}</td>
								<td>${book.borrows}</td>
								<td>
									<a href="<%=request.getContextPath()%>/selectbookid?id=${book.id}">
										<input type="button" class="upd-button" value="编辑" />
									</a>
									<a href="<%=request.getContextPath()%>/manage/bookdelete?id=${book.id}">
										<input type="button" class="del-button" value="删除" onclick="return confirm('确认删除这本书吗？')"/>
									</a>
									<a href="borrowbook.jsp?bookid=${book.id}">
										<input type="button" class="bor-button" value="借阅" />
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<jsp:include page="../html/footer.jsp"></jsp:include>
		</article>
		<jsp:include page="../html/bookaside.jsp"></jsp:include>
	</main>
</body>

</html>
<script>
	function logout() {
		if (window.confirm('是否退出?')) {
			window.location.href = 'login.html';
		} else {
			return false;
		}
	}
</script>