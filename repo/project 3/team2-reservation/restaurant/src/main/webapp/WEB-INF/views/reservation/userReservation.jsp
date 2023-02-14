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

	<!-- <script src="/resources/styles/js/jquery-3.3.1.min.js"></script> -->
	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<!-- 템플릿 자체 jquery파일에서 ajax 사용불가하게 되어있어서 jquery 링크 코드 사용 -->
	
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
						<a href="home" class="">Home
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
			<br /><br />
		</div>
		<c:choose>
		<c:when test="${ not empty listByName }">
		<p class="h5" style="margin: 0 auto; text-align: center"># Custumer Reservation List</p><br />
		
		<table class="table text-center table-hover container" id="list-table">
			<thead class="thead-dark">
				<tr>
					<th>No.</th>
					<th>테이블 번호</th>
					<th>예약자명</th>
					<th>날짜</th>
					<th>시간</th>
					<th>이메일</th>
					<th>성인</th>
					<th>아동</th>
					<th>수정</th>
					<th>삭제</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="reservation" items="${ listByName }">
			<form action="deleteReservation" method="post" id="delete-form">
				<input type="hidden" value="" name="resId">
				<tr style="color:#000;">
					<td>${ reservation.resId }</td>
					<td>${ reservation.resTableId }번</td>
					<td>${ reservation.resName }</td>
					<td id="resDateTd"><fmt:formatDate value="${ reservation.resDate }" pattern="yyyy-MM-dd"/></td>
					<td>${ reservation.resTime }시</td>
					<td>${ reservation.resEmail }</td>
					<td>${ reservation.resAdult }명</td>
					<td>${ reservation.resChild }명</td>
					<td><button class="btn btn-outline-info btn-sm modify-btn" type="button">수정</button></td>
					<td><button class="btn btn-outline-danger btn-sm delete-btn" type="button">삭제</button></td>
				</tr>
			</form>
			</c:forEach>
			</tbody>
		</table>
		
		</c:when>
					<c:otherwise>
					<p class="h5" style="margin: 0 auto; text-align: center"># 조회된 문의사항이 없습니다.</p>
					<br />
					</c:otherwise>
			</c:choose>
	</section>
	<br>
	<br>
	<br>
	<br>
	<!-- Modify Menu Modal -->
	<div class="modal fade" id="modify-menu" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel" style="color: #000;">Modify menu</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="text-align:center">
					<form action="/modifyBook" method="post" class="signin-form" id="modifyBook_form">
							<div>
							<input type="hidden" name="resId" id="resId" />
							<div>
									<input class="form-control" aria-describedby="basic-addon1" type="text" name="resName" placeholder="예약자명" id="resName"
										required style="color: #000;">
								</div>
								<br />
								<div>
									<input class="form-control" aria-describedby="basic-addon1" type="email" name="resEmail" placeholder="Email" id="resEmail"
										required style="color: #000;">
								</div>
								<br />
								<div>
									<input class="form-control" aria-describedby="basic-addon1" type="date" name="resDate" placeholder="날짜" id="resDate"
										required style="color: #000;">
								</div>
								<br />
								<div>
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
								<br />
								<div>
									<select class="selectpicker" style="color: #000;"
										name="resAdult" required id="resAdult">
										<option selected hidden disabled value="">성인</option>
										<option value="1">1명</option>
										<option value="2">2명</option>
										<option value="3">3명</option>
										<option value="4">4명</option>
									</select>
								</div>
								<br />
								<div>
									<select class="selectpicker" style="color: #000;" name="resChild" id="resChild" required>
										<option selected hidden disabled value="">아동</option>
										<option value="0">0명</option>
										<option value="1">1명</option>
										<option value="2">2명</option>
										<option value="3">3명</option>
										<option value="4">4명</option>
									</select>
								<br />
								</div>
								<br />
								<div>
									<select class="selectpicker" style="color: #000;" name="resTableId" id="resTableId" required>
										<option selected hidden disabled value="">테이블 번호</option>
										<option value="1" class="seat">1번</option>
										<option value="2" class="seat">2번</option>
										<option value="3" class="seat">3번</option>
										<option value="4" class="seat">4번</option>
										<option value="5" class="seat">5번</option>
										<option value="6" class="seat">6번</option>
										<option value="7" class="seat">7번</option>
										<option value="8" class="seat">8번</option>
										<option value="9" class="seat">9번</option>
										<option value="10" class="seat">10번</option>
										<option value="11" class="seat">11번</option>
									</select>
								</div>
								
							</div>
							
							<br>
						<button type="button" class="btn btn-secondary"
						data-dismiss="modal" style="color:white">취소</button>
						<button type="submit" class="btn btn-style btn-info submit" id="btn_modify">수정하기</button>
						</form>
				</div>
			</div>
		</div>
	</div>
	<!-- End -->
	
	<!-- MODIFY-MENU-JS -->
	<script type="text/javascript">
	
	var now_utc = Date.now()
	var timeOff = new Date().getTimezoneOffset()*60000;
	var today = new Date(now_utc-timeOff).toISOString().split("T")[0];
	document.getElementById("resDate").setAttribute("min", today);

				
	$(function() {
		
		// modify function
		$('#list-table tbody .modify-btn').on('click', function(event) {
		
			const tr = $(this).parent().parent();
			const resId = tr.find('td:eq(0)').text();
			
			$.ajax({
				"method" :'POST',
				"url" : '/getReservationData',
				"data" : {resId:resId},
				"success" : function(data, status, xhr) {
					
					$('#modifyBook_form input[name=resId]').val(data.resId);
					$('#modifyBook_form input[name=resName]').val(data.resName);
					$('#modifyBook_form input[name=resEmail]').val(data.resEmail);
					$('#modifyBook_form input[name=resDate]').val(data.resDate.substring(0, 10));
					$('#modifyBook_form select[name=resTime]').val(data.resTime);
					$('#modifyBook_form select[name=resAdult]').val(data.resAdult);
					$('#modifyBook_form select[name=resChild]').val(data.resChild);
					$('#modifyBook_form select[name=resTableId]').val(data.resTableId);
					
					$('#modify-menu').modal('show');
				},
				"error" :function(xhr, status, err) {
					alert("error");
				}
			});
			
		});
		
		// 수정 중복검사
		$('#resDate, #resTime').on('change',function(event){
			
			$('#resTableId').val('');	
			if(!($('#resDate').val() && $('#resTime').val())){
				
				return;
			}
			const resDate = $('#resDate').val();
			const resTime = $('#resTime').val();
			
			$.ajax({
				"method":'post',
				"url":'checkOverlap',
				"data":{ "resDate": resDate, "resTime": resTime },
				"success":function(data,status,xhr){
					$('#resTableId option').prop('disabled', false);
					$('#resTableId option').removeAttr('style');
						for (var i = 0; i < data.length; i++) {
							$('#resTableId option[value=' + data[i].resTableId + "]").prop('disabled', true);
							$('#resTableId option[value=' + data[i].resTableId + "]").css('background-color','#ccc');
							$('#resTableId option[value=' + data[i].resTableId + "]").css('color' , 'red');
							
						}
				},
				error:function(xhr,status,xhr){
					alert(err);
				}
			});
		});
		
		// delete function
		$('#list-table tbody .delete-btn').on('click', function(event) {
			
			const tr = $(this).parent().parent();
			const resId = tr.find('td:eq(0)').text();
			
			$('input[name=resId]').val(resId);
			
			var deleted = confirm("(삭제된 예약정보는 복구할 수 없습니다.)\n" + resId + " 번 예약정보를 정말 삭제하시겠습니까?");
			
			if(deleted == true){
				alert('삭제되었습니다.');
				$("#delete-form").submit();
			} else {
				
				false;
			}
		});
		
	});

	 </script>
	<!-- End -->

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
	<script>
	
	
	</script>
	<!-- /move top -->
</body>

</html>