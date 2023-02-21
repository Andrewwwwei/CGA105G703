package com.location.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.LinkedHashMap;

import com.google.gson.Gson;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sound.midi.Soundbank;

import com.location.model.LocationService;
import com.location.model.LocationVO;
import com.locationPic.model.LocationPicService;
import com.locationPic.model.LocationPicVO;
import com.tripDetail.model.TripDetailService;
import com.tripDetail.model.TripDetailVO;

 
@WebServlet(urlPatterns = {"/front-end/TripPlan/tripLoc.do","/back-end/Location/loc.do"})//等同到web.xml註冊
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class LocationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("insert".equals(action)) {//來自/back-end/addLoc.jsp  /front-end/getOne_LocInfo.jsp的請求 
			Integer userId = req.getParameter("userId") == null? null : Integer.valueOf(req.getParameter("userId"));
			String locName = req.getParameter("loc_name").trim();
			String longitude = req.getParameter("longitude").trim();
			String latitude = req.getParameter("latitude").trim();
			String address = req.getParameter("address").trim();
			String phone = req.getParameter("phone").trim().length() == 0? null : req.getParameter("phone").trim();
			Integer locStatus = Integer.valueOf(req.getParameter("locStatus"));
			String forwardWhere = req.getParameter("forwardWhere");
			
			// 解析圖片
			Collection<Part> pic = "front-end".equals(forwardWhere)? new ArrayList<Part>() : req.getParts();

//					開始新增資料					
			LocationService locSer = new LocationService();
			LocationVO locVO = locSer.addLoc(userId, locName, longitude, latitude, address, phone, locStatus, pic);
			
			req.setAttribute("locVO", locVO);
//					完成新增準備轉交
			if("front-end".equals(forwardWhere)) {
				//前台使用ajax
				Gson gson = new Gson();
				String jsonStr = "";
				jsonStr = gson.toJson(locVO);
				PrintWriter out = res.getWriter();
				out.print(jsonStr);
				out.close();
			}else {
				String url = "/back-end/Location/locManage.jsp";
				req.getRequestDispatcher(url).forward(req, res);
			}
			
			
			
		}

		if ("getOne_For_Update".equals(action)) {//來自/back-end的請求 
			// 1.接收請求參數
			Integer locId = Integer.valueOf(req.getParameter("LOC_ID"));

			// 開始查詢資料
			LocationService locSrv = new LocationService();
			LocationVO locVO = locSrv.getOneLoc(locId);
			req.setAttribute("locVO", locVO);//資料庫取出的locVO物件，存入req
			
			Boolean openModal = true;
			req.setAttribute("openModal", openModal);

			// 查詢完成準備轉交
			String url = "/back-end/Location/locManage.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

		if ("update".equals(action)) { // 來自listOneLoc.jsp的請求
			
			/*************************** 1.接收請求參數 **********************/
			Integer locId = Integer.valueOf(req.getParameter("locId"));
			Integer userId = req.getParameter("userId") == null? null : Integer.valueOf(req.getParameter("userId"));
			String locName = req.getParameter("loc_name").trim();
			String longitude = req.getParameter("longitude").trim();
			String latitude = req.getParameter("latitude").trim();
			String address = req.getParameter("address").trim();
			String phone = req.getParameter("phone").trim().length() == 0? null : req.getParameter("phone").trim();
			Integer locStatus = Integer.valueOf(req.getParameter("state"));

			LocationVO locVO = new LocationVO();
			locVO.setLocId(locId);
			locVO.setUserId(userId);
			locVO.setLocName(locName);
			locVO.setLongitude(longitude);
			locVO.setLatitude(latitude);
			locVO.setLocAddress(address);
			locVO.setLocPhone(phone);
			locVO.setLocStatus(locStatus);
			//開始改資料
			LocationService locSvc = new LocationService();
			locVO = locSvc.updateLoc(locId, userId, locName, longitude, latitude, address, phone, locStatus);
			//修改完成，準備轉交
			req.setAttribute("locVO", locVO);
			String url = "/back-end/Location/locManage.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			
		}
		
		if("search".equals(action)) {//來自tripPlan.jsp的請求
			//1.接收請求參數
			String searchWord =req.getParameter("word");
			String forwardWhere = String.valueOf(req.getParameter("forward"));
			//0:上架 1:下架 2:自訂地點
			Integer takeOn = 0;
			Integer takeOff = 1;
			
			
			//2.開始搜尋
			LocationService locSvc = new LocationService();
			List<LocationVO> list = null;
			if (forwardWhere.equals("front-end")) {
				list = locSvc.getForLocation(searchWord,takeOn);        //前台只需要拿到上架的地點
			}else {
				list = locSvc.getForLocation(searchWord,takeOn,takeOff);//後台上下架地點都要拿到
			}
			
			req.setAttribute("searchWord", searchWord);
			req.setAttribute("search", "search");
			req.setAttribute("searchList", list); 
			
			//3.搜尋完成開始轉發
			String url = forwardWhere.equals("front-end")? "/front-end/TripPlan/tripPlan.jsp":"/back-end/Location/locManage.jsp";
			req.getRequestDispatcher(url).forward(req, res);
			
		}
		
		if ("getOneLoc".equals(action)) {//來自tripPlan.jsp getOne_LocInfo.jsp的請求ajax
			//1.接收請求參數
			Integer locId = Integer.valueOf(req.getParameter("LOC_ID"));
			
			//2.開始搜尋
			List<Object> returnList = new ArrayList<>();
			LinkedHashMap<String, Object> map = new LinkedHashMap<>();
			LocationService locSvc = new LocationService();
			LocationVO locVO = locSvc.getOneLoc(locId);
			returnList.add(locVO);
			
			LocationPicService locPicSvc = new LocationPicService();
			List<LocationPicVO> locPicList = locPicSvc.getLocPic(locId);
			for(int count = 0; count < locPicList.size(); count++) {
				String base64Str = Base64.getEncoder().encodeToString(locPicList.get(count).getLocPic());
				map.put("LocPic",base64Str);
				returnList.add(map);
				map = new LinkedHashMap<>();
			}
			
			//3.搜尋結束，傳送給JS
			Gson gson = new Gson();
			String jsonStr = "";
			jsonStr = gson.toJson(returnList);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();
			
		}
		
		if ("deleteUserLoc".equals(action)) {//來自getOne_LocInfo.jsp的請求
			//1.接收請求參數
			Integer locId = Integer.valueOf(req.getParameter("LOC_ID"));
			
			//2.開始刪除資料
			LocationService locSvc = new LocationService();
			locSvc.deleteUserLoc(locId);
			
			//3.刪除完成後，傳成功刪除訊息給JS
			PrintWriter out = res.getWriter();
			out.print("successfully deleted");
			out.close();
			
		}
		
		if ("ajaxGetLocInfo".equals(action)) {//來自map.js的請求 ajax
			//1.接收請求參數
			Integer tripId = Integer.valueOf(req.getParameter("tripId"));
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			
			//2.開始搜尋
			TripDetailService tripDetailSvc = new TripDetailService();
			List<TripDetailVO> tripDetailList = tripDetailSvc.ajaxGetTripDetail(tripId, startDate, endDate);
			
			List<LocationVO> locList = new ArrayList<LocationVO>();
			for(TripDetailVO tripDetail: tripDetailList) {
			 	Integer locId = tripDetail.getLocId();
			 	LocationService locSvc = new LocationService();
			 	LocationVO locVO = locSvc.getOneLoc(locId);
			 	
			 	locList.add(locVO);
			}
			//搜尋完成，轉送給JS
			Gson gson = new Gson();
			String jsonStr = "";
			jsonStr = gson.toJson(locList);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();
			
		}
		
		if("ajaxGetCusLoc".equals(action)) {
			//1.接收參數
			Integer userId = Integer.valueOf(req.getParameter("USER_ID"));
			
			//2.開始查詢
			LocationService locSvc = new LocationService();
			List<LocationVO> locList = locSvc.getForUserId(userId);
			
			//3.轉傳資料給JS
			Gson gson = new Gson();
			String jsonStr = "";
			jsonStr = gson.toJson(locList);
			PrintWriter out = res.getWriter();
			out.print(jsonStr);
			out.close();
		}

	}
}




















