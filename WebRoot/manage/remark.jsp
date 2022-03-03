<%@page import="com.sdcet.library.domain.Remark"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>评论管理</title>
<link rel="stylesheet" type="text/css" href="../css/min.css">
</head>

<body>
 <jsp:useBean id="remarkDao" class="com.sdcet.library.dao.jdbc.RemarkDaoJDBC" scope="application"></jsp:useBean>
	<jsp:include page="../html/header.jsp"></jsp:include>
	<main>
		<article>
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">评论列表</div>
				</div>
				<div class="panel-body">
					<table class="bordered">
						<thead>
							<tr>
								<th>ID</th>
                                <th>内容</th>
                                <th>作者</th>
                                <th>时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<%
                            List<Remark> remarkList = remarkDao.findAll2();
                            request.setAttribute("remarkList", remarkList);
                        %>

						<c:forEach items="${remarkList}" var="remark" >
                            <tr>
                                <td>${remark.id}</td>
                                <td>${remark.content}</td>
                                <td>${remark.author}</td>
                                <td>${remark.date}</td>
								<td>
									<a href="<%=request.getContextPath()%>/manage/remarkdelete?id=${remark.id}">
										<input type="button" class="del-button" value="删除" onclick="return confirm('确认删除这个评论吗？')"/>
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<jsp:include page="../html/footer.jsp"></jsp:include>
		</article>
		<jsp:include page="../html/remarkaside.jsp"></jsp:include>
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