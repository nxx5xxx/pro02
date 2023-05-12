package com.okbs.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.okbs.model.Oracle11;
import com.okbs.dto.Product;
import com.okbs.vo.CategoryVO;

public class ProductDAO {
	private Connection conn=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs = null;
	
	public ArrayList<Product> getProductNSList(String ccode){
		ArrayList<Product> proList = new ArrayList<>();
		try {
			conn = Oracle11.getConnection();
			if(ccode.equals("00")){
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
			pstmt.setString(1, product.getPcode());
			pstmt.setString(2, product.getPname());
			pstmt.setInt(3, product.getPrice());
			pstmt.setString(4, product.getPdesc());
			pstmt.setInt(5, product.getPamount());
			pstmt.setString(6, product.getCcode());
			pstmt.setString(7, "img/proimg/"+product.getImg());
			pstmt.setString(8, "img/proimg/"+product.getImg2());
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
		Oracle11.close(pstmt, conn);
	}
	
	public Product selectPcode(String pcode){
		Product pro = new Product();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_SELECT_PCODE);
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));
				pro.setPrice(rs.getInt("price"));
				pro.setPdesc(rs.getString("pdesc"));
				pro.setPamount(rs.getInt("pamount"));
				pro.setCcode(rs.getString("ccode"));
				pro.setImg(rs.getString("img"));
				pro.setImg2(rs.getString("img2"));
				//System.out.println("셀렉트값 잘 받아옴");
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}

		Oracle11.close(rs, pstmt, conn);
		return pro;
	}
	
	public void updateProduct(Product product,int imgsw){
		int sw = 0;
		try{
			conn = Oracle11.getConnection();
			if(imgsw==0){
				pstmt = conn.prepareStatement(Oracle11.PRODUCT_UPDATE);
			}else if(imgsw==10){
				pstmt = conn.prepareStatement(Oracle11.PRODUCT_UPDATE_IMG);
			}else if(imgsw==11){
				pstmt = conn.prepareStatement(Oracle11.PRODUCT_UPDATE_IMG_IMG2);
			}else{
				pstmt = conn.prepareStatement(Oracle11.PRODUCT_UPDATE_IMG2);
			}
			System.out.println(imgsw);
			System.out.println(pstmt);
			pstmt.setString(1, product.getPname());
			pstmt.setInt(2, product.getPrice());
			pstmt.setString(3, product.getPdesc());
			pstmt.setInt(4, product.getPamount());
			pstmt.setString(5, product.getCcode());
			if(12>imgsw&&imgsw>=10){
				System.out.println("이미지1 수정");
				pstmt.setString(6, "img/proimg/"+product.getImg());
				pstmt.setString(imgsw-3,product.getPcode());
				}
			
			if(imgsw==11){
				System.out.println("이미지2 수정");
				pstmt.setString(7, "img/proimg/"+product.getImg2());
				pstmt.setString(8,product.getPcode());
			}else if(imgsw==12){
				System.out.println("이미지2만 수정");
				pstmt.setString(6, "img/proimg/"+product.getImg2());
				pstmt.setString(7,product.getPcode());
			}else if(imgsw==0){
				System.out.println("이미지는 냅두고 수정");
				pstmt.setString(6, product.getPcode());
			}
			
			
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
		Oracle11.close(pstmt, conn);
	}
	
	public void deleteProduct(String pcode){
		int sw = 0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_DELETE_PCODE);
			pstmt.setString(1, pcode);
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
	
	public ArrayList<CategoryVO> getFirstCategoryList(){
		ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.CATEGORY_FIRST_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CategoryVO cate = new CategoryVO();
				cate.setFtcate(rs.getString("ftcate"));
				//System.out.println(rs.getString("ftcate")); //카테고리 목록 테스트
				cate.setCgroup(rs.getString("cgroup"));
				cateList.add(cate);
			}
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return cateList;
	}
	
	public ArrayList<CategoryVO> getSecondCategoryList(String ct){
		ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.CATEGORY_SECOND_SELECT);
			pstmt.setString(1, ct);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CategoryVO cate = new CategoryVO();
				cate.setFrcate(rs.getString("frcate"));
				cate.setCname(rs.getString("cname"));
				cateList.add(cate);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, conn);
		}
		return cateList;
	}
	public int pcodeGenerator(){
		int pcode=0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.PCODE_GENERATE);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pcode = Integer.parseInt(rs.getString("pcode")) + 1;
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return pcode;
	}
	
	public void plusAmount(int plsamount,String pcode){
		int sw=0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_PLUS_AMOUNT);
			pstmt.setInt(1, plsamount);
			pstmt.setString(2, pcode);
			sw = pstmt.executeUpdate();
			if(sw>0){
				System.out.println(pcode+" 제품에"+plsamount+"개 입고완료");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Oracle11.close(pstmt, conn);
	}
	
	public void minusAmount(int plsamount,String pcode){
		int sw=0;
		int amount=0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_MINUS_AMOUNT);
			pstmt.setInt(1, plsamount);
			pstmt.setString(2, pcode);
			sw = pstmt.executeUpdate();
			if(sw>0){
				System.out.println(pcode+" 제품에"+plsamount+"개 출고완료");
			}
			
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_SELECT_PCODE);
			pstmt.setString(1, pcode);
			rs=pstmt.executeQuery();
			if(rs.next()){
				amount = rs.getInt("pamount");
			}
			
			if(amount<0){
			pstmt=conn.prepareStatement(Oracle11.PRODUCT_ZERO_AMOUNT);
			pstmt.setString(1, pcode);
			sw=pstmt.executeUpdate();
				if(sw>0){
					System.out.println("재고값이 0으로 변경되었습니다");
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
	
}
