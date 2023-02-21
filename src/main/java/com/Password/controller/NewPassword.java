package com.Password.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Users.model.UsersService;
import com.Users.model.UsersVO;

/**
 * Servlet implementation class NewPassword
 */
@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String newPassword = request.getParameter("password");
		String confPassword = request.getParameter("confPassword");
		RequestDispatcher dispatcher = null;
		if (newPassword != null && confPassword != null && newPassword.equals(confPassword) && newPassword.trim().length() != 0 && confPassword.trim().length() != 0) {

			try {
				UsersService userSvc = new UsersService();
				UsersVO usersVO = userSvc.ResetPassword(newPassword,(String) session.getAttribute("email"));
					request.setAttribute("statusSuccess", "修改密碼成功，請使用新密碼登入");
					RequestDispatcher success = request.getRequestDispatcher("/front-end/member/login.jsp");
					success.forward(request, response);
					return;
				} catch (Exception e) {
				
				e.printStackTrace();
			}
		}
		if(newPassword.equals(null) || confPassword.equals(null) || newPassword.trim().length() == 0 || confPassword.trim().length() == 0) {
		request.setAttribute("statusfail", "修改密碼失敗，密碼欄不可留空");
		RequestDispatcher success = request.getRequestDispatcher("/front-end/member/newPassword.jsp");
		success.forward(request, response);
		return;
		
		}
		if(!(newPassword.equals(confPassword))) {
			request.setAttribute("statusfail","修改密碼失敗，兩欄密碼必須一樣");
			RequestDispatcher success = request.getRequestDispatcher("/front-end/member/newPassword.jsp");
			success.forward(request, response);
			return;
			}	
	}

}
