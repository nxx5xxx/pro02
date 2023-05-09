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
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
table{width:65vw ; margin: 0 auto; font-size:2vmin;}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: left; border-bottom:1px solid grey}
th,td {padding:1.1vh}
input{width:100%}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<h2>제작자 : ${author }</h2>

  <a href="${path1 }/NoticeList.do">공지사항</a>
<hr>
	<div class="table_wrap">

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
		</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>