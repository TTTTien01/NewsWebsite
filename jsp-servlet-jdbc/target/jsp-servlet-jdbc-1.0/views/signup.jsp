<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>	

<c:url var="loginURL" value="/dang-nhap">
	<c:param name="action" value="login" />
</c:url>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng ký tài khoản</title>
</head>
<body>
	<div class="container">
		<div class="login-form">
			<div class="main-div">
				<c:if test="${not empty alert and not empty message}">
					<div class="alert alert-${alert}">
						${message}
					</div>
				</c:if>
				<form action="<c:url value='/dang-ky'/>" id="formSignUp" method="post">
					<h3 class="mb-3">ĐĂNG KÝ TÀI KHOẢN</h3>
				
					<div class="form-group">
						<input type="text" class="form-control" id="fullName" name="fullName"
							placeholder="Họ tên">
					</div>
				
					<div class="form-group">
						<input type="text" class="form-control" id="userName" name="userName"
							placeholder="Tên đăng nhập">
					</div>

					<div class="form-group">
						<input type="password" class="form-control" id="password" name="password"
							placeholder="Mật khẩu">
					</div>
					
					<input type="hidden" name="action" value="signup" />
					<button type="submit" class="btn btn-primary" >Đăng ký</button>
					<a class="mt-3" href="${loginURL}" >Đã có tài khoản? Đăng nhập ngay!</a>
				</form>
			</div>
		</div>
	</div>
</body>
</html>