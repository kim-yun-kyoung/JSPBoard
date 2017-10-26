<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<html>
<head>
<title>회원가입 화면</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript">
function checkId(){
	
	return false;
}

$(function(){
	$('#checkBtn').click(function(){
		var id = $('#idInput').val();
		
		$.ajax({
			type:"post",
			url : "member?task=searchId",
			data:"checkId="+id,
			dataType:'text',
			success:function(check){
				if(check.length > 0){
					alert(check+"는 이미 존재하는 아이디입니다.");
					$('#idInput').value("");	
				}else{
					alert("사용 가능한 아이디입니다.");
				}
			},
			error:function(){}
		})
		return false;
	})
})
	
	
</script>
</head>
<body>
<c:set var="myContextPath" value="${pageContext.request.contextPath}"/>
<form action="${myContextPath}/member" method="post">
	<input type="hidden" name="task" value="join">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" id="idInput" name="id" size="20" placeholder="아이디를 입력하세요."></td>
			<td><input type="button" id="checkBtn"  value="중복 검사"></td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="password" name="pw" size="20" placeholder="패스워드를 입력하세요."></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" size="20" placeholder="이름을 입력하세요."></td>
		</tr>
		<tr>
			<td>휴대폰</td>
			<td><input type="text" name="phone" size="20" placeholder="01012345678"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="작성완료"></td>
		</tr>
	</table>
</form>
</body>
</html>