<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tin tức</title>
</head>
<body>
	<div class="row mb-5">

		<div class="container">
			<h3 class="text-center my-5">${categoryName}</h3>
			<div class="row">	
				<c:forEach var="item" items="${model.listResult}">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card">
					  		<img height="300" class="card-img-top img-responsive" src="<c:url value="${contextPath}/${item.thumbnail}" />" alt="Card image cap">
					  		<div class="card-body">
			    				<h5 class="card-title">${item.title}</h5>
						    	<p class="card-text">${item.shortDescription}</p>
						    	<a href="<c:url value="/tin-tuc?id=${item.id}" />" class="btn btn-primary">Xem chi tiết</a>
					  		</div>
						</div>
					</div>
				</c:forEach>
				
			</div>
			<!-- /.row -->

		</div>
		<!-- /.col-lg-9 -->

	</div>
	<!-- /.row -->
</body>
</html>