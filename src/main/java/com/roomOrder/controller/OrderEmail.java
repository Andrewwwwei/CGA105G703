package com.roomOrder.controller;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class OrderEmail {
	
	// 設定傳送郵件:至收信人的Email信箱,Email主旨,Email內容
		public void sendMail(String to, String subject, String messageText) {
				
		   try {
			   // 設定使用SSL連線至 Gmail smtp Server
			   Properties props = new Properties();
			   props.put("mail.smtp.host", "smtp.gmail.com");
			   props.put("mail.smtp.socketFactory.port", "465");
			   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			   props.put("mail.smtp.auth", "true");
			   props.put("mail.smtp.port", "465");
			   // 設定發送者
			   final String myGmail = "";
			     final String myGmail_password = "";
				   Session session = Session.getInstance(props, new Authenticator() {
					   protected PasswordAuthentication getPasswordAuthentication() {
						   return new PasswordAuthentication(myGmail, myGmail_password);
					   }
				   });

				   Message message = new MimeMessage(session);
				   message.setFrom(new InternetAddress(myGmail));
				   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
				  
				   //設定信中的主旨  
				   message.setSubject(subject);
				   //設定信中的內容 
				   message.setContent(messageText, "text/html; charset=UTF-8");

				   Transport.send(message);
		     }catch (MessagingException e){
			     System.out.println("傳送失敗!");
			     e.printStackTrace();
		     }
		   }
			
}
