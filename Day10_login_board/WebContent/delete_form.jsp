<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>���� Ȯ�� ������</title>
</head>
<body>	
<%
	int articleNum = 
		(Integer) request.getAttribute("articleNum");
%>
	<form action="<%=request.getContextPath()%>/board" 
											method="post">
		�� ��й�ȣ : 
		<input type="submit" value="����Ȯ��">
		<input type="hidden" name="type" value="delete">
		<input type="hidden" name="articleNum" value="<%=articleNum%>">
	</form>
</body>
</html>