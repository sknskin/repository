<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
					<li class="right-side "><a href="/home" class="">Home<span class="fa fa-angle-right" aria-hidden="true"></span></a><p></li>
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
				<div class="contact-grids d-grid">
					<div class="contact-left">
						<h4>We are here for you</h4>
						<h6>Sequi doloribus distinctio, possimus ut corrupti facilis
							odit, ducimus aut aliquid dicta beatae numquam sed odio impedit
							quas, deleniti neque veritatis sunt.</h6>
						<div class="hours">
							<h6 class="info mt-3 mb-1">Email</h6>
							<p>
								<a href="#!"> Affinité-mail@support.com</a>
							</p>
							<h6 class="info mt-3 mb-1">Address</h6>
							<p class="para">Seoul, Gangnam-gu, Teheran-ro-5-gil<br />
				Jangyeon Building 3~7 floor, Korea.</p>
							<h6 class="info mt-3 mb-1">Contact</h6>
							<p class="margin-top">
								<a href="#!">02-3481-1005</a>
							</p>
							<br />
							<br />
						</div>
					</div>
					
						<div class="contact-right">
						<form action="search-message-by-name" method="post" id="search-message">
								<button type="submit" class="btn btn-info btn-style" id="my_message_btn" style="float: right">Search My Message</button>
								<input type="hidden" name="msgName" value="" />
							</form>
						<form:form id="message_form" action="send-message" method="post" modelAttribute="messageDto">
						<h3 class="header-name ">Send Message</h3>
						<br /><br />
								<div class="input-grids">
								<input type="text" class="form-control" placeholder="Your Name*" aria-label="Username" aria-describedby="basic-addon1" name="msgName" required autocomplete="off"/>
								<input type="text" class="form-control" placeholder="Your Email*" aria-label="Username" aria-describedby="basic-addon1" id="msgEmail" name="msgEmail" required autocomplete="off"/>
								<input type="text" class="form-control" placeholder="Title*" aria-label="Username" aria-describedby="basic-addon1" id="msgTitle" name="msgTitle" required autocomplete="off"/>
								</div>
								<div class="form-input">
									<textarea id="msgDetail" name="msgDetail" placeholder="Type your Message here*" class="form-control" aria-describedby="basic-addon1" required autocomplete="off"></textarea>
								</div>
								<br />
							<button type="submit" class="btn btn-primary btn-style">Send Message</button>
						</form:form>
					</div>
					
				</div>
				<div class="container-fluid">
				<div class="map-iframe mt-5">
					<!-- <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d317718.69319292053!2d-0.3817765050863085!3d51.528307984912544!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x47d8a00baf21de75%3A0x52963a5addd52a99!2sLondon%2C+UK!5e0!3m2!1sen!2spl!4v1562654563739!5m2!1sen!2spl" width="100%" height="400" frameborder="0" style="border: 0px; pointer-events: none;" allowfullscreen=""></iframe> -->
					<iframe
						src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3165.32927862754!2d127.02688761530978!3d37.500151279810424!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca15857ce38dd%3A0xac87641577138f5d!2z6re466aw7Lu07ZOo7YSw7JWE7Lm0642w66-4IOqwleuCqOy6oO2NvOyKpA!5e0!3m2!1sen!2skr!4v1674026836237!5m2!1sen!2skr"
						width="1100px" height="400" frameborder="0"
						style="border: 0px; pointer-events: none;"
						allowfullscreen=""></iframe>
				</div>
			</div>
			<br /><br /><br /><br /> 
			</div>
			
		</div>
	</section>
	
	<!-- My_Message Modal -->
	<div class="modal fade" id="MyMessageModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h2 class="modal-title fs-5" id="exampleModalLabel">Search My Message</h2>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          		<span aria-hidden="true">&times;</span>
        	</button>
	      </div>
	      <div class="modal-body">
	        <input type="text" class="form-control" value="" placeholder="문의자 성함을 입력하세요." aria-label="Username" aria-describedby="basic-addon1" name="before_message_name" id="before_message_name" required autocomplete="off"/>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btn_cancel_my">Close</button>
	        <button type="button" class="btn btn-primary" id="btn_search_my">Search</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<%-- <form action="search-by-name" method="get" id="selectByName">
	  	<input type="hidden" name="msg_name" />
	</form> --%>
	
	<!-- Send_Message Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h2 class="modal-title fs-5" id="exampleModalLabel">Send Message</h2>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          		<span aria-hidden="true">&times;</span>
        	</button>
	      </div>
	      <div class="modal-body">
	        성함과 이메일 주소를 확인해주세요.<br />
	        문의를 접수하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btn_cancel">Close</button>
	        <button type="button" class="btn btn-primary" id="btn_submit">Send</button>
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
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript">

	
		var confirmed = false;
	
		// my message
		$('#search-message').on('submit', function(event) {
			
			if (!confirmed) {

				event.preventDefault();
				$("#MyMessageModal").modal("show");

				return false;
			}
		});
		
		$('#before_message_name').on('change', function(evnet) {
			
			event.preventDefault();
			
			$('input[name=msgName]').attr('value', $('#before_message_name').val());
		});
		
		$('#btn_cancel_my').on('click', function() {

			confirmed = false;
			$('#MyMessageModal').modal('hide');
		});
		
		$('#btn_search_my').on('click', function() {
			
			if ($('input[name=before_message_name]').val().length != 0) {
				confirmed = true;
				$('#search-message').submit();
			} else {
				alert('문의자 성함을 입력해주세요.');
				return false;
			}
		});
		
		
		// submit 
		$('#message_form').on('submit', function(event) {

			if (!confirmed) {

				event.preventDefault();
				$("#exampleModal").modal("show");

				return false;
			}
		});

		$('#btn_cancel').on('click', function() {

			confirmed = false;
			$('#exampleModal').modal('hide');
		});

		$('#btn_submit').on('click', function() {

			confirmed = true;
			$('#message_form').submit();
			alert('문의가 접수되었습니다.');
		});

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