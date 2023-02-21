package com.location.model;

import java.io.Serializable;

public class LocationVO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer locId;
	private Integer userId;
	private String locName;
	private String longitude;
	private String latitude;
	private String locAddress;
	private String locPhone;
	private Integer locStatus;
	
	
	public Integer getLocId() {
		return locId;
	}
	public void setLocId(Integer locId) {
		this.locId = locId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getLocName() {
		return locName;
	}
	public void setLocName(String locName) {
		this.locName = locName;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLocAddress() {
		return locAddress;
	}
	public void setLocAddress(String locAddress) {
		this.locAddress = locAddress;
	}
	public String getLocPhone() {
		return locPhone;
	}
	public void setLocPhone(String locPhone) {
		this.locPhone = locPhone;
	}
	public Integer getLocStatus() {
		return locStatus;
	}
	public void setLocStatus(Integer locStatus) {
		this.locStatus = locStatus;
	}
}
