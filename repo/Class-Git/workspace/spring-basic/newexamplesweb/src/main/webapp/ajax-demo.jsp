<%@page import="java.util.Date"%>
<%@page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>
		 
<%

%>
		 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

	[<a href="ajax-demo.jsp">현재 시간 보기 (동기방식)</a>]
	[<a href="#" id="async-link">현재 시간 보기 (비동기방식)</a>]
	<hr>
	<div id="message">
		<%= new Date() %>
	</div>
	
	<% if (Math.random() > 0.5) { %>
	<img src="nature.jpg">
	<% } else { %>
	<img src="nature2.jpg">
	<% } %>
	
	
	<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
	<script type="text/javascript">
	$(function() {
		$('#async-link').on('click', function(event) {
			event.preventDefault();
			
			$.ajax({
				"url": "get-time.action",
				"method": "get",
				"success": function(data, status, xhr) {
					$('#message').text(data);
				},
				"error": function(xhr, status, err) {
					alert(err);
				}
			});
			
		});
		
	});
	</script>

</body>
</html>












