package com.Password.controller;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ForgotPassword
 */
@WebServlet("/forgotPassword")
public class ForgotPassword extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		RequestDispatcher dispatcher = null;
		String otpvalue = "";
		HttpSession mySession = request.getSession();
		
		if(email!=null || !email.equals("")) {
			// sending otp
			Random rand = new Random();
			for(int i=0;i<5;i++) {
			otpvalue = otpvalue+Integer.toString(rand.nextInt(9));
			}

			String to = email;// change accordingly
			// Get the session object
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("", "");// Put your email
																									// id and
																									// password here
				}
			});
			// compose message
			try {
				MimeMessage message = new MimeMessage(session);
				message.setFrom(new InternetAddress(email));// change accordingly
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
				message.setSubject("7Tour旅遊網密碼驗證信");
				message.setText("會員:"+email+"\n你好"+"您的驗證碼為: " + otpvalue+"\n"+"請於30分鐘之內盡快更新個人密碼\n"+"以免權益遭致虧損，7Tour旅遊網感謝您~");
				// send message
				Transport.send(message);
			}

			catch (MessagingException e) {
				throw new RuntimeException(e);
			}
			dispatcher = request.getRequestDispatcher("/front-end/member/EnterOtp.jsp");
			request.setAttribute("message","驗證碼已傳送至您的電子信箱");
			//request.setAttribute("connection", con);
			mySession.setAttribute("otp",otpvalue); 
			mySession.setAttribute("email",email); 
			dispatcher.forward(request, response);
			//request.setAttribute("status", "success");
		}
		
	}

}
