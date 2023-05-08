package com.okbs.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.User1;
import com.okbs.model.UserDAO;


@WebServlet("/GoMyPageUserModify.do")
public class GoMyPageUserModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userdao = new UserDAO();
		User1 user = new User1();
		String id = request.getParameter("id");
		//System.out.println(request.getParameter("id") + "마이페이지두 id리퀘스트값");
		user = userdao.getMypage(id);
		String address = user.getAddress();
		int wherePostCode = address.indexOf(")  ");
		int whereAddr2 = address.indexOf("  , ")+4;
		String postCode = address.substring(1,wherePostCode);
		String addr1 = address.substring(wherePostCode+3, whereAddr2);
		String addr2 = address.substring(whereAddr2);
		System.out.println(addr1);
		System.out.println(addr2);
		request.setAttribute("postCode", postCode);
		request.setAttribute("addr1", addr1);
		request.setAttribute("addr2", addr2);
		request.setAttribute("user", user);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/login/myPageModify.jsp");
		view.forward(request, response);
	}

}
