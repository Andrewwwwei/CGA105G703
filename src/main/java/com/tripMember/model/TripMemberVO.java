package com.tripMember.model;

import java.io.Serializable;

public class TripMemberVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer tripMbrId;
	private Integer tripId;
	private Integer userId;
	private Boolean isMbr;
	public Integer getTripMbrId() {
		return tripMbrId;
	}
	public void setTripMbrId(Integer tripMbrId) {
		this.tripMbrId = tripMbrId;
	}
	public Integer getTripId() {
		return tripId;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Boolean getIsMbr() {
		return isMbr;
	}
	public void setIsMbr(Boolean isMbr) {
		this.isMbr = isMbr;
	}
	
}