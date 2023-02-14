<%@page language="java" 
		contentType="text/html; charset=utf-8"
    	pageEncoding="utf-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	
	<title>글상세보기</title>
	
	<link rel="Stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
	<link rel="Stylesheet" href="/spring-demoweb-f/resources/styles/default.css">
	<link rel="Stylesheet" href="/spring-demoweb-f/resources/styles/input.css">
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시글 정보</div>
		        
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>${ board.title }</td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>${ board.writer }</td>
		            </tr>
		            <tr>
		            	<th>조회수</th>
		            	<td>${ board.readCount }</td>
		            </tr>
		            <tr>
		            	<th>등록일자</th>
		            	<td>${ board.regDate }</td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                <c:forEach var="attachment" items="${ board.attachments }">
			                <a href="download.action?attachNo=${ attachment.attachNo }" style="text-decoration: none">
			                   ${ attachment.userFileName }
			                </a>
			                [${ attachment.downloadCount }]
			                <br>
			            </c:forEach>
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
						<td>
<c:set var="enter" value="
" />
							${ fn:replace(board.content, enter, "<br>") }
						</td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<%-- 로그인한 사용자와 작성자가 같은 경우에 편집, 삭제 버튼 표시 --%>
		        	<c:if test="${ not empty loginuser and loginuser.memberId eq board.writer }">
		        	<input type="button" id="update_button" value="편집" style="height:25px" />
		        	<input type="button" id="delete_button" value="삭제" style="height:25px" />
		        	</c:if>		        	
		        	<input type="button" id="tolist_button" value="목록보기" style="height:25px" />
		        </div>
		    </div>
		</div>
		
		<br><br>
		
		<!-- write comment area -->
		<form id="commentform" action="write-comment.action" method="post">
			<input type="hidden" name="boardNo" value="${ board.boardNo }" />
			<input type="hidden" name="pageNo" value="${ pageNo }" />
			<input type="hidden" name="writer" value="${ loginuser.memberId }" />
			<table style="width:550px;border:solid 1px;margin:0 auto">
	            <tr>
	                <td style="width:500px">	                	
	                    <textarea id="comment_content" name="content" style="width:500px" rows="3"></textarea>	                    
	                </td>
	                <td style="width:50px;vertical-align:middle">
	                	<a id="writecomment" href="javascript:" style="text-decoration:none">
	                		댓글<br />등록
	                	</a>
	                </td>
	            </tr>                    
	        </table>
        </form>
		<!-- end of write comment area -->
		
		<!-- comment list area -->
		<br>
	    <hr style="width:550px;margin:0 auto">
	    <br>
	    <table id="comment-list" style="width:550px;border:solid 1px;margin:0 auto">
		
		</table>
		<!-- end of comment list area -->
		
		<br><br><br><br><br>
		
	</div>
	</div>
	
	<!-- Modal -->
	<div class="modal fade" id="recomment-modal" 
		 data-bs-backdrop="static" data-bs-keyboard="false" 
		 tabindex="-1" aria-labelledby="recomment-modal-label" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h1 class="modal-title fs-5" id="recomment-modal-label">댓글 쓰기</h1>
	      </div>
	      <div class="modal-body">
	      <!-- write comment area -->
			<form id="recommentform" action="write-recomment.action" method="post">
				<input type="hidden" name="commentNo" value="" />
				<input type="hidden" name="writer" value="${ loginuser.memberId }" />
				   
				<textarea class="form-control" name="content" rows="3"></textarea>             	
	        </form>
		  <!-- end of write comment area -->
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-secondary cancel-button" data-bs-dismiss="modal">취소</button>
	        <button type="button" class="btn btn-primary write-button">쓰기</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<script type="text/javascript">
	$(function() {
		$('#tolist_button').on('click', function(event) {
			location.href = 'list.action?pageNo=${ requestScope.pageNo }';
		});
		
		$('#delete_button').on('click', function(event) {
			const ok = confirm("${ board.boardNo }번 글을 삭제할까요?");
			if (!ok) return;
			
			//location.href = 'delete.action?boardNo=${board.boardNo}&pageNo=${pageNo}';
			location.href = '${board.boardNo}/delete.action?pageNo=${pageNo}';
		});
		
		$('#update_button').on('click', function(event) {
			location.href = 'edit.action?boardNo=${board.boardNo}&pageNo=${pageNo}';
		});
		
		///////////////////////////////////////
		
		$('#comment-list').load("comment-list.action?boardNo=${ board.boardNo }");
		
		$('#writecomment').on('click', function(event) {
			// alert('서버로 댓글 쓰기 요청');
			// $('#commentform').submit(); // form 객체의 submit 메서드는 form을 서버로 전송하는 명령 (동기 방식)
			
			/*
 			const boardNo = $('#commentform input[name=boardNo]').val();
			const writer = $('#commentform input[name=writer]').val();
			const content = $('#commentform textarea[name=content]').val();			
			*/
			
			const formData = $('#commentform').serialize(); //form 내부의 모든 입력 요소의 값을 전송가능한 문자열 형식으로 반환
			// const formData = $('#commentform').serializeArray(); //form 내부의 모든 입력 요소의 값을 전송가능한 객체 배열 형식으로 반환
						
			$.ajax({
				"url": "write-comment.action",
				"method": "post",
				"data": formData,
				"success": function(data, status, xhr) {
					if (data == "success") {
						// alert('댓글을 등록했습니다.');
						$('#comment-list').load("comment-list.action?boardNo=${ board.boardNo }");
						$('#commentform textarea').val("");
					}
				},
				"error": function(xhr, status, err) {
					
				}
			});
		});
		
		var currentEditCommentNo = null;
		
		$('#comment-list').on('click', '.edit-comment', function(event) {
			event.preventDefault();
			
			if (currentEditCommentNo != null) {
				$('#comment-view-area-' + currentEditCommentNo).show();
				$('#comment-edit-area-' + currentEditCommentNo).hide();
			}
			
			var commentNo = $(this).data('comment-no'); // $(this) : 이벤트 발생 객체 (여기서는 <a class="edit-comment" ...>)
			
			$('#comment-view-area-' + commentNo).hide();
			$('#comment-edit-area-' + commentNo).show();
			
			currentEditCommentNo = commentNo
		});
		$('#comment-list').on('click', '.cancel-edit-comment', function(event) {
			event.preventDefault();
			
			var commentNo = $(this).data("comment-no"); // $(this) : 이벤트 발생 객체 (여기서는 <a class="cancel-edit-comment" ...>)
			const editForm = $('#comment-edit-area-' + commentNo + ' form');
			editForm[0].reset(); // editForm : jQuery 객체 , editForm[0] : javascript 객체
			
			$('#comment-view-area-' + commentNo).show();
			$('#comment-edit-area-' + commentNo).hide();
			
			currentEditCommentNo = null;
		});
		
		// $('#comment-list .delete-comment').on('click', function(event) {
		$('#comment-list').on('click', '.delete-comment', function(event) {
			event.preventDefault();
			
			var commentNo = $(this).data('comment-no'); // .data('comment-no') --> data-comment-no="value"를 조회
			
			const yn = confirm(commentNo + "번 댓글을 삭제할까요?");
			if (!yn) return;
			
			// 동기 방식 All Refresh 요청
			//location.href = 
			//	'delete-comment.action?commentNo=' + commentNo + '&boardNo=${board.boardNo}&pageNo=${pageNo}';
			
			// jQuery 기반의 비동기(ajax) 요청
			$.ajax({
				"url": "delete-comment.action",
				"method": "get",
				"data" : 'commentNo=' + commentNo,
				"success": function(data, status, xhr) {
					if (data == "success") {
						// 1. 해당 댓글만 수정
						/*
						const html = `<br><br>
							<span style='color:gray'>삭제된 글입니다.</span>
							<br><br>`						
						$('#comment-view-area-' + commentNo).html(html); 
						*/
						// 2. 댓글 목록 전체 갱신
						//    jQuery의 load 함수 : 지정된 html 요소의 내용을 응답받은 부분 HTML로 (비동기) 갱신
						$('#comment-list').load("comment-list.action?boardNo=${ board.boardNo }");
					} else {
						alert('삭제 실패 2');
					}
					
				},
				"error": function(xhr, status, err) {
					alert('삭제 실패 1');
				}
			});
			
		});
		
		// $('#comment-list .update-comment').on('click', function(event) {
		$('#comment-list').on('click', '.update-comment', function(event) {
			const commentNo = $(this).data('comment-no'); // $(this) : 이벤트 발생한 객체, .data('abc') : data-abc 값 읽기
			
			const editForm = $('#comment-edit-area-' + commentNo + ' form');
			// alert(editForm.serialize());
			
			$.ajax({
				"url": "update-comment.action",
				"method": "post",
				"data": editForm.serialize(),
				"success": function(data) {
					if (data == "success") {
						$('#comment-list').load("comment-list.action?boardNo=${ board.boardNo }");
					}
				},
				"error": function(xhr, status, err) {
					alert('fail : ' + status);
				}
			});
		});
		
		$('#comment-list').on('click', ".recomment-link", function(event) {
			event.preventDefault();
			
			var commentNo = $(this).data("comment-no");
			$('#recommentform')[0].reset();
			$('#recommentform input[name=commentNo]').val(commentNo);
			
			// javascript code로 modal 표시
			$('#recomment-modal').modal('show');
			
		});
		
		$('#recomment-modal .write-button').on('click', function(event) {
			
			if ( $('#recommentform textarea').val().length == 0 ) {
				alert('댓글을 작성하세요')
				return;
			}
			
			var formData = $('#recommentform').serialize();
			
			$.ajax({
				"url": "write-recomment.action",
				"method": "post",
				"data": formData,
				"success": function(data) {
					if (data == "success") {
						$('#comment-list').load("comment-list.action?boardNo=${ board.boardNo }");
						$('#recomment-modal').modal('hide');
					}
				},
				"error": function() {
					
				}
			});			
		
		});
		
	});
	</script>

</body>
</html>










