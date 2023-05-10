package com.okbs.controller.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crypto.util.AES256;
import com.okbs.dto.User1;
import com.okbs.model.UserDAO;


@WebServlet("/UserInsert.do")
public class UserInsertCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8" );
		UserDAO userDao = new UserDAO();
		User1 user = new User1();
		user.setId(request.getParameter("id"));
		String key = "%04x";
		String pw= "";
		try {
			pw = AES256.encryptAES256(request.getParameter("pw"), key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setPw(pw);
		user.setName(request.getParameter("name"));
		user.setTel(request.getParameter("tel"));
		String addr = "("+request.getParameter("postcode")+")  "+request.getParameter("addr1")+"  , "+request.getParameter("addr2");
		user.setAddress(addr);
		user.setEmail(request.getParameter("email"));
		userDao.insertUser(user);
		response.sendRedirect("GoLogin.do");
	}

}
