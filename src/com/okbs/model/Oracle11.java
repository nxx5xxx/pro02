package com.okbs.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oracle11 {
	static String driver = "oracle.jdbc.driver.OracleDriver";
	static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	static String user = "pro02";
	static String password = "1234";
		final static String NOTICE_SELECT_ALL = "select * from notice";
		final static String NOTICE_SELECT_ONE = "select * from notice where idx=?";
		final static String NOTICE_READCOUNT_UPDATE = "update notice set cnt=cnt+1 where idx=?";
		final static String NOTICE_INSERT = "insert into notice values(noti_seq.nextval,?,?,?,?,default,default)";
		final static String NOTICE_UPDATE = "update notice set title=?,content=?,file1=? where idx=?";
		final static String NOTICE_UPDATE_NOTFILE = "update notice set title=?,content=? where idx=?";
		final static String NOTICE_DELETE = "delete from notice where idx=?";
		
		final static String USER1_ONE_SELECT = "select * from user1 where id=?";
		final static String USER1_INSERT = "insert into user1 values(?,?,?,?,?,?,default,default)";
		final static String USER1_MYPAGE_MODIFY = "update user1 set pw=?,name=?,tel=?,addr=?,email=? where id=?";
		final static String USER1_DELETE = "delete from user1 where id=? ";
		final static String USER1_UPDATE_POINT ="update user1 set point=point+1 where id=?";
		
		final static String PRODUCT_SELECT = "select * from product where ccode=?";
		final static String PRODUCT_SELECT_CATE_ALL = "select * from product where ccode like ?||'%'";
		final static String PRODUCT_SELECT_ALL = "select * from product";
		final static String PRODUCT_INSERT = "insert into product values(pcode.nextval,?,?,?,?,?,?,?)";
		
		
		
		final static String CATEGORY_SELECT_ALL = "select * from category";
		final static String CATEGORY_SELECT_ONE = "select * from category where ccode=?";
		final static String CATEGORY_SELECT_HALF = "select * from category where ccode like ?||'%'";
		
		
	//연결해주는 연결자
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,user,password);
		return conn;
	}
	public static void close(PreparedStatement pstmt, Connection conn){
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet rs,PreparedStatement pstmt, Connection conn){
		if(rs!=null){
			try{
				rs.close();
			}catch(Exception e){
			}
		}
		if(pstmt!=null){
			try{
				pstmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try{
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
	/*public static void close(PreparedStatement pstmt, Connection conn){
		//pstmt가 null이 아니란말은 연결이 되어있다면이라는뜻
		if(pstmt!=null){
			트{}
				pstmt.close();
			캐{ e.printStackTrace();}
		if(conn!=null)
		}
	}
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
	위방식으로 닫고*/

