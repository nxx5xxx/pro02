package com.okbs.controller.faq;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Faq;
import com.okbs.model.FaqDAO;


@WebServlet("/GoFaqList.do")
public class GoFaqList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Faq> fList = new ArrayList<>();
		FaqDAO fdao = new FaqDAO();
		fList= fdao.FaqList();
		
		request.setAttribute("faqList", fList);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/faq/faqList.jsp");
		view.forward(request, response);
		
		
	}

}
