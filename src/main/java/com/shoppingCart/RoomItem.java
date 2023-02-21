package com.shoppingCart;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class RoomItem implements Serializable{
	private static final long serialVersionUID = 1L;
	private String venName;
	private String roomName;
	private Integer roomId;
	private LocalDate checkinDate;
	private LocalDate checkoutDate;
	private String people;
	private Integer price;
	private Integer amount;
	
	
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
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
	public String getPeople() {
		return people;
	}
	public void setPeople(String people) {
		this.people = people;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public RoomItem() {
		super();
	}
	
	public RoomItem(String venName, String roomName, Integer roomId, LocalDate checkinDate, LocalDate checkoutDate,
			String people, Integer price, Integer amount) {
		super();
		this.venName = venName;
		this.roomName = roomName;
		this.roomId = roomId;
		this.checkinDate = checkinDate;
		this.checkoutDate = checkoutDate;
		this.people = people;
		this.price = price;
		this.amount = amount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(checkinDate, checkoutDate, roomName, venName);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoomItem other = (RoomItem) obj;
		return Objects.equals(checkinDate, other.checkinDate)
				&& Objects.equals(checkoutDate, other.checkoutDate) && Objects.equals(roomName, other.roomName)
				&& Objects.equals(venName, other.venName);
	}
}
