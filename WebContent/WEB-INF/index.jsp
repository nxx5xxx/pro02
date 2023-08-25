<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<%
	String id = "";
	if(session.getAttribute("id")!=null){
		id = (String) session.getAttribute("id");
	}
%>
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
<div class="wrap_bt" style="text-align:center">
	<%-- <h2>제작자 : ${author }</h2> --%>
	<h1 style="text-align:center">${alert }</h1>
	<img src="${path1 }/img/logo/logo_on.png">
	
	<c:if test="${id=='admin' }">
	<h1>관리자로 로그인 중 입니다</h1>
	</c:if>
	<c:if test="${id!='admin' }">
	<h1>로그아웃 상태 입니다</h1>
	</c:if>

  <a href="${path1 }/NoticeList.do">공지사항</a>
  <h4>우측에 보이는 메뉴를 이용하여 둘러볼 수 있습니다</h4>
  <h4>첫번째 버튼 : 홈으로</h4>
  <h4>두번째 버튼 : 상품메뉴</h4>
  <h4>세번째 버튼 : 공지사항</h4>
  <h4>네번째 버튼 : 장바구니</h4>
  <h4>다섯번째 버튼 : 나의 구매내역 및 배송현황</h4>
  <h4>여섯번째 버튼 : 자주묻는 질문</h4>
  <h4>7,8버튼 : 최상단,최하단 으로</h4>
  <hr>
  <h4>이 프로젝트는 최대한 반응형에 맞춰 만들어 봤던 프로젝트 입니다</h4>
  <h4>자세한 작업 내역은  <a href="https://github.com/nxx5xxx/pro02/blob/master/readme.md">readme.md</a>를 참조해주세요</h4>
  
  
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>