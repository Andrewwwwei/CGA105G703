package com.actMember.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import oracle.sql.DATE;

public class ActMemberVO  implements java.io.Serializable{
	private Integer userId;
	private Integer activityId;
	private String activityNotice;
	private String evaluationContent;
	private Integer evaluationScore;
	private LocalDateTime evaluationTime;
	private Integer memberStatus;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public String getActivityNotice() {
		return activityNotice;
	}
	public void setActivityNotice(String activityNotice) {
		this.activityNotice = activityNotice;
	}
	public String getEvaluationContent() {
		return evaluationContent;
	}
	public void setEvaluationContent(String evaluationContent) {
		this.evaluationContent = evaluationContent;
	}
	public Integer getEvaluationScore() {
		return evaluationScore;
	}
	public void setEvaluationScore(Integer evaluationScore) {
		this.evaluationScore = evaluationScore;
	}
	public LocalDateTime getEvaluationTime() {
		return evaluationTime;
	}
	public void setEvaluationTime(LocalDateTime evaluationTime) {
		this.evaluationTime = evaluationTime;
	}
	public Integer getMemberStatus() {
		return memberStatus;
	}
	public void setMemberStatus(Integer memberStatus) {
		this.memberStatus = memberStatus;
	}
	
	

	
//    public com.act.model.ActVO getActVO() {
//    	com.act.model.ActService actSvc = new com.act.model.ActService();
//	    com.act.model.ActVO actVO = actSvc.getOneAct(activityId);
//	    return actVO;
//    } 
    // actVO去join member 

}
