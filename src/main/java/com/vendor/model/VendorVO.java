package com.vendor.model;

import java.sql.Date;

public class VendorVO implements java.io.Serializable{
	private Integer venId;
	private String venPw;
	private String venName;
	private String venTaxnum;
	private String venEmail;
	private String venTel;
	private String venLocation;
	private String venWin;
	private String venWintel;
	private String venIntro;
	private String venCanpolicy;
	private String venNotice;
	private String venBank;
	private Integer venStatus;
	private Float venScore;
	private Integer venScorePeople;
	private Date venJoinDate;
	public Integer getVenId() {
		return venId;
	}
	public String getVenPw() {
		return venPw;
	}
	public String getVenName() {
		return venName;
	}
	public String getVenTaxnum() {
		return venTaxnum;
	}
	public String getVenEmail() {
		return venEmail;
	}
	public String getVenTel() {
		return venTel;
	}
	public String getVenLocation() {
		return venLocation;
	}
	public String getVenWin() {
		return venWin;
	}
	public String getVenWintel() {
		return venWintel;
	}
	public String getVenIntro() {
		return venIntro;
	}
	public String getVenCanpolicy() {
		return venCanpolicy;
	}
	public String getVenNotice() {
		return venNotice;
	}
	public String getVenBank() {
		return venBank;
	}
	public Integer getVenStatus() {
		return venStatus;
	}
	public Float getVenScore() {
		return venScore;
	}
	public Integer getVenScorePeople() {
		return venScorePeople;
	}
	public Date getVenJoinDate() {
		return venJoinDate;
	}
	public void setVenId(Integer venId) {
		this.venId = venId;
	}
	public void setVenPw(String venPw) {
		this.venPw = venPw;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public void setVenTaxnum(String venTaxnum) {
		this.venTaxnum = venTaxnum;
	}
	public void setVenEmail(String venEmail) {
		this.venEmail = venEmail;
	}
	public void setVenTel(String venTel) {
		this.venTel = venTel;
	}
	public void setVenLocation(String venLocation) {
		this.venLocation = venLocation;
	}
	public void setVenWin(String venWin) {
		this.venWin = venWin;
	}
	public void setVenWintel(String venWintel) {
		this.venWintel = venWintel;
	}
	public void setVenIntro(String venIntro) {
		this.venIntro = venIntro;
	}
	public void setVenCanpolicy(String venCanpolicy) {
		this.venCanpolicy = venCanpolicy;
	}
	public void setVenNotice(String venNotice) {
		this.venNotice = venNotice;
	}
	public void setVenBank(String venBank) {
		this.venBank = venBank;
	}
	public void setVenStatus(Integer venStatus) {
		this.venStatus = venStatus;
	}
	public void setVenScore(Float venScore) {
		this.venScore = venScore;
	}
	public void setVenScorePeople(Integer venScorePeople) {
		this.venScorePeople = venScorePeople;
	}
	public void setVenJoinDate(Date venJoindate) {
		this.venJoinDate = venJoindate;
	}
	
}
