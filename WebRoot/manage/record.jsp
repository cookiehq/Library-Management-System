<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.sdcet.library.domain.Record"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>类别管理</title>
<link rel="stylesheet" type="text/css" href="../css/min.css">
</head>

<body>
 <jsp:useBean id="recordDao" class="com.sdcet.library.dao.jdbc.RecordDaoJDBC" scope="application"></jsp:useBean>
	<jsp:include page="../html/header.jsp"></jsp:include>
	<main>
		<article>
			<div class="panel">
				<div class="panel-heading">
					<div class="panel-title">借阅记录</div>
				</div>
				<div class="panel-body">
					<div class="pull-left"">
						<a href="borrowbook.jsp"><input type="button" class="button"
							value="借阅" /></a>
					</div>
					<div class="pull-right"">
						<form action="<%=request.getContextPath()%>/selectrecord"
							method="post">
							<input type="text" name="userid" placeholder="请输入读者ID" /> <input
								type="submit" class="button" value="搜索" />
						</form>
					</div>
					<table class="bordered">
						<thead>
							<tr>
								<th>图书ID</th>
                                <th>读者ID</th>
                                <th>借阅时间</th>
                                <th>到期时间</th>
                                <th>状态</th>
                                <th>操作</th>
							</tr>
						</thead>
						<%
                            List<Record> recordList1 = recordDao.overdue();
                            request.setAttribute("recordList1", recordList1);
                            
                            List<Record> recordList2 = recordDao.soonToExpire();
                            request.setAttribute("recordList2", recordList2);
                            
                            List<Record> recordList3 = recordDao.unexpired();
                            request.setAttribute("recordList3", recordList3);
                        %>

						<c:forEach items="${recordList1}" var="record" >
                            <tr class="red">
                                <td>${record.bookid}</td>
                                <td>${record.userid}</td>
                                <td>${record.ndate}</td>
                                <td>${record.mdate}</td>
                                <td>${record.state}</td>
                                <td>
                                	<a href="<%=request.getContextPath()%>/returnbook?bookid=${record.bookid}&userid=${record.userid}">
										<input type="button" class="bor-button" value="归还" />
									</a>
                                </td>
                            </tr>
                         </c:forEach>
                         <c:forEach items="${recordList2}" var="record" >
                            <tr class="yellow">
                                <td>${record.bookid}</td>
                                <td>${record.userid}</td>
                                <td>${record.ndate}</td>
                                <td>${record.mdate}</td>
                                <td>${record.state}</td>
                                <td>
                                	<a href="<%=request.getContextPath()%>/returnbook?bookid=${record.bookid}&userid=${record.userid}">
										<input type="button" class="bor-button" value="归还" />
									</a>
                                </td>
                            </tr>
                         </c:forEach>
                         <c:forEach items="${recordList3}" var="record" >
                            <tr class="green">
                                <td>${record.bookid}</td>
                                <td>${record.userid}</td>
                                <td>${record.ndate}</td>
                                <td>${record.mdate}</td>
                                <td>${record.state}</td>
                                <td>
                                	<a href="<%=request.getContextPath()%>/returnbook?bookid=${record.bookid}&userid=${record.userid}">
										<input type="button" class="bor-button" value="归还" />
									</a>
                                </td>
                            </tr>
                         </c:forEach>
                         
					</table>
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