<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<title>Insert title here</title>
<style type="text/css">
	.container{
		margin-top: 50px;
	}
	.row {
		margin: 0 auto;
		width: 960px;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>믿고보는 맛집리스트</h3>
		<hr>
		<div class="row">
			<c:forEach var="vo" items="${list }" varStatus="s">
				<c:if test="${s.index>=0 && s.index<12 }">
					  <div class="col-md-3">
					    <div class="thumbnail">
					      <a href="#">
					        <img src="${vo.poster }" alt="Fjords" style="width:100%">
					        <div class="caption">
					          <p style="font-size: 10px;">${vo.title }</p>
					        </div>
					      </a>
					    </div>
					  </div>
				</c:if>
			</c:forEach>
		</div>
		
		<h3>지역별 맛집리스트</h3>
		<hr>
		<div class="row">
			<c:forEach var="vo" items="${list }" varStatus="s">
				<c:if test="${s.index>=12 && s.index<18 }">
					  <div class="col-md-4">
					    <div class="thumbnail">
					      <a href="#">
					        <img src="${vo.poster }" alt="Fjords" style="width:100%">
					        <div class="caption">
					          <p style="font-size: 10px;">${vo.title }</p>
					        </div>
					      </a>
					    </div>
					  </div>
				</c:if>
			</c:forEach>
		</div>
		
		<h3>믿고보는 맛집리스트</h3>
		<hr>
		<div class="row">
			<c:forEach var="vo" items="${list }" varStatus="s">
				<c:if test="${s.index>=18 && s.index<30 }">
					  <div class="col-md-3">
					    <div class="thumbnail">
					      <a href="#">
					        <img src="${vo.poster }" alt="Fjords" style="width:100%">
					        <div class="caption">
					          <p style="font-size: 10px;">${vo.title }</p>
					        </div>
					      </a>
					    </div>
					  </div>
				</c:if>
			</c:forEach>
		</div>
	</div>
</body>
</html>