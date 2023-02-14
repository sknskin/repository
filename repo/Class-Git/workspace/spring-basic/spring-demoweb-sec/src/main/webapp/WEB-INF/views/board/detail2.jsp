<%@page language="java" 
		contentType="text/html; charset=utf-8"
    	pageEncoding="utf-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글상세보기</title>
	<link rel="Stylesheet" href="/spring-demoweb-sec/resources/styles/default.css" />
	<link rel="Stylesheet" href="/spring-demoweb-sec/resources/styles/input.css" />
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
		<c:forEach var="comment" items="${ board.comments }">				
			<tr>
				<td style="text-align:left;margin:5px;border-bottom: solid 1px;">					
					<div id="comment-view-area-${ comment.commentNo }">
					<c:choose>
					<c:when test="${ comment.deleted }">
						<br><br>
						<span style='color:gray'>삭제된 글입니다.</span>
						<br><br>
					</c:when>
					<c:otherwise>
						${ comment.writer } &nbsp;&nbsp; [${ comment.regDate }]
					    <br /><br />
					    <span>${ fn:replace(comment.content, enter, "<br>") }</span>
						<br /><br />
						<div style='display:${ (not empty loginuser and loginuser.memberId == comment.writer) ? "block" : "none" }'>
					    	<a class="edit-comment" data-comment-no="${ comment.commentNo }" href="javascript:">편집</a>
							&nbsp;
							<a class="delete-comment" data-comment-no="${ comment.commentNo }" href="javascript:">삭제</a>
						</div>
						<a class="recomment-link btn btn-sm btn-success">댓글 쓰기</a>
					</c:otherwise>
					</c:choose>
					</div>	                
					<div id="comment-edit-area-${ comment.commentNo }" style="display: none">
						${ comment.writer } &nbsp;&nbsp; [${ comment.regDate }]
						<br /><br />
						<form>
						<input type="hidden" name="commentNo" value="${ comment.commentNo }" />
						<textarea name="content" style="width: 550px" rows="3" 
							maxlength="200">${ comment.content }</textarea>
						</form>
						<br />
						<div>
							<a class="update-comment" href="javascript:">수정</a> 
							&nbsp; 
							<a class="cancel-edit-comment" data-comment-no="${ comment.commentNo }" href="javascript:">취소</a>
						</div>
					</div>
			
				</td>
			</tr>
		</c:forEach>        	
		</table>
		<!-- end of comment list area -->
		
		<br><br><br><br><br>
		
	</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
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
		
		$('#writecomment').on('click', function(event) {
			// alert('서버로 댓글 쓰기 요청');
			$('#commentform').submit(); // form 객체의 submit 메서드는 form을 서버로 전송하는 명령
		});
		
		$('#comment-list .edit-comment').on('click', function(event) {
			event.preventDefault();
			
			var commentNo = $(this).data('comment-no'); // $(this) : 이벤트 발생 객체 (여기서는 <a class="edit-comment" ...>)
			
			$('#comment-view-area-' + commentNo).hide();
			$('#comment-edit-area-' + commentNo).show();
		});
		$('#comment-list .cancel-edit-comment').on('click', function(event) {
			event.preventDefault();
			
			var commentNo = $(this).data("comment-no"); // $(this) : 이벤트 발생 객체 (여기서는 <a class="cancel-edit-comment" ...>)
			
			$('#comment-view-area-' + commentNo).show();
			$('#comment-edit-area-' + commentNo).hide();
		});
		
		$('#comment-list .delete-comment').on('click', function(event) {
			event.preventDefault();
			
			var commentNo = $(this).data('comment-no'); // .data('comment-no') --> data-comment-no="value"를 조회
			
			const yn = confirm(commentNo + "번 댓글을 삭제할까요?");
			if (!yn) return;
			
			location.href = 
				'delete-comment.action?commentNo=' + commentNo + '&boardNo=${board.boardNo}&pageNo=${pageNo}';
			
		});
		
	});
	</script>

</body>
</html>










