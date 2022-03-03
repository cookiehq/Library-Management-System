<%@page import="com.sdcet.library.domain.Remark"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<title>贴吧吐槽</title>
		<link rel="stylesheet" href="css/forum.css">
		<script type="text/javascript" charset="utf-8" src="ueditor/ueditor.config.js"></script>
	    <script type="text/javascript" charset="utf-8" src="ueditor/ueditor.all.min.js"> </script>
	    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	    <script type="text/javascript" charset="utf-8" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	
	    <script type="text/javascript">
	    	var ue = UE.getEditor('editor');
	    </script>
		
	</head>
	
	<body>
		<jsp:useBean id="remarkDao" class="com.sdcet.library.dao.jdbc.RemarkDaoJDBC" scope="application"></jsp:useBean>
		<jsp:include page="nav.jsp"></jsp:include>
		<div class="ingredients">
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">贴吧吐槽</div>
				</div>
				<div class="panel-body">
				<% 
            		List<Remark> remarkList = remarkDao.findAll();
            		request.setAttribute("remarkList", remarkList);
            	%>
	            	<c:forEach items="${remarkList}" var="remark">
	            		<div class="remark">
							<div class="name">
								<label>${remark.author}：</label>
							</div>
							<div class="text">
								<p>${remark.content}</p>
							</div>
							<div class="time">
								<p>${remark.date}</p>
							</div>
						</div>
						
					</c:forEach>
					<br><hr><br>
					<label>发表评论</label>
					<br>
					<br>
					<form action="addremark" method="post" class="basic-grey2" name="upfile">
						<div class="scr">
							<script id="editor" type="text/plain" name="content" style="height:300px;"></script>
						</div>
						<label>
							<span>&nbsp;</span>
							<input type="submit" class="button" value="Send" />
						</label>
						<input type="hidden" name="id" value="${user.id}"/>
						<input type="hidden" name="author" value="${user.loginName}"/>
					</form>

				</div>
			</div>
		</div>

		<div class="copy">
			<p>版权所有：&copy;图书馆</p>
		</div>
	</body>

</html>