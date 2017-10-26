<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>수정하기 완료</title>
</head>
<body>
	<%
		int articleNum = 
			(Integer) request.getAttribute("articleNum");
	%>
	<h2>수정하기가 완료되었습니다.</h2>
	<a href=
		"<%=request.getContextPath()%>/board?task=boardList">
		[게시판 목록으로]
	</a>
	<a href=
		"<%=request.getContextPath()%>/board?task=read&articleNum=<%=articleNum%>">
		[수정한 글 보기]
	</a>
</body>
</html>




