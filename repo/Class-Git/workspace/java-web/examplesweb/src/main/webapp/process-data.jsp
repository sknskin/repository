<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<%
String sDan = request.getParameter("dan"); // 요청 데이터는 언제나 String
int dan = Integer.parseInt(sDan); // 문자열 -> 정수
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구구단(<%= dan %>)</title>
</head>
<body>
	<table border='1' style='width:300px;margin:0 auto'>
	<% for (int i = 0; i < 10; i++) { %>
		<tr><th><%= dan %> x <%= i %> = <%= dan * i %></th></tr>
	<% } %>
	</table>
</body>
</html>








