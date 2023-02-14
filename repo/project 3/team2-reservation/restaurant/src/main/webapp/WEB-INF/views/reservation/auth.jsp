<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Restaurant Reservation</title>
<!-- Template CSS -->
<link rel="stylesheet" href="/resources/styles/css/style-libearty.css">
<link rel="stylesheet" href="/resources/styles/css/reservation.css">
<link href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>
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
	<% int total = 0;
	if(application.getAttribute("total") != null){
		total = (int)application.getAttribute("total");
	}
	total++;
	application.setAttribute("total", total);
%>
<% String str = UUID.randomUUID().toString().split("-")[1];
   session.setAttribute("auth", str);
%>
<h2>인증페이지</h2>
<hr/>
<form action="auth_ok.jsp" method="post">
<h4>인증문자 :<%=str %></h4>
<p>인증문자를 입력하세염</p>
<p>인증문자를 입력:<input type="text" name="code" size="10" required></p>
<input type="submit" value="확인">
</form>
	
	
</body>
</html>