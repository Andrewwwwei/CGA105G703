package com.actReport.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import oracle.sql.DATE;

public class ActReportVO  implements java.io.Serializable{
	private Integer activityReportId;
	private Integer activityId;
	private Integer reportUserId;
	private Integer empNo;
	private String reportContent;
	private Integer reportStatus;
	private LocalDateTime reportTime;
	private String reportTimeStr;
	private Integer reportMatter;
	private LocalDateTime reportFinishTime;
	private String reportFinishTimeStr;
	
	public String getReportFinishTimeStr() {
		return reportFinishTimeStr;
	}
	public void setReportFinishTimeStr(String reportFinishTimeStr) {
		this.reportFinishTimeStr = reportFinishTimeStr;
	}
	public Integer getActivityReportId() {
		return activityReportId;
	}
	public void setActivityReportId(Integer activityReportId) {
		this.activityReportId = activityReportId;
	}
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getReportUserId() {
		return reportUserId;
	}
	public void setReportUserId(Integer reportUserId) {
		this.reportUserId = reportUserId;
	}
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public String getReportContent() {
		return reportContent;
	}
	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}
	public Integer getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}
	public LocalDateTime getReportTime() {
		return reportTime;
	}
	public void setReportTime(LocalDateTime reportTime) {
		this.reportTime = reportTime;
	}
	public Integer getReportMatter() {
		return reportMatter;
	}
	public void setReportMatter(Integer reportMatter) {
		this.reportMatter = reportMatter;
	}
	public LocalDateTime getReportFinishTime() {
		return reportFinishTime;
	}
	public void setReportFinishTime(LocalDateTime reportFinishTime) {
		this.reportFinishTime = reportFinishTime;
	}
	public String getReportTimeStr() {
		return reportTimeStr;
	}
	public void setReportTimeStr(String reportTimeStr) {
		this.reportTimeStr = reportTimeStr;
	}

	
	
}
