package com.okbs.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Faq;
import com.okbs.model.FaqDAO;

@WebServlet("/UpdateFaq.do")
public class UpdateFaq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Faq faq = new Faq();
		faq.setFno(request.getParameter("fno"));
		faq.setCategory(request.getParameter("category"));
		faq.setTitle(request.getParameter("title"));
		faq.setContent(request.getParameter("content"));
		
		FaqDAO fdao = new FaqDAO();
		fdao.FaqUpdate(faq);
		
		response.sendRedirect("GoFaqList.do");
		
	}

}
