package com.ecpayPayment.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;
import com.shoppingCart.RoomItem;
import com.stock.model.StockService;
import com.Users.model.UsersVO;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutOneTime;

@WebServlet("/EcpayPayment")
public class EcpayPaymentServlet extends HttpServlet {
    public static AllInOne domain;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	req.setCharacterEncoding("UTF-8");
		@SuppressWarnings("unchecked")
		Vector<RoomItem> paylist = (Vector<RoomItem>) req.getSession().getAttribute("paymentItems");
		StockService stockService = new StockService();
		int total = 0;////總金額
////////// 確認庫存
		for (RoomItem roomItem : paylist) {
			Stream<LocalDate> stream = roomItem.getCheckinDate().datesUntil(roomItem.getCheckoutDate(),
					Period.ofDays(1));
			List<LocalDate> dateList = stream.collect(Collectors.toList());
			for (LocalDate day : dateList) {
				if (stockService.getOneStock(roomItem.getRoomId(), day).getRoomRest() < roomItem.getAmount()) {
					req.setAttribute("lossStock", "error");
					req.getRequestDispatcher("/ShoppingCart?action=showCart").forward(req, res);
					return; // 如果其中有一天庫存小於需求，中斷回傳
				}
			}
			total += roomItem.getPrice() * roomItem.getAmount();
		}
		UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
		Integer couponNo = (req.getParameter("couponNo") == null || req.getParameter("couponNo").equals("")) ? null : Integer.parseInt(req.getParameter("couponNo"));
		int couponDiscount = 0; ////優惠券折抵金額
		CouponService couponService = new CouponService();
		if(!(couponNo == null)) {
			CouponVO couponVO = couponService.getOneCoupon(couponNo);
			if(couponVO.getCouponType().equals(0)) {
				couponDiscount = couponVO.getCouponDiscount().intValue();
			}else {
				BigDecimal discount = new BigDecimal(couponVO.getCouponDiscount());
				BigDecimal totalPrice = new BigDecimal(total);
				discount = totalPrice.subtract(totalPrice.multiply(discount)).setScale(0, RoundingMode.HALF_UP);
				couponDiscount = discount.intValue();
			}
		}
		Boolean bonusPointsUse = (req.getParameterValues("bonus") != null ) ? true : false;
		Integer bonusPoint = 0; ////紅利點數
		if(bonusPointsUse) {
			if(total > usersVO.getBonusPoints()) {
				bonusPoint = usersVO.getBonusPoints();
			}else {
				bonusPoint = total;
			}
		}
		int payTotal = total - couponDiscount - bonusPoint;//付款金額
		
		String customerName = req.getParameter("customerName");
		String phone = req.getParameter("customerPhone");
		String email = req.getParameter("customerEmail");
		
		HashMap<String, Object> payInfo = new HashMap<String, Object>();
		payInfo.put("couponNo", couponNo);
		payInfo.put("customerName", customerName);
		payInfo.put("customerPhone", phone);
		payInfo.put("customerEmail", email);
		payInfo.put("orgPrice", total);
		payInfo.put("couponDiscount", couponDiscount);
		payInfo.put("bonusPoint", bonusPoint);
		req.getSession().setAttribute("payInfo", payInfo);
		
		if(payTotal < 1) {
			req.getRequestDispatcher("/ShoppingCart?action=newOrder").forward(req, res);
			return;
		}
        // 根據表單建立收款連結 (不要使用中文，編碼有問題)
        // 使用者跳轉至綠界的交易流程網站
        // 按照流程輸入卡號..... (中文編碼!)
            // 測試卡號: 一般信用卡測試卡號 : 4311-9522-2222-2222 安全碼 : 222
            // 信用卡測試有效月/年：輸入的 MM/YYYY 值請大於現在當下時間的月年，
            // 例如在 2016/04/20 當天作測試，請設定 05/2016(含)之後的有效月年，否則回應刷卡失敗。
            // 手機請輸入正確，因為會傳驗證碼
        // 檢查後台: 信用卡收單 - 交易明細 - 查詢
        domain = new AllInOne("");
        AioCheckOutOneTime obj = new AioCheckOutOneTime();
        // 從 view 獲得資料，依照 https://developers.ecpay.com.tw/?p=2866 獲得必要的參數
        // MerchantTradeNo  : 必填 特店訂單編號 (不可重複，因此需要動態產生)
        obj.setMerchantTradeNo(new String("salon" + System.currentTimeMillis()));
        // MerchantTradeDate  : 必填 特店交易時間 yyyy/MM/dd HH:mm:ss
        obj.setMerchantTradeDate(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new java.util.Date()));
        // TotalAmount  : 必填 交易金額
        obj.setTotalAmount(Integer.toString(payTotal));
        // TradeDesc  : 必填 交易描述
        obj.setTradeDesc("7TourTravelWebsite");
        // ItemName  : 必填 商品名稱
        obj.setItemName("RoomBooking");
        // ReturnURL   : 必填  當消費者付款完成後，綠界科技會以 Server POST 傳送付款結果參數到該Server網址
        obj.setReturnURL("a");
        // OrderResultURL   : 選填 消費者完成付費後。重新導向的位置
//        obj.setOrderResultURL("http://localhost:8081/CGA105G2/index.jsp");
        obj.setClientBackURL("http://" + req.getServerName() + ":8081" + req.getContextPath() + "/ShoppingCart?action=newOrder");
        obj.setNeedExtraPaidInfo("N");


        // 回傳form訂單 並自動將使用者導到 綠界
        String form = domain.aioCheckOut(obj, null);
        res.setCharacterEncoding("UTF-8");
        res.getWriter().print("<html><body>" + form + "</body></html>");
    }
}
