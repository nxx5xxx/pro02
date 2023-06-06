package com.okbs.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import com.okbs.dto.Faq;

public class FaqDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ArrayList<Faq> FaqList(){
		ArrayList<Faq> flst = new ArrayList<>();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.FAQ_LIST);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Faq faq = new Faq();
				faq.setFno(rs.getString("fno"));
				faq.setCategory(rs.getString("category"));
				faq.setTitle(rs.getString("title"));
				faq.setContent(rs.getString("content"));
				flst.add(faq);
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return flst;
	}
	
	public void insertFaq(Faq faq){
		int sw = 0;
		int intfno = 0;
		String fno = "";
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.FAQ_SELECT_ROWNUM);
			rs = pstmt.executeQuery();
			if(rs.next()){
				intfno = Integer.parseInt(rs.getString("fno"))+1;
				fno = intfno+"";
			}else{
				fno = "30001";
			}
			pstmt = conn.prepareStatement(Oracle11.FAQ_INSERT);
			pstmt.setString(1,fno);
			pstmt.setString(2, faq.getCategory());
			pstmt.setString(3, faq.getTitle());
			pstmt.setString(4, faq.getContent());
			sw = pstmt.executeUpdate();
			if(sw>0){
				System.out.println("FAQ등록이 정상적으로 이뤄졌습니다");
			}

			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);

	}
	
	public Faq FaqDetail(String fno){
		Faq faq = new Faq();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.FAQ_SELECT_ONE);
			pstmt.setString(1, fno);
			rs = pstmt.executeQuery();
			if(rs.next()){
				faq.setFno(rs.getString("fno"));
				faq.setCategory(rs.getString("category"));
				faq.setTitle(rs.getString("title"));
				faq.setContent(rs.getString("content"));
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return faq;
	}
	
	public void FaqUpdate(Faq faq){
		int sw= 0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.FAQ_UPDATE);
			pstmt.setString(1, faq.getTitle());
			pstmt.setString(2, faq.getContent());
			pstmt.setString(3, faq.getCategory());
			pstmt.setString(4, faq.getFno());
			sw= pstmt.executeUpdate();
			if(sw>0){
				System.out.println("FAQ수정이 잘 이뤄졌습니다");
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(pstmt, conn);
	}
}
