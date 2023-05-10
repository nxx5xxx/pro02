package com.okbs.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.okbs.dto.Product;

public class ProductDAO {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs = null;
	
	public ArrayList<Product> getProductNSList(String ccode){
		ArrayList<Product> proList = new ArrayList<>();
		try {
			conn = Oracle11.getConnection();
			if(ccode.equals("00")){
				System.out.println(ccode);
				pstmt = conn.prepareStatement(Oracle11.PRODUCT_SELECT_ALL);
			}else{
				pstmt = conn.prepareStatement(Oracle11.PRODUCT_SELECT_CATE_ALL);
				pstmt.setString(1, ccode);
			}
			rs=pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));  
				pro.setPrice (rs.getInt("price"));         
				pro.setPdesc(rs.getString("pdesc"));  
				pro.setPamount(rs.getInt("pamount"));         
				pro.setCcode(rs.getString("ccode"));    
				pro.setImg(rs.getString("img"));
				proList.add(pro);
			}
		
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return proList;
	}
	
	public void insertProduct(Product product){
		int sw = 0;
		try{
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_INSERT);
			pstmt.setString(1, product.getPname());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getPdesc());
			pstmt.setInt(4, product.getPamount());
			pstmt.setString(5, product.getCcode());
			pstmt.setString(6, "img/proimg/"+product.getImg());
			pstmt.setString(7, product.getImg2());
			sw = pstmt.executeUpdate();
			if(sw>0){
				System.out.println("파일이 성공적으로 업로드되었습니다");
			}else{
				System.out.println("파일 업로드 에러");
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
