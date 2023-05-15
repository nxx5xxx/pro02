<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />

<html>
<head>
<meta charset="UTF-8">
<title>${id }님의 구매내역</title>
<%@ include file="/common.jsp" %>
<style>
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
.table_wrap>table {margin:0 auto; font-size:2vmin;}
table{width:85vw}
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
	<span class="noti">나의 구매내역</span>
		<table <c:if test="${id=='admin' }">style="width:90vw; font-size:1.2vh"</c:if>>
		<thead>
				<tr>
					<th>순번</th>
				<c:if test="${id=='admin' }">
					<th>주문번호</th>
					<th>고객ID</th>
					<th>상품코드</th>
				</c:if>
					<th>상품이름</th>
					<th>전화번호</th>
					<th>주소</th>
					<th>주문수량</th>
					<th>가격</th>
					<th>택배사</th>
					<th>송장번호</th>
					<th>배송상태</th>
					<th>구입일</th>
				</tr>
		</thead>
		<tbody>
			<c:forEach items="${buyList }" var="buy" varStatus="cnt">
				<tr>
					<td>${cnt.count }</td>
					<c:if test="${id=='admin' }">
						<td>${buy.onum }</td>
						<td>${buy.id }</td>
						<td>${buy.pcode }</td>
					</c:if>
					<td><a href="${path1 }/GoProductDetail.do?pcode=${buy.pcode }" >${buy.pname }</a></td>
					<td>${buy.tel }</td>
					<td>${buy.addr }</td>
					<td>${buy.amount }개</td>
					<td>${buy.price }</td>
					<td>${buy.ename }</td>
					<td>${buy.ecode }</td>
					<td>${buy.status }</td>
					<td>${buy.odate }</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<a href="${path1 }/GoBuy.do?id=${id}" style="font-size:18px ; line-height:50px">구매하기</a>
	</div>


</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
