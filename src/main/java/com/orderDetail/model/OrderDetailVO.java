package com.orderDetail.model;

public class OrderDetailVO implements java.io.Serializable{
	private Integer roomId;
	private Integer orderId;
	private Integer roomAmount;
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public Integer getRoomAmount() {
		return roomAmount;
	}
	public void setRoomAmount(Integer roomAmount) {
		this.roomAmount = roomAmount;
	}
	
	
}
