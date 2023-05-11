<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }"/>

<html>
<head>
<meta charset="UTF-8">
<title>${product.pname}</title>
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
	<h1>${product.pname}</h1>
		<table>
		<c:if test="${product.pdesc != null}">
			<tr>
				<th colspan="2">${product.pdesc}</th>
			</tr>
		</c:if>
				<tr>
			<th>상품가격</th>
			<td>${product.price}</td>
		</tr>
		<tr>
			<th>재고량</th>
			<c:if test="${product.pamount <=0}"><td>품절</td></c:if>
			<c:if test="${product.pamount >0}"><td>${product.pamount}</td></c:if>
		</tr>
		<c:if test="${product.img != 'img/proimg/null'}">
			<tr>
				<th>커버</th>
				<td style="text-align:right;">
					<img src="${product.img}" style="width:15vh">
				</td>
			</tr>
		</c:if>
		<c:if test="${product.img2 != 'img/proimg/null'}">
			<tr>
				<th colspan="2" style="text-align:center">
				<img src="${product.img2 }" style="width:100vh">
				</th>
			</tr>
		</c:if>
		<tr>
			<td colspan="2"><input type="button" value="뒤로가기" onclick="history.go(-1)"></td>
		</tr>
		</table>
	</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>