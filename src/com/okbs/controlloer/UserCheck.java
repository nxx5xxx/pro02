package com.okbs.controlloer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.okbs.model.UserDAO;

/**
 * Servlet implementation class UserCheck
 */
@WebServlet("/UserCheck.do")
public class UserCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO userdao = new UserDAO();
		int sw=0;
		String id = request.getParameter("id");
		boolean result = false;
		sw = userdao.idCheck(id);
		//sw가 1이면 있는아이디(rs.next()로 검색되므로))
		if(sw>=1){
			result=false;
		}else{
			result=true;
		}
		JSONObject json = new JSONObject();
		json.put("result", result);
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}



}
