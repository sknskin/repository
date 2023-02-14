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

    <title>About Restaurant</title>

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
<!-- //bootstrap working-->
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


<!-- breadcrumbs -->
    <section class="w3l-inner-banner-main">
        <div class="about-inner about ">
            <div class="container">   
            <div class="breadcrumbs-sub">
                <ul class="breadcrumbs-custom-path">
                    <li class="right-side "><a href="home" class="">Home<span class="fa fa-angle-right" aria-hidden="true"></span></a> <p></li>
                    <li class="active ">About</li>
                </ul>
            </div>
</div>

   </div>
    </section>
<!-- breadcrumbs //-->

<section class="w3l-content-with-photo-4"  id="about">
    <div class="content-with-photo4-block ">
        <div class="container">
            <div class="main-titles-head ">
                <h3 class="header-name ">
					Why we are Best
                </h3>
                <br />
                <p class="tiltle-para  ">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Hic fuga sit illo modi aut aspernatur tempore laboriosam saepe dolores eveniet.</p>
            </div>
            <br /><br />
            <div class="row">

                <div class="my-bio col-lg-5">
                    <h3>Best Restaurant food ideas & traditions from the whole world</h3>
                    <br />
                <p class="para mb-3">Lorem ipsum dolor sit amet  deleniti dolore sequi labore similique vitae .inventore deleniti dolore sequi labore similique vitae quae est.</p>
                <p class="para mb-3">Cum quo maiores asperiores sequi error alias molli aliquid pr Lorem ipsum dolor sit amet consectetur adipisicing elit.inventore deleniti dolore sequi labore similique vitae quae est.</p>
                <p class="para mb-3">Sit amet consectetur adipisicing elit.inventore deleniti dolore sequi labore similique vitae quae est.</p>
        
            </div>
            <div class="col-lg-7">
                <div class="foot-best">
                <img src="resources/styles/images/b7.jpg" alt="product" class="img-responsive about-me">
                <img src="resources/styles/images/b8.jpg" alt="product" class="img-responsive about-me">
            </div>
            </div>
            </div>
    </div>
</div>
</section>
<section class="w3l-stats">
  <div class="main-w3 ">
      <div class="container">
          <div class="row align-items-center">

              <div class="col-lg-6 main-cont-wthree-fea text-center">
                  <div class="row">
                      <div class="col-6 pr-2">
                          <div class="grids-speci1">
                              <span class="fa fa-smile-o"></span>
                              <h3 class="title-spe">1200+</h3>
                              <p>Happy Clients</p>
                          </div>
                      </div>
                      <div class="col-6 pl-2">
                          <div class="grids-speci1">
                              <span class="fa fa-cutlery"></span>
                              <h3 class="title-spe">25+</h3>
                              <p>Resturant</p>
                          </div>
                      </div>
                      <div class="col-6 pr-2 mt-4">
                          <div class="grids-speci1">
                              <span class="fa fa-users"></span>
                              <h3 class="title-spe">85+</h3>
                              <p>Chefs</p>
                          </div>
                      </div>
                      <div class="col-6 pl-2 mt-4">
                          <div class="grids-speci1">
                              <span class="fa fa-comments"></span>
                              <h3 class="title-spe">575+</h3>
                              <p>Dishes</p>
                          </div>
                      </div>
                  </div>
              </div>
              <div class="col-lg-6 title">
                <h3 class="title-big">Best luxury Food in Italy.Cutlery Resturant since 1887.</h3>
                <br />
                <p class="mt-4 para">Lorem ipsum, dolor sit amet consectetur adipisicing elit. Nemo, soluta impedit.
                    Consequatur,
                    quasi mollitia? Adipisci exercitationem amet dolore expedita repudiandae voluptatem impedit ipsa
                    vero
                    repellendus? Lorem ipsum dolor, sit amet consectetur adipisicing elit. Odio molestiae ex aut
                    possimus atque explicabo nostrum recusandae ab quisquam at!</p>
            </div>
          </div>

      </div>
  </div>
</section>
<section class="w3l-recent-work">
	<div class="jst-two-col">
		<div class="container">
<div class="row">

	<div class="my-bio col-lg-6">
		<h3>We Deliver Good Food
			Offer Great Smile</h3>
			<br />
	<p class="para mb-3">Lorem ipsum dolor sit amet consectetur adipisicing elit inventore deleniti dolore sequi labore similique vitae .inventore deleniti dolore sequi labore similique vitae quae est.</p>
	<p class="para mb-3">Cum quo maiores asperiores sequi error alias mollitia? Voluptatibus impedit aliquid pr Lorem ipsum dolor sit amet consectetur adipisicing elit.inventore deleniti dolore sequi labore similique vitae quae est.</p>
	<!-- <a href="about.html" class="action-button btn mt-3">Read more</a> -->
</div>
<div class="col-lg-6 ">
	<img src="resources/styles/images/b4.jpg" alt="product" class="img-responsive about-me">
</div>
</div>
		</div>
	</div>
</section>
<section class="w3l-team-main-6" id="team">
	<!-- /team-grids -->
	<div class="team-content-page  ">

		<div class="container">
			<div class="main-titles-head ">
                <h3 class="header-name ">
					Our Food Magician
                </h3>
                <br />
                <p class="tiltle-para  ">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Hic fuga sit illo modi aut aspernatur tempore laboriosam saepe dolores eveniet.</p>
            </div>
            <br />
			<div class="row main-contteam-29">
				<div class="team-main-29 col-md-4 text-center">
					<div class="column-29">
						<a href="#!">
							<img class="img-responsive" src="resources/styles/images/t1.jpg" alt=" ">
						</a>
						<div class="right-team-9">
							<h6><a href="#!" class="title-team-29">John Withawind</a></h6>
							<p class="sm-text-29">Head Chef</p>
						</div>
						<ul class="social">
							<li><a href="#!"><span class="fa fa-facebook-square"></span></a></li>
							<li><a href="#!"><span class="fa fa-twitter-square"></span></a></li>
							<li><a href="#!"><span class="fa fa-linkedin-square"></span></a></li>
						</ul>
					</div>
				   
				</div>
				<div class="team-main-29 col-md-4 mt-md-0 mt-4 text-center">
					<div class="column-29">
						<a href="#!">
							<img class="img-responsive" src="resources/styles/images/t2.jpg" alt=" ">
						</a>
						<div class="right-team-9">
							<h6><a href="#!" class="title-team-29">Ty Ballgame</a></h6>
							<p class="sm-text-29">Head Chef</p>
						</div>
						<ul class="social">
							<li><a href="#!"><span class="fa fa-facebook-square"></span></a></li>
							<li><a href="#!"><span class="fa fa-twitter-square"></span></a></li>
							<li><a href="#!"><span class="fa fa-linkedin-square"></span></a></li>
						</ul>
					</div>
				   
				</div>
				<div class="team-main-29 col-md-4 mt-md-0 mt-4 text-center">
					<div class="column-29">
						<a href="#!">
							<img class="img-responsive" src="resources/styles/images/t3.jpg" alt=" ">
						</a>
						<div class="right-team-9">
							<h6><a href="#!" class="title-team-29">Evanly Shlee</a></h6>
							<p class="sm-text-29">Head Chef</p>
						</div>
						<ul class="social">
							<li><a href="#!"><span class="fa fa-facebook-square"></span></a></li>
							<li><a href="#!"><span class="fa fa-twitter-square"></span></a></li>
							<li><a href="#!"><span class="fa fa-linkedin-square"></span></a></li>
						</ul>
					</div>
					
				</div>
			 </div>
		  </div>
	</div>
	<br /><br />
	<!-- /team-grids -->
</section>

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
</script>
<!-- /move top -->
</body>

</html>
