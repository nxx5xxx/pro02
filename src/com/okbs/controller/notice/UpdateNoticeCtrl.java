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

@WebServlet("/UpdateNotice.do")
public class UpdateNoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		NoticeDAO ndao = new NoticeDAO();
		Notice notice =new Notice();
		ServletContext context = getServletContext();
		String savePath = "/data";
		String uploadFilePath = context.getRealPath(savePath);
		int uploadFileSizeLimit = 10 * 1024 * 1024 ; 
		String encType = "UTF-8";
		DefaultFileRenamePolicy nDFRP = new DefaultFileRenamePolicy();
		System.out.println("지정된 업로드 디렉토리 :" +savePath);
		System.out.println("서버상의 실제 업로드 되는 디렉토리 :" +uploadFilePath);
		
		int idx=0;
		String title="";
		String content="";
		String fileName="";
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, uploadFileSizeLimit, encType, nDFRP);
			fileName = multi.getFilesystemName("file");
			if(fileName == null){
				System.out.println("업로드된 첨부파일이 없습니다.");
			}
			idx = Integer.parseInt(multi.getParameter("idx")); //일반 request로하면 이미 response를해서 값이없음으로 나온다 그러므로 멀티파트리퀘스트를 써야한다
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			

		} catch (Exception e) {
			System.out.println("예외발생 : "+e);
		}
		notice.setIdx(idx);notice.setTitle(title);notice.setContent(content);notice.setFile1(fileName);
		ndao.updateNotice(notice);

		response.sendRedirect("NoticeList.do");
	}

}
