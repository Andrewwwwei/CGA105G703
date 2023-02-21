package com.act.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.Users.model.UsersVO;
import com.actMessage.model.ActMessageVO;
import com.actReport.model.*;
import com.actReport.model.ActReportService;

import oracle.sql.INTERVALDS;



@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 4 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/back-end/report/actReport.do")

public class ActReportServlet extends HttpServlet {

	ActReportService actReportSvc = new ActReportService();

	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");


		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				String str = req.getParameter("activityReportId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入檢舉揪團明細編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_actReportHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer activityReportId = null;
				try {
					activityReportId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("檢舉揪團明細編號不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_actReportHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				ActReportVO actReportVO = actReportSvc.getOneActReport(activityReportId);
				if (actReportVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/report/select_actReportHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actReportVO", actReportVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/report/listOneReport.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer activityReportId = Integer.valueOf(req.getParameter("activityReportId"));
				
				/***************************2.開始查詢資料****************************************/
				ActReportVO actReportVO = actReportSvc.getOneActReport(activityReportId);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("actReportVO", actReportVO);         // 資料庫取出的empVO物件,存入req
				String url = "/back-end/report/update_report_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
				successView.forward(req, res);
		}
		
//						揪團檢舉新增
		if ("actReportInsert".equals(action)) { // 來自addEmp.jsp的請求
			if(req.getSession().getAttribute("usersVO") == null){
				req.getRequestDispatcher("/front-end/member/login.jsp").forward(req, res);
				return;
			}
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO"); 
			Integer reportUserId = usersVO.getUserId();
			
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());
			
//			Integer reportUserId = Integer.valueOf(req.getParameter("reportUserId").trim());
			
			String reportContent = req.getParameter("reportContent").trim();
			
			Integer reportMatter = Integer.valueOf(req.getParameter("reportMatter").trim());


			
			
			ActReportVO actReportVO = new ActReportVO();
			actReportVO.setActivityId(activityId);
			actReportVO.setEmpNo(Integer.valueOf(300001));
			actReportVO.setReportUserId(reportUserId);
			actReportVO.setReportContent(reportContent);
			actReportVO.setReportStatus(0);
			actReportVO.setReportMatter(reportMatter);
			
			LocalDateTime now = LocalDateTime.now();
			actReportVO.setReportTime(now);
			actReportVO.setReportFinishTime(now);


			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actReportVO", actReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actHomePage.jsp");
					failureView.forward(req, res);
					return;
				}

			/*************************** 2.開始新增資料 ***************************************/
			
			try {
				actReportSvc.addActReport(actReportVO);
			}catch(RuntimeException e){
				
				e.printStackTrace();
				req.setAttribute("actReportVO", actReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/actHomePage.jsp");
				failureView.forward(req, res);
				return;
			};
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/act/actHomePage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交actHomePage.jsp
			successView.forward(req, res);

		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/	
			Integer activityReportId = Integer.valueOf(req.getParameter("activityReportId").trim());
			
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());
			Integer reportUserId = Integer.valueOf(req.getParameter("reportUserId").trim());
			
			Integer empNo = Integer.valueOf(req.getParameter("empNo").trim());
			
			String reportContent = req.getParameter("reportContent").trim();
			
			Integer reportStatus = Integer.valueOf(req.getParameter("reportStatus").trim());
			
			
			Integer reportMatter = Integer.valueOf(req.getParameter("reportMatter").trim());
		
			
			ActReportVO actReportVO = new ActReportVO();
			actReportVO.setActivityReportId(activityReportId);
			actReportVO.setActivityId(activityId);
			actReportVO.setReportUserId(reportUserId);
			actReportVO.setEmpNo(empNo);
			actReportVO.setReportContent(reportContent);
			actReportVO.setReportStatus(reportStatus);
			actReportVO.setReportMatter(reportMatter);
			
			LocalDateTime now = LocalDateTime.now();
			actReportVO.setReportTime(now);
			DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String reportTimeStr2 = now.format(format);
			actReportVO.setReportTimeStr(reportTimeStr2);
			

			LocalDateTime now2 = LocalDateTime.now();
			actReportVO.setReportFinishTime(now2);
			DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
			String reportFinishTimeStr = now.format(format2);
			actReportVO.setReportFinishTimeStr(reportFinishTimeStr);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actReportVO", actReportVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/report/update_report_input.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始修改資料 *****************************************/
			actReportSvc.updateActReport(actReportVO.getActivityReportId(), actReportVO.getActivityId(), actReportVO.getReportUserId(), actReportVO.getEmpNo(), actReportVO.getReportContent(), actReportVO.getReportStatus(), now, reportMatter, now2);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actReportVO", actReportVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/report/listOneReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer activityReportId = Integer.valueOf(req.getParameter("activityReportId"));
			/*************************** 2.開始刪除資料 ***************************************/
			actReportSvc.deletActReport(activityReportId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/report/listAllReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
