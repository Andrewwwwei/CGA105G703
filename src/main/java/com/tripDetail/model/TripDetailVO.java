package com.tripDetail.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class TripDetailVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer tripDatailId;
	private Integer tripId;
	private Integer locId;
	private Timestamp arrivalTime;
	private Timestamp leaveTime;
	
	public Integer getTripDatailId() {
		return tripDatailId;
	}
	public void setTripDatailId(Integer tripDatailId) {
		this.tripDatailId = tripDatailId;
	}
	public Integer getTripId() {
		return tripId;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	public Timestamp getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(Timestamp arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public Timestamp getLeaveTime() {
		return leaveTime;
	}
	public void setLeaveTime(Timestamp leaveTime) {
		this.leaveTime = leaveTime;
	}
	
}