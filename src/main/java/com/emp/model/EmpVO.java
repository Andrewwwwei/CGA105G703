package com.emp.model;

import java.sql.Date;
import java.sql.Timestamp;

public class EmpVO implements java.io.Serializable {
	private Integer empNo;
	private String empPw;
	private String empName;
	private String empDep;
	private String jobTitle;
	private String empIdnum;
	private String empEmail;
	private String empTel;
	private Integer empStatus;
	private Date empHiredate;
	
	public Integer getEmpNo() {
		return empNo;
	}
	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}
	public String getEmpPw() {
		return empPw;
	}
	public void setEmpPw(String empPw) {
		this.empPw = empPw;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDep() {
		return empDep;
	}
	public void setEmpDep(String empDep) {
		this.empDep = empDep;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public String getEmpIdnum() {
		return empIdnum;
	}
	public void setEmpIdnum(String empIdnum) {
		this.empIdnum = empIdnum;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpTel() {
		return empTel;
	}
	public void setEmpTel(String empTel) {
		this.empTel = empTel;
	}
	public Integer getEmpStatus() {
		return empStatus;
	}
	public void setEmpStatus(Integer empStatus) {
		this.empStatus = empStatus;
	}
	public Date getEmpHiredate() {
		return empHiredate;
	}
	public void setEmpHiredate(Date empHiredate) {
		this.empHiredate = empHiredate;
	}	
}
