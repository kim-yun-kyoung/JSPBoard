<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>�� �ۼ� ȭ��</title>
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
			<td>����</td>
			<td>
				<c:forEach var="i" begin="0" end="${b_level}">
				�� ���: 
				</c:forEach>
				<input type="text" name="title" size="10">
			</td>
		</tr>
		<tr>
			<td>����</td>
			<td>
				<textarea rows="5" cols="10" name="contents" placeholder="���⿡ ������ �Է��ϼ���."></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="�ۼ��Ϸ�">
			</td>
		</tr>
	</table>
	</form>
	<div>
		<a href="<%=request.getContextPath()%>/board?task=writeForm">�۾���</a> 
		<a href="<%=request.getContextPath()%>/board?task=boardList">����</a>
		<a href="<%=request.getContextPath()%>/board?task=boardList">���</a>
	</div>
</body>
</html>