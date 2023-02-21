package com.article.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.article.model.*;
import com.article_reply.model.ArticleReplyService;
import com.article_reply.model.ArticleReplyVO;
import com.article_report.model.ArticleReportService;
import com.article_report.model.ArticleReportVO;
import com.Users.model.UsersService;
import com.Users.model.UsersVO;
import com.Users.model.*;

@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
@WebServlet("/article") // 等同到web.xml註冊
public class ArticleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		// 查
		if ("userPosted".equals(action)) { // 成功後進到前台會員頁面 (我的文章)
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
			try {
				if (usersVO == null) {
					res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
					return;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			Integer userId = null;
			String userIdParam = req.getParameter("userId");
			if (userIdParam != null && !userIdParam.isEmpty()) {
				userId = Integer.valueOf(userIdParam);
			}

			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> mypost = articleSvc.findMyPost(userId);
			req.setAttribute("mypost", mypost);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/userPosted.jsp");
			successView.forward(req, res);
		}

		if ("forumHome".equals(action)) { // 進到論壇首頁
			ArticleService artSvc = new ArticleService();
			ArticleReportVO articleReportVO = new ArticleReportVO();
			List<ArticleVO> hotlist = artSvc.findTopUserAllPost();
			req.setAttribute("hotlist", hotlist);
			List<ArticleVO> list = artSvc.getAllShowStatus();
			req.setAttribute("articleVOlist", list);
			req.setAttribute("articleReportVO", articleReportVO);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/forumHome.jsp");
			successView.forward(req, res);
		}

		if ("listAllArt".equals(action)) { // 成功後進到後台文章管理頁面
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> allpost = articleSvc.getAll();
			req.setAttribute("allpost", allpost);
			RequestDispatcher successView = req.getRequestDispatcher("/back-end/article/listAllArtPost.jsp");
			successView.forward(req, res);
		}

		if ("SelectOnePost_Display".equals(action)) { // 來自forumHome.jsp 論壇首頁的請求 成功進到單一文章頁面
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			UsersVO usersVO = null;
			try {
				usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			Integer userId = null;
			String userIdParam = req.getParameter("userId");
			if (userIdParam != null && !userIdParam.isEmpty()) {
				userId = Integer.valueOf(userIdParam);
			}
			Integer artId = null;
			String artIdParam = req.getParameter("artId");
			if (artIdParam != null && !artIdParam.isEmpty()) {
				artId = Integer.valueOf(artIdParam);
			}
			/*************************** 2.開始查詢資料 *****************************************/
			if (artId != null) {
				ArticleService articleSvc = new ArticleService();
				articleSvc.updateShowCount(artId); // 觀看次數加1
				ArticleVO articleVO = articleSvc.getOneArticle(artId); // 依照文章編號選取單一文章頁面
				ArticleReplyService articleReplySvc = new ArticleReplyService();
				List<ArticleReplyVO> articleReplyVOList = articleReplySvc.getOnePostAllMsg(artId); // 顯示單一文章所有留言
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("userId", userId);
				req.setAttribute("usersVO", usersVO);
				req.setAttribute("articleVO", articleVO);
				req.setAttribute("articleReplyVOList", articleReplyVOList);
				String url = "/front-end/article/viewOnePost.jsp"; // 轉交單一文章頁面
				req.getRequestDispatcher(url).forward(req, res);
			}
		}

		// 修改

		if ("UserPost1_Update".equals(action)) { // 前台會員頁面(我的文章) 按 "修改" 進到 會員文章修改頁面
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 ****************************************/
			Integer artId = Integer.valueOf(req.getParameter("artId"));
			/*************************** 2.開始查詢資料 ****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(artId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("articleVO", articleVO); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/article/updateUserPost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("UserPost2_Update".equals(action)) { // 來自updateUserPost.jsp的請求 會員文章修改頁面 按送出 回到 前台會員頁面(我的文章)
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer artId = Integer.valueOf(req.getParameter("artId").trim()); // 不像insert需要做驗證
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			Integer artTypeId = Integer.valueOf(req.getParameter("artTypeId"));
			String artTitle = req.getParameter("artTitle").trim();
			if (artTitle == null || artTitle.trim().length() == 0) {
				errorMsgs.add("文章標題要記得填! 不要忘瞜(//●⁰౪⁰●)//");
			} else if (artTitle.trim().length() > 100) {
				errorMsgs.add("標題太長記不住啦  盡量1到100字之間~答應我(,,Ծ‸Ծ,, )");
			}
			String artContent = req.getParameter("artContent").trim();
			if (artContent == null || artContent.trim().length() == 0) {
				errorMsgs.add("空白可是不行的唷 都來了.. (ఠ్ఠ ˓̭ ఠ్ఠ)");
			}
			ArticleVO articleVO = new ArticleVO();
			articleVO.setArtId(artId);
			articleVO.setUserId(userId);
			articleVO.setArtTypeId(artTypeId);
			articleVO.setArtTitle(artTitle);
			articleVO.setArtContent(artContent);

			// Send the use back to the form, if there were errors 前面只要有任何一個發生錯誤
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleVO", articleVO); // 含有輸入格式錯誤的articleVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/updateUserPost.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO oneArticle = articleSvc.getOneArticle(artId);

			oneArticle.setArtId(artId);
			oneArticle.setUserId(userId);
			oneArticle.setArtTypeId(artTypeId);
			oneArticle.setArtTitle(artTitle);
			oneArticle.setArtContent(artContent);
			articleSvc.updateArticle(oneArticle);
			List<ArticleVO> mypost = articleSvc.findMyPost(userId);
			req.setAttribute("mypost", mypost);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/front-end/article/userPosted.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("AllArtUpdate1".equals(action)) { // 後台文章管理 按 "修改狀態" 進到 文章修改頁面
			/*************************** 1.接收請求參數 ****************************************/
			Integer artId = Integer.valueOf(req.getParameter("artId"));
			/*************************** 2.開始查詢資料 ****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(artId);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("articleVO", articleVO);
			String url = "/back-end/article/updateArtPost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("AllArtUpdate2".equals(action)) { // 來自updateArtPost.jsp的請求 文章修改頁面 按送出 回到 後台文章管理頁面
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer artId = Integer.valueOf(req.getParameter("artId"));
			Integer artTypeId = Integer.valueOf(req.getParameter("artTypeId"));
			Integer artStatus = Integer.valueOf(req.getParameter("artStatus"));
			/*************************** 2.開始修改資料 *****************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(artId);
			articleVO.setArtId(artId);
			articleVO.setArtTypeId(artTypeId);
			articleVO.setArtStatus(artStatus);
			articleSvc.updateArticle(articleVO);

			List<ArticleVO> allpost = articleSvc.getAll();
			req.setAttribute("allpost", allpost);
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/back-end/article/listAllArtPost.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); //
			successView.forward(req, res);
		}

		// 新增

		if ("addart".equals(action)) { // 從論壇首頁按發佈文章
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
			try {
				if (usersVO == null) {
					res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
					return;
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			req.setAttribute("usersVO", usersVO);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/addArtPost.jsp");
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 發文頁面按送出 成功回到論壇首頁
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer userId = null; // Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			try {
				userId = Integer.valueOf(req.getParameter("userId").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Integer artTypeId = null;
			try {
				artTypeId = Integer.valueOf(req.getParameter("artTypeId").trim());
			} catch (NumberFormatException e) {
				artTypeId = 0;
				errorMsgs.put("", "別忘了勾選主題唷~");
			}
			String artTitle = req.getParameter("artTitle").trim();
			if (artTitle == null || artTitle.trim().length() == 0) {
				errorMsgs.put("", "標題記得填唷~");
			} else if (artTitle.trim().length() > 100) {
				errorMsgs.put("", "標題長度要在1到100之間~溫馨提醒");
			}
			String artContent = req.getParameter("artContent").trim();
			if (artContent == null || artContent.trim().length() == 0) {
				errorMsgs.put("", " 記得填內容喔~~我們很期待你寶貴的文字");
			}
			// 照片
			InputStream in = req.getPart("artPic").getInputStream(); // 從javax.servlet.http.Part物件取得上傳檔案的InputStream
			byte[] artPic = null;
			if (in.available() != 0) {
				artPic = new byte[in.available()];
				in.read(artPic);
				in.close();
			} else
				errorMsgs.put("", " 記得上傳照片<@0@>");

			ArticleVO articleVO = new ArticleVO();
			articleVO.setUserId(userId);
			articleVO.setArtTypeId(artTypeId);
			articleVO.setArtTitle(artTitle);
			articleVO.setArtContent(artContent);
			articleVO.setArtPic(artPic);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("articleVO", articleVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/addArtPost.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 2.開始新增資料 ***************************************/
			ArticleService artSvc = new ArticleService();
			artSvc.addArticle(userId, artTypeId, artTitle, artContent, artPic);
			List<ArticleVO> list = artSvc.getAllShowStatus();
			req.setAttribute("list", list);
			List<ArticleVO> hotlist = artSvc.findTopUserAllPost();
			req.setAttribute("hotlist", hotlist);
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
			req.setAttribute("usersVO", usersVO);
			String url = "/front-end/article/forumHome.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		// 刪除
		if ("UserPost_delete".equals(action)) {
			/*************************** 1.接收請求參數 ***************************************/
			Integer artId = Integer.valueOf(req.getParameter("artId"));
			Integer userId = Integer.valueOf(req.getParameter("userId").trim());
			/*************************** 2.開始刪除資料 ***************************************/
			ArticleService articleSvc = new ArticleService();
			ArticleVO articleVO = articleSvc.getOneArticle(artId);
			articleVO.setArtStatus(1);
			articleSvc.updateArticle(articleVO);
			List<ArticleVO> mypost = articleSvc.findMyPost(userId);
			req.setAttribute("mypost", mypost);
			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/article/userPosted.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}

		if ("SearchByArtType".equals(action)) {
			int artTypeId = Integer.valueOf(req.getParameter("artTypeId"));
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> list = articleSvc.findPostType(artTypeId);
			req.setAttribute("articleVOlist", list);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/searchArtType.jsp");
			successView.forward(req, res);
		}

		if ("SearchByKeyword".equals(action)) { // 搜尋框關鍵字搜尋

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("keyword");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入關鍵字");
			}
			/*************************** 2.開始查詢資料 *****************************************/
			ArticleService artSvcArticleService = new ArticleService();
			List<ArticleVO> artList = artSvcArticleService.searchByKeyword(str);
//			System.out.println("size =" + artList.size());

			if (artList == null || artList.size() == 0) {
				errorMsgs.add("查無相關文章");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/article/forumHome.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("artList", artList);
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/searchbyKeyWord.jsp");
			successView.forward(req, res);
		}

		if ("powerSearchAllPost".equals(action)) { // 搜尋框搜全部
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			// 存放錯誤訊息 以防我們需要丟出錯誤訊息到頁面
			req.setAttribute("errorMsgs", errorMsgs);
			/*************************** 1.將輸入資料轉為Map **********************************/
			// 採用Map<String,String[]> getParameterMap()的方法
			// 注意:an immutable java.util.Map
			Map<String, String[]> map = req.getParameterMap();
			/*************************** 2.開始複合查詢 ***************************************/
			ArticleService articleSvc = new ArticleService();
			List<ArticleVO> powerList = articleSvc.getAllPowerSearch(map);
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.getSession().setAttribute("powerList", powerList); // 資料庫取出的list物件,存入request
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/article/searchAll.jsp"); // 成功轉交searchAll.jsp
			successView.forward(req, res);
		}

		if ("DBGifReader".equals(action)) {
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				Integer artId = Integer.valueOf(req.getParameter("artId"));
				ArticleService artSvc = new ArticleService();
				out.write(artSvc.getOneArticle(artId).getArtPic());
			} catch (Exception e) {
				InputStream in = getServletContext().getResourceAsStream("/front-end/article/image/artpic/13.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();

			}
		}

		if ("DBGifReaderUser".equals(action)) {
			res.setContentType("image/gif");
			ServletOutputStream out = res.getOutputStream();

			try {
				Integer userId = Integer.valueOf(req.getParameter("userId"));
				UsersService userSvc = new UsersService();
				out.write(userSvc.getOneUser(userId).getUserPic());
			} catch (Exception e) {
				InputStream in = getServletContext()
						.getResourceAsStream("/front-end/article/image/artpic_person/4.png");
				byte[] buf = new byte[in.available()];
				in.read(buf);
				out.write(buf);
				in.close();
			}
		}

	}
}
