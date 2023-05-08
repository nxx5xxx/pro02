package com.okbs.controlloer;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

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


@WebServlet("/MyPageModify.do")
public class MyPageModify extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		User1 user = new User1();
		UserDAO userdao =new UserDAO();
		user.setId(request.getParameter("id"));
		//또는 user.setId((String)session.getAttribute("id"));
		String key = "%04x";
		String encodingPw = "";
		try {
			encodingPw = AES256.encryptAES256(request.getParameter("pw"), key);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| InvalidParameterSpecException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		user.setPw(encodingPw);
		user.setName(request.getParameter("name"));
		user.setTel(request.getParameter("tel"));
		String addr="("+request.getParameter("postcode")+")  "+request.getParameter("addr1")+"  , "+request.getParameter("addr2");
		user.setAddress(addr);
		user.setEmail(request.getParameter("email"));
		int sw= 0;
		sw = userdao.myPageUserModify(user);
		
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("id");
		if(sid==null){
			System.out.println("비정상적인 접근 발견");
			session.invalidate();
			response.sendRedirect(request.getContextPath());
		}else if(sw==0){
			String alert = "정보를 수정하지 못했습니다";
			request.setAttribute("alert", alert);
			//response.sendRedirect("MyPageUser.do?id="+request.getParameter("id"));
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/login/myPageModify.jsp");
			view.forward(request, response);
		}else{
			response.sendRedirect("MyPageUser.do?id="+request.getParameter("id"));
			
		}
	}

}
