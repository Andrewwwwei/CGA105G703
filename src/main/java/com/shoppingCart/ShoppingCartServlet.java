package com.shoppingCart;

import java.io.IOException;
import java.io.InputStream;
import java.math.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.coupon.model.CouponService;
import com.coupon.model.CouponVO;
import com.orderDetail.model.OrderDetailService;
import com.orderDetail.model.OrderDetailVO;
import com.room.model.RoomService;
import com.room.model.RoomVO;
import com.roomOrder.controller.OrderEmail;
import com.roomOrder.model.RoomOrderService;
import com.roomOrder.model.RoomOrderVO;
import com.roomPhoto.model.RoomPhotoService;
import com.roomPhoto.model.RoomPhotoVO;
import com.stock.model.StockService;
import com.user_coupon.model.UserCouponService;
import com.user_coupon.model.UserCouponVO;
import com.Mes.model.MesService;
import com.Users.model.UsersService;
import com.Users.model.UsersVO;
import com.vendor.model.VendorService;
import com.vendor.model.VendorVO;
import com.vendorPic.model.VendorPicService;
import com.vendorPic.model.VendorPicVO;

@WebServlet("/ShoppingCart")
public class ShoppingCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		@SuppressWarnings("unchecked")
		Vector<RoomItem> buylist = (Vector<RoomItem>) session.getAttribute("shoppingcart");
		String action = req.getParameter("action");

		// 新增至購物車
		if (action.equals("ADD")) {
			try {
				Map<String, Object> resMap = new HashMap<String, Object>();
				RoomItem aRoomItem = getRoomItem(req);
				if (buylist == null) {
					buylist = new Vector<RoomItem>();
					buylist.add(aRoomItem);
					resMap.put("size", 1);
				} else {
					if (buylist.contains(aRoomItem)) {
						RoomItem hasRoomItem = buylist.get(buylist.indexOf(aRoomItem));
						hasRoomItem.setAmount(hasRoomItem.getAmount() + aRoomItem.getAmount());
						resMap.put("size", buylist.size());
					} else {
						buylist.add(aRoomItem);
						resMap.put("size", buylist.size());
					}
				}

				session.setAttribute("shoppingcart", buylist);
				resMap.put("status", "ok");
				JSONObject responseJSONObject = new JSONObject(resMap);
				res.getWriter().println(responseJSONObject);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// 刪除購物車項目
		if (action.equals("DELETE")) {
			try {
				Map<String, Object> resMap = new HashMap<String, Object>();
				int indexNum = Integer.parseInt(req.getParameter("indexNum"));
				buylist.removeElementAt(indexNum);
				resMap.put("size", buylist.size());

				session.setAttribute("shoppingcart", buylist);
				resMap.put("status", "ok");
				JSONObject responseJSONObject = new JSONObject(resMap);
				res.getWriter().println(responseJSONObject);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// 查看購物車
		if (action.equals("showCart")) {
			try {
				if (buylist == null || buylist.size() == 0) {
					req.getRequestDispatcher("/front-end/cart/emptyCart.jsp").forward(req, res);
					return;
				} else {
					RoomService roomService = new RoomService();
					StockService stockService = new StockService();
					List<Integer> amountList = new ArrayList<Integer>();
					for (RoomItem roomItem : buylist) {
						Stream<LocalDate> stream = roomItem.getCheckinDate().datesUntil(roomItem.getCheckoutDate(),
								Period.ofDays(1));
						List<LocalDate> dateList = stream.collect(Collectors.toList());
						int amount = 100;
						for (LocalDate day : dateList) {
							if (stockService.getOneStock(roomItem.getRoomId(), day).getRoomRest() < amount) {
								amount = stockService.getOneStock(roomItem.getRoomId(), day).getRoomRest(); // 取得庫存量
							}
						}
						amountList.add(amount);
					}
					req.setAttribute("amountList", amountList);
					req.getRequestDispatcher("/front-end/cart/cart.jsp").forward(req, res);
					return;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// 修改購物車項目數量
		if (action.equals("changeNum")) {
			try {
				int indexNum = Integer.parseInt(req.getParameter("indexNum"));
				int newAmount = Integer.parseInt(req.getParameter("newAmount"));
				buylist.get(indexNum).setAmount(newAmount);

				session.setAttribute("shoppingcart", buylist);
				res.getWriter().write("ok");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// 準備購物車結帳
		if (action.equals("preparePayment")) {
			try {
				String indexNum = req.getParameter("indexNum");
				String indexNum2 = indexNum.replaceAll("[\\[\\]]", "");
				String[] out = indexNum2.split(",");
				Vector<RoomItem> paylist = new Vector<RoomItem>();
				for (int i = 0; i < out.length; i++) {
					int index = Integer.parseInt(out[i]);
					paylist.add(buylist.get(index));
				}
				// 檢查庫存
				StockService stockService = new StockService();
				for (RoomItem roomItem : paylist) {
					Stream<LocalDate> stream = roomItem.getCheckinDate().datesUntil(roomItem.getCheckoutDate(),
							Period.ofDays(1));
					List<LocalDate> dateList = stream.collect(Collectors.toList());
					for (LocalDate day : dateList) {
						if (stockService.getOneStock(roomItem.getRoomId(), day).getRoomRest() < roomItem.getAmount()) {
							res.getWriter().write("no");
							return; // 如果其中有一天庫存小於需求，中斷回傳
						}
					}
				}

				session.setAttribute("paymentItems", paylist);
				res.getWriter().write("ok");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// 直接結帳準備 
		if (action.equals("directPreparePayment")) {
			try {
				RoomItem roomItem = getRoomItem(req);
				// 檢查庫存
				StockService stockService = new StockService();
				Stream<LocalDate> stream = roomItem.getCheckinDate().datesUntil(roomItem.getCheckoutDate(),
						Period.ofDays(1));
				List<LocalDate> dateList = stream.collect(Collectors.toList());
				for (LocalDate day : dateList) {
					if (stockService.getOneStock(roomItem.getRoomId(), day).getRoomRest() < roomItem.getAmount()) {
						res.getWriter().write("no");
						return; // 如果其中有一天庫存小於需求，中斷回傳
					}
				}
				Vector<RoomItem> paylist = new Vector<RoomItem>();
				paylist.add(roomItem);
				session.setAttribute("paymentItems", paylist);
				res.getWriter().write("ok");
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		// 至結帳頁面
		if (action.equals("goPayment")) {
			try {
				// 是否登入
				UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
				if(usersVO == null) {
					req.getRequestDispatcher("/front-end/member/login.jsp").forward(req, res);
					return;
				}
				Integer userId = usersVO.getUserId();
				
				@SuppressWarnings("unchecked")
				Vector<RoomItem> paylist = (Vector<RoomItem>) session.getAttribute("paymentItems");
				int total = 0;
				for (RoomItem roomItem : paylist) {
					total += roomItem.getPrice() * roomItem.getAmount();
				}
				req.setAttribute("total", total);
				UserCouponService userCouponService = new UserCouponService();
				CouponService couponService = new CouponService();
				List<UserCouponVO> userCouponVOList = userCouponService.gettByUserId(userId);
				List<CouponVO> couponVOList = new ArrayList<CouponVO>();
				Timestamp now = Timestamp.valueOf(LocalDateTime.now());
				for (UserCouponVO userCouponVO : userCouponVOList) {
					if (userCouponVO.getCouponQnt() > 0) { // 優惠券還未用完
						CouponVO couponVO = couponService.getOneCoupon(userCouponVO.getCouponNo());
						// 優惠券滿額條件有到達 && 在有效期限內
						if (total >= couponVO.getCouponSelector() && couponVO.getCouponEnd().after(now)
								&& (couponVO.getCouponStart().before(now) || couponVO.getCouponStart().equals(now))) {
							couponVOList.add(couponVO);
						}
					}
				}
				req.setAttribute("couponVOList", couponVOList);
				//取得5年
				ArrayList<Object> yearList = new ArrayList<Object>();
				LocalDate thisYear = LocalDate.now();
				yearList.add(thisYear.getYear());
				for(int i = 1; i <= 5 ; i++) {
					yearList.add(thisYear.plusYears(i).getYear());
				}
				req.setAttribute("yearList", yearList);
				UsersService usersService = new UsersService();
				req.setAttribute("usersVO", usersService.getOneUser(userId));
				req.getRequestDispatcher("/front-end/room/room_payment.jsp").forward(req, res);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("/front-end/room/room_index.jsp").forward(req, res);
				return;
			}
		}
		
		// 優惠券折扣計算
		if (action.equals("countCouponDiscount")) {
			try {
				Integer couponNo = Integer.parseInt(req.getParameter("couponNo"));
				Integer totalPrice = Integer.parseInt(req.getParameter("totalPrice"));
				Map<String, Object> resMap = new HashMap<String, Object>();
				CouponService couponService = new CouponService();
				CouponVO couponVO = couponService.getOneCoupon(couponNo);
				if(couponVO.getCouponType().equals(0)) {
					resMap.put("couponDiscount", couponVO.getCouponDiscount().intValue());
					resMap.put("payPrice", totalPrice - couponVO.getCouponDiscount().intValue());
				}else {
					BigDecimal discount = new BigDecimal(couponVO.getCouponDiscount());
					BigDecimal totalPriceB = new BigDecimal(totalPrice);
					discount = totalPriceB.subtract(totalPriceB.multiply(discount)).setScale(0, RoundingMode.HALF_UP);
					Integer couponDiscount = discount.intValue();
					resMap.put("couponDiscount", couponDiscount);
					resMap.put("payPrice", totalPrice - couponDiscount);
				}
				resMap.put("status", "ok");
				JSONObject responseJSONObject = new JSONObject(resMap);
				res.getWriter().println(responseJSONObject);
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		// 成立訂單
		if (action.equals("newOrder")) {
			try {
				Map<String, List<Object>> orderMap = new HashMap<String, List<Object>>();
				@SuppressWarnings("unchecked")
				Vector<RoomItem> paylist = (Vector<RoomItem>) session.getAttribute("paymentItems");
				StockService stockService = new StockService();
				HashMap<String, Object> payInfo = (HashMap<String, Object>) session.getAttribute("payInfo");
				int total = (int) payInfo.get("orgPrice");////總金額
				
				for (RoomItem roomItem : paylist) {
					List<Object> anOrder = new ArrayList<Object>();
					anOrder.add(roomItem.getVenName());
					anOrder.add(roomItem.getCheckinDate());
					anOrder.add(roomItem.getCheckoutDate());
					orderMap.put(roomItem.getVenName() + roomItem.getCheckinDate().toString() + roomItem.getCheckoutDate().toString()
							, anOrder);
				}
				
				VendorService vendorService = new VendorService();
				RoomService roomService = new RoomService();
				UsersService usersService = new UsersService();
				UsersVO usersVO = (UsersVO) req.getSession().getAttribute("usersVO");
				Integer couponNo = (Integer) payInfo.get("couponNo");
				Integer bonusPoint = (Integer) payInfo.get("bonusPoint"); ////紅利點數
				int couponDiscount = (int) payInfo.get("couponDiscount"); ////優惠券折抵金額
				
				String customerName = (String) payInfo.get("customerName");
				String phone = (String) payInfo.get("customerPhone");
				String email = (String) payInfo.get("customerEmail");
				List<List<Object>> allOrderList = new ArrayList<List<Object>>(orderMap.values());
				int orderIndex = 0;
				
		////////// 新增訂單(1.訂單與明細 2.更新庫存 3.更新會員 4.更新會員優惠券)
				String messageText = "親愛的顧客" + usersVO.getUserName() + "您好<br>"
						+ "    您" + LocalDate.now() + "於本網站訂購以下住宿，訂單已成立"
						+ "    <br>"; ////郵件內容
				RoomOrderService roomOrderService = new RoomOrderService();
				Integer newBP = new BigDecimal(total - bonusPoint - couponDiscount).divide(new BigDecimal(100), 0 , RoundingMode.HALF_UP).intValue();
				usersVO.setBonusPoints(usersVO.getBonusPoints() - bonusPoint + newBP);
				usersVO.setPurchaseTotal(usersVO.getPurchaseTotal() + total - bonusPoint - couponDiscount);
				if(usersVO.getPurchaseTotal() >= 50000) {
					usersVO.setUserShopLevel((byte)1);
				}
				Connection con = null;
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei", "root", "02021");
							
				// 1●設定於 pstm.executeUpdate()之前
				con.setAutoCommit(false);	
				
				for(List<Object> anOrder : allOrderList) {
					RoomOrderVO roomOrderVO = new RoomOrderVO();
					roomOrderVO.setCustomerName(customerName);
					roomOrderVO.setCustomerPhone(phone);
					roomOrderVO.setCustomerEmail(email);
					List<OrderDetailVO> detailList = new ArrayList<OrderDetailVO>();
					int i = 0;
					int subTotal = 0; /////小計
					for(RoomItem roomItem : paylist) {
						if(roomItem.getVenName().equals(anOrder.get(0)) && roomItem.getCheckinDate().equals(anOrder.get(1)) && roomItem.getCheckoutDate().equals(anOrder.get(2))) {
							if(i == 0) {
								roomOrderVO.setUserId(usersVO.getUserId()); 
								roomOrderVO.setVenId(roomService.getOneRoom(roomItem.getRoomId()).getVenId());
								roomOrderVO.setCouponNo(couponNo);
								roomOrderVO.setCheckinDate(roomItem.getCheckinDate());
								roomOrderVO.setCheckoutDate(roomItem.getCheckoutDate());
								roomOrderVO.setOrderTime(LocalDateTime.now());
								roomOrderVO.setOrderStatus(0);
							}
							OrderDetailVO orderDetailVO = new OrderDetailVO();
							orderDetailVO.setRoomId(roomItem.getRoomId());
							orderDetailVO.setRoomAmount(roomItem.getAmount());
							detailList.add(orderDetailVO);
							i++;
							subTotal += roomItem.getPrice() * roomItem.getAmount();
							
							///////////更新庫存
							Stream<LocalDate> stream = roomItem.getCheckinDate().datesUntil(roomItem.getCheckoutDate(),
									Period.ofDays(1));
							List<LocalDate> dateList = stream.collect(Collectors.toList());
							for (LocalDate day : dateList) {
								Integer newRest = stockService.getOneStock(roomItem.getRoomId(), day).getRoomRest() - roomItem.getAmount();
								stockService.updateStock(roomItem.getRoomId(), day, newRest, con);
							}
						}
					}
					
					if(orderIndex != allOrderList.size() - 1) {
						Integer thisBP = new BigDecimal(subTotal).divide(new BigDecimal(total), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(bonusPoint)).setScale(0, RoundingMode.HALF_UP).intValue();
						Integer thisCP = new BigDecimal(subTotal).divide(new BigDecimal(total), 4, RoundingMode.HALF_UP).multiply(new BigDecimal(couponDiscount)).setScale(0, RoundingMode.HALF_UP).intValue();
						total = total - subTotal;
						bonusPoint = bonusPoint - thisBP;
						couponDiscount = couponDiscount - thisCP;
						roomOrderVO.setBonusPointsUse(thisBP);
						roomOrderVO.setOrderCharge(subTotal);
						roomOrderVO.setOrderChargeDiscount(subTotal - thisBP - thisCP);
					}else {
						roomOrderVO.setBonusPointsUse(bonusPoint);
						roomOrderVO.setOrderCharge(subTotal);
						roomOrderVO.setOrderChargeDiscount(subTotal - bonusPoint - couponDiscount);
					}
					roomOrderService.addRoomOrderWithDetailUpdateUser(roomOrderVO, detailList, con);
					orderIndex++;
					
					//郵件內容
					messageText += "    <table style=\"border-collapse:collapse; border: 1px solid black; text-align: center; margin: 5px;\">"
				      		+ "        <tr>"
				      		+ "            <th style=\"background-color:#75c6e0; padding: 10px;\">" + vendorService.getOneVendor(roomOrderVO.getVenId()).getVenName() 
				      		+ "<br>" + roomOrderVO.getCheckinDate() + " 至 " + roomOrderVO.getCheckoutDate() + "</th>"
				      		+ "        </tr>"
				      		+ "        <tr style=\"border-collapse:collapse; border: 1px solid black;\"><td>";
					int listcount = 0;
					for(OrderDetailVO vo : detailList) {
						if(listcount != 0) {
							messageText += "<br>" + roomService.getOneRoom(vo.getRoomId()).getRoomName() + vo.getRoomAmount() + "間";
						}else {
							messageText += roomService.getOneRoom(vo.getRoomId()).getRoomName() + vo.getRoomAmount() + "間";
						}
					}
					messageText += "</td></tr></table>";
				}
				usersService.updateUser(usersVO, con);
				if(couponNo != null) {
					UserCouponService userCouponService = new UserCouponService();
					UserCouponVO userCouponVO = userCouponService.getOneUserCoupon(couponNo, usersVO.getUserId());
					userCouponVO.setCouponQnt(userCouponVO.getCouponQnt() - 1);
					userCouponService.updateUserCoupon(userCouponVO, con);
				}
				
				// 2●設定於 pstm.executeUpdate()之後
				con.commit();
				con.setAutoCommit(true);
				con.close();
				
	////////// 寄出通知訂單成立郵件
				String subject = "【7Tour】親愛的顧客您好，您的訂單已成立";
				messageText += "    訂單明細可至會員中心【我的訂單】查詢<br>"
			      		+ "    <br>"
			      		+ "    此為系統通知郵件，請勿回覆此郵件。<br>"
			      		+ "    <br>"
			      		+ "    感謝您使用7Tour旅遊網！<br>"
			      		+ "    祝您有個美好的旅程~<br>"
			      		+ "    7Tour訂房中心";	 
				OrderEmail orderEmail = new OrderEmail();
			    orderEmail.sendMail(usersVO.getUserAccount(), subject, messageText);	
    ///////// 站內通知
				byte[] buf = null;
				try (InputStream in = Files.newInputStream(Path.of(getServletContext().getRealPath("/front-end/room/images") + "/shopping-bag.png"))){
					buf = new byte[in.available()];
					in.read(buf);
				} catch (IOException e) {
					e.printStackTrace();
				}
				MesService mesService = new MesService();
				mesService.addMesVO(usersVO.getUserId(), "訂單已成立",
						"您的住宿訂單已成立，詳情可至我的訂單中查看",
						buf, new Timestamp(System.currentTimeMillis()), (byte) 1);
				
	////////// 刪除session paymentItems 及 shoppingcart已結帳項目
			    if(buylist != null) {
					for(RoomItem roomItem : paylist) {
						if(buylist.contains(roomItem)) {
							buylist.remove(roomItem);
						}
					}
					session.setAttribute("shoppingcart", buylist);
			    }
			    session.removeAttribute("paymentItems");
			    session.removeAttribute("payInfo");
				
				req.getRequestDispatcher("/RoomOrder?action=toThisUserOrder").forward(req, res);
				return;
			}catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. "
						+ e.getMessage());
			}catch (Exception e) {
				e.printStackTrace();
				req.getRequestDispatcher("/RoomOrder?action=toThisUserOrder").forward(req, res);
				return;
			}
		}
		
		//////到廠商所有房型頁面
		if("toVenPage".equals(action)) {
			try {
				Integer roomId = Integer.parseInt(req.getParameter("roomId"));
				RoomService roomService = new RoomService();
				Integer venId = roomService.getOneRoom(roomId).getVenId();
				res.getWriter().write(venId.toString());
				return;
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}

	private RoomItem getRoomItem(HttpServletRequest req) {
		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String venName = req.getParameter("venName");
		String roomName = req.getParameter("roomName");
		Integer roomId = Integer.parseInt(req.getParameter("roomId"));
		LocalDate checkinDate = LocalDate.parse(req.getParameter("checkinDate"), DATEFORMATTER);
		LocalDate checkoutDate = LocalDate.parse(req.getParameter("checkoutDate"), DATEFORMATTER);
		String people = req.getParameter("people");
		Integer price = Integer.parseInt(req.getParameter("price"));
		Integer amount = Integer.parseInt(req.getParameter("amount"));

		RoomItem roomItem = new RoomItem(venName, roomName, roomId, checkinDate, checkoutDate, people, price, amount);

		return roomItem;
	}
}
