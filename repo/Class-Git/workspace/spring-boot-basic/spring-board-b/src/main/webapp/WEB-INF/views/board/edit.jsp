<%@page import="com.demoweb.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글수정</title>
	<link rel="Stylesheet" href="/resources/styles/default.css" />
	<link rel="Stylesheet" href="/resources/styles/input2.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시글 정보</div>
		        
		        <form action="edit"
		        	  method="post">
		        	<input type="hidden" name="boardNo" value="${ board.boardNo }">
		        	<input type="hidden" name="pageNo" value="${ pageNo }">
		        <table>
		            <tr>
		                <th>제목</th>
		                <td>
		                    <input type="text" name="title" style="width:580px"
		                    	   value="${ board.title }" />
		                </td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td>
		                	${ board.writer }		                	
		                </td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                    <input type="file" name="attach" style="width:580px;height:20px" />
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
		                <td>
		                	<textarea name="content" 
		                		style="width:580px" rows="15">${ board.content }</textarea>
		                </td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<input type="submit" value="글수정" style="height:25px" />
		        	<input id="btn-cancel" type="button" value="취소" style="height:25px"  />
		        </div>
		        </form>
		    </div>
		</div>   	
	
	</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#btn-cancel').on('click', function(event) {
			location.href = 'detail?boardNo=${ board.boardNo }&pageNo=${ pageNo }';
		});
	});
	</script>

</body>
</html>






