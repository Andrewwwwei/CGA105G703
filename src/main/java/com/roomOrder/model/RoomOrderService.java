package com.roomOrder.model;

import java.sql.Connection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.orderDetail.model.OrderDetailVO;
import com.Users.model.UsersVO;
import com.vendor.model.VendorVO;


public class RoomOrderService {
	private RoomOrder_interface dao;

	public RoomOrderService() {
		dao = new RoomOrderJDBCDAO();
//		dao = new RoomDAO();
	}

	public RoomOrderVO addRoomOrder(Integer userId, Integer venId, Integer couponNo, String customerName,
			String customerPhone, String customerEmail, LocalDate checkinDate, LocalDate checkoutDate,
			LocalDateTime orderTime, Integer bonusPointsUse, Integer orderCharge, Integer orderChargeDiscount,
			Integer orderStatus) {

		RoomOrderVO roomOrderVO = new RoomOrderVO();

		roomOrderVO.setUserId(userId);
		roomOrderVO.setVenId(venId);
		roomOrderVO.setCouponNo(couponNo);
		roomOrderVO.setCustomerName(customerName);
		roomOrderVO.setCustomerPhone(customerPhone);
		roomOrderVO.setCustomerEmail(customerEmail);
		roomOrderVO.setCheckinDate(checkinDate);
		roomOrderVO.setCheckoutDate(checkoutDate);
		roomOrderVO.setOrderTime(orderTime);
		roomOrderVO.setBonusPointsUse(bonusPointsUse);
		roomOrderVO.setOrderCharge(orderCharge);
		roomOrderVO.setOrderChargeDiscount(orderChargeDiscount);
		roomOrderVO.setOrderStatus(orderStatus);
		dao.insert(roomOrderVO);

		return roomOrderVO;
	}

	//�w�d�� Struts 2 �� Spring MVC ��
	public void addRoomOrder(RoomOrderVO roomOrderVO) {
		dao.insert(roomOrderVO);
	}
	
	public RoomOrderVO updateRoomOrder(Integer orderId, Integer userId, Integer venId, Integer couponNo, String customerName,
			String customerPhone, String customerEmail, LocalDate checkinDate, LocalDate checkoutDate,
			LocalDateTime orderTime, Integer bonusPointsUse, Integer orderCharge, Integer orderChargeDiscount,
			Integer orderStatus, String orderCancel, Integer orderRefund, Integer score, String reviews,
			LocalDate reviewsTime) {

		RoomOrderVO roomOrderVO = new RoomOrderVO();

		roomOrderVO.setOrderId(orderId);
		roomOrderVO.setUserId(userId);
		roomOrderVO.setVenId(venId);
		roomOrderVO.setCouponNo(couponNo);
		roomOrderVO.setCustomerName(customerName);
		roomOrderVO.setCustomerPhone(customerPhone);
		roomOrderVO.setCustomerEmail(customerEmail);
		roomOrderVO.setCheckinDate(checkinDate);
		roomOrderVO.setCheckoutDate(checkoutDate);
		roomOrderVO.setOrderTime(orderTime);
		roomOrderVO.setBonusPointsUse(bonusPointsUse);
		roomOrderVO.setOrderCharge(orderCharge);
		roomOrderVO.setOrderChargeDiscount(orderChargeDiscount);
		roomOrderVO.setOrderStatus(orderStatus);
		roomOrderVO.setOrderCancel(orderCancel);
		roomOrderVO.setOrderRefund(orderRefund);
		roomOrderVO.setScore(score);
		roomOrderVO.setReviews(reviews);
		roomOrderVO.setReviewsTime(reviewsTime);
		dao.update(roomOrderVO);

		return dao.findByPrimaryKey(orderId);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateRoomOrder(RoomOrderVO roomOrderVO) {
		dao.update(roomOrderVO);
	}

	public void deleteRoomOrder(Integer orderId) {
		dao.delete(orderId);
	}

	public RoomOrderVO getOneRoomOrder(Integer orderId) {
		return dao.findByPrimaryKey(orderId);
	}

	public List<RoomOrderVO> getAll() {
		return dao.getAll();
	}
	
	public List<RoomOrderVO> getByVendorId(Integer venId) {
		return dao.findByVenId(venId);
	}
	
	public List<RoomOrderVO> getByUserIdOrderByCheckin(Integer userId) {
		return dao.findByUserIdOrderByCheckin(userId);
	}
	
	public void addRoomOrderWithDetailUpdateUser(RoomOrderVO roomOrderVO, List<OrderDetailVO> detailList, Connection con) {
		dao.insertWithDetailUpdateUser(roomOrderVO, detailList, con);
	}
	
	public void updateOrderReview(RoomOrderVO roomOrderVO, VendorVO vendorVO) {
		dao.updateWithVendor(roomOrderVO, vendorVO);
	}
}
