package com.okbs.controller.basket;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okbs.model.BasketDAO;
import com.okbs.vo.BasketVO;


@WebServlet("/GoBasket.do")
public class GoBasket extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();

		
		
		String sid = (String) session.getAttribute("id");
		if(sid.equals(id)){
			BasketDAO basdao = new BasketDAO();
			ArrayList<BasketVO> bas = new ArrayList<>();
			bas = basdao.getMyBasket(sid);
			
			//재고보다 장바구니에 높게 담으면 안되므로 재고수량도 갖고와야함
			request.setAttribute("basList", bas);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/basket/basket.jsp?id="+id);
		view.forward(request, response);
		}else{
			String alert="비정상적인 접근을 허용하지 않습니다";
			request.setAttribute("alert", alert);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
			view.forward(request, response);
		}
	}

}
