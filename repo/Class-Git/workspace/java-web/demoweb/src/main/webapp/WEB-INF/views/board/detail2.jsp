<%@page import="com.demoweb.dto.BoardAttachDto"%>
<%@page import="com.demoweb.dto.MemberDto"%>
<%@page import="com.demoweb.dto.BoardDto"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

    
<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>글상세보기</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<link rel="Stylesheet" href="/demoweb/styles/input.css" />
</head>
<body>

	<div id="pageContainer">
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		<div id="inputcontent">
		    <div id="inputmain">
		        <div class="inputsubtitle">게시글 정보</div>
		        <% BoardDto board = (BoardDto)request.getAttribute("board"); %>
		        <table>
		            <tr>
		                <th>제목</th>
		                <td><%= board.getTitle() %></td>
		            </tr>
		            <tr>
		                <th>작성자</th>
		                <td><%= board.getWriter() %></td>
		            </tr>
		            <tr>
		            	<th>조회수</th>
		            	<td><%= board.getReadCount() %></td>
		            </tr>
		            <tr>
		            	<th>등록일자</th>
		            	<td><%= board.getRegDate() %></td>
		            </tr>
		            <tr>
		                <th>첨부파일</th>
		                <td>
		                <% for (BoardAttachDto attachment : board.getAttachments()) { %>
			                <a href="download.action?attachNo=<%= attachment.getAttachNo() %>" style="text-decoration: none">
			                   <%= attachment.getUserFileName() %>
			                </a>
			                [<%= attachment.getDownloadCount() %>]
			                <br>
		                <% } %>
		                </td>
		            </tr>
		            <tr>
		                <th>글내용</th>
						<td>
							<%= board.getContent().replace("\r\n", "<br>")
												  .replace("\r", "<br>")
												  .replace("\n", "<br>") %>
						</td>
		            </tr>
		        </table>
		        <div class="buttons">
		        	<%-- 로그인한 사용자와 작성자가 같은 경우에 편집, 삭제 버튼 표시 --%>
		        	<% MemberDto member = (MemberDto)session.getAttribute("loginuser"); %>
		        	<% if (member != null && member.getMemberId().equals(board.getWriter())) { %>
		        	<input type="button" id="update_button" value="편집" style="height:25px" />
		        	<input type="button" id="delete_button" value="삭제" style="height:25px" />
		        	<% } %>
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
			location.href = 'list.action?pageNo=<%= request.getAttribute("pageNo") %>';
		});
		
		$('#delete_button').on('click', function(event) {
			const ok = confirm("<%= board.getBoardNo() %>번 글을 삭제할까요?");
			if (!ok) return;
			
			location.href = 'delete.action?boardNo=<%= board.getBoardNo() %>&pageNo=<%= request.getAttribute("pageNo") %>';
		});
		
		$('#update_button').on('click', function(event) {
			location.href = 'edit.action?boardNo=<%= board.getBoardNo() %>&pageNo=<%= request.getAttribute("pageNo") %>';
		});
	});
	</script>

</body>
</html>










