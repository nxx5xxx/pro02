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
	<form action="${path1 }/GoDirectBuy.do?id=${id }" method="post">
		<table>
		<c:if test="${product.pdesc != null}">
			<tr>
				<th colspan="2">${product.pdesc}</th>
			</tr>
		</c:if>
				<tr>
			<th>상품가격</th>
			<td>${product.price}
			<input type="hidden" name="pcode" value="${product.pcode}"></td>
		</tr>
		<tr>
			<th>재고량</th>
			<c:if test="${product.pamount <=0}"><td>품절</td></c:if>
			<c:if test="${product.pamount >0}">
			<td>${product.pamount} 개
			<c:if test="${id != null && id != 'admin' }"><br><input type="text" name="amount" value="1" id="basamount" style="text-align:right" class="inw20"><br><input type="button" value="장바구니" onclick="insertbasket()" class="inw10"><input type="submit" value="바로구매" class="inw10"></c:if></td>
			</c:if>
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
				<img src="${product.img2 }" style="width:100vh; max-width:90%">
				</th>
			</tr>
		</c:if>
		<tr>
			<td colspan="2"><input type="button" value="뒤로가기" onclick="history.go(-1)"></td>
		</tr>
		</table>
	</form>
		<script>
		function insertbasket(){
			//var params = { cate1:$("#cate1").val() }
			$.ajax({
				url:"${path1 }/InsertBasket.do",
				type:"post",
				dataType:"json",
				data:{amount:$("#basamount").val() , pcode:${product.pcode}},
				encType:"UTF-8"
			});
			alert("장바구니에 물건을 담았습니다");
		}
		</script>
	</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>