<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="com.okbs.dto.*" %>
<%@ page import="java.util.*" %>
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
	String sql="select ~ from 테이블명 ";
	try{
		Class.forName(driver);
		try{
			conn=DriverManager.getConnection(url, user, password);
			try{
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, "?에 넣을값");
				rs=pstmt.executeQuery();
				
				//받아올 List<클래스명> 리스트명 =new ArrayList<>();
				while(rs.next()){
					// String 변수명  = rs.getString("컬럼명");
					//클래스명 member,board등등 = new 클래스명();
					//member,board등등.set컬럼명(변수명);
					//리스트명.add(member,board등등);
					}
				//이것까지해야 EL태그로 불러올수있음
				//pageContext.setAttribute("forEach로불러올이름",리스트명);
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
