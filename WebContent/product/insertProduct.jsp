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
<title>상품추가</title>
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
	<h1>상품추가</h1>
	<form action="${path1 }/ProductInsert.do" method="post" enctype="multipart/form-data">
		<table>
		<tr>	
			<th><label for="pcode">상품코드</label></th>
			<td><input type="text" name="pcode" id="pcode" placeholder="자동으로 입력됩니다" readonly></td>
		</tr>
		<tr>
			<th><label for="pname">상품이름</label></th>
			<td><input type="text" name="pname" id="pname" placeholder="상품이름" required></td>
		</tr>
		<tr>
			<th><label for="pdesc">내용</label></th>
			<td><textarea rows="10" name="pdesc" id="pdesc" placeholder="상품내용"></textarea></td>
		</tr>
		<tr>
			<th><label for="img2">본문 이미지</label></th>
			<td><input type="file" name="img2" id="img2" ></td>
		</tr>
		<tr>
			<th><label for="price">상품가격</label></th>
			<td><input type="text" name="price" id="price" pattern="^[0-9]+$" required></td>
		</tr>
		<tr>
			<th><label for="pamount">재고량</label></th>
			<td><input type="text" name="pamount" id="pamount" required></td>
		</tr>
		<tr>
			<th><label for="ccode">카테고리코드</label></th>
			<td><input type="text" name="ccode" id="ccode" placeholder="01닌텐도02플스03엑스박스/01기타02액션03총04RPG/04기타01조이스틱02주변기기03완구" required></td>
		</tr>
		<tr>
			<th><label for="img">썸네일 이미지</label></th>
			<td><input type="file" name="img" id="img" ></td>
		</tr>
		<tr>
			<td><input type="button" value="목록으로" onclick="location.href='${path1 }/ProductList.do?ccode=00'"></td>
			<td><input type="reset" value="초기화"><input type="submit" value="등록"></td>
		</tr>
		</table>
	</form>
	</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>