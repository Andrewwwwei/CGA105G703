package com.ColVen.model;
import java.sql.Date;
import java.sql.Timestamp;
public class ColVenVO implements java.io.Serializable{
	private Integer venId  ;
	private Integer userId	;
	private Integer venIdW  ;
	private Integer userIdW	;
	private String venName	;
	private String venTel	;
	private String venLocation	;
	private String venWin	;
	private String venIntro	;
	private String venScore	;
	private String venScorePeople	;
	
	public Integer getVenId() {
		return venId;
	}
	public void setVenId(Integer venId) {
		this.venId = venId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getVenIdW() {
		return venIdW;
	}
	public void setVenIdW(Integer venIdW) {
		this.venIdW = venIdW;
	}
	public Integer getUserIdW() {
		return userIdW;
	}
	public void setUserIdW(Integer userIdW) {
		this.userIdW = userIdW;
	}
	public String getVenName() {
		return venName;
	}
	public void setVenName(String venName) {
		this.venName = venName;
	}
	public String getVenTel() {
		return venTel;
	}
	public void setVenTel(String venTel) {
		this.venTel = venTel;
	}
	public String getVenLocation() {
		return venLocation;
	}
	public void setVenLocation(String venLocation) {
		this.venLocation = venLocation;
	}
	public String getVenWin() {
		return venWin;
	}
	public void setVenWin(String venWin) {
		this.venWin = venWin;
	}
	public String getVenIntro() {
		return venIntro;
	}
	public void setVenIntro(String venIntro) {
		this.venIntro = venIntro;
	}
	public String getVenScore() {
		return venScore;
	}
	public void setVenScore(String venScore) {
		this.venScore = venScore;
	}
	public String getVenScorePeople() {
		return venScorePeople;
	}
	public void setVenScorePeople(String venScorePeople) {
		this.venScorePeople = venScorePeople;
	}
	
}
	

