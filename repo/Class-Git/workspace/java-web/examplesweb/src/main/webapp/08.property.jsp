<%@page import="com.examplesweb.dto.MemberDto"%>
<%@ page language="java" 
		 contentType="text/html; charset=UTF-8"
    	 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Java Bean & Property</title>
</head>
<body>

<% MemberDto member = new MemberDto(); %>
<jsp:useBean id="member2" class="com.examplesweb.dto.MemberDto" />

<% 
member.setMemberId("johndoe"); 
member.setEmail("johndoe@example.com");
%>
<jsp:setProperty name="member2" property="memberId" value="janedoe" /> <%-- memberId : setMemberId --%>
<jsp:setProperty name="member2" property="email" value="janedoe@example.com" /> <%-- email : setEamil --%>

	<h1><%= member.getMemberId() %></h1>
	<h1><%= member.getEmail() %></h1>
	
	<hr>
	
	<h1><jsp:getProperty name="member2" property="memberId" /></h1> <%-- memberId --> getMemberId --%>
	<h1><jsp:getProperty name="member2" property="email" /></h1> <%-- email : getEmail --%>

</body>
</html>





