package com.ColIti.model;
import java.sql.Date;
import java.sql.Timestamp;
public class ColItiVO implements java.io.Serializable{
	private Integer tripShareId  ;
	private Integer userId	;
	private Integer tripShareIdW  ;
	private Integer userIdW	;
	private Integer ususerId;
	private String tripName	;
	private Date startDate	;
	private Date endDate	;
	private Integer tripShareLlkesCount	;
	private Integer tripShareShowCount	;
	private String userAccount	;
	
	public Integer getUsuserId() {
		return ususerId;
	}
	public void setUsuserId(Integer ususerId) {
		this.ususerId = ususerId;
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
	public Integer getTripShareLlkesCount() {
		return tripShareLlkesCount;
	}
	public void setTripShareLlkesCount(Integer tripShareLlkesCount) {
		this.tripShareLlkesCount = tripShareLlkesCount;
	}
	public Integer getTripShareShowCount() {
		return tripShareShowCount;
	}
	public void setTripShareShowCount(Integer tripShareShowCount) {
		this.tripShareShowCount = tripShareShowCount;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public Integer getTripShareId() {
		return tripShareId;
	}
	public void setTripShareId(Integer tripShareId) {
		this.tripShareId = tripShareId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTripShareIdW() {
		return tripShareIdW;
	}
	public void setTripShareIdW(Integer tripShareIdW) {
		this.tripShareIdW = tripShareIdW;
	}
	public Integer getUserIdW() {
		return userIdW;
	}
	public void setUserIdW(Integer userIdW) {
		this.userIdW = userIdW;
	}

	
	
	
}
	

