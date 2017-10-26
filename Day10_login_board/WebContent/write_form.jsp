<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>글 작성 화면</title>
</head>
<body>
<%
	String task = request.getParameter("task");
%>
	<form action="<%=request.getContextPath()%>/board" method="post">
		<input type="hidden" name="task" value="write">	
		<input type="hidden" name="writer" value="${sessionScope.loginId}">
	<table border="1">
		<tr>
			<td>제목</td>
			<td>
				<input type="text" name="title" size="10">
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>
				<textarea rows="5" cols="10" name="contents" placeholder="여기에 내용을 입력하세요."></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="작성완료">
			</td>
		</tr>
	</table>
	</form>
	<div>
		<a href="<%=request.getContextPath()%>/board?task=writeForm">글쓰기</a> 
		<a href="<%=request.getContextPath()%>/board?task=boardList">삭제</a>
		<a href="<%=request.getContextPath()%>/board?task=boardList">목록</a>
	</div>
</body>
</html>