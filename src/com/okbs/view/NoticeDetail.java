package com.okbs.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Notice;
import com.okbs.model.NoticeDAO;

//@WebServlet("이안의 넣은값을 그대로 호출하는것")
@WebServlet("/NoticeDetail.do")
public class NoticeDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		NoticeDAO ndao = new NoticeDAO();
		Notice noti = new Notice();
		noti = ndao.getNotice(idx);
		String fileName = noti.getFile1().substring(5);//5번째부터 이름 갖고오기
		String filePath = noti.getFile1().substring(0,4); //0번째부터 4번전(5번째) 전까지 갖고오기
//		fileName = URLEncoder.encode(fileName, "UTF-8"); //이거넣으면 한글파일이 깨져버림

		request.setAttribute("note", noti);
		request.setAttribute("fileName", fileName);
		request.setAttribute("filePath", filePath);
		//디스패쳐 뷰 생성해서 notiList.jsp로 요청받은 notiList포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/notice/noticeDetail.jsp");
		view.forward(request, response);
		
	}

}
