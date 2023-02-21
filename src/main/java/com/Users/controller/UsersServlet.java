package com.Users.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import com.Mes.model.MesService;
import com.Users.model.UsersService;
import com.Users.model.UsersVO;

@MultipartConfig
@WebServlet("/usersServlet")
public class UsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
//====================================================================================

		if ("login".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			String userAccount = req.getParameter("userAccount");// 來自login.jsp的請求
			String password = req.getParameter("password");
			UsersService userSvc = new UsersService();

			if (userSvc.login(userAccount, password) == true) {
				if (userSvc.findStatusByUserAccount(userAccount) == 0) {
					UsersService userSvc1 = new UsersService();
					UsersVO usersVO = userSvc1.getOneUserEmail(userAccount);
					req.getSession().setAttribute("usersVO", usersVO);
					RequestDispatcher success = req.getRequestDispatcher("/front-end/index.jsp");
					success.forward(req, res);
				} else {
					errorMsgs.add("您的帳戶已停權限制");
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
					failureView.forward(req, res);
					return;
				}
			} else {
				req.getRequestDispatcher("/front-end/member/loginFail.html").forward(req, res);
			}
//====================================================================================

		}
		if ("loginout".equals(action)) {// 來自loginout.jsp的請求
			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");

		}
//====================================================================================
		if ("addMember".equals(action)) {
			UsersService userSvc1 = new UsersService();
			String userAccount = req.getParameter("userAccount");// 來自addMemner.jsp的請求
			String userPassword = req.getParameter("userPassword");
			String userName = req.getParameter("userName");
			String userNickname = req.getParameter("userNickname");
			String userIdNo = req.getParameter("userIdNo");
			java.sql.Date userBrthday = java.sql.Date.valueOf(req.getParameter("userBrthday"));
			String userPhone = req.getParameter("userPhone");
			String userAddress = (req.getParameter("city") + req.getParameter("dist") + req.getParameter("Address"));
			String userGender = req.getParameter("userGender");
			Part part = req.getPart("userPic");
			InputStream in = part.getInputStream();
			byte[] userPic = new byte[in.available()];
			in.read(userPic);
			in.close();
			byte userCertificationStatus = (byte) 0;
			byte userStatus = (byte) 0;
			byte userForumLevel = (byte) 0;
			byte userShopLevel = (byte) 0;
			Integer bonusPoints = 0;
			Float alltogetherScore = (float) 0.0;
			Integer alltogetherScorePeople = 0;
			Integer artCount = 0;
			Integer likeTotalCount = 0;
			Integer purchaseTotal = 0;
			Byte reportTotalCount = (byte) 0;
			userSvc1.addUser(userAccount, userPassword, userName, userNickname, userIdNo, userBrthday, userPhone,
					userAddress, userCertificationStatus, userStatus, userGender, userForumLevel, userPic,
					userShopLevel, bonusPoints, alltogetherScore, alltogetherScorePeople, artCount, likeTotalCount,
					purchaseTotal, reportTotalCount);
			req.getSession().setAttribute("userAccount", userAccount);
			RequestDispatcher success = req.getRequestDispatcher("/front-end/member/newUserMes.jsp");
			success.forward(req, res);

		}
		// ====================================================================================
		if ("NewuserMail".equals(action)) { // 來自newUserMes.jsp
			/*************************** 1.接收請求參數 ***************************************/
			String userAccount = req.getParameter("userAccount");
			Long datetime = System.currentTimeMillis();
	        Timestamp timestamp = new Timestamp(datetime);
			/*************************** 2.開始刪除資料 ***************************************/
	        UsersService userSvc = new UsersService();
			Integer userId = userSvc.findIdByUserAccount(userAccount);
			MesService mesSvc = new MesService();
			mesSvc.addMesVO(userId, "歡迎新用戶", "限定兔年人氣商品新上架，讓兔子跳躍之姿充滿活力的形象，祝福每一個人「今年順利成功」。", null, timestamp, (byte)1);
			
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/insertOk.jsp");
			successView.forward(req, res);
		}
		// ====================================================================================
		if ("userInfo".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處裡 **********************/
			String userAccount = req.getParameter("userAccount");// 來自userInfo.jsp的請求
			String userNickname = req.getParameter("userNickname");
			String userPhone = req.getParameter("userPhone");
			Part part = req.getPart("userPic");
			InputStream in = part.getInputStream();
			byte[] userPic = new byte[in.available()];
			in.read(userPic);
			in.close();

			if (userNickname == null | (userNickname.trim()).length() == 0) {
				errorMsgs.add("請輸入匿名");
				res.sendRedirect("/front-end/member/login.jsp");
				return;
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/login.jsp");
				failureView.forward(req, res);
				return;
			}
			UsersService userSvc = new UsersService();
			UsersVO usersVO = userSvc.getOneUserEmail(userAccount);
			userSvc.updateUserInfo(userNickname, userPhone, userPic, userAccount);
			req.getSession().setAttribute("usersVO", usersVO);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/member/updateOk.jsp");
			successView.forward(req, res);
		}
		// ====================================================================================
		if ("updatePassword".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處裡 **********************/
			String originalUserPassword = req.getParameter("originalUserPassword");// 來自updatePassword.jsp的請求
			String againNewPassword = req.getParameter("againNewPassword");
			String userAccount = req.getParameter("userAccount");
			UsersService userSvc = new UsersService();
			userSvc.UpdatePassword(againNewPassword,originalUserPassword,userAccount);
//			req.getSession().invalidate();
			res.sendRedirect(req.getContextPath() + "/front-end/member/updatePasswordOk.jsp");
			return;
			}
		// ====================================================================================
		if ("deleteMes".equals(action)) { // 來自index.jsp
			/*************************** 1.接收請求參數 ***************************************/
			Integer mesId = Integer.valueOf(req.getParameter("mesId"));
			/*************************** 2.開始刪除資料 ***************************************/
			MesService mesSvc = new MesService();
			mesSvc.deleteMes(mesId);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/index.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		if ("changeColor".equals(action)) { // 來自index.jsp
			/*************************** 1.接收請求參數 ***************************************/
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			/*************************** 2.開始刪除資料 ***************************************/
			MesService mesSvc = new MesService();
			mesSvc.updateReadMesVO(userId);
			/*************************** 3.更新完成,準備轉交(Send the Success view) ***********/
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/index.jsp");// 更新成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
		if("getUserPic".equals(action)) {
			res.setContentType("image/jpeg");
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			UsersService usersService = new UsersService();
			UsersVO usersVO = usersService.getOneUser(userId);
			byte[] userPic = usersVO.getUserPic();
			try(ServletOutputStream out = res.getOutputStream()){
				out.write(userPic);
			}catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}

}
