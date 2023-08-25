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
		final static String NOTICE_SELECT_ALL = "select * from notice order by idx desc";
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
		final static String PRODUCT_INSERT = "insert into product values(?,?,?,?,?,?,?,?)";
		final static String PRODUCT_SELECT_PCODE ="select * from product where pcode=?";
		final static String PRODUCT_UPDATE = "update product set pname=?,price=?,pdesc=?,pamount=?,ccode=? where pcode=?";
		final static String PRODUCT_UPDATE_IMG = "update product set pname=?,price=?,pdesc=?,pamount=?,ccode=?,img=? where pcode=? ";
		final static String PRODUCT_UPDATE_IMG2 = "update product set pname=?,price=?,pdesc=?,pamount=?,ccode=?,img2=? where pcode=? ";
		final static String PRODUCT_UPDATE_IMG_IMG2 = "update product set pname=?,price=?,pdesc=?,pamount=?,ccode=?,img=?,img2=? where pcode=? ";
		final static String PRODUCT_DELETE_PCODE = "delete from product where pcode=?";
		final static String PRODUCT_PLUS_AMOUNT= "update product set pamount=pamount+? where pcode=?";
		final static String PRODUCT_MINUS_AMOUNT= "update product set pamount=pamount-? where pcode=?";
		final static String PRODUCT_ZERO_AMOUNT = "update product set pamount=0 where pcode=?";
		
		final static String CATEGORY_SELECT_ALL = "select * from category";
		final static String CATEGORY_SELECT_ONE = "select * from category where ccode=?";
		final static String CATEGORY_SELECT_HALF = "select * from category where ccode like ?||'%'";
		final static String CATEGORY_FIRST_SELECT = "select distinct substr(ccode,1,2) as ftcate, cgroup from category group by substr(ccode,1,2), cgroup order by ftcate";
		final static String CATEGORY_SECOND_SELECT = "select ccode as frcate, cname from category where ccode like ?||'%' order by frcate";
		
		final static String PCODE_GENERATE = "select pcode from  (select * from product order by pcode desc) where rownum=1";
		
		final static String BASKETVO_ALLUSER_SELECT = "select a.bnum as bnum, a.id as id , a.pcode as pcode ,c.pname as pname ,c.pamount as pamount, a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img from basket a,user1 b , product c where a.pcode=c.pcode and a.id=b.id";
		final static String BASKETVO_ONEUSER_SELECT = "select a.bnum as bnum, a.id as id , a.pcode as pcode ,c.pname as pname ,c.pamount as pamount , a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img from basket a,user1 b , product c where a.pcode=c.pcode and a.id=b.id and a.id=?";
		final static String BASKET_LASTBNUM_SELECT = "select * from (select * from basket order by bnum desc) where rownum=1";
		final static String BASKET_ALREADY_SELECT = "select * from basket where id=? and pcode=?";
		final static String BASKET_ALREADY_UPDATE = "update basket set bamount=bamount+?,price=price*(bamount+?) where bnum=?";
		final static String BASKET_INSERT = "insert into basket values(?,?,?,?,?)";
		final static String BASKET_DELETE = "delete from basket where bnum=?";
		final static String BASKET_UPDATE_BNUM = "update basket set bamount=? where bnum=?";
		
		final static String BUY_BASKET_SELECT_ID ="select a.bnum as bnum, a.id as id , a.pcode as pcode ,c.pname as pname ,a.bamount as  bamount, (a.bamount*c.price) as price, c.img as img from basket a,user1 b , product c where a.pcode=c.pcode and a.id=b.id and a.id=?";
		final static String BUY_HOWMUCH = "select sum(a.bamount*c.price)as sumprice from  basket a,user1 b ,product c where a.id=b.id and a.pcode=c.pcode and a.id=?";
		final static String BUY_INSERT = "insert into buy values(?,?,?,?,?,?,?,' ',' ',default,default)";
		final static String BUY_ALL_SELECT = "select * from buy";
		final static String BUY_LAST_ONUM = "select * from  (select * from buy order by onum desc) where rownum=1";
		final static String BUY_LAST_PNUM = "select * from  (select * from payment order by pnum desc) where rownum=1";
		final static String BUY_LIST_SELECT_ID ="select * from buy where id=?";
		final static String BUY_LIST_IMG_SELECT_ID ="select * from buy a , product b where a.pcode=b.pcode and id=?";
		final static String BUY_LIST_IMG_SELECT_ALL ="select * from buy a , product b where a.pcode=b.pcode order by onum";
		final static String BUY_CHANGE_POST_STATUS = "update buy set ename=?, ecode=?, status=? where onum=?";
		final static String BUY_CHANGE_CONFIRM ="update buy set status=? where onum=?";
		final static String BUY_SELECT_ONUM = "select * from buy where onum=?";
		
		final static String REVIEW_INSERT = "insert into review values(?,?,?,default,?,?)";
		final static String REVIEW_SELECT_ROWNUM = "select * from (select * from review order by bno desc) where rownum=1";
		final static String REVIEW_SELECT_FROM_PCODE="select a.bno as bno , a.id as id , a.onum as onum ,a.b_date as b_date , a.b_review as b_review ,a.b_score as b_score from review a, buy b where a.onum = b.onum and b.pcode=?";
		
		final static String PAYMENT_INSERT = "insert into payment values(?,?,?,?,?,?,default)";
		
		
		final static String FAQ_LIST = "select * from faq";
		final static String FAQ_SELECT_ONE = "select * from faq where fno=?";
		final static String FAQ_SELECT_ROWNUM = "select * from (select * from faq order by fno desc) where rownum=1";
		final static String FAQ_INSERT = "insert into faq values(?,?,?,?)";
		final static String FAQ_UPDATE = "update faq set title=?,content=?,category=? where fno=?";
		
		
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

