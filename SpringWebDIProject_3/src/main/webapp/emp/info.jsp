<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class=container>
		<h1 class=text-center>사원 정보</h1>
		<table class=table>
			<tr>
				<th>사번</th>
				<th>이름</th>
				<th>직위</th>
				<th>입사일</th>
				<th>급여</th>
			</tr>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td>${vo.empno }</td>
					<td>${vo.ename }</td>
					<td>${vo.job }</td>
					<td>${vo.dbday }</td>
					<td>${vo.sal }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>