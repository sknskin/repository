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
                <a href="/spring-demoweb-b/home.action">GREEN WEBSITE</a>
            </div>
            <div class="links">
                      
            <c:choose>
            <c:when test="${ empty loginuser }">
            	<a href="/spring-demoweb-b/account/login.action">로그인</a>
                <a href="/spring-demoweb-b/account/register.action">회원가입</a>
            </c:when>
            <c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/spring-demoweb-b/account/logout.action">로그아웃</a>
            </c:otherwise>
            </c:choose>
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                    <li><a href="#">사용자관리</a></li>
					<li><a href="#">메일보내기</a></li>
					<li><a href="#">자료실</a></li>
					<li><a href="/spring-demoweb-b/board/list.action">게시판</a></li>
                </ul>
            </div>
		</div>