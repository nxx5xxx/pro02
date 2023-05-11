<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
					<th>상품수량</th>
					<th>가격</th>
					<th>상품이미지</th>
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
					<td>${bas.bamount }</td>
					<td>${bas.price }</td>
					<td><img src="${bas.img }" style="width:15vh"></td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
			<c:if test="${!empty id }">
				<a href="${path1 }/GoInsNotice.do" style="font-size:18px ; line-height:50px">글쓰기</a>
			</c:if>
	</div>

</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
