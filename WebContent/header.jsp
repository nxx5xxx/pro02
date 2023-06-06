<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- c:set var="path" value="${realPath }"  --%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<%
	String sessionId = (String)session.getAttribute("id");
/* 	if(sessionId!=null){
		System.out.println(sessionId+"님이 로그인하였습니다");
	} */
	String realPath = (String)application.getAttribute("realPath");
%>
<header>
	<nav>
		<div class="home">
            	<a href="${path }">홈으로</a>
		</div>
		<div class="hd_wrap">
			<!-- if처리할것 -->
			<!-- session로그인 전 일때 -->
			<ul>
				<c:choose>
				<c:when test="${id=='admin' }">
                    <li>관리자모드</li>
                    <li><a href="${path }/GoBasket.do?id=admin">고객장바구니</a></li>
					<li><a href="${path }/GoBuyList.do?id=${id}">고객 구매내역</a></li>
                    <li><a href="${path }/Logout.do">관리자모드나가기</a></li>
                    
				</c:when>
				<c:when test="${!empty id }">                    
					<li><a href="${path }/Logout.do">로그아웃</a></li>
                    <li><a href="${path }/MyPageUser.do?id=${id}">마이페이지</a></li>
                    <li><a href="${path }/GoBasket.do?id=${id}">장바구니</a></li>
                    <li><a href="${path }/GoBuyList.do?id=${id}">구매내역</a></li>
				</c:when>
				<c:otherwise>
                    <li><a href="${path }/GoLogin.do">로그인</a></li>
                    <li><a href="${path }/GotermsUser.do">회원가입</a></li>
				</c:otherwise>
				</c:choose>
		</ul>
	</div>
</nav>
  <hr style="clear:both">
    <div class="fix_menubar">
   <!--  <input type="checkbox" id="hidemenu" name="hidemenu" class="hide_chkbox" style="display:none"><label for="hidemenu" class="hideitem" title="메뉴접기">메뉴접기</label> -->
        <ul>
            <li><a href="${path }" class="item0" title="홈으로">홈</a></li>
            <li><input type="checkbox" id="category" name="category" class="cate_chkbox" style="display:none"><label for="category" class="item1" title="상품목록">스위치메뉴</label>
            	<div class="sub">
	            	<ul>
	            		<li>
	            		<a href="${path }/ProductList.do?ccode=00">모든제품</a>
	            		</li>
	            		<li>
	            		<a href="${path }/ProductList.do?ccode=01">닌텐도스위치</a>
	            		</li>
	            		<li>
	            		<a href="${path }/ProductList.do?ccode=02">플레이스테이션</a>
	           			</li>
	           			<li>
	           			<a href="${path }/ProductList.do?ccode=03">엑스박스</a>
	           			</li>
	           			<li>
	           			<a href="${path }/ProductList.do?ccode=04">기타</a>
	           			</li>
	            	</ul>
            	</div>
            </li>
            <li><a href="${path }/NoticeList.do" class="item2" title="게시판">게시판</a></li>
            <li><a href="${path }/GoBasket.do?id=${id}" class="item3" title="장바구니">장바구니</a></li>
            <li><a href="${path }/GoBuyList.do?id=${id}" class="item4" title="주문내역">주문내역</a></li>
            <li><a href="" class="item5" title="QNA">QNA</a></li>
            <li><a href="${path }/GoFaqList.do" class="item6" title="자주묻는질문">FAQ</a></li>
            <li><a href="#" class="item7" title="페이지상단">페이지상단</a></li>
            <li><a href="#bottom" class="item8" title="페이지하단">페이지하단</a></li>
        </ul>
    </div>
</header>