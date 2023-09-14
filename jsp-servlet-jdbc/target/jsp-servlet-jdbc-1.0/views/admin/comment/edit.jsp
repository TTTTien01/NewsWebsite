<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="commentAPI" value="/api-admin-comments" />
<c:url var="commentsList" value="/admin-comments" />
<html>
<head>
    <title>Thêm/Chỉnh sửa bình luận</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <div class="row">
             <div class = "col-sm-6">
                   <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/trang-chu" />">Trang chủ</a>
                </li>
                <li class="active">Thêm
             </div>
             
             <div class = "col-sm-6 text-right">
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
									    Thêm bình luận
									  </a>
             </div>
            </div>
            /Chỉnh sửa bình luận</li>
            </ul><!-- /.breadcrumb -->
        </div>
        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                      <c:if test="${not empty messageResponse}">
							<div class="alert alert-${alert}">
										${messageResponse}
							</div>
						</c:if>
                      <form id="formSubmit">
                      	<input type="hidden" value="${model.id}" id="id" name="id"/>
  
                          <div class="form-group">
                              <label class="col-sm-3 control-label no-padding-right">Nội dung bình luận</label>
                              <div class="col-sm-9">
                                  <input type="text" class="form-control" id="content" name="content" value="${model.content}"/>
                              </div>
                          </div>
                          
                          <br/>
                          <br/>
                          
                          <!-- danh sách người dùng -->
                          <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Người đăng</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="userId" name="userId">
                                    	<c:if test="${empty model.userId}">
                                    		<option value="">Chọn người đăng</option>
	                                        <c:forEach var="item" items="${users}">
	                                        	<option value="${item.id}">${item.userName}</option> 
	                                        </c:forEach>
                                    	</c:if>
                                    	
                                    	<c:if test="${not empty model.userId}">
                                    		<option value="">Chọn người đăng</option>
	                                        <c:forEach var="item" items="${users}">       
	                                        	<option value="${item.id}" <c:if test="${item.id == model.userId}">selected="selected"</c:if>>
	                                        		${item.userName}
	                                        	</option>
	                                        </c:forEach>
                                    	</c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                          
                          <!-- danh sách bài viết -->
                          <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Bài viết</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="newsId" name="newsId">
                                    	<c:if test="${empty model.newsId}">
                                    		<option value="">Chọn bài viết</option>
	                                        <c:forEach var="item" items="${news}">
	                                        	<option value="${item.id}">${item.title}</option> 
	                                        </c:forEach>
                                    	</c:if>
                                    	
                                    	<c:if test="${not empty model.newsId}">
                                    		<option value="">Chọn bài viết</option>
	                                        <c:forEach var="item" items="${news}">       
	                                        	<option value="${item.id}" <c:if test="${item.id == model.newsId}">selected="selected"</c:if>>
	                                        		${item.title}
	                                        	</option>
	                                        </c:forEach>
                                    	</c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            
                            <!-- kiểm duyệt -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Kiểm duyệt</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="invalid" name="invalid">
                                    	<option value="0" <c:if test="${model.invalid == 0}">selected="selected"</c:if> >
                                    		Hợp lệ
                                    	</option>
                                    	
                                    	<option value="1" <c:if test="${model.invalid == 1}">selected="selected"</c:if> >
                                       		Vi phạm tiêu chuẩn cộng đồng
                                       	</option>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                          
                          
                          
					 <div class="form-group mt-3">
					 	<div class="col-sm-3"></div>
                              <div class="col-sm-9">
                                  <c:if test="${not empty model.id}">
                                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật bình luận" id="btnAddOrUpdateNew"/>
                                  </c:if>
                                  <c:if test="${empty model.id}">
                                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm bình luận" id="btnAddOrUpdateNew"/>
                                  </c:if>
                              </div>
                          </div>
                      </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
	$('#btnAddOrUpdateNew').click(function (e) {
		e.preventDefault();
		const data = {};
		const formData = $('#formSubmit').serializeArray();
		$.each(formData, function (i, v) {
            data[v.name] = v.value;
        });
		
        const id = $('#id').val();
        if (id == "") {
            add(data);
        } else {
            update(data);
        }
	});
	
	function add(data) {
        $.ajax({
            url: '${commentAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${commentsList}?type=edit&id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${commentsList}?type=list&maxPageItems=5&page=1&message=error_system";
            }
        });
    }
	
    function update(data) {
        $.ajax({
            url: '${commentAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${commentsList}?type=edit&id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${commentsList}?type=list&maxPageItems=5&page=1&message=error_system";
            }
        });
    }
</script>
</body>
</html>
