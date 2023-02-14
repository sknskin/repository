<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로또 당첨 예상 번호</title>
</head>
<body>
	<% int[] numbers = (int[])request.getAttribute("numbers"); %>
	
	<table border="1" style="width:500px;margin:0 auto">
		<tr>
			<th colspan="6" style="height:50px;font-size:15pt">
				<a href="select-lotto-numbers">당첨 예상 번호 뽑기</a>
			</th>
		</tr>
		<tr style="height:75px">
		<% for (int number : numbers) { %>
			<th><%= number %></th>
		<% } %>
		</tr>
	</table>

</body>
</html>