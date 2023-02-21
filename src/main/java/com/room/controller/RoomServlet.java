package com.room.controller;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ColVen.model.*;
import com.orderDetail.model.*;
import com.room.model.*;
import com.roomOrder.model.*;
import com.roomPhoto.model.*;
import com.stock.model.*;
import com.Users.model.*;
import com.article.model.*;
import com.vendor.model.*;
import com.vendorPic.model.*;

@WebServlet("/Room")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("findRooms".equals(action)) { // 搜尋符合房間結果
			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String from = req.getParameter("from");
			DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			String keyword = req.getParameter("keyword").trim();
			String keywordReg = "^[(\\u4e00-\\u9fff)(a-zA-Z)]{1,10}$";
			String startDay = req.getParameter("startDay");
			String endDay = req.getParameter("endDay");
			String people = req.getParameter("people");
			String peopleReg = "^[1-4]$";
			String roomAmount = req.getParameter("roomAmount");
			String roomAmountReg = "^[1-5]$";
			req.setAttribute("startDay", startDay);
			req.setAttribute("endDay", endDay);
			if (keyword.isEmpty()) { // 關鍵字驗證
				errorMsgs.put("keywordErrorMsg", "請輸入欲查詢之地區或住宿名稱");
			} else if (!keyword.matches(keywordReg)) {
				errorMsgs.put("keywordErrorMsg", "請勿輸入數字或符號");
			}
			LocalDate lStartDay = LocalDate.now();
			LocalDate lEndDay = LocalDate.now();
			if (startDay.trim().isEmpty()) { // 入住日驗證
				errorMsgs.put("ckinErrorMsg", "請選擇入住日");
			} else {
				try {
					lStartDay = LocalDate.parse(startDay, DATEFORMATTER);
					if (lStartDay.isBefore(LocalDate.now())) {
						errorMsgs.put("ckinErrorMsg", "入住日不可小於今日");
					}
				} catch (DateTimeParseException e) {
					errorMsgs.put("ckinErrorMsg", "日期格式錯誤");
				}
			}
			if (endDay.trim().isEmpty()) { // 退房日驗證
				errorMsgs.put("ckoutErrorMsg", "請選擇退房日");
			} else {
				try {
					lEndDay = LocalDate.parse(endDay, DATEFORMATTER);
					if (!lStartDay.isBefore(lEndDay)) {
						errorMsgs.put("ckoutErrorMsg", "退房日需大於入住日");
					}
				} catch (DateTimeParseException e) {
					errorMsgs.put("ckoutErrorMsg", "日期格式錯誤");
				}
			}
			if (people == null || !people.matches(peopleReg)) { // 人數驗證
				errorMsgs.put("peopleErrorMsg", "請選擇人數");
			}
			if (roomAmount == null || !roomAmount.matches(roomAmountReg)) { // 房數驗證
				errorMsgs.put("roomAmtErrorMsg", "請選擇房數");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("errorMsgs", errorMsgs);
				req.getRequestDispatcher("/front-end/room/room_search_result.jsp").forward(req, res);
				return;// 程式中斷
			}
//			/***************************2.開始查詢資料*****************************************/
			try {
				List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
				VendorService vendorService = new VendorService();
				List<VendorVO> venList = vendorService.getByKeyword(keyword); // 先取得符合關鍵字的旅宿list
				if (venList.size() == 0) {
					req.setAttribute("noData", "無符合關鍵字之旅宿，請重新查詢");
					req.getRequestDispatcher("/front-end/room/room_search_result.jsp").forward(req, res);
					return;
				}
				RoomService roomService = new RoomService();
				StockService stockService = new StockService();
				Stream<LocalDate> stream = lStartDay.datesUntil(lEndDay, Period.ofDays(1));
				List<LocalDate> dateList = stream.collect(Collectors.toList());
				for (VendorVO vendorVO : venList) {
					if (!vendorVO.getVenStatus().equals(1)) {
						continue;
					}
					int roomprice = 50000;
					Map<String, Object> oneVen = new LinkedHashMap<String, Object>();
					List<RoomVO> roomList = roomService.getAllByVen(vendorVO.getVenId()); // 取出旅宿中所有房型list
					room: for (RoomVO roomVO : roomList) {
						if (roomVO.getRoomPeople() >= Integer.parseInt(people) && roomVO.getRoomUpdate().equals(1)) { // 房間人數要>=需求人數&&房型是上架的
							for (LocalDate day : dateList) {
								StockVO oneStock = stockService.getOneStock(roomVO.getRoomId(), day);
								if (oneStock == null || oneStock.getRoomRest() < Integer.parseInt(roomAmount)) {
									continue room; // 如果沒有庫存資料或其中有一天庫存小於需求，換下一房 (每一天要>=需求房數)
								}
							}
							if (roomVO.getRoomPrice() < roomprice) {
								roomprice = roomVO.getRoomPrice(); // 取得最便宜的那間
								int amount = 100;
								for (LocalDate day : dateList) {
									if (stockService.getOneStock(roomVO.getRoomId(), day).getRoomRest() < amount) {
										amount = stockService.getOneStock(roomVO.getRoomId(), day).getRoomRest(); // 取得庫存量
										oneVen.put("venId", vendorVO.getVenId());
										oneVen.put("venName", vendorVO.getVenName());
										oneVen.put("venAddr", vendorVO.getVenLocation());
										oneVen.put("venIntro", vendorVO.getVenIntro());
										oneVen.put("venScore", vendorVO.getVenScore());
										oneVen.put("roomPrice", roomprice);
										oneVen.put("roomStock", amount);
										oneVen.put("roomView", roomVO.getRoomView());
									}
								}
							}
						}
					}
					if (!oneVen.isEmpty()) {
						result.add(oneVen);
					}
				}
				if (result.size() == 0) {
					req.setAttribute("noData", "搜尋期間旅宿庫存不足，請更改搜尋條件");
					req.getRequestDispatcher("/front-end/room/room_search_result.jsp").forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("resultList", result);
				String url = "/front-end/room/room_search_result.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room/room_index.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("showFirstVenImages".equals(action)) { // 顯示廠商第一張圖片
			res.setContentType("image/jpeg");
			/*************************** 1.接收請求參數 ****************************************/
			Integer venId = new Integer(req.getParameter("venId"));
			/****************************
			 * 2.開始查詢資料，篩選資料
			 ****************************************/
			VendorPicService vendorPicService = new VendorPicService();
			List<VendorPicVO> venPicList = vendorPicService.getThisVendorpic(venId);
			/*************************** 3.輸出圖片 ************/
			byte[] content = venPicList.get(0).getVenPic();
			try (ServletOutputStream out = res.getOutputStream()) {
				out.write(content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		if ("findVenRooms".equals(action)) { // 顯示該廠商符合房型

			Map<String, String> errorMsgs = new LinkedHashMap<String, String>();
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Integer venId = Integer.parseInt(req.getParameter("venId"));
			UsersVO users = (UsersVO) req.getSession().getAttribute("usersVO");
			Integer userId = 0;
			if(users != null) {
				userId = users.getUserId();
			}
			String startDay = req.getParameter("startDay");
			String endDay = req.getParameter("endDay");
			String people = req.getParameter("people");
			String peopleReg = "^[1-4]$";
			String roomAmount = req.getParameter("roomAmount");
			String roomAmountReg = "^[1-5]$";
			req.setAttribute("startDay", startDay);
			req.setAttribute("endDay", endDay);
			try {
				/*************************** 2.開始查詢資料 *****************************************/
				//////////////// 店家收藏資訊 //////////////////
				ColVenService colVenService = new ColVenService();
				ColVenVO colVenVO = colVenService.getOneColVen(venId, userId);
				req.setAttribute("colVenVO", colVenVO);
				//////////////// 店家資訊 //////////////////
				VendorService vendorService = new VendorService();
				VendorVO vendorVO = vendorService.getOneVendor(venId);
				VendorPicService vendorPicService = new VendorPicService();
				List<VendorPicVO> vendorpicVOList = vendorPicService.getThisVendorpic(venId);
				req.setAttribute("vendorVO", vendorVO);
				req.setAttribute("vendorpicVOList", vendorpicVOList);
				//////////////// 評價資訊 //////////////////
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> roomOrderVOList = roomOrderService.getByVendorId(venId);
				List<Map<String, Object>> reviewList = new ArrayList<Map<String, Object>>();
				UsersService usersService = new UsersService();
				RoomService roomService = new RoomService();
				OrderDetailService orderDetailService = new OrderDetailService();
				for (RoomOrderVO orderVO : roomOrderVOList) {
					if (orderVO.getReviewsTime() != null) {
						Map<String, Object> review = new LinkedHashMap<String, Object>();
						UsersVO usersVO = usersService.getOneUser(orderVO.getUserId());
						List<OrderDetailVO> orderDetailVOList = orderDetailService.getByOrderID(orderVO.getOrderId());
						List<String> roomNameList = new ArrayList<String>();
						for (OrderDetailVO orderDetailVO : orderDetailVOList) {
							roomNameList.add(roomService.getOneRoom(orderDetailVO.getRoomId()).getRoomName());
						}
						review.put("userNick", usersVO.getUserNickname());
						review.put("roomNameList", roomNameList);
						review.put("stayDay", orderVO.getCheckinDate());
						review.put("reviews", orderVO.getReviews());
						review.put("score", orderVO.getScore());
						review.put("reviewsTime", orderVO.getReviewsTime());
						reviewList.add(review);
					}
				}
				req.setAttribute("reviewList", reviewList);
				/*************************** 輸入格式的錯誤處理 **********************/
				LocalDate lStartDay = LocalDate.now();
				LocalDate lEndDay = LocalDate.now();
				if (startDay.trim().isEmpty()) { // 入住日驗證
					errorMsgs.put("ckinErrorMsg", "請選擇入住日");
				} else {
					try {
						lStartDay = LocalDate.parse(startDay, DATEFORMATTER);
						if (lStartDay.isBefore(LocalDate.now())) {
							errorMsgs.put("ckinErrorMsg", "入住日不可小於今日");
						}
					} catch (DateTimeParseException e) {
						errorMsgs.put("ckinErrorMsg", "日期格式錯誤");
					}
				}
				if (endDay.trim().isEmpty()) { // 退房日驗證
					errorMsgs.put("ckoutErrorMsg", "請選擇退房日");
				} else {
					try {
						lEndDay = LocalDate.parse(endDay, DATEFORMATTER);
						if (!lStartDay.isBefore(lEndDay)) {
							errorMsgs.put("ckoutErrorMsg", "退房日需大於入住日");
						}
					} catch (DateTimeParseException e) {
						errorMsgs.put("ckoutErrorMsg", "日期格式錯誤");
					}
				}
				if (people == null || !people.matches(peopleReg)) { // 人數驗證
					errorMsgs.put("peopleErrorMsg", "請選擇人數");
				}
				if (roomAmount == null || !roomAmount.matches(roomAmountReg)) { // 房數驗證
					errorMsgs.put("roomAmtErrorMsg", "請選擇房數");
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs);
					req.getRequestDispatcher("/front-end/room/room_in_vendor.jsp").forward(req, res);
					return;// 程式中斷
				}

				//////////////// 房型資訊 //////////////////
				StockService stockService = new StockService();
				List<RoomVO> roomVOList = roomService.getAllByVen(venId); // 取出旅宿中所有房型list
				Stream<LocalDate> stream = lStartDay.datesUntil(lEndDay, Period.ofDays(1));
				List<LocalDate> dateList = stream.collect(Collectors.toList());
				RoomPhotoService roomPhotoService = new RoomPhotoService();
				List<Map<String, Object>> roomList = new ArrayList<Map<String, Object>>();
				room: for (RoomVO roomVO : roomVOList) {
					Map<String, Object> oneRoom = new LinkedHashMap<String, Object>();
					int amount = 100;
					if (roomVO.getRoomPeople() >= Integer.parseInt(people) && roomVO.getRoomUpdate().equals(1)) { // 房間人數要>=需求人數&&房型是上架的
						for (LocalDate day : dateList) {
							StockVO oneStock = stockService.getOneStock(roomVO.getRoomId(), day);
							if (oneStock == null || oneStock.getRoomRest() < Integer.parseInt(roomAmount)) {
								continue room; // 如果沒有庫存資料或其中有一天庫存小於需求，換下一房 (每一天要>=需求房數)
							}else if (stockService.getOneStock(roomVO.getRoomId(), day).getRoomRest() < amount) {
								amount = stockService.getOneStock(roomVO.getRoomId(), day).getRoomRest(); // 取得庫存量
							}
						}
						List<RoomPhotoVO> roomPhotoVOList = roomPhotoService.getThisRoomPhoto(roomVO.getRoomId());
						List<Integer> rmpIdList = new ArrayList<Integer>();
						for (RoomPhotoVO photoVO : roomPhotoVOList) {
							rmpIdList.add(photoVO.getRoomPhotoId());
						}
						oneRoom.put("roomPhIdList", rmpIdList);
						oneRoom.put("roomName", roomVO.getRoomName());
						oneRoom.put("roomId", roomVO.getRoomId());
						oneRoom.put("roomPeople", roomVO.getRoomPeople());
						oneRoom.put("roomIntro", roomVO.getRoomIntro());
						oneRoom.put("roomRest", amount);
						oneRoom.put("roomPrice", roomVO.getRoomPrice());
						oneRoom.put("breakfast", roomVO.getBreakfast());
						oneRoom.put("air_condotioner", roomVO.getAirCondotioner());
						oneRoom.put("wifi", roomVO.getWifi());
						oneRoom.put("television", roomVO.getTelevision());
						oneRoom.put("safebox", roomVO.getSafebox());
						oneRoom.put("fridge", roomVO.getFridge());
						oneRoom.put("water_boiler", roomVO.getWaterBoiler());
						oneRoom.put("bathroom", roomVO.getBathroom());
						oneRoom.put("toiletries", roomVO.getToiletries());
						roomList.add(oneRoom);
						
						//////////////////增加觀看次數
						roomVO.setRoomView(roomVO.getRoomView() + 1);
						roomService.updateRoom(roomVO);
					}
				}

				if (roomList.size() == 0) {
					req.setAttribute("noData", "搜尋期間房型搶購一空，請更改搜尋條件");
					req.getRequestDispatcher("/front-end/room/room_in_vendor.jsp").forward(req, res);
					return;
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("roomList", roomList);
				String url = "/front-end/room/room_in_vendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room/room_search_result.jsp");
				failureView.forward(req, res);
				return;
			}
		}

		if ("showVenAllRooms".equals(action)) { // 顯示廠商所有房型
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			try {
				Integer venId = Integer.parseInt(req.getParameter("venId"));
				UsersVO users = (UsersVO) req.getSession().getAttribute("usersVO");
				Integer userId = 0;
				if(users != null) {
					userId = users.getUserId();
				}
				/*************************** 2.開始查詢資料 *****************************************/
				////////////////店家收藏資訊 //////////////////
				ColVenService colVenService = new ColVenService();
				ColVenVO colVenVO = colVenService.getOneColVen(venId, userId);
				req.setAttribute("colVenVO", colVenVO);
				//////////////// 店家資訊 //////////////////
				VendorService vendorService = new VendorService();
				VendorVO vendorVO = vendorService.getOneVendor(venId);
				VendorPicService vendorPicService = new VendorPicService();
				List<VendorPicVO> vendorpicVOList = vendorPicService.getThisVendorpic(venId);
				//////////////// 評價資訊 //////////////////
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> roomOrderVOList = roomOrderService.getByVendorId(venId);
				List<Map<String, Object>> reviewList = new ArrayList<Map<String, Object>>();
				UsersService usersService = new UsersService();
				RoomService roomService = new RoomService();
				OrderDetailService orderDetailService = new OrderDetailService();
				for (RoomOrderVO orderVO : roomOrderVOList) {
					if (orderVO.getReviewsTime() != null) {
						Map<String, Object> review = new LinkedHashMap<String, Object>();
						UsersVO usersVO = usersService.getOneUser(orderVO.getUserId());
						List<OrderDetailVO> orderDetailVOList = orderDetailService.getByOrderID(orderVO.getOrderId());
						List<String> roomNameList = new ArrayList<String>();
						for (OrderDetailVO orderDetailVO : orderDetailVOList) {
							roomNameList.add(roomService.getOneRoom(orderDetailVO.getRoomId()).getRoomName());
						}
						review.put("userNick", usersVO.getUserNickname());
						review.put("roomNameList", roomNameList);
						review.put("stayDay", orderVO.getCheckinDate());
						review.put("reviews", orderVO.getReviews());
						review.put("score", orderVO.getScore());
						review.put("reviewsTime", orderVO.getReviewsTime());
						reviewList.add(review);
					}
				}
				//////////////// 房型資訊 //////////////////
				List<RoomVO> roomVOList = roomService.getAllByVen(venId); // 取出旅宿中所有房型list
				RoomPhotoService roomPhotoService = new RoomPhotoService();
				List<Map<String, Object>> roomList = new ArrayList<Map<String, Object>>();
				for (RoomVO roomVO : roomVOList) {
					Map<String, Object> oneRoom = new LinkedHashMap<String, Object>();
					if (roomVO.getRoomUpdate().equals(1)) { // 房型是上架的
						List<RoomPhotoVO> roomPhotoVOList = roomPhotoService.getThisRoomPhoto(roomVO.getRoomId());
						List<Integer> rmpIdList = new ArrayList<Integer>();
						for (RoomPhotoVO photoVO : roomPhotoVOList) {
							rmpIdList.add(photoVO.getRoomPhotoId());
						}
						oneRoom.put("roomPhIdList", rmpIdList);
						oneRoom.put("roomId", roomVO.getRoomId());
						oneRoom.put("roomName", roomVO.getRoomName());
						oneRoom.put("roomPeople", roomVO.getRoomPeople());
						oneRoom.put("roomIntro", roomVO.getRoomIntro());
						oneRoom.put("roomPrice", roomVO.getRoomPrice());
						oneRoom.put("breakfast", roomVO.getBreakfast());
						oneRoom.put("air_condotioner", roomVO.getAirCondotioner());
						oneRoom.put("wifi", roomVO.getWifi());
						oneRoom.put("television", roomVO.getTelevision());
						oneRoom.put("safebox", roomVO.getSafebox());
						oneRoom.put("fridge", roomVO.getFridge());
						oneRoom.put("water_boiler", roomVO.getWaterBoiler());
						oneRoom.put("bathroom", roomVO.getBathroom());
						oneRoom.put("toiletries", roomVO.getToiletries());
						roomList.add(oneRoom);
						
						//////////////////增加觀看次數
						roomVO.setRoomView(roomVO.getRoomView() + 1);
						roomService.updateRoom(roomVO);		
					}
				}
				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("vendorVO", vendorVO);
				req.setAttribute("vendorpicVOList", vendorpicVOList);
				req.setAttribute("reviewList", reviewList);
				req.setAttribute("roomList", roomList);
				String url = "/front-end/room/all_room_in_vendor.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/room/room_index.jsp");
				failureView.forward(req, res);
				return;
			}
		}
		
		if ("toRoomIndex".equals(action)) { //至訂房首頁
			try {
				RoomService roomService = new RoomService();
				VendorService vendorService = new VendorService();
				////Top10熱門房型
				List<RoomVO> roomVOList = roomService.getAll();
				Collections.sort(roomVOList, new RoomSort());
				Set<Integer> venSet = new TreeSet<Integer>();
				for(RoomVO roomVO : roomVOList) {
					venSet.add(roomVO.getVenId());
					if(venSet.size() == 10) {
						break;
					}
				}
				String[] regexes = {"(.*)台北(.*)", "(.*)臺北(.*)", "(.*)新北(.*)", "(.*)桃園(.*)",
						"(.*)臺中(.*)", "(.*)台中(.*)", "(.*)臺南(.*)", "(.*)台南(.*)", "(.*)高雄(.*)", 
						"(.*)宜蘭(.*)", "(.*)花蓮(.*)", "(.*)臺東(.*)", "(.*)台東(.*)", "(.*)新竹(.*)",
						"(.*)屏東(.*)", "(.*)苗栗(.*)", "(.*)彰化(.*)", "(.*)南投(.*)", "(.*)雲林(.*)",
						"(.*)嘉義(.*)", "(.*)基隆(.*)", "(.*)澎湖(.*)", "(.*)金門(.*)", "(.*)連江(.*)"};
				List<Map<String, Object>> hotList = new ArrayList<Map<String,Object>>();
				for(Integer venId : venSet) {
					VendorVO vendorVO = vendorService.getOneVendor(venId);
					Map<String, Object> hotMap = new HashMap<String, Object>();
					String area = "";
					for(String regex : regexes) {
						if(vendorVO.getVenLocation().matches(regex)) {
							area = regex.substring(4, 6);
							break;
						}
					}
					List<RoomVO> roomVOs = roomService.getAllByVen(venId);
					int roomprice = 200000;
					for(RoomVO roomVO : roomVOs) {
						if (roomVO.getRoomPrice() < roomprice) {
							roomprice = roomVO.getRoomPrice(); // 取得最便宜的那間
						}
					}
					hotMap.put("venId", venId);
					hotMap.put("venName", vendorVO.getVenName());
					hotMap.put("area", area);
					hotMap.put("price", roomprice);
					hotMap.put("score", vendorVO.getVenScore());
					hotList.add(hotMap);
				}
				//////台北Top10
				List<VendorVO> vendorVOs1 = vendorService.getByKeyword("台北");
				List<VendorVO> vendorVOs2 = vendorService.getByKeyword("臺北");
				List<RoomVO> roomVOTPs = new ArrayList<RoomVO>();
				for(VendorVO vo : vendorVOs1) {
					List<RoomVO> allByVen = roomService.getAllByVen(vo.getVenId());
					for(RoomVO roomVO : allByVen) {
						roomVOTPs.add(roomVO);
					}
				}
				for(VendorVO vo : vendorVOs2) {
					List<RoomVO> allByVen = roomService.getAllByVen(vo.getVenId());
					for(RoomVO roomVO : allByVen) {
						roomVOTPs.add(roomVO);
					}
				}
				Collections.sort(roomVOTPs, new RoomSort());
				Set<Integer> venTPSet = new TreeSet<Integer>();
				for(RoomVO roomVO : roomVOTPs) {
					venTPSet.add(roomVO.getVenId());
					if(venTPSet.size() == 10) {
						break;
					}
				}
				List<Map<String, Object>> TPList = new ArrayList<Map<String,Object>>();
				for(Integer venId : venTPSet) {
					VendorVO vendorVO = vendorService.getOneVendor(venId);
					Map<String, Object> hotMap = new HashMap<String, Object>();
					List<RoomVO> roomVOs = roomService.getAllByVen(venId);
					int roomprice = 200000;
					for(RoomVO roomVO : roomVOs) {
						if (roomVO.getRoomPrice() < roomprice) {
							roomprice = roomVO.getRoomPrice(); // 取得最便宜的那間
						}
					}
					hotMap.put("venId", venId);
					hotMap.put("venName", vendorVO.getVenName());
					hotMap.put("price", roomprice);
					hotMap.put("score", vendorVO.getVenScore());
					TPList.add(hotMap);
				}
				
				//////台中Top10
				List<VendorVO> vendorVOs3 = vendorService.getByKeyword("台中");
				List<VendorVO> vendorVOs4 = vendorService.getByKeyword("臺中");
				List<RoomVO> roomVOTCs = new ArrayList<RoomVO>();
				for(VendorVO vo : vendorVOs3) {
					List<RoomVO> allByVen = roomService.getAllByVen(vo.getVenId());
					for(RoomVO roomVO : allByVen) {
						roomVOTCs.add(roomVO);
					}
				}
				for(VendorVO vo : vendorVOs4) {
					List<RoomVO> allByVen = roomService.getAllByVen(vo.getVenId());
					for(RoomVO roomVO : allByVen) {
						roomVOTCs.add(roomVO);
					}
				}
				Collections.sort(roomVOTCs, new RoomSort());
				Set<Integer> venTCSet = new TreeSet<Integer>();
				for(RoomVO roomVO : roomVOTCs) {
					venTCSet.add(roomVO.getVenId());
					if(venTCSet.size() == 10) {
						break;
					}
				}
				List<Map<String, Object>> TCList = new ArrayList<Map<String,Object>>();
				for(Integer venId : venTCSet) {
					VendorVO vendorVO = vendorService.getOneVendor(venId);
					Map<String, Object> hotMap = new HashMap<String, Object>();
					List<RoomVO> roomVOs = roomService.getAllByVen(venId);
					int roomprice = 200000;
					for(RoomVO roomVO : roomVOs) {
						if (roomVO.getRoomPrice() < roomprice) {
							roomprice = roomVO.getRoomPrice(); // 取得最便宜的那間
						}
					}
					hotMap.put("venId", venId);
					hotMap.put("venName", vendorVO.getVenName());
					hotMap.put("price", roomprice);
					hotMap.put("score", vendorVO.getVenScore());
					TCList.add(hotMap);
				}			
				
				//////台南Top10
				List<VendorVO> vendorVOs5 = vendorService.getByKeyword("台南");
				List<VendorVO> vendorVOs6 = vendorService.getByKeyword("臺南");
				List<RoomVO> roomVOTNs = new ArrayList<RoomVO>();
				for(VendorVO vo : vendorVOs5) {
					List<RoomVO> allByVen = roomService.getAllByVen(vo.getVenId());
					for(RoomVO roomVO : allByVen) {
						roomVOTNs.add(roomVO);
					}
				}
				for(VendorVO vo : vendorVOs6) {
					List<RoomVO> allByVen = roomService.getAllByVen(vo.getVenId());
					for(RoomVO roomVO : allByVen) {
						roomVOTNs.add(roomVO);
					}
				}
				Collections.sort(roomVOTNs, new RoomSort());
				Set<Integer> venTNSet = new TreeSet<Integer>();
				for(RoomVO roomVO : roomVOTNs) {
					venTNSet.add(roomVO.getVenId());
					if(venTNSet.size() == 10) {
						break;
					}
				}
				List<Map<String, Object>> TNList = new ArrayList<Map<String,Object>>();
				for(Integer venId : venTNSet) {
					VendorVO vendorVO = vendorService.getOneVendor(venId);
					Map<String, Object> hotMap = new HashMap<String, Object>();
					List<RoomVO> roomVOs = roomService.getAllByVen(venId);
					int roomprice = 200000;
					for(RoomVO roomVO : roomVOs) {
						if (roomVO.getRoomPrice() < roomprice) {
							roomprice = roomVO.getRoomPrice(); // 取得最便宜的那間
						}
					}
					hotMap.put("venId", venId);
					hotMap.put("venName", vendorVO.getVenName());
					hotMap.put("price", roomprice);
					hotMap.put("score", vendorVO.getVenScore());
					TNList.add(hotMap);
				}
				
				
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("hotList", hotList);
				req.setAttribute("TPList", TPList);
				req.setAttribute("TCList", TCList);
				req.setAttribute("TNList", TNList);
				String url = "/front-end/room/room_index.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
		
	}
	
	//以Room的觀看次數升序排列
	private class RoomSort implements Comparator<RoomVO> { 
		public int compare(RoomVO a, RoomVO b) 
		{ 
			return b.getRoomView() - a.getRoomView(); 
		} 
	} 

}
