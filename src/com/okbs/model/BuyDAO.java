package com.okbs.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.okbs.dto.Buy;
import com.okbs.dto.Payment;
import com.okbs.vo.BasketVO;
import com.okbs.vo.BuyVO;


public class BuyDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs =null;
	
	public ArrayList<BasketVO> buyFromBasket(String id){
		ArrayList<BasketVO> basList = new ArrayList<>();
		try {
			conn =Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BUY_BASKET_SELECT_ID);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			while(rs.next()){
				BasketVO bas = new BasketVO();
				bas.setBnum(rs.getString("bnum"));
				bas.setId(rs.getString("id"));
				bas.setPcode(rs.getString("pcode"));
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
	public int totalMoney(String id){
		int totalmoney = 0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BUY_HOWMUCH);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				totalmoney = rs.getInt("sumprice");
			}
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return totalmoney;
	}
	public void insertBuy(Buy buy){
		int intonum=0;
		String onum="";
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BUY_LAST_ONUM);
			rs = pstmt.executeQuery();
			if(rs.next()){
				intonum = Integer.parseInt(rs.getString("onum")) + 1 ;
				onum=intonum+"";
			}else{
				onum="10001";
			}
			
			pstmt = conn.prepareStatement(Oracle11.BUY_INSERT);
			pstmt.setString(1, onum);
			pstmt.setString(2, buy.getId());
			pstmt.setString(3, buy.getPcode());
			pstmt.setString(4, buy.getTel());
			pstmt.setString(5, buy.getAddr());
			pstmt.setInt(6, buy.getAmount());
			pstmt.setInt(7, buy.getPrice());
			pstmt.executeUpdate();
		
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
	}
	public String getOnum(){
		int intonum=0;
		String onum="";
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BUY_LAST_ONUM);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				intonum = Integer.parseInt(rs.getString("onum")) + 1 ;
				onum=intonum+"";
				
				//System.out.println("getonum "+onum +": 잘 받아옴");
			}else{
				onum="10001";
			}
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return onum;
	}
	
	public String getPnum(){
		int intpnum=0;
		String pnum="";
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BUY_LAST_PNUM);
			rs = pstmt.executeQuery();
			if(rs.next()){
				intpnum = Integer.parseInt(rs.getString("pnum")) + 1 ;
				pnum=intpnum+"";
				//System.out.println("getonum "+pnum +": 잘 받아옴");
			}else{
				pnum="20001";
			}
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return pnum;
	}
	
	public int insertSales(Buy buy,Payment pay,String bnum){
		int cnt = 0;
		try {
			conn = Oracle11.getConnection();
			conn.setAutoCommit(false);
			//구매목록 추가
			pstmt = conn.prepareStatement(Oracle11.BUY_INSERT);
/*			System.out.println(buy.getOnum() +"Onum");
			System.out.println(buy.getId() +"id");
			System.out.println(buy.getPcode() +"pcode");
			System.out.println(buy.getTel() +"tel");
			System.out.println(buy.getAddr() +"addr");
			System.out.println(buy.getAmount() +"amount");
			System.out.println(buy.getPrice() +"price");*/
//"insert into buy values(?,?,?,?,?,?,?,' ',' ',default,default)";
			pstmt.setString(1, buy.getOnum());
			pstmt.setString(2, buy.getId());
			pstmt.setString(3, buy.getPcode());
			pstmt.setString(4, buy.getTel());
			pstmt.setString(5, buy.getAddr());
			pstmt.setInt(6, buy.getAmount());
			pstmt.setInt(7, buy.getPrice());
//			System.out.println("구매목록추가에 문제발생");
			cnt = pstmt.executeUpdate();
			
			
			//결제추가
			pstmt = conn.prepareStatement(Oracle11.PAYMENT_INSERT);
/*			System.out.println(pay.getPnum() +"Pnum");
			System.out.println(pay.getId() +"id");
			System.out.println(pay.getOnum() +"Onum");
			System.out.println(pay.getPaymtd() +"paymtd");
			System.out.println(pay.getCredit() +"credit");
			System.out.println(pay.getPrice() +"price");*/

			pstmt.setString(1, pay.getPnum());
			pstmt.setString(2, pay.getId());
			pstmt.setString(3, pay.getOnum());
			pstmt.setString(4, pay.getPaymtd());
			pstmt.setString(5, pay.getCredit());
			pstmt.setInt(6, pay.getPrice());
//			System.out.println("결제추가에 문제발생");
			cnt = cnt+ pstmt.executeUpdate();
			
			//장바구니에서 삭제
			if(bnum!=null){
			pstmt = conn.prepareStatement(Oracle11.BASKET_DELETE);
//			System.out.println(bnum);
			pstmt.setString(1, bnum);
//			System.out.println("장바구니 지우기에 문제발생");
			cnt = cnt + pstmt.executeUpdate();
			}
			
			//상품재고에서 삭제
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_MINUS_AMOUNT);
			pstmt.setInt(1, buy.getAmount());
			pstmt.setString(2, buy.getPcode());
//			System.out.println("상품갯수 제거에 문제발생");
			cnt = cnt + pstmt.executeUpdate();
			

			conn.commit();
			conn.setAutoCommit(true);
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return cnt;
	}
	
	public int insertSales2(Buy buy,Payment pay){
		int cnt = 0;
		try {
			conn = Oracle11.getConnection();
			conn.setAutoCommit(false);
			//구매목록 추가
			pstmt = conn.prepareStatement(Oracle11.BUY_INSERT);
/*			System.out.println(buy.getOnum() +"Onum");
			System.out.println(buy.getId() +"id");
			System.out.println(buy.getPcode() +"pcode");
			System.out.println(buy.getTel() +"tel");
			System.out.println(buy.getAddr() +"addr");
			System.out.println(buy.getAmount() +"amount");
			System.out.println(buy.getPrice() +"price");*/
//"insert into buy values(?,?,?,?,?,?,?,' ',' ',default,default)";
			pstmt.setString(1, buy.getOnum());
			pstmt.setString(2, buy.getId());
			pstmt.setString(3, buy.getPcode());
			pstmt.setString(4, buy.getTel());
			pstmt.setString(5, buy.getAddr());
			pstmt.setInt(6, buy.getAmount());
			pstmt.setInt(7, buy.getPrice());
//			System.out.println("구매목록추가에 문제발생");
			cnt = pstmt.executeUpdate();
			
			
			//결제추가
			pstmt = conn.prepareStatement(Oracle11.PAYMENT_INSERT);
/*			System.out.println(pay.getPnum() +"Pnum");
			System.out.println(pay.getId() +"id");
			System.out.println(pay.getOnum() +"Onum");
			System.out.println(pay.getPaymtd() +"paymtd");
			System.out.println(pay.getCredit() +"credit");
			System.out.println(pay.getPrice() +"price");*/

			pstmt.setString(1, pay.getPnum());
			pstmt.setString(2, pay.getId());
			pstmt.setString(3, pay.getOnum());
			pstmt.setString(4, pay.getPaymtd());
			pstmt.setString(5, pay.getCredit());
			pstmt.setInt(6, pay.getPrice());
//			System.out.println("결제추가에 문제발생");
			cnt = cnt+ pstmt.executeUpdate();
			

			
			//상품재고에서 삭제
			pstmt = conn.prepareStatement(Oracle11.PRODUCT_MINUS_AMOUNT);
			pstmt.setInt(1, buy.getAmount());
			pstmt.setString(2, buy.getPcode());
//			System.out.println("상품갯수 제거에 문제발생");
			cnt = cnt + pstmt.executeUpdate();
			

			conn.commit();
			conn.setAutoCommit(true);
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return cnt;
	}
	public ArrayList<BuyVO> buyList(String id){
		ArrayList<BuyVO> buyList = new ArrayList<>();
		try {
			conn = Oracle11.getConnection();
			if(id.equals("admin")){
				pstmt = conn.prepareStatement(Oracle11.BUY_LIST_IMG_SELECT_ALL);	
			}else{
				pstmt = conn.prepareStatement(Oracle11.BUY_LIST_IMG_SELECT_ID);
				pstmt.setString(1, id);
			}
			rs = pstmt.executeQuery();
			while(rs.next()){
				BuyVO buy = new BuyVO();
				buy.setOnum(rs.getString("onum"));
				buy.setId(rs.getString("id"));
				buy.setPcode(rs.getString("pcode"));
				buy.setTel(rs.getString("tel"));
				buy.setAddr(rs.getString("addr"));
				buy.setAmount(rs.getInt("amount"));
				buy.setPrice(rs.getInt("price"));
				buy.setEname(rs.getString("ename"));
				buy.setEcode(rs.getString("ecode"));
				buy.setStatus(rs.getString("status"));
				buy.setOdate(rs.getString("odate"));
				buy.setPname(rs.getString("pname"));
				buy.setImg(rs.getString("img"));
				buyList.add(buy);
			}
			
		} catch(ClassNotFoundException e) {
			System.out.println("오라클JDBC 파일이 잘못되었습니다");
		} catch(SQLException e) {
			System.out.println("SQL구문이 잘못되었습니다");
		} catch(Exception e){
			System.out.println("식별할수 없는 오류가 발생했습니다.");
		}
		Oracle11.close(rs, pstmt, conn);
		return buyList;
	}
	
	public void changePostStatus(Buy buy){
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.BUY_CHANGE_POST_STATUS);
			pstmt.setString(1, buy.getEname());
			pstmt.setString(2, buy.getEcode());
			pstmt.setString(3, buy.getStatus());
			pstmt.setString(4, buy.getOnum());
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
}
