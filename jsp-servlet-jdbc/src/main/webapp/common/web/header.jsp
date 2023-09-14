<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Navigation -->


<!-- Topbar Start -->
<div class="container-fluid">
	<!-- Trên menu End -->
	<div class="row align-items-center py-2 px-lg-5">
		<div class="col-lg-4">
			<a href="" class="navbar-brand d-none d-lg-block">
				<h1 class="text-secondary">
					<span class="text-danger">Website</span>Tin Tức
				</h1>
			</a>
		</div>
	</div>
	<!-- TRên menu End -->
</div>
<!-- Topbar End -->



<!-- Navbar Start -->
<div class="container-fluid p-0 mb-3">
	<nav class="navbar navbar-expand-lg bg-light navbar-light ">
		<div class="collapse navbar-collapse px-0 px-lg-3 justify-content-center" id="navbarResponsive">
			<div class="navbar-nav col-9 justify-content-between">
				<ul class="navbar-nav">
					<li class="nav-item active ">
						<a class="nav-item nav-link" href="<c:url value="/trang-chu" />">
							Trang chủ
						</a>
					</li>
					
					<li class="nav-item active ">
						<a class="nav-item nav-link" href="<c:url value="/tin-tuc" />">
							Tin tức
						</a>
					</li>
					
					<%-- <c:forEach var="item" items="${categories}">
						<li class="nav-item active"><a class="nav-link"
							href="<c:url value="/tin-tuc?categoryCode=${item.code}" />">
								${item.name} <span class="sr-only">(current)</span>
						</a></li>
					</c:forEach> --%>
					<li class="nav-item active">
						<a class="nav-link"
							href="<c:url value="/lien-he" />">Liên hệ <span
								class="sr-only">(current)</span>
						</a>
					</li>
					
					<li class="nav-item d-flex align-items-center">
					<div class="dropdown">
  <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    Danh mục
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    
    <c:forEach items="${categories}" var="item">
    <a class="dropdown-item" href='<c:url value="/tin-tuc?categoryCode=${item.code}" />'>${item.name}</a>
					</c:forEach>
  </div>
</div>
					</li>
				</ul>
				
				<ul class="nav navbar-nav navbar-right align-items-center">
					<li class="nav-item active glyphicon glyphicon-user">
						<form action="<c:url value="/tin-tuc" />" method="get" class="d-flex">				
							<input class="form-control" type="text" name="search" id="search" placeholder="Tìm kiếm" />
							<button type="submit" class="btn">
								<i class="fas fa-search"></i>
							</button>
						</form>
					</li>
				
				<!-- Đã đăng nhập -->
					<c:if test="${not empty USERMODEL}">
						<li class="nav-item active">
						 <a class="nav-link"
							href='${USERMODEL.roleCode == "ADMIN" ? "/admin-home" : "/"}'>
								Xin chào, ${USERMODEL.fullName} 
						 </a>
						</li>
	
						<li class="nav-item active"><a class="nav-link"
							href='<c:url value="/thoat?action=logout"/>'>Thoát</a></li>
					</c:if>
	
	
					<!-- Chưa đăng nhập -->
					<c:if test="${empty USERMODEL}">
						<li class="nav-item active glyphicon glyphicon-user"><a
							class="nav-link" href='<c:url value="/dang-nhap?action=login"/>'>
								<span class="glyphicon glyphicon-user"></span>Đăng nhập <span
								class="sr-only">(current)</span>
						</a></li>
					</c:if>
				</ul>
			</div>

		</div>
</div>
</div>
</nav>
</div>
<!-- Navbar End -->
