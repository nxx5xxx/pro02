<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta charset="UTF-8">
<title>${updatenotice.title} 수정</title>
<%@ include file="/common.jsp" %>
<style>
#filechange {display:none;}
#yeschange:checked ~ #filechange {display:block}

.table_wrap {display : block; text-align:center;  margin-bottom:5vw;}
table{width:65vw ; margin: 0 auto; font-size:2vmin;}
th {text-align: right; border-bottom:1px solid grey}
td {text-align: left; border-bottom:1px solid grey}
th,td {padding:1.1vh}
input{width:100%}
textarea {resize : none; width:100%}
.changeBox {display:none}
</style>

</head>
<body>
<%@ include file="/header.jsp" %>
<div class="wrap_bt">
	<div class="table_wrap">
		<form action="${path1 }/UpdateNotice.do" method="post" enctype="multipart/form-data">
		<h3>${updatenotice.title} 수정페이지</h3>
		 <table>
		 	<tr>
				<th>제목</th>
				<td colspan="3"><input type="text" id="title" name="title" value="${updatenotice.title}">	</td>
			</tr>
			<tr>
				<th>글 번호</th>
				<td>${updatenotice.idx }</td>
				<th>글쓴이</th>
				<td>${updatenotice.author }</td>
			</tr>
			<tr>
			<th style="vertical-align:top">내용</th>
			<td colspan="3"><div class="td_min"><textarea rows="10"  id="content" name="content" >${updatenotice.content}</textarea></div></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<c:if test="${fileName == 'null'}">
				<td colspan="3">
				<label for="file">첨부파일 없음</label>
				<input type="file" name="file" id="file">
				</td>
				</c:if>
				<c:if test="${fileName != 'null'}">
				<td colspan="3"><span>${fileName } </span>
					<span>
					<input type="radio" name="filechange" id="nochange" checked class="changeBox"><label for="nochange">교체안함</label>
					<input type="radio" name="filechange" id="yeschange" class="changeBox"><label for="yeschange">교체함</label>
					<input type="file" name="file" id="filechange" style="width:80%">
					<input type="hidden" name="file" id="hiddenfile" value="${fileName}">
					</span>
				</td>
				</c:if>
			</tr>
			<tr>
				<th>등록일</th>
				<td>
				<fmt:parseDate value="${updatenotice.resdate}" var="resdate" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate value="${resdate}" pattern="yyyy/MM/dd HH:mm" />
				</td>
				<th>조회수</th>
				<td>${updatenotice.cnt }</td>
			</tr>
			<tr>
			<td colspan="2">
			<!-- 히든처리해서 넘길것들  -->
			<input type="hidden" name="idx" value="${updatenotice.idx }">
			<input type="hidden" name="author" value="${updatenotice.author}">
			<input type="button" value="되돌아가기" onclick="location.href='${path1 }/NoticeList.do'">
			</td>
			<td>
			<input type="submit" value="수정">
			</td>
			<td>
			<input type="button" value="삭제" onclick="location.href='${path1 }/DeleteNotice.do?idx=${updatenotice.idx }'">
			</td>
			</tr>
		</table> 
		</form>
	</div>
</div>
<%@ include file="/footer.jsp" %>

</body>
</html>