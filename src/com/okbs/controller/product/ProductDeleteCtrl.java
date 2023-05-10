package com.okbs.controller.product;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.model.ProductDAO;

@WebServlet("/ProductDelete.do")
public class ProductDeleteCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ProductDAO prodao = new ProductDAO();
		prodao.deleteProduct(request.getParameter("pcode"));

		response.sendRedirect("ProductList.do?ccode=00");
	}


}
