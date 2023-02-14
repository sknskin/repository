<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>


<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8'>
	<title>Error</title>
	<link rel='Stylesheet' href='/spring-demoweb-f/resources/styles/default.css'>
</head>
<body>

	<div id='pageContainer'>
			
		<jsp:include page="/WEB-INF/views/include/header.jsp">
			<jsp:param name="bgcolor" value="skyblue" />
		</jsp:include>
		
		<div id='content'>
			
		</div>
	</div>
	
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript">
	$(function() {
		alert("[${ error_type }] : ${ message }");
		location.href = "/spring-demoweb-f/board/list.action";
	});
	</script>

</body>
</html>







