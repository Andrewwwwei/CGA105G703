package com.article_reply.model;

import java.time.LocalDateTime;

public class ArticleReplyVO implements java.io.Serializable{ 
	
	private Integer artReplyId;
	private Integer artId;
	private Integer userId;
	private String replyContent;
	private LocalDateTime replyTime;
	private Integer replyStatus;
	
	
	public Integer getArtReplyId() {
		return artReplyId;
	}
	public void setArtReplyId(Integer artReplyId) {
		this.artReplyId = artReplyId;
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
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public LocalDateTime getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(LocalDateTime replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getReplyStatus() {
		return replyStatus;
	}
	public void setReplyStatus(Integer replyStatus) {
		this.replyStatus = replyStatus;
	}
	// for join artTitle from artId
		public com.article.model.ArticleVO getArticleVO() {
			com.article.model.ArticleService articleSvc = new com.article.model.ArticleService();
			com.article.model.ArticleVO articleVO = articleSvc.getOneArticle(artId);
			return articleVO;
		}

		// for join userNickname from userId
		public com.Users.model.UsersVO getUsersVO() {
			com.Users.model.UsersJDBCDAO userDAO = new com.Users.model.UsersJDBCDAO();
			com.Users.model.UsersVO usersVO = userDAO.findByPrimaryKey(userId);
			return usersVO;
		}
	


}
