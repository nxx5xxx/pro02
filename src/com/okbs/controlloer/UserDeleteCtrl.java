package com.okbs.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.model.UserDAO;

@WebServlet("/UserDelete.do")
public class UserDeleteCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userdao = new UserDAO();
		int sw = 0;
		sw = userdao.userDelete(request.getParameter("id"));
		if(sw==0){
			String alert = "유저정보를 삭제하지 못했습니다";
			request.setAttribute("alert", alert);
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/login/myPageModify.jsp");
			view.forward(request, response);
			
		}else{
			response.sendRedirect("Logout.do");
		}
	}

}
