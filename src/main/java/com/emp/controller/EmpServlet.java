package com.emp.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


import com.Mes.model.MesService;
import com.Users.model.UsersService;
import com.Users.model.UsersVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.permission.model.*;

@MultipartConfig
@WebServlet("/EmpServlet")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
//====================================================================================
		
		if ("backEndlogin".equals(action)) {
			Integer empNo = Integer.valueOf(req.getParameter("empNo"));// 來自backpage.jsp的請求
			String empPw = req.getParameter("empPw");
			EmpService empSvc = new EmpService();
//			--------------- 帳密及狀態檢查 --------------------
			EmpVO empVO = empSvc.getOneEmp(empNo);
			if(empVO == null) {
				req.setAttribute("errorMsg", "查無此帳號");
				req.setAttribute("empNo", empNo);
				req.getRequestDispatcher("/back-end/backEndLogin.jsp").forward(req, res);
				return;
			}else if (!empPw.equals(empVO.getEmpPw())) {
				req.setAttribute("errorMsg", "密碼錯誤");
				req.setAttribute("empNo", empNo);
				req.getRequestDispatcher("/back-end/backEndLogin.jsp").forward(req, res);
				return;
			}else if(!empVO.getEmpStatus().equals(1)) {
				req.setAttribute("errorMsg", "帳號未啟用或已停權");
				req.setAttribute("empNo", empNo);
				req.getRequestDispatcher("/back-end/backEndLogin.jsp").forward(req, res);
				return;
			}
			
//			--------------- 權限處理 --------------------		
				PermissionService permisSvc = new PermissionService();
				List<PermissionVO> permissionVOList = permisSvc.getPermisByEmp(empNo);
//			--------------- 轉交到首頁 --------------------
				req.getSession().setAttribute("empVO", empVO);
				req.getSession().setAttribute("permissionVOList", permissionVOList);
				RequestDispatcher success = req.getRequestDispatcher("/back-end/backpage.jsp");
				success.forward(req, res);
				return;
		}
//====================================================================================
		if ("loginOut".equals(action)) {// 來自backpage.jsp的請求
			req.getSession().invalidate(); 
			res.sendRedirect(req.getContextPath()+"/back-end/backEndLogin.jsp");
			return;
		}
		
		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數**********************/
				String str = req.getParameter("empNo");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入員工姓名");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/Emplistallpage.jsp");
					failureView.forward(req, res);
					return;//
				}
				
				Integer empNo = null;
				try {
					empNo = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("請輸入員工編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/Emplistallpage.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始比對資料*****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empNo);
				if (empVO == null) {
					errorMsgs.add("查無資料");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/Emplistallpage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.完成，準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO);
				String url = "/back-end/emp/EmpGetOne.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���? listOneEmp.jsp
				successView.forward(req, res);
		}
		
		
		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer empNo = Integer.valueOf(req.getParameter("empNo"));
				
				/***************************2.開始新增資料****************************************/
				EmpService empSvc = new EmpService();
				EmpVO empVO = empSvc.getOneEmp(empNo);
								
				/***************************3.完成，準備轉交(Send the Success view)************/
				req.setAttribute("empVO", empVO);       
				String url = "/back-end/emp/EmpUpdate.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				System.out.println(empVO);
				
		}
		
		
		if ("update".equals(action)) { 
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數**********************/
			Integer empNo = Integer.valueOf(req.getParameter("empNo").trim());    
			String empPw = req.getParameter("empPw").trim();
			if (empPw == null || empPw.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			String empName = req.getParameter("empName");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (empName == null || empName.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			} else if(!empName.trim().matches(enameReg)) { 
				errorMsgs.add("請勿空白");
            }	
			
		
			String empDep = req.getParameter("empDep").trim();
			if (empDep == null || empDep.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			
			String empJobTitle = String.valueOf(req.getParameter("empJobTitle").trim());
			if (empJobTitle == null || empJobTitle.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			
			String empId = req.getParameter("empIdnum").trim();
			if (empId == null || empId.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			
			String empEmail = req.getParameter("empEmail").trim();
			if (empEmail == null || empEmail.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
			
			String empTel = req.getParameter("empTel").trim();
			if (empTel == null || empTel.trim().length() == 0) {
				errorMsgs.add("請勿空白");
			}
       
            Integer empStatus = Integer.valueOf(req.getParameter("empStatus"));
            
            java.sql.Date empHireDate =null;
            try {
            	empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate").trim());
			} catch (IllegalArgumentException e) {
				empHireDate=new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請勿空白");
			}
			
				EmpVO empVO = new EmpVO();
				empVO.setEmpNo(empNo);
				empVO.setEmpPw(empPw);
				empVO.setEmpName(empName);
				empVO.setEmpDep(empDep);
				empVO.setJobTitle(empJobTitle);
				empVO.setEmpIdnum(empId);
				empVO.setEmpEmail(empEmail);
				empVO.setEmpTel(empTel);
				empVO.setEmpHiredate(empHireDate);
				empVO.setEmpStatus(empStatus);


				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("empVO", empVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/EmpUpdate.jsp");
					failureView.forward(req, res);
					return; //
				}
				
				/***************************2.開始更新資料*****************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.updateEmp(empNo, empPw,empName,empDep,empJobTitle,empId,empEmail,empTel,empStatus,empHireDate);
				
				/***************************完成，準備轉交(Send the Success view)*************/
				req.setAttribute("empVO", empVO); 
				String url = "/back-end/emp/Emplistallpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
		}

		 if ("insert".equals(action)) { 
	        	
			 Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
				req.setAttribute("errorMsgs", errorMsgs);
				
				List<String> errorMsgs1 = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("errorMsgs1", errorMsgs1);

					/***********************1.*************************/
				String empPw = req.getParameter("empPw").trim();
				if (empPw == null || empPw.trim().length() == 0) {
					errorMsgs1.add("請填寫密碼");
				}
				
				String empName = req.getParameter("empName");
				String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (empName == null || empName.trim().length() == 0) {
					errorMsgs1.add("請填寫姓名");
					}

				
				String empDep = req.getParameter("empDep").trim();
				if (empDep == null || empDep.trim().length() == 0) {
					errorMsgs1.add("請填寫部門");
				}
				
				String empJobTitle = String.valueOf(req.getParameter("empJobTitle").trim());
				if (empJobTitle == null || empJobTitle.trim().length() == 0) {
					errorMsgs1.add("請填寫職稱");
				}
				
				String empId = req.getParameter("empIdnum").trim();
				if (empId == null || empId.trim().length() == 0) {
					errorMsgs1.add("請填寫身分證字號");
				}
				
				String empEmail = req.getParameter("empEmail").trim();
				if (empEmail == null || empEmail.trim().length() == 0) {
					errorMsgs1.add("請填寫電子信箱");
				}
				
				String empTel = req.getParameter("empTel").trim();
				if (empTel == null || empTel.trim().length() == 0) {
					errorMsgs1.add("請填寫電話");
				}
	       
	            Integer empStatus = Integer.valueOf(req.getParameter("empStatus"));
	
	            
	            java.sql.Date empHireDate =null;
	            try {
	            	empHireDate = java.sql.Date.valueOf(req.getParameter("empHireDate").trim());
				} catch (IllegalArgumentException e) {
					empHireDate=new java.sql.Date(System.currentTimeMillis());
					errorMsgs1.add("請選擇日期");
				}

				EmpVO empVO = new EmpVO();
				empVO.setEmpPw(empPw);
				empVO.setEmpName(empName);
				empVO.setEmpDep(empDep);
				empVO.setJobTitle(empJobTitle);
				empVO.setEmpIdnum(empId);
				empVO.setEmpEmail(empEmail);
				empVO.setEmpTel(empTel);
				empVO.setEmpHiredate(empHireDate);
				empVO.setEmpStatus(empStatus);


				// Send the use back to the form, if there were errors
				if (!errorMsgs1.isEmpty()) {
					req.setAttribute("empVO", empVO); 
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/Empaddpage.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.�}�l�s�W���?***************************************/
				EmpService empSvc = new EmpService();
				empVO = empSvc.addEmp(empPw,empName,empDep,empJobTitle,empId,empEmail,empTel,empStatus,empHireDate);
				
				/***************************3.�s�W����,�ǳ����?(Send the Success view)***********/
//					
				String url ="/back-end/emp/Emplistallpage.jsp";
				
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);				
		}
			
			
			
		 if ("delete".equals(action)) { 

			List<String> deletErr = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("deletErr", deletErr);
	
				/***************************1.�����ШD�Ѽ�***************************************/
				Integer empNo = Integer.valueOf(req.getParameter("empNo"));
				
				/***************************2.�}�l�R�����?***************************************/
			
				try {
					EmpService empSvc = new EmpService();
					empSvc.deleteEmp(empNo);
				} catch (Exception e) {
					
					
					deletErr.add("無法刪除，請確認權限設定");
				}
				
				/***************************3.�R������,�ǳ����?(Send the Success view)***********/								
				String url = "/back-end/emp/Emplistallpage.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
		}
	
		 if ("listEmps_ByCompositeQuery".equals(action)) { 
			List<String> QueryerrorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("QueryerrorMsgs", QueryerrorMsgs);

				
				/***************************1.撠撓�鞈��Map**********************************/ 

//					Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				

				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.嚙罷嚙締嚙複合嚙範嚙踝蕭***************************************/
				EmpService empSvc = new EmpService();
				List<EmpVO> list  = empSvc.getAll(map);
				
				/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)************/
				req.setAttribute("listEmps_ByCompositeQuery", list); 
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/emp/listEmps_ByCompositeQuery.jsp"); // 嚙踝蕭嚙穀嚙踝蕭嚙締istEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
//====================================================================================
		if ("usercontrol".equals(action)) {// 來自backpage.jsp的請求
			RequestDispatcher success = req.getRequestDispatcher("/back-end/emp/systemSelect.jsp");
			success.forward(req, res);
			return;
		}
//====================================================================================
		if ("userMes".equals(action)) {// 來自backpage.jsp的請求
			RequestDispatcher success = req.getRequestDispatcher("/back-end/emp/userMes.jsp");
			success.forward(req, res);
			return;
		}
//====================================================================================
		if ("userStatus".equals(action)) {// 來自backpage.jsp的請求
			RequestDispatcher success = req.getRequestDispatcher("/back-end/emp/userStatus.jsp");
			success.forward(req, res);
		}
//====================================================================================
		
		if ("getOne_For_userMail".equals(action)) { // 來自userMes.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
			String userMail = req.getParameter("userMail");
				if (userMail == null || (userMail.trim()).length() == 0) {
					errorMsgs.add("請輸入使用者Email");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userMes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料*****************************************/
				UsersService usersSvc = new UsersService();
				UsersVO usersVO = usersSvc.getOneUserEmail(userMail);
				if (usersVO == null) {
					errorMsgs.add("沒有找到這筆資料，請重新輸入");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/emp/userMes.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************3.查詢完成，準備轉交(Send the Success view)*************/
				req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
				String url = "/back-end/emp/sendUserMes.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserMes.jsp
				successView.forward(req, res);
				return;
		}
//====================================================================================
		if ("getOne_For_userName".equals(action)) { // 來自userMes.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
			String userName = req.getParameter("userName");
			if (userName == null || (userName.trim()).length() == 0) {
				errorMsgs.add("請輸入使用者姓名");
			}
					// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/userMes.jsp");
				failureView.forward(req, res);
				return;
			}
					
			/***************************2.開始新增資料*****************************************/
			UsersService usersSvc = new UsersService();
			UsersVO usersVO = usersSvc.getOneUserName(userName);
			if (usersVO == null) {
				errorMsgs.add("沒有找到這筆資料，請重新輸入");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/userMes.jsp");
						failureView.forward(req, res);
						return;
					}
					
			/***************************3.查詢完成，準備轉交(Send the Success view)*************/
			req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
			String url = "/back-end/emp/sendUserMes.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserMes.jsp
			successView.forward(req, res);
			return;
		}
//====================================================================================
				
		if ("mesSend".equals(action)) { // 來自sendUserMes.jsp的請求
			String sendContent = req.getParameter("sendContent");
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

		/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
			if (sendContent == null || (sendContent.trim()).length() == 0) {
				errorMsgs.add("請輸入留言內容");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
				failureView.forward(req, res);
				return;
			}
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
				failureView.forward(req, res);
				return;
			}

			String str = req.getParameter("userId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入使用者ID");
			}
			Integer userId = null;
			try {
				userId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("請輸入正確格式");
			}
	/***************************2.開始新增資料*****************************************/
			MesService mesSvc = new MesService();
			String Time= req.getParameter("sendTime");
			String sendTitle = req.getParameter("sendTitle");
			java.sql.Timestamp sendTime = java.sql.Timestamp.valueOf(Time);
			String str1=req.getParameter("readMesseng");
			Integer int1= Integer.valueOf(str1);
			byte readMesseng = int1.byteValue();
			Part part = req.getPart("sendPic");
			InputStream in = part.getInputStream();
				byte[] sendPic = new byte[in.available()];
				in.read(sendPic);
				in.close();
				try {
					if (part == null||part.getSize() == 0) {
				errorMsgs.add("請選擇上傳照片");
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
				failureView.forward(req, res);
				return;
					
				} 
				}catch (Exception e) {
				e.printStackTrace();
				}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
				failureView.forward(req, res);
				return;
			}
			
	/***************************3.查詢完成，準備轉交(Send the Success view)*************/
			mesSvc.addMesVO(userId, sendTitle, sendContent, sendPic, sendTime, readMesseng);
			errorMsgs.add("上傳成功");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/emp/sendUserMes.jsp");
			failureView.forward(req, res);
			return;
		}
		//====================================================================================
			
		if ("getAll_Total_Count_userstatus".equals(action)) { // 來自userMes.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
			String userStatus = req.getParameter("userStatus");
			if (userStatus == null || (userStatus.trim()).length() == 0) {
				errorMsgs.add("請輸入檢舉次數");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/userStatus.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer reportTotalCount = null;
			try {
				reportTotalCount = Integer.valueOf(userStatus);
			} catch (Exception e) {
				errorMsgs.add("請輸入正確格式");
			}
			/***************************2.開始新增資料*****************************************/
			UsersService usersSvc = new UsersService();
			List<UsersVO> usersVO = usersSvc.getAllByReportTotalCount(reportTotalCount);
			if (usersVO == null) {
				errorMsgs.add("沒有找到這筆資料，請重新輸入");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/userStatus.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************3.查詢完成，準備轉交(Send the Success view)*************/
			req.getSession().setAttribute("reportTotalCount", reportTotalCount); // 資料庫取出的reportTotalCount,存入req
			String url = "/back-end/emp/sendUserReportTotalCount.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserReportTotalCount.jsp
			successView.forward(req, res);
		}
			//====================================================================================
			
		if ("getOne_For_userMail_userstatus".equals(action)) { // 來自userStatus.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
			String userMail = req.getParameter("userMail");
			if (userMail == null || (userMail.trim()).length() == 0) {
				errorMsgs.add("請輸入使用者Email");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/userStatus.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料*****************************************/
			UsersService usersSvc = new UsersService();
			UsersVO usersVO = usersSvc.getOneUserEmail(userMail);
			if (usersVO == null) {
				errorMsgs.add("沒有找到這筆資料，請重新輸入");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/emp/userStatus.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************3.查詢完成，準備轉交(Send the Success view)*************/
			req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
			String url = "/back-end/emp/sendUserReportTotalCountOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserReportTotalCountOne.jsp
			successView.forward(req, res);
		}
			//====================================================================================
		if ("getOne_For_userName_userstatus".equals(action)) { // 來自userStatus.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		/***************************1.接收請求參數 - 輸入格式的錯誤處裡**********************/
			String userName = req.getParameter("userName");
			if (userName == null || (userName.trim()).length() == 0) {
			errorMsgs.add("請輸入使用者姓名");
			}
					// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/userStatus.jsp");
				failureView.forward(req, res);
				return;
			}
					
			/***************************2.開始新增資料*****************************************/
			UsersService usersSvc = new UsersService();
			UsersVO usersVO = usersSvc.getOneUserName(userName);
			if (usersVO == null) {
				errorMsgs.add("沒有找到這筆資料，請重新輸入");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/userStatus.jsp");
				failureView.forward(req, res);
				return;
			}
					
			/***************************3.查詢完成，準備轉交(Send the Success view)*************/
			req.getSession().setAttribute("usersVO", usersVO); // 資料庫取出的UsersVO物件,存入req
			String url = "/back-end/emp/sendUserReportTotalCountOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交sendUserReportTotalCountOne.jsp
			successView.forward(req, res);
			return;
		}
		if ("suspension".equals(action)) { // 來自sendUserReportTotalCountOne.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數***************************************/
			String userAccount = req.getParameter("userAccount");
			/***************************2.開始刪除資料***************************************/
			UsersService usersSvc = new UsersService();
			usersSvc.updateUserStatus(userAccount);
			/***************************3.更新完成,準備轉交(Send the Success view)***********/	
			errorMsgs.add("停權成功");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/emp/sendUserReportTotalCountOne.jsp");
			failureView.forward(req, res);
			return;
		}
		if ("disSuspensionOneForone".equals(action)) { // 來自sendUserReportTotalCount.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數***************************************/
			String userAccount = req.getParameter("userAccount");
			/***************************2.開始刪除資料***************************************/
			UsersService usersSvc = new UsersService();
			usersSvc.restorationUserStatusByUserAccount(userAccount);
			/***************************3.更新完成,準備轉交(Send the Success view)***********/	
			errorMsgs.add("復權成功");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/emp/sendUserReportTotalCountOne.jsp");
			failureView.forward(req, res);
			return;
		}
		if ("suspensionOne".equals(action)) { // 來自sendUserReportTotalCount.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數***************************************/
			String userAccount = req.getParameter("userAccount");
			/***************************2.開始刪除資料***************************************/
			UsersService usersSvc = new UsersService();
			usersSvc.updateUserStatus(userAccount);
			/***************************3.更新完成,準備轉交(Send the Success view)***********/	
			errorMsgs.add("停權成功");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/emp/sendUserReportTotalCount.jsp");
			failureView.forward(req, res);
			return;
		}
		if ("suspensionAll".equals(action)) { // 來自sendUserReportTotalCount.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數***************************************/
			String []userAccountAll = req.getParameterValues("userAccountAll");
			/***************************2.開始刪除資料***************************************/
			UsersService usersSvc = new UsersService();
			if(userAccountAll!=null) {
				for(int i=0; i<userAccountAll.length;i++)
				{
			 	usersSvc.updateUserStatus(userAccountAll[i]);
				}
			}
			/***************************3.更新完成,準備轉交(Send the Success view)***********/	
			errorMsgs.add("停權成功");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/emp/sendUserReportTotalCount.jsp");
			failureView.forward(req, res);
			return;
		}
		if ("disSuspensionOne".equals(action)) { // 來自sendUserReportTotalCount.jsp
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			/***************************1.接收請求參數***************************************/
			String userAccount = req.getParameter("userAccount");
			/***************************2.開始刪除資料***************************************/
			UsersService usersSvc = new UsersService();
			usersSvc.restorationUserStatusByUserAccount(userAccount);
			/***************************3.更新完成,準備轉交(Send the Success view)***********/	
			errorMsgs.add("復權成功");
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/emp/sendUserReportTotalCount.jsp");
			failureView.forward(req, res);
			return;
		}
	}
	
}
