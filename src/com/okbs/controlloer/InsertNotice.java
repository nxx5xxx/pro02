package com.okbs.controlloer;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Notice;
import com.okbs.model.NoticeDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/InsertNotice.do")
public class InsertNotice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		String savePath="/data";
		int uploadFileSizeLimit = 10 * 1024 * 1024;
		String encType = "UTF-8";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		
		String title = "";
		String author = "";
		String content = "";
		String fileName = "";
			
		try{
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath,uploadFileSizeLimit,encType,new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("file");
			if(fileName==null){
				System.out.println("파일 업로드 실패");
			}
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			author = multi.getParameter("author");

		}catch(Exception e){
			System.out.println("예외발생"+e);
		}
		NoticeDAO ndao = new NoticeDAO();
		ndao.insertNotice(title, content, author, fileName);
		System.out.println("파일 업로드 성공");

		response.sendRedirect("NoticeList.do");
	}

}
