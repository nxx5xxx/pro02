package com.okbs.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Product;
import com.okbs.dto.Review;
import com.okbs.model.BuyDAO;
import com.okbs.model.ProductDAO;

@WebServlet("/GoProductDetail.do")
public class GoProductDetailCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		ProductDAO prodao = new ProductDAO();
		product = prodao.selectPcode(request.getParameter("pcode"));
		
		ArrayList<Review> revList = new ArrayList<>();
		BuyDAO buydao = new BuyDAO();
		revList = buydao.getReview(request.getParameter("pcode"));
		
		
		request.setAttribute("revList",revList);
		request.setAttribute("product", product);
		
		RequestDispatcher view = request.getRequestDispatcher("/product/productDetail.jsp");
		view.forward(request, response);
	}


}
