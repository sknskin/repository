<%@ page language="java" 
		 contentType="text/html; charset=utf-8" 
		 pageEncoding="utf-8"%>
		 
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
<head>
	<meta charset='utf-8' />
	<title>Register</title>
	<link rel='Stylesheet' href='/spring-demoweb-f/resources/styles/default.css' />
	<link rel='Stylesheet' href='/spring-demoweb-f/resources/styles/input.css' />
	<style type="text/css">
		.error {
			color:red;
			font-weight:bold;
		}
	</style>
</head>
<body>

	<div id='pageContainer'>
		
		<jsp:include page="/WEB-INF/views/include/header.jsp" />
		
		<div id="inputcontent">
			<br /><br />
		    <div id="inputmain">
		        <div class="inputsubtitle">회원기본정보</div>		  
		        <form:form id="registerform" action="register.action" method="post"
		        		   modelAttribute="memberDto">
		        <table>
		            <tr>
		                <th>아이디(ID)</th>
		                <td>
		                    <input type="text" id="memberId" name="memberId" style="width:280px" />
		                    <br>
		                    <form:errors path="memberId" class="error" /><!-- BindingResult에 저장된 오류 메시지 표시 -->
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호</th>
		                <td>
		                	<input type="password" id="passwd" name="passwd" style="width:280px" />
		                	<br>
		                    <form:errors path="passwd" class="error" />
		                </td>
		            </tr>
		            <tr>
		                <th>비밀번호 확인</th>
		                <td>
		                    <input type="password" id="confirm" name="confirm" style="width:280px" />
		                </td>
		            </tr>
		            <tr>
		                <th>이메일</th>
		                <td>
		                	<input type="text" id="email" name="email" style="width:280px" />
		                	<br>
		                    <form:errors path="email" class="error" />
		                </td>
		            </tr>
		                       		            
		        </table>
		        <div class="buttons">
		        	<input id="register" type="submit" value="등록" style="height:25px" />
		        	<input id="cancel" type="button" value="취소" style="height:25px"  />

		        </div>
		        </form:form>
		    </div>
		</div>   	
	</div>

</body>
</html>


