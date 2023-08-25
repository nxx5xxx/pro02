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
				

	<c:if test="${id=='admin' }"><span class="noti">고객 구매내역</span></c:if>		
	<c:if test="${id!='admin' }"><span class="noti">나의 구매내역</span></c:if>
		<table <c:if test="${id=='admin' }">style="width:85vw; font-size:1.4vh"</c:if>>
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
						<c:if test="${id=='admin' }">
							<td><input type="text" name="ename${cnt.count }" id="ename${cnt.count }" value="${buy.ename }"></td>
							<td><input type="text" name="ecode${cnt.count }" id="ecode${cnt.count }" value="${buy.ecode }"></td>
							<td><select name="status${cnt.count }" id="status${cnt.count }" >
							<option value="${buy.status }">${buy.status }</option>
							<option value="">---------------</option>
							<option value="배송전">배송전</option>
							<option value="배송중">배송중</option>
							<option value="배송완료">배송완료</option>
							<option value="구매확정">구매확정</option>
							</select>
							<input type="button" name="changepost" id="changepost" onclick="changepost(${cnt.count },${buy.onum })" value="배송변경">
							</td>
						</c:if>
							<c:if test="${id!='admin' }">
							<td>${buy.ename }</td>
							<td>${buy.ecode }</td>
							<td><span id="buyresult">${buy.status }</span>
							<c:if test="${buy.status=='배송완료' || buy.status=='배송중' }">
							<input type="button" name="buysuccess" id="buysuccess" onclick="buysuccess(${cnt.count },${buy.onum })" value="구매확정">
							<input type="hidden" name="buyok" id="buyok" value="구매확정">
							</c:if>
							<c:if test="${buy.status=='구매확정' }">
							<input type="button" name="changepost" id="changepost" onclick="goreview(${buy.onum })" value="후기작성">
							</c:if>
							</td>
						</c:if>
					<td>${buy.odate }</td>
				</tr>
			</c:forEach>
		</tbody>
		</table>
		<script>
		//관리자 배송상태 변경
		function changepost(numb,onum){
			
			//alert(onum+"주문번호 배송상태 변경완료");
			//alert($("#status"+numb).val());
			if($("#status"+numb).val()!=""){
			alert(numb+"번째 줄 배송상태 변경완료");
				$.ajax({
					url:"${path1 }/ChangePostStatus.do",
					dataType:"json",
					type:"post",
					data:{ename:$("#ename"+numb).val() , ecode:$("#ecode"+numb).val() , onum:onum, status:$("#status"+numb).val()},
					encType:"UTF-8",
					success:function(result){
						console.log(result);
						//var 
					}
				});
				location.reload(); //새로고침 명령어
			}else{
				alert(numb+"번째 줄 배송상태를 확인해주세요");
			}
		}
		///////////////////////////////
		//구매확정
		function buysuccess(x,onum){
			if(confirm("정말로 구매 확정 하시겠습니까?")==true){
				alert("구매확정하였습니다");
				
				$.ajax({
					url:"${path1 }/ChangePostStatus.do",
					dataType:"json",
					type:"post",
					encType:"UTF-8",
					data:{onum:onum , status:$("#buyok").val()}
				})
				
			}else{
				alert("취소하였습니다");
			}
		}
		/////////////////////////////
		//리뷰작성
		function goreview(onum){
			var popX = (window.screen.width / 2) - (500 / 2);
			var popY= (window.screen.height / 2) - (360 / 2);
			var onum = onum
			window.open("${path1 }/GoinsertReview.do?onum="+onum,"리뷰쓰기","status=no,toolbar=no,scrollbars=no, width=550, height=360,left="+ popX + ", top="+ popY);
		}
		
		</script>
	</div>


</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
