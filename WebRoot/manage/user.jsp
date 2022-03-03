<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Users"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>类别管理</title>
<link rel="stylesheet" type="text/css" href="../css/min.css">
</head>

<body>
<jsp:useBean id="usersDao" class="com.sdcet.library.dao.jdbc.UserDaoJDBC" scope="application"></jsp:useBean>
	<jsp:include page="../html/header.jsp"></jsp:include>
	<main>
		<article>
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">图书列表</div>
				</div>
				<div class="panel-body">
					<table class="bordered">
						<thead>
							<tr>
								<th>ID</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>联系方式</th>
                                <th>操作</th>
							</tr>
						</thead>
						<%
                             List<Users> usersList = usersDao.findAll();
                             request.setAttribute("usersList", usersList);
                        %>

						<c:forEach items="${usersList}" var="user" >
                            <tr>
                                <td>${user.id}</td>
                                <td>${user.name}</td>
                                <td>${user.gender}</td>
                                <td>${user.phone}</td>
								<td>
									<a href="<%=request.getContextPath()%>/manage/userdelete?id=${user.id}">
										<input type="button" class="del-button" value="删除" onclick="return confirm('确定删除这个读者吗？')"/>
									</a>
								</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
			<jsp:include page="../html/footer.jsp"></jsp:include>
		</article>
		<jsp:include page="../html/useraside.jsp"></jsp:include>
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