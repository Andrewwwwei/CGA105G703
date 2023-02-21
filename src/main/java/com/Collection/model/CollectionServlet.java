package com.Collection.model;



import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ColArt.model.ColArtService;
import com.ColIti.model.ColItiService;
import com.ColVen.model.ColVenService;
import com.Users.model.UsersVO;
import com.article.model.ArticleService;
import com.article.model.ArticleVO;
import com.article_reply.model.ArticleReplyService;
import com.article_reply.model.ArticleReplyVO;
import com.ColArt.model.ColArtVO;



@MultipartConfig
@WebServlet("/collectionServlet")
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html;charset=utf-8");
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");
		
		
		if ("colartinsert".equals(action)) {
		    Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
		    req.setAttribute("errorMsgs", errorMsgs);
		    // session 取得會員編號
		    UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
		    Integer userId = null;
		    if (usersVO != null) {
		        userId = usersVO.getUserId();
		    }

		    // 若使用者未登入，則跳轉到登入頁面
		    if (userId == null) {
		        res.sendRedirect(req.getContextPath() + "/front-end/member/login.jsp");
		        return;
		    }
		    
		    Integer artId = Integer.valueOf(req.getParameter("artId"));
		    ColArtService colArtSvc = new ColArtService();
		    ColArtVO colArtVO = colArtSvc.getColArt(userId, artId);

		    if (colArtVO == null) {
		        // 如果用戶沒收藏 就收藏起來
		        colArtSvc.addColArt(artId, userId);
		    } else {
		        // 如果已經收藏 就取消收藏
		        colArtSvc.deleteColArt(artId,userId);
		    }

		    // 跳回單一文章頁面
		    ArticleService articleSvc = new ArticleService();
		    ArticleVO articleVO = articleSvc.getOneArticle(artId);
		    ArticleReplyService articleReplySvc = new ArticleReplyService();
		    List<ArticleReplyVO> articleReplyVOList = articleReplySvc.getOnePostAllMsg(artId);
		    req.setAttribute("userId", userId);
		    req.setAttribute("usersVO", usersVO);
		    req.setAttribute("articleVO", articleVO);
		    req.setAttribute("articleReplyVOList", articleReplyVOList);
		    String url = "/front-end/article/viewOnePost.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}
		
		if ("deleteArt".equals(action)) {
            // 取消收藏文章
            Integer artId = Integer.valueOf(req.getParameter("artId"));
            Integer userId = Integer.valueOf(req.getParameter("userId"));

            // 取消收藏
            ColArtService colArtSvc = new ColArtService();
            colArtSvc.deleteColArt(artId, userId);

            // 返回到我的收藏頁面
            RequestDispatcher successView = req.getRequestDispatcher("/front-end/collection/myArt.jsp");
            successView.forward(req, res);
        }

		
		if ("deleteVen".equals(action)) { // 來自collection.jsp

				/***************************1.接收請求參數***************************************/
				Integer venId = Integer.valueOf(req.getParameter("venId"));
				Integer userId = Integer.valueOf(req.getParameter("userId"));
	
				/***************************2.開始刪除資料***************************************/
				ColVenService colVenSvc = new ColVenService();
				colVenSvc.deleteColVen(venId,userId);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				RequestDispatcher successView = req.getRequestDispatcher("/front-end/collection/collection.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		if ("deleteArt".equals(action)) { // 來自myArt.jsp

			/***************************1.接收請求參數***************************************/
			Integer artId = Integer.valueOf(req.getParameter("artId"));
			Integer userId = Integer.valueOf(req.getParameter("userId"));

			/***************************2.開始刪除資料***************************************/
			ColArtService colArtSvc = new ColArtService();
			colArtSvc.deleteColArt(artId,userId);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/collection/myArt.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	}
		if ("deleteTrip".equals(action)) { // 來自myTrip.jsp

			/***************************1.接收請求參數***************************************/
			Integer tripShareId = Integer.valueOf(req.getParameter("tripShareId"));
			Integer userId = Integer.valueOf(req.getParameter("userId"));

			/***************************2.開始刪除資料***************************************/
			ColItiService colItiSvc = new ColItiService();
			colItiSvc.deleteColIti(tripShareId,userId);
			
			/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/collection/myTrip.jsp");// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
	}
		if ("loginout".equals(action)) {// 來自loginout.jsp的請求
			req.getSession().invalidate(); 
 res.sendRedirect(req.getContextPath()+"/front-end/member/login.jsp");
		
		}
		if ("collection".equals(action)) {// 來自loginout.jsp的請求
			UsersVO usersVO=(UsersVO) req.getSession().getAttribute("usersVO");
			try {
				if(usersVO==null) {
				res.sendRedirect(req.getContextPath()+"/front-end/member/login.jsp");
					return;
				}
			} catch (NullPointerException e) {
					e.printStackTrace();
			}
			RequestDispatcher successView = req.getRequestDispatcher("/front-end/collection/collection.jsp");
			successView.forward(req, res);
		}
	}

}
