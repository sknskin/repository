<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!doctype html>
<html lang="ko">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Restaurant Home</title>

    <!-- Template CSS -->
    <link rel="stylesheet" href="resources/styles/css/style-libearty.css">
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
  </head>
  <body id="home">

	<!-- ======= Header ======= -->
  	<jsp:include page="/WEB-INF/views/include/header.jsp" />
  	<!-- End Header -->

<script src="resources/styles/js/jquery-3.3.1.min.js"></script> <!-- Common jquery plugin -->
<!--bootstrap working-->
<script src="resources/styles/js/bootstrap.min.js"></script>

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
  </script>
  <!--//MENU-JS-->
<!-- disable body scroll which navbar is in active -->
<script>
$(function () {
  $('.navbar-toggler').click(function () {
    $('body').toggleClass('noscroll');
  })
});
</script>
<!-- disable body scroll which navbar is in active -->

<section class="w3l-hero-headers-9" id="home">
  <div class="slide text-center header11" data-selector="header11">
      <div class="container">   
      <div class="banner-text ">
              <h5 class=" ">Only taste is <br>real for food</h5>
              <p class=" ">Adipi sicing elit. Quia, aliquid voluptatum corporis Dicta. Deleniti possimus culpa nemo asperiores aperiam mollitia, maiores Lorem ipsum dolor.</p>
              <a href="/menu/list" class="btn logo-button top-margin">View Our Menu</a>
          </div>
      </div>
  </div>
</section>
<section class="w3l-teams-15">
	<div class="team-single-main ">
		<div class="container">

		<div  class="row">
				<div class="column2 image-text col-lg-6">
					<div class="nature-row ">
						<h5><a href="/about" >
							Enjoy Our Delicious Food</a></h5>
					<p class="para  text ">
						Mollitia placeat modi explicabo voluptatum corporis unde Dicta, provident
						Rem adipisci Mollitia placeat modi.</p>
					</div>
					<div class="nature-row ">
						<h5><a href="/about">
							All Different Types </a></h5>
					<p class="para  text ">
						Mollitia placeat modi explicabo voluptatum corporis unde Dicta, provident
						Rem adipisci Mollitia placeat modi.</p>
					</div>
					<div class="nature-row ">
						<h5><a href="/about">Experience new flavors</a></h5>
					<p class="para  text ">
						Mollitia placeat modi explicabo voluptatum corporis unde Dicta, provident
						Rem adipisci Mollitia placeat modi.</p>
					</div>
				</div>
			<div class="column2 image-text col-lg-6">
				<img src="resources/styles/images/b1.jpg" alt="product" class="img-responsive ">
			</div>
		</div>
		</div>
		</div>
	</div>
</section>
<section class="w3l-specification-6">
    <div class="specification-layout ">
        <div class="container">
            <div class="main-titles-head ">
                <h3 class="header-name ">
					Our Special Feature
                </h3>
                <p class="tiltle-para  ">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Hic fuga sit illo modi aut aspernatur tempore laboriosam saepe dolores eveniet.</p>
                <br /><br />
            </div>
                    <div class="call-grids-w3 d-grid">
                        <div class="grids-1  grids-effect-2">
                            <span class="fa fa-beer"></span>
                            <h4><a href="#!" class="title-head">Well Decorated</a></h4>
                            <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed Ut blandit. Duis feugiat tortor sed.</p>
                        </div>
                        <div class="grids-1  grids-effect-2">
                            <div class="color-white">
                                <span class="fa fa-cutlery"></span>
                            <h4><a href="#!" class=" title-head">Breakfast</a></h4>
                            <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed  Ut blandit. Duis feugiat tortor sed.</p>
                        </div>
                    </div>
                        <div class="grids-1 grids-effect-2">
                            <span class="fa fa-handshake-o"></span>
                            <h4><a href="#!" class="title-head">Expert Chef</a></h4>
                            <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed  Ut blandit. Duis feugiat tortor sed.</p>
                        </div>
                        <div class="grids-1  grids-effect-2">
                            <div class="color-white">
                                <span class="fa fa-spoon"></span>
                            <h4><a href="#!" class=" title-head">Lunch</a></h4>
                            <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed  Ut blandit. Duis feugiat tortor sed.</p>
                        </div>
                    </div>
                            <div class="grids-1 grids-effect-2">
                                <span class="fa fa-smile-o"></span>
                                <h4><a href="#!" class="title-head">Quick Serve</a></h4>
                                <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed Ut blandit. Duis feugiat tortor sed.</p>
                            </div>
                            
                        <div class="grids-1  grids-effect-2">
                            <div class="color-white">
                                <span class="fa fa-apple"></span>
                            <h4><a href="#!" class=" title-head">Dinner</a></h4>
                            <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed  Ut blandit. Duis feugiat tortor sed.</p>
                        </div>
                    </div>
                            <div class="grids-1 grids-effect-2">
                                <span class="fa fa-diamond"></span>
                                <h4><a href="#!" class="title-head title-head">Delicious Food</a></h4>
                                <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed  Ut blandit. Duis feugiat tortor sed.</p>
                            </div>  
                        <div class="grids-1  grids-effect-2">
                            <div class="color-white">
                                <span class="fa fa-thumbs-o-up"></span>
                            <h4><a href="#!" class=" title-head">Desert</a></h4>
                            <p class="para">Vivamus a ligula quam. Duis feugiat tortor sed  Ut blandit. Duis feugiat tortor sed.</p>
                        </div>
                    </div>
                  </div>
        </div>
</section>

<section class="w3l-call-to-action_9">
    <div class="call-w3">
        <div class="container text-center">
            <div class="title-head">
            <h3>Enjoy Our<br>
                Delicious Food</h3>
            <p >Fugit tempora dolor dolore recusandae aut at cum autem esse neque. Consequuntur dolor sequi nulla corrupti blanditiis similique</p>
        </div>
          
       </div>
       </div>
</section>
	
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
			
			
	    <!-- ======= Portfolio Section ======= -->
	    <section id="portfolio" class="section-bg">
	      <div class="container" data-aos="fade-up">
	
	        <header class="section-header">
	          <h3 class="section-title">${product.prodCategory}</h3>
	        </header>
	
		  <div class="row" data-aos="fade-up" data-aos-delay="100">
		      <div class=" col-lg-12">
		          <ul id="portfolio-flters">
		          <c:if test="${products.size()>0}">
		          
		           <!-- <button type="button" data-filter="all" class="btn btn-outline-info category-btn">ALL</button> -->
		           
		           <c:forEach var="category" items="${ categories }">
					
					<button type="button" data-filter="${fn:replace(category, ' ', '')}" class="btn btn-outline-info category-btn">${category}</button>
			           
	           </c:forEach>
	          </c:if>
	          </ul>
	        </div>
	        </div>
	      </div>
   	  	
		</section>
	      	     
	     <!-- Menu Content Area -->
	     <section class="w3l-services-6">
		  <div class="services-layout ">
		    <div class="container">
		    <div class="blog-grids row">

		    <c:forEach var="product" items="${ products }">
		      <div class="col-lg-4 col-md-6 col-sm-6 blog-grid product-item" data-category="${ fn:replace(product.prodCategory, ' ', '') }" id="zoomIn">
		          <a href="/menu/menudetail?prodId=${ product.prodId }">
		              <figure><img src="/resources/styles/images/b1.jpg" class="img-fluid" alt=""></figure>
		          </a>
		           
		          <div class="blog-info">	
		              <h3><a href="/menu/menudetail?prodId=${ product.prodId }">${ product.prodName }</a> </h3>
		              <p class="para mt-3">${ product.prodDesc }</p>
		          
		          </div>
		          
		      </div>
		      </c:forEach>

		  	</div>
		    </div>
		  </div>
		</section>
  	    <!-- Menu Content Area -->
	    </div>
	  </div>
	  </div>
	 	
	</section>
	
	<!--  Menu Area -->


  <!-- ======= Footer ======= -->
  <jsp:include page="/WEB-INF/views/include/footer.jsp" />
  <!-- End Footer -->


<!-- move top -->
<button onclick="topFunction()" id="movetop" title="Go to top">
	<span class="fa fa-long-arrow-up"></span>
</button>

	<script>
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
		$(function () {
			<!-- disable body scroll which navbar is in active -->
		  	$('.navbar-toggler').click(function () {
		    	$('body').toggleClass('noscroll');
		  	})
		
		  	$('.category-btn').on('click', function(event) {
			  
			  	$('div.product-item[data-category]').css('display', '');
		
			  	filter = $(this).data('filter');
			  	/* if (filter === "all") {
				  	return;
			  	} */
			  	// alert(filter);
			  
			  	$('div.product-item[data-category!=' + filter + ']').css('display', 'none');
		  	
		  	});

		});
	  	<!-- disable body scroll which navbar is in active -->
	  	
	</script>
	</body>

</html>