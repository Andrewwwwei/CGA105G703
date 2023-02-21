package com.permissfunc.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.permissfunc.model.PermisFuncService;
import com.permissfunc.model.PermisFuncVO;
import com.permission.model.PermissionService;
import com.permission.model.PermissionVO;

@WebServlet("/FuncServlet")

public class PermisFuncServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
		if ("getOneFunc_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer funcId = Integer.valueOf(req.getParameter("funcId"));
			

			/*************************** 2.開始新增資料 ****************************************/
			PermisFuncService permissSvc = new PermisFuncService();
			PermisFuncVO permisFuncVO = permissSvc.getOnePermisFunc(funcId);
			PermissionService permissionSvc = new PermissionService();
			List<PermissionVO> list =permissionSvc.getPermisByFunc(funcId);

		

			/*************************** 3.完成，準備轉交(Send the Success view) ************/
			req.setAttribute("permisFuncVO", permisFuncVO); // ��Ʈw���X��empVO����,�s�Jreq
			req.setAttribute("permissionVO", list);
			String url = "/back-end/permisFunc/PermisFuncUpDate.jsp";


			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���? update_emp_input.jsp
			successView.forward(req, res);


			
			
			
		}
			if ("funcUpdate".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

				List<String> funcUpdateErr = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("funcUpdateErr", funcUpdateErr);

				/*************************** 1.接收請求參數 **********************/
				Integer funcId = Integer.valueOf(req.getParameter("funcId").trim());

				String funcName = req.getParameter("funcName").trim();
				if (funcName == null || funcName.trim().length() == 0) {
					funcUpdateErr.add("功能名稱請勿空白");
				}
				
				PermisFuncVO permisFuncVO = new PermisFuncVO();
				
				permisFuncVO.setFuncName(funcName);
				permisFuncVO.setFuncId(funcId);
				
				// Send the use back to the form, if there were errors
				if (!funcUpdateErr.isEmpty()) {
					req.setAttribute("permisFuncVO", permisFuncVO); // �t����J�榡���~��empVO����,�]�s�Jreq
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/permisFunc/PermisFuncUpDate.jsp");
					failureView.forward(req, res);
					return; // �{�����_
				}
				/*************************** 2.開始更新資料 *****************************************/
				PermisFuncService permissSvc = new PermisFuncService();
				permisFuncVO = permissSvc.updatePermisFunc(funcName, funcId);

				/*************************** 完成，準備轉交(Send the Success view) *************/
				req.setAttribute("permisFuncVO", permisFuncVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
				String url = "/back-end/permisFunc/PermisFuncListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
				successView.forward(req, res);
			}
				
				if ("insert".equals(action)) {

					Map<String, String> funcAddErr = new LinkedHashMap<String, String>();
					req.setAttribute("funcAddErr", funcAddErr);

					List<String> funcAddErrL = new LinkedList<String>();
					// Store this set in the request scope, in case we need to
					// send the ErrorPage view.
					req.setAttribute("funcAddErrL", funcAddErrL);

					/*********************** 1.接收資料 *************************/
					String funcName = req.getParameter("funcName").trim();
					if (funcName == null || funcName.trim().length() == 0) {
						funcAddErrL.add("請勿空白");
						}
						
						PermisFuncVO permisFuncVO = new PermisFuncVO();						
						permisFuncVO.setFuncName(funcName);
						// Send the use back to the form, if there were errors
						if (!funcAddErrL.isEmpty()) {
							req.setAttribute("permisFuncVO", permisFuncVO);
							RequestDispatcher failureView = req.getRequestDispatcher("/back-end/permisFunc/PermisFuncAdd.jsp");
							failureView.forward(req, res);
							return;
						}
						/*********************** 2.更新資料 *************************/
						PermisFuncService permissSvc = new PermisFuncService();
						permisFuncVO = permissSvc.addPermisFunc(funcName);
				
						/*********************** 3.傳回資料 *************************/							
						String url = "/back-end/permisFunc/PermisFuncListAll.jsp";

						RequestDispatcher successView = req.getRequestDispatcher(url); 
						successView.forward(req, res);

			

			}

					if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

						List<String> funcDeleteErr = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("funcDeleteErr", funcDeleteErr);
				
							/***************************1.接收資料***************************************/
							Integer funcId = Integer.valueOf(req.getParameter("funcId"));
							
							/***************************2.更新資料 ***************************************/
							
							try {
								PermisFuncService permissSvc = new PermisFuncService();
								permissSvc.deletePermisFunc(funcId);
							} catch (Exception e) {
								
								
								funcDeleteErr.add("無法刪除，請確認使用中權限");
							}
							
							/***************************3.傳回資料(Send the Success view)***********/								
							String url = "/back-end/permisFunc/PermisFuncListAll.jsp";
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req, res);
					}
					
					if ("listPermission_ByCompositeQuery".equals(action)) { 
						List<String> QueryerrorMsgs = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("QueryerrorMsgs", QueryerrorMsgs);

							
							/***************************1.撠撓�鞈��Map**********************************/ 
							HttpSession session = req.getSession();
							Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
							
							if (req.getParameter("whichPage") == null){
								Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
								session.setAttribute("map",map1);
								map = map1;
							} 
							
							/***************************2.嚙罷嚙締嚙複合嚙範嚙踝蕭***************************************/
							PermissionService permissionSvc = new PermissionService();
							List<PermissionVO> list  = permissionSvc.getAll(map);
							
							/***************************3.嚙範嚙賠改蕭嚙踝蕭,嚙褒喉蕭嚙踝蕭嚙�(Send the Success view)************/
							req.setAttribute("listPermission_ByCompositeQuery", list); // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭list嚙踝蕭嚙踝蕭,嚙編嚙皚request
							RequestDispatcher successView = req.getRequestDispatcher("/back-end/permission/listPermission_ByCompositeQuery.jsp"); // 嚙踝蕭嚙穀嚙踝蕭嚙締istEmps_ByCompositeQuery.jsp
							successView.forward(req, res);
					}
		
					if ("deletPermission".equals(action)) { // �Ӧ�listAllEmp.jsp

						List<String> permisDeleteErr = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("permisDeleteErr", permisDeleteErr);
				
							/***************************1.接收資料***************************************/
							Integer funcId = Integer.valueOf(req.getParameter("funcId"));
							Integer empNo = Integer.valueOf(req.getParameter("empNo"));
							
							/***************************2.更新資料 ***************************************/
							
							try {
								PermissionService permissionSvc = new PermissionService();
								permissionSvc.deletePermission(empNo,funcId);
							} catch (Exception e) {
								
								
								permisDeleteErr.add("無法刪除，請確認使用中權限");
							}
							
							/***************************3.傳回資料(Send the Success view)***********/								
							
							String url = "/FuncServlet?action=getOneFunc_For_Update&funcId="+funcId;
							RequestDispatcher successView = req.getRequestDispatcher(url);
							successView.forward(req, res);
							return;
					}
	
					if ("addEmpPermis".equals(action)) {

						Map<String, String> empAddErr = new LinkedHashMap<String, String>();
						req.setAttribute("empAddErr", empAddErr);

						List<String> empAddErrL = new LinkedList<String>();
						// Store this set in the request scope, in case we need to
						// send the ErrorPage view.
						req.setAttribute("empAddErrL", empAddErrL);

						/*********************** 1.接收資料 *************************/
						Integer empNo = Integer.valueOf(req.getParameter("empNo"));
						if (empNo == null ) {
							empAddErrL.add("請勿空白");
							}
						Integer funcId = Integer.valueOf(req.getParameter("empfuncId"));
						if (funcId == null ) {
							empAddErrL.add("請勿空白");
						}
							PermissionVO permissionVO = new PermissionVO();						
							permissionVO.setEmpNo(empNo);
							// Send the use back to the form, if there were errors
							if (!empAddErrL.isEmpty()) {
								req.setAttribute("permissionVO", permissionVO);
								RequestDispatcher failureView = req.getRequestDispatcher("/back-end/permisFunc/PermisFuncUpDate.jsp");
								failureView.forward(req, res);
								return;
							}
							/*********************** 2.更新資料 *************************/
							PermissionService permissionSvc = new PermissionService();
							permissionVO = permissionSvc.addPermission(funcId,empNo);
					
							/*********************** 3.傳回資料 *************************/							
							
							String url = "/FuncServlet?action=getOneFunc_For_Update&funcId="+funcId;
							RequestDispatcher successView = req.getRequestDispatcher(url); 
							successView.forward(req, res);
							return;

				

				
	}

	}
}

