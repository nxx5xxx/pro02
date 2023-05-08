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
	String sql="select * from user1 ";
	try{
		Class.forName(driver);
		try{
			conn=DriverManager.getConnection(url, user, password);
			try{
				pstmt=conn.prepareStatement(sql);
				rs=pstmt.executeQuery();
				ArrayList<User1> userList = new ArrayList<>();
				while(rs.next()){
					String id = rs.getString("id");
					String pw = rs.getString("pw");
					String name = rs.getString("name");
					String tel = rs.getString("tel");
					String addr = rs.getString("addr");
					String email = rs.getString("email");
					String regdate = rs.getString("regdate");
					int point = rs.getInt("point");
					
					User1 member = new User1();
					member.setId(id);
					member.setPw(pw);
					member.setName(name);
					member.setTel(tel);
					member.setAddress(addr);
					member.setEmail(email);
					member.setRegdate(regdate);
					member.setPoint(point);
					userList.add(member);
				}
				pageContext.setAttribute("userList", userList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<tr>
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>전화번호</th><th>주소</th><th>이메일</th><th>등록일</th><th>포인트</th>
		</tr>
	<c:forEach items="${userList }" var="user">
		<tr>
			<td>${user.id }</td><td>${user.pw }</td>
			<td>${user.name }</td><td>${user.tel }</td>
			<td>${user.address }</td><td>${user.email }</td>
			<td>${user.regdate }</td><td>${user.point }</td>
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
