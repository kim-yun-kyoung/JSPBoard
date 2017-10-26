<%@page import="vo.Article"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<title>�� �б� ȭ��</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>�۹�ȣ:</td>
			<td>${article.articleNum }</td>
		</tr>
		<tr>
			<td>����:</td>
			<td>
			<c:forEach begin="1" end="${article.b_level}">�����:</c:forEach>
				${article.title}
			</td>
		</tr>
		<tr>
			<td>�ۼ���:</td>
			<td>${article.writer }</td>
		</tr>
		<tr>
			<td>�ۼ���:</td>
			<td>${article.writeTime }</td>
		</tr>
		<tr>
			<td>��ȸ��:</td>
			<td>${article.readCount}</td>
		</tr>
		<tr>
			<td>����:</td>
			<td>${article.contents}</td>
		</tr>
	</table>
	<c:if test="${sessionScope.loginId==article.writer}">
	<a href="<%=request.getContextPath()%>/board?task=updateForm&articleNum=${article.articleNum}">
		[�����ϱ�]
	</a>
	<a href="<%=request.getContextPath()%>/board?task=deleteForm&articleNum=${article.articleNum}">
		[�����ϱ�]
	</a>
	</c:if>
	<a href="<%=request.getContextPath()%>/board?task=replyForm&b_list=${article.b_list}
				&b_level=${article.b_level}&b_ridx=${article.b_ridx}">[��۾���]</a>
	<a href="<%=request.getContextPath()%>/board?task=boardList">
		[�Խ��� �������]
	</a>
	<br>
	${article.b_list},${article.b_level},${article.b_ridx}
</body>
</html>





