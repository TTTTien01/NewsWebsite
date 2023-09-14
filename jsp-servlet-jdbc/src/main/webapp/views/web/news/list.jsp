<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Tin tức</title>
</head>
<body>
	<!-- Breadcrumb Start -->
    <div class="container-fluid">
        <div class="container">
            <nav class="breadcrumb bg-transparent m-0 p-0">
                <a class="breadcrumb-item" href="<c:url value="/trang-chu" />">Trang chủ</a>
                <a class="breadcrumb-item" href="<c:url value="/tin-tuc" />">Tin tức</a>
            </nav>
        </div>
    </div>
    <!-- Breadcrumb End -->

    
    <!-- News With Sidebar Start -->
    <div class="container-fluid py-3">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="row">
                        <div class="col-12">
                           	<c:if test="${listByCategory == true}">
	                            <div class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
	                           		<h3 class="m-0">${categoryName}</h3>
	                           		<a class="text-secondary font-weight-medium text-decoration-none" href="<c:url value="/tin-tuc" />">Xem tất cả</a>
      		                    </div>
                           	</c:if>                                
                        </div>
                        
                        
                        <c:forEach var="item" items="${model.listResult}">
	                        <div class="col-lg-4">
	                            <div class="position-relative mb-4">
	                                <img style="height: 250px" class="img-fluid w-100 img-responsive" src="<c:url value="${contextPath}/${item.thumbnail}" />" style="object-fit: cover;">
	                                <div class="overlay position-relative bg-light">
	                                    <div class="mb-2" style="font-size: 14px;">
	                                        <a href="<c:url value="/tin-tuc?categoryCode=${item.categoryCode}" />">
	                                        	${item.categoryName}
	                                        </a>
	                                        <span class="px-1">/</span>
	                                        <span>${item.createdDate}</span>
	                                        <br>
			                                <p><i class="fas fa-eye"></i> ${item.views}</p>
	                                    </div>
	                                    <a class="h4" href="<c:url value="/tin-tuc?id=${item.id}" />">${item.title}</a>
	                                    <p class="m-0">${item.shortDescription}</p>
	                                </div>
	                            </div>
	                        </div>
                        </c:forEach>
                        
                        
                        	<form 
                        		class="mt-2 col-12 d-flex justify-content-center" 
                        		action="<c:url value="/tin-tuc" /><c:if test="${listByCategory == true}">?categoryCode=${currentCaterogyCode}</c:if>" 
                        		id="formSubmit" method="get">		
								<ul class="pagination" id="pagination"></ul>
								
								<input type="hidden" value="" id="page" name="page" />
								<input type="hidden" value="" id="maxPageItems" name="maxPageItems" />
								<input type="hidden" value="" id="sortName" name="sortName" />
								<input type="hidden" value="" id="sortBy" name="sortBy" />
						    </form>                        
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- News With Sidebar End -->
	    
	    


    <!-- Back to Top -->
    <a href="trang-chu" class="btn btn-dark back-to-top"><i class="fa fa-angle-up"></i></a>
    
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
						$('#sortName').val('');
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