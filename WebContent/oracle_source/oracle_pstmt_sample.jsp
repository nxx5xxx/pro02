<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "pro02";
	String password = "1234";
	Connection conn;
	PreparedStatement pstmt;
	String sql="update 테이블명 set 바꿀컬럼=?  where 프라이머리키잡는게 좋음";
	int sw=0;
	
	
	try{
		Class.forName(driver);
		try{
			conn=DriverManager.getConnection(url, user, password);
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "?에 넣을값");
				sw=pstmt.executeUpdate();
				if(sw>0){
					System.out.println("실행 완료");
					response.sendRedirect("주소");
				}else{
					System.out.println("실행오류");
					response.sendRedirect("주소");
				}
				
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
