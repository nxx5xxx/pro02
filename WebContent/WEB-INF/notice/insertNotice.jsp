<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<c:set var="path1" value="${pageContext.request.contextPath }"/>

<%
//path1는 c:set으로 잡아줘야한다
	String path123 = request.getContextPath();
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/common.jsp" %>
<style>
</style>

</head>

<%@ include file="/header.jsp" %>
<!-- 파일을 보내려면 멀티미디어데이터라
enctype=multipart/form-data 를써줘야한다
enctype는 인코딩 타입이라는의미
이것을 하기위해서는 cos.jar가 필요하다
<form action="" method="" enctype="multipart/form-data">

첨부파일 올리기
<label for="file1"> 첨부파일 </label>
<input type="file" name="file" id="file1" >
accept="*.pdf, *png, *.jpg" 이걸쓸경우  이파일들만 가능하다는 의미 -->
<body>
	
	<form action="${path1 }/InsertNotice.do" method="post" enctype="multipart/form-data">
		<table>
		<tr>	
			<th><label for="title">제목</label></th>
			<td><input type="text" name="title" id="title" placeholder="제목을 입력하세용"></td>
		</tr>
		<tr>
			<th><label for="content">내용</label></th>
			<td><textarea rows="10" cols="100" name="content" id="content" placeholder="내용을 입력하세요"></textarea></td>
		</tr>
		<tr>
			<th><label for="file1">첨부파일</label></th>
			<td><input type="file" name="file" id="file1"></td>
		</tr>
		<tr>
			<th><label for="author">글쓴이</label></th>
			<td><input type="text" name="author" id="author" value="${id }" readonly></td>
		</tr>
		<tr>
			<td><input type="submit" value="등록"></td>
			<td><input type="reset" value="취소"></td>
		</tr>
		</table>
	</form>
</body>
</html>