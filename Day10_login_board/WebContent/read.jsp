<%@page import="vo.Article"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<title>글 읽기 화면</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>글번호:</td>
			<td>${article.articleNum }</td>
		</tr>
		<tr>
			<td>제목:</td>
			<td>
			<c:forEach begin="1" end="${article.b_level}">ㄴ답글:</c:forEach>
				${article.title}
			</td>
		</tr>
		<tr>
			<td>작성자:</td>
			<td>${article.writer }</td>
		</tr>
		<tr>
			<td>작성일:</td>
			<td>${article.writeTime }</td>
		</tr>
		<tr>
			<td>조회수:</td>
			<td>${article.readCount}</td>
		</tr>
		<tr>
			<td>내용:</td>
			<td>${article.contents}</td>
		</tr>
	</table>
	<c:if test="${sessionScope.loginId==article.writer}">
	<a href="<%=request.getContextPath()%>/board?task=updateForm&articleNum=${article.articleNum}">
		[수정하기]
	</a>
	<a href="<%=request.getContextPath()%>/board?task=deleteForm&articleNum=${article.articleNum}">
		[삭제하기]
	</a>
	</c:if>
	<a href="<%=request.getContextPath()%>/board?task=replyForm&b_list=${article.b_list}
				&b_level=${article.b_level}&b_ridx=${article.b_ridx}">[답글쓰기]</a>
	<a href="<%=request.getContextPath()%>/board?task=boardList">
		[게시판 목록으로]
	</a>
	<br>
	${article.b_list},${article.b_level},${article.b_ridx}
</body>
</html>





