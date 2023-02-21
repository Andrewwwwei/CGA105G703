package com.act.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.sql.*;
import java.time.LocalDateTime;

import com.Mes.model.MesService;
import com.Mes.model.MesVO;
import com.act.model.*;
import com.Users.model.*;
import com.actMember.model.ActMemberService;
import com.actMember.model.ActMemberVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 4 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/back-end/act/act.do")
public class ActServlet extends HttpServlet {

	private static final byte[] ActivityTheCover = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//System.out.println(action);

//		團主允許會員加入此揪團
		if("UserJoin".equals(action)) {
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO"); 
//			Integer userId = usersVO.getUserId();
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			Integer activityId = Integer.valueOf(req.getParameter("activityId"));
			//////////////////更改揪團成員狀態
			ActMemberService actMemberService = new ActMemberService();
			// 從ActMemberVO去getUserId與activityId取得參數值

			ActMemberVO actMemberVO = actMemberService.getOneActMember(userId, activityId);
			// MemberStatus Set的為已加入
			actMemberVO.setMemberStatus(1);
			actMemberVO.setEvaluationScore(1);
			
			// 去MemberService更新資料
			actMemberService.updateActMember(actMemberVO);
			
//			新增MesInsert會員訊息通知

			Timestamp now2 = new Timestamp(System.currentTimeMillis());

			/*************************** 2.加入揪團資料 ***************************************/
//			新增MemberInsert與MesInsert
			MesService mesSvc = new MesService();
//			新增MesInsert
			mesSvc.addMesVO(userId, "揪團申請成功", "您的揪團申請已通過，可至揪團頁面查看詳情", null, now2, (byte)1); // (byte)1 為未讀
			
			/////////////////準備導向頁面的資料
			ActService actService = new  ActService();
			ActVO actVO = actService.getOneAct(activityId);
			List<ActMemberVO> actMemberVOList = actMemberService.getThisActMembers(activityId);
			
	
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			 req.setAttribute("actVO", actVO);
			 req.setAttribute("actMemberVOList", actMemberVOList);
			 String url = "/front-end/member/memberCheck.jsp"; // 查詢成功後轉交memberCheck.jsp
			 RequestDispatcher successView = req.getRequestDispatcher(url); 
			 successView.forward(req, res);
		}
		
//		團主拒絕會員加入此揪團
		if("UserNoJoin".equals(action)) {

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			Integer activityId = Integer.valueOf(req.getParameter("activityId"));
			//////////////////更改揪團成員狀態
			ActMemberService actMemberService = new ActMemberService();
			// 從ActMemberVO去getUserId與activityId取得參數值
			ActMemberVO actMemberVO = actMemberService.getOneActMember(userId, activityId); 
			// MemberStatus Set的為已拒絕
			actMemberVO.setMemberStatus(2);
			actMemberService.updateActMember(actMemberVO);
//			新增MesInsert會員訊息通知
			Timestamp now2 = new Timestamp(System.currentTimeMillis());

			/*************************** 2.團主拒絕此成員加入揪團資料 ***************************************/

			MesService mesSvc = new MesService();
//			新增MesInsert
			mesSvc.addMesVO(userId, "揪團申請失敗", "您的揪團申請已被團主拒絕，可至揪團頁面查看詳情", null, now2, (byte)1);// (byte)1 為未讀
			/////////////////準備導向頁面的資料
			ActService actService = new  ActService();
			ActVO actVO = actService.getOneAct(activityId);
			List<ActMemberVO> actMemberVOList = actMemberService.getThisActMembers(activityId);
			
	
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			 req.setAttribute("actVO", actVO);
			 req.setAttribute("actMemberVOList", actMemberVOList);
			 String url = "/front-end/member/memberCheck.jsp"; // 查詢成功後轉交memberCheck.jsp
			 RequestDispatcher successView = req.getRequestDispatcher(url); 
			 successView.forward(req, res);
		}
		
//		申請加入揪團列表:用會員編號搜尋自己參加的揪團
		if("getMyJoinAct".equals(action)) {
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

//			取得會員編號userSId
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO"); 
			if(usersVO == null) {
				req.getRequestDispatcher("/front-end/member/login.jsp").forward(req, res);
				return;
			}
			Integer userId = usersVO.getUserId();
			
		    /*************************** 2.開始查詢資料 *****************************************/

			ActMemberService actMemberService = new ActMemberService();
//			從MemSvc取的usersId 再放進listMemVO裡
			List<ActMemberVO> actMemberVOList = actMemberService.getByUserId(userId);
//			從memVOList撈到ActId
			List<ActVO> actVOList = new ArrayList<ActVO>();
			ActService actService = new ActService();
			
			for(ActMemberVO memVO : actMemberVOList) {
				ActVO actVO = actService.getOneAct(memVO.getActivityId());
				actVOList.add(actVO);
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actVOList", actVOList);
			String url = "/front-end/member/memberMyJoinAct.jsp"; // 查詢成功後轉交memberMyJoinAct.jsp
		    RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		
//		我創建的揪團:用團主的會員編號搜尋開的揪團 memberHome.jsp
		if("getLeaderAct".equals(action)) {

			   /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				
				UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO"); 
				if(usersVO == null) {
					req.getRequestDispatcher("/front-end/member/login.jsp").forward(req, res);
					return;
				}
				Integer userId = usersVO.getUserId();
			   
			    /*************************** 2.開始查詢資料 *****************************************/

				   ActService actSvc = new ActService();
				   List<ActVO> actVOList = actSvc.getLeaderAct(userId);
				   
				   
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				 req.setAttribute("actVOList", actVOList);
				 res.setContentType("image/jpeg");
				 String url = "/front-end/member/memberHome.jsp"; // 查詢成功後轉交memberHome.jsp
				 RequestDispatcher successView = req.getRequestDispatcher(url); 
				 successView.forward(req, res);
		}
//				我的揪團列表 團主查看單一揪團 memberHome.jsp
		if("getMemberCheck".equals(action)) {

			   /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			   Integer activityId = null;
			   try {
				   activityId = Integer.valueOf(req.getParameter("activityId"));
			   } catch (NumberFormatException e) {
			    e.printStackTrace();
			   }
			   
			    /*************************** 2.開始查詢資料 *****************************************/
			   ActService actService = new ActService();
			   ActVO actVO = actService.getOneAct(activityId);
			   ActMemberService actMemberService = new ActMemberService();
			   List<ActMemberVO> actMemberVOList = actMemberService.getThisActMembers(activityId);
				   
				   
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			 req.setAttribute("actVO", actVO);
			 req.setAttribute("actMemberVOList", actMemberVOList);
			 String url = "/front-end/member/memberCheck.jsp"; // 查詢成功後轉交memberCheck.jsp
			 RequestDispatcher successView = req.getRequestDispatcher(url); 
			 successView.forward(req, res);
		}
		
		if ("SelectOneAct".equals(action)) { // 來自actHomePage.jsp 揪團首頁的請求 成功進到單一瀏覽頁面
			
			   /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			  	// 取得揪團的ID 
			   Integer activityId = null;
			   try {
				   activityId = Integer.valueOf(req.getParameter("activityId"));
			   } catch (NumberFormatException e) {
			    e.printStackTrace();
			   }
			
			    /*************************** 2.開始查詢資料 *****************************************/
			   if (activityId != null) {
				ActService actSvc = new ActService();
				 // 依照揪團ID編號選取單一揪團頁面
			    ActVO actVO = actSvc.getOneActContent(activityId);

			    /*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			    req.setAttribute("actVO", actVO);
			    String url = "/front-end/act/actContent.jsp"; // 轉交單一揪團頁面
			    req.getRequestDispatcher(url).forward(req, res);
			    
			   }
		}
//		actContent揪團團主確認加入或拒絕轉跳至memberCheck.jsp 
		if("applyJoin".equals(action)) {
			if(req.getSession().getAttribute("usersVO") == null){
				req.getRequestDispatcher("/front-end/member/login.jsp").forward(req, res);
				return;
			}
	
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			新增MemberInsert成員 申請加入與通知團主確認加入或拒絕揪團成員	
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());			
			


//			actMemberVO				
			ActMemberVO actMemberVO = new ActMemberVO();
			LocalDateTime now = LocalDateTime.now();
			actMemberVO.setUserId(((UsersVO)req.getSession().getAttribute("usersVO")).getUserId());
			actMemberVO.setActivityId(activityId);
			actMemberVO.setEvaluationTime(now);
			actMemberVO.setMemberStatus(0);

//			新增MesInsert會員訊息通知

			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			Timestamp now2 = new Timestamp(System.currentTimeMillis());

//			mesVO
			MesVO mesVO = new MesVO();
//			mesVO.setUserId(userId);
			
//			actVO抓單一瀏覽的資料
			ActService actSvc = new ActService();
		    ActVO actVO = actSvc.getOneActContent(activityId);

			/*************************** 2.開始新增資料 ***************************************/
//			新增MemberInsert與MesInsert
			ActMemberService actMemSvc = new ActMemberService();
			MesService mesSvc = new MesService();
			try {
//			新增MemberInsert
				actMemSvc.addActMember(actMemberVO);
//			新增MesInsert
				mesSvc.addMesVO(userId, "揪團申請", "您的揪團有人申請，請至揪團頁面審核申請", null, now2, (byte)1);					
			}catch(RuntimeException e){
				e.printStackTrace();
				errorMsgs.add("新增失敗");
			};
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actMemberVO", actMemberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/addContend.jsp");
				failureView.forward(req, res);
				return;
			}
		    req.setAttribute("actVO", actVO);
			String url = "/front-end/member/actMemberJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交actMemberJoin.jsp
			successView.forward(req, res);
		}

//				揪團成員申請加入 與MesInsert寫到applyJoin裡
		if ("MemberInsert".equals(action)) { // 來自addMemberJion.jsp的請求 

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());				
	
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());			
			
			ActMemberVO actMemberVO = new ActMemberVO();
//			actVO.setTripId(tripId);
			LocalDateTime now = LocalDateTime.now();
			actMemberVO.setUserId(userId);
			actMemberVO.setActivityId(activityId);
			actMemberVO.setEvaluationTime(now);

	
			/*************************** 2.開始新增資料 ***************************************/
				ActMemberService actMemSvc = new ActMemberService();
			try {
				actMemberVO = actMemSvc.getOneActMember(userId, activityId);
					
			}catch(RuntimeException e){
				e.printStackTrace();
				errorMsgs.add("新增失敗 : 沒有此會員");
			};
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actMemberVO", actMemberVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/addAct.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/member/actMemberJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
		// 會員訊息Mes通知加入 與MemberInsert寫到applyJoin裡
		if ("MesInsert".equals(action)) { // 來自actContent.jsp 揪團單一瀏覽的請求 成功進到申請加入頁面
			System.out.println("MemberJion");
			   /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
//			    Integer userId = Integer.valueOf(req.getParameter("userId"));
			  	// 取得揪團的ID 
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());				
			   try {
				   activityId = Integer.valueOf(req.getParameter("activityId"));
			   } catch (NumberFormatException e) {
			    e.printStackTrace();
			   }
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());	
			
			String sendTitle = req.getParameter("sendTitle").trim();

			String sendContent = req.getParameter("sendContent").trim();
			
//			Integer readMesseng = Integer.valueOf(req.getParameter("readMesseng").trim());	
			
			MesVO mesVO = new MesVO();
			
			mesVO.setUserId(userId);
			mesVO.setSendTitle(sendTitle);
			mesVO.setSendContent(sendContent);
//			mesVO.setReadMesseng(0);

			    /*************************** 2.開始查詢資料 *****************************************/
			MesService mesSvc = new MesService();
			try {
				mesVO = mesSvc.getOneMesVO(userId);
						
			}catch(RuntimeException e) {
				errorMsgs.add("新增失敗");
				e.printStackTrace();
			}
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("mesVO", mesVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/addContent.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/member/actMemberJoin.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);

		}
		
		
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("activityId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入正確揪團活動編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/select_ActHome.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer activityId = null;
			try {
				activityId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("揪團活動編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("activityId", str);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/select_ActHome.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ActService actSvc = new ActService();
			ActVO actVO = actSvc.getOneAct(activityId);
			if (actVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/act/select_ActHome.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/act/listOneAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer activityId = Integer.valueOf(req.getParameter("activityId"));

			/*************************** 2.開始查詢資料 ****************************************/
			ActService actSvc = new ActService();
			ActVO actVO = actSvc.getOneAct(activityId);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("actVO", actVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/act/updateAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
//      jsp圖片輸出  		
		if ("showFirstImages".equals(action)) {
			res.setContentType("image/jpeg");
			/*************************** 1.接收請求參數 ****************************************/
			Integer activityId = new Integer(req.getParameter("activityId"));
			/***************************
			 * 2.開始查詢資料，篩選資料
			 ****************************************/
			ActService actService = new ActService();
			ActVO images = actService.getOneAct(activityId);
//			Optional<ActVO> firstImages = images.stream().findFirst();
	
			/*************************** 3.輸出圖片 ************/
			byte[] content = images.getActivityTheCover();
			// 如果沒上傳圖片 就會顯示自己主機上的圖
			if(content == null) {
				byte[] buf = null;
			    try (InputStream in = Files.newInputStream(Path.of(getServletContext().getRealPath("/front-end/act/images") + "/Logo.png"));
			    		ServletOutputStream out = res.getOutputStream()){
			     buf = new byte[in.available()];
			     in.read(buf);
			     out.write(buf);
			     return;
			    } catch (IOException e) {
			     e.printStackTrace();
			    }
			}
			try (ServletOutputStream out = res.getOutputStream()) {
				out.write(content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/			
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO"); 
			Integer userId = usersVO.getUserId();
			
			Integer activityId = Integer.valueOf(req.getParameter("activityId").trim());

			String activityTitle = req.getParameter("activityTitle").trim();
			if (activityTitle == null || activityTitle.trim().length() == 0) {
				errorMsgs.add("揪團標題請勿空白");
			}

			String activityContent = req.getParameter("activityContent").trim();
			if (activityContent == null || activityContent.trim().length() == 0) {
				errorMsgs.add("揪團內容請勿空白");
			}
			
			String currentNumberStr = req.getParameter("currentNumber").trim();
			String currentNumberReg = "[\\d]{1,3}"; // Reg正則表達式,輸入0-9,最多3個數字
			if (currentNumberStr == null || currentNumberStr.trim().length() == 0) {
				errorMsgs.add("請輸入正確參與人數,請勿空白");
			} else if (!currentNumberStr.matches(currentNumberReg)) {
				errorMsgs.add("請輸入正確參與人數,只能輸入數字,最多輸入三位數!!");
			}
			Integer currentNumber = Integer.valueOf(currentNumberStr);
			
			String maxPeopleStr = req.getParameter("maxPeople").trim();
			String maxPeopleReg = "[\\d]{1,3}"; // Reg正則表達式,輸入0-9,最多3個數字
			if (maxPeopleStr == null || maxPeopleStr.trim().length() == 0) {
				errorMsgs.add("請輸入正確揪團上限人數,請勿空白");
			} else if (!maxPeopleStr.matches(maxPeopleReg)) {
				errorMsgs.add("請輸入正確揪團上限人數,只能輸入數字,最多輸入三位數!!");
			}
			Integer maxPeople = Integer.valueOf(maxPeopleStr);

			java.sql.Date registrationTime = null;
			try {
				registrationTime = java.sql.Date.valueOf(req.getParameter("registrationTime").trim());
			} catch (IllegalArgumentException e) {
				registrationTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入報名開始日期!");
			}

			java.sql.Date registrationEnd = null;
			try {
				registrationEnd = java.sql.Date.valueOf(req.getParameter("registrationTime").trim());
			} catch (IllegalArgumentException e) {
				registrationEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入報名結束日期!");
			}

			java.sql.Date tripStart = null;
			try {
				tripStart = java.sql.Date.valueOf(req.getParameter("tripStart").trim());
			} catch (IllegalArgumentException e) {
				tripStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入行程開始時間!");
			}

			java.sql.Date tripEnd = null;
			try {
				tripEnd = java.sql.Date.valueOf(req.getParameter("tripEnd").trim());
			} catch (IllegalArgumentException e) {
				tripEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入行程時間!");
			}
			
			
			
			byte[] activityTheCover = null;
			Part part = req.getPart("activityTheCover");// 上傳檔案大小很大的資料(byte)去用part物件處理,from表單
			InputStream in = part.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			activityTheCover = buf;	
			
			
			// 底下判斷如果沒上傳新圖片,set原來的原圖不會改變的判斷式
			if(buf.length == 0) {
			// set除了圖片以外的物件,從Service裡的getOneAct方法去拿actVO裡的物件
				ActService actService = new ActService();
				ActVO actVO = actService.getOneAct(activityId);
//				actVO.setTripId(tripId);
				actVO.setUserId(userId);
				actVO.setActivityTitle(activityTitle);
				actVO.setActivityContent(activityContent);
				actVO.setCurrentNumber(currentNumber);
				actVO.setMaxPeople(maxPeople);
				actVO.setRegistrationTime(registrationTime);
				actVO.setRegistrationEnd(registrationEnd);
				actVO.setTripStart(tripStart);
				actVO.setTripEnd(tripEnd);
	
				actService.updateAct(actVO);
				//
				String url = "/front-end/act/actHomePage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交updateAct.jsp
				successView.forward(req, res);
				//
				return;
			}		
	
			String activityState = req.getParameter("activityState").trim();
			Integer a;
			if ("揪團中".equals(activityState)) {
				activityState = "0";
			} else if ("已取消".equals(activityState)) {
				activityState = "1";
			} else {
				activityState = "2";
			}
			if ("activityState".equals(action)) { // 來自addAct.jsp的請求
				req.setAttribute("errorMsgs", errorMsgs);
			}

			ActVO actVO = new ActVO();
			actVO.setActivityId(activityId);
//			actVO.setTripId(tripId);
			actVO.setUserId(userId);
			actVO.setActivityTitle(activityTitle);
			actVO.setActivityContent(activityContent);
			actVO.setCurrentNumber(currentNumber);
			actVO.setMaxPeople(maxPeople);
			actVO.setRegistrationTime(registrationTime);
			actVO.setRegistrationEnd(registrationEnd);
			actVO.setTripStart(tripStart);
			actVO.setTripEnd(tripEnd);
			actVO.setActivityTheCover(activityTheCover);


			actVO.setActivityState(Integer.valueOf(activityState));

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/member/memberHome.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ActService actSvc = new ActService();
			actVO = actSvc.updateAct(actVO);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("actVO", actVO); // 資料庫update成功後,正確的的actVO物件,存入req
			String url = "/front-end/act/actHomePage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交memberHome.jsp
			successView.forward(req, res);
		}
		if ("activityInsert".equals(action)) { // 來自addAct.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

//			String tripIdStr = req.getParameter("tripId").trim();
//			String tripIdReg = "[\\d]{1,5}"; // 匹配最少5個數字
//			if (tripIdStr == null || tripIdStr.trim().length() == 0) {
//				errorMsgs.add("請輸入正確行程編號數字,請勿空白");
//			} else if (!tripIdStr.matches(tripIdReg)) {
//				errorMsgs.add("請輸入正確行程編號數字,只能輸入數字!!");
//			}
//			Integer tripId = Integer.valueOf(tripIdStr);
			
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO"); 
			Integer userId = usersVO.getUserId();
			
			String activityTitle = req.getParameter("activityTitle").trim();
			if (activityTitle == null || activityTitle.trim().length() == 0) {
				errorMsgs.add("揪團標題請勿空白");
			}

			String activityContent = req.getParameter("activityContent").trim();
			if (activityTitle == null || activityTitle.trim().length() == 0) {
				errorMsgs.add("揪團內容請勿空白");
			}

			Integer currentNumber = Integer.valueOf(req.getParameter("currentNumber").trim());

			Integer maxPeople = Integer.valueOf(req.getParameter("maxPeople").trim());

			java.sql.Date registrationTime = null;
			try {
				registrationTime = java.sql.Date.valueOf(req.getParameter("registrationTime").trim());
			} catch (IllegalArgumentException e) {
				registrationTime = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入報名開始時間!");
			}

			java.sql.Date registrationEnd = null;
			try {
				registrationEnd = java.sql.Date.valueOf(req.getParameter("registrationEnd").trim());
			} catch (IllegalArgumentException e) {
				registrationEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入報名結束時間!");
			}

			java.sql.Date tripStart = null;
			try {
				tripStart = java.sql.Date.valueOf(req.getParameter("tripStart").trim());
			} catch (IllegalArgumentException e) {
				tripStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入行程開始時間!");
			}

			java.sql.Date tripEnd = null;
			try {
				tripEnd = java.sql.Date.valueOf(req.getParameter("tripEnd").trim());
			} catch (IllegalArgumentException e) {
				tripEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入行程時間!");
			}
			
			Part part = req.getPart("activityTheCover");// 上傳檔案大小很大的資料(byte)去用part物件處理,from表單的name屬性,此方法會回傳一個上傳檔案的Part物件
			InputStream in = part.getInputStream();
			byte[] activityTheCover = new byte[in.available()]; // 上傳到伺服器的資料,用此方法已BinaryStream的方式送進資料庫,成為表格文件
			in.read(activityTheCover);

			
//			byte[] activityTheCover = req.getParameter("activityTheCover").getBytes();

			String activityState = req.getParameter("activityState").trim();
				if ("揪團中".equals(activityState)) {
					activityState = "0";
				} else if ("已取消".equals(activityState)) {
					activityState = "1";
				} else if ("檢舉成功,已下架".equals(activityState)) {
					activityState = "2";
				};
					
				if ("activityState".equals(action)) { // 來自addEmp.jsp的請求
				req.setAttribute("errorMsgs", errorMsgs);
				}
//			Integer activityState = Integer.valueOf(activityState);
			ActVO actVO = new ActVO();
//			actVO.setTripId(tripId);
			actVO.setUserId(userId);
			actVO.setActivityTitle(activityTitle);
			actVO.setActivityContent(activityContent);
			actVO.setCurrentNumber(currentNumber);
			actVO.setMaxPeople(maxPeople);
			actVO.setRegistrationTime(registrationTime);
			actVO.setRegistrationEnd(registrationEnd);
			actVO.setTripStart(tripStart);
			actVO.setTripEnd(tripEnd);
			actVO.setActivityTheCover(activityTheCover); // 封面
			actVO.setActivityState(Integer.valueOf(activityState));

			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/addAct.jsp");
					failureView.forward(req, res);
					return;
				}

			/*************************** 2.開始新增資料 ***************************************/
				ActService actSvc = new ActService();
			try {
				actVO = actSvc.addAct( userId, activityTitle, activityContent, currentNumber, maxPeople,
							registrationTime, registrationEnd, tripStart, tripEnd, activityTheCover,
							Integer.valueOf(activityState));
					
			}catch(RuntimeException e){
				e.printStackTrace();
				errorMsgs.add("新增失敗 : 沒有此會員");
			};
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("actVO", actVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/act/addAct.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/act/actHomePage.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer activityId = Integer.valueOf(req.getParameter("activityId"));
			/*************************** 2.開始刪除資料 ***************************************/
			ActService actSvc = new ActService();
			actSvc.deletAct(activityId);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/act/listAllAct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	
//	if("get_Img".equals(action)) {
//		res.setContentType("image/jpg");
//		ServletOutputStream out = res.getOutputStream();
//		String activityIdStr = req.getParameter("activity_id");
//		Integer activity_id = Integer.valueOf(activityIdStr);
//		ActVO actVO = new ActVO();
//		ActService actService = new ActService();
//		actService.getImgs(activity_id);
//		
//		if(actVO.getImgs() == null) { 
//			// 如果沒圖片 會傳預設的圖片
//			InputStream in = getServletContext().getResourceAsStream("/front-end/act/images/Logo.png");
//			//讀取原本的圖片
//			byte[] originalImg = new byte[in.available()]; 
//			in.read(originalImg);
//			out.write(originalImg); 
//			out.close();
//			in.close();
//		}else {
//			byte[] actImg = actVO.getImgs();
//			out.write(actImg);
//			out.close();
//		}
//	}
		
		
  }
	
}
//if("get_activity_location".equals(action)){
//List<ActVO> actVO = actSvc.getActivityLocation(req.getParameter("location"));
//
////req.setAttribute("actVOList", actVOList); // 資料庫取出的empVO物件,存入req
////String url = "/front-end/act/actNorth.jsp";

////RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneTrip.jsp
////successView.forward(req, res);
//}