<%@include file="/common/taglib.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="contactAPI" value="/api-admin-contacts" />
<c:url var="contactsList" value="/admin-contacts" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách liên hệ</title>
</head>
<body>
	<div class="main-content">
	<form action="<c:url value="/admin-news" />" id="formSubmit" method="get">
		<div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="<c:url value="/trang-chu" />">Trang chủ</a></li>
				</ul>
				<!-- /.breadcrumb -->
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
									  <%-- <a
									    flag="info"
									    class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
									    data-toggle="tooltip"
									    title="Thêm bài viết"
									    href='<c:url value="/admin-news?type=edit"/>'
									  >
									    <span>
									      <i class="fa fa-plus-circle bigger-110 purple"></i>
									    </span>
									  </a> --%>
									  <button
									    id="btnDelete"
									    type="button"
									    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
									    data-toggle="tooltip"
									    title="Xóa liên hệ"
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
										<th>Tên người đăng</th>
										<th>Email</th>
										<th>Số điện thoại</th>
										<th>Tiêu đề</th>
										<th>Thao tác</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="item" items="${model.listResult}">
										<tr>
											<td>
												<input type="checkbox" id="checkbox_${item.id}" value="${item.id}" />
											</td>
											<td>${item.fullName}</td>
											<td>${item.email}</td>
											<td>${item.phoneNumber}</td>
											<td>${item.title}</td>
											<td>
												<c:url var="editURL" value="/admin-contacts">
													<c:param name="type" value="edit"/>
													<c:param name="id" value="${item.id}"/>
												</c:url>
												
												<a class="btn btn-sm btn-info btn-edit" data-toggle="tooltip"
												   title="Xem chi tiết" href='${editURL}'>
												   <i class="fa fa-eye" aria-hidden="true"></i>
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
			const limit = 2;
			
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
		
		$('#btnDelete').click(function (e) {
			const isConfirmed = confirm('Bạn có chắc muốn xóa các liên hệ này?');
			
			if (isConfirmed) {				
				const data = {};
				const ids = $('tbody input[type=checkbox]:checked').map(function() {
					return $(this).val();
				}).get();
				data['ids'] = ids;
				deleteContact(data);
			}
		});
		
		function deleteContact(data) {
	        $.ajax({
	            url: '${contactAPI}',
	            type: 'DELETE',
	            contentType: 'application/json',
	            data: JSON.stringify(data),
	            success: function (result) {
	                window.location.href = "${contactsList}?type=list&maxPageItems=2&page=1&message=delete_success";
	            },
	            error: function (error) {
	            	window.location.href = "${contactsList}?type=list&maxPageItems=2&page=1&message=error_system";
	            }
	        });
	    }
	</script>
</body>
</html>