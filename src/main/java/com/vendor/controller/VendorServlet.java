package com.vendor.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Users.model.UsersService;
import com.Users.model.UsersVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;

@WebServlet("/Vendor")
public class VendorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		if ("vendorLogin".equals(action)) { // 廠商登入
			String venId = req.getParameter("venId");
			String password = req.getParameter("password");
			VendorService vendorService = new VendorService();
			VendorVO vendorVO = null;
			vendorVO = vendorService.getOneVendor(Integer.parseInt(venId));
			if(vendorVO == null) {
				req.setAttribute("errorMsgs", "帳號不存在");
				RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
				failureView.forward(req, res);
				return;
			}
			if (password.equals(vendorVO.getVenPw())) {
				req.getSession().setAttribute("venId", Integer.parseInt(venId));
				RequestDispatcher success = req.getRequestDispatcher("/RoomOrder?action=toVendorIndex");
				success.forward(req, res);
			} else {
				req.setAttribute("errorMsgs", "密碼錯誤");
				RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if ("vendorLogout".equals(action)) { // 廠商登出
			req.getSession().removeAttribute("venId");
			res.sendRedirect(req.getContextPath() + "/vendor/VenLogin.jsp");

		}
	}

}
