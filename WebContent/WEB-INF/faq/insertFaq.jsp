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
<title>공지사항쓰기</title>
<%@ include file="/common.jsp" %>
<style>
textarea {resize : none; width:100%}
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
table{width:65vw ; margin: 0 auto; font-size:2vmin;}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: right; border-bottom:1px solid grey}
th,td {padding:1.1vh}
input{width:100%}
</style>

</head>

<%@ include file="/header.jsp" %>

<body>
<div class="wrap_bt">
	<div class="table_wrap">
	<h1>공지사항</h1>
	<form action="${path1 }/InsertFaq.do" method="post" >
		<table>
		<tr>
			<th>카테고리</th>
			<td>
			<select name="category" >
				<option value="기타">기타</option>
				<option value="배송관련">배송관련</option>
				<option value="상품관련">상품관련</option>
				<option value="결제관련">결제관련</option>
			</select>					
			</td>
		</tr>
		<tr>	
			<th><label for="title">제목</label></th>
			<td><input type="text" name="title" id="title" placeholder="제목을 입력하세용" required></td>
		</tr>
		<tr>
			<th><label for="content">내용</label></th>
			<td><textarea rows="10" name="content" id="content" placeholder="내용을 입력하세요" required></textarea></td>
		</tr>
		<tr>
			<td><input type="button" value="목록으로" onclick="location.href='${path1 }/NoticeList.do'"></td>
			<td><input type="reset" value="초기화"><input type="submit" value="등록"></td>
		</tr>
		</table>
	</form>
	</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>