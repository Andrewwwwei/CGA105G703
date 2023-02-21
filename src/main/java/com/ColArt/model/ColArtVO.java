package com.ColArt.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ColArtVO implements java.io.Serializable {
	private Integer artId;
	private Integer userId;
	private Integer artIdW;
	private Integer userIdW;
	private Integer arUserId;
	private String artTitle;
	private String artContent;
	private Integer artLikesCount;
	private Integer artShowCount;
	private Timestamp artTime;
	private String ususerAccount;
	
	public String getUsuserAccount() {
		return ususerAccount;
	}

	public void setUsuserAccount(String ususerAccount) {
		this.ususerAccount = ususerAccount;
	}

	public Integer getArUserId() {
		return arUserId;
	}

	public void setArUserId(Integer arUserId) {
		this.arUserId = arUserId;
	}

	public String getArtTitle() {
		return artTitle;
	}

	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}

	public String getArtContent() {
		return artContent;
	}

	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}

	public Integer getArtLikesCount() {
		return artLikesCount;
	}

	public void setArtLikesCount(Integer artLikesCount) {
		this.artLikesCount = artLikesCount;
	}

	public Integer getArtShowCount() {
		return artShowCount;
	}

	public void setArtShowCount(Integer artShowCount) {
		this.artShowCount = artShowCount;
	}

	public Timestamp getArtTime() {
		return artTime;
	}

	public void setArtTime(Timestamp artTime) {
		this.artTime = artTime;
	}

	public Integer getArtIdW() {
		return artIdW;
	}

	public void setArtIdW(Integer artIdW) {
		this.artIdW = artIdW;
	}

	public Integer getUserIdW() {
		return userIdW;
	}

	public void setUserIdW(Integer userIdW) {
		this.userIdW = userIdW;
	}

	public Integer getArtId() {
		return artId;
	}

	public void setArtId(Integer artId) {
		this.artId = artId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
