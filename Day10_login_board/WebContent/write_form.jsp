<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<html>
<head>
<title>�� �ۼ� ȭ��</title>
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
			<td>����</td>
			<td>
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