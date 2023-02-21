package com.article.model;

import java.time.LocalDateTime;

public class ArticleVO implements java.io.Serializable {

	public Integer getArtRpId() {
		return artRpId;
	}

	public void setArtRpId(Integer artRpId) {
		this.artRpId = artRpId;
	}

	private Integer artId;
	private Integer userId;
	private Integer artTypeId;
	private String artTitle;
	private String artContent;
	private Integer artLikesCount;
	private Integer artShowCount;
	private LocalDateTime artTime;
	private LocalDateTime artEditTime;
	private Integer artStatus;
	private byte[] artPic;
	private String userNickname;
	private Integer artRpId;

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

	public Integer getArtTypeId() {
		return artTypeId;
	}

	public void setArtTypeId(Integer artTypeId) {
		this.artTypeId = artTypeId;
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

	public LocalDateTime getArtTime() {
		return artTime;
	}

	public void setArtTime(LocalDateTime artTime) {
		this.artTime = artTime;
	}

	public LocalDateTime getArtEditTime() {
		return artEditTime;
	}

	public void setArtEditTime(LocalDateTime artEditTime) {
		this.artEditTime = artEditTime;
	}

	public Integer getArtStatus() {
		return artStatus;
	}

	public void setArtStatus(Integer artStatus) {
		this.artStatus = artStatus;
	}

	public byte[] getArtPic() {
		return artPic;
	}

	public void setArtPic(byte[] artPic) {
		this.artPic = artPic;
	}

	// for join ( userNickname、userForumLevel、userPic、 artCount、 likeTotalCount 、reportTotalCount ) from userId
	public com.Users.model.UsersVO getUsersVO() {
		com.Users.model.UsersJDBCDAO userDAO = new com.Users.model.UsersJDBCDAO();
		com.Users.model.UsersVO usersVO = userDAO.findByPrimaryKey(userId);
		return usersVO;
	}
	
	public com.article_report.model.ArticleReportVO getArticleReportVO() {
		com.article_report.model.ArticleReportJDBCDAO articleReportJDBCDAO = new com.article_report.model.ArticleReportJDBCDAO();
		com.article_report.model.ArticleReportVO articleReportVO = articleReportJDBCDAO.findByPrimaryKey(artId);
		return articleReportVO;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	
	// for join artpic from 多張照片用 
//	    public com.article_pic.model.ArticlePicVO getArtPicVO() { // 有個方法叫getArtPicVO
//		    com.article_pic.model.ArticlePicService artpicSvc = new com.article_pic.model.ArticlePicService(); //找到Service
//		    com.article_pic.model.ArticlePicVO artPicVO = artpicSvc.getArtPic(artPic);
//		    return artPicVO;
	// }
}
