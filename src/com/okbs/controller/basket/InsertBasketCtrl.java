package com.okbs.controller.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okbs.dto.Basket;
import com.okbs.dto.Product;
import com.okbs.model.BasketDAO;
import com.okbs.model.ProductDAO;

@WebServlet("/InsertBasket.do")
public class InsertBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	System.out.println("인서트바스켓실행");
	HttpSession session = request.getSession();
	String id =(String) session.getAttribute("id");
	
	String pcode = request.getParameter("pcode");
	//System.out.println("pcode값 테스트 "+pcode);
	int amount = Integer.parseInt(request.getParameter("amount"));
	//System.out.println("amount값 테스트 "+amount);
	
	Basket bas = new Basket();

	BasketDAO basdao = new BasketDAO();
	Product pro = new Product();
	ProductDAO prodao = new ProductDAO();
	//상품정보 불러옴
	pro = prodao.selectPcode(pcode);
	int price = pro.getPrice();
	bas.setPcode(pcode);
	bas.setBamount(amount);
	bas.setPrice(price);
	bas.setId(id);
	//불러온상품정보랑 대입해서 장바구니 업데이트
	basdao.insertBasket(bas);
	
	//System.out.println("여기까지 완료");
	
	}

}
