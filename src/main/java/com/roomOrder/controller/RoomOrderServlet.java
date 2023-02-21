package com.roomOrder.controller;

import java.io.IOException;
import java.io.InputStream;
import java.math.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;
import com.roomOrder.model.*;
import com.stock.model.StockService;
import com.stock.model.StockVO;
import com.Mes.model.MesService;
import com.Users.model.UsersService;
import com.Users.model.UsersVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;


@WebServlet("/RoomOrder")
public class RoomOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("toThisUserOrder".equals(action)) { // 會員訂單管理
			try {
				UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
				if(usersVO == null) {
					req.getRequestDispatcher("/front-end/member/login.jsp").forward(req, res);
					return;
				}
				Integer userId = usersVO.getUserId();
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> list = roomOrderService.getByUserIdOrderByCheckin(userId);       
				req.setAttribute("orderListByCheckin", list);
				req.getRequestDispatcher("/front-end/room/user_order_list.jsp").forward(req, res);
				return;
			}catch (Exception e) {
				req.getRequestDispatcher("/Room?action=toRoomIndex").forward(req, res);
				return;
			}
		}
		
		if ("getOneOrder".equals(action)) { // 搜尋該廠商某訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				if(venId == null) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				List<RoomOrderVO> roomOrderList = new ArrayList<RoomOrderVO>();
				RoomOrderService roomOrderService = new RoomOrderService();
				req.setAttribute("orderTab", 5);
				String keyword = req.getParameter("keyword");
				try {
					Integer orderId = Integer.parseInt(keyword.trim());
					/*************************** 查詢資料 ************************/
					RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(orderId);
					if(roomOrderVO == null) { //查無資料
						req.getRequestDispatcher("/vendor/vendor_order_list.jsp").forward(req, res);
						return;
					}else if(!roomOrderVO.getVenId().equals(venId)) { //查無資料
						req.getRequestDispatcher("/vendor/vendor_order_list.jsp").forward(req, res);
						return;
					/*************************** 準備轉交(Send the Success view) ************/
					}else {
						roomOrderList.add(roomOrderVO);
						req.setAttribute("roomOrderList", roomOrderList);
						req.getRequestDispatcher("/vendor/vendor_order_list.jsp").forward(req, res);
						return;
					}
					
				} catch (NumberFormatException n) { //用姓名搜尋
					/*************************** 查詢資料 ************************/
					if(keyword.trim().length() == 0) {
						req.getRequestDispatcher("/vendor/vendor_order_list.jsp").forward(req, res);
						return;
					}
					String name = keyword.trim();
					List<RoomOrderVO> venOrderList = roomOrderService.getByVendorId(venId);
					for(RoomOrderVO rv : venOrderList) {
						if(rv.getCustomerName().contains(name)) {
							roomOrderList.add(rv);
						}
					}
					if(roomOrderList.size() == 0) { //查無資料
						req.getRequestDispatcher("/vendor/vendor_order_list.jsp").forward(req, res);
						return;
					}
					/*************************** 準備轉交(Send the Success view) ************/
					req.setAttribute("roomOrderList", roomOrderList);
					req.getRequestDispatcher("/vendor/vendor_order_list.jsp").forward(req, res);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				toIndex(req, res, (Integer) req.getSession().getAttribute("venId"));
			}
		}

		if ("getAllVendorOrder".equals(action)) { // 該廠商所有訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				if(venId == null) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 查詢資料 ************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> roomOrderList = roomOrderService.getByVendorId(venId);
				/*************************** 準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderList", roomOrderList);
				req.setAttribute("orderTab", 4);
				String url = "/vendor/vendor_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				toIndex(req, res, (Integer) req.getSession().getAttribute("venId"));
			}
		}

		if ("getfutureVendorOrder".equals(action)) { // 該廠商待入住訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				if(venId == null) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 查詢資料 ************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> roomOrderList = roomOrderService.getByVendorId(venId);
				List<RoomOrderVO> newRoomOrderList = new ArrayList<RoomOrderVO>();
				for (RoomOrderVO roomOrderVO : roomOrderList) {
					if (roomOrderVO.getOrderStatus() == 0) {
						newRoomOrderList.add(roomOrderVO);
					}
				}
				/*************************** 準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderList", newRoomOrderList);
				req.setAttribute("orderTab", 0);
				String url = "/vendor/vendor_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				toIndex(req, res, (Integer) req.getSession().getAttribute("venId"));
			}
		}

		if ("getLivingVendorOrder".equals(action)) { // 該廠商入住中訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				if(venId == null) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 查詢資料 ************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> roomOrderList = roomOrderService.getByVendorId(venId);
				List<RoomOrderVO> newRoomOrderList = new ArrayList<RoomOrderVO>();
				for (RoomOrderVO roomOrderVO : roomOrderList) {
					if (roomOrderVO.getOrderStatus() == 1) {
						newRoomOrderList.add(roomOrderVO);
					}
				}
				/*************************** 準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderList", newRoomOrderList);
				req.setAttribute("orderTab", 1);
				String url = "/vendor/vendor_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				toIndex(req, res, (Integer) req.getSession().getAttribute("venId"));
			}
		}

		if ("getFinishVendorOrder".equals(action)) { // 該廠商已完成訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				if(venId == null) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 查詢資料 ************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> roomOrderList = roomOrderService.getByVendorId(venId);
				List<RoomOrderVO> newRoomOrderList = new ArrayList<RoomOrderVO>();
				for (RoomOrderVO roomOrderVO : roomOrderList) {
					if (roomOrderVO.getOrderStatus() == 2) {
						newRoomOrderList.add(roomOrderVO);
					}
				}
				/*************************** 準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderList", newRoomOrderList);
				req.setAttribute("orderTab", 2);
				String url = "/vendor/vendor_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				toIndex(req, res, (Integer) req.getSession().getAttribute("venId"));
			}
		}

		if ("getCancelVendorOrder".equals(action)) { // 該廠商已取消訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				if(venId == null) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				/*************************** 查詢資料 ************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				List<RoomOrderVO> roomOrderList = roomOrderService.getByVendorId(venId);
				List<RoomOrderVO> newRoomOrderList = new ArrayList<RoomOrderVO>();
				for (RoomOrderVO roomOrderVO : roomOrderList) {
					if (roomOrderVO.getOrderStatus() == 3) {
						newRoomOrderList.add(roomOrderVO);
					}
				}
				/*************************** 準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderList", newRoomOrderList);
				req.setAttribute("orderTab", 3);
				String url = "/vendor/vendor_order_list.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				toIndex(req, res, (Integer) req.getSession().getAttribute("venId"));
			}
		}
		
		
		
		if ("checkin".equals(action)) { // 辦理入住
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer orderId = new Integer(req.getParameter("orderId"));
				/*************************** 開始更新資料 ****************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(orderId);
				roomOrderVO.setOrderStatus(1);
				roomOrderService.updateRoomOrder(roomOrderVO);
				/*************************** 更新完成,準備轉交(Send the Success view) ************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				toIndex(req, res, venId);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("checkout".equals(action)) { // 辦理退房
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer orderId = new Integer(req.getParameter("orderId"));
				/*************************** 開始更新資料 ****************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(orderId);
				roomOrderVO.setOrderStatus(2);
				roomOrderService.updateRoomOrder(roomOrderVO);
				///////////////////通知會員可評價
				byte[] buf = null;
				try (InputStream in = Files.newInputStream(Path.of(getServletContext().getRealPath("/front-end/room/images") + "/rating.png"))){
					buf = new byte[in.available()];
					in.read(buf);
				} catch (IOException e) {
					e.printStackTrace();
				}
				MesService mesService = new MesService();
				mesService.addMesVO(roomOrderVO.getUserId(), "訂單已完成",
						"您的住宿訂單已完成，快到我的訂單-訂單明細中留下評價，分享您的住宿經驗吧~",
						buf, new Timestamp(System.currentTimeMillis()), (byte) 1);
				/*************************** 更新完成,準備轉交(Send the Success view) ************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				toIndex(req, res, venId);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("toVendorIndex".equals(action)) { // 查詢該廠商訂單(首頁)
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer venId = (Integer) req.getSession().getAttribute("venId");
				if(venId == null) {
					RequestDispatcher failureView = req.getRequestDispatcher("/vendor/VenLogin.jsp");
					failureView.forward(req, res);
					return;
				}
				toIndex(req, res, venId);
				return;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if ("cancelOrder".equals(action)) { // 會員取消訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer orderId = new Integer(req.getParameter("orderId"));
				/*************************** 開始查詢資料 ****************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(orderId);
				/*************************** 若查無此訂單，回訂單清單 ****************************/
				if (roomOrderVO == null) {
					req.getRequestDispatcher("/front-end/room/user_order_list.jsp").forward(req, res);
					return;
				}
				OrderDetailService orderDetailService = new OrderDetailService();
				List<OrderDetailVO> list = orderDetailService.getByOrderID(orderId);
				/*************************** 更新訂單狀態，取消訂單 ****************************/
				roomOrderVO.setOrderStatus(3);
				roomOrderService.updateRoomOrder(roomOrderVO);
				///////////////////通知會員訂單已取消
				byte[] buf = null;
				try (InputStream in = Files.newInputStream(Path.of(getServletContext().getRealPath("/front-end/room/images") + "/cancelled.png"))){
					buf = new byte[in.available()];
					in.read(buf);
				} catch (IOException e) {
					e.printStackTrace();
				}
				MesService mesService = new MesService();
				mesService.addMesVO(roomOrderVO.getUserId(), "訂單已取消",
						"您的住宿訂單編號：" + orderId + "已取消",
						buf, new Timestamp(System.currentTimeMillis()), (byte) 1);
				///////////////歸還庫存量
				StockService stockService = new StockService();
				LocalDate lStartDay = roomOrderVO.getCheckinDate();
				LocalDate lEndDay = roomOrderVO.getCheckoutDate();
				Stream<LocalDate> stream = lStartDay.datesUntil(lEndDay, Period.ofDays(1));
				List<LocalDate> dateList = stream.collect(Collectors.toList());
				for(OrderDetailVO vo : list) {
					for(LocalDate day : dateList) {
						StockVO stockVO = stockService.getOneStock(vo.getRoomId(), day);
						if(stockVO != null) {
							stockVO.setRoomRest(stockVO.getRoomRest() + vo.getRoomAmount());
							stockService.updateStock(stockVO);
						}
					}
				}
				/*************************** 更新完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderVO", roomOrderVO);
				req.setAttribute("orderDetailList", list);
				String url = "/front-end/room/user_order_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("/front-end/room/user_order_list.jsp").forward(req, res);
			}
		}

		if ("reviewOrder".equals(action)) { // 會員評論訂單
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer orderId = new Integer(req.getParameter("orderId"));
				Integer score = new Integer(req.getParameter("score"));
				String reviews = req.getParameter("reviews").trim();
				/*************************** 開始查詢資料 ****************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(orderId);
				/*************************** 若查無此訂單，回訂單清單 ****************************/
				if (roomOrderVO == null) {
					req.getRequestDispatcher("/front-end/room/user_order_list.jsp").forward(req, res);
					return;
				}
				OrderDetailService orderDetailService = new OrderDetailService();
				List<OrderDetailVO> list = orderDetailService.getByOrderID(orderId);
				/*************************** 寫入評價及廠商評價數+1、總評分計算 ****************************/
				roomOrderVO.setScore(score);
				roomOrderVO.setReviews(reviews);
				roomOrderVO.setReviewsTime(LocalDate.now());
				VendorService vendorService = new VendorService();
				VendorVO vendorVO = vendorService.getOneVendor(roomOrderVO.getVenId());
				Float venScore = vendorVO.getVenScore();
				Integer venScorePeople = vendorVO.getVenScorePeople();
				if(venScore == null || venScore.equals(Float.valueOf("0")) || venScorePeople == null || venScorePeople.equals(0)) {
					vendorVO.setVenScore(score.floatValue());
					vendorVO.setVenScorePeople(1);
				}else {
					Float newScore = new BigDecimal(venScore)
					.multiply(new BigDecimal(venScorePeople))
					.add(new BigDecimal(score))
					.divide(new BigDecimal(venScorePeople + 1), 1, RoundingMode.HALF_UP).floatValue();
					vendorVO.setVenScore(newScore);
					vendorVO.setVenScorePeople(venScorePeople + 1);
				}
				roomOrderService.updateOrderReview(roomOrderVO, vendorVO);
				/*************************** 更新完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderVO", roomOrderVO);
				req.setAttribute("orderDetailList", list);
				String url = "/front-end/room/user_order_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("/front-end/room/user_order_list.jsp").forward(req, res);
			}
		}
	}

	public void toIndex(HttpServletRequest req, HttpServletResponse res, int venId)
			throws ServletException, IOException {
		RoomOrderService roomOrderService = new RoomOrderService();
		List<RoomOrderVO> allList = roomOrderService.getByVendorId(venId);
		List<RoomOrderVO> checkinList = new ArrayList<RoomOrderVO>();
		List<RoomOrderVO> checkoutList = new ArrayList<RoomOrderVO>();
		List<RoomOrderVO> livingList = new ArrayList<RoomOrderVO>();
		int livingAmount = 0;
		int checkinAmount = 0;
		int checkoutAmount = 0;

		for (RoomOrderVO order : allList) {
			if (order.getCheckinDate().isEqual(LocalDate.now()) && order.getOrderStatus() == 0) {
				checkinList.add(order);
			}
			if (order.getCheckoutDate().isEqual(LocalDate.now()) && order.getOrderStatus() == 1) {
				checkoutList.add(order);
			}

			if (order.getOrderStatus() == 1) {
				livingList.add(order);
			}

		}
		OrderDetailService orderDetailService = new OrderDetailService();
		for (RoomOrderVO order : checkinList) {
			List<OrderDetailVO> odList = orderDetailService.getByOrderID(order.getOrderId());
			for (OrderDetailVO od : odList) {
				checkinAmount += od.getRoomAmount();
			}
		}
		for (RoomOrderVO order : checkoutList) {
			List<OrderDetailVO> odList = orderDetailService.getByOrderID(order.getOrderId());
			for (OrderDetailVO od : odList) {
				checkoutAmount += od.getRoomAmount();
			}
		}
		for (RoomOrderVO order : livingList) {
			List<OrderDetailVO> odList = orderDetailService.getByOrderID(order.getOrderId());
			for (OrderDetailVO od : odList) {
				livingAmount += od.getRoomAmount();
			}
		}

		/*************************** 更新完成,準備轉交(Send the Success view) ************/
		req.setAttribute("checkinList", checkinList);
		req.setAttribute("checkoutList", checkoutList);
		req.setAttribute("livingList", livingList);
		req.setAttribute("livingAmount", livingAmount);
		req.setAttribute("checkinAmount", checkinAmount);
		req.setAttribute("checkoutAmount", checkoutAmount);
		String url = "/vendor/vendor_index.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);
	}

}
