package com.okbs.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String author="nxx5xxx";
		//홈 디렉토리 잡기
		//어플리케이션 객체
		ServletContext application = request.getServletContext();
		//경로설정
		String realPath =  request.getSession().getServletContext().getRealPath("/");
		application.setAttribute("realPath", realPath);
		
		//메인페이지 포워딩
		//리퀘스트에서 어플리케이션으로 바꿈
		application.setAttribute("author", author);
		//여기서 리퀘스트 디스패쳐를 해야지만 WEB-INF안에 접속가능함
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
		view.forward(request, response);
		
	}

}
