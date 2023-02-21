package com.act.controller;

import java.io.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.Mes.model.MesService;
import com.Mes.model.MesVO;
import com.Users.model.UsersVO;
import com.act.model.ActService;
import com.act.model.ActVO;
import com.actMember.model.ActMemberService;
import com.actMember.model.ActMemberVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 4 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/actMember.do")
public class ActMemberServlet extends HttpServlet{

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
				String str = req.getParameter("userId");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_memberHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				String str2 = req.getParameter("activityId");
				if (str2 == null || (str2.trim()).length() == 0) {
					errorMsgs.add("請輸入會員編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_memberHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer userId = null;
				try {
					userId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_memberHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				Integer activityId = null;
				try {
					activityId = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("會員編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_memberHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				ActMemberService actMemberSvc = new ActMemberService();

				ActMemberVO actMemberVO = actMemberSvc.getOneActMember(userId, activityId);
				if (actMemberVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_memberHome.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				/***************************3.查詢完成,準備轉交(Send the Success view)*************/
				req.setAttribute("actMemberVO", actMemberVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/member/select_memberHome.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
				successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求
			System.out.println("1");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/***************************1.接收請求參數****************************************/
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			Integer activityId = Integer.valueOf(req.getParameter("userId"));
			System.out.println("2");
			
			/***************************2.開始查詢資料****************************************/
			ActMemberService actMemberSvc = new ActMemberService();

			ActMemberVO actMemberVO = actMemberSvc.getOneActMember(userId, activityId);
				
			/***************************3.查詢完成,準備轉交(Send the Success view)************/
			req.setAttribute("actMemberVO", actMemberVO);         // 資料庫取出的empVO物件,存入req
			String url = "/back-end/member/update_member_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
			
		if ("actMemberInsert".equals(action)) { // 來自addEmp.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
//			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			
//			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());
			
//			String activityNotice = req.getParameter("activityNotice").trim();

			String evaluationContent = req.getParameter("evaluationContent").trim();
	
			String str = req.getParameter("evaluationScoreNember"); //請求一個評價分數的參數
			Integer evaluationScoreNember = Integer.valueOf(str);// 評價分數
			Integer evaluationScore = Integer.valueOf(req.getParameter("evaluationScoreNember").trim());
			
			ActMemberVO actMemberVO = new ActMemberVO();
			LocalDateTime now = LocalDateTime.now();
			actMemberVO.setUserId(2);
			actMemberVO.setActivityId(47);
//			actMemberVO.setActivityNotice(activityNotice);
			actMemberVO.setEvaluationContent(evaluationContent);
			actMemberVO.setEvaluationScore(evaluationScoreNember);
			actMemberVO.setEvaluationTime(now);


			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actMemberVO", actMemberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}

			/*************************** 2.開始新增資料 ***************************************/
				ActMemberService actMemberSvc = new ActMemberService();

			try {
				actMemberVO = actMemberSvc.addActMember(evaluationScore, "activityNotice", evaluationContent, evaluationScoreNember, now, evaluationScore);
					
			}catch(RuntimeException e){
				
				errorMsgs.add("新增失敗");
			};
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actMemberVO", actMemberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		
		
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());
			
			String activityNotice = req.getParameter("activityNotice").trim();
			
			String evaluationContent = req.getParameter("evaluationContent").trim();
	
			Integer evaluationScore = Integer.valueOf(req.getParameter("evaluationScoreNember").trim());
			
			Integer memberStatus = Integer.valueOf(req.getParameter("memberStatus").trim());
			
			ActMemberVO actMemberVO = new ActMemberVO();
			actMemberVO.setUserId(userId);
			actMemberVO.setActivityId(activityId);
			actMemberVO.setActivityNotice(activityNotice);
			actMemberVO.setEvaluationContent(evaluationContent);
			actMemberVO.setEvaluationScore(evaluationScore);
			actMemberVO.setEvaluationTime(LocalDateTime.now());
			actMemberVO.setMemberStatus(memberStatus);
			
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actMemberVO", actMemberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/addMember.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			ActMemberService actMemberSvc = new ActMemberService();

			actMemberVO = actMemberSvc.updateActMember(actMemberVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actMemberVO", actMemberVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/member/listOneMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			/*************************** 2.開始刪除資料 ***************************************/
			ActMemberService actMemberSvc = new ActMemberService();

			actMemberSvc.deletActMember(userId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/member/listAllMember.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
	
////通知團主確認加入或拒絕揪團成員 已寫到ActServlet的applyJoin裡
//if ("NoticeMember".equals(action)) { // 來自actHomePage.jsp 揪團首頁的請求 成功進到單一瀏覽頁面
//	List<String> errorMsgs = new LinkedList<String>();
//	// Store this set in the request scope, in case we need to
//	// send the ErrorPage view.
//	req.setAttribute("errorMsgs", errorMsgs);
//	/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
//	
////	加入成員
//					
//
//	Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());			
//
//
//	ActMemberVO actMemberVO = new ActMemberVO();
//	LocalDateTime now = LocalDateTime.now();
////	actMemberVO.setUserId(((UsersVO)req.getSession().getAttribute("usersVO")).getUserId());
//	actMemberVO.setUserId(1);
//	actMemberVO.setActivityId(activityId);
//	actMemberVO.setEvaluationTime(now);
//	actMemberVO.setMemberStatus(0);
////	新增通知
//
//	Integer userId = Integer.valueOf(req.getParameter("userId").trim());
//	MesVO mesVO = new MesVO();
//	Timestamp now2 = new Timestamp(System.currentTimeMillis());
//	
//	
//	/*************************** 2.開始新增資料 ***************************************/
//	ActMemberService actMemberSvc = new ActMemberService();
//	MesService mesSvc = new MesService();
//	
//	try {
//		actMemberSvc.addActMember(actMemberVO);
//		mesSvc.addMesVO(userId, "揪團申請", "您的揪團有人申請，請至揪團頁面審核申請", null, now2, (byte)1);
//	}catch(RuntimeException e) {
//		
//		e.printStackTrace();
//		errorMsgs.add("新增失敗 : 沒有此會員");
//		
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("actMemberVO", actMemberVO); // 含有輸入格式錯誤的empVO物件,也存入req
//			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/member/select_memberHome.jsp");
//			failureView.forward(req, res);
//			return;
//		}
//		
//	}
//	/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
//	String url = "/back-end/member/select_memberHome.jsp";
//	RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
//	successView.forward(req, res);
//	return;
//	
//	
//}	
}
