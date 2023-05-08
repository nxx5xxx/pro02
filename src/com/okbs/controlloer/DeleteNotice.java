package com.okbs.controlloer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.model.NoticeDAO;

@WebServlet("/DeleteNotice.do")
public class DeleteNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		NoticeDAO ndao = new NoticeDAO();
		ndao.deleteNotice(idx);
		response.sendRedirect("NoticeList.do");
	}


}
