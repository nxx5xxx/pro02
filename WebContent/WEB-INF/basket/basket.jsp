<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />

<html>
<head>
<meta charset="UTF-8">
<title>${id }님의 장바구니</title>
<%@ include file="/common.jsp" %>
<style>
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
.table_wrap>table {margin:0 auto; font-size:2vmin;}
table{width:65vw}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: right; border-bottom:1px solid grey}
th,td {padding:1.1vh}
.noti {font-weight:bold ; font-size:5vmin}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<div class="table_wrap">
	<span class="noti">나의 장바구니</span>
		<table>
		<thead>
				<tr>
					<th>순번</th>
				<c:if test="${id=='admin' }">
					<th>고객ID</th>
					<th>장바구니번호</th>
					<th>상품코드</th>
				</c:if>
					<th>상품명</th>
					<th>담은수량</th>
					<th>재고수량</th>
					<th>가격</th>
					<th style="padding-right:5vw">상품이미지</th>
				</tr>
		</thead>
		<tbody>
			<c:forEach items="${basList }" var="bas" varStatus="cnt">
				<tr>
					<td>${cnt.count }</td>
					<c:if test="${id=='admin' }">
						<td>${bas.id }</td>
						<td>${bas.bnum }</td>
						<td>${bas.pcode }</td>
					</c:if>
					<td><a href="${path1 }/GoProductDetail.do?pcode=${bas.pcode }" title="수량 : ${bas.bamount }">${bas.pname }</a></td>
					<td><input type="number" id="bskamount${cnt.count }" value="${bas.bamount }" class="inw20 nob" min="1" max="${bas.pamount }" ><label for="bskamount">개</label>
					<input type="button" onclick="changeBasket(${bas.pamount },${bas.bnum },${cnt.count })" value="변경">
					</td>
					<td>${bas.pamount }개</td>
					<td>${bas.price }</td>
					<td><img src="${bas.img }" style="width:15vh;">
					<input type="button" value="제거" onclick="location.href='${path1 }/DeleteBasket.do?bnum=${bas.bnum }'">
					</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<a href="${path1 }/GoBuy.do?id=${id}" style="font-size:18px ; line-height:50px">구매하기</a>
	</div>
	<script>
	function changeBasket(x,y,cnt){//여기서 x는 위에서 받은 재고갯수임
		/* alert("장바구니를 변경하였습니다");
		alert($("#bskamount").val());
		alert("x : "+x);
		alert("y : "+y); */
 		if($("#bskamount"+cnt).val()>0 && $("#bskamount"+cnt).val() <= x){
 			//alert("장바구니를 변경하였습니다2");
 			$.ajax({
				url:"${path1 }/UpdateBasket.do",
				type:"post",
				dataType:"json",
				data:{bskamount:$("#bskamount"+cnt).val() , bnum:y},
				encType:"UTF-8"
			});
 			location.reload();
		}else{
			alert("장바구니 수량을 다시 확인해주세요");
		}
	}
	</script>

</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
