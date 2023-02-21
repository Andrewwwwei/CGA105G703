package com.user_coupon.model;
import java.util.*;
public interface UserCouponDAO_interface {
	  public void insert(UserCouponVO userCoupon);
      public void update(UserCouponVO userCoupon);
      public void delete(Integer COUPON_NO,Integer USER_ID);
      public UserCouponVO findByPrimaryKey(Integer COUPON_NO,Integer USER_ID);
      public List<UserCouponVO> getAll();
      public List<UserCouponVO> getByUserId(Integer userId);
      public void update2(UserCouponVO userCoupon, java.sql.Connection con);
	
}
