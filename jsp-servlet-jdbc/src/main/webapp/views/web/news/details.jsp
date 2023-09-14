<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<c:url value="/tin-tuc?id=${model.id}" var="currentURL" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Tin tức</title>

</head>
<body>


	<!-- News With Sidebar Start -->
	<div class="container-fluid py-3">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<!-- News Detail Start -->
					<div class="position-relative mb-3">
						<div class="overlay position-relative bg-light">
							<div class="mb-3">
								${model.createdDate}
								<p><i class="fas fa-eye"></i> ${model.views}</p>
							</div>
							<div>
								<h3 class="text-center">${model.title}</h3>
								<p class="my-4">
									<i>${model.shortDescription}</i>
								</p>

								<div>${model.content}</div>
							</div>
						</div>
					</div>
					<!-- News Detail End -->

					<!-- Comment List Start -->
					<div class="bg-light mb-3" style="padding: 30px;">
						<h3 class="mb-4">Comments</h3>
						<div class="media mb-4">
							<div id="comment-section" class="container">
								<c:forEach items="${comments}" var="comment">
									<div class="panel panel-default my-3 px-3 border-left">
										<div class="panel-heading d-flex align-items-center mb-2">
											<h5 class="panel-title mb-0 mr-2">${comment.userName}</h5>
											<small class="text-muted"><i>${comment.createdDate}</i></small>
										</div>
										<div class="panel-body">
											<%-- <p>${comment.invalid}</p>--%>
											<c:if test="${comment.invalid == 1}">
												<p class="text-muted">
													<i>Bình luận đã bị ẩn do vi phạm tiêu chuẩn cộng đồng</i>
												</p>
											</c:if>

											<c:if test="${comment.invalid == 0}">
												<p>${comment.content}</p>
											</c:if>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>

					</div>
					<!-- Comment List End -->
					<c:if test="${not empty USERMODEL}">
						<div class="col-12 d-flex flex-column">
							<h4>Đóng góp ý kiến</h4>

							<form id="formSubmit">
								<div class="form-floating mb-3 mt-3">
									<textarea class="form-control" id="content" name="content"
										placeholder="Nội dung"></textarea>
								</div>
								<input type="button" class="btn btn-primary" value="Đăng"
									id="postCommentBtn" />
							</form>
						</div>
					</c:if>

					<c:if test="${empty USERMODEL}">
						<div class="col-12 d-flex flex-column">
							<a class="my-3" href="<c:url value='/dang-nhap?action=login' />">Đăng
								nhập để bình luận</a>
						</div>
					</c:if>

				</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<!-- News With Sidebar End -->



	<!--<div class="contaner mt-3">
		<div class="col-12">
			<h3 class="text-center">${model.title}</h3>
			<p class="my-4">
				<i>${model.shortDescription}</i>
			</p>

			<div>${model.content}</div>
		</div>

		<!-- Comments part 
	<div class="col-12 d-flex flex-column mt-3">
		<h4>Bình luận</h4>

		<div id="comment-section" class="container">
			<c:forEach items="${comments}" var="comment">
				<div class="panel panel-default my-3 px-3 border-left">
					<div class="panel-heading d-flex align-items-center mb-2">
						<h5 class="panel-title mb-0 mr-2">${comment.userName}</h5>
						<small class="text-muted"><i>${comment.createdDate}</i></small>
					</div>
					<div class="panel-body">
						<%-- <p>${comment.invalid}</p>--%>
						<c:if test="${comment.invalid == 1}">
							<p class="text-muted">
								<i>Bình luận đã bị ẩn do vi phạm tiêu chuẩn cộng đồng</i>
							</p>
						</c:if>

						<c:if test="${comment.invalid == 0}">
							<p>${comment.content}</p>
						</c:if>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<c:if test="${not empty USERMODEL}">
		<div class="col-12 d-flex flex-column">
			<h4>Đóng góp ý kiến</h4>

			<form id="formSubmit">
				<div class="form-floating mb-3 mt-3">
					<textarea class="form-control" id="content" name="content"
						placeholder="Nội dung"></textarea>
				</div>
				<input type="button" class="btn btn-primary" value="Đăng"
					id="postCommentBtn" />
			</form>
		</div>
	</c:if>

	<c:if test="${empty USERMODEL}">
		<div class="col-12 d-flex flex-column">
			<a class="my-3" href="<c:url value='/dang-nhap?action=login' />">Đăng
				nhập để bình luận</a>
		</div>
	</c:if>


	</div>
	<!-- /.col-lg-9 -->

	<script>
		$(document).ready(function() {
			let alreadyIncreaseView = false;
			
	      $(window).on('scroll', function() {
    	 	if (!alreadyIncreaseView) {
    	 		const windowHeight = $(window).height(); // Chiều cao cửa sổ trình duyệt
    	        const scrollPos = $(window).scrollTop(); // Vị trí scroll hiện tại

    	        const halfWindowHeight = windowHeight / 2;
    	        const targetScrollPos = halfWindowHeight; // Vị trí scroll cần kiểm tra (nửa màn hình)

    	        if (scrollPos >= targetScrollPos) {
    	          // Khi scroll xuống nửa màn hình
    	          console.log("gọi api tăng view!");
    	          
    	          increaseViews();
    	          
		    	 alreadyIncreaseView = true;
    	        }
    	 	}
	        
	      });
	    });
	
		$('#postCommentBtn').click(function(e) {
			e.preventDefault();

			const data = {};
			const formData = $('#formSubmit').serializeArray();

			$.each(formData, function(i, v) { //dong for
				data[v.name] = v.value;
			});

			addComment(data);
		});

		function addComment(data) {
			$.ajax({
				url : "/api-comments?newsid=${model.id}",
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					
					const commentSection = $('#comment-section');
					
					commentSection.append(
						`
						<div class="panel panel-default my-3 px-3 border-left">
							<div class="panel-heading d-flex align-items-center mb-2">
								<h5 class="panel-title mb-0 mr-2">\${result.createdBy}</h5>
								<small class="text-muted"><i>\${result.createdDate}</i></small>
							</div>
							<div class="panel-body">
								<p>\${result.content}</p>
							</div>
						</div>
						`
					);
					
					$('#content').val('');
					$('#content').trigger('focus');
					
				},
				error: function(error) {
					console.log(error);
				}
			});
		}
		
		function increaseViews() {
			$.ajax({
				url : "/news-views-increment?id=${model.id}",
				type : 'GET',
				contentType : 'application/json',
				dataType : 'json',
				success : function(result) {
					
					console.log("Tăng view thành công!")
					
				},
				error: function(error) {
					console.log("Tăng view thất bại:" + error);
				}
			});
		}
	</script>
</body>
</html>