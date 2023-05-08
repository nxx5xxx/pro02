<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />

<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<%@ include file="/common.jsp" %>
<style>
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<table>
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>등록일</th>
			<th>조회수</th>
		</tr>
	<c:forEach items="${notiList }" var="note">
		<tr>
			<td>${note.idx }</td>
			<td><a href="${path1 }/NoticeDetail.do?idx=${note.idx }">${note.title}</a></td>
			<td>${note.author}</td>
			<td>${note.resdate}</td>
			<td>${note.cnt }</td>
		</tr>
	</c:forEach>
	<c:if test="${!empty id }">
	<tr>
	<td colspan="4"><a href="${path1 }/GoInsNotice.do">글쓰기</a></td>
	</tr>
	</c:if>
</table>

</body>
</html>
