package com.roomOrder.model;

import java.util.*;

import com.orderDetail.model.OrderDetailVO;
import com.Users.model.UsersVO;
import com.vendor.model.VendorVO;


public interface RoomOrder_interface {
	public void insert(RoomOrderVO roomOrderVO);
    public void update(RoomOrderVO roomOrderVO);
    public void delete(Integer orderId);
    public RoomOrderVO findByPrimaryKey(Integer orderId);
    public List<RoomOrderVO> getAll();
    public List<RoomOrderVO> findByVenId(Integer venId); // 廠商訂單
    public List<RoomOrderVO> findByUserIdOrderByCheckin(Integer userId); // 會員訂單 依入住日新至舊排序
  //同時新增訂單與明細
    public void insertWithDetailUpdateUser(RoomOrderVO roomOrderVO, List<OrderDetailVO> detailList, java.sql.Connection con); 
  //新增評論與更新廠商
    public void updateWithVendor(RoomOrderVO roomOrderVO, VendorVO vendorVO);
}
