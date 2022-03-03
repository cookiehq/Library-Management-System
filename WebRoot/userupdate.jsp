<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Users"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>修改信息</title>
		<link rel="stylesheet" href="css/nav.css">
	</head>

	<body>
	<jsp:useBean id="usersDao" class="com.sdcet.library.dao.jdbc.UserDaoJDBC" scope="application"></jsp:useBean>
		<jsp:include page="nav.jsp"></jsp:include>
		<div class="ingredients">
			<div class="panel-heading">
				<div class="panel-title">修改信息</div>
			</div>
			<hr>
		</div>
			
			<form action="updateuser" method="post" class="basic-grey">
				<label>
					<span>ID :</span>
					<input type="text" name="id" value="${user.id}" readonly="readonly"/>
				</label>
				<label>
					<span>用户名 :</span>
					<input type="text" name="loginName" value="${user.loginName}" />
				</label>
				<label>
					<span>密码 :</span>
					<input type="text" name="password" value="${user.password}" />
				</label>
				<label>
					<span>姓名 :</span>
					<input type="text" name="name" value="${user.name}" />
				</label>
				<label>
					<span>性别 :</span>
					<input type="text" name="gender" value="${user.gender}" />
				</label>
				<label>
					<span>联系方式 :</span>
					<input type="text" name="phone" value="${user.phone}" />
				</label>
				<label>
					<span>&nbsp;</span>
					<input type="submit" class="button" value="修改" />
					<a href="logout"><input type="button" class="button" value="退出" /></a>
				</label>
			</form>
		
		<div class="copy">
			<p>版权所有：&copy;图书馆</p>
		</div>
	</body>

</html>