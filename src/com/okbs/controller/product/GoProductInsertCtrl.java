package com.okbs.controller.product;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.model.ProductDAO;
import com.okbs.vo.CategoryVO;


@WebServlet("/GoProductInsert.do")
public class GoProductInsertCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ProductDAO dao = new ProductDAO();
		ArrayList<CategoryVO> cateList = dao.getFirstCategoryList(); 
		
		request.setAttribute("cateList", cateList);
		
		RequestDispatcher view = request.getRequestDispatcher("/product/insertProduct.jsp");
		view.forward(request, response);
		
		
	}

}
