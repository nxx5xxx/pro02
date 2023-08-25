<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />

<html>
<head>
<meta charset="UTF-8">
<title>자주하는질문</title>
<%@ include file="/common.jsp" %>
<style>
.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
.table_wrap>table {margin:0 auto; font-size:2vmin;}
.lstwrap{width:65vw;margin:0 auto;text-align:left}
.noti {font-weight:bold ; font-size:5vmin}
.fa { display:none; }
.fq { display:none; }
.fq:checked + .fa {display:block;}
.lstwrap li { padding-top:12px; padding-bottom:12px; border-top:1px solid #f0f0f0; }
.lstwrap li label { cursor:pointer; }
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<div class="table_wrap">
	<span class="noti">자주묻는질문</span>
			<ul class="lstwrap">
				<c:forEach var="faq" items="${faqList }" varStatus="cnt">
				<li>
					<span>${cnt.count }</span>
					<label for="fq${cnt.count }">
						<span>${faq.title }</span>
					</label>
					<input type="checkbox" id="fq${cnt.count }" class="fq">
					<c:if test="${id=='admin' }">
					<a href="${path1 }/GoUpdateFaq.do?fno=${faq.fno }" class="fa" title="관리자로 로그인시 수정가능">${faq.content }</a>
					</c:if>
					<c:if test="${id!='admin' }">
					<span class="fa">${faq.content }</span>
					</c:if>
				</li>
				</c:forEach>
				<c:if test="${empty faqList }">
				<li>
					<p>FAQ가 존재하지 않습니다.</p>
				</li>
				</c:if>	
			</ul>
			<c:if test="${id=='admin' }">
			<input type="button" value="FAQ등록" onclick="location.href='${path1}/GoInsertFaq.do'">
			</c:if>
		</div>
</div>
<%@ include file="/footer.jsp" %>
</body>
</html>
