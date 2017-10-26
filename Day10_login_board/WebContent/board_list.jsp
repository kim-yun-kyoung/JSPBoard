<%@page import="java.util.List"%>
<%@page import="vo.Article"%>
<%@page import="vo.ArticlePage"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<html>
<head>
<title>게시판 목록</title>
</head>

<body>
<c:set var="myContextPath" value="${pageContext.request.contextPath}"/>
	<h2>이곳은 심플 게시판 화면 입니다.</h2>
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>
		</tr>
	<c:choose>	
		<c:when test="${empty articlePage.articleList}">	
			<tr>
				<td colspan="5">
					작성된 게시글이 존재하지 않습니다.
				</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach var="article" items="${articlePage.articleList}">
				<tr>
					<td>${article.articleNum}</td>
					<td>
					<%-- <%  ArticlePage articlePage = (ArticlePage)request.getAttribute("articlePage");
						List<Article> list = articlePage.getArticleList();
						for(int i = 0; i < list.size();i++){
							out.print(" ㄴ답글:");
					}%> --%>
					<c:forEach var="i" begin="1" end="${article.b_level}">
					 ㄴ답글:${i}
					</c:forEach>
						<a href="${myContextPath}/board?task=read&articleNum=${article.articleNum}">
						${article.title}
						</a>
					</td>
					<td>${article.writer}</td>
					<td><fmt:formatDate value="${article.writeTime}" type="both" dateStyle="short" timeStyle="short"/></td>
					<td>${article.readCount}</td>
					<td>${article.b_list},${article.b_level},${article.b_ridx}</td>
				</tr>
			</c:forEach>
		</c:otherwise>	
	</c:choose>	
	</table>
<!-- 하단 페이지 링크 걸기 -->
	<div>
		<c:if test="${articlePage.startPage>1}">
		<a href="${myContextPath}/board?p=${articlePage.startPage-1}"> 
		[이전] </a>
		</c:if>
		<c:forEach begin="${articlePage.startPage}" 
						end="${articlePage.endPage}" var="i">		
			<a href="${myContextPath}/board?p=${i}"> ${i} </a>
		</c:forEach>
		<c:if test="${articlePage.endPage<articlePage.totalPage}">
		<a href="${myContextPath}/board?p=${articlePage.endPage+1}"> 
		[다음] </a>
		</c:if>			
	</div>
	<div>
		<c:if test="${not empty sessionScope.loginId}">
			<a href="${myContextPath}/board?task=writeForm">
				<button>글쓰기</button>
			</a>
		</c:if>
		
		<c:if test="${empty sessionScope.loginId}">
			<a href="${myContextPath}/member?task=loginForm">
				<button>로그인</button>
			</a>
		</c:if>
	</div>
</body>
</html>




