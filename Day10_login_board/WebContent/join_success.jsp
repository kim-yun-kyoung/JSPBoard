<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입 완료</title>
</head>
<body>
<c:set var="myContextPath" value="${pageContext.request.contextPath}"/>
	<h2>회원가입이 완료되었습니다.</h2>
	<a href="${myContextPath}/member?task=loginForm">로그인하러 가기</a>
	
</body>
</html>