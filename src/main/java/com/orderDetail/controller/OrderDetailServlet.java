package com.orderDetail.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.orderDetail.model.*;
import com.roomOrder.model.*;


@WebServlet("/OrderDetail")
public class OrderDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOrderDetailByOrder".equals(action)) { // 取得該訂單所有訂單明細
//待判斷會員是否為該訂單會員			
			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer orderId = new Integer(req.getParameter("orderId"));
				/*************************** 開始查詢資料 ****************************************/
				RoomOrderService roomOrderService = new RoomOrderService();
				RoomOrderVO roomOrderVO = roomOrderService.getOneRoomOrder(orderId);
				/*************************** 若查無此訂單，回訂單清單 ****************************************/
				if(roomOrderVO == null) { 
					req.getRequestDispatcher("/front-end/room/user_order_list.jsp").forward(req, res);
					return;
				}
				OrderDetailService orderDetailService = new OrderDetailService();
				List<OrderDetailVO> list = orderDetailService.getByOrderID(orderId);
				
				/*************************** 查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("roomOrderVO", roomOrderVO);
				req.setAttribute("orderDetailList", list);
				String url = "/front-end/room/user_order_detail.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);
				
			} catch (Exception e) {
				req.getRequestDispatcher("/front-end/room/user_order_list.jsp").forward(req, res);
			}
		}
	}

}
