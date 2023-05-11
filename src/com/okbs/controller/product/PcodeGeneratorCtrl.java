package com.okbs.controller.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.okbs.model.ProductDAO;


@WebServlet("/PcodeGeneratorCtrl.do")
public class PcodeGeneratorCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		/*p코드에 nextval로 하지말고 +1씩 값을추가하려면
		p코드를 내림차순으로 값을 셀렉트 한 후 웨어절에 where rownum = 1; 을 추가하여
		가장 위에있는 코드만 받는다*/
		ProductDAO prodao = new ProductDAO();
		int intpcode = prodao.pcodeGenerator();
		String pcode = intpcode + "";
		JSONObject json = new JSONObject();
		json.put("pcode", pcode);
		PrintWriter out = response.getWriter();
		out.println(json.toString());
		
		
		
	}

}
