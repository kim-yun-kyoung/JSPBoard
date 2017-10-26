<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인 실패</title>
</head>
<body>
	<h2>${sessionScope.loginId}님 반갑습니다.</h2>
	<a href="${pageContext.request.contextPath}/member?task=loginForm">로그인 하러 가기</a>
</body>
</html>