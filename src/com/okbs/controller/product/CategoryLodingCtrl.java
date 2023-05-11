package com.okbs.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.okbs.model.ProductDAO;
import com.okbs.vo.CategoryVO;


@WebServlet("/CategoryLoding.do")
public class CategoryLodingCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String ccode1 = request.getParameter("ccode1");
		
		ProductDAO prodao = new ProductDAO();
		ArrayList<CategoryVO> ctList = new ArrayList<>();
		ctList = prodao.getSecondCategoryList(ccode1);
		//System.out.println(ctList.get(0).toString()); //테스트
		request.setAttribute("ctList", ctList);
		JSONObject json = new JSONObject();
		json.put("ctList", ctList);
		PrintWriter out = response.getWriter();
		out.println(json.toString());
		
		
		
	}

}
