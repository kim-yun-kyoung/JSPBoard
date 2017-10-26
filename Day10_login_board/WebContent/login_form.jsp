<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>로그인 화면</title>
</head>
<body>
<c:set var="myContextPath" value="${pageContext.request.contextPath}"/>
<c:if test="${empty sessionScope.loginId}">
	<form action="${myContextPath}/member" method="post">
		ID <input type="text" name="id" size="20"><br>
		PW <input type="password" name="pw" size="20"><br>
		<input type="submit" value="로그인">
		<input type="hidden" name="task" value="login">
	</form>
</c:if>
<c:if test="${not empty sessionScope.loginId}">
<script type="text/javascript">
	alert("이미 로그인 된 사용자 입니다.");
	location.href='${pageContext.request.contextPath}/board';
</script>
</c:if>
</body>
</html>