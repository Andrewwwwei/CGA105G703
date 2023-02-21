package com.stock.model;

import java.time.*;

public class StockVO implements java.io.Serializable{
	private Integer roomId;
	private LocalDate stayDate;
	private Integer roomRest;
	
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public LocalDate getStayDate() {
		return stayDate;
	}
	public void setStayDate(LocalDate stayDate) {
		this.stayDate = stayDate;
	}
	public Integer getRoomRest() {
		return roomRest;
	}
	public void setRoomRest(Integer roomRest) {
		this.roomRest = roomRest;
	}
	
	
}
