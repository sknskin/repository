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

    <title>Restaurant Menu</title>

    <!-- Template CSS -->
    <link rel="stylesheet" href="/resources/styles/css/style-libearty.css">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
  </head>
  
  <body id="home">

  <!-- Header include -->
  <jsp:include page="/WEB-INF/views/include/header.jsp" />

  <!-- Common jquery plugin -->
  <script src="/resources/styles/js/jquery-3.3.1.min.js"></script>
  
  <!--bootstrap working-->
  <script src="/resources/styles/js/bootstrap.min.js"></script>

	<!-- Navigator -->
	<section class="w3l-inner-banner-main">
	    <div class="about-inner blog ">
	        <div class="container">   
	        <div class="breadcrumbs-sub">
	            <ul class="breadcrumbs-custom-path">
	                <li class="right-side ">
	                <a href="/home" class="">Home</a><span class="fa fa-angle-right" aria-hidden="true"></span></li>
					<li class="active ">Menu</li>
	            </ul>
	        </div>
			</div>
		</div>
	</section>
	<!-- Navigator //-->
	
	<!-- Menu Contents Area -->
	<section class="w3l-services-6">
	  <div class="services-layout ">
	    <div class="container">
	    
	    <!-- Menu Tag Area -->
	    <!-- 1. 페이지 진입 시 전체 메뉴 노출하기 (완료) <br> 2. 태그 버튼 클릭 시 해당 태그의 카테고리 메뉴만 노출하기 (완료) <br> 3. 메뉴 클릭 시 디테일페이지 진입하기 (완료) -->
	     <div class="main-titles-head">
	      <h3 class="header-name "> About The Menu. </h3> <br>
	      <p class="tiltle-para"> 우리 레스토랑에서 운영 중인 메뉴 입니다. </p>
	      <p class="tiltle-para"> 원하시는 메뉴 카테고리를 선택하시고 클릭 시 해당 메뉴로 이동 합니다. </p> <br>
			
			
	    <!-- 카테고리 태그 버튼 -->
	    <section id="portfolio" class="section-bg">
	      <div class="container" data-aos="fade-up">
	
	        <header class="section-header">
	          <h3 class="section-title">${product.prodCategory}</h3>
	        </header>
	
		  <div class="row" data-aos="fade-up" data-aos-delay="100">
		      <div class=" col-lg-12">
		          <ul id="portfolio-flters">
		          <c:if test="${products.size()>0}">
		          
		           <button type="button" data-filter="all" class="btn btn-outline-info category-btn">ALL</button>
		           
		           <c:forEach var="category" items="${ categories }">
					
				   <button type="button" data-filter="${fn:replace(category, ' ', '')}" class="btn btn-outline-info category-btn">${category}</button>
			           
	           </c:forEach>
	          </c:if>
	          </ul>
	        </div>
	        </div>
	      </div>
		</section>
		<!-- 카테고리 태그 버튼 -->
	      	     
	    <!-- Menu Content Area -->
	    <section class="w3l-services-6">
		  <div class="services-layout ">
		   <div class="container">
		    <div class="blog-grids row">

		    <c:forEach var="product" items="${ products }">
		      <div class="col-lg-4 col-md-6 col-sm-6 blog-grid product-item" data-category="${ fn:replace(product.prodCategory, ' ', '') }" id="zoomIn">
		          <a href="#" class="go-detail-link" data-prod-id="${ product.prodId }">
		              <figure><img src="/resources/styles/images/b1.jpg" class="img-fluid" alt=""></figure>
		          </a>
		           
		          <div class="blog-info">	
		            <h3><a href="#" class="go-detail-link" data-prod-id="${ product.prodId }">${ product.prodName }</a> </h3>
		              <p class="para mt-3">${ product.prodDesc }</p>
		          
		          </div>
		      </div>
		    </c:forEach>
		    
		  	</div>
		    </div>
		  </div>
		</section>
  	    <!-- Menu Content Area -->
  	 			
		<!-- 페이징 -->
		<ul class="pagination justify-content-center mt-sm-5 mt-4 mb-0">
          <li class="page-item disable">
          	<a class="page-link page-prev" href="#previous" tabindex="-1">Previous</a>
          </li>
          
          <li class="page-item">
          	<a class="page-link page-number" data-pageno="1" href="#1">1</a>
          </li>
          
          <li class="page-item">
          	<a class="page-link page-number" data-pageno="2" href="#2">2</a>
          </li>
          
          <li class="page-item">
          	<a class="page-link page-number" data-pageno="3" href="#3">3</a>
          </li>
          <li class="page-item">
          	<a class="page-link page-number" data-pageno="4" href="#4">4</a>
          </li>
          
          <li class="page-item">
          	<a class="page-link page-number" data-pageno="5" href="#5">5</a>
          </li>
          
          <li class="page-item">
          	<a class="page-link page-number" data-pageno="6" href="#6">6</a>
          </li>
          
          <li class="page-item">
          	<a class="page-link page-next" href="#next">→</a>
          </li>
      	</ul>
  	    <!-- 페이징 -->
		
	    </div>
	  </div>
	  </div>
	</section>
	<!--  Menu Area -->

	<!-- move top -->
	<button onclick="topFunction()" id="movetop" title="Go to top">
		<span class="fa fa-long-arrow-up"></span>
	</button>
	<!-- move top -->
	
  	<!-- Footer Start -->
  	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
  	
  	
  	<!--Javascript -->
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
	  
		// Main navigation Active Class Add Remove
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
			
		<!-- 페이징 -->
		$(function () {
		  	$('.navbar-toggler').click(function () {
		    	$('body').toggleClass('noscroll');
		  	});
		  	
		  	// 사용할 변수 선언
		  	let currentCategory = "all";
		  	let currentPage = 1;
		  	let pageCount = 0;
		  	const pageSize = 6;
		  	
		  	const pageNoHtml = `<li class="page-item">
	          						<a class="page-link" data-pageno="-1" href="#"></a>
		          				</li>`
		  	
			// category-btn : All 버튼 클릭 시 이벤트 처리
		  	$('.category-btn').on('click', function(event) {
		  		currentPage = 1;
		  		
			  	$('div.product-item[data-category]').css('display', 'none');
		
			  	filter = $(this).data('filter');
			  	currentCategory = filter;
			  	
			  	// 선택된 카테고리의 1페이지 데이터 표시
			  	var menuCount = 0;
			  	if (filter === "all") {
			  		$('div.product-item[data-category]').slice(0, pageSize).css('display', '');
			  		menuCount = $('div.product-item[data-category]').length;
			  	} else {
				  	// $('div.product-item[data-category!=' + filter + ']').css('display', 'none');
				  	$('div.product-item[data-category=' + filter + ']').slice(0, pageSize).css('display', '');
				  	menuCount = $('div.product-item[data-category=' + filter + ']').length;
			  	}
			  	
			  	// 페이지 번호 표시 
			  	$('.pagination').empty();
			  	
			  	// 페이지 번호 표시 - 이전 페이지 
	          	const prevHtml = $(pageNoHtml);
	          	prevHtml.find('a').attr({'data-pageno': 'prev'}).addClass('page-prev').text("previous");
	          	$('.pagination').append(prevHtml);
	          	
	          	// 페이지 번호 표시 - 각 페이지 번호 
			  	pageCount = Math.ceil(menuCount / pageSize);
			  	for (var i = 0; i < pageCount; i++) {
			  		const html = $(pageNoHtml);
			  		html.find('a').attr({'data-pageno': i+1}).addClass('page-number').text(i+1);
			  		$('.pagination').append(html);
			  	}
			  	
			  	// 페이지 번호 표시 - 다음 페이지 
			  	const nextHtml = $(pageNoHtml);
			  	nextHtml.find('a').attr({'data-pageno': 'next'}).addClass('page-next').text("next");
		  		$('.pagination').append(nextHtml);
			  	
		  		// 페이지 번호 표시 - 현재 페이지 활성화 
			  	$('.page-link.page-number').parent().removeClass('active');
		  		$('.page-link.page-number[data-pageno=' + currentPage  + ']').parent().addClass('active');
		  	});
		  	
		    // pagination : 페이징 영역 버튼 클릭 시 동작하는 이벤트 처리기 
		  	$('.pagination').on('click', '.page-link.page-number, .page-link.page-prev, .page-link.page-next', function(event) {
		  		event.preventDefault();
		  		
		  		// 클릭된 페이지 번호 찾기 
		  		let pageNo = $(this).data('pageno');
		  		if (pageNo == "prev") {
		  			pageNo = currentPage <= 1 ? 1 : currentPage - 1;
		  		} else if (pageNo == "next") {
		  			pageNo = currentPage >= pageCount ? pageCount : currentPage + 1
		  		} else {
		  			pageNo = parseInt(pageNo);	
		  		}
		  		
		  		currentPage = pageNo;
		  		// alert(pageNo);
		  		
		  		
		  		// 현재 페이지 번호 활성화 
		  		$('.page-link.page-number').parent().removeClass('active');
		  		$('.page-link.page-number[data-pageno=' + pageNo  + ']').parent().addClass('active');
		  		
		  		// 표시될 메뉴 항목 선택하고 표시
		  		const start = (pageNo - 1) * pageSize;
		  		const end = start + pageSize;
		  		
		  		$('div.product-item[data-category]').css('display', 'none');
		  		
		  		if (currentCategory === "all") {
			  		$('div.product-item[data-category]').slice(start, end).css('display', '');
			  	} else {
				  	// $('div.product-item[data-category!=' + filter + ']').css('display', 'none');
				  	$('div.product-item[data-category=' + currentCategory + ']').slice(start, end).css('display', '');
			  	}
		  		
		  	});
		          				
		    $('.go-detail-link').on('click', function(event) {
		    	event.preventDefault();
		    	const prodId = $(this).data('prod-id');
		    	location.href = "/menu/menudetail?prodId=" + prodId + "&prodCategory=" + currentCategory;
		    })
		    
		    // 처음 시작할 때 all 버튼 강제 클릭
		    <c:choose>
		    <c:when test="${ empty prodCategory }">
		  	$(".category-btn[data-filter=all]").trigger('click');
		  	</c:when>
		  	<c:otherwise>
		  	$(".category-btn[data-filter=${fn:replace(prodCategory, ' ', '')}]").trigger('click');
		  	</c:otherwise>
		  	</c:choose>
		});
		<!-- 페이징 -->

	</script>
	
</body>
</html>