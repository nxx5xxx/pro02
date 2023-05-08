<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>${updatenotice.title} 수정</title>
<%@ include file="/common.jsp" %>
<style>
#filechange {display:none;}
#yeschange:checked ~ #filechange {display:block}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<form action="${path1 }/UpdateNotice.do" method="post" enctype="multipart/form-data">
${updatenotice.title} 수정페이지
 <table>
		<tr>
			<th>글번호</th>
			<th>title</th>
			<th>content</th>
			<th>author</th>
			<th>file1</th>
			<th>resdate</th>
			<th>cnt</th>
		</tr>
		<tr>
			<td>${updatenotice.idx }</td>
			<td>
			<input type="text" id="title" name="title" value="${updatenotice.title}">			
			</td>
			<td>
			<label for="content">${updatenotice.content}</label>
			<textarea rows="10" cols="200" id="content" name="content" >${updatenotice.content}</textarea>
			</td>
			<td>${updatenotice.author }</td>
			<c:if test="${fileName == 'null'}">
			<td>
			<label for="file">첨부파일 없음</label>
			<input type="file" name="file" id="file">
			</td>
			</c:if>
			<c:if test="${fileName != 'null'}">
			<td><p>${fileName } 안비어있음</p>
				<p>
				<input type="radio" name="filechange" id="nochange" checked><label for="nochange">교체안함</label>
				<input type="radio" name="filechange" id="yeschange"><label for="nochange">교체함</label>
				<input type="file" name="file" id="filechange">
				<input type="hidden" name="file" id="hiddenfile" value="${fileName}">${fileName}
				</p>
			</td>
			</c:if>
			<td>
			<fmt:parseDate value="${updatenotice.resdate}" var="resdate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<fmt:formatDate value="${resdate}" pattern="yyyy/MM/dd HH:mm" />
			</td>
			<td>${updatenotice.cnt }</td>
		</tr>
</table> 
<div>
	<!-- 히든처리해서 넘길것들  -->
	<input type="hidden" name="idx" value="${updatenotice.idx }">
	<input type="hidden" name="author" value="${updatenotice.author}">
	<input type="button" value="되돌아가기" onclick="location.href='${path1 }/NoticeList.do'">
	<input type="submit" value="수정">
	<input type="button" value="삭제" onclick="location.href='${path1 }/DeleteNotice.do?idx=${updatenotice.idx }'">
</div>
</form>
</body>
</html>