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
	<link rel="Stylesheet" href="/spring-demoweb-d/resources/styles/default.css" />
	<link rel="Stylesheet" href="/spring-demoweb-d/resources/styles/input.css" />
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
	});
	</script>

</body>
</html>










