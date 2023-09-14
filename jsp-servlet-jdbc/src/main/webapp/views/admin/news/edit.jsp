<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<c:url var="newsAPI" value="/api-admin-news" />
<c:url var="newsList" value="/admin-news" />
<html>
<head>
    <title>Thêm/Chỉnh sửa bài viết</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <div class="row">
               <div class ="col-sm-6">
                 <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/trang-chu" />">Trang chủ</a>                </li>
                <li class="active">Thêm/Chỉnh sửa bài viết</li>
                
                
            </ul><!-- /.breadcrumb -->
               </div>
               <div class ="col-sm-6 text-right">
                   <a flag="info"
					class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
					data-toggle="tooltip"
					title="Thêm bài viết"
					href='<c:url value="/admin-news?type=edit"/>'>
						<span>
							<i class="fa fa-plus-circle bigger-110 purple"></i>
						</span>
			       Thêm mới bài viết</a>
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
						
						
                        <form method="post" 
                       		action="<c:url value='/admin-news' />"
                       		enctype="multipart/form-data">
                        	<input type="hidden" value="${model.id}" id="id" name="id"/>
                        	
                        	<div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-9">
                                    <select class="form-control" id="categoryCode" name="categoryCode">
                                    	<c:if test="${empty model.categoryCode}">
                                    		<option value="">Chọn thể loại</option>
	                                        <c:forEach var="item" items="${categories}">
	                                        	<option value="${item.code}">${item.name}</option> 
	                                        </c:forEach>
                                    	</c:if>
                                    	
                                    	<c:if test="${not empty model.categoryCode}">
                                    		<option value="">Chọn thể loại</option>
	                                        <c:forEach var="item" items="${categories}">       
	                                        	<option value="${item.code}" <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>
	                                        		${item.name}
	                                        	</option>
	                                        </c:forEach>
                                    	</c:if>
                                    </select>
                                </div>
                            </div>
                            <br/>
                            <br/>
                        
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="title" name="title" value="${model.title}" required/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            
                           	<div class="form-group">
							    <label class="col-sm-3 control-label no-padding-right">Ảnh bìa</label>
							    <div class="col-sm-9">
							        <input type="file" class="form-control" id="thumbnail" name="thumbnail" required/>
							    </div>
							</div>
							<br/>
							<br/>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-9">
                                    <input type="text" class="form-control" id="shortDescription" name="shortDescription" value="${model.shortDescription}" required/>
                                </div>
                            </div>
                            <br/>
                            <br/>
                            
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-9">                                 
                                    <textarea class="form-control" rows="" cols="" id="content" name="content" required>${model.content}</textarea>
                                </div>
                            </div>
                            <br/>
                            <br/>
							 <div class="form-group">
							 	<div class="col-sm-3"></div>
                                <div class="col-sm-9">
                                    <c:if test="${not empty model.id}">
                                        <input type="submit" class="btn btn-white btn-warning btn-bold" value="Cập nhật bài viết""/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="submit" class="btn btn-white btn-warning btn-bold" value="Thêm bài viết"/>
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
	let editor = '';
	
	$(document).ready(function(e) {
		editor = CKEDITOR.replace('content');
	});
</script>
</body>
</html>
