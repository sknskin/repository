<%@page import="com.demoweb.dto.BoardDto"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>    

<!DOCTYPE html>

<html>
<head>
	<meta charset="utf-8" />
	<title>게시글 목록</title>
	<link rel="Stylesheet" href="/demoweb/styles/default.css" />
	<style>
	a { text-decoration: none }
	</style>
</head>
<body>

	<div id="pageContainer">
	
	
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div style="padding-top:25px;text-align:center">
		
			[ <a href="write.action">게시글 쓰기</a> ]
			<br /><br />
			
			<table border="1" style="width:600px;margin:0 auto">
				<tr style="background-color:orange;height:30px">
					<th style="width:100px">번호</th>
					<th style="width:300px">제목</th>
					<th style="width:125px">작성자</th>
					<th style="width:50px">조회수</th>
					<th style="width:125px">작성일</th>					
				</tr>
				<% List<BoardDto> boards = (List<BoardDto>)request.getAttribute("boards"); %>
				<% for (BoardDto board : boards) { %>
				<tr style="height:30px">
					<td><%= board.getBoardNo() %></td>
					<td style="text-align:left; padding-left:5px;">
						<% if (board.isDeleted()) { %>
						<span style="color:lightgray"><%= board.getTitle() %> [삭제된 글]</span>
						<% } else { %>
						<a href='detail.action?boardNo=<%= board.getBoardNo() %>&pageNo=<%= request.getAttribute("pageNo") %>'>
						<%= board.getTitle() %>
						</a>
						<% } %>
					</td>
					<td><%= board.getWriter() %></td>
					<td><%= board.getReadCount() %></td>
					<td><%= board.getRegDate() %></td>
				</tr>
				<% } %>
								
			</table>
			
			<br><br>
			
			<%= request.getAttribute("pager").toString() %>
			
			<br /><br /><br /><br />
		
		</div>
		
	</div>
		

</body>
</html>











