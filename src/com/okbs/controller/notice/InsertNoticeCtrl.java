package com.okbs.controller.notice;

import java.io.IOException;

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
public class InsertNoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		NoticeDAO ndao = new NoticeDAO();
		Notice notice = new Notice();
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
		notice.setTitle(title);
		notice.setContent(content);
		notice.setAuthor(author);
		notice.setFile1(fileName);
		ndao.insertNotice(notice);

		response.sendRedirect("NoticeList.do");
	}

}
