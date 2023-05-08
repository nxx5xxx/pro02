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

<title>메인페이지</title>
<%@ include file="/common.jsp" %>
<style>

</style>

</head>
<body>
<%@ include file="/header.jsp" %>
	<h2>제작자 : ${author }</h2>
	<a href="${path1 }/admin/memberList.jsp">전체고객목록보기</a>
	<a href="${path1 }/board/board.jsp">전체게시글보기</a>
	

  <a href="${path1 }/NoticeList.do">공지사항</a>
<hr>
	<form action="${path1 }/UserInsert.do" method="post" onsubmit="return insertCheck(this)">
		<table>
			<tr>
				<th><label for="id">아이디</label></th>
				<td><input type="text" name="id" id="id" placeholder="아이디" required><input type="button" value="중복체크" style="width:30%" onclick="idCheck()">
				<input type="hidden" name="idck" id="idck" value="noIdCheck">
				<p id="msg"></p>
				</td>
			</tr>
			<tr>
				<th><label for="pw">비밀번호</label></th>
				<td><input type="text" name="pw" id="pw" placeholder="비밀번호" required></td>
			</tr>
			<tr>
				<th><label for="pw">비밀번호 확인</label></th>
				<td><input type="text" name="pw2" id="pw2" placeholder="비밀번호 확인" required></td>
			</tr>
			<tr>
				<th><label for="name">이름</label></th>
				<td><input type="text" name="name" id="name" placeholder="이름" required></td>
			</tr>
			<tr>
				<th><label for="tel">전화번호</label></th>
				<td><input type="text" name="tel" id="tel" placeholder="전화번호" required></td>
			</tr>
			<tr>
				<th>주소</th>
				<td>

				<input type="text" name="postcode" id="postcode" placeholder="우편번호" /><br>
				<input type="text" name="addr1" id="addr1" placeholder="기본주소" /><br>
				<input type="text" name="addr2" id="addr2" placeholder="상세주소" /><br>
				<input type="button" name="addr_btn" id="addr_btn" onclick="findAddr()" value="주소검색">
				</td>
			</tr>
			<tr>
				<th><label for="email">이메일</label></th>
				<td><input type="text" name="email" id="email" placeholder="이메일"></td>
			</tr>
			<tr>
				<td><input type="reset" value="취소" style="width:100%"></td>
				<td><input type="submit" value="가입" style="width:70%"></td>
			</tr>
		</table>
	</form>
	<script>
	function idCheck(){
		if($("#id").val()==""){
			alert("아이디를 입력하시기 바랍니다.");
			$("#id").focus();
			return;
		}
		var params = { id:$("#id").val()}
		$.ajax({
			//url:"보낼주소"
			url:"${path1 }/UserCheck.do",
			//type:"전송방식" (안쓰면 get 겟으로할경우 IdCheck.do?=id이런식으로달아줘야함)
			type:"post",
			dataType:"json",
			data:params,
			success:function(result){
				//console.log(result); --F12눌럿을때 거기있는 콘솔창에 출력해보는것
				console.log(result);
				var idChk = result.result;
				if(idChk==false){
					$("#idck").val("no");
					$("#msg").html("다른 아이디를 입력하세요")
				}else if(idChk==true){
					$("#idck").val("yes");
					$("#msg").html("사용 가능한 아이디")
				}else{
					$("#msg").html("다시 시도 바랍니다")
				}
			}
			
			//처리가 성공하면 success로 데이터가 온다
		})
		
		
	}
	function insertCheck(x){
		if(x.pw.value!=x.pw2.value){
			alert("비밀번호가 일치하지 않습니다")
			x.pw.focus();
			return false;
		}
		if(x.idck.value!="yes"){
			alert("아이디 중복체크를 하지 않으셨습니다")
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