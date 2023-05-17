<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>리뷰작성</title>
<%@ include file="/common.jsp" %>
<style>
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
.table_wrap>table {margin:0 auto; font-size:3.5vmin;}
table{width:85vw}
th {text-align: center; border-bottom:1px solid grey}
td {text-align: center; border-bottom:1px solid grey}
th,td {padding:1.1vh}
textarea {resize : none}
</style>
</head>
<body>
<div class="wrap_bt">
	<div class="table_wrap">
		<table>
			<tr>
				<th colspan="2">이용후기 작성</th>
			</tr>
			<tr>
				<th>
					만족도
				</th>
				<td>
				<input type="radio" name="manjok" id="man1" value="매우풀만족"><label for="man1">매우풀만족</label>
				<input type="radio" name="manjok" id="man2" value="풀만족"><label for="man2">풀만족</label>
				<input type="radio" name="manjok" id="man3" value="보통만족"><label for="man3">보통만족</label>
				<input type="radio" name="manjok" id="man4" value="만족"><label for="man4">만족</label>
				<input type="radio" name="manjok" id="man5"  value="매우만족" checked><label for="man5">매우만족</label>
				</td>
			</tr>
			<tr>
				<th>
					리뷰 
				</th>
				<td>
					<textarea rows="12" cols="55" id="content" placeholder="고객님의 솔직한 리뷰를 담가주세요~" required></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<input type="hidden" id="id" value="${id }">
				<input type="button" value="확인" onclick="insertReview(${onum })">
				<input type="button" value="취소" onclick="cancel()">
				</td>

				

			</tr>
		
		</table>
		<script>
		function insertReview(onum){
			//데이터 잘 받아왔나 확인
			//alert(onum);
			//alert($("[name=manjok]:checked").val());
			//alert($("#content").val());
 			$.ajax({
				url:"${path1}/InsertReview.do",
				dataType:"json",
				encType:"UTF-8",
				type:"post",
				data:{onum:onum , b_score:$("[name=manjok]:checked").val() , b_review : $("#content").val() , id : $("#id").val() }
			}) 
			alert("리뷰를 등록했습니다");
 			opener.parent.location.reload();
 			window.close();
		}
		
		function cancel(){
			window.close();
		}
		</script>
	</div>
</div>

</body>
</html>