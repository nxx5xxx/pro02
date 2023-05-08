<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8" );
%>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">

<title>마이페이지 수정페이지</title>
<%@ include file="/common.jsp" %>
<style>
.yesdel {display:none;}
.delon:checked ~ .yesdel {display:block;}

</style>

</head>
<body>
<%@ include file="/header.jsp" %>
	<h2>제작자 : ${author }</h2>
	<a href="${path1 }/admin/memberList.jsp">전체고객목록보기</a>
	<a href="${path1 }/board/board.jsp">전체게시글보기</a>
	

  <a href="${path1 }/NoticeList.do">공지사항</a>
<hr>
<c:if test="${!empty alert }">
	<p>${alert }</p>
</c:if>

	<form action="${path1 }/MyPageModify.do" method="post" onsubmit="return insertCheck(this)">
		<table class="tb">
			<tr>
				<th><label for="id">아이디</label></th>
				<td><input type="text" name="id" id="id" value="${id}" readonly></td>
			</tr>
			<tr>
				<th><label for="pw">비밀번호</label></th>
				<td><input type="password" name="pw" id="pw" value="${user.pw }" required>
				</td>
			</tr>
			<tr class="okpw">
				<th><label for="pw">비밀번호 확인</label></th>
				<td>
				<input type="password" name="pw2" id="pw2" value="${user.pw }" required>
				</td>
			</tr>
			<tr>
				<th><label for="name">이름</label></th>
				<td><input type="text" name="name" id="name" value="${user.name }" required></td>
			</tr>
			<tr>
				<th><label for="tel">전화번호</label></th>
				<td><input type="text" name="tel" id="tel" value="${user.tel }" required></td>
			</tr>
			<tr>
				<th><label for="addr">주소</label></th>
				<td>
				<h6>${user.address }</h6><br>
				<input type="text" name="postcode" id="postcode" value="${postCode }" /><br>
				<input type="text" name="addr1" id="addr1" value="${addr1 }" /><br>
				<input type="text" name="addr2" id="addr2" value="${addr2 }" /><br>
				<input type="button" name="addr_btn" id="addr_btn" onclick="findAddr()" value="주소검색">
				</td>
			</tr>
			<tr>
				<th><label for="email">이메일</label></th>
				<td><input type="text" name="email" id="email" value="${user.email }"></td>
			</tr>
			<tr>
				<td><input type="reset" value="초기화" style="width:100%"></td>
				<td><input type="submit" value="수정" style="width:50%"><input type="button" value="뒤로가기" onclick="location.href='${path1}/MyPageUser.do?id=${id }'" style="width:50%">
				</td>
			</tr>
			<tr>
				<th colspan="3" style="text-align:center">
				<label for="delete">삭제</label>
				<input type="checkbox" id="delete"  class="delon">
					<div class="yesdel">
					<input type="button" value="삭제확인" style="width:33%" onclick="location.href='${path1 }/UserDelete.do?id=${id}'" >
					</div>
				</th>
			</tr>
		</table>
	</form>
	<script>
	function insertCheck(x){
		if(x.pw.value!=x.pw2.value){
			alert("비밀번호가 서로 일치하지 않습니다")
			x.pw.focus();
			return false;
		}
	}
	function findAddr(){
		new daum.Postcode({
			oncomplete: function(data) {
				console.log(data);
				var roadAddr = data.roadAddress;
				var jibunAddr = data.jibunAddress;
				document.getElementById("postcode").value = data.zonecode;
				if(roadAddr !== '') {
					document.getElementById("addr1").value = roadAddr;				
				} else if(jibunAddr !== ''){
					document.getElementById("addr1").value = jibunAddr;
				}
				document.getElementById("addr2").focus();
			}
		}).open();		
	}
	</script>
	<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</body>
</html>