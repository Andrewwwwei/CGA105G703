package com.tripDetail.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.location.model.LocationService;
import com.location.model.LocationVO;
import com.locationPic.model.LocationPicService;
import com.locationPic.model.LocationPicVO;
import com.tripDetail.model.TripDetailService;
import com.tripDetail.model.TripDetailVO;

@WebServlet("/front-end/TripPlan/tripDetail.do")
public class tripDetailServlet extends HttpServlet{
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
		
		if("addTripLoc".equals(action)) {//來自getOne_LocInfo.jsp ajax的請求
			//1.接收請求參數
			Integer tripId = Integer.valueOf(req.getParameter("TRIP_ID"));
			Integer locId = Integer.valueOf(req.getParameter("LOC_ID"));
			
			Timestamp arrivalTime = Timestamp.valueOf(req.getParameter("arrivalTime")+":00");
			Timestamp leaveTime = Timestamp.valueOf(req.getParameter("leaveTime")+":00");
			
			//2.開始新增
			TripDetailService tripDetailSvc = new TripDetailService();
			TripDetailVO tripDetailVO = tripDetailSvc.addTripDetail(tripId, locId, arrivalTime, leaveTime);
			
			//3.完成新增，轉送給JS
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
			String jsonStr = "";
			jsonStr = gson.toJson(tripDetailVO);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();
		}
		
		if ("getTrip_TripDetail".equals(action)) {//來自tripPlan.jsp ajax的請求
			//1.接收請求參數
			Integer tripId = Integer.valueOf(req.getParameter("TRIP_ID"));
			Date date = Date.valueOf(req.getParameter("DATE"));
			
			//2.開始搜尋
			TripDetailService tripDetailSvc = new TripDetailService();
			List<TripDetailVO> activeList = tripDetailSvc.getTrip_TripDetail(tripId, date);
			List<Object> returnList = new ArrayList<Object>();
			for(int count = 0; count < activeList.size(); count++) {
				LinkedHashMap<String, Object> map = new LinkedHashMap<>();
				SimpleDateFormat format = new SimpleDateFormat("HH:mm");
				map.put("TripDId",activeList.get(count).getTripDatailId());
				map.put("TripId",activeList.get(count).getTripId());
				map.put("LocId",activeList.get(count).getLocId());
				map.put("ArrivalTime",format.format(activeList.get(count).getArrivalTime()));
				map.put("LeaveTime",format.format(activeList.get(count).getLeaveTime()));
				map.put("DATE", new SimpleDateFormat("MM/dd").format(date));
				//取得地點資訊
				LocationService locSvc = new LocationService();
				LocationVO locVO= locSvc.getOneLoc(activeList.get(count).getLocId());
				map.put("UserId",locVO.getUserId());
				map.put("LocName",locVO.getLocName());
				map.put("Long",locVO.getLongitude());
				map.put("Lat",locVO.getLatitude());
				map.put("Address",locVO.getLocAddress());
				map.put("Phone",locVO.getLocPhone());
				map.put("Status",locVO.getLocStatus());
				//取得地點圖片
				LocationPicService locPicSvc = new LocationPicService();
				List<LocationPicVO> locPicList = locPicSvc.getLocPic(activeList.get(count).getLocId());
				if (locPicList.size() != 0) {
					String base64Str = Base64.getEncoder().encodeToString(locPicList.get(0).getLocPic());
					map.put("locPic",base64Str);
				}else {
					map.put("locPic","");
				}
				returnList.add(map);
			}
			
			//3.完成搜尋，轉送給JS
			Gson gson = new Gson();
			String jsonStr = "";
			jsonStr = gson.toJson(returnList);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();
		}
		
		if ("delete_Date".equals(action)) {//來自tripPlan.jsp的請求
			//1.接收請求參數
			Integer tripId = Integer.valueOf(req.getParameter("TRIP_ID"));
			Date date = Date.valueOf(req.getParameter("DATE"));
			
			//2.開始刪除
			TripDetailService tripDetailSvc = new TripDetailService();
			tripDetailSvc.deleteByDate(tripId, date);
			
			//3.刪除完成後開始轉交
			String url = "/front-end/TripPlan/tripPlan.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}
		
		if ("deleteTripLoc".equals(action)) {//來自tripPlan.jsp的請求
			//1.接收請求參數
			Integer tripDetailId = Integer.valueOf(req.getParameter("TRIP_DETAIL_ID"));
			
			//2.開始刪除
			TripDetailService tripDetailSvc = new TripDetailService();
			tripDetailSvc.deleteTripDetail(tripDetailId);
			
			//3.完成搜尋，轉送給成功刪除訊息給JS
			String jsonStr = "successfully deleted";
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();
		}
	}

}
