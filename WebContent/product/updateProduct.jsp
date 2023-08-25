<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }"/>

<html>
<head>
<meta charset="UTF-8">
<title>${product.pname} 상품수정</title>
<%@ include file="/common.jsp" %>
<style>
textarea {resize : none; width:100%}
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
table{width:65vw ; margin: 0 auto; font-size:2vmin;}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: right; border-bottom:1px solid grey}
th,td {padding:1.1vh}
input{width:100%}
#imgchange {display:none;}
#yeschange:checked ~ #imgchange {display:block}
#img2change {display:none;}
#yeschange2:checked ~ #img2change {display:block}
.changeBox {display:none}
.fl {float:left ; clear:left;line-height:3}
</style>

</head>

<%@ include file="/header.jsp" %>

<body>
<div class="wrap_bt">
	<div class="table_wrap">
	<h1>${product.pname} 상품수정</h1>
	<form action="${path1 }/ProductUpdate.do" method="post" enctype="multipart/form-data">
		<table>
		<tr>	
			<th><label for="pcode">상품코드</label></th>
			<td><input type="text" name="pcode" id="pcode" value=${product.pcode } readonly></td>
		</tr>
		<tr>
			<th><label for="pname">상품이름</label></th>
			<td><input type="text" name="pname" id="pname" value="${product.pname}" required></td>
		</tr>
		<tr>
			<th><label for="pdesc">내용</label></th>
			<td><textarea rows="10" name="pdesc" id="pdesc" >${product.pdesc}</textarea></td>
		</tr>
		<tr>
			<c:if test="${product.img2 == 'img/proimg/null'}">
				<th><label for="img">본문 이미지 없음</label></th>
				<td><input type="file" name="img2" id="img2">
				</td>
			</c:if>
				
			<c:if test="${product.img2 != 'img/proimg/null'}">
				<th><label for="img2">본문 이미지</label></th>
				<td style="text-align:left;">
					<c:set var="img2length" value="${fn:length(product.img2) }" />
					<c:set var="img2Name" value="${fn:substring(product.img2,11,img2length) }" />
					${img2Name}<br>
					<input type="radio" name="filechange2" id="nochange2" checked class="changeBox"><label for="nochange2" >교체안함</label>
					<input type="radio" name="filechange2" id="yeschange2" class="changeBox"><label for="yeschange2" >교체함</label>
					<input type="file" name="img2" id="img2change" style="width:80%;clear:both">
					<input type="hidden" name="img2" id="hiddenimg2" value="${product.img2}">
				</td>
			</c:if>
		</tr>
		<tr>
			<th><label for="price">상품가격</label></th>
			<td><input type="text" name="price" id="price" value="${product.price}" pattern="^[0-9]+$" required></td>
		</tr>
		<tr>
			<th><label for="pamount">재고량</label></th>
			<td><input type="text" name="pamount" id="pamount" value="${product.pamount}" required></td>
		</tr>
		<tr>
			<th><label for="ccode">카테고리코드</label></th>
			<td><input type="text" name="ccode" id="ccode" value="${product.ccode}" placeholder="01닌텐도02플스03엑스박스/00기타01액션02총03RPG/04기타01조이스틱02주변기기03완구" required></td>
		</tr>
		<tr>
				<c:if test="${product.img == 'img/proimg/null'}">
				<th><label for="img">썸네일 이미지 없음</label></th>
				<td><input type="file" name="img" id="img">
				</td></c:if>
				
				<c:if test="${product.img != 'img/proimg/null'}">
				<th><label for="img">썸네일 이미지</label></th>
				<td style="text-align:left;">
					<input type="radio" name="filechange" id="nochange" checked class="changeBox"><label for="nochange" class="fl">교체안함</label>
					<input type="radio" name="filechange" id="yeschange" class="changeBox"><label for="yeschange" class="fl">교체함</label>
					<img src="${product.img}" style="width:15vh">
					<input type="file" name="img" id="imgchange" style="width:80%;clear:both">
					<input type="hidden" name="img" id="hiddenimg" value="${product.img}">
				</td>
				</c:if>
		</tr>
		<tr>
			<td><input type="button" value="목록으로" onclick="location.href='${path1 }/ProductList.do?ccode=00'"></td>
			<td><input type="reset" value="초기화"><input type="submit" value="수정"></td>
		</tr>
		</table>
	</form>
	</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>