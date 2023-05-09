package com.okbs.controlloer.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.okbs.dto.User1;
import com.okbs.model.UserDAO;


@WebServlet("/MyPageUser.do")
public class MyPageUserCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String sid = (String) session.getAttribute("id");
		if(sid.equals(id)){
		UserDAO userdao = new UserDAO();
		User1 user = new User1();
		

		user = userdao.getMypage(id);
		int pwLength = 0;
		String decodingPw = user.getPw();
		pwLength = decodingPw.length();
		String pwSubString = decodingPw.substring(0,3);
		for(int i=3;i<pwLength;i++){
			pwSubString = pwSubString+"*";
		}
		user.setPw(pwSubString);
		
		
		request.setAttribute("user", user);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/login/myPageUser.jsp");
		view.forward(request, response);
		}else{
			String alert="비정상적인 접근을 허용하지 않습니다";
			request.setAttribute("alert", alert);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
			view.forward(request, response);
		}
	}

}
