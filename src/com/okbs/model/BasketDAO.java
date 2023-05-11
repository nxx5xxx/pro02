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
			pstmt = conn.prepareStatement(Oracle11.BASKET_ALLUSER_SELECT);
			System.out.println("어드민실행");
			}else{
			pstmt = conn.prepareStatement(Oracle11.BASKET_ONEUSER_SELECT);
			pstmt.setString(1, id);
			System.out.println("고객실행");
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				BasketVO bas = new BasketVO();
				bas.setBnum(rs.getString("bnum"));
				bas.setId(rs.getString("id"));
				bas.setPcode(rs.getString("pcode"));
				System.out.println(rs.getString("pcode"));
				bas.setPname(rs.getString("pname"));
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

	
}
