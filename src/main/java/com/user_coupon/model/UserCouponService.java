package com.user_coupon.model;
import java.sql.Connection;
import java.util.*;

public class UserCouponService {
	private UserCouponDAO_interface dao;

	public UserCouponService() {
		dao = new UserCouponJDBCDAO();
	}
	
	public UserCouponVO addUserCoupon(Integer couponNo, Integer userId, Integer couponQnt) {
		UserCouponVO userCouponVO = new UserCouponVO();
		userCouponVO.setCouponNo(couponNo);
		userCouponVO.setUserId(userId);
		userCouponVO.setCouponQnt(couponQnt);
		dao.insert(userCouponVO);
		return userCouponVO;
	}
	
	public UserCouponVO updateUserCoupon(Integer couponNo, Integer userId, Integer couponQnt) {
		UserCouponVO userCouponVO = new UserCouponVO();
		userCouponVO.setCouponNo(couponNo);
		userCouponVO.setUserId(userId);
		userCouponVO.setCouponQnt(couponQnt);
		dao.update(userCouponVO);
		return userCouponVO;
	}
	
	public UserCouponVO getOneUserCoupon(Integer couponNo, Integer userId) {
		return dao.findByPrimaryKey(couponNo, userId);
	}
	
	public List<UserCouponVO> getAll() {
		return dao.getAll();
	}
	
	public List<UserCouponVO> gettByUserId(Integer userId) {
		return dao.getByUserId(userId);
	}
	
	public void updateUserCoupon(UserCouponVO userCouponVO, Connection con) {
		dao.update2(userCouponVO, con);
		return;
	}
}
