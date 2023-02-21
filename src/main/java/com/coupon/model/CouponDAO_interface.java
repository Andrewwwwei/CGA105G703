package com.coupon.model;
import java.util.*;
public interface CouponDAO_interface {
	  public void insert(CouponVO couponVO);
      public void update(CouponVO couponVO);
      public void delete(Integer COUPON_NO);
      public CouponVO findByPrimaryKey(Integer COUPON_NO);
      public List<CouponVO> getAll();

	
}
