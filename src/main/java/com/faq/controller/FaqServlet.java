package com.faq.controller;

import java.io.IOException;

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



import com.faq.model.FaqService;
import com.faq.model.FaqVO;


@WebServlet("/FAQServlet")
public class FaqServlet extends HttpServlet {
	
		public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			doPost(req, res);
		}

		public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

			req.setCharacterEncoding("UTF-8");
			String action = req.getParameter("action");
		

			
			if ("getOne_For_Update".equals(action)) {

				List<String> faqUpErr = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("faqUpErr", faqUpErr);

				/*************************** 1.接收請求參數 ****************************************/
				Integer faqId = Integer.valueOf(req.getParameter("faqId"));

				/*************************** 2.開始新增資料 ****************************************/
				FaqService faqSvc = new FaqService();
				FaqVO faqVO = faqSvc.getOneFaq(faqId);

				/*************************** 3.完成，準備轉交(Send the Success view) ************/
				req.setAttribute("faqVO",faqVO); // 
				String url = "/back-end/faq/FAQListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 
				successView.forward(req, res);
			}

			if ("update".equals(action)) { 

				List<String> faqUpErr = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("faqUpErr", faqUpErr);

				/*************************** 1.接收請求參數 **********************/
				Integer faqId = Integer.valueOf(req.getParameter("faqId"));
				System.out.println(faqId);
				String faqTitle = req.getParameter("faqTitle").trim();
				if (faqTitle == null || faqTitle.trim().length() == 0) {
					faqUpErr.add("標題請勿空白");
				}
				String faqContent = req.getParameter("faqContent").trim();
				if (faqContent == null || faqContent.trim().length() == 0) {
					faqUpErr.add("內容請勿空白");
				} 

				FaqVO faqVO = new FaqVO();
				
				faqVO.setFaqTitle(faqTitle);
				faqVO.setFaqContent(faqContent);
				faqVO.setFaqId(faqId);
				

				// Send the use back to the form, if there were errors
				if (!faqUpErr.isEmpty()) {
					req.setAttribute("faqVO", faqVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/faq/FAQListAll.jsp");
					failureView.forward(req, res);
					return; 
				}

				/*************************** 2.開始更新資料 *****************************************/
				FaqService faqSvc = new FaqService();
				faqVO = faqSvc.updateFaq(faqTitle, faqContent,faqId);

				/*************************** 完成，準備轉交(Send the Success view) *************/
				req.setAttribute("faqVO", faqVO); 
				String url ="/back-end/faq/FAQListAll.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				return;
			}

			if ("insert".equals(action)) {

				Map<String, String> faqAddErr = new LinkedHashMap<String, String>();
				req.setAttribute("faqAddErr", faqAddErr);

				List<String> faqAddErrL = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("faqAddErrL", faqAddErrL);

				/*********************** 1. *************************/
				String faqTitle = req.getParameter("faqTitle").trim();
				if (faqTitle == null || faqTitle.trim().length() == 0) {
					faqAddErrL.add("標題請勿空白");
				}
				String faqContent = req.getParameter("faqContent").trim();
				if (faqContent == null || faqContent.trim().length() == 0) {
					faqAddErrL.add("內容請勿空白");
				} 
				

				FaqVO faqVO = new FaqVO();
				faqVO.setFaqContent(faqContent);
				faqVO.setFaqTitle(faqTitle);
				


				// Send the use back to the form, if there were errors
				if (!faqAddErrL.isEmpty()) {
					req.setAttribute("faqVO", faqVO);
					RequestDispatcher failureView = req.getRequestDispatcher("/back-end/faq/FAQListAll.jsp");
					failureView.forward(req, res);
					return;
				}

				/**************************** 2. ***************************************/
				FaqService faqSvc = new FaqService();
				faqVO = faqSvc.addFaq(faqContent, faqTitle);

				/**************************** 3.***********/
				String url = "/back-end/faq/FAQListAll.jsp";
				

				RequestDispatcher successView = req.getRequestDispatcher(url); 
				successView.forward(req, res);
				
				
			}

			if ("delete".equals(action)) { 

				List<String> faqDeleteErr = new LinkedList<String>();
				// Store this set in the request scope, in case we need to
				// send the ErrorPage view.
				req.setAttribute("faqDeleteErr", faqDeleteErr);
		
					/***************************1.�����ШD�Ѽ�***************************************/
					Integer faqId = Integer.valueOf(req.getParameter("faqId"));
					
					/***************************2.�}�l�R�����?***************************************/
					
					try {
						FaqService faqSvc = new FaqService();
						faqSvc.deleteFaq(faqId);
					} catch (Exception e) {
						
						
						faqDeleteErr.add("無法刪除，請確認權限設定");
					}
					
					/***************************3.�R������,�ǳ����?(Send the Success view)***********/								
					String url = "/back-end/faq/FAQListAll.jsp";
					RequestDispatcher successView = req.getRequestDispatcher(url);
					successView.forward(req, res);
			}
			
			
		}

	}
	

