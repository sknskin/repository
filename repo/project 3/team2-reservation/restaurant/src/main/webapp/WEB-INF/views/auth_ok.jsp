<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

	

<% request.setCharacterEncoding("utf-8");
   String code = request.getParameter("code");
   if(session.getAttribute("auth").equals(code)){
	   session.setAttribute("authYN", "y");
	   response.sendRedirect("reservation.jsp");
   }else{%>
   <script>
   	alert("인증번호가 틀렸습니다.");
   	location.href="auth.jsp";
   </script> 
   <%} %>

	
	
