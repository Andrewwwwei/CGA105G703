package com.locationPic.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.locationPic.model.LocationPicService;
import com.locationPic.model.LocationPicVO;

@WebServlet("/back-end/Location/locPic.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class LocationPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("get_For_Update".equals(action)) {//來自getAllLoc.jsp
			//1. 接收請求參數
			Integer locId = Integer.valueOf(req.getParameter("LOC_ID"));
			
			//2.開始查詢資料
			LocationPicService locPicSvc = new LocationPicService();
			List<LocationPicVO> locPicVO = locPicSvc.getLocPic(locId);

			req.setAttribute("locPicVO", locPicVO);//資料庫取出的locPicVO物件，存入req
			req.setAttribute("locId", locId);
			
			Boolean openPicModal = true;
			req.setAttribute("openPicModal", openPicModal);
			
			//查詢完成準備轉交
			String url = "/back-end/Location/locManage.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		if ("addPic".equals(action)) {
			//1.接收請求參數
			Integer locId = Integer.valueOf(req.getParameter("LOC_ID"));
			
			//2.解析圖片
			Collection<Part> pic = req.getParts();
			
			//3.開始新增照片
			LocationPicService locPicSvc = new LocationPicService();
			LocationPicVO locPicVO = locPicSvc.addLocPic(locId, pic);
			String url = "/back-end/Location/locPic.do?LOC_ID=" + locId + "&action=get_For_Update";
			//查詢完成準備轉交
			req.getRequestDispatcher(url).forward(req, res);
			
		}
		
		if ("delete".equals(action)) {
			//1.接收請求參數
			Integer locPicId = Integer.valueOf(req.getParameter("LOC_PIC_ID"));
			Integer locId = Integer.valueOf(req.getParameter("LOC_ID"));
			
			//2.開始刪除資料
			LocationPicService locPicSvc = new LocationPicService();
			locPicSvc.deleteLocPic(locPicId);
			
			//刪除完成準備轉交
			String url = "/back-end/Location/locPic.do?LOC_ID=" + locId + "&action=get_For_Update";
			req.getRequestDispatcher(url).forward(req, res);
		}
		 
	}

}
