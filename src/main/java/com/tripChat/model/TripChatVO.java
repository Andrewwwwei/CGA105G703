package com.tripChat.model;

public class TripChatVO {
	private String userId;
	private String tripId;
	private String message;
	private String[] sendTime;
	
	public TripChatVO() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTripId() {
		return tripId;
	}

	public void setTripId(String tripId) {
		this.tripId = tripId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getSendTime() {
		return sendTime;
	}

	public void setSendTime(String[] sendTime) {
		this.sendTime = sendTime;
	}

	
	
}
