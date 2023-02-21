package com.orderDetail.model;

import java.util.*;
import java.sql.*;

public class OrderDetailJDBCDAO implements OrderDetail_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
			"INSERT INTO order_detail (room_id,order_id,room_amount) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT room_id,order_id,room_amount FROM order_detail order by room_id, order_id";
	private static final String GET_ONE_STMT = 
			"SELECT room_id,order_id,room_amount FROM order_detail where room_id = ? AND order_id = ?";
	private static final String DELETE = 
			"DELETE FROM order_detail where room_id = ? AND order_id = ?";
	private static final String UPDATE = 
			"UPDATE order_detail set room_amount=? where room_id = ? AND order_id = ?";
	private static final String GET_BY_ORDERID = 
			"SELECT room_id,order_id,room_amount FROM order_detail where order_id = ?";
	private static final String GET_BY_ROOMID = 
			"SELECT room_id,order_id,room_amount FROM order_detail where room_id = ?";
	
	@Override
	public void insert(OrderDetailVO orderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, orderDetailVO.getRoomId());
			pstmt.setInt(2, orderDetailVO.getOrderId());
			pstmt.setInt(3, orderDetailVO.getRoomAmount());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void update(OrderDetailVO orderDetailVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, orderDetailVO.getRoomAmount());
			pstmt.setInt(2, orderDetailVO.getRoomId());
			pstmt.setInt(3, orderDetailVO.getOrderId());
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public void delete(Integer roomId, Integer orderId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomId);
			pstmt.setInt(2, orderId);
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	@Override
	public OrderDetailVO findByPrimaryKey(Integer roomId, Integer orderId) {

		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, roomId);
			pstmt.setInt(2, orderId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setRoomId(rs.getInt("room_id"));
				orderDetailVO.setOrderId(rs.getInt("order_id"));
				orderDetailVO.setRoomAmount(rs.getInt("room_amount"));
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	
		return orderDetailVO;
	}
	@Override
	public List<OrderDetailVO> getAll() {

		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setRoomId(rs.getInt("room_id"));
				orderDetailVO.setOrderId(rs.getInt("order_id"));
				orderDetailVO.setRoomAmount(rs.getInt("room_amount"));
				list.add(orderDetailVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<OrderDetailVO> findByOrderId(Integer orderId) {

		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ORDERID);
			pstmt.setInt(1, orderId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setRoomId(rs.getInt("room_id"));
				orderDetailVO.setOrderId(rs.getInt("order_id"));
				orderDetailVO.setRoomAmount(rs.getInt("room_amount"));
				list.add(orderDetailVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<OrderDetailVO> findByRoomId(Integer roomId) {
		
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ROOMID);
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setRoomId(rs.getInt("room_id"));
				orderDetailVO.setOrderId(rs.getInt("order_id"));
				orderDetailVO.setRoomAmount(rs.getInt("room_amount"));
				list.add(orderDetailVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	@Override
	public void insert2(OrderDetailVO orderDetailVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_STMT);
     		pstmt.setInt(1, orderDetailVO.getRoomId());
			pstmt.setInt(2, orderDetailVO.getOrderId());
			pstmt.setInt(3, orderDetailVO.getRoomAmount());
			
			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-detail");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	public static void main(String[] args) {
		OrderDetailJDBCDAO dao = new OrderDetailJDBCDAO();
		
		// 新增
//		OrderDetailVO orderDetailVO = new OrderDetailVO();
//		orderDetailVO.setRoomId(2);
//		orderDetailVO.setOrderId(2);
//		orderDetailVO.setRoomAmount(1);
//		dao.insert(orderDetailVO);
		
		
		// 修改
//		OrderDetailVO orderDetailVO1 = new OrderDetailVO();
//		orderDetailVO1.setRoomId(2);
//		orderDetailVO1.setOrderId(2);
//		orderDetailVO1.setRoomAmount(2);
//		dao.update(orderDetailVO1);
		
		// 刪除
//		dao.delete(2,2);
		
		//查詢
//		OrderDetailVO orderDetailVO2 = dao.findByPrimaryKey(1,2);
//		System.out.print(orderDetailVO2.getRoomId() + ",");
//		System.out.print(orderDetailVO2.getOrderId() + ",");
//		System.out.print(orderDetailVO2.getRoomAmount());
//		System.out.println("---------------------");
		
		//查詢
//		List<OrderDetailVO> list = dao.getAll();
//		for (OrderDetailVO aOrderDetail : list) {
//			System.out.print(aOrderDetail.getRoomId() + ",");
//			System.out.print(aOrderDetail.getOrderId() + ",");
//			System.out.print(aOrderDetail.getRoomAmount();
//			System.out.println();
//		}
		List<OrderDetailVO> list = dao.findByOrderId(1);
		for (OrderDetailVO aOrderDetail : list) {
			System.out.print(aOrderDetail.getRoomId() + ",");
			System.out.print(aOrderDetail.getOrderId() + ",");
			System.out.print(aOrderDetail.getRoomAmount());
			System.out.println();
		}

	}
	
}
