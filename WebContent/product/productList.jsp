<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
.cate_wrap { width:600px; margin:0 auto}
.top {text-align : center}
img {width:15vh}
.plsin,.mnsin {display:none}
.chkpls:checked ~ .plsin{display:block ;width:30%; float:right}
.chkmns:checked ~ .mnsin{display:block ;width:30%; float:right}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<div class="top"><span class="noti">${cgroup }</span></div>
	<div class="cate_wrap">
	<ul class="category">
		<li><a href="${path1}/ProductList.do?ccode=00">모든제품&nbsp; ></a></li>
		<c:if test="${ccode!=00 }">
		<li><a href="${path1}/ProductList.do?ccode=${ccode}">${cgroup }&nbsp; ></a></li></c:if>
	<c:if test="${ccode==00 }">
		<li><a href="${path1}/ProductList.do?ccode=01">닌텐도스위치</a></li>
		<li><a href="${path1}/ProductList.do?ccode=02">플레이스테이션</a></li>
		<li><a href="${path1}/ProductList.do?ccode=03">엑스박스</a></li>
		<li><a href="${path1}/ProductList.do?ccode=04">그외</a></li>
	</c:if>
	<c:if test="${ccode!=00 }">
	<c:forEach items="${cateList }" var="cate">
		<li><a href="${path1}/ProductList.do?ccode=${cate.ccode}">${cate.cname }</a></li>
	</c:forEach>
	</c:if>
	</ul>
	</div>
	<div class="table_wrap">
		<table>
			<thead>
					<tr>
						<th style="width:8%">상품번호</th>
						<th>상품이름</th>
						<th>가격</th>
						<th>상세설명</th>
						<th style="width:5%">재고</th>
						<th style="width:12%">카테고리번호</th>
						<th colspan="2" style="text-align:center">상품사진</th>
					</tr>
			</thead>
			<tbody>
				<c:forEach items="${proList }" var="pro" varStatus="cnt">
					<tr>
						<td>${pro.pcode }</td>
						<td><a href="${path1 }/GoProductDetail.do?pcode=${pro.pcode }" title="수량 : ${pro.pamount }">${pro.pname}</a>
						<c:if test="${id =='admin' }"> 
						<br><label for="pluschk${cnt.count }">입고</label><input type="checkbox" id="pluschk${cnt.count }" class="chkpls">
						<input type="text" id="plusintput${cnt.count }" class="plsin" placeholder="개수입력" >
						<input type="button" value="입고" class="plsin" onclick="plusproduct(${cnt.count },${pro.pcode })"><br>
						
							<c:if test="${pro.pamount > 0 }">
								<label for="minuschk${cnt.count }">출고</label><input type="checkbox" id="minuschk${cnt.count }" class="chkmns">
								<input type="text" id="minusinput${cnt.count }" class="mnsin" placeholder="개수입력">
								<input type="button" value="출고" class="mnsin" onclick="minusproduct(${cnt.count },${pro.pcode })">
							</c:if>
						</c:if>
						</td>
						<td>${pro.price }</td>
						<td>${pro.pdesc }</td>
						<c:if test="${pro.pamount > 0 }">
						<td>${pro.pamount }</td>
						</c:if>
						<c:if test="${pro.pamount <= 0 }">
						<td>품절</td>
						</c:if>
						<td>${pro.ccode }</td>
						<td><img src='${path1 }/${pro.img}' alt="${pro.pname }"/></td>
						<c:if test="${id == 'admin'}">
						<td><input type="button" value="수정" onclick="location.href='${path1 }/GoProductUpdate.do?pcode=${pro.pcode }'"><br><br>
							<input type="button" value="삭제" onclick="location.href='${path1 }/ProductDelete.do?pcode=${pro.pcode }'"><td>
						</c:if>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<script>
		function plusproduct(x,y){
			$.ajax({
				url:"${path1 }/PlusAmount.do",
				type:"post",
				dataType:"json",
				data:{pluschk:$("#plusintput"+x).val() , pcode:y},
				encType:"UTF-8",
				success:function(result){
					console.log(result);
					var pamount = result.pamount;
					$("#plusintput"+x).val(amount);
				}
			});
			location.reload();
		}
		function minusproduct(x,y){
			$.ajax({
				url:"${path1 }/MinusAmount.do",
				type:"post",
				dataType:"json",
				data:{mnschk:$("#minusinput"+x).val() , pcode:y},
				encType:"UTF-8",
				success:function(result){
					console.log(result);
					var pamount = result.pamount;
					$("#minusintput"+x).val(amount);
				}
			});
			location.reload();
		}
		</script>
		<div>
			<c:if test="${id == 'admin'}">
			|<a href="${path1 }/GoProductInsert.do" style="font-size:24px">글 쓰기</a>
			</c:if>
		</div>
	</div>
	</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
