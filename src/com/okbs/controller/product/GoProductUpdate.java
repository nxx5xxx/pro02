package com.okbs.controller.product;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Product;
import com.okbs.model.ProductDAO;

@WebServlet("/GoProductUpdate.do")
public class GoProductUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		ProductDAO prodao = new ProductDAO();
		product = prodao.selectPcode(request.getParameter("pcode"));
		
		request.setAttribute("product", product);
		
		RequestDispatcher view = request.getRequestDispatcher("/product/updateProduct.jsp");
		view.forward(request, response);
	}


}
