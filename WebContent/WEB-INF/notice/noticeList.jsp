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
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
.table_wrap>table {margin:0 auto; font-size:2vmin;}
table{width:65vw}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: right; border-bottom:1px solid grey}
th,td {padding:1.1vh}
.noti {font-weight:bold ; font-size:5vmin}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<div class="table_wrap">
	<span class="noti">공지사항</span>
		<table>
		<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>글쓴이</th>
					<th>등록일</th>
					<th>조회수</th>
				</tr>
		</thead>
		<tbody>
			<c:forEach items="${notiList }" var="note">
				<tr>
					<td>${note.idx }</td>
					<td><a href="${path1 }/NoticeDetail.do?idx=${note.idx }">${note.title}</a></td>
					<td>${note.author}</td>
					<td>${note.resdate}</td>
					<td>${note.cnt }</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
			<c:if test="${!empty id }">
				<a href="${path1 }/GoInsNotice.do" style="font-size:18px ; line-height:50px">글쓰기</a>
			</c:if>
	</div>

</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
