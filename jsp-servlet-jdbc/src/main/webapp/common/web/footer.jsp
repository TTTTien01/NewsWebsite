<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Footer 
<footer class="py-5 bg-dark">
	<div class="container">	
		<p class="m-0 text-center text-white">Copyright &copy; Web Tin Tức 2023</p>
	</div>
	.container 
</footer>-->

<!-- Footer Start -->
    <div class="container-fluid bg-light pt-5 px-sm-3 px-md-5">
        <div class="row">
            <div class="col-lg-4 col-md-6 mb-5">
                <a href="index.html" class="navbar-brand">
                    <h1 class="mb-2 mt-n2 display-5 text-uppercase"><span class="text-primary">Website</span>Tin Tức</h1>
                </a>
                <p>Trực tiếp bóng đá là trang chuyên trực tuyến hàng loạt các giải bóng đá trực tiếp siêu mượt, mang đến những trải nghiệm chất lượng nhất cho người hâm mộ bóng đá Việt Nam.

</p>
                <div class="d-flex justify-content-start mt-4">
                    <a class="btn btn-outline-secondary text-center mr-2 px-0" style="width: 38px; height: 38px;" href="#"><i class="fab fa-twitter"></i></a>
                    <a class="btn btn-outline-secondary text-center mr-2 px-0" style="width: 38px; height: 38px;" href="#"><i class="fab fa-facebook-f"></i></a>
                    <a class="btn btn-outline-secondary text-center mr-2 px-0" style="width: 38px; height: 38px;" href="#"><i class="fab fa-linkedin-in"></i></a>
                    <a class="btn btn-outline-secondary text-center mr-2 px-0" style="width: 38px; height: 38px;" href="#"><i class="fab fa-instagram"></i></a>
                    <a class="btn btn-outline-secondary text-center mr-2 px-0" style="width: 38px; height: 38px;" href="#"><i class="fab fa-youtube"></i></a>
                </div>
            </div>
            <div class="col-lg-3 col-md-6 mb-5">
                <h4 class="font-weight-bold mb-4">DANH MỤC</h4>
                <div class="d-flex flex-wrap m-n1">
                    <c:forEach var="item" items="${categories}">
						<a class="btn btn-sm btn-outline-secondary m-1"
							href="<c:url value="/tin-tuc?categoryCode=${item.code}" />">
								${item.name} <span class="sr-only">(current)</span>
						</a>
					</c:forEach>
                </div>
            </div>
            
            <div class="col-lg-5 col-md-4 mb-6">
                <h4 class="font-weight-bold mb-4">LIÊN HỆ</h4>
                <div class="d-flex flex-column justify-content-start" >
	        <iframe width="450px" height="250px" padding="5px" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3929.1078237348665!2d105.72025667329852!3d10.007951772901379!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31a08903d92d1d0d%3A0x2c147a40ead97caa!2zVHLGsOG7nW5nIMSQ4bqhaSBo4buNYyBOYW0gQ-G6p24gVGjGoQ!5e0!3m2!1svi!2s!4v1692541114026!5m2!1svi!2s" width="600" height="450" style="border:0;" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade" ></iframe>
                </div>
            </div>
           
        </div>
         <hr/>
    </div>
    
    <div class="container-fluid py-4 px-sm-3 px-md-5">
        <p class="m-0 text-center">
            &copy; <a class="font-weight-bold" href="#">Website Tin Tức</a>.Thiết kế bởi <a class="font-weight-bold" href="https://htmlcodex.com">Nhóm 3 - DH19TIN04</a>
        </p>
    </div>
    <!-- Footer End -->
