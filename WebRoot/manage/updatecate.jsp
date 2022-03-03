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
					<div class="panel-title">编辑图书</div>
				</div>
				<div class="panel-body">
					<form action="<%=request.getContextPath()%>/manage/updatecate"
						method="post" class="basic-grey">
						<label> 
							<span>类别编号 :</span> 
							<input type="text" name="id" placeholder="请输入图书类别编号" value="${selcate.id}"/>
						</label> 
						<label> 
							<span>类别名称 :</span> 
							<input type="text" name="name" placeholder="请输入图书类别名称" value="${selcate.name}" />
						</label> 
						<label> 
							<span>类别描述 :</span> 
							<input type="text" name="description" placeholder="请输入图书类别描述" value="${selcate.description}"/>
						</label> 
						<label> 
							<span>&nbsp;</span> 
							<input type="submit" class="button" value="提交" />
						</label>
					</form>

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