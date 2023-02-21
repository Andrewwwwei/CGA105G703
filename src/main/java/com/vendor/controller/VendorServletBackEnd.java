package com.vendor.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;
import com.vendorPic.model.VendorPicService;
import com.vendorPic.model.VendorPicVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)     
@WebServlet("/VenServlet")
public class VendorServletBackEnd extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // �Ӧ�select_page.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 **********************/
			Integer venId = Integer.valueOf(req.getParameter("venId"));

			/*************************** 2.開始比對資料 *****************************************/
			VendorService venSvc = new VendorService();
			VendorVO vendorVO = venSvc.getOneVendor(venId);

			/*************************** 3.完成，準備轉交(Send the Success view) *************/
			req.setAttribute("vendorVO", vendorVO); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back-end/ven/VenListOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // ���\���? listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer venId = Integer.valueOf(req.getParameter("venId"));

			/*************************** 2.開始新增資料 ****************************************/
			VendorService venSvc = new VendorService();
			VendorVO vendorVO = venSvc.getOneVendor(venId);
			VendorPicService vendorPicService = new VendorPicService();
			List<VendorPicVO> vendorPicVOList = vendorPicService.getThisVendorpic(venId);

			/*************************** 3.完成，準備轉交(Send the Success view) ************/
			req.setAttribute("vendorVO", vendorVO); // ��Ʈw���X��empVO����,�s�Jreq
			req.setAttribute("vendorPicVOList", vendorPicVOList); // ��Ʈw���X��empVO����,�s�Jreq
			String url = "/back-end/ven/VenUpDate.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\���? update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // �Ӧ�update_emp_input.jsp���ШD

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 **********************/
			Integer venId = Integer.valueOf(req.getParameter("venId").trim());
			String venPw = req.getParameter("venPw").trim();
			if (venPw == null || venPw.trim().length() == 0) {
				errorMsgs.add("請填寫密碼");
			}
			String venName = req.getParameter("venName");
			if (venName == null || venName.trim().length() == 0) {
				errorMsgs.add("請填寫廠商名稱");
			} 

			String venTaxNum = req.getParameter("venTaxNum").trim();
			if (venTaxNum == null || venTaxNum.trim().length() == 0) {
				errorMsgs.add("請填寫統一編號");
			}

			String venEmail = req.getParameter("venEmail").trim();
			if (venEmail == null || venEmail.trim().length() == 0) {
				errorMsgs.add("請填寫Email");
			}

			String venTel = req.getParameter("venTel").trim();
			if (venTel == null || venTel.trim().length() == 0) {
				errorMsgs.add("請填寫廠商電話");
			}

			String venLocation = req.getParameter("venLocation").trim();
			if (venLocation == null || venLocation.trim().length() == 0) {
				errorMsgs.add("請填寫地址");
			}

			String venWin = req.getParameter("venWin").trim();
			if (venWin == null || venWin.trim().length() == 0) {
				errorMsgs.add("請填寫聯繫窗口");
			}
			String venWinTel = req.getParameter("venWinTel").trim();
			if (venWinTel == null || venWinTel.trim().length() == 0) {
				errorMsgs.add("請填寫聯繫窗口電話");
			}
			String venIntro = req.getParameter("venIntro").trim();
			if (venIntro == null || venIntro.trim().length() == 0) {
				errorMsgs.add("請填寫旅館介紹");
			}
			String venCanPolicy = req.getParameter("venCanPolicy").trim();
			if (venCanPolicy == null || venCanPolicy.trim().length() == 0) {
				errorMsgs.add("請填寫取消政策");
			}
			String venNotice = req.getParameter("venNotice").trim();
			if (venNotice == null || venNotice.trim().length() == 0) {
				errorMsgs.add("請填寫入住須知");
			}
			String venBank = req.getParameter("venBank").trim();
			if (venBank == null || venBank.trim().length() == 0) {
				errorMsgs.add("請填寫銀行帳號");
			}

			Integer venStatus = Integer.valueOf(req.getParameter("venStatus"));
			Float venScore = Float.valueOf(req.getParameter("venScore"));
			Integer venScorePeople = Integer.valueOf(req.getParameter("venScorePeople"));

			java.sql.Date venJoinDate = null;
			try {
				venJoinDate = java.sql.Date.valueOf(req.getParameter("venJoinDate").trim());
			} catch (IllegalArgumentException e) {
				venJoinDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請填寫加入日期");
			}

			VendorVO vendorVO = new VendorVO();
			
			vendorVO.setVenPw(venPw);
			vendorVO.setVenName(venName);
			vendorVO.setVenTaxnum(venTaxNum);
			vendorVO.setVenEmail(venEmail);
			vendorVO.setVenTel(venTel);
			vendorVO.setVenLocation(venLocation);
			vendorVO.setVenWin(venWin);
			vendorVO.setVenWintel(venWinTel);
			vendorVO.setVenIntro(venIntro);
			vendorVO.setVenCanpolicy(venCanPolicy);
			vendorVO.setVenNotice(venNotice);
			vendorVO.setVenBank(venBank);
			vendorVO.setVenStatus(venStatus);
			vendorVO.setVenScore(venScore);
			vendorVO.setVenScorePeople(venScorePeople);
			vendorVO.setVenJoinDate(venJoinDate);
			vendorVO.setVenId(venId);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("vendorVO", vendorVO); // �t����J�榡���~��empVO����,�]�s�Jreq
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ven/VenUpDate.jsp");
				failureView.forward(req, res);
				return; // �{�����_
			}

			/*************************** 2.開始更新資料 *****************************************/
			VendorService venSvc = new VendorService();
			vendorVO = venSvc.updateVendor(venPw, venName, venTaxNum, venEmail, venTel, venLocation, venWin,
					venWinTel, venIntro, venCanPolicy, venNotice, venBank, venStatus, venScore, venScorePeople,
					venJoinDate,venId);

			/*************************** 完成，準備轉交(Send the Success view) *************/
			req.setAttribute("vendorVO", vendorVO); // ��Ʈwupdate���\��,���T����empVO����,�s�Jreq
			String url = "/back-end/ven/VenListAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �ק令�\��,���listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);

			List<String> errorMsgs1 = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs1);

			/*********************** 1. *************************/
			String venPw = req.getParameter("venPw").trim();
			String venName = req.getParameter("venName");

			if (venName == null || venName.trim().length() == 0) {
				errorMsgs1.add("廠商名稱請勿空白");
			} 

			String venTaxNum = req.getParameter("venTaxNum").trim();
			if (venTaxNum == null || venTaxNum.trim().length() == 0) {
				errorMsgs1.add("統一編號請勿空白");
			}

			String venEmail = String.valueOf(req.getParameter("venEmail").trim());
			if (venEmail == null || venEmail.trim().length() == 0) {
				errorMsgs1.add("電子信箱請勿空白");
			}

			String venTel = req.getParameter("venTel").trim();
			if (venTel == null || venTel.trim().length() == 0) {
				errorMsgs1.add("廠商電話請勿空白");
			}

			String venLocation = req.getParameter("venLocation").trim();
			if (venLocation == null || venLocation.trim().length() == 0) {
				errorMsgs1.add("旅宿地址請勿空白");
			}

			String venWin = req.getParameter("venWin").trim();
			if (venWin == null || venWin.trim().length() == 0) {
				errorMsgs1.add("廠商窗口請勿空白");
			}
			String venWinTel = req.getParameter("venWinTel").trim();
			if (venWinTel == null || venWinTel.trim().length() == 0) {
				errorMsgs1.add("窗口電話請勿空白");
			}
			String venIntro = req.getParameter("venIntro").trim();
			if (venIntro == null || venIntro.trim().length() == 0) {
				errorMsgs1.add("旅宿介紹請勿空白");
			}

			String venCanPolicy = req.getParameter("venCanPolicy").trim();
			if (venCanPolicy == null || venCanPolicy.trim().length() == 0) {
				errorMsgs1.add("取消政策請勿空白");
			}
			String venNotice = req.getParameter("venNotice").trim();
			if (venNotice == null || venNotice.trim().length() == 0) {
				errorMsgs1.add("入住須知請勿空白");
			}
			String venBank = req.getParameter("venBank").trim();
			if (venBank == null || venBank.trim().length() == 0) {
				errorMsgs1.add("銀行帳號請勿空白");
			}

			Integer venStatus = Integer.valueOf(req.getParameter("venStatus"));
			Integer venScorePeople = Integer.valueOf(req.getParameter("venScorePeople"));
			java.sql.Date venJoinDate = null;
			try {
				venJoinDate = java.sql.Date.valueOf(req.getParameter("venJoinDate").trim());
			} catch (IllegalArgumentException e) {
				venJoinDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs1.add("時間錯誤");
			}

			VendorVO vendorVO = new VendorVO();
			vendorVO.setVenPw(venPw);
			vendorVO.setVenName(venName);
			vendorVO.setVenTaxnum(venTaxNum);
			vendorVO.setVenEmail(venEmail);
			vendorVO.setVenTel(venTel);
			vendorVO.setVenLocation(venLocation);
			vendorVO.setVenWin(venWin);
			vendorVO.setVenWintel(venWinTel);
			vendorVO.setVenIntro(venIntro);
			vendorVO.setVenCanpolicy(venCanPolicy);
			vendorVO.setVenNotice(venNotice);
			vendorVO.setVenBank(venBank);
			vendorVO.setVenStatus(venStatus);
			vendorVO.setVenScorePeople(venScorePeople);
			vendorVO.setVenJoinDate(venJoinDate);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs1.isEmpty()) {
				req.setAttribute("vendorVO", vendorVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/ven/VenAddPage.jsp");
				failureView.forward(req, res);
				return;
			}

			/**************************** 2. ***************************************/
			VendorService venSvc = new VendorService();
			vendorVO = venSvc.addVendor(venPw, venName, venTaxNum, venEmail, venTel, venLocation, venWin, venWinTel,
					venIntro, venCanPolicy, venNotice, venBank, venStatus,  venScorePeople, venJoinDate);

			/**************************** 3.�s�W����,�ǳ����?(Send the Success view)
			 ***********/
			Integer venId = venSvc.getVendorByTax(venTaxNum).getVenId();
			req.setAttribute("venId", venId);
			String url = "/back-end/ven/VenAddPic.jsp";
			

			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
			
			
		}



		if ("venListAll_ByCompositeQuery".equals(action)) { // 嚙諉佗蕭select_page.jsp嚙踝蕭嚙複合嚙範嚙賠請求
			List<String> QueryerrorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("QueryerrorMsgs", QueryerrorMsgs);

			/***************************1 **********************************/
			

			HttpSession session = req.getSession();
			Map<String, String[]> map = (Map<String, String[]>) session.getAttribute("map");

			if (req.getParameter("whichPage") == null) {
				Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
				session.setAttribute("map", map1);
				map = map1;
			}

			/***************************2 ***************************************/
			
			VendorService venSvc = new VendorService();
			List<VendorVO> list = venSvc.getAll(map);

			/***************************3 ************/
			
			req.setAttribute("venListAll_ByCompositeQuery", list); // 嚙踝蕭w嚙踝蕭嚙碼嚙踝蕭list嚙踝蕭嚙踝蕭,嚙編嚙皚request
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/ven/VenListAll_ByCompositeQuery.jsp"); // 嚙踝蕭嚙穀嚙踝蕭嚙締istEmps_ByCompositeQuery.jsp
			successView.forward(req, res);
		}
		
		if ("getOneVenPic".equals(action)) {
			
			 res.setContentType("image/jpeg");
/*************************** 1.接收請求參數 ****************************************/
			  Integer venPicid =  Integer.valueOf(req.getParameter("venPicid"));
/*************************** 2.開始新增資料 ****************************************/
			  VendorPicService venPicSvc = new VendorPicService();
			  VendorPicVO oneVendorpic = venPicSvc.getOneVendorpic(venPicid);
			  byte[] pic = oneVendorpic.getVenPic();
			  try(ServletOutputStream out1 = res.getOutputStream()){
				  out1.write(pic);
			  }catch (Exception e) {
				e.printStackTrace();
			 }
			  return;
		}
		
		if ("deleteVenPic".equals(action)) {
			
			/*************************** 1.接收請求參數 ****************************************/
			Integer venPicid =  Integer.valueOf(req.getParameter("venPicid"));
			/*************************** 2.開始新增資料 ****************************************/
			VendorPicService venPicSvc = new VendorPicService();
			Integer venId = venPicSvc.getOneVendorpic(venPicid).getVenId();
			venPicSvc.deleteVendorpic(venPicid);
			String url = "/VenServlet?action=getOne_For_Update&venId=" + venId;
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
			
		if ("venPicUp".equals(action)) {
			Integer venId =  Integer.valueOf(req.getParameter("venId"));
			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			int picCount = 1;
			for (Part part : parts) {
				if(picCount <= parts.size()-2) {
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 的新方法
					in.read(buf);
					in.close();
					VendorPicService vendorPicService = new VendorPicService();
					vendorPicService.addVendorpic(venId, buf);
				}
				picCount++;
			}
			String url = "/VenServlet?action=getOne_For_Update&venId=" + venId;
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		if ("picUpload".equals(action)) {
			Integer venId =  Integer.valueOf(req.getParameter("venId"));
			
		
			
			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理
			int picCount = 1;
			for (Part part : parts) {
				if(picCount <= parts.size()-2) {
					InputStream in = part.getInputStream();
					byte[] buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 的新方法
					in.read(buf);
					in.close();
					VendorPicService vendorPicService = new VendorPicService();
					vendorPicService.addVendorpic(venId, buf);
				}
				picCount++;
			}
			String url = "/VenServlet?action=getOne_For_Update&venId=" + venId;
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			return;
		}
		
		if ("delete".equals(action)) { // �Ӧ�listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.�����ШD�Ѽ�***************************************/
			Integer venId =  Integer.valueOf(req.getParameter("venId"));
				
				/***************************2.�}�l�R�����?***************************************/
				
				try {
					VendorService venSvc = new VendorService();
					venSvc.deleteVendor(venId);
				} catch (Exception e) {
					
					
					errorMsgs.add("無法刪除");
				}
				
				/***************************3.�R������,�ǳ����?(Send the Success view)***********/								
				String url = "/back-end/ven/VenListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
		}
		
		
	}
}
