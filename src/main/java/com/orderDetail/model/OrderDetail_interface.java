package com.orderDetail.model;

import java.util.*;


public interface OrderDetail_interface {
	public void insert(OrderDetailVO orderDetailVO);
    public void update(OrderDetailVO orderDetailVO);
    public void delete(Integer roomId, Integer orderId);
    public OrderDetailVO findByPrimaryKey(Integer roomId, Integer orderId);
    public List<OrderDetailVO> getAll();
    public List<OrderDetailVO> findByOrderId(Integer orderId);
    public List<OrderDetailVO> findByRoomId(Integer roomId);
  //同時新增訂單與明細，並更新會員
    public void insert2 (OrderDetailVO orderDetailVO, java.sql.Connection con);
}
