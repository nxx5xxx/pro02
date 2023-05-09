<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">

<title>로그인</title>
<%@ include file="/common.jsp" %>
<style>
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
table{width:35vw ; margin: 0 auto; font-size:2vmin;}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: right; border-bottom:1px solid grey}
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
					<td><input type="password" name="pw" id="pw" placeholder="비밀번호"></td>
				</tr>
				<tr>
					<td><input type="reset" value="취소" style="width:100%"></td>
					<td><input type="submit" value="로그인" style="width:100%"></td>
				</tr>
			</table>
		</form>
	</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>