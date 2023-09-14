<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="categoryAPI" value="/api-admin-categories" />
<c:url var="categoriesList" value="/admin-categories" />
<html>
<head>
    <title>Chỉnh sửa danh mục</title>
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
                <li class="active">Chỉnh sửa bài viết</li>
            </ul><!-- /.breadcrumb -->
              </div>
              
              <div class="col-sm-6 text-right">
                 <a
									    flag="info"
									    class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
									    data-toggle="tooltip"
									    title="Thêm danh mục"
									    href='<c:url value="/admin-categories?type=edit"/>'
									  >
									    <span>
									      <i class="fa fa-plus-circle bigger-110 purple"></i>
									    </span>
									    
									    Thêm danh mục
									  </a>
              </div>
            </div>
            
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
                              <label class="col-sm-3 control-label no-padding-right">Tên danh mục</label>
                              <div class="col-sm-9">
                                  <input type="text" class="form-control" id="name" name="name" value="${model.name}" required/>
                              </div>
                          </div>
                          <br/>
                          <br/>
                          
                          <div class="form-group">
                              <label class="col-sm-3 control-label no-padding-right">Mã danh mục</label>
                              <div class="col-sm-9">
                                  <input type="text" class="form-control" id="code" name="code" value="${model.code}" required/>
                              </div>
                          </div>
                          <br/>
                          <br/>
                          
					 <div class="form-group mt-3">
					 	<div class="col-sm-3"></div>
                              <div class="col-sm-9">
                                  <c:if test="${not empty model.id}">
                                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật danh mục" id="btnAddOrUpdateNew"/>
                                  </c:if>
                                  <c:if test="${empty model.id}">
                                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm danh mục" id="btnAddOrUpdateNew"/>
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
            addCategory(data);
        } else {
            updateCategory(data);
        }
	});
	
	function addCategory(data) {
        $.ajax({
            url: '${categoryAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${categoriesList}?type=edit&id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${categoriesList}?type=list&maxPageItems=5&page=1&message=error_system";
            }
        });
    }
	
    function updateCategory(data) {
        $.ajax({
            url: '${categoryAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${categoriesList}?type=edit&id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${categoriesList}?type=list&maxPageItems=5&page=1&message=error_system";
            }
        });
    }
</script>
</body>
</html>
