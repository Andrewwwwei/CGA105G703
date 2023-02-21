package com.stock.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomOrder.controller.RoomOrderServlet;
import com.roomOrder.model.RoomOrderVO;
import com.stock.model.StockService;

@WebServlet("/Stock")
public class StockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("getVendorAllStockByDate".equals(action)) {  //廠商查詢剩餘房數
			Integer venId = (Integer)req.getSession().getAttribute("venId");
			if(venId == null) {
				RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
				failureView.forward(req, res);
				return;
			}
			Map<String,String> errorMsgs = new LinkedHashMap<String,String>();
			DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			try {
				/*************************** 1.接收請求參數 + 參數錯誤判斷 ****************************************/
				String startDay = req.getParameter("start-day");
				String endDay = req.getParameter("end-day");
				req.setAttribute("startDay", startDay);
				req.setAttribute("endDay", endDay);
				LocalDate lStartDay = LocalDate.now();
				LocalDate lEndDay = LocalDate.now();
				if (startDay.trim().isEmpty()) { // 入住日驗證
					errorMsgs.put("ckinErrorMsg", "請選擇入住日");
				}else {
					try {
						lStartDay = LocalDate.parse(startDay, DATEFORMATTER);
						if(lStartDay.isBefore(LocalDate.now())) {
							errorMsgs.put("ckinErrorMsg", "入住日不可小於今日");
						}
					}catch(DateTimeParseException e) {
						errorMsgs.put("ckinErrorMsg", "日期格式錯誤");
					}
				}
				if (endDay.trim().isEmpty()) {  // 退房日驗證
					errorMsgs.put("ckoutErrorMsg", "請選擇退房日");
				} else {
					try {
						lEndDay = LocalDate.parse(endDay, DATEFORMATTER);
						if (!lStartDay.isBefore(lEndDay)) {
							errorMsgs.put("ckoutErrorMsg", "退房日需大於入住日");
						}
					}catch(DateTimeParseException e) {
						errorMsgs.put("ckoutErrorMsg", "日期格式錯誤");
					}
				}
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("errorMsgs", errorMsgs);
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/vendor_room_rest.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				/***************************2.開始查詢資料*****************************************/
				RoomService roomService = new RoomService();
				List<RoomVO> roomList = roomService.getAllByVen(venId);
				StockService stockService = new StockService();
				Stream<LocalDate> stream =lStartDay.datesUntil(lEndDay, Period.ofDays(1));
				List<LocalDate> dateList = stream.collect(Collectors.toList());
				List<List<String>> resultList = new ArrayList<List<String>>();
				for(RoomVO roomVO : roomList) {
					List<Integer> amountList = new ArrayList<Integer>();
					for(LocalDate day : dateList) {
						if(stockService.getOneStock(roomVO.getRoomId(), day) != null) {
							amountList.add(stockService.getOneStock(roomVO.getRoomId(), day).getRoomRest());
						}
					}
					if(amountList.size() > 0) {
						List<String> roomRestList = new ArrayList<String>();
						roomRestList.add(roomVO.getRoomName());
						roomRestList.add(Collections.min(amountList).toString());
						roomRestList.add(roomVO.getRoomAmount().toString());
						resultList.add(roomRestList);
					}
				}
				/*************************** 準備轉交(Send the Success view) ************/
				req.setAttribute("resultList", resultList);
				req.getRequestDispatcher("/vendor/vendor_room_rest.jsp").forward(req, res);
				return;
				
			}catch(Exception e) {
				e.printStackTrace();
				RoomOrderServlet roomOrderServlet = new RoomOrderServlet();
				roomOrderServlet.toIndex(req, res, venId);
			}
		}
	}

}
