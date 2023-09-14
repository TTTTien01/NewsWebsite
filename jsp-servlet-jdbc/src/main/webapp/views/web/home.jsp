<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="src/main/webapp/template/web/css/style2.css"
	rel="stylesheet">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap"
	rel="stylesheet">

<!-- Font Awesome -->
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css"
	rel="stylesheet">

<title>Trang chủ</title>
</head>
<body>

	<div class="row mb-5">
		<div class="container">
			<div class="container-fluid py-3">
				<div class="container">
					<div class="row">
						<div class="col-lg-12">
							<div class="row mb-3">
								<!-- Tin tức phổ biến -->
								<div class="col-12">
									<div
										class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
										<h3 class="m-0">Tin tức phổ biến</h3>
										<a
											class="text-secondary font-weight-medium text-decoration-none"
											href="<c:url value="/tin-tuc" />">Xem tất cả</a>
									</div>
								</div>

								<c:forEach items="${popularNews}" var="item">
									<div class="col-lg-4">
										<div class="position-relative mb-4">
											<img class="img-fluid w-100" 
												src="<c:url value="${contextPath}/${item.thumbnail}" />"
												style="object-fit: cover; hieght: 250px ">
											<div class="overlay position-relative bg-light">
												<div class="mb-2" style="font-size: 14px;">
													<a href="">${item.categoryName}</a> <span class="px-1">/</span>
													<span>${item.createdDate}</span> 

													<p><i class="fas fa-eye"></i> ${item.views}</p>
												</div>
												<a class="h4"
													href="<c:url value="/tin-tuc?id=${item.id}" />">${item.title}</a>
												<p class="m-0 ">${item.shortDescription}</p>
											</div>
										</div>
									</div>
								</c:forEach>


							</div>
						</div>
						<br> <br>
						<!-- Tin tức mới nhất -->
						<div class="col-lg-12">
							<div class="row mb-3">
								<div class="col-12">
									<div
										class="d-flex align-items-center justify-content-between bg-light py-2 px-4 mb-3">
										<h3 class="m-0">Mới nhất</h3>
										<a
											class="text-secondary font-weight-medium text-decoration-none"
											href="<c:url value="/tin-tuc" />">Xem tất cả</a>
									</div>
								</div>

								<%-- <c:forEach items="${lastestNews}" var="item">
									<c:set var="shortDescription" value="${item.shortDescription}" />
									<c:set var="words" value="0" />
									<c:forTokens items="${item.shortDescription}" delims=" "
										var="word">
										<c:set var="words" value="${words + 1}" />
										<c:if test="${words == 50}">
											<c:set var="shortDescription"
												value="${shortDescription.substring(0, shortDescription.indexOf(word) - 1)}" />
										</c:if>
									</c:forTokens>
									<div class="col-lg-4">
										<div class="position-relative mb-3">
											<img class="img-fluid w-100"
												src="<c:url value="${contextPath}/${item.thumbnail}" />"
												style="object-fit: cover;">
											<div class="overlay location-relative bg-light">
												<div class="mb-2" style="font-size: 14px;">
													<a href="">${item.categoryName}</a> <span class="px-1">/</span>
													<span>${item.createdDate}</span> <br> <img
														style="height: 1px" src="/news/images.png" />
													<p>${item.views}</p>
												</div>
												<a class="h4" href="">${item.title}</a>
												<p class="m-0">${shortDescription}</p>
											</div>
										</div>
									</div>
								</c:forEach> --%>


								<c:forEach items="${lastestNews}" var="item">
									<div class="col-lg-4">
										<div class="position-relative mb-3">
											<img class="img-fluid w-100"
												src="<c:url value="${contextPath}/${item.thumbnail}" />"
												style="object-fit: cover; height: 250px">
											<div class="overlay position-relative bg-light">
												<div class="mb-2" style="font-size: 14px;">
													<a href="">${item.categoryName}</a> <span class="px-1">/</span>
													<span>${item.createdDate}</span> <br> 

													<p><i class="fas fa-eye"></i> ${item.views}</p>
												</div>
												<a class="h4"
													href="<c:url value="/tin-tuc?id=${item.id}" />">${item.title}</a>
												<p class="m-0">${item.shortDescription} </p>
											</div>
										</div>
									</div>
								</c:forEach>

							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>