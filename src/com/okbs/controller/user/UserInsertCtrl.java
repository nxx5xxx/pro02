package com.okbs.controller.user;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crypto.util.AES256;
import com.okbs.dto.User1;
import com.okbs.model.UserDAO;


@WebServlet("/UserInsert.do")
public class UserInsertCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8" );
		UserDAO userDao = new UserDAO();
		User1 user = new User1();
		user.setId(request.getParameter("id"));
		String key = "%04x";
		String pw= "";
		try {
			pw = AES256.encryptAES256(request.getParameter("pw"), key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		user.setPw(pw);
		user.setName(request.getParameter("name"));
		user.setTel(request.getParameter("tel"));
		String addr = "("+request.getParameter("postcode")+")  "+request.getParameter("addr1")+"  , "+request.getParameter("addr2");
		user.setAddress(addr);
		user.setEmail(request.getParameter("email"));
		userDao.insertUser(user);
		response.sendRedirect("GoLogin.do");
		
		/*
		 회원가입축하메일 정상작동하나 git에 올리기위해 각주처리함
		 	try {
			naverMailSend(request, response);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}


public static void naverMailSend(HttpServletRequest request, HttpServletResponse response) throws AddressException, MessagingException{
	String host = "smtp.naver.com";
	String id = ""; //"smtp_nxx5xxx@naver.com";
	String pw = "";// 패스워드
	Properties props = new Properties();
	props.put("mail.smtp.host", host);
	props.put("mail.smtp.port", 465);
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.ssl.enable", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.debug", "true");
    String recipient = request.getParameter("email");
    String subject = "회원 가입 축하 이메일";
    String body = "회원 가입을 축하드립니다.";
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(id, pw);
        }
    });
    session.setDebug(true);
    Message mimeMessage = new MimeMessage(session);
    mimeMessage.setFrom(new InternetAddress(id));
    mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
    mimeMessage.setSubject(subject);
    mimeMessage.setText(body);
    Transport.send(mimeMessage);*/
}
}
