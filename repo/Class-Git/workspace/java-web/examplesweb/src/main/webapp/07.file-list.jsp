<%@page import="java.net.URLEncoder"%>
<%@page import="java.io.File"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>
    	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload File List</title>
</head>
<body>

[ <a href="07.file-upload.jsp">파일 업로드</a> ]
<hr>

<%-- upload-files 폴더에 저장된 파일 목록을 읽고 표시 --%>
<%
//업로드한 파일이 저장된 디렉터리 경로를 컴퓨터 경로로 변경해서 가져오기
String path = application.getRealPath("/upload-files");
// 디렉터리 관리 객체 만들기
File dir = new File(path);

%>
<% if (!dir.exists()) { %>
	<h3 style="color:red">업로드 디렉터리가 없습니다.</h3>
<% } else { %>
	<% 
	// 디렉터리 하위의 모든 파일과 디렉터리 정보 가져오기
	File[] files = dir.listFiles();
	%>	
	<% for (File file : files) { // 디렉터리 하위의 파일을 순서대로 읽어서 %>
		<a href='file-download?filename=<%= URLEncoder.encode(file.getName(), "utf-8") %>' style="text-decoration: none">
		<%= file.getName() %>
		</a>
		<br>
	<% } %>
<% } %>

</body>
</html>











