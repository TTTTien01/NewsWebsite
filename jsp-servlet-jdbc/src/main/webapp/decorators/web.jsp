<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><dec:title default="Trang chủ" /></title>

	<!-- css -->
	<link
		href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css' />"
		rel="stylesheet" type="text/css" media="all" />
	
		
		<link href="<c:url value='/template/web/css/style2.css' />"
		rel="stylesheet" type="text/css" media="all" />
		
		<!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700;900&display=swap" rel="stylesheet">   

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.0/css/all.min.css" rel="stylesheet">
		
	<script type="text/javascript" src="<c:url value='/template/web/jquery/jquery.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/template/web/bootstrap/js/bootstrap.bundle.min.js' />"></script>
	
 	<script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>	

</head>
<body>
	<!-- header -->
	<%@ include file="/common/web/header.jsp"%>
	<!-- header -->

	<dec:body />

	<!-- footer -->
	<%@ include file="/common/web/footer.jsp"%>
	<!-- footer -->
	
</body>
</html>