package com.okbs.model;
/*package com.okbs.model;
import java.util.ArrayList;

import com.okbs.dto.Notice;

import java.sql.*;

	
public class NoticeDAO {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	//String sql="";
	public ArrayList<Notice> noticeListAll(){
		ArrayList<Notice> notiList = new ArrayList<>();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Notice noti = new Notice();
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setCnt(rs.getInt("cnt"));
				
				notiList.add(noti);
			}
			

		} catch (ClassNotFoundException e) {
				System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Oracle11.close(rs, pstmt, conn);
		return notiList;
	}
	//오라클셀렉트원
	public Notice getNotice(int idx){
		updateReadCount(idx);
		Notice noti = new Notice();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_SELECT_ONE);
			pstmt.setInt(1, idx );
			rs = pstmt.executeQuery();
			if(rs.next()){
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setCnt(rs.getInt("cnt"));
			}

		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return noti;
	}

	public void updateReadCount(int idx){
		//pstmt=conn에 NOTICE READ COUNT UPDATE
		//pstmt.setInt(1,idx);
		//pstmt.executeUpdate();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_READCOUNT_UPDATE);
			pstmt.setInt(1, idx );
			pstmt.executeUpdate();

		} catch(ClassNotFoundException e) {
				System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(pstmt, conn);
		
	}
	
	public void insertNotice(Notice noti){
		int sw=0;
		try{
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_INSERT);
			pstmt.setString(1, noti.getTitle());
			pstmt.setString(2, noti.getContent());
			pstmt.setString(3, noti.getAuthor());
			pstmt.setString(4, "data/"+noti.getFile1());
			sw=pstmt.executeUpdate();
			if(sw>0){
				System.out.println("Notice등록 완료");
			}else{
				System.out.println("Notice등록 에러");
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
	}
	}
}
*/