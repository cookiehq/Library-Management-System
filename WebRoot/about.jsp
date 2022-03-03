<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>关于我们</title>
		<link rel="stylesheet" href="css/nav.css">
		<style type="text/css">
			.tu{
			 width: 800px;
			 margin: 0px auto;
			}
			.miaoshu{
				width: 800px;
				text-indent: 2;
				margin: 0px auto;
				font-family: "微软雅黑";
				font-size: x-large;
			}
			
			</style>
	</head>
	<body>
		<!--nav-->
		<jsp:include page="nav.jsp"></jsp:include>
	   <div class="tu">
		   <img src="images/11.jpg"width="800px" height="400px">
		</div>
	   <div class="mid">
			<div class="left">
				
			</div>
			<div class="right">
				
			</div>
		</div>
		<div class="miaoshu">
			<p>
				我们此次制作了图书管理系统，在这里，我们对于被借阅书籍的较多的次数的
			书籍排列出来，我们对图书进行了分类管理有用户的以及管理员的，
			用户可以对自己喜欢看的哪一类的图书想借阅，可以通过对书名或书籍的编号进行查询
			从而得到想要借的图书。我们对图书设置了借阅期限等功能，对图书的增，删，改，查等功能
			进行了实现。希望你能在借阅书籍的过程中，更加的便利。
			</p>
			
			
		</div>
			
				<div class="copy">
			<p>版权所有：&copy;书籍大库</p>
		</div>
	</body>
</html>
