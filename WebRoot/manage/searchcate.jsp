<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
<link rel="stylesheet" type="text/css" href="../css/min.css">
</head>

<body>
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
						<form action="<%=request.getContextPath()%>/selectcatename"
							method="post">
							<input type="text" name="name" placeholder="请输入类别名称" /> <input
								type="submit" class="button" value="搜索" />
						</form>
					</div>
					<table class="bordered">
						<thead>
							<tr>
								<th>ID</th>
                                <th>类别名称</th>
                                <th>类别描述</th>
								<th>操作</th>
							</tr>
						</thead>

						<c:forEach items="${selcateList}" var="cate" >
                            <tr>
                                <td>${cate.id}</td>
                                <td>${cate.name}</td>
                                <td>${cate.description}</td>
								<td><a
									href="<%=request.getContextPath()%>/selectcateid?id=${cate.id}"><input
										type="button" class="upd-button" value="编辑" /></a> <a
									href="<%=request.getContextPath()%>/manage/catedelete?id=${cate.id}"><input
										type="button" class="del-button" value="删除" onclick="return confirm('确认删除此分类吗？')"/></a></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<jsp:include page="../html/footer.jsp"></jsp:include>
		</article>
		<jsp:include page="../html/cateaside.jsp"></jsp:include>
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