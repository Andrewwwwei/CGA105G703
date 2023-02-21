package com.article_report.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.Users.model.UsersVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.article_reply.model.ArticleReplyService;
import com.article_reply.model.ArticleReplyVO;
import com.article_report.model.ArticleReportService;
import com.article_report.model.ArticleReportVO;

@WebServlet("/articleReport") // 等同到web.xml註冊
public class ArticleReportServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 查
		if ("listAllArtReport".equals(action)) { // 成功後進到後台文章檢舉
			ArticleReportService articleRepSvc = new ArticleReportService();
			List<ArticleReportVO> allRepPost = articleRepSvc.getAll();
			req.setAttribute("allRepPost", allRepPost);
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/article/listAllReport.jsp");
			successView.forward(req, res);
		}

		// 新增

		if ("addreport".equals(action)) { // 單一文章頁按檢舉按鈕到檢舉頁
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
			try {
				if (usersVO == null) {
					res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
					return;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			Integer artId = null;
			String artIdParam = req.getParameter("artId");
			if (artIdParam != null && !artIdParam.isEmpty()) {
				artId = Integer.valueOf(artIdParam);
			}
			if (artId != null) {
				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(artId);
				req.setAttribute("articleVO", articleVO);
				req.setAttribute("usersVO", usersVO);
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/addReport.jsp");
				successView.forward(req, res);
			}
		}

		if ("InsertReport".equals(action)) { // 檢舉頁面 成功回到單一文章
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer artId = null;
			String artIdParam = req.getParameter("artId");
			if (artIdParam != null && !artIdParam.isEmpty()) {
				artId = Integer.valueOf(artIdParam);
			}
			Integer userId = null;
			String userIdParam = req.getParameter("userId");
			if (userIdParam != null && !userIdParam.isEmpty()) {
			    userId = Integer.valueOf(userIdParam);
			}
			
			Integer rpReason = Integer.valueOf(req.getParameter("rpReason").trim());
			String rpContent = req.getParameter("rpContent").trim();
			if (rpContent == null || rpContent.trim().length() == 0) {
				errorMsgs.add(" 記得填內容喔~~我們很期待你寶貴的文字");
			}
			Integer rpStatus = Integer.valueOf(0);
			
			
			ArticleReportVO articleReportVO = new ArticleReportVO();
			articleReportVO.setArtId(artId);
			articleReportVO.setUserId(userId);
			articleReportVO.setRpReason(rpReason);
			articleReportVO.setRpContent(rpContent);
			articleReportVO.setRpStatus(rpStatus);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleReportVO", articleReportVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addReport.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			ArticleReportService artrepSvc = new ArticleReportService();
			ArticleReportVO onearticleReportVO = new ArticleReportVO();
			onearticleReportVO.setArtId(artId);
			onearticleReportVO.setUserId(userId);
			onearticleReportVO.setRpReason(rpReason);
			onearticleReportVO.setRpContent(rpContent);
			onearticleReportVO.setRpStatus(rpStatus);
			artrepSvc.addArticleReport(userId,rpReason,rpContent,rpStatus,artId);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(artId); // 依照文章編號選取單一文章頁面
			ArticleReplyService articleReplySvc = new ArticleReplyService();
			List<ArticleReplyVO> articleReplyVOList = articleReplySvc.getOnePostAllMsg(artId); // 顯示單一文章所有留言
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
			try {
				if (usersVO == null) {
					res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
					return;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			req.setAttribute("userId", userId);
			req.setAttribute("usersVO", usersVO);
			req.setAttribute("articleVO", articleVO);
			req.setAttribute("articleReplyVOList", articleReplyVOList);
			String url = "/front-end/article/viewOnePost.jsp"; // 轉交單一文章頁面
			req.getRequestDispatcher(url).forward(req, res);
		}
	

		// 修改
		
		if ("allReportUpdate1".equals(action)) { // 後台文章檢舉管理 按 "修改狀態" 進到 文章檢舉修改頁面
			/*************************** 1.接收請求參數 ****************************************/
			Integer artRpId = Integer.valueOf(req.getParameter("artRpId"));
			/*************************** 2.開始查詢資料 ****************************************/
			ArticleReportService articleRepSvc = new ArticleReportService();
			ArticleReportVO articleReportVO = articleRepSvc.getOneArticleReport(artRpId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("articleReportVO", articleReportVO);
			String url = "/back-end/article/updateArtReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("allReportUpdate2".equals(action)) { // 來自updateArtPost.jsp的請求 文章修改頁面 按送出 回到 後台文章管理頁面
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer artRpId = Integer.valueOf(req.getParameter("artRpId"));
			Integer rpStatus = Integer.valueOf(req.getParameter("rpStatus"));

			/*************************** 2.開始修改資料 *****************************************/
			ArticleReportService articleRepSvc = new ArticleReportService();
			ArticleReportVO articleReportVO = articleRepSvc.getOneArticleReport(artRpId);
//			System.out.println(articleReportVO.getArtRpId());
//			System.out.println(articleReportVO.getRpReason());
			articleReportVO.setRpStatus(rpStatus);
			articleRepSvc.updateArtRep(articleReportVO);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			List<ArticleReportVO> allRepPost = articleRepSvc.getAll();
			req.setAttribute("allRepPost", allRepPost);
			String url = "/back-end/article/listAllReport.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
	}
}
