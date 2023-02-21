package com.orderDetail.model;

import java.util.List;


public class OrderDetailService {
	private OrderDetail_interface dao;

	public OrderDetailService() {
		dao = new OrderDetailJDBCDAO();
//		dao = new RoomDAO();
	}

	public OrderDetailVO addOrderDetail(Integer roomId, Integer orderId, Integer roomAmount) {

		OrderDetailVO orderDetailVO = new OrderDetailVO();

		orderDetailVO.setRoomId(roomId);
		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setRoomAmount(roomAmount);
		dao.insert(orderDetailVO);

		return orderDetailVO;
	}

	//�w�d�� Struts 2 �� Spring MVC ��
	public void addOrderDetail(OrderDetailVO orderDetailVO) {
		dao.insert(orderDetailVO);
	}
	
	public OrderDetailVO updateOrderDetail(Integer roomId, Integer orderId, Integer roomAmount) {

		OrderDetailVO orderDetailVO = new OrderDetailVO();

		orderDetailVO.setRoomId(roomId);
		orderDetailVO.setOrderId(orderId);
		orderDetailVO.setRoomAmount(roomAmount);
		dao.update(orderDetailVO);

		return dao.findByPrimaryKey(roomId, orderId);
	}
	
	//�w�d�� Struts 2 �Ϊ�
	public void updateOrderDetail(OrderDetailVO orderDetailVO) {
		dao.update(orderDetailVO);
	}

	public void deleteOrderDetail(Integer roomId, Integer orderId) {
		dao.delete(roomId, orderId);
	}

	public OrderDetailVO getOneOrderDetail(Integer roomId, Integer orderId) {
		return dao.findByPrimaryKey(roomId, orderId);
	}

	public List<OrderDetailVO> getAll() {
		return dao.getAll();
	}
	
	public List<OrderDetailVO> getByOrderID(Integer orderId) {
		return dao.findByOrderId(orderId);
	}
	public List<OrderDetailVO> getByRoomID(Integer roomId) {
		return dao.findByRoomId(roomId);
	}

}
