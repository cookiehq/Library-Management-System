<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Books"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="../css/min.css">
</head>

<body>
	<jsp:include page="../html/header.jsp"></jsp:include>
	<main>
		<article>
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">管理员信息</div>
				</div>
				<div class="panel-body">
					<form class="basic-grey">
						<label> 
							<span>ID :</span> 
							<input type="text"name="borrows" value="${admin.id}" readonly="readonly"/>
						</label> 
						<label> 
							<span>用户名 :</span> 
							<input type="text"name="borrows" value="${admin.loginName}" readonly="readonly"/>
						</label> 
						<label> 
							<span>姓名 :</span> 
							<input type="text"name="borrows" value="${admin.name}" readonly="readonly"/>
						</label> 
						<label> 
							<span>性别 :</span> 
							<input type="text"name="borrows" value="${admin.gender}" readonly="readonly"/>
						</label> 
						<label> 
							<span>联系方式 :</span> 
							<input type="text"name="borrows" value="${admin.phone}" readonly="readonly"/>
						</label> 
						<label> 
							<span>&nbsp;</span> 
							<a href="<%=request.getContextPath()%>/manage/logout">
								<input type="button" class="button" value="退出" onclick="return confirm('确认退出吗？')"/>
							</a>
						</label>
					</form>

				</div>
			</div>
			<jsp:include page="../html/footer.jsp"></jsp:include>
		</article>
		<jsp:include page="../html/adminaside.jsp"></jsp:include>
	</main>
</body>

</html>