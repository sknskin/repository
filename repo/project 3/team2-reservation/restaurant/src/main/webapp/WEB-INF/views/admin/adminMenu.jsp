<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
					<li class="right-side "><a href="/home" class="">Home <span
							class="fa fa-angle-right" aria-hidden="true"></span>
					</a></li>
					<li class="active ">Admin</li>
				</ul>
			</div>
		</div>
	</section>
	<br>
	<div class="row" style="margin-left:80px">
		<div class="col-3">
			<div class="nav flex-column nav-pills" style="width: 200px;">
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/admin/adminStatistics">Admin Statistics</a>
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/admin/adminReservation">Admin Reservation</a>
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/admin/adminMessage">Admin Message</a>
				<a style="height: 30px; font-size: 18px" class="nav-link active" href="/admin/adminMenu">Admin Menu</a>
				<a style="height: 30px; font-size: 18px" class="nav-link" href="/logout">Admin Logout</a>
			</div>
		</div>
	</div>

	<section class="w3l-contact-info-main" id="contact">
		<div class="contact	">
			<div class="container">
				<div style="text-align: center">
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-outline-success"
						data-toggle="modal" data-target="#register-menu">메뉴를
						등록하세요</button>
					<br><br><br>
				</div>
				<div class="main-titles-head">
					<h3 class="header-name ">Menu List</h3>
				</div>
			</div>
		</div>
	</section>
	<br>
	<div class="tableOut">
		<div class="tableIn">
			<form id="delete-form" action="deleteMenu" method="post">
				<input type="hidden" name="prodId">
			</form>
			<!-- <form id="modify-form" action="getMenuData" method="post">
				<input type="hidden" name="prodId">
			</form> -->
			<table id="menu-list-table" class="table" style="width:100%;font-size:12px">
				<thead class="thead-dark">
					<tr>
						<th style="display:none"></th>
						<th>Image</th>
						<th>Name</th>
						<th>Category</th>
						<th>Price</th>
						<th>Description</th>
						<th>Ingredients</th>
						<th>Modify/Delete</th>
					</tr>
				</thead>
				<c:forEach var="products" items="${ products }">
				<tr class="">
					<td style="display:none">${ products.prodId }</td>
					<c:choose>
					<c:when test="${ !empty products.savedFileName }">
					<td><img src="/product-images/${ products.savedFileName }" width="150"></td>
					</c:when>
					<c:otherwise>
					<td><img src="/product-images/default.png" width="150"></td>
					</c:otherwise>
					</c:choose>
					<td>${ products.prodName }</td>
					<td>${ products.prodCategory }</td>
					<td><fmt:formatNumber value="${ products.prodPrice }" pattern="₩#,###" /></td>
					<td>${ products.prodDesc }</td>
					<td>${ products.prodIngredients }</td>
					<td>
						<button class="btn btn-outline-info btn-sm modify-btn" style="float:left;">수정</button>
						<button class="btn btn-outline-danger btn-sm delete-btn" style="float:right;" data-toggle="modal" data-target="#delete-check">삭제</button>
					</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
	
	<!-- Delete Check Modal -->
	<div class="modal" id="delete-check" tabindex="-1">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title">Delete</h5>
	      </div>
	      <div class="modal-body">
	        <p>삭제하시겠습니까?</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary"
			data-dismiss="modal" style="color:white">취소</button>
			<button class="btn btn-style btn-info delete-ok-btn">삭제하기</button>
	      </div>
	    </div>
	  </div>
	</div>
	<!-- End -->

	<!-- MODIFY-DELETE-BUTTON-JS -->
	<script type="text/javascript">
		$(function() {
			console.log($.ajax);
			// 메뉴 수정 버튼 (버튼 클릭시 해당 데이터 모달창으로 가져오기)
			$('#menu-list-table tbody .modify-btn').on('click', function(event) {
				// 버튼의 부모객체 = td, td의 부모객체 = tr
				const tr = $(this).parent().parent();
				const tds = tr.find('td');
				
				// 0 -> 상품아이디(0), 이미지(1), 상품명(2), 상품가격(3), 상품설명(4), 상품재료(5), 메뉴관리(6)
				const prodId = tr.find("td:eq(0)").text();
				
				// ajax로 데이터 조회 -> 조회된 데이터를 modal 입력요소에 저장
				$.ajax({
					"method":"POST",
					"url":"/admin/getMenuData",
					"data": {prodId: prodId},
					"success":function(data, xhr, status ){
						$('#modify input[name=prodId]').val(data.prodId);
						$('#modify input[name=prodName]').val(data.prodName);
						$('#modify select[name=prodCategory]').val(data.prodCategory);
						$('#modify input[name=prodPrice]').val(data.prodPrice);
						$('#modify textarea[name=prodDesc]').val(data.prodDesc);
						$('#modify textarea[name=prodIngredients]').val(data.prodIngredients);
						$('#modify input[name=prevSavedFileName]').val(data.savedFileName);
						$('#modify input[name=prevUserFileName]').val(data.userFileName);
						$('#modify div[id=prevUserFileName]').html(data.userFileName);
					},
					"error":function(xhr, status, err){
						alert("오류")
					}
				});
				
				// modal 표시
				$('#modify-menu').modal('show');
			});
			
			// 메뉴 삭제 버튼 (버튼 클릭)
			$('#menu-list-table tbody .delete-btn').on('click', function(event) {
				// 버튼의 부모객체 = td, td의 부모객체 = tr
				const tr = $(this).parent().parent();
				const tds = tr.find('td');
				
				// 0 -> 상품아이디(0), 이미지(1), 상품명(2), 상품가격(3), 상품설명(4), 상품재료(5), 메뉴관리(6)
				const prodId = tr.find("td:eq(0)").text();
				
				// hidden 타입 input에 값 저장
				$('#delete-form input[name=prodId]').val(prodId);
			});
			
			// 메뉴 삭제 모달 삭제 버튼 (버튼 클릭)
			$('.delete-ok-btn').on('click', function(event) {
				$('#delete-form').submit();
			});
			
		});
	</script>
	<!-- End -->
	
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
					<form id="modify" action="modifyMenu" method="post" class="insert-menu-form" enctype="multipart/form-data">
						<div class="input-grids">
							<input type="hidden" name="prodId" id="prodId">
							<input type="text" name="prodName" id="productName"
								placeholder="Product Name*" class="form-control" required="required">
							<br>
							<select id="modifyCategorySelbox" name="prodCategory">
							  <option selected>Choose Product Category*</option>
							  <c:forEach var="category" items="${ categories }">
							  <option value="${ category }">${ category }</option>
							  </c:forEach>
							  <option value="direct">직접입력</option>
							</select>
							<br>
							<input type="text" class="form-control" placeholder="Product Category*" id="modifyCategorySelboxDirect" name="modifyCategorySelboxDirect" style="margin-top:22px">
							<br>
							<input type="number" name="prodPrice" id="productPrice"
								placeholder="Product Price (₩)*" class="form-control" required="required">
						</div>
						<br>
						<div class="form-input">
							<textarea name="prodDesc" id="productDesc" class="form-control"
								placeholder="Type Product Description*" required="required"></textarea>
							<br>
							<textarea name="prodIngredients" id="productIngredients" class="form-control"
								placeholder="Type Product Ingredients*" required="required"></textarea>
						</div>
						<hr>
						<div>
							<input type="hidden" name="prevUserFileName">
							<input type="hidden" name="prevSavedFileName">						
							등록된 파일 : <input name="prevUserFileName" disabled>
						</div>
						<br>
						<div class="filebox">
						    <input class="upload-name" name="modifyProductImg" placeholder="이미지 수정을 원하지 않으면 그대로 두십시오*" disabled>
						    <label for="modifyProductImg">파일찾기</label> 
						    <input type="file" name="modifyProductImg" id="modifyProductImg" accept="image/gif,image/jpeg,image/png">
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
	<script>
		// 첨부파일 선택시 input창에 파일명 뜨게 하기
		$("#modifyProductImg").on('change',function(){
		  var fileName = $("#modifyProductImg").val();
		  $(".upload-name").val(fileName);
		});
		
		// 수정 완료시 alert
		$("#modify").submit('#btn_modify', function(e){
			alert("수정이 완료되었습니다");
		});
		
		// 카테고리 select box
		$("#modifyCategorySelboxDirect").hide();
		
		$("#modifyCategorySelbox").change(function() {
			$("#modifyCategorySelboxDirect").val('');
			// 직접입력을 누를 때 나타남
			if ($("#modifyCategorySelbox").val() == "direct") {
				$("#modifyCategorySelboxDirect").show();
			} else {
				$("#modifyCategorySelboxDirect").hide();
			}
		});
	</script>
	<!-- End -->
	
	
	<!-- Register Menu Modal -->
	<div class="modal fade" id="register-menu" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel" style="color: #000;">Register Menu</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" style="text-align:center">
					<form id="register" action="registerMenu" method="post" class="insert-menu-form" enctype="multipart/form-data">
						<div class="input-grids">
							<input type="text" name="prodName" id="productName"
								placeholder="Product Name*" class="form-control" required="required">
							<br>
							<select id="categorySelbox" name="prodCategory">
							  <option selected>Choose Product Category*</option>
							  <c:forEach var="category" items="${ categories }">
							  <option value="${ category }">${ category }</option>
							  </c:forEach>
							  <option value="direct">직접입력</option>
							</select>
							<br>
							<input type="text" class="form-control" placeholder="Product Category*" id="categorySelboxDirect" name="categorySelboxDirect" style="margin-top:22px">
							<br>
							<input type="number" name="prodPrice" id="productPrice"
								placeholder="Product Price (₩)*" class="form-control" required="required">
						</div>
						<br>
						<div class="form-input">
							<textarea name="prodDesc" id="productDesc" class="form-control"
								placeholder="Type Product Description*" required="required"></textarea>
							<br>
							<textarea name="prodIngredients" id="productIngredients" class="form-control"
								placeholder="Type Product Ingredients*" required="required"></textarea>
						</div>
						<br>
						<div class="filebox">
						    <input class="upload-name" placeholder="Choose Product Image*" disabled>
						    <label for="productImg">파일찾기</label> 
						    <input type="file" name="productImg" id="productImg" onchange="fileCheck(this)" accept="image/gif,image/jpeg,image/png">
						</div>
						<br>
						<button type="button" class="btn btn-secondary"
						data-dismiss="modal" style="color:white">취소</button>
						<button type="submit" class="btn btn-style btn-info submit" id="btn_register">등록하기</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- End -->
	
	<br><br><br><br>
	
	<!-- REGISTER-MENU-JS -->
	<script>
		// 카테고리 select box
		$("#categorySelboxDirect").hide();
		
		$("#categorySelbox").change(function() {
			$("#categorySelboxDirect").val('');
			// 직접입력을 누를 때 나타남
			if ($("#categorySelbox").val() == "direct") {
				$("#categorySelboxDirect").show();
			} else {
				$("#categorySelboxDirect").hide();
			}
		});
	
		// 첨부파일 선택시 input창에 파일명 뜨게 하기
		$("#productImg").on('change',function(){
		  var fileName = $("#productImg").val();
		  $(".upload-name").val(fileName);
		});
		
		// 첨부파일 이미지 형식만 허락하기
		/* function fileCheck(obj) {
			pathpoing = obj.value.lastIndexOf('.');
			filepoint = obj.value.substring(pathpoint+1, obj.length);
			filetype = filepoint.toLowerCase();
			if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype='bmp') {
				// 정상적인 이미지 확장자 파일일 경우 ...
			} else {
				alert('이미지 파일만 선택할 수 있습니다.');
				parentObj = obj.parentNode
				node = parentObj.replaceChild(obj.cloneNode(true), obj);
				return false;
			}
			if(filetype=='bmp') {
				upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다.\n그래도 계속 하시겠습니까?');
				if(!upload) return false;
			}
		} */
		
		// 등록 완료시 alert
		$("#register").submit('#btn_register', function(e){
			alert("등록이 완료되었습니다");
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
	<!-- /move top -->
</body>

</html>