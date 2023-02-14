<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Contact</title>

<!-- Template CSS -->
<link rel="stylesheet" href="resources/styles/css/style-libearty.css">
<link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
</head>
<body id="home">

	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<!-- End Header -->

	<script src="resources/styles/js/jquery-3.3.1.min.js"></script>
	<!-- Common jquery plugin -->
	<!--bootstrap working-->
	<script src="resources/styles/js/bootstrap.min.js"></script>
	<!-- //bootstrap working-->
	<!--/MENU-JS-->
	<script>
		$(window).on("scroll", function() {
			var scroll = $(window).scrollTop();

			if (scroll >= 80) {
				$("#site-header").addClass("nav-fixed");
			} else {
				$("#site-header").removeClass("nav-fixed");
			}
		});

		//Main navigation Active Class Add Remove
		$(".navbar-toggler").on("click", function() {
			$("header").toggleClass("active");
		});
		$(document).on("ready", function() {
			if ($(window).width() > 991) {
				$("header").removeClass("active");
			}
			$(window).on("resize", function() {
				if ($(window).width() > 991) {
					$("header").removeClass("active");
				}
			});
		});
	</script>
	<!--//MENU-JS-->
	<!-- disable body scroll which navbar is in active -->
	<script>
		$(function() {
			$('.navbar-toggler').click(function() {
				$('body').toggleClass('noscroll');
			})
		});
	</script>
	<!-- disable body scroll which navbar is in active -->


	<!-- breadcrumbs -->
	<section class="w3l-inner-banner-main">
		<div class="about-inner contact">
			<div class="breadcrumbs-sub">
				<ul class="breadcrumbs-custom-path">
					<li class="right-side "><a href="home" class="">Home<span class="fa fa-angle-right" aria-hidden="true"></span></a><p></li>
					<li class="active ">Contact</li>
				</ul>
			</div>
		</div>
	</section>
	<!-- breadcrumbs //-->

	<section class="w3l-contact-info-main" id="contact">
		<div class="contact-sec	">
			<div class="container">
				<div class="main-titles-head">
					<h3 class="header-name ">Get In Touch</h3>
					<p class="tiltle-para ">Lorem ipsum dolor sit amet consectetur,
						adipisicing elit. Hic fuga sit illo modi aut aspernatur tempore
						laboriosam saepe dolores eveniet.</p>
				</div>
				<br />
				<br />
				<br />
				<br />
				<br />
				<c:choose>
				<c:when test="${ not empty list }">
				<div class="contact-grids d-grid">
				<p class="h5" style="margin: 0 auto; text-align: center"># Custumer Messages List</p><br />
					
					<table class="table text-center table-hover container" style="width: 1160px; text-align: center; margin: 0 auto">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col" width="10%">No</th>
					      <th scope="col" width="10%">Name</th>
					      <th scope="col" width="20%">Email</th>
					      <th scope="col" width="20%">Title</th>
					      <th scope="col" width="40%">Detail</th>
					      <th scope="col" width="10%">Confirmed</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="list" items="${ list }" varStatus="status">
						  	<tr>
						      <th scope="row">${ status.count }</th>
						  			<td>${ list.msgName }</td>
						  			<td>${ list.msgEmail }</td>
						  			<td>${ list.msgTitle }</td>
						  			<td>${ list.msgDetail }</td>
							  <c:choose>
						  		<c:when test="${ list.msgDeleted }">
						      		<td><a style="font-weight: bold; color: darkgrey">Confirmed</a></td>
						      	</c:when>
						  		<c:otherwise>
						  			<td>Unchecked</td>
						  		</c:otherwise>
							  </c:choose>
						    </tr>
						</c:forEach>
					  </tbody>
					</table>
					
			<br /><br /><br /><br /> 
			</div>
			</c:when>
					<c:otherwise>
					<p class="h5" style="margin: 0 auto; text-align: center"># 조회된 문의사항이 없습니다.</p>
					</c:otherwise>
			</c:choose>
		</div>
	</section>
	<br /><br /><br /><br /><br />
	<!-- ======= Footer ======= -->
	<jsp:include page="/WEB-INF/views/include/footer.jsp" />
	<!-- End Footer -->

	<!-- move top -->
	<button onclick="topFunction()" id="movetop" title="Go to top">
		<span class="fa fa-long-arrow-up"></span>
	</button>
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript">


		// When the user scrolls down 20px from the top of the document, show the button
		window.onscroll = function() {
			scrollFunction()
		};

		function scrollFunction() {
			if (document.body.scrollTop > 20
					|| document.documentElement.scrollTop > 20) {
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