<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Naver IT News</title>
</head>
<body>

	<table align="center" border="1">
		<tr>
			<th>이미지</th>
			<th>제목/내용</th>
		</tr>
		<c:forEach var="news" items="${ newsList }">
		<tr>
			<td><img src="${ news.image }" width="106" height="72"></td>
			<td style="width:600px">
				<p><a href="${ news.link }">${ news.title }</a></p>
				<p>${ news.desc }</p>
			</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>