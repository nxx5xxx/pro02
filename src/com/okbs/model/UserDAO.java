package com.okbs.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.crypto.util.AES256;
import com.okbs.dto.User1;

public class UserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public User1 getLoginInfo(String id){
		User1 user = new User1();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.USER1_ONE_SELECT);
			pstmt.setString(1, id);
			//암호화해서 비교하려면 USER1_LOGIN에 2,pw를 빼주고 re.getString으로 받은값을 복호화해서 비교해준다
			rs = pstmt.executeQuery();
			if(rs.next()){
				userPoint(id);
				user.setId(rs.getString("id"));
				user.setPw(rs.getString("pw"));
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다"+e);
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다"+e);
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다."+e);
		}

		Oracle11.close(rs, pstmt, conn);
		return user;
	}
	//
	public void userPoint(String id){
		try{
			conn=Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.USER1_UPDATE_POINT);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다"+e);
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다"+e);
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다."+e);
		}
		Oracle11.close(pstmt, conn);
	}
	//
	public void insertUser(String id,String pw, String name,String tel, String addr,String email){
		int sw =0 ;
		try {
			conn = Oracle11.getConnection();
			pstmt=conn.prepareStatement(Oracle11.USER1_INSERT);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			if(addr!=""){
				pstmt.setString(5, addr);
			}else{
				pstmt.setString(5, "-");
			}
			if(email!=""){
				pstmt.setString(6, email);	
			}else{
				pstmt.setString(6, "-");
			}
			sw=pstmt.executeUpdate();
			if(sw>0){
				System.out.println("회원가입 완료");
			}else{
				System.out.println("회원등록 에러");
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다"+e);
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다"+e);
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다."+e);
		}
		
		Oracle11.close(pstmt, conn);
	}
	
	public User1 getMypage(String id){
		User1 user = new User1();
		String decodingPw ="";
		String key = "%04x";
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.USER1_ONE_SELECT);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				decodingPw = AES256.decryptAES256(rs.getString("pw"), key);
				user.setPw(decodingPw);
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setAddress(rs.getString("addr"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
				user.setPoint(rs.getInt("point"));
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다"+e);
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다"+e);
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다."+e);
		}
		Oracle11.close(rs, pstmt, conn);
		return user;
	}
	public int myPageUserModify(User1 user){
		int sw=0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.USER1_MYPAGE_MODIFY);
			pstmt.setString(1, user.getPw());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getTel());
			pstmt.setString(4, user.getAddress());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getId());
			sw =pstmt.executeUpdate();
			//update user1 set pw=?,name=?,tel=?,addr=?,email=? where id=?
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다"+e);
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다"+e);
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다."+e);
		}
		return sw;
	}
	public int userDelete(String id){
		int sw=0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.USER1_DELETE);
			pstmt.setString(1, id);
			sw=pstmt.executeUpdate();
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다"+e);
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다"+e);
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다."+e);
		}
		Oracle11.close(pstmt, conn);
		return sw;
	}
	
	public int idCheck(String id){
		int sw = 0;
		try{
			conn=Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.USER1_ONE_SELECT);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				sw=1;
			}
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다"+e);
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다"+e);
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다."+e);
		}
		Oracle11.close(rs, pstmt, conn);
		return sw;
	}
}
