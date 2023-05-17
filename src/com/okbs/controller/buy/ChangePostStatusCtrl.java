package com.okbs.controller.buy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Buy;
import com.okbs.model.BuyDAO;


@WebServlet("/ChangePostStatus.do")
public class ChangePostStatusCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		String ename = request.getParameter("ename");
		String ecode = request.getParameter("ecode");
		String onum = request.getParameter("onum");
		String status = request.getParameter("status");
		//ajax로 잘넘겨받았나 테스트
		//System.out.println(request.getParameter("ename"));
		//System.out.println(request.getParameter("ecode"));
		System.out.println(request.getParameter("onum"));
		if(ename==null && ecode == null ){
			Buy buy = new Buy();
			BuyDAO buydao = new BuyDAO();
			buy.setStatus(status);
			buy.setOnum(onum);
			buydao.confirmBuy(buy);
		}else{
		Buy buy = new Buy();
		BuyDAO buydao = new BuyDAO();
		buy.setEcode(ecode);
		buy.setEname(ename);
		buy.setOnum(onum);
		buy.setStatus(status);
		buydao.changePostStatus(buy);
		}


		
	}

}
