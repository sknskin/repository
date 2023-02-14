<%@page language="java" 
		contentType="text/html; charset=utf-8" 
		pageEncoding="utf-8"%>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

		<c:choose>
		<c:when test="${ not empty param.bgcolor }">
		<div id="header" style="background-color:${ param.bgcolor }">
		</c:when>
		<c:otherwise>
		<div id="header">
		</c:otherwise>
		</c:choose>
            <div class="title">
                <a href="/home">GREEN WEBSITE</a>
            </div>
            <div class="links">
                      
            <c:choose>
            <c:when test="${ empty loginuser }">
            	<a href="/account/login">로그인</a>
                <a href="/account/register">회원가입</a>
            </c:when>
            <c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/account/logout">로그아웃</a>
            </c:otherwise>
            </c:choose>
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                    <li><a href="#">사용자관리</a></li>
					<li><a href="#">이메일</a></li>
					<li><a href="#">자료실</a></li>
					<li><a href="/board/list">게시판</a></li>
                </ul>
            </div>
		</div>