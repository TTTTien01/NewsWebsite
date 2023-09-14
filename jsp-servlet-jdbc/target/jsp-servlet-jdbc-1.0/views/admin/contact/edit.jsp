<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
<%-- <c:url var="newsAPI" value="/api-admin-news" />
<c:url var="newsList" value="/admin-news" /> --%>
<html>
<head>
    <title>Chi tiết liên hệ</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/trang-chu" />">Trang chủ</a>
                </li>
                <li class="active">Chi tiết liên hệ</li>
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
						
						<h6>Chi tiết liên hệ</h6>
						
                        <table class="table">
						  <tbody>
						    <tr>
						      <th>Id</th>
						      <td>${model.id}</td>
						    </tr>
						    
						    <tr>
						      <th>Tên người gửi</th>
						      <td>${model.fullName}</td>
						    </tr>
						    
						    <tr>
						      <th>Email</th>
						      <td>${model.email}</td>
						    </tr>
						    
						    <tr>
						      <th>Số điện thoại</th>
						      <td>${model.phoneNumber}</td>
						    </tr>
						    
						    <tr>
						      <th>Tiêu đề</th>
						      <td>${model.title}</td>
						    </tr>
						    
						    <tr>
						      <th>Nội dung</th>
						      <td>${model.content}</td>
						    </tr>
						  </tbody>
						</table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
