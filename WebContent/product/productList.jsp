<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />

<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<%@ include file="/common.jsp" %>
<style>
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
.table_wrap>table {margin:0 auto; font-size:2vmin;}
table{width:65vw}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: right; border-bottom:1px solid grey}
th,td {padding:1.1vh}
.noti {font-weight:bold ; font-size:5vmin}
.category {float:left ;}
.category > li {float:left ; margin-left : 30px}
.cate_wrap { width:500px; margin:0 auto}
.top {text-align : center}
img {width:15vh}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<div class="top"><span class="noti">${cgroup }</span></div>
	<div class="cate_wrap">
	<ul class="category">
		<li><a href="${path1}/ProductList.do?ccode=${ccode}">ALL > </a></li>
	<c:forEach items="${cateList }" var="cate">
		<li><a href="${path1}/ProductList.do?ccode=${cate.ccode}">${cate.cname }</a></li>
	</c:forEach>
	</ul>
	</div>
	<div class="table_wrap">
		<table>
			<thead>
					<tr>
						<th>상품번호</th>
						<th>상품이름</th>
						<th>가격</th>
						<th>상세설명</th>
						<th>재고</th>
						<th>카테고리번호</th>
						<th>상품사진</th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${proList }" var="pro">
					<tr>
						<td>${pro.pcode }</td>
						<td>${pro.pname}</td>
						<td>${pro.price }</td>
						<td>${pro.pdesc }</td>
						<td>${pro.pamount }</td>
						<td>${pro.ccode }</td>
						<td><img src='${path1 }/${pro.img}' alt="${pro.pname }"/></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div>
			<c:if test="${id == 'admin'}">
			|<a href="${path1 }/product/insertProduct.jsp" style="font-size:24px">글 쓰기</a>
			</c:if>
		</div>
	</div>
	</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
