package com.okbs.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Notice;
import com.okbs.model.NoticeDAO;


@WebServlet("/GoUpdateNotice.do")
public class GoUpdateNoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		 Notice notice = new Notice();
		 NoticeDAO ndao = new NoticeDAO();
		 notice = ndao.getNotice(idx);
		 String fileName = notice.getFile1().substring(5);
		 request.setAttribute("fileName", fileName);
		request.setAttribute("updatenotice", notice);
		 
		

		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/notice/noticeUpdate.jsp");
		view.forward(request, response);
	}

}
