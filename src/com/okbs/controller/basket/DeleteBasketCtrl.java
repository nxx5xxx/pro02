package com.okbs.controller.basket;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okbs.model.BasketDAO;

@WebServlet("/DeleteBasket.do")
public class DeleteBasketCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		BasketDAO basdao = new BasketDAO();
		basdao.deleteBasket(request.getParameter("bnum"));
		HttpSession session = request.getSession();
		
		response.sendRedirect("GoBasket.do?id="+session.getAttribute("id"));
	}


}
