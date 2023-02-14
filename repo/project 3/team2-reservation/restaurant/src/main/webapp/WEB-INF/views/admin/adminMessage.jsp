<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>Administrator</title>

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
					<li class="active ">Admin</li>
				</ul>
			</div>
		</div>
	</section>
	<br>
	<div class="row" style="margin-left:80px">
		<div class="col-3">
			<div class="nav flex-column nav-pills" style="width: 200px">
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/admin/adminStatistics">Admin Statistics</a>
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/admin/adminReservation">Admin Reservation</a>
				<a style="height: 30px; font-size: 18px" class="nav-link active" href="/admin/adminMessage">Admin Message</a>
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/admin/adminMenu">Admin Menu</a>
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/logout">Admin Logout</a>
			</div>
		</div>
	</div>

	<section class="w3l-contact-info-main" id="contact">
		<div class="contact-sec	">
			<div class="container">
				<div class="main-titles-head">
					<h3 class="header-name ">Message</h3>
					<p class="tiltle-para ">고객 메시지함</p>
				</div>
				<br />
				<br />
				<br />
				<div>
				
				<p class="h5" style="margin: 0 auto; text-align: center"># Custumer Messages List (For 30 days)</p><br />
					<form action="message-list" method="post">
					<table class="table text-center table-hover container" style="width: 1060px; text-align: center; margin: 0 auto">
					
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col" width="10%">No</th>
					      <th scope="col" width="10%">Name</th>
					      <th scope="col" width="20%">Email</th>
					      <th scope="col" width="20%">Title</th>
					      <th scope="col" width="40%">Detail</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="messages" items="${ messages }" varStatus="status">
					  	
						  	<tr>
						      <th scope="row">${ status.count }</th>
						      <c:choose>
						  		<c:when test="${ messages.msgDeleted }">
						      		<td style="text-decoration: line-through; font-weight: lighter;">${ messages.msgName }</td>
						      	</c:when>
						  		<c:otherwise>
						  			<td>${ messages.msgName }</td>
						  		</c:otherwise>
							  </c:choose>
							  <c:choose>
						  		<c:when test="${ messages.msgDeleted }">
						      		<td style="text-decoration: line-through; font-weight: lighter;">${ messages.msgEmail }</td>
						      	</c:when>
						  		<c:otherwise>
						  			<td>${ messages.msgEmail }</td>
						  		</c:otherwise>
							  </c:choose>
							  <c:choose>
						  		<c:when test="${ messages.msgDeleted }">
						      		<td style="text-decoration: line-through; font-weight: lighter;">${ messages.msgTitle }</td>
						      	</c:when>
						  		<c:otherwise>
						  			<td><a href="adminMessageDetail?msgId=${ messages.msgId }" style="color: black; font-weight: bold">${ messages.msgTitle }</a></td>
						  		</c:otherwise>
							  </c:choose>
							  <c:choose>
						  		<c:when test="${ messages.msgDeleted }">
						      		<td><a style="font-weight: bold; color: darkgrey">Confirmed</a></td>
						      	</c:when>
						  		<c:otherwise>
						  			<td><a href="adminMessageDetail?msgId=${ messages.msgId }" style="color: black; font-weight: bold">${ messages.msgDetail }</a></td>
						  		</c:otherwise>
							  </c:choose>
						    </tr>
						    
						</c:forEach>
					  </tbody>
					</table>
					</form>
				</div>
			</div>
			<br><br><br><br>
		</div>
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