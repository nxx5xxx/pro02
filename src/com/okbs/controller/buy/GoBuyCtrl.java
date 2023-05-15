package com.okbs.controller.buy;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okbs.dto.User1;
import com.okbs.model.BasketDAO;
import com.okbs.model.BuyDAO;
import com.okbs.model.UserDAO;
import com.okbs.vo.BasketVO;


@WebServlet("/GoBuy.do")
public class GoBuyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		if(sid.equals(id)){
			BasketDAO basdao = new BasketDAO();
			ArrayList<BasketVO> bas = new ArrayList<>();
			bas = basdao.getMyBasket(sid);

			request.setAttribute("basList", bas);
			//총 합 가격을 갖고와야함
			//select sum(a.bamount*c.price)as sumprice from  basket a,user1 b ,product c where a.id=b.id and a.pcode=c.pcode and a.id='kim';
			BuyDAO buydao = new BuyDAO();
			int totalmoney = buydao.totalMoney(sid);
			request.setAttribute("totalmoney", totalmoney);
			
			UserDAO userdao = new UserDAO();
			User1 user = new User1();
			user = userdao.getMypage(sid);
			
			String address = user.getAddress();
			int wherePostCode = address.indexOf(")  ");
			int whereAddr2 = address.indexOf("  , ");
			String postCode="";
			if(wherePostCode>1){
			postCode = address.substring(1,wherePostCode);
			}
			
			String addr1 = "";
			if(whereAddr2>1){
			addr1 = address.substring(wherePostCode+3, whereAddr2);
			}
			
			String addr2 = address.substring(whereAddr2+4);
			System.out.println(addr1);
			System.out.println(addr2);
			request.setAttribute("postCode", postCode);
			request.setAttribute("addr1", addr1);
			request.setAttribute("addr2", addr2);
			request.setAttribute("user", user);
			
			
			
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/buy/buy.jsp?id="+id);
		view.forward(request, response);
		}else{
			String alert="비정상적인 접근을 허용하지 않습니다";
			request.setAttribute("alert", alert);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
			view.forward(request, response);
		}
	}

}
