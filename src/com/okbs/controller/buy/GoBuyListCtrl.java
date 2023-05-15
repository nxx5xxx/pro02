package com.okbs.controller.buy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.model.BuyDAO;
import com.okbs.vo.BuyVO;


@WebServlet("/GoBuyList.do")
public class GoBuyListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html charset=UTF-8");
		String id = request.getParameter("id");
		ArrayList<BuyVO> buyList = new ArrayList<>();
		BuyDAO buydao = new BuyDAO();
		buyList = buydao.buyList(id);
		System.out.println(id);
		request.setAttribute("buyList", buyList);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/buy/buyList.jsp");
		view.forward(request, response);
	}

}
