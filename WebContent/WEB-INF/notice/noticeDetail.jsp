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
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
table{width:65vw ; margin: 0 auto; font-size:2vmin;}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: left; border-bottom:1px solid grey}
th,td {padding:1.1vh}
input{width:100%}
.td_min {min-height:55vh ; background-color:white;}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<div class="table_wrap">
 <table>
		<tr>
			<th>제목</th>
			<td colspan="3">${note.title}</td>
		</tr>
		<tr>
			<th>글 번호</th>
			<td>${note.idx }</td>
			<th>글쓴이</th>
			<td>${note.author}</td>
		</tr>
		<tr>
			<th style="vertical-align:top">내용</th>
			<td colspan="3"><pre class="td_min">${note.content}</pre></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<c:if test="${fileName == 'null'}">
			<td colspan="3">첨부파일 없음</td>
			</c:if>
			<c:if test="${fileName != 'null' }">
			<td colspan="3"><a href="${path1 }/${filePath }/${fileName }" download >${fileName}</a></td>
			</c:if>
		</tr>
		<tr>
			<th>등록일</th>
			<td>
			<fmt:parseDate value="${note.resdate}" var="resdate" pattern="yyyy-MM-dd HH:mm:ss"/>
			<fmt:formatDate value="${resdate}" pattern="yyyy/MM/dd HH:mm" />
			</td>
			<th>조회수</th>
			<td>${note.cnt }</td>
		</tr>
		
</table> 
		<div>
			<a href="${path1 }/NoticeList.do" style="font-size:24px">목록으로</a>
			<c:if test="${id == 'admin'|| note.author == id }">
			|<a href="${path1 }/GoUpdateNotice.do?idx=${note.idx }" style="font-size:24px">글 수정</a>
			</c:if>
		</div>
	</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>