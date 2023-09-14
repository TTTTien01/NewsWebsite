<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Liên hệ</title>
</head>
<body>

	<section class="my-5" id="contact">
	  <div class="container">
		<div class="row">
		  <div class="col-md-7">
	        <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3929.1078237348665!2d105.72025667329852!3d10.007951772901379!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31a08903d92d1d0d%3A0x2c147a40ead97caa!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBOYW0gQ-G6p24gVGjGoQ!5e0!3m2!1svi!2s!4v1692541114026!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
	      </div>
	
	      <div class="col-md-5">
	          	<h4>
	          		<strong>Gửi liên hệ</strong>
	          	</h4>
	          	
	          	<c:if test="${not empty message}">
					<div class="alert alert-${alert}">
								${message}
					</div>
				</c:if>
	          	
		        <form method="post" action="/lien-he">
		          <div class="form-group">
		            <input type="text" class="form-control" name="fullName" placeholder="Họ tên">
		          </div>
		          
		          <div class="form-group">
		            <input type="email" class="form-control" name="email" placeholder="Email">
		          </div>
		          
		          <div class="form-group">
		            <input type="tel" class="form-control" name="phoneNumber" placeholder="Số điện thoại">
		          </div>
		          
		          <div class="form-group">
		            <input type="text" class="form-control" name="title" placeholder="Tiêu đề">
		          </div>
		          
		          <div class="form-group">
		            <textarea class="form-control" name="content" rows="3" placeholder="Nội dung"></textarea>
		          </div>
		          
		          <button class="btn btn-default" type="submit" name="button">
		              <i class="fa fa-paper-plane-o" aria-hidden="true"></i> 
		              Gửi
		          </button>
		        </form>
	      </div>
	    </div>
	  </div>
	</section>
    
</body>
</html>