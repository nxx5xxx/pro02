<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${note.title }</title>
<%@ include file="/common.jsp" %>
<style>
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
 <table>
		<tr>
			<th>idx</th>
			<th>title</th>
			<th>content</th>
			<th>author</th>
			<th>file1</th>
			<th>resdate</th>
			<th>cnt</th>
		</tr>
		<tr>
			<td>${note.idx }</td>
			<td>${note.title}</td>
			<td>${note.content}</td>
			<td>${note.author}</td>
			<c:if test="${fileName == 'null'}">
			<td>첨부파일 없음</td>
			</c:if>
			<c:if test="${fileName != 'null' }">
			<td><a href="${path1 }/${filePath }/${fileName }" download >${fileName}</a></td>
			</c:if>
			<td>
			<fmt:parseDate value="${note.resdate}" var="resdate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<fmt:formatDate value="${resdate}" pattern="yyyy/MM/dd HH:mm" />
			</td>
			<td>${note.cnt }</td>
		</tr>
</table> 
<div>
	<a href="${path1 }/NoticeList.do">목록으로</a>
	<c:if test="${id == 'admin' }">
	<a href="${path1 }/GoUpdateNotice.do?idx=${note.idx }">글 수정</a>
	</c:if>
	<c:if test="${note.author == id }">
	<a href="${path1 }/GoUpdateNotice.do?idx=${note.idx }">글 수정</a>
	</c:if>
</div>
</body>
</html>