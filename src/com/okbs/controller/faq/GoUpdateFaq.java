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


@WebServlet("/GoUpdateFaq.do")
public class GoUpdateFaq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fno = request.getParameter("fno");
		Faq faq = new Faq();
		FaqDAO fdao = new FaqDAO();
		faq= fdao.FaqDetail(fno);
		
		request.setAttribute("faq", faq);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/faq/faqDetail.jsp");
		view.forward(request, response);
		
		
	}

}
