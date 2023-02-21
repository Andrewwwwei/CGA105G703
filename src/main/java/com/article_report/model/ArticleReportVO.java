package com.article_report.model;
import java.security.Timestamp;
import java.sql.Date;
import java.time.LocalDateTime;



public class ArticleReportVO implements java.io.Serializable{  
	
	public LocalDateTime getRpDoneTime() {
		return rpDoneTime;
	}
	public void setRpDoneTime(LocalDateTime rpDoneTime) {
		this.rpDoneTime = rpDoneTime;
	}

	private Integer artRpId;
	private Integer userId;
	private Integer artId;
	private Integer rpReason;
	private Integer empno;
	private LocalDateTime rpTime;
	private LocalDateTime rpDoneTime;
	private Integer rpStatus;
	private String rpNote;
	private String rpContent;
	
	public Integer getArtRpId() {
		return artRpId;
	}
	public void setArtRpId(Integer artRpId) {
		this.artRpId = artRpId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getArtId() {
		return artId;
	}
	public void setArtId(Integer artId) {
		this.artId = artId;
	}
	public Integer getRpReason() {
		return rpReason;
	}
	public void setRpReason(Integer rpReason) {
		this.rpReason = rpReason;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public LocalDateTime getRpTime() {
		return rpTime;
	}
	public void setRpTime(LocalDateTime rpTime) {
		this.rpTime = rpTime;
	}
	public Integer getRpStatus() {
		return rpStatus;
	}
	public void setRpStatus(Integer rpStatus) {
		this.rpStatus = rpStatus;
	}
	public String getRpNote() {
		return rpNote;
	}
	public void setRpNote(String rpNote) {
		this.rpNote = rpNote;
	}
	public String getRpContent() {
		return rpContent;
	}
	public void setRpContent(String rpContent) {
		this.rpContent = rpContent;
	}
	
	// for join artTitle from article
			public com.article.model.ArticleVO getArticleVO() {
				com.article.model.ArticleService articleSvc = new com.article.model.ArticleService();
				com.article.model.ArticleVO articleVO = articleSvc.getOneArticle(artId);
				return articleVO;
			}
	
}
