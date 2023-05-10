package com.okbs.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.okbs.dto.Category;



public class CategoryDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ArrayList<Category> getCategoryList(String ccode){
		ArrayList<Category> cateList = new ArrayList<>();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.CATEGORY_SELECT_HALF);
			pstmt.setString(1, ccode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Category cate = new Category();
				cate.setCcode(rs.getString("ccode"));
				cate.setCgroup(rs.getString("cgroup"));
				cate.setCname(rs.getString("cname"));
				cateList.add(cate);
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
	
		return cateList;
	}
	
}
