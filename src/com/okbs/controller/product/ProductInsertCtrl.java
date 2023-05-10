package com.okbs.controller.product;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.okbs.dto.Product;
import com.okbs.model.ProductDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@WebServlet("/ProductInsert.do")
public class ProductInsertCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ProductDAO prodao =new ProductDAO();
		Product product = new Product();
		String img1 = "";
		String img2 = "";
		String savePath = "/img/proimg";
		ServletContext context = getServletContext();
		String uploadFilePath = context.getRealPath(savePath);
		try{
		MultipartRequest multi = new MultipartRequest(request,uploadFilePath,10485760,"UTF-8",new DefaultFileRenamePolicy());
		img1 = multi.getFilesystemName("img");
		img2 = multi.getFilesystemName("img2");
		product.setPname(multi.getParameter("pname"));
		product.setPrice(Integer.parseInt(multi.getParameter("price")));
		product.setPdesc(multi.getParameter("pdesc"));
		product.setPamount(Integer.parseInt(multi.getParameter("pamount")));
		product.setCcode(multi.getParameter("ccode"));
		product.setImg(img1);
		product.setImg2(img2);
		}catch(Exception e){
			System.out.println("예외발생"+e);
		}
		prodao.insertProduct(product);
		
		response.sendRedirect("ProductList.do?ccode=00");
		
	}

}
