<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>index페이지</title>
</head>
<body>
<c:set var="myContextPath" value="${pageContext.request.contextPath}"/>
<h2>우리 홈페이지에 오신것을 환영합니다.</h2>
<a href="${myContextPath}/member?task=joinForm">회원가입</a>
<a href="${myContextPath}/member?task=loginForm">로그인</a>
	
</body>
</html>