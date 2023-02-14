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

<title>Administrator</title>

<!-- Template CSS -->
<link rel="stylesheet" href="/resources/styles/css/style-libearty.css">
<link rel="stylesheet" href="/resources/styles/css/reservation.css">
<link
	href="https://fonts.googleapis.com/css?family=Playfair+Display:400,700&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap"
	rel="stylesheet">
	
	<!-- 구글 폰트 -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
<!-- 구글 폰트 -->

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" ></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js"></script>

<style>
	.modal fade>h2{font-family: 'Noto Sans KR';}
</style>
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
	<script>
	$(document).ready(function(){
		
		$(".seat").click(function(e){
			
			var result = confirm(e.target.id + ' 번 테이블을 예약하시겠습니까?');
			
			if (!result) return;
			
			if(e.target.id === '1') {
				alert('1 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '2') {
				alert('2 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '3') {
				alert('3 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '4') {
				alert('4 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '5') {
				alert('5 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '6') {
				alert('6 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '7') {
				alert('7 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '8') {
				alert('8 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '9') {
				alert('9 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '10') {
				alert('10 번 테이블을 선택하셨습니다.');
			} else if(e.target.id === '11') {
				alert('11 번 테이블을 선택하셨습니다.');
			}
			
			$(".seat").attr("disabled", true);
			$(e.target).css({'background-color':'#6feaf6'});
			$('input[name=resTableId]').attr('value', e.target.id);
		});
		
		$("#reset_btn").click(function(e) {
			
			var reset = confirm('테이블을 다시 선택하시겠습니까?');
			
			if (reset) {
				
				$(".seat").attr("disabled", false);
				location.reload(true);
			}
		});
	
	});
	</script>
	
	<!-- breadcrumbs -->
	<section class="w3l-inner-banner-main">
		<div class="about-inner contact">
			<div class="breadcrumbs-sub">
				<ul class="breadcrumbs-custom-path">
					<li class="right-side ">
						<a href="home" class="">Home
							<span class="fa fa-angle-right" aria-hidden="true"></span>
						</a>
					</li>
					<li class="active ">Reservation</li>
				</ul>
			</div>
		</div>
	</section>
	
	<section class="w3l-contact-info-main" id="contact" style="width:1200px; margin:0 auto;">
		<div class="contact-sec	">
			<div class="container">
				<div class="main-titles-head">
					<h3 class="header-name ">Reservation</h3>
					<p class="tiltle-para ">예약</p>
				</div>
				<br />
				<br />
				<br />
				<div class="contact-grids d-grid">
		
				</div>
			</div>
		</div>
		
		<ul class="showcase">
		<li>
			<div class="box"></div> <small>N/A</small>
		</li>
		<li>
			<div class="box selected"></div> <small>Selected</small>
		</li>
		<li>
			<div class="box occupied"></div> <small>Occupied</small>
		</li>
	</ul>
	
			
	<div class="rsv_container">
	<div class="river_view"><h3>River</h3></div>
		<div class="row">	
		
			<button class="seat" id="1" style="text-align: center;" onclick="checkDateTime()">1</button>
			<button class="seat" id="2" style="text-align: center;" onclick="checkDateTime()">2</button>
			<button class="seat" id="3" style="text-align: center;" onclick="checkDateTime()">3</button>
			<button class="seat" id="4" style="text-align: center;" onclick="checkDateTime()">4</button>
			<button class="seat" id="5" style="text-align: center;" onclick="checkDateTime()">5</button>
			<button class="seat" id="6" style="text-align: center;" onclick="checkDateTime()">6</button>
			<button class="seat" id="7" style="text-align: center;" onclick="checkDateTime()">7</button>
			<button class="seat" id="8" style="text-align: center;" onclick="checkDateTime()">8</button>
			
		</div>
		<div class="row">
			<button class="seat" id="9" style="text-align: center;" onclick="checkDateTime()">9</button>
			<button class="seat" id="10" style="text-align: center;" onclick="checkDateTime()">10</button>
			<button class="seat" id="11" style="text-align: center;" onclick="checkDateTime()">11</button>
			<div class="kitchen"><h3>Kitchen</h3></div>
		</div>
		
		<button id="reset_btn">테이블 선택 초기화</button>
	</div>
	
	<div class="w3l-clients" id="client" style="margin-top:80px;">
		<div class="main-w3 text-center" style="padding: 0 0;">
			<div class="container">
				<div class="form-right-inf">
					<div class="form-inner-cont">
						<div class="main-titles-head ">
							<h3 class="header-name white " style="color:#000;">Reservation Your Table</h3>
							<br />
						</div>
						<form action="/reservation/book" method="post" class="signin-form" id="book_form">
							<div class="row book-form">
							<!-- <input type="hidden" name="resId" id="resId" /> -->
							<div class="form-input col-lg-6 col-md-6" style="margin-bottom:15px;">
									<input type="text" name="resName" placeholder="예약자명" id="resName"
										required style="color: #000;">
								</div>
								<div class="form-input col-lg-6 col-md-6 mt-md-0 mt-3">
									<input type="email" name="resEmail" placeholder="Email" id="resEmail"
										required style="color: #000;">
								</div>
								<div class="form-input col-lg-6 col-md-6">
									<input type="date" name="resDate" placeholder="날짜" id="resDate"
										required style="color: #000;">
								</div>
								<div class="form-input col-lg-6 col-md-6">
									<select class="selectpicker" style="color: #000;" id="resTime"
										required name="resTime">
										<option selected hidden disabled value="">예약 시간</option>
										<option value=11>11:00</option>
										<option value=12>12:00</option>
										<option value=13>13:00</option>
										<option value=14>14:00</option>
										<option value=15>15:00</option>
										<option value=16>16:00</option>
										<option value=17>17:00</option>
										<option value=18>18:00</option>
										<option value=19>19:00</option>
										<option value=20>20:00</option>
									</select>

								</div>
								<div class="form-input col-md-4  mt-3">
									<select class="selectpicker" style="color: #000;"
										name="resAdult" required id="resAdult">
										<option selected hidden disabled value="">성인</option>
										<option value="1">1명</option>
										<option value="2">2명</option>
										<option value="3">3명</option>
										<option value="4">4명</option>
									</select>

								</div>
								<div class="form-input col-md-4 mt-3">
									<select class="selectpicker" style="color: #000;" name="resChild" id="resChild" required>
										<option selected hidden disabled value="">아동</option>
										<option value="0">0명</option>
										<option value="1">1명</option>
										<option value="2">2명</option>
										<option value="3">3명</option>
										<option value="4">4명</option>
									</select>

								</div>
								<div class="form-input col-md-4  mt-3">
									<input type="text" name="resTableId" value="" placeholder="테이블 번호" readonly style="color:#000;" id="resTableId" required></input>
								</div>
								
							</div>
							
							<div class="res-modal" style="display:flex; margin-top:20px;">
									<button type="button" class="btn btn-outline-primary" data-toggle="modal" data-target="#book-modal-check" style="width:50%; margin:0 auto; background:green; border:none;">예약확인</button>
									<button type="submit" class="btn" style="width:51%;margin:0 auto;">예약하기</button>
							</div>
						</form>
						<br /> <br /> <br />
					</div>
				</div>
			</div>
		</div>
	</div>
		
	</section>
	
	<!-- Modal -->
	<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h2 class="modal-title fs-5" id="exampleModalLabel" style="color:#000;">Reservation</h2>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          		<span aria-hidden="true">&times;</span>
        	</button>
	      </div>
	      <div class="modal-body" style="color:#000;">
	        예약하시겠습니까?
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary" data-dismiss="modal" id="btn_cancel">취소하기</button>
	        <button type="button" class="btn btn-primary" id="btn_submit">예약하기</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- Modal -->
	
	<div class="modal fade" id="book-modal-check" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h2 class="modal-title fs-5" id="exampleModalLabel" style="color:#000;">예약자 정보 조회</h2>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          		<span aria-hidden="true">&times;</span>
        	</button>
	      </div>
	      <div class="modal-body" style="color:#000;">
	       	<input class="form-control" aria-describedby="basic-addon1" placeholder="예약자 성함을 입력하세요." type="text" name="before_resName" id="before_resName" />
	      </div>
	      <div class="modal-footer">
	      	<button type="button" class="btn btn-primary" data-dismiss="modal">취소</button>
	      	<button type="button" class="btn btn-primary" id="resName_submit">예약자 조회</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<form action="reservation-by-name" method="post" id="selectByName">
		<input type="hidden" name="resName" value="" />
	</form>
	<form action="reservationInfo" method="get" id="Info-form">
		<input type="hidden" name="resName" value="" />
	</form>

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
	
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript">
	 	
	var now_utc = Date.now()
	var timeOff = new Date().getTimezoneOffset()*60000;
	var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
	document.getElementById("resDate").setAttribute("min", today);

	var confirmed = false;
	
	$('#book_form').on('submit', function(e) {
		
		if ($('input[name=resName]').val() != '' && $('input[name=resTableId]').val() == '') {
			
			alert('좌석표에서 테이블 번호를 선택해주세요.');
			return false;
		
		} else if (!confirmed) {
			
			e.preventDefault();
			$('#exampleModal').modal('show');
			return false;
		}
	});
	
	$('#btn_cancel').on('click', function() {
		
		confirmed = false;
		$('#exampleModal').modal('hide');
	});

	$('#btn_submit').on('click', function() {
		
		confirmed = true;
		$('#Info-form').submit();
		 $('#book_form').submit();
	});
	
	$('#before_resName').on('change', function(event) {
		
		event.preventDefault();
		
		$('input[name=resName]').attr('value', $('#before_resName').val());
	});
	
	$('#resName_submit').on('click', function(event) {
		
		event.preventDefault();
		$('#selectByName').submit();
		
	});
	
	$("#selectByName").submit(function(){
		
		var getName= RegExp(/^[가-힣]+$/);
		
		if($("#before_resName").val() == ""){
			 alert("성함을 입력해주세요.");
	        $("#before_resName").focus();
		
			return false;
		}
		 if (!getName.test($("#resName").val())) {
		        alert("예약자분 성함을 정확히 입력해주세요.");
		        $("#before_resName").val("");
		        $("#before_resName").focus();
		        return false;
		      }
	});
	
	</script>
	
	
</body>

</html>