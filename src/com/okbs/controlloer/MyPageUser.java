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


@WebServlet("/MyPageUser.do")
public class MyPageUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userdao = new UserDAO();
		User1 user = new User1();
		String id = request.getParameter("id");
		user = userdao.getMypage(id);
		
		
		//String 별암호 하나생성 서브스트링으로 나눠주고 그거 셋어트리뷰트로 보내기
		//암호 앞세글자만 보이도록 처리하는 구문
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
	}

}
