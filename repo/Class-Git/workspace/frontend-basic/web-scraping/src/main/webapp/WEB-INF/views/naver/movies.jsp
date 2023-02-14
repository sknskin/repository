<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Naver Movies</title>
</head>
<body>

	<table align="center" border="1">
		<tr>
			<th>이미지</th>
			<th>제목/내용</th>
		</tr>
		<c:forEach var="movie" items="${ movies }">
		<tr>
			<td><img src="${ movie.image }" width="99" height="141"></td>
			<td style="width:600px">
				<p><a href="${ movie.link }">${ movie.title }</a></p>
				<p>${ movie.rating } ( ${ movie.count } )</p>
			</td>
		</tr>
		</c:forEach>
	</table>

</body>
</html>