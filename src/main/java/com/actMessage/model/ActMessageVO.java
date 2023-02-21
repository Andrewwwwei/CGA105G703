package com.actMessage.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import oracle.sql.DATE;

public class ActMessageVO  implements java.io.Serializable{
	private Integer messageId;
	private Integer activityId;
	private Integer userId;
	private String messageContent;
	private LocalDateTime messageContentTime;
	private String messageContentTimeStr;
	
	public Integer getMessageId() {
		return messageId;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public LocalDateTime getMessageContentTime() {
		return messageContentTime;
	}
	public void setMessageContentTime(LocalDateTime messageContentTime) {
		this.messageContentTime = messageContentTime;
	}
	public String getMessageContentTimeStr() {
		return messageContentTimeStr;
	}
	public void setMessageContentTimeStr(String messageContentTimeStr) {
		this.messageContentTimeStr = messageContentTimeStr;
	}
	
	
	
}
