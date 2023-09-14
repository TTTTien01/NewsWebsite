<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<div class="row mb-5">
		<div class="container">
			<h3 class="text-center my-5">Danh sách tin tức</h3>
			
			<form action="<c:url value="/trang-chu" />" id="formSubmit" method="get">
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
					
					<div class="col-12 mt-3 d-flex justify-content-center">					
						<ul class="pagination" id="pagination"></ul>
					</div>
					
					<input type="hidden" value="list" id="list" name="type" />
					<input type="hidden" value="" id="page" name="page" />
					<input type="hidden" value="" id="maxPageItems" name="maxPageItems" />
					<input type="hidden" value="" id="sortName" name="sortName" />
					<input type="hidden" value="" id="sortBy" name="sortBy" />
				</div>
			</form>
		</div>
	</div>

	<script type="text/javascript">
		$(function() {
			const currentPage = ${model.page};
			const totalPages = ${model.totalPages};
			const limit = 6;
			
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPages,
				startPage: currentPage,
				visiblePages : 10,
				first : "Trang đầu",
				last : "Trang cuối",
				prev : "Trước",
				next : "Sau",
				onPageClick : function(event, page) {
					if (page != currentPage) {
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('asc');
						$('#maxPageItems').val(limit);
						$('#formSubmit').submit();
					}
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		});
	
	</script>
</body>
</html>