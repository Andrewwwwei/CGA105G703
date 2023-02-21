package com.coupon.model;
import java.sql.Date;
import java.sql.Timestamp;
public class CouponVO implements java.io.Serializable{
	private Integer couponNo  ;
	private String couponTitle	;
	private Integer couponType	;
	private String couponContent	;
	private Timestamp couponStart;
	private Timestamp couponEnd;
	private Integer couponIdentity;
	private Float couponDiscount;
	private Integer couponCount;
	private Integer couponSelector;
	private Integer couponPerAmout;
	
	public Integer getCouponNo() {
		return couponNo;
	}
	public void setCouponNo(Integer couponNo) {
		this.couponNo = couponNo;
	}
	public String getCouponTitle() {
		return couponTitle;
	}
	public void setCouponTitle(String couponTitle) {
		this.couponTitle = couponTitle;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public String getCouponContent() {
		return couponContent;
	}
	public void setCouponContent(String couponContent) {
		this.couponContent = couponContent;
	}
	public Timestamp getCouponStart() {
		return couponStart;
	}
	public void setCouponStart(Timestamp couponStart) {
		this.couponStart = couponStart;
	}
	public Timestamp getCouponEnd() {
		return couponEnd;
	}
	public void setCouponEnd(Timestamp couponEnd) {
		this.couponEnd = couponEnd;
	}
	public Integer getCouponIdentity() {
		return couponIdentity;
	}
	public void setCouponIdentity(Integer couponIdentity) {
		this.couponIdentity = couponIdentity;
	}
	public Float getCouponDiscount() {
		return couponDiscount;
	}
	public void setCouponDiscount(Float couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	public Integer getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}
	public Integer getCouponSelector() {
		return couponSelector;
	}
	public void setCouponSelector(Integer couponSelector) {
		this.couponSelector = couponSelector;
	}
	public Integer getCouponPerAmout() {
		return couponPerAmout;
	}
	public void setCouponPerAmout(Integer couponPerAmout) {
		this.couponPerAmout = couponPerAmout;
	}
}
	

