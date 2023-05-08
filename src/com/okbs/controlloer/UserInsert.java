package com.okbs.controlloer;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crypto.util.AES256;
import com.okbs.model.UserDAO;


@WebServlet("/UserInsert.do")
public class UserInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8" );
		
		UserDAO user = new UserDAO();
		String id = request.getParameter("id");
		String key = "%04x";
		String pw= "";
		try {
			pw = AES256.encryptAES256(request.getParameter("pw"), key);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| InvalidParameterSpecException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String addr = "("+request.getParameter("postcode")+")  "+request.getParameter("addr1")+"  , "+request.getParameter("addr2");
		System.out.println("addr : " + request.getParameter("addr1"));
		String email = request.getParameter("email");
		user.insertUser(id, pw, name,tel ,  addr, email);
		response.sendRedirect("GoLogin.do");
	}

}
