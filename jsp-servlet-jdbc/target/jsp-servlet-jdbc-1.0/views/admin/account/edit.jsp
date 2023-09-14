<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="accountAPI" value="/api-admin-accounts" />
<c:url var="accountsList" value="/admin-accounts" />
<html>
<head>
    <title>Chỉnh sửa tài khoản</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs', 'fixed')}catch(e){}
            </script>
            <div class="row">
                <div class ="col-sm-6">
                    <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/trang-chu" />">Trang chủ</a>
                </li>
                <li class="active">Tài khoản</li>
            </ul><!-- /.breadcrumb -->
                </div>
                
                <div class ="col-sm-6 text-right">
                   <a
									    flag="info"
									    class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
									    data-toggle="tooltip"
									    title="Thêm tài khoản"
									    href='<c:url value="/admin-accounts?type=edit"/>'
									  >
						<span>
							<i class="fa fa-plus-circle bigger-110 purple"></i>
						</span>
			       Thêm mới tài khoản</a>
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
                              <label class="col-sm-3 control-label no-padding-right">Tài khoản</label>
                              <div class="col-sm-9">
                                  <input type="text" class="form-control" id="userName" name="userName" value="${model.userName}"/>
                              </div>
                          </div>
                          <br/>
                          <br/>
                          <br />
                          
                          <div class="form-group">
                              <label class="col-sm-3 control-label no-padding-right">Mật khẩu</label>
                              <div class="col-sm-9">
                                  <input type="text" class="form-control" id="password" name="password" value="${model.password}"/>
                              </div>
                          </div>
                          <br/>
                          <br/>
                          
                          <div class="form-group">
                              <label class="col-sm-3 control-label no-padding-right">Họ tên</label>
                              <div class="col-sm-9">
                                  <input type="text" class="form-control" id="fullName" name="fullName" value="${model.fullName}"/>
                              </div>
                          </div>
                          <br/>
                          <br/>
                          
                          <div class="form-group">
                             <label class="col-sm-3 control-label no-padding-right">Trạng thái</label>
                             <div class="col-sm-9">
                               <select class="form-control" id="status" name="status">  
                               	<option value="1" <c:if test="${model.status == 1}">selected="selected"</c:if>>
                               		Đang hoạt động
                               	</option>
                               	
                               	<option value="0" <c:if test="${model.status == 0}">selected="selected"</c:if>>
                               		Đã chặn
                               	</option>
                                 </select>
                             </div>
                            </div>
                            <br/>
                            <br/>
                          
                         <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">Vai trò</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="roleCode" name="roleCode">
                                	<c:if test="${empty model.roleCode}">
                                		<option value="">Chọn vai trò</option>
                                     	<c:forEach var="item" items="${roles}">
                                     		<option value="${item.code}">${item.name}</option> 
                                     	</c:forEach>
                                	</c:if>
                                	
                                	<c:if test="${not empty model.roleCode}">
                                		<option value="">Chọn vai trò</option>
                                     <c:forEach var="item" items="${roles}">       
                                     	<option value="${item.code}" <c:if test="${item.code == model.roleCode}">selected="selected"</c:if>>
                                     		${item.name}
                                     	</option>
                                     </c:forEach>
                                	</c:if>
                                </select>
                             </div>
                            </div>
                            <br/>
                            <br/>
                          
					 <div class="form-group mt-3">
					 	<div class="col-sm-3"></div>
                              <div class="col-sm-9">
                                  <c:if test="${not empty model.id}">
                                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Cập nhật thông tin" id="btnAddOrUpdateNew"/>
                                  </c:if>
                                  <c:if test="${empty model.id}">
                                      <input type="button" class="btn btn-white btn-warning btn-bold" value="Thêm tài khoản" id="btnAddOrUpdateNew"/>
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
            url: '${accountAPI}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${accountsList}?type=edit&id="+result.id+"&message=insert_success";
            },
            error: function (error) {
            	window.location.href = "${accountsList}?type=list&maxPageItems=5&page=1&message=error_system";
            }
        });
    }
	
    function update(data) {
        $.ajax({
            url: '${accountAPI}',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
            	window.location.href = "${accountsList}?type=edit&id="+result.id+"&message=update_success";
            },
            error: function (error) {
            	window.location.href = "${accountsList}?type=list&maxPageItems=5&page=1&message=error_system";
            }
        });
    }
</script>
</body>
</html>
