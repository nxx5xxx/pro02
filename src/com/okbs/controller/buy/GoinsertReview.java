package com.okbs.controller.buy;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okbs.dto.Buy;
import com.okbs.model.BuyDAO;

/**
 * Servlet implementation class GoinsertReview
 */
@WebServlet("/GoinsertReview.do")
public class GoinsertReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String onum = request.getParameter("onum");
		String sid = (String)session.getAttribute("id");
		System.out.println(sid);
		//오더값 받아와서거기있는 id랑 다를경우 비정상적인 접속으로 판단하여 error창띄우기
		BuyDAO buydao = new BuyDAO();
		String id = buydao.buyID(onum);
		if(sid.equals(id)){
			request.setAttribute("onum", onum);
		RequestDispatcher view =  request.getRequestDispatcher("/WEB-INF/review/insertReview.jsp");
		view.forward(request, response);
		}else{
			System.out.println("비정상 접근 발견");
			response.sendRedirect("notFoundErrorPage.jsp");
		}
	}

}
