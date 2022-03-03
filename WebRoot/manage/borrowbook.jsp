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
					<div class="panel-title">借阅图书</div>
				</div>
				<div class="panel-body">
					<form action="<%=request.getContextPath()%>/codeidcheck"
						method="post" class="basic-grey">
						<label> 
							<span>借阅码 :</span> 
							<input type="text" name="id" placeholder="请输入借阅码" value="${param.codemessage eq 1 ? "借阅码不存在" : ""}"/>
						</label> 
						<label> 
							<span>图书编号 :</span> 
							<input type="text" name="bookid" placeholder="请输入图书编号" value="${param.bookid}"/>
						</label> 
						<label> 
							<span>读者编号 :</span> 
							<input type="text" name="userid" placeholder="请输入读者编号" />
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
		<jsp:include page="../html/borrowaside.jsp"></jsp:include>
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