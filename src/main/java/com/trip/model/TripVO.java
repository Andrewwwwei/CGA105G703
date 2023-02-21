package com.trip.model;

import java.io.Serializable;
import java.sql.Date;

public class TripVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer tripId;
	private String tripName;
	private Date startDate;
	private Date endDate;
	private byte[] coverPic;
	private String note;
	
	public Integer getTripId() {
		return tripId;
	}
	public void setTripId(Integer tripId) {
		this.tripId = tripId;
	}
	public String getTripName() {
		return tripName;
	}
	public void setTripName(String tripName) {
		this.tripName = tripName;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public byte[] getCoverPic() {
		return coverPic;
	}
	public void setCoverPic(byte[] coverPic) {
		this.coverPic = coverPic;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
