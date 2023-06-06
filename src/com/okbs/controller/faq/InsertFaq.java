package com.okbs.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Faq;
import com.okbs.model.FaqDAO;

@WebServlet("/InsertFaq.do")
public class InsertFaq extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		Faq faq = new Faq();
		FaqDAO fdao = new FaqDAO();
		
		faq.setCategory(request.getParameter("category"));
		faq.setTitle(request.getParameter("title"));
		faq.setContent(request.getParameter("content"));
		fdao.insertFaq(faq);
		
		response.sendRedirect("GoFaqList.do");
	}

}
