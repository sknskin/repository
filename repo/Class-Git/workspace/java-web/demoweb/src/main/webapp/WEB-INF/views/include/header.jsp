<%@page import="com.demoweb.dto.MemberDto"%>
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
                <a href="/demoweb/home.action">GREEN WEBSITE</a>
            </div>
            <div class="links">
                      
            <c:choose>
            <c:when test="${ empty loginuser }">
            	<a href="/demoweb/account/login.action">로그인</a>
                <a href="/demoweb/account/register.action">회원가입</a>
            </c:when>
            <c:otherwise>
            	${ loginuser.memberId }님 환영합니다.
            	<a href="/demoweb/account/logout.action">로그아웃</a>
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
					<li><a href="/demoweb/board/list.action">게시판</a></li>
                </ul>
            </div>
		</div>
		<div style="text-align:right;padding:5px;border:solid 1px;">
		[현재접속자수 : ${ current_count }]
		[누적접속자수 : ${ total_count }]
		
		</div>
		
		
		
		
		
		
		
		
		
		
		
		
		