<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.okbs.dto.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "pro02";
	String password = "1234";
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	String sql="select * from review ";
	try{
		Class.forName(driver);
		try{
			conn=DriverManager.getConnection(url, user, password);
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				ArrayList<Review> rvList = new ArrayList<>();
				while(rs.next()){
					String bno = rs.getString("bno");
					String id = rs.getString("id");
					String onum = rs.getString("onum");
					String b_date = rs.getString("b_date");
					String b_review = rs.getString("b_review");
					int b_score = rs.getInt("b_score");


					Review review = new Review();
					review.setBno(bno);
					review.setId(id);
					review.setOnum(onum);
					review.setB_date(b_date);
					review.setB_review(b_review);
					review.setB_score(b_score);

					rvList.add(review);
				}
				pageContext.setAttribute("rvList", rvList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체게시글</title>
</head>
<body>
	<table>
	<tr>
	<th>글번호</th><th>아이디</th><th>주문번호</th><th>작성일</th><th>이용후기</th><th>별점</th>
	</tr>
	<c:forEach var="rv" items="${rvList }">
		<tr>
			<td>${rv.bno }</td>
			<td>${rv.id}</td>
			<td>${rv.onum}</td>
			<td>${rv.b_date}</td>
			<td>${rv.b_review}</td>
			<td>${rv.b_score}</td>
		</tr>
	</c:forEach>
	</table>
</body>
</html>
<%
				rs.close();
				pstmt.close();
				conn.close();
				
			}catch(Exception e){
				System.out.println("SQL연결 에러");
			}
		}catch(Exception e){
			System.out.println("DB연결 에러");
		}
	}catch(Exception e){
		System.out.println("드라이버 로딩 에러");
	}
	
%>