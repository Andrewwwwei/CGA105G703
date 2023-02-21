package com.Password.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidateOtp
 */
@WebServlet("/ValidateOtp")
public class ValidateOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String value=request.getParameter("otp");
		HttpSession session=request.getSession();
		String otp=(String) session.getAttribute("otp");
		RequestDispatcher dispatcher=null;
		
		
		if (value.equals(otp)) 
		{
			
				request.setAttribute("email", request.getParameter("email"));
				request.setAttribute("status", "密碼成功更新");
			  dispatcher=request.getRequestDispatcher("/front-end/member/newPassword.jsp");
			dispatcher.forward(request, response);
			
		}
		else
		{
			request.setAttribute("message","驗證碼錯誤，請重新輸入");
			
		   dispatcher=request.getRequestDispatcher("/front-end/member/EnterOtp.jsp");
			dispatcher.forward(request, response);
		
		}
		
	}

}
