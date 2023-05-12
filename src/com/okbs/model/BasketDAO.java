package com.okbs.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.okbs.dto.Basket;
import com.okbs.vo.BasketVO;

public class BasketDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ArrayList<BasketVO> getMyBasket(String id){
		ArrayList<BasketVO> basList = new ArrayList<>();
		
		try {
			conn = Oracle11.getConnection();
			if(id.equals("admin")){
			pstmt = conn.prepareStatement(Oracle11.BASKETVO_ALLUSER_SELECT);
			//System.out.println("어드민 장바구니 실행");
			}else{
			pstmt = conn.prepareStatement(Oracle11.BASKETVO_ONEUSER_SELECT);
			pstmt.setString(1, id);
			//System.out.println("고객 장바구니 실행");
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				BasketVO bas = new BasketVO();
				bas.setBnum(rs.getString("bnum"));
				bas.setId(rs.getString("id"));
				bas.setPcode(rs.getString("pcode"));
				//System.out.println(rs.getString("pcode"));
				bas.setPname(rs.getString("pname"));
				bas.setPamount(rs.getShort("pamount"));
				bas.setBamount(rs.getInt("bamount"));
				bas.setPrice(rs.getInt("price"));
				bas.setImg(rs.getString("img"));
				basList.add(bas);
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return basList;
	}

	public void insertBasket(Basket bas){
		int intbnum=0;
		String bnum="";
		int sw=0;
		Boolean rssw = false;
		
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BASKET_ALREADY_SELECT);
			pstmt.setString(1, bas.getId());
			pstmt.setString(2, bas.getPcode());
			rs =pstmt.executeQuery();
			if(rs.next()){
				rssw=true;
				bnum = rs.getString("bnum");
			}
			//System.out.println(rs.next());//이미 장바구니에 있는물품이면 true 없으면 false
			if(rssw){//장바구니에 있으면 업데이트실행해서 추가
				pstmt = conn.prepareStatement(Oracle11.BASKET_ALREADY_UPDATE);
				pstmt.setInt(1, bas.getBamount());
				pstmt.setInt(2, bas.getBamount());
				pstmt.setString(3, bnum);
				sw= pstmt.executeUpdate();
				if(sw>0){
					System.out.println("장바구니에 잘 담겻을겁니다");
				}
				
			}else{//장바구니에 없으면 새로 만들기
				pstmt = conn.prepareStatement(Oracle11.BASKET_LASTBNUM_SELECT);
				rs = pstmt.executeQuery();
				if(rs.next()){
					intbnum = Integer.parseInt(rs.getString("bnum")) + 1 ;
					bnum=intbnum+"";
				}else{
					bnum="30001";
				}
			
			
				pstmt = conn.prepareStatement(Oracle11.BASKET_INSERT);
				pstmt.setString(1, bnum);
				pstmt.setString(2, bas.getId());
				pstmt.setString(3, bas.getPcode());
				pstmt.setInt(4, bas.getBamount());
				pstmt.setInt(5, bas.getPrice()*bas.getBamount());
				sw = pstmt.executeUpdate();
					if(sw>0){
						System.out.println("장바구니에 잘 담겻을겁니다");
				}
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
	
	public void deleteBasket(String bnum){
		int sw = 0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BASKET_DELETE);
			pstmt.setString(1, bnum);
			sw=pstmt.executeUpdate();
			if(sw>0){
				System.out.println("삭제가 성공적으로 이루어짐");
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
	
	public void updateBasket(int bamount, String bnum){
		//int sw=0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BASKET_UPDATE_BNUM);
			pstmt.setInt(1, bamount);
			pstmt.setString(2, bnum);
			pstmt.executeUpdate();
			/*sw=pstmt.executeUpdate();
			if(sw>0){
				System.out.println("장바구니 업데이트 완료");
			}*/
			
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
