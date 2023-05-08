<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">

<title>메인페이지</title>
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
<h6>${alert }</h6>
	<form action="${path1 }/Login.do" method="post">
		<table>
			<tr>
				<th><label for="id">아이디</label></th>
				<td><input type="text" name="id" id="id" placeholder="아이디">
				</td>
				
			</tr>
			<tr>
				<th><label for="pw">비밀번호</label></th>
				<td><input type="text" name="pw" id="pw" placeholder="비밀번호"></td>
			</tr>
			<tr>
				<td><input type="reset" value="취소" style="width:100%"></td>
				<td><input type="submit" value="로그인" style="width:100%"></td>
			</tr>
		</table>
	</form>
</body>
</html>