<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
					<div class="panel-title">图书类别添加</div>
				</div>
				<div class="panel-body">
					<form action="<%=request.getContextPath()%>/manage/addcate"
						method="post" class="basic-grey">
						<label> 
							<span>类别编号 :</span> 
							<input type="text" name="id" placeholder="请输入图书类别编号" />
						</label> 
						<label> 
							<span>类别名称 :</span> 
							<input type="text" name="name" placeholder="请输入图书类别名称" />
						</label> 
						<label> 
							<span>类别描述 :</span> 
							<input type="text" name="description" placeholder="请输入图书类别描述" />
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