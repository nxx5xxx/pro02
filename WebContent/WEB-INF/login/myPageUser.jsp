<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8" );
%>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">

<title>마이페이지</title>
<%@ include file="/common.jsp" %>
<style>

</style>

</head>
<body>
<%@ include file="/header.jsp" %>
	<h2>제작자 : ${author }</h2>
	<a href="${path1 }/admin/memberList.jsp">전체고객목록보기</a>
	<a href="${path1 }/board/board.jsp">전체게시글보기</a>
	

  <a href="${path1 }/NoticeList.do">공지사항</a>
<hr>
		<table>
			<tr>
				<th>아이디</th>
				<td>${id}</td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>${user.pw}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td>${user.name}</td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td>${user.tel}</td>
			</tr>
			<tr>
				<th>주소</th>
				<td>${user.address}</td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>${user.email}</td>
			</tr>
			<tr>
				<th>가입일</th>
				<td>${user.regdate}</td>
			</tr>
			<tr>
				<th>포인트</th>
				<td>${user.point}</td>
			</tr>
			<tr>
				<td><input type="button" value="뒤로" onclick="location.href='goBack()'" style="width:100%"></td>
				<td><input type="button" value="수정" onclick="location.href='${path1 }/GoMyPageUserModify.do?id=${id}'" style="width:100%"></td>
			</tr>
		</table>
</body>
</html>