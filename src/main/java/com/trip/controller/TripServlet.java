package com.trip.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.trip.model.TripService;
import com.trip.model.TripVO;
import com.tripDetail.model.TripDetailService;

@WebServlet(urlPatterns = {"/front-end/TripPlan/trip.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TripServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req,res);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {//來自userTripAll.jsp的請求
			//1.接收請求參數
			String tripName = req.getParameter("trip_name");
			Date startDate = Date.valueOf(req.getParameter("start_date"));
			Date endDate = Date.valueOf(req.getParameter("end_date"));
			String note = req.getParameter("note").trim().length() ==0 ? null : req.getParameter("note");
			byte[] coverPic = req.getPart("cover-pic").getSubmittedFileName().isEmpty()? null:req.getPart("cover-pic").getInputStream().readAllBytes();
			
			Integer userId = Integer.valueOf(req.getParameter("userId"));
			
			//2.開始新增資料
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.addTrip(tripName, startDate, endDate, coverPic, note, userId);
			
			req.setAttribute("tripVO", tripVO);
			//3.完成新增準備轉交
			String url = "/front-end/TripPlan/userTripAll.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		if ("delete".equals(action)) {//來自userTripAll.jsp的請求
			//1.接收請求參數
			Integer tripId = Integer.valueOf(req.getParameter("TRIP_ID"));
			
			//2.開始刪除行程
			TripService tripSvc = new TripService();
			tripSvc.deleteTrip(tripId);
			
			//3.完成刪除準備轉交
			String url = "/front-end/TripPlan/userTripAll.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		if ("getPic_For_Update".equals(action)) {//來自userTripAll.jsp的請求
			//1.接收請求參數
			Integer tripId =Integer.valueOf(req.getParameter("TRIP_ID"));
			
			//2.開始查詢圖片
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.getOneTrip(tripId);
			
			//3.開啟modal
			Boolean openPicModal = true;
			
			req.setAttribute("openPicModal", openPicModal);
			req.setAttribute("tripVO", tripVO);
			
			//4.完成後準備轉交
			String url = "/front-end/TripPlan/userTripAll.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		if ("updateTripPic".equals(action)) {//來自userTripAll.jsp的請求
			//1.接收參數
			Integer tripId = Integer.valueOf(req.getParameter("TRIP_ID"));
			String tripName = req.getParameter("TRIP_NAME");
			Date startDate = Date.valueOf(req.getParameter("START_DATE"));
			Date endDate = Date.valueOf(req.getParameter("END_DATE"));
			String note = req.getParameter("NOTE");
			
			byte[] coverPic = req.getPart("COVER_PIC").getSubmittedFileName().isEmpty()? null : req.getPart("COVER_PIC").getInputStream().readAllBytes();
			
			
			//2.開始更新圖片
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.updateTrip(tripName, startDate, endDate, coverPic, note, tripId);
			
			req.setAttribute("tripVO", tripVO);
			
			//3.完成後準備轉交
			String url = "/front-end/TripPlan/trip.do?TRIP_ID=" + tripId + "&action=getPic_For_Update";
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		if("update".equals(action)) {
			//1.接收參數
			Date startDate = Date.valueOf(req.getParameter("startDate"));
			Date endDate = Date.valueOf(req.getParameter("endDate"));
			Integer tripId = Integer.valueOf(req.getParameter("tripId"));
			String tripName = req.getParameter("tripName");
			byte[] coverPic = Base64.getDecoder().decode(req.getParameter("coverPic"));
			String note = req.getParameter("note");
			String method = req.getParameter("method");
			
			
			//2.開始更新資料
			TripService tripSvc = new TripService();
			TripVO tripVO = tripSvc.updateTrip(tripName, startDate, endDate, coverPic, note, tripId);
			req.setAttribute("tripVO", tripVO);
			
			//3.更新完成準備轉交
			if ("ajax".equals(method)) {
				Gson gson = new Gson();
				String jsonStr = "";
				jsonStr = gson.toJson(tripVO);
				PrintWriter out = res.getWriter();
				out.print(jsonStr);
				out.close();
			}else {
				String url = "/front-end/TripPlan/tripPlan.jsp";
				req.getRequestDispatcher(url).forward(req, res);				
			}
			
				
			}

		
	}
}
