<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>�����ϱ� �Ϸ�</title>
</head>
<body>
	<%
		int articleNum = 
			(Integer) request.getAttribute("articleNum");
	%>
	<h2>�����ϱⰡ �Ϸ�Ǿ����ϴ�.</h2>
	<a href=
		"<%=request.getContextPath()%>/board?task=boardList">
		[�Խ��� �������]
	</a>
	<a href=
		"<%=request.getContextPath()%>/board?task=read&articleNum=<%=articleNum%>">
		[������ �� ����]
	</a>
</body>
</html>




