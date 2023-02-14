<%@page language="java" 
		contentType="text/html; charset=utf-8" 
		pageEncoding="utf-8"%>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

		<c:choose>
		<c:when test="${ not empty param.bgcolor }">
		<div id="header" style="background-color:${ param.bgcolor }">
		</c:when>
		<c:otherwise>
		<div id="header">
		</c:otherwise>
		</c:choose>
            <div class="title">
                <a href="/spring-demoweb-f/home.action">GREEN WEBSITE</a>
            </div>
            <div class="links">
                      
            <c:choose>
            <c:when test="${ empty loginuser }">
            	<a href="/spring-demoweb-f/account/login.action">로그인</a>
                <a href="/spring-demoweb-f/account/register.action">회원가입</a>
            </c:when>
            <c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/spring-demoweb-f/account/logout.action">로그아웃</a>
            </c:otherwise>
            </c:choose>
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                    <li><a href="#"><spring:message code="menu.admin" /></a></li>
					<li><a href="#"><spring:message code="menu.email" /></a></li>
					<li><a href="#"><spring:message code="menu.library" /></a></li>
					<li><a href="/spring-demoweb-f/board/list.action"><spring:message code="menu.board" /></a></li>
                </ul>
            </div>
		</div>