package com.act.model;

import java.sql.Date;
import java.time.LocalDate;
import oracle.sql.DATE;

public class ActVO  implements java.io.Serializable{
	private Integer activityId;
	private Integer tripId;
	private Integer userId;
	private String activityTitle;
	private String activityContent;
	private Integer currentNumber;
	private Integer maxPeople;
	private Date registrationTime;
	private Date registrationEnd;
	private Date tripStart;
	private Date tripEnd;
	private byte[] activityTheCover;
	private Integer activityState;
	private byte[] Imgs; // 抓取前台揪團列表圖片
//	private String activityLocation;
	

	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
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
	public String getActivityTitle() {
		return activityTitle;
	}
	public void setActivityTitle(String activityTitle) {
		this.activityTitle = activityTitle;
	}
	public String getActivityContent() {
		return activityContent;
	}
	public void setActivityContent(String activityContent) {
		this.activityContent = activityContent;
	}
	public Integer getCurrentNumber() {
		return currentNumber;
	}
	public void setCurrentNumber(Integer currentNumber) {
		this.currentNumber = currentNumber;
	}
	public Integer getMaxPeople() {
		return maxPeople;
	}
	public void setMaxPeople(Integer maxPeople) {
		this.maxPeople = maxPeople;
	}
	public Date getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(Date registrationTime) {
		this.registrationTime = registrationTime;
	}
	public Date getRegistrationEnd() {
		return registrationEnd;
	}
	public void setRegistrationEnd(Date registrationEnd) {
		this.registrationEnd = registrationEnd;
	}
	public Date getTripStart() {
		return tripStart;
	}
	public void setTripStart(Date tripStart) {
		this.tripStart = tripStart;
	}
	public Date getTripEnd() {
		return tripEnd;
	}
	public void setTripEnd(Date tripEnd) {
		this.tripEnd = tripEnd;
	}
	public byte[] getActivityTheCover() {
		return activityTheCover;
	}
	public void setActivityTheCover(byte[] activityTheCover) {
		this.activityTheCover = activityTheCover;
	}
	public Integer getActivityState() {
		return activityState;
	}
	public void setActivityState(Integer activityState) {
		this.activityState = activityState;
	}

	public byte[] getImgs() {
		return getImgs();
	}
	public void setImg(byte[] getImg) {
		this.Imgs = getImg;
	}
	
//	public String getActivityLocation() {
//		return activityLocation;
//	}
//	public void setActivityLocation(String activityLocation) {
//		this.activityLocation = activityLocation;
//	}

	
}
