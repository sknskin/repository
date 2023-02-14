<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
	
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Reservation</title>

<!-- Template CSS -->
<link rel="stylesheet" href="/resources/styles/css/style-libearty.css">
<link rel="stylesheet" href="/resources/styles/css/administrator.css">
<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap"
	rel="stylesheet">
</head>
<body id="home">

	<!-- ======= Header ======= -->
	<jsp:include page="/WEB-INF/views/include/header.jsp" />
	<!-- End Header -->

	<script src="/resources/styles/js/jquery-3.3.1.min.js"></script>
	<!-- Common jquery plugin -->
	<!--bootstrap working-->
	<script src="/resources/styles/js/bootstrap.min.js"></script>
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
					<li class="right-side ">
						<a href="/home" class="">Home
							<span class="fa fa-angle-right" aria-hidden="true"></span>
						</a>
					</li>
					<li class="active ">Reservation</li>
				</ul>
			</div>
		</div>
	</section>
	<br>
	
	<section class="w3l-contact-info-main" id="contact">
		<div class="contact-sec	">
			<div class="container">
				<div class="main-titles-head">
					<h3 class="header-name ">Reservation</h3>
					<p class="tiltle-para ">예약 목록</p>
				</div>
				<br />
				<br />
				<br />
				<div class="contact-grids d-grid">

				</div>
			</div>
		</div>
		<p class="h5" style="margin: 0 auto; text-align: center"># Custumer Reservation Information</p><br />
		<form>
		<table class="table text-center table-hover container" style="width: 600px; text-align: center">
			<thead class="thead-dark">
				<tr>
					<th scope="col" width="20%">#&nbsp;${listByName.resId }</th>
					<th scope="col" width="80%">Reservation Detail</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td>예약자 성함</td><td>${ listByName.resName }</td>
				</tr>
				<tr>
					<td>이메일</td><td>${ listByName.resEmail }</td>
				</tr>
				<tr>
					<td>예약일</td><td><fmt:formatDate value="${ listByName.resDate }" pattern="yyyy-MM-dd"/></td>
				</tr>
				<tr>
					<td>예약시간</td><td>${ listByName.resTime }시</td>
				</tr>
				<tr>
					<td>테이블 번호</td><td>${ listByName.resTableId }번</td>
				</tr>
				<tr>
					<td>성인</td><td>${ listByName.resAdult }명</td>
				</tr>
				<tr>
					<td>아동</td><td>${ listByName.resChild }명</td>
				</tr>
			</tbody>
	
		</table>
		</form>
		<br /><br />
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