<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<c:url var="commentAPI" value="/api-admin-comments" />
<c:url var="commentsList" value="/admin-comments" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bình luận của người dùng</title>
</head>
<body>
	<div class="main-content">
	<form action="<c:url value="/admin-comments" />" id="formSubmit" method="get">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="<c:url value="/trang-chu" />">Trang chủ</a>
					</li>
				</ul>
				<!-- /.bread crumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty messageResponse}">
							<div class="alert alert-${alert}">
								${messageResponse}
							</div>
						</c:if>
						
						<div class="widget-box table-filter">
							<div class="table-btn-controls">
								<div class="pull-right tableTools-container">
									<div class="dt-buttons btn-overlap btn-group">
									  <a
									    flag="info"
									    class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
									    data-toggle="tooltip"
									    title="Thêm bình luận"
									    href='<c:url value="/admin-comments?type=edit"/>'
									  >
									    <span>
									      <i class="fa fa-plus-circle bigger-110 purple"></i>
									    </span>
									  </a>
									  <button
									    id="btnDelete"
									    type="button"
									    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
									    data-toggle="tooltip"
									    title="Xóa bình luận"
									  >
									    <span>
									      <i class="fa fa-trash-o bigger-110 pink"></i>
									    </span>
									  </button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			
				<div class="row">
					<div class="col-xs-12">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th></th>
										<th>Bài viết</th>
										<th>Người đăng</th>
										<th>Nội dung</th>
										<th>Thao tác</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${model.listResult}">
										<tr>
											<td>
												<input type="checkbox" id="checkbox_${item.id}" value="${item.id}" />
											</td>
											<td>${item.newsId}</td>
											<td>${item.userName}</td>
											<td>${item.content}</td>
											<td>
												<!-- /admin-comments?type=edit&id=${item.id} -->
												<c:url var="editURL" value="/admin-comments">
													<c:param name="type" value="edit"/>
													<c:param name="id" value="${item.id}"/>
												</c:url>
												
												<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
												   title="Cập nhật bình luận" href='${editURL}'>
												   <i class="fa fa-pencil-square-o" aria-hidden="true"></i>
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>

							<ul class="pagination" id="pagination"></ul>
							<input type="hidden" value="list" id="list" name="type" />
							<input type="hidden" value="" id="page" name="page" />
							<input type="hidden" value="" id="maxPageItems" name="maxPageItems" />
							<input type="hidden" value="" id="sortName" name="sortName" />
							<input type="hidden" value="" id="sortBy" name="sortBy" />
						</div>
					</div>
				</div>
			</div>
		</div>
		</form>
	</div>
	<!-- /.main-content -->

	<!-- pagination -->
	<script type="text/javascript">
		$(function() {
			const currentPage = ${model.page};
			const totalPages = ${model.totalPages};
			const limit = 5;
			
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
						$('#sortBy').val('');
						$('#maxPageItems').val(limit);
						$('#formSubmit').submit();
					}
				}
			}).on('page', function(event, page) {
				console.info(page + ' (from event listening)');
			});
		});
		
		$('#btnDelete').click(function (e) {
			const isConfirmed = confirm('Bạn có chắc muốn xóa các bình luận này không?');
			
			if (isConfirmed) {
				const data = {};
				const ids = $('tbody input[type=checkbox]:checked').map(function() {
					return $(this).val();
				}).get();
				data['ids'] = ids;
				deleteComment(data);
			}
			
		});
		
		function deleteComment(data) {
	        $.ajax({
	            url: '${commentAPI}',
	            type: 'DELETE',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            success: function (result) {
	                window.location.href = "${commentsList}?type=list&maxPageItems=5&page=1&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href = "${commentsList}?type=list&maxPageItems=5&page=1&message=error_system";
	            }
	        });
	    }
	</script>
</body>
</html>