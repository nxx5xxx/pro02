package com.okbs.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Category;
import com.okbs.dto.Product;
import com.okbs.model.CategoryDAO;
import com.okbs.model.ProductDAO;

@WebServlet("/ProductList.do")
public class ProductListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String allccode = request.getParameter("ccode");
		ProductDAO prodao = new ProductDAO();
		CategoryDAO catedao = new CategoryDAO();
		String ccode;
		
		ArrayList<Product> proList = new ArrayList<>();
		ArrayList<Category> cateList = new ArrayList<>();
		proList = prodao.getProductNSList(allccode);
		cateList = catedao.getCategoryList(allccode);
		
		if(allccode.length()>2){
			ccode=allccode.substring(0, 2);
		}else{
			ccode=allccode;
		}
		
		String cgroup = cateList.get(0).getCgroup();
		
		request.setAttribute("cgroup", cgroup);
		request.setAttribute("ccode", ccode);
		request.setAttribute("cateList", cateList);
		request.setAttribute("proList", proList);
		
		
		RequestDispatcher view = request.getRequestDispatcher("/product/productList.jsp");
		view.forward(request, response);
	}


}
