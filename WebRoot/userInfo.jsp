<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Users"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>个人信息</title>
		<link rel="stylesheet" href="css/nav.css">
	</head>

	<body>
	<jsp:useBean id="usersDao" class="com.sdcet.library.dao.jdbc.UserDaoJDBC" scope="application"></jsp:useBean>
		<jsp:include page="nav.jsp"></jsp:include>
		<div class="ingredients">
			<div class="panel-heading">
				<div class="panel-title">个人信息</div>
			</div>
			<hr>
		</div>
			
			<form class="basic-grey">
				<label>
					<span>ID :</span>
					<input type="text" name="id" value="${user.id}" readonly="readonly"/>
				</label>
				<label>
					<span>用户名 :</span>
					<input type="text" name="loginName" value="${user.loginName}" readonly="readonly"/>
				</label>
				<label>
					<span>姓名 :</span>
					<input type="text" name="name" value="${user.name}" readonly="readonly"/>
				</label>
				<label>
					<span>性别 :</span>
					<input type="text" name="gender" value="${user.gender}" readonly="readonly"/>
				</label>
				<label>
					<span>联系方式 :</span>
					<input type="text" name="phone" value="${user.phone}" readonly="readonly"/>
				</label>
				<label>
					<span>&nbsp;</span>
					<a href="userupdate.jsp"><input type="button" class="button" value="修改" /></a>
					<a href="logout"><input type="button" class="button" value="退出" onclick="return confirm('确认退出吗？')"/></a>
					<a href="userlogin.html"><input type="button" class="button" value="登录" /></a>
					<a href="useradd.jsp"><input type="button" class="button" value="注册" /></a>
				</label>
			</form>
		
		<div class="copy">
			<p>版权所有：&copy;图书馆</p>
		</div>
	</body>

</html>