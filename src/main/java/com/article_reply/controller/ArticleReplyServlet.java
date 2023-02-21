package com.article_reply.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.article_reply.model.ArticleReplyService;
import com.article_reply.model.ArticleReplyVO;
import com.Users.model.UsersVO;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
@WebServlet("/article/reply") // 等同到web.xml註冊
public class ArticleReplyServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert_reply".equals(action)) { // 來自viewOnePost.jsp的請求
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");

			Integer userId = null;
			try {
				userId = Integer.valueOf(req.getParameter("userId").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			Integer artId = Integer.valueOf(req.getParameter("artId").trim());
			Integer replyStatus = Integer.valueOf(0);
		
			String replyContent = req.getParameter("replyContent").trim();
			if (replyContent == null || replyContent.trim().length() == 0) {
				req.setAttribute("errorMsg", "沒有文字大家看不懂耶 記得填~~");

				ArticleService articleSvc = new ArticleService();
				ArticleVO articleVO = articleSvc.getOneArticle(artId);
				ArticleReplyService articleReplySvc = new ArticleReplyService();
				List<ArticleReplyVO> articleReplyVOList = articleReplySvc.getOnePostAllMsg(artId);

				req.setAttribute("articleVO", articleVO);
				req.setAttribute("articleReplyVOList", articleReplyVOList);
				req.setAttribute("userId", userId);
				req.setAttribute("usersVO", usersVO);
				String url = "/front-end/article/viewOnePost.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			// 新增資料
			ArticleReplyService articleReplySvc = new ArticleReplyService();
			articleReplySvc.addArticleReply(artId, userId, replyStatus, replyContent);
			/*************************** 3.收尋資料 ***************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(artId);
			List<ArticleReplyVO> articleReplyVOList = articleReplySvc.getOnePostAllMsg(artId);
			/*************************** 4.新增完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("usersVO", usersVO);
			req.setAttribute("userId", userId);
			req.setAttribute("articleVO", articleVO);
			req.setAttribute("articleReplyVOList", articleReplyVOList);
			String url = "/front-end/article/viewOnePost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("myMsgDelete".equals(action)) {

			// 1.接收請求參數
			Integer artId = Integer.valueOf(req.getParameter("artId").trim());
			Integer userId = null;
			try {
				userId = Integer.valueOf(req.getParameter("userId").trim());
			} catch (NullPointerException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Integer artReplyId = Integer.valueOf(req.getParameter("artReplyId").trim());
			Integer replyStatus = Integer.valueOf(1);
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");

			// 2.開始修改資料 (呼叫 ArticleReplyService 的 updateArtReplyStatus 方法修改 artReplyId 的狀態)
			ArticleReplyService articleReplyService = new ArticleReplyService();
			articleReplyService.updateArtReplyStatus(artReplyId, replyStatus);

			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(artId);
			List<ArticleReplyVO> articleReplyVOList = articleReplyService.getOnePostAllMsg(artId);
			// 3.查詢完成,準備轉交(Send the Success view) (將 artId 和 errorMsgs 存入 request，並將 request
			// 轉交到 "selectOnePostAllMsg")
			req.setAttribute("articleVO", articleVO);
			req.setAttribute("userId", userId);
			req.setAttribute("articleReplyVOList", articleReplyVOList);
			req.setAttribute("usersVO", usersVO);

			String url = "/front-end/article/viewOnePost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
	}

}
