package com.act.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.act.model.ActService;
import com.act.model.ActVO;
import com.actMessage.model.ActMessageService;
import com.actMessage.model.ActMessageVO;
import com.Mes.model.MesService;


@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 4 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/back-end/message/actMessage.do")

public class ActMessageServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		ActMessageService actMessageSvc = new ActMessageService();


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("messageId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入留言編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_messageHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer messageId = null;
				try {
					messageId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("留言編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_messageHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ActMessageVO actMessageVO = actMessageSvc.getOneActMessage(messageId);
				if (actMessageVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/message/select_messageHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actMessageVO", actMessageVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/message/listOneMessage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer messageId = Integer.valueOf(req.getParameter("messageId"));
				
				/***************************2.開始查詢資料****************************************/
				ActMessageVO actMessageVO = actMessageSvc.getOneActMessage(messageId);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actMessageVO", actMessageVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/message/update_message_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
		
		if ("actMessageInset".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());
			
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			
			String messageContent = req.getParameter("messageContent").trim();
		
			ActMessageVO actMessageVO = new ActMessageVO();
			LocalDateTime now = LocalDateTime.now();
			actMessageVO.setActivityId(activityId);
			actMessageVO.setUserId(userId);
			actMessageVO.setMessageContent(messageContent);
			actMessageVO.setMessageContentTime(now);


			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actMessageVO", actMessageVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/addMessage.jsp");
					failureView.forward(req, res);
					return;
				}

			/*************************** 2.開始新增資料 ***************************************/
			
			try {
				actMessageVO = actMessageSvc.addActMessage(activityId,userId,messageContent,now);
					
			}catch(RuntimeException e){
				
				errorMsgs.add("新增失敗");
			};
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actMessageVO", actMessageVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/addMessage.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/message/listAllMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/	
			
			Integer messageId = Integer.valueOf(req.getParameter("messageId").trim());
			
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());
			
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			
			String messageContent = req.getParameter("messageContent").trim();
			
			ActMessageVO actMessageVO = new ActMessageVO();
			actMessageVO.setMessageId(messageId);
			actMessageVO.setActivityId(activityId);
			actMessageVO.setUserId(userId);
			actMessageVO.setMessageContent(messageContent);
			
			LocalDateTime now = LocalDateTime.now();
			actMessageVO.setMessageContentTime(now);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String messageContentTimeStr = now.format(format);
			actMessageVO.setMessageContentTimeStr(messageContentTimeStr);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actMessageVO", actMessageVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/message/addMessage.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			actMessageVO = actMessageSvc.updateActMessage(actMessageVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actMessageVO", actMessageVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/message/listOneMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer messageId = Integer.valueOf(req.getParameter("messageId"));
			/*************************** 2.開始刪除資料 ***************************************/
			actMessageSvc.deletActMessage(messageId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/message/listAllMessage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
