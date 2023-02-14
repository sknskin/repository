<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!doctype html>
<html lang="ko">
  
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Restaurant Menu Detail</title>

    <!-- Template CSS -->
    <link rel="stylesheet" href="/resources/styles/css/style-libearty.css">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
  </head>
  
  <body id="home">

  <!-- Header include -->
  <jsp:include page="/WEB-INF/views/include/header.jsp" />

	<!-- Navigator -->
	<section class="w3l-inner-banner-main">
	    <div class="about-inner blog ">
	        <div class="container">   
	        <div class="breadcrumbs-sub">
	            <ul class="breadcrumbs-custom-path">
	                <li class="right-side "><a href="list" class="">Menu</a>
	                <span class="fa fa-angle-right" aria-hidden="true"></span></li>
					<li class="active ">Detail</li>
	            </ul>
	        </div>
			</div>
		</div>
	</section>
	<!-- Navigator //-->
	
	<!-- Main Contents -->
	<section class="w3l-blog-single">
	  <div class="sec-padding ">
	    <div class="container">
	      <div class="d-grid grid-colunm-2">
        
        <!-- left side blog post content -->
        <div class="single-left">
           <div class="single-left1">
	
			<!-- 메뉴의 각 컬럼의 데이터를 이하 내용에 주입해서 매번 디테일 페이지에 보여줄 수 있게끔 구현 -->
			<div class="row">
				
				<div class="column2 image-text col-lg-6">
					<img src="/resources/styles/images/b1.jpg" alt="product" class="img-responsive ">
				</div>
						
				<div class="column2 image-text col-lg-6">
						
					<div class="nature-row">
						
						<br>
						<button type="button" class="btn btn-outline-info"> ${ product.prodCategory } </button> <!-- 카테고리명 출력 -->
						<br><br>
						
						<h2> ${ product.prodName } </h2> 		<!-- 메뉴이름 -->
						<br>
						
						<h4> ${ product.prodPrice } 원 </h4>		<!-- 메뉴가격 -->
						<br>
						
						<h6><strong> 재료 및 원산지 </strong> : <br> ${ product.prodIngredients } </h6> 	<!-- 메뉴재료 -->
						<br>
						
						<h6><strong> 메뉴소개 </strong> : <br> ${ product.prodDesc } </h6> 			<!-- 메뉴설명 -->
						<br>
						
					</div>
					
				</div>
			
			</div>
            </div>
            
            <!-- 이전 메뉴, 다음 메뉴 
            		prodId > prodCategory > menulist -->
            <!-- 이전버튼 : 클릭 시 현재페이지의 카테고리의 이전 메뉴로 이동 -->
			<!-- 다음버튼 : 클릭 시 현재페이지의 카테고리의 다음 메뉴로 이동 -->
            <nav class="pagination post-navigation row mt-5">
                <div class="page-item post-prev col-6">
                
                	<%-- <a href=“/menu/menudetail?prodId=<%= product.prodId - 1 %>”> pre </a> --%>
                    <%-- <a href="/menu/menudetail?prodId=${ product.prodId }" rel="prev"> --%>
                    <a href="/menu/menudetail?prodId=${ product.prodId }&type=prev&prodCategory=${prodCategory}" rel="prev">
                        <h5> Prev Menu </h5>
                        
                    </a>
                </div>
                
                <div class="page-item post-next col-6 text-right">
                    <a href="/menu/menudetail?prodId=${ product.prodId}&type=next&prodCategory=${prodCategory}" rel="next">
                        <h5> Next Menu </h5>
                    </a>
                </div>
            </nav>
			<!-- 이전 메뉴, 다음 메뉴 -->
			
			<!-- 고객 의견 받기 -->
            <div class="testi-top mt-5 pt-3">

                <h4 class="post-content-title">메뉴들은 어떠셨나요? <br>고객님의 소중한 의견은 우리 레스토랑을 성장시킵니다.</h4>
                
                <div class="form-commets mt-5"> 
                    <form action="#" method="post">
                        <div class="media-form">
                            <input type="text" name="Name" required="required" placeholder="고객님의 성함을 입력하세요" >
                            <input type="email" name="Email" required="required" placeholder="이메일 주소를 입력하세요" >
                        </div>
                        <textarea name="Message" required="required" placeholder="Write your comments here"></textarea>
                    
                        <div class="text-right">
                            <button class="btn btn-style " type="submit">의견보내기</button>
                        </div>

                    </form>
                </div>
            </div>
        	
        </div>

	        <!-- 카테고리 버튼 클릭 시 해당 카테고리 태그의 메뉴로 이동하고, 
	        	 Conunt에는 메뉴들의 총 갯수를 보여주고 싶은데...
	        	 그냥 카테고리명만 볼 수 있도록 하고싶다.. -->
	        <div class="right-side-bar">
	            <aside class="posts p-4 ">
	                <h3 class="aside-title">Choose Categories</h3>
	                <ul class="category">
	                	
	                  <c:forEach var="category" items="${ categories }">
	                    <li><a href="/menu/list?prodCategory=${ category } ">
	                    <span class="btn btn-outline-info category-btn">${ category }</span>
	                    <label> ${ category } </label></a></li>
	                  </c:forEach>
	                  
	                </ul>
	            </aside>
        	</div>
        	<!-- //right side bar -->
        	
			</div>
		</div>
		</div>
	</section>
	<!-- Main Contents -->

	<!-- move top -->
	<button onclick="topFunction()" id="movetop" title="Go to top">
		<span class="fa fa-long-arrow-up"></span>
	</button>
	<!-- move top -->
	
  	<!-- Footer Start -->
  	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
  	<!-- End Footer -->
	
  	<!-- Common jquery plugin -->		
  	<script src="/resources/styles/js/jquery-3.3.1.min.js"></script>
   
  	<!--bootstrap working-->
  	<script src="/resources/styles/js/bootstrap.min.js"></script>
	
	<!--/MENU-JS-->
	<script>
		$(window).on("scroll", function () {
		var scroll = $(window).scrollTop();
	  
		if (scroll >= 80) {
			$("#site-header").addClass("nav-fixed");
		    } else {
			$("#site-header").removeClass("nav-fixed");
				}
		});
	  
		//Main navigation Active Class Add Remove
		$(".navbar-toggler").on("click", function () {
		  $("header").toggleClass("active");
		});
		$(document).on("ready", function () {
		  if ($(window).width() > 991) {
			$("header").removeClass("active");
		  }
		  $(window).on("resize", function () {
			if ($(window).width() > 991) {
			  $("header").removeClass("active");
			}
		  });
		});
		<!--//MENU-JS-->
	
		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function () {
			scrollFunction()
		};
	
		function scrollFunction() {
			if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
				document.getElementById("movetop").style.display = "block";
			} else {
				document.getElementById("movetop").style.display = "none";
			}
		}
	
		// When the user clicks on the button, scroll to the top of the document
		function topFunction() {
			document.body.scrollTop = 0;
			document.documentElement.scrollTop = 0;
		}
		<!-- /move top -->
		
	</script>
</body>
</html>