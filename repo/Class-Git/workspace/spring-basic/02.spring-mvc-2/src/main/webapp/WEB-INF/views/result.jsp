<%@ page session="false" pageEncoding="UTF-8" contentType="text/html; charset=utf-8" %>
<html>
<head>
	<title>Result</title>
</head>
<body>
	<br /><br /><br />
	<div style="padding-left:20px">
		<h2>This is Result Page !!!!!</h2>
		
		<h3><%= request.getAttribute("person2") %></h3>
		<h3>${ person2 }</h3>
		<h3>${ person }</h3>
		
	</div>
</body>
</html>
