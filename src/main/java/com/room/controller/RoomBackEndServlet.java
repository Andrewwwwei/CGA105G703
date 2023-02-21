package com.room.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderDetail.model.OrderDetailService;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomPhoto.model.RoomPhotoService;
import com.roomPhoto.model.RoomPhotoVO;
import com.stock.model.StockService;
import com.stock.model.StockVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;

@WebServlet("/RoomBackEnd")
public class RoomBackEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("goThisVenRoomType".equals(action)) { //廠商房型列表
			Integer venId = Integer.parseInt(req.getParameter("venId"));
			VendorService vendorService = new VendorService();
			VendorVO vendorVO = vendorService.getOneVendor(venId);
			RoomService roomService = new RoomService();
			List<RoomVO> roomVOList = roomService.getAllByVen(venId);
			req.setAttribute("venName", vendorVO.getVenName());
			req.setAttribute("venId", venId);
			req.setAttribute("roomVOList", roomVOList);
			req.getRequestDispatcher("/back-end/room/ven_room_list.jsp").forward(req, res);
			return;
		}
		
		if("toRoomPic".equals(action)) { //查看房型圖片
			Integer roomId = Integer.parseInt(req.getParameter("roomId"));
			RoomService roomService = new RoomService();
			RoomVO roomVO = roomService.getOneRoom(roomId);
			Integer venId = roomVO.getVenId();
			VendorService vendorService = new VendorService();
			VendorVO vendorVO = vendorService.getOneVendor(venId);
			RoomPhotoService roomPhotoService = new RoomPhotoService();
			List<RoomPhotoVO> roomPhotoVOList = roomPhotoService.getThisRoomPhoto(roomId);
			if(roomPhotoVOList == null) {
				req.getRequestDispatcher("/back-end/room/roomTypePic.jsp").forward(req, res);
				return;
			}
			List<Integer> photoIdList = new ArrayList<Integer>();
			for(RoomPhotoVO vo : roomPhotoVOList) {
				photoIdList.add(vo.getRoomPhotoId());
			}
			if(req.getParameter("upload") != null) {
				switch (req.getParameter("upload")) {
				case "ok": 
					req.setAttribute("upload", "success");
					break;
				case "big" :
					req.setAttribute("errorMsg", "檔案大小過大");
					break;
				case "delete" :
					req.setAttribute("delete", "success");
					break;
				case "empty" :
					req.setAttribute("errorMsg", "未選擇圖片或檔案有誤");
					break;
				}
			}
			req.setAttribute("venId", venId);
			req.setAttribute("venName", vendorVO.getVenName());
			req.setAttribute("roomId", roomId);
			req.setAttribute("roomName", roomVO.getRoomName());
			req.setAttribute("photoIdList", photoIdList);
			req.getRequestDispatcher("/back-end/room/roomTypePic.jsp").forward(req, res);
			return;
		}
		
		if("offShow".equals(action)) { //下架房型
			Integer roomId = Integer.parseInt(req.getParameter("roomId"));
			RoomService roomService = new RoomService();
			RoomVO roomVO = roomService.getOneRoom(roomId);
			roomVO.setRoomUpdate(0);
			roomService.updateRoom(roomVO);
			//刪除庫存，已有庫存的日期跳過
			StockService stockService = new StockService();
			List<StockVO> stockVOList = stockService.getByRoomId(roomId);
			for(StockVO vo : stockVOList) {
				if(vo.getRoomRest().equals(roomVO.getRoomAmount())) {  
					stockService.deleteStock(roomId, vo.getStayDate());
				}
			}
			res.setContentType("text/plain");
			res.getWriter().write("ok");
			return;
		}
		
		if("onShow".equals(action)) { //上架房型
			Integer roomId = Integer.parseInt(req.getParameter("roomId"));
			RoomService roomService = new RoomService();
			RoomVO roomVO = roomService.getOneRoom(roomId);
			roomVO.setRoomUpdate(1);
			roomService.updateRoom(roomVO);
			//更新庫存，已有庫存的日期跳過
			LocalDate toSixMonths = LocalDate.now().plusMonths(6).plusDays(2);
	        Stream<LocalDate> stream = LocalDate.now().datesUntil(toSixMonths, Period.ofDays(1));
			List<LocalDate> dateList = stream.collect(Collectors.toList());
			StockService stockService = new StockService();
			for(LocalDate date : dateList) {
				if(stockService.getOneStock(roomId, date) == null) {  //查不到庫存就新增
					stockService.addStock(roomId, date, roomVO.getRoomAmount());
				}
			}
			res.setContentType("text/plain");
			res.getWriter().write("ok");
			return;
		}
		
		if("toAddRoom".equals(action)) { //至新增房型頁面
			Integer venId = Integer.parseInt(req.getParameter("venId"));
			VendorService vendorService = new VendorService();
			String venName = vendorService.getOneVendor(venId).getVenName();
			req.setAttribute("venName", venName);
			req.setAttribute("venId", venId);
			req.getRequestDispatcher("/back-end/room/addRoom.jsp").forward(req, res);
			return;
		}
		
		if("insertRoom".equals(action)) { //新增房型
			List<String> errorMsgs = new ArrayList<String>();
			Integer venId = Integer.parseInt(req.getParameter("venId"));
			VendorService vendorService = new VendorService();
			VendorVO vendorVO = vendorService.getOneVendor(venId);
			String roomName = req.getParameter("roomName");
			Integer people = Integer.parseInt(req.getParameter("people"));
			String roomAmountS = req.getParameter("roomAmount");
			String priceS = req.getParameter("price");
			String roomAreaS = req.getParameter("roomArea");
			String roomIntro = req.getParameter("roomIntro");
			String[] facilities = req.getParameterValues("facilities");
			Integer breakfast = 0;
			Integer airCondotioner = 0;
			Integer wifi = 0;
			Integer television = 0;
			Integer safebox = 0;
			Integer fridge = 0;
			Integer waterBoiler = 0;
			Integer bathroom = 0;
			Integer toiletries = 0;
			if(facilities != null) {
				req.setAttribute("facilities", facilities);
				for(String facility : facilities) {
					switch (facility) {
					case "breakfast":
						breakfast = 1;
						break;
					case "airCondotioner":
						airCondotioner = 1;
						break;
					case "wifi":
						wifi = 1;
						break;
					case "television":
						television = 1;
						break;
					case "safebox":
						safebox = 1;
						break;
					case "fridge":
						fridge = 1;
						break;
					case "waterBoiler":
						waterBoiler = 1;
						break;
					case "bathroom":
						bathroom = 1;
						break;
					case "toiletries":
						toiletries = 1;
						break;
					}
				}
			}
			if (roomName.isEmpty()) { // 關鍵字驗證
				errorMsgs.add( "房型名稱不可空白");
			} 
			Integer roomAmount = 0;
			try {
				roomAmount = Integer.parseInt(roomAmountS.trim());
			}catch (Exception e) {
				errorMsgs.add( "房間數量空白或格式錯誤");
			}
			Integer price = 0;
			try {
				price = Integer.parseInt(priceS.trim());
			}catch (Exception e) {
				errorMsgs.add( "價格空白或格式錯誤");
			}
			Integer roomArea = 0;
			try {
				roomArea = Integer.parseInt(roomAreaS.trim());
			}catch (Exception e) {
				errorMsgs.add( "坪數空白或格式錯誤");
			}
			if(roomIntro.isEmpty()) {
				errorMsgs.add( "房型介紹不可空白");
			}else if(roomIntro.length() > 500) {
				errorMsgs.add( "房型介紹不可超過500字");
			}
			////有錯誤訊息
			if(errorMsgs.size() != 0) {
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("venName", vendorVO.getVenName());
				req.setAttribute("venId", venId);
				req.getRequestDispatcher("/back-end/room/addRoom.jsp").forward(req, res);
				return;
			}else {
				RoomService roomService = new RoomService();
				roomService.addRoom(venId, roomName, roomAmount, people, price, roomArea, roomIntro, 0, 0, 
						breakfast, airCondotioner, wifi, television, safebox, fridge, waterBoiler, bathroom, toiletries);
				List<RoomVO> roomVOList = roomService.getAllByVen(venId);
				req.setAttribute("venName", vendorVO.getVenName());
				req.setAttribute("venId", venId);
				req.setAttribute("roomVOList", roomVOList);
				req.setAttribute("addRoom", "success");
				req.getRequestDispatcher("/back-end/room/ven_room_list.jsp").forward(req, res);
				return;
			}
		}
		
		if("toUpdateRoom".equals(action)) { //至房型修改頁面
			Integer roomId = Integer.parseInt(req.getParameter("roomId"));
			RoomService roomService = new RoomService();
			RoomVO roomVO = roomService.getOneRoom(roomId);
			Integer venId = roomVO.getVenId();
			VendorService vendorService = new VendorService();
			VendorVO vendorVO = vendorService.getOneVendor(venId);
			//檢查是否下架或已有訂單
			OrderDetailService orderDetailService = new OrderDetailService();
			if(orderDetailService.getByRoomID(roomId).size() > 0) {
				List<RoomVO> roomVOList = roomService.getAllByVen(venId);
				req.setAttribute("venName", vendorVO.getVenName());
				req.setAttribute("venId", venId);
				req.setAttribute("roomVOList", roomVOList);
				req.setAttribute("updateError", "房型已有交易紀錄，無法修改");
				req.getRequestDispatcher("/back-end/room/ven_room_list.jsp").forward(req, res);
				return;
			}else if(roomVO.getRoomUpdate().equals(1)) {
				List<RoomVO> roomVOList = roomService.getAllByVen(venId);
				req.setAttribute("venName", vendorVO.getVenName());
				req.setAttribute("venId", venId);
				req.setAttribute("roomVOList", roomVOList);
				req.setAttribute("updateError", "請先下架房型");
				req.getRequestDispatcher("/back-end/room/ven_room_list.jsp").forward(req, res);
				return;
			}
			req.setAttribute("venName", vendorVO.getVenName());
			req.setAttribute("venId", venId);
			req.setAttribute("roomVO", roomVO);
			req.setAttribute("roomId", roomVO.getRoomId());
			req.getRequestDispatcher("/back-end/room/updateRoom.jsp").forward(req, res);
			return;
		}
		
		if("updateRoom".equals(action)) { //修改房型
			List<String> errorMsgs = new ArrayList<String>();
			Integer venId = Integer.parseInt(req.getParameter("venId"));
			Integer roomId = Integer.parseInt(req.getParameter("roomId"));
			VendorService vendorService = new VendorService();
			VendorVO vendorVO = vendorService.getOneVendor(venId);
			String roomName = req.getParameter("roomName");
			Integer people = Integer.parseInt(req.getParameter("people"));
			String roomAmountS = req.getParameter("roomAmount");
			String priceS = req.getParameter("price");
			String roomAreaS = req.getParameter("roomArea");
			String roomIntro = req.getParameter("roomIntro");
			String[] facilities = req.getParameterValues("facilities");
			Integer breakfast = 0;
			Integer airCondotioner = 0;
			Integer wifi = 0;
			Integer television = 0;
			Integer safebox = 0;
			Integer fridge = 0;
			Integer waterBoiler = 0;
			Integer bathroom = 0;
			Integer toiletries = 0;
			if(facilities != null) {
				req.setAttribute("facilities", facilities);
				for(String facility : facilities) {
					switch (facility) {
					case "breakfast":
						breakfast = 1;
						break;
					case "airCondotioner":
						airCondotioner = 1;
						break;
					case "wifi":
						wifi = 1;
						break;
					case "television":
						television = 1;
						break;
					case "safebox":
						safebox = 1;
						break;
					case "fridge":
						fridge = 1;
						break;
					case "waterBoiler":
						waterBoiler = 1;
						break;
					case "bathroom":
						bathroom = 1;
						break;
					case "toiletries":
						toiletries = 1;
						break;
					}
				}
			}
			if (roomName.isEmpty()) { // 關鍵字驗證
				errorMsgs.add( "房型名稱不可空白");
			} 
			Integer roomAmount = 0;
			try {
				roomAmount = Integer.parseInt(roomAmountS.trim());
			}catch (Exception e) {
				errorMsgs.add( "房間數量空白或格式錯誤");
			}
			Integer price = 0;
			try {
				price = Integer.parseInt(priceS.trim());
			}catch (Exception e) {
				errorMsgs.add( "價格空白或格式錯誤");
			}
			Integer roomArea = 0;
			try {
				roomArea = Integer.parseInt(roomAreaS.trim());
			}catch (Exception e) {
				errorMsgs.add( "坪數空白或格式錯誤");
			}
			if(roomIntro.isEmpty()) {
				errorMsgs.add( "房型介紹不可空白");
			}else if(roomIntro.length() > 500) {
				errorMsgs.add( "房型介紹不可超過500字");
			}
			////有錯誤訊息
			if(errorMsgs.size() != 0) {
				req.setAttribute("errorMsgs", errorMsgs);
				req.setAttribute("venName", vendorVO.getVenName());
				req.setAttribute("venId", venId);
				req.setAttribute("roomId", roomId);
				req.getRequestDispatcher("/back-end/room/updateRoom.jsp").forward(req, res);
				return;
			}else {
				RoomService roomService = new RoomService();
				RoomVO roomVO = roomService.getOneRoom(roomId);
				roomService.updateRoom(roomId, venId, roomName, roomAmount, people, price, roomArea, roomIntro,
						roomVO.getRoomUpdate(), roomVO.getRoomView(), breakfast, airCondotioner, wifi, television, safebox, fridge,
						waterBoiler, bathroom, toiletries);
				List<RoomVO> roomVOList = roomService.getAllByVen(venId);
				req.setAttribute("venName", vendorVO.getVenName());
				req.setAttribute("venId", venId);
				req.setAttribute("roomVOList", roomVOList);
				req.setAttribute("updateRoom", "success");
				req.getRequestDispatcher("/back-end/room/ven_room_list.jsp").forward(req, res);
				return;
			}
		}
	}

}
