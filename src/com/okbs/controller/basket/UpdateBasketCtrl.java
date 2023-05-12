package com.okbs.controller.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.model.BasketDAO;

@WebServlet("/UpdateBasket.do")
public class UpdateBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//System.out.println("장바구니 내  수량변경");
		int bamount = Integer.parseInt(request.getParameter("bskamount"));
		String bnum = request.getParameter("bnum");
		
		System.out.println("받은수량 " + bamount);
		System.out.println("장바구니번호  " +bnum);
		BasketDAO basdao = new BasketDAO();
		basdao.updateBasket(bamount, bnum);
	}

}
