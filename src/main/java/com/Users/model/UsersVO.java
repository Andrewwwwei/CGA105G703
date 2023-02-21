package com.Users.model;


import java.sql.Date;
import java.sql.Timestamp;

public class UsersVO implements java.io.Serializable {
	private Integer userId;
	private String userAccount;
	private String userPassword;
	private String userName;
	private String userNickname;
	private String userIdNo;
	private Date userBrthday;
	private String userPhone;
	private String userAddress;
	private Byte userCertificationStatus;
	private Byte userStatus;
	private String userGender;
	private Timestamp userRegistrationDate;
	private Byte userForumLevel;
	private byte[] userPic;
	private Byte userShopLevel;
	private Integer bonusPoints;
	private Float alltogetherScore;
	private Integer alltogetherScorePeople;
	private Integer artCount;
	private Integer likeTotalCount;
	private Integer purchaseTotal;
	private Byte reportTotalCount;
	private String userPasswordW;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserIdNo() {
		return userIdNo;
	}

	public void setUserIdNo(String userIdNo) {
		this.userIdNo = userIdNo;
	}

	public Date getUserBrthday() {
		return userBrthday;
	}

	public void setUserBrthday(Date userBrthday) {
		this.userBrthday = userBrthday;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Byte getUserCertificationStatus() {
		return userCertificationStatus;
	}

	public void setUserCertificationStatus(Byte userCertificationStatus) {
		this.userCertificationStatus = userCertificationStatus;
	}

	public Byte getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Byte userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Timestamp getUserRegistrationDate() {
		return userRegistrationDate;
	}

	public void setUserRegistrationDate(Timestamp userRegistrationDate) {
		this.userRegistrationDate = userRegistrationDate;
	}

	public Byte getUserForumLevel() {
		return userForumLevel;
	}

	public void setUserForumLevel(Byte userForumLevel) {
		this.userForumLevel = userForumLevel;
	}

	public byte[] getUserPic() {
		return userPic;
	}

	public void setUserPic(byte[] userPic) {
		this.userPic = userPic;
	}

	public Byte getUserShopLevel() {
		return userShopLevel;
	}

	public void setUserShopLevel(Byte userShopLevel) {
		this.userShopLevel = userShopLevel;
	}

	public Integer getBonusPoints() {
		return bonusPoints;
	}

	public void setBonusPoints(Integer bonusPoints) {
		this.bonusPoints = bonusPoints;
	}

	public Float getAlltogetherScore() {
		return alltogetherScore;
	}

	public void setAlltogetherScore(Float alltogetherScore) {
		this.alltogetherScore = alltogetherScore;
	}

	public Integer getAlltogetherScorePeople() {
		return alltogetherScorePeople;
	}

	public void setAlltogetherScorePeople(Integer alltogetherScorePeople) {
		this.alltogetherScorePeople = alltogetherScorePeople;
	}

	public Integer getArtCount() {
		return artCount;
	}

	public void setArtCount(Integer artCount) {
		this.artCount = artCount;
	}

	public Integer getLikeTotalCount() {
		return likeTotalCount;
	}

	public void setLikeTotalCount(Integer likeTotalCount) {
		this.likeTotalCount = likeTotalCount;
	}

	public Integer getPurchaseTotal() {
		return purchaseTotal;
	}

	public void setPurchaseTotal(Integer purchaseTotal) {
		this.purchaseTotal = purchaseTotal;
	}

	public Byte getReportTotalCount() {
		return reportTotalCount;
	}

	public void setReportTotalCount(Byte reportTotalCount) {
		this.reportTotalCount = reportTotalCount;
	}

	public String getUserPasswordW() {
		return userPasswordW;
	}

	public void setUserPasswordW(String userPasswordW) {
		this.userPasswordW = userPasswordW;
	}
	
}
