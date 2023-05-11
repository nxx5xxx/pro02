package com.okbs.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.model.ProductDAO;


@WebServlet("/MinusAmount.do")
public class MinusAmountCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int mnschk = Integer.parseInt(request.getParameter("mnschk"));
		String pcode = request.getParameter("pcode"); 
		System.out.println(mnschk);
		System.out.println(request.getParameter("pcode"));
		ProductDAO prodao=new ProductDAO();
		
		prodao.minusAmount(mnschk,pcode);
		
	}

}
