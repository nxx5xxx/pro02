package com.okbs.controlloer.user;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crypto.util.AES256;
import com.okbs.dto.User1;
import com.okbs.model.UserDAO;


@WebServlet("/Login.do")
public class LoginCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User1 user = new User1();
		UserDAO ldao = new UserDAO();
		String key = "%04x";
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		user = ldao.getLoginInfo(id );
		//System.out.println(user.getId()+"\\"+user.getPw());
		String decodingPw="";
		if(user.getPw() != null){
			try {
				decodingPw = AES256.decryptAES256(user.getPw(), key);
			} catch (InvalidKeyException | NoSuchPaddingException
					| NoSuchAlgorithmException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | BadPaddingException
					| IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(user.getId() == null || !pw.equals(decodingPw) ){
			//System.out.println("아이디 또는 비밀번호를 바르게 입력해야 로그인이 됩니다");
			//시스템아웃을 아이디 다시입력하라는 알러트 넣어주기
			String alert = "아이디 또는 비밀번호를 바르게 입력해야 로그인이 됩니다";
			request.setAttribute("alert", alert);
			RequestDispatcher view = request.getRequestDispatcher("/GoLogin.do");
			view.forward(request, response);
		}else{
			HttpSession session = request.getSession();//request객체로부터 세션을 갖고와 HttpSession이라는 타입을가진 session을 만든다
			session.setAttribute("id", user.getId() );//세션설정부터해야함
			//session.setAttribute("pw", user.getPw() ); //비밀번호를 세션에 딱히 갖고있을필요가없음
			RequestDispatcher view = request.getRequestDispatcher("/");
			view.forward(request, response);
			//response.sendRedirect(request.getContextPath());
			//response.sendRedirect("/");
		}
	}

}
