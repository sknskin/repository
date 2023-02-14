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
				
				<p class="h5" style="margin: 0 auto; text-align: center"># Custumer Messages Detail</p><br />
				
					<table class="table text-center table-hover container" style="width: 1060px; text-align: center; margin: 0 auto">
					  <thead class="thead-dark">
					    <tr>
					      <th scope="col" width="20%">#&nbsp;${ message.msgId }</th>
					      <th scope="col" width="80%">Inquiry</th>
					    </tr>
					  </thead>
					  <tbody>
					  	<tr>
					  		<td>Title</td><td>${ message.msgTitle }</td>
					  	</tr>
						<tr>
							<td>Name</td><td>${ message.msgName }</td>
						</tr>
						<tr>
							<td>Email</td><td>${ message.msgEmail }</td>
						</tr>
						<tr>
							<td>Detail</td><td>${ message.msgDetail }</td>
						</tr>
						<tr>
							<td>Confirm</td><td><button type="button" class="btn btn-outline-success" data-toggle="modal" data-target="#modal-confirm">confirm</button></td>
						</tr>
					  </tbody>
					</table>
					<form action="/confirm" method="get" id="message-confirm">
						<input type="hidden" value="${ message.msgId }" name="msgId">
					</form>
				</div>
			</div>
			<br><br><br><br>
		</div>
	</section>
	<!-- Modal -->
	<div class="modal fade" id="modal-confirm" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h2 class="modal-title fs-5" id="exampleModalLabel">Confirm</h2>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          		<span aria-hidden="true">&times;</span>
        	</button>
	      </div>
	      <div class="modal-body">
	        문의사항을 확인하셨습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <button type="button" class="btn btn-primary" id="btn_submit">Confirm</button>
	      </div>
	    </div>
	  </div>
	</div>

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
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript">
	$('#btn_submit').on('click', function(event) {
		
		event.preventDefault();
		$('#message-confirm').submit();
	});
	
	</script>
	<!-- /move top -->
</body>

</html>