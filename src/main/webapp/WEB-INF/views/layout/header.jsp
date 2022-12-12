<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
    Welcome Back, <sec:authentication property="name"/> 
    Welcome Back, <sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css" />
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">HOME</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
			<c:choose>
					<c:when test=" ${empty principal}">
						<li class="nav-item"><a class="nav-link" href="/login-form">로그인</a></li>
						<li class="nav-item"><a class="nav-link" href="/join-form">회원가입</a></li>	
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="/board/form">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/form">회원정보</a></li>	
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>				
					</c:otherwise>
				</c:choose> 
			</ul>
		</div>
	</nav>
	<br>
	
	