package com.coupon.model;


import java.util.List;
import java.util.Set;


public class CouponService {

	private CouponDAO_interface dao;

	public CouponService() {
		dao = new CouponJDBCDAO();
	}
	public List<CouponVO> getAll() {
		return dao.getAll();
	}
	public CouponVO getOneCoupon(Integer couponNo) {
		return dao.findByPrimaryKey(couponNo);
	}
	public void deleteCoupon(Integer artId) {
		dao.delete(artId);
	}
	
	

}
