package com.okbs.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Notice;
import com.okbs.model.NoticeDAO;


@WebServlet("/NoticeList.do")
public class NoticeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NoticeDAO ndao = new NoticeDAO();
		//다오에는 이 내용이 담겨져있음
		//ArrayList<Notice> x = new ArrayList<>();
		ArrayList<Notice> notiList = new ArrayList<>();
		notiList = ndao.noticeListAll();
		request.setAttribute("notiList", notiList);
		//디스패쳐 뷰 생성해서 notiList.jsp로 요청받은 notiList포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/notice/noticeList.jsp");
		view.forward(request, response);
		
	}

}
