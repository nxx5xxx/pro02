package com.okbs.controller.buy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Buy;
import com.okbs.dto.Review;
import com.okbs.model.BuyDAO;


@WebServlet("/InsertReview.do")
public class InsertReview extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String onum = request.getParameter("onum");
		String b_review = request.getParameter("b_review");
		String b_score_str = request.getParameter("b_score");
		
		//ajax 받아온거 확인
		/*System.out.println(id);
		System.out.println(onum);
		System.out.println(b_review);
		System.out.println(b_score_str);*/
		
		int b_score = 5;
		switch(b_score_str){
			case "매우풀만족":
				b_score = 1;
				break;
			case "불만족":
				b_score = 2;
				break;
			case "보통만족":
				b_score = 3;
				break;
			case "만족":
				b_score = 4;
				break;
			case "매우만족":
				b_score = 5;
				break;
		}
		Review rev = new Review();
		rev.setId(id);
		rev.setOnum(onum);
		rev.setB_review(b_review);
		rev.setB_score(b_score);
		BuyDAO buydao = new BuyDAO();
		buydao.insertReview(rev);
		
		String status = "후기보기";
		Buy buy = new Buy();
		buy.setStatus(status);
		
		buy.setOnum(onum);
		buydao.confirmBuy(buy);
		
		
	}

}
