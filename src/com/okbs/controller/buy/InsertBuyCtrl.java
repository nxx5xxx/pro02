package com.okbs.controller.buy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Buy;
import com.okbs.dto.Payment;
import com.okbs.model.BuyDAO;

@WebServlet("/AddPayment.do")
public class InsertBuyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		

		int pcodelast = Integer.parseInt(request.getParameter("pcodelast"));
		//System.out.println(request.getParameter("pcodelast")); //pcode의 마지막번째까지 갖고옴
		String addr = "("+request.getParameter("postcode")+")  "+request.getParameter("address1")+"  , "+request.getParameter("address2");
		String id = request.getParameter("id");
		BuyDAO buydao = new BuyDAO();
		//구매정보
		for(int i=1;i<=pcodelast;i++){
			Buy buy = new Buy();
			String bnum = request.getParameter("bnum"+i);
			buy.setOnum(buydao.getOnum());
			buy.setId(id);
			//System.out.println(request.getParameter("pcode"+i));
			buy.setPcode(request.getParameter("pcode"+i));
			buy.setTel(request.getParameter("tel"));
			buy.setAddr(addr);
			int amount = Integer.parseInt(request.getParameter("amount"+i));
			buy.setAmount(amount);
			int price = Integer.parseInt(request.getParameter("price"+i));
			buy.setPrice(price);
			//buydao.insertBuy(buy);
			
			Payment pay = new Payment();
			pay.setPnum(buydao.getPnum());
			pay.setId(id);
			pay.setOnum(buy.getOnum());
			pay.setPaymtd(request.getParameter("ptype"));
			pay.setCredit(request.getParameter("ptnum"));
			pay.setPrice(price);
			
			int cnt = buydao.insertSales(buy, pay, bnum);
			if(cnt>=3){
				System.out.println("트랜잭션 처리 성공");
			} else {
				System.out.println("트랜잭션 처리 실패");
			}
			
		}
		
	}
}
