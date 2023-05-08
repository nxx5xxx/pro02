package com.okbs.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/GoInsNotice.do")
public class GoInsNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
//폼처럼 post나 get방식이 아니라면 service로해야 함
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/notice/insertNotice.jsp");
		view.forward(request, response);
		
	}

}
