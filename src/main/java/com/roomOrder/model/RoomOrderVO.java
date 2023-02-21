package com.roomOrder.model;

import java.time.*;

public class RoomOrderVO  implements java.io.Serializable{
	private Integer orderId;
	private Integer userId;
	private Integer venId;
	private Integer couponNo;
	private String customerName;
	private String customerPhone;
	private String customerEmail;
	private LocalDate checkinDate;
	private LocalDate checkoutDate;
	private LocalDateTime orderTime;
	private Integer bonusPointsUse;
	private Integer orderCharge;
	private Integer orderChargeDiscount;
	private Integer orderStatus;
	private String orderCancel;
	private Integer orderRefund;
	private Integer score;
	private String reviews;
	private LocalDate reviewsTime;
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVenId() {
		return venId;
	}
	public void setVenId(Integer venId) {
		this.venId = venId;
	}
	public Integer getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(Integer couponNo) {
		this.couponNo = couponNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public LocalDate getCheckinDate() {
		return checkinDate;
	}
	public void setCheckinDate(LocalDate checkinDate) {
		this.checkinDate = checkinDate;
	}
	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}
	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}
	public LocalDateTime getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}
	public Integer getBonusPointsUse() {
		return bonusPointsUse;
	}
	public void setBonusPointsUse(Integer bonusPointsUse) {
		this.bonusPointsUse = bonusPointsUse;
	}
	public Integer getOrderCharge() {
		return orderCharge;
	}
	public void setOrderCharge(Integer orderCharge) {
		this.orderCharge = orderCharge;
	}
	public Integer getOrderChargeDiscount() {
		return orderChargeDiscount;
	}
	public void setOrderChargeDiscount(Integer orderChargeDiscount) {
		this.orderChargeDiscount = orderChargeDiscount;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderCancel() {
		return orderCancel;
	}
	public void setOrderCancel(String orderCancel) {
		this.orderCancel = orderCancel;
	}
	public Integer getOrderRefund() {
		return orderRefund;
	}
	public void setOrderRefund(Integer orderRefund) {
		this.orderRefund = orderRefund;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getReviews() {
		return reviews;
	}
	public void setReviews(String reviews) {
		this.reviews = reviews;
	}
	public LocalDate getReviewsTime() {
		return reviewsTime;
	}
	public void setReviewsTime(LocalDate reviewsTime) {
		this.reviewsTime = reviewsTime;
	}
	
}
