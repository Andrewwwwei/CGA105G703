package com.tripMember.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Users.model.UsersService;
import com.Users.model.UsersVO;
import com.google.gson.Gson;
import com.tripMember.model.TripMemberService;
import com.tripMember.model.TripMemberVO;

import core.jedis.JedisHandler;

@WebServlet("/front-end/TripPlan/tripMbr.do")
public class TripMemberServlet extends HttpServlet{
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
		System.out.println(action);
		
		if ("insert".equals(action)) {//來自tripPlan.jsp的請求
			System.out.println("iiid");
			//1.接收請求參數
			Integer tripId = Integer.valueOf(req.getParameter("TRIP_ID"));
			String userAccount = req.getParameter("USER_ACCOUNT");
			String tripName = req.getParameter("TRIP_NAME");
			String userName = req.getParameter("USER_NAME");
			Boolean isMbr =false;//insert一律都是false
			
			//2.先查詢這個帳號是否存在
			UsersService userSvc = new UsersService();
			UsersVO userVO= userSvc.getOneUserEmail(userAccount);
			
			//查詢此帳號是否已在群組內
			TripMemberService tripMbrSvc = new TripMemberService();
			TripMemberVO tripMbrVO = tripMbrSvc.getOneTripMbr(tripId, userVO.getUserId());
			String retrunStr = "";
			Gson gson = new Gson();
			
			if (userVO == null || tripMbrVO !=null) {
				retrunStr = "fail";
			}else {
				Integer userId = userVO.getUserId();
				//3.拿到userId開始新增資料
				tripMbrSvc.addTripMbr(tripId, userId, isMbr);
				retrunStr = gson.toJson(userVO);
				
				//同時將邀請訊息加入Redis
				JedisHandler jHM = new JedisHandler();
				jHM.saveUserInTrip(userVO, tripName, tripId, userName);
			}
			
			//3.新增完成，轉交給JS
			PrintWriter out = res.getWriter();
			out.print(retrunStr);
			out.close();
			
		}
		
		
		if("updateInvite".equals(action)) {
			//1.接收請求參數
			String userId = req.getParameter("userId");
			Integer index =  Integer.valueOf(req.getParameter("index"));
			String status = req.getParameter("status");
			String tripId = req.getParameter("tripId");
			System.out.println("userID " + userId);
			System.out.println("tripId " + tripId);
			
			//2.update table
			TripMemberService tripMbrSvc = new TripMemberService();
			TripMemberVO tripMemberVO = tripMbrSvc.getOneTripMbr(Integer.valueOf(tripId), Integer.valueOf(userId));
			tripMemberVO.setIsMbr(true);
			tripMbrSvc.updateTripMbr(tripMemberVO);
			
			
			JedisHandler jedisHandler = new JedisHandler();
			jedisHandler.updateInvite(userId, index, status);
		}
		
		if("getUserPic".equals(action)) {
		   //1.接收請求參數
		   String userId = req.getParameter("UserID");
		   System.out.println("getUSerPic " + userId);
		   
		   //2.查詢user帳號
		   UsersService userSvc = new UsersService();
		   UsersVO userVO= userSvc.getOneUser(Integer.valueOf(userId));
		   String userAccount = "\""+userVO.getUserAccount()+"\"";
		   
		   req.getRequestDispatcher("/front-end/member/getUserPic.jsp?UserAccount="+userAccount).forward(req, res);
		  }
	}
	
}
