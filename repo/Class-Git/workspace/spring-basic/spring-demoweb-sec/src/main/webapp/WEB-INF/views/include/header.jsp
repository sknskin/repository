<%@page language="java" 
		contentType="text/html; charset=utf-8" 
		pageEncoding="utf-8"%>
		
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

		<div id="header">
            <div class="title">
                <a href="/spring-demoweb-sec/home.action">GREEN WEBSITE</a>
            </div>
            <div class="links">
            
            <sec:authorize access="isAnonymous()">
            	<a href="/spring-demoweb-sec/account/login.action">로그인</a>
                <a href="/spring-demoweb-sec/account/register.action">회원가입</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
            	<sec:authentication property="name" /> 님 환영합니다.
            	<a href="/spring-demoweb-sec/logout">로그아웃</a>
            </sec:authorize>
            
            </div>
        </div>
                
        <div id="menu">
            <div>
                <ul>
                    <li><a href="/spring-demoweb-sec/admin/user-management">사용자관리</a></li>
					<li><a href="#">메일보내기</a></li>
					<li><a href="#">자료실</a></li>
					<li><a href="/spring-demoweb-sec/board/list.action">게시판</a></li>
                </ul>
            </div>
		</div>