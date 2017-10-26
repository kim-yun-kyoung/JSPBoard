<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>글 작성 화면</title>
</head>
<body>
	<form action="<%=request.getContextPath()%>/board" method="post">
		<input type="hidden" name="task" value="reply_write">	
		<input type="hidden" name="writer" value="${sessionScope.loginId}">
		<input type="hidden" name="b_list" value="${b_list}">
		<input type="hidden" name="b_level" value="${b_level}">
		<input type="hidden" name="b_ridx" value="${b_ridx}">
											
	${b_list} , ${b_level} , ${b_ridx }												
	<table border="1">
		<tr>
			<td>제목</td>
			<td>
				<c:forEach var="i" begin="0" end="${b_level}">
				ㄴ 답글: 
				</c:forEach>
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