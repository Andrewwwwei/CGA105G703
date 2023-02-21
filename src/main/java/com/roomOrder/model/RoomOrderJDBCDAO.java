package com.roomOrder.model;

import java.sql.*;
import java.util.*;

import com.orderDetail.model.OrderDetailJDBCDAO;
import com.orderDetail.model.OrderDetailVO;
import com.Users.model.UsersJDBCDAO;
import com.Users.model.UsersVO;
import com.vendor.model.VendorJDBCDAO;
import com.vendor.model.VendorVO;

import java.time.*;

public class RoomOrderJDBCDAO implements RoomOrder_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";
	
	private static final String INSERT_STMT = 
			"INSERT INTO room_order (user_id,ven_id,coupon_no,customer_name,customer_phone,customer_email,checkin_date,checkout_date,order_time,"
					+ "bonus_points_use,order_charge,order_charge_discount,order_status)" 
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT order_id,user_id,ven_id,coupon_no,customer_name,customer_phone,customer_email,checkin_date,checkout_date,order_time,"
			+ "bonus_points_use,order_charge,order_charge_discount,order_status,order_cancel,order_refund,score,reviews,reviews_time FROM room_order order by order_id";
	private static final String GET_ONE_STMT = 
			"SELECT order_id,user_id,ven_id,coupon_no,customer_name,customer_phone,customer_email,checkin_date,checkout_date,order_time,"
			+ "bonus_points_use,order_charge,order_charge_discount,order_status,order_cancel,order_refund,score,reviews,reviews_time FROM room_order where order_id = ?";
	private static final String DELETE = 
			"DELETE FROM room_order where order_id = ?";
	private static final String UPDATE = 
			"UPDATE room_order set user_id=?, ven_id=?, coupon_no=?, customer_name=?, customer_phone=?, customer_email=?, checkin_date=?, checkout_date=?, order_time=?, "
			+ "bonus_points_use=?, order_charge=?, order_charge_discount=?, order_status=?, order_cancel=?, order_refund=?, score=?, reviews=?, reviews_time=? where order_id = ?";
	private static final String GET_BY_VENID = "SELECT * FROM room_order where ven_id=? order by order_id DESC";
	private static final String GET_BY_USERID_CHEKIN = "SELECT * FROM room_order where user_id=? order by checkin_date DESC";
	
	
	@Override
	public void insert(RoomOrderVO roomOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, roomOrderVO.getUserId());
			pstmt.setInt(2, roomOrderVO.getVenId());
			pstmt.setObject(3, roomOrderVO.getCouponNo());
			pstmt.setString(4, roomOrderVO.getCustomerName());
			pstmt.setString(5, roomOrderVO.getCustomerPhone());
			pstmt.setString(6, roomOrderVO.getCustomerEmail());
			pstmt.setObject(7, roomOrderVO.getCheckinDate());
			pstmt.setObject(8, roomOrderVO.getCheckoutDate());
			pstmt.setObject(9, roomOrderVO.getOrderTime());
			pstmt.setInt(10, roomOrderVO.getBonusPointsUse());
			pstmt.setInt(11, roomOrderVO.getOrderCharge());
			pstmt.setInt(12, roomOrderVO.getOrderChargeDiscount());
			pstmt.setInt(13, roomOrderVO.getOrderStatus());
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
	public void update(RoomOrderVO roomOrderVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomOrderVO.getUserId());
			pstmt.setInt(2, roomOrderVO.getVenId());
			pstmt.setObject(3, roomOrderVO.getCouponNo());
			pstmt.setString(4, roomOrderVO.getCustomerName());
			pstmt.setString(5, roomOrderVO.getCustomerPhone());
			pstmt.setString(6, roomOrderVO.getCustomerEmail());
			pstmt.setObject(7, roomOrderVO.getCheckinDate());
			pstmt.setObject(8, roomOrderVO.getCheckoutDate());
			pstmt.setObject(9, roomOrderVO.getOrderTime());
			pstmt.setInt(10, roomOrderVO.getBonusPointsUse());
			pstmt.setInt(11, roomOrderVO.getOrderCharge());
			pstmt.setInt(12, roomOrderVO.getOrderChargeDiscount());
			pstmt.setInt(13, roomOrderVO.getOrderStatus());
			pstmt.setString(14, roomOrderVO.getOrderCancel());
			pstmt.setObject(15, roomOrderVO.getOrderRefund());
			pstmt.setObject(16, roomOrderVO.getScore());
			pstmt.setString(17, roomOrderVO.getReviews());
			pstmt.setObject(18, roomOrderVO.getReviewsTime());
			pstmt.setInt(19, roomOrderVO.getOrderId());
			
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
	public void delete(Integer orderId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, orderId);
			
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
	public RoomOrderVO findByPrimaryKey(Integer orderId) {

		RoomOrderVO roomOrderVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, orderId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setOrderId(rs.getInt("order_id"));
				roomOrderVO.setUserId(rs.getInt("user_id"));
				roomOrderVO.setVenId(rs.getInt("ven_id"));
				roomOrderVO.setCouponNo(rs.getInt("coupon_no"));
				roomOrderVO.setCustomerName(rs.getString("customer_name"));
				roomOrderVO.setCustomerPhone(rs.getString("customer_phone"));
				roomOrderVO.setCustomerEmail(rs.getString("customer_email"));
				roomOrderVO.setCheckinDate(rs.getObject("checkin_date", LocalDate.class));
				roomOrderVO.setCheckoutDate(rs.getObject("checkout_date", LocalDate.class));
				roomOrderVO.setOrderTime(rs.getObject("order_time", LocalDateTime.class));
				roomOrderVO.setBonusPointsUse(rs.getInt("bonus_points_use"));
				roomOrderVO.setOrderCharge(rs.getInt("order_charge"));
				roomOrderVO.setOrderChargeDiscount(rs.getInt("order_charge_discount"));
				roomOrderVO.setOrderStatus(rs.getInt("order_status"));
				roomOrderVO.setOrderCancel(rs.getString("order_cancel"));
				roomOrderVO.setOrderRefund(rs.getInt("order_refund"));
				roomOrderVO.setScore(rs.getInt("score"));
				roomOrderVO.setReviews(rs.getString("reviews"));
				roomOrderVO.setReviewsTime(rs.getObject("reviews_time", LocalDate.class));
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
	
		return roomOrderVO;
	}
	@Override
	public List<RoomOrderVO> getAll() {

		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setOrderId(rs.getInt("order_id"));
				roomOrderVO.setUserId(rs.getInt("user_id"));
				roomOrderVO.setVenId(rs.getInt("ven_id"));
				roomOrderVO.setCouponNo(rs.getInt("coupon_no"));
				roomOrderVO.setCustomerName(rs.getString("customer_name"));
				roomOrderVO.setCustomerPhone(rs.getString("customer_phone"));
				roomOrderVO.setCustomerEmail(rs.getString("customer_email"));
				roomOrderVO.setCheckinDate(rs.getObject("checkin_date", LocalDate.class));
				roomOrderVO.setCheckoutDate(rs.getObject("checkout_date", LocalDate.class));
				roomOrderVO.setOrderTime(rs.getObject("order_time", LocalDateTime.class));
				roomOrderVO.setBonusPointsUse(rs.getInt("bonus_points_use"));
				roomOrderVO.setOrderCharge(rs.getInt("order_charge"));
				roomOrderVO.setOrderChargeDiscount(rs.getInt("order_charge_discount"));
				roomOrderVO.setOrderStatus(rs.getInt("order_status"));
				roomOrderVO.setOrderCancel(rs.getString("order_cancel"));
				roomOrderVO.setOrderRefund(rs.getInt("order_refund"));
				roomOrderVO.setScore(rs.getInt("score"));
				roomOrderVO.setReviews(rs.getString("reviews"));
				roomOrderVO.setReviewsTime(rs.getObject("reviews_time", LocalDate.class));
				list.add(roomOrderVO);
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
	public List<RoomOrderVO> findByVenId(Integer venId) {

		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_VENID);
			pstmt.setInt(1, venId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setOrderId(rs.getInt("order_id"));
				roomOrderVO.setUserId(rs.getInt("user_id"));
				roomOrderVO.setVenId(rs.getInt("ven_id"));
				roomOrderVO.setCouponNo(rs.getInt("coupon_no"));
				roomOrderVO.setCustomerName(rs.getString("customer_name"));
				roomOrderVO.setCustomerPhone(rs.getString("customer_phone"));
				roomOrderVO.setCustomerEmail(rs.getString("customer_email"));
				roomOrderVO.setCheckinDate(rs.getObject("checkin_date", LocalDate.class));
				roomOrderVO.setCheckoutDate(rs.getObject("checkout_date", LocalDate.class));
				roomOrderVO.setOrderTime(rs.getObject("order_time", LocalDateTime.class));
				roomOrderVO.setBonusPointsUse(rs.getInt("bonus_points_use"));
				roomOrderVO.setOrderCharge(rs.getInt("order_charge"));
				roomOrderVO.setOrderChargeDiscount(rs.getInt("order_charge_discount"));
				roomOrderVO.setOrderStatus(rs.getInt("order_status"));
				roomOrderVO.setOrderCancel(rs.getString("order_cancel"));
				roomOrderVO.setOrderRefund(rs.getInt("order_refund"));
				roomOrderVO.setScore(rs.getInt("score"));
				roomOrderVO.setReviews(rs.getString("reviews"));
				roomOrderVO.setReviewsTime(rs.getObject("reviews_time", LocalDate.class));
				list.add(roomOrderVO);
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
	public List<RoomOrderVO> findByUserIdOrderByCheckin(Integer userId) {

		List<RoomOrderVO> list = new ArrayList<RoomOrderVO>();
		RoomOrderVO roomOrderVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_USERID_CHEKIN);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomOrderVO = new RoomOrderVO();
				roomOrderVO.setOrderId(rs.getInt("order_id"));
				roomOrderVO.setUserId(rs.getInt("user_id"));
				roomOrderVO.setVenId(rs.getInt("ven_id"));
				roomOrderVO.setCouponNo(rs.getInt("coupon_no"));
				roomOrderVO.setCustomerName(rs.getString("customer_name"));
				roomOrderVO.setCustomerPhone(rs.getString("customer_phone"));
				roomOrderVO.setCustomerEmail(rs.getString("customer_email"));
				roomOrderVO.setCheckinDate(rs.getObject("checkin_date", LocalDate.class));
				roomOrderVO.setCheckoutDate(rs.getObject("checkout_date", LocalDate.class));
				roomOrderVO.setOrderTime(rs.getObject("order_time", LocalDateTime.class));
				roomOrderVO.setBonusPointsUse(rs.getInt("bonus_points_use"));
				roomOrderVO.setOrderCharge(rs.getInt("order_charge"));
				roomOrderVO.setOrderChargeDiscount(rs.getInt("order_charge_discount"));
				roomOrderVO.setOrderStatus(rs.getInt("order_status"));
				roomOrderVO.setOrderCancel(rs.getString("order_cancel"));
				roomOrderVO.setOrderRefund(rs.getInt("order_refund"));
				roomOrderVO.setScore(rs.getInt("score"));
				roomOrderVO.setReviews(rs.getString("reviews"));
				roomOrderVO.setReviewsTime(rs.getObject("reviews_time", LocalDate.class));
				list.add(roomOrderVO);
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
	public void insertWithDetailUpdateUser(RoomOrderVO roomOrderVO, List<OrderDetailVO> detailList, Connection con) {

		PreparedStatement pstmt = null;

		try {

    			// 先新增訂單
    			String cols[] = {"ORDER_ID"};
    			pstmt = con.prepareStatement(INSERT_STMT , cols);	
    			pstmt.setInt(1, roomOrderVO.getUserId());
    			pstmt.setInt(2, roomOrderVO.getVenId());
    			pstmt.setObject(3, roomOrderVO.getCouponNo());
    			pstmt.setString(4, roomOrderVO.getCustomerName());
    			pstmt.setString(5, roomOrderVO.getCustomerPhone());
    			pstmt.setString(6, roomOrderVO.getCustomerEmail());
    			pstmt.setObject(7, roomOrderVO.getCheckinDate());
    			pstmt.setObject(8, roomOrderVO.getCheckoutDate());
    			pstmt.setObject(9, roomOrderVO.getOrderTime());
    			pstmt.setInt(10, roomOrderVO.getBonusPointsUse());
    			pstmt.setInt(11, roomOrderVO.getOrderCharge());
    			pstmt.setInt(12, roomOrderVO.getOrderChargeDiscount());
    			pstmt.setInt(13, roomOrderVO.getOrderStatus());
    			pstmt.executeUpdate();
    			
    			//掘取對應的自增主鍵值
    			String nextOrderId = null;
    			ResultSet rs = pstmt.getGeneratedKeys();
    			while (rs.next()) {
    				nextOrderId = rs.getString(1);
    			} 
    			rs.close();
    		
    		// 再同時新增員工
    		OrderDetailJDBCDAO detailDao = new OrderDetailJDBCDAO();
    		for (OrderDetailVO orderDetailVO : detailList) {
    			orderDetailVO.setOrderId(Integer.parseInt(nextOrderId)) ;
    			detailDao.insert2(orderDetailVO,con);
    		}
    		
    		
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-order");
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
	@Override
	public void updateWithVendor(RoomOrderVO roomOrderVO, VendorVO vendorVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增部門
			String cols[] = {"ORDER_ID"};
			pstmt = con.prepareStatement(UPDATE , cols);
			pstmt.setInt(1, roomOrderVO.getUserId());
			pstmt.setInt(2, roomOrderVO.getVenId());
			pstmt.setObject(3, roomOrderVO.getCouponNo());
			pstmt.setString(4, roomOrderVO.getCustomerName());
			pstmt.setString(5, roomOrderVO.getCustomerPhone());
			pstmt.setString(6, roomOrderVO.getCustomerEmail());
			pstmt.setObject(7, roomOrderVO.getCheckinDate());
			pstmt.setObject(8, roomOrderVO.getCheckoutDate());
			pstmt.setObject(9, roomOrderVO.getOrderTime());
			pstmt.setInt(10, roomOrderVO.getBonusPointsUse());
			pstmt.setInt(11, roomOrderVO.getOrderCharge());
			pstmt.setInt(12, roomOrderVO.getOrderChargeDiscount());
			pstmt.setInt(13, roomOrderVO.getOrderStatus());
			pstmt.setString(14, roomOrderVO.getOrderCancel());
			pstmt.setObject(15, roomOrderVO.getOrderRefund());
			pstmt.setObject(16, roomOrderVO.getScore());
			pstmt.setString(17, roomOrderVO.getReviews());
			pstmt.setObject(18, roomOrderVO.getReviewsTime());
			pstmt.setInt(19, roomOrderVO.getOrderId());
			pstmt.executeUpdate();
			
			//再同時更新廠商
			VendorJDBCDAO dao = new VendorJDBCDAO();
			dao.update(vendorVO);
			
			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	public static void main(String[] args) {
		RoomOrderJDBCDAO dao = new RoomOrderJDBCDAO();
		
		// 新增
		RoomOrderVO roomOrderVO = new RoomOrderVO();
		roomOrderVO.setUserId(1);
		roomOrderVO.setVenId(500000);
		roomOrderVO.setCouponNo(null);
		roomOrderVO.setCustomerName("name");
		roomOrderVO.setCustomerPhone("phone");
		roomOrderVO.setCustomerEmail("email");
		roomOrderVO.setCheckinDate(LocalDate.of(2022,3,22));
		roomOrderVO.setCheckoutDate(LocalDate.of(2022,3,23));
		roomOrderVO.setOrderTime(LocalDateTime.of(2022,3,22,16,34,33));
		roomOrderVO.setBonusPointsUse(0);
		roomOrderVO.setOrderCharge(3000);
		roomOrderVO.setOrderChargeDiscount(3000);
		roomOrderVO.setOrderStatus(2);
		roomOrderVO.setOrderCancel(null);
		roomOrderVO.setReviews(null);
		roomOrderVO.setReviewsTime(null);
		dao.insert(roomOrderVO);
		
		// 修改
//		RoomOrderVO roomOrderVO1 = new RoomOrderVO();
//		roomOrderVO1.setOrderId(17);
//		roomOrderVO1.setUserId(1);
//		roomOrderVO1.setVenId(500000);
//		roomOrderVO1.setCustomerName("mm");
//		roomOrderVO1.setCustomerPhone("");
//		roomOrderVO1.setCustomerEmail("");
//		roomOrderVO1.setCheckinDate(LocalDate.of(2022,3,21));
//		roomOrderVO1.setCheckoutDate(LocalDate.of(2022,3,22));
//		roomOrderVO1.setOrderTime(LocalDateTime.of(2022,3,20,16,34,33));
//		roomOrderVO1.setBonusPointsUse(0);
//		roomOrderVO1.setOrderCharge(3000);
//		roomOrderVO1.setOrderChargeDiscount(3000);
//		roomOrderVO1.setOrderStatus(2);
//		roomOrderVO1.setOrderCancel(null);
//		roomOrderVO1.setReviews("");
//		roomOrderVO1.setReviewsTime(LocalDate.now());
//		dao.update(roomOrderVO1);
		
		// 刪除
//		dao.delete(4);
		
		// 查詢
//		RoomOrderVO roomOrderVO2 = dao.findByPrimaryKey(2);
//		System.out.print(roomOrderVO2.getOrderId() + ",");
//		System.out.print(roomOrderVO2.getUserId() + ",");
//		System.out.print(roomOrderVO2.getVenId() + ",");
//		System.out.print(roomOrderVO2.getCouponNo() + ",");
//		System.out.print(roomOrderVO2.getCustomerName() + ",");
//		System.out.print(roomOrderVO2.getCustomerPhone() + ",");
//		System.out.println(roomOrderVO2.getCustomerEmail());
//		System.out.println(roomOrderVO2.getCheckinDate());
//		System.out.println(roomOrderVO2.getCheckoutDate());
//		System.out.println(roomOrderVO2.getOrderTime());
//		System.out.println(roomOrderVO2.getBonusPointsUse());
//		System.out.println(roomOrderVO2.getOrderCharge());
//		System.out.println(roomOrderVO2.getOrderChargeDiscount());
//		System.out.println(roomOrderVO2.getOrderStatus());
//		System.out.println(roomOrderVO2.getOrderCancel());
//		System.out.println(roomOrderVO2.getOrderRefund());
//		System.out.println(roomOrderVO2.getScore());
//		System.out.println(roomOrderVO2.getReviews());
//		System.out.println(roomOrderVO2.getReviewsTime());
//		System.out.println("---------------------");
		
		// 查詢
//		List<RoomOrderVO> list = dao.getAll();
//		for (RoomOrderVO aRoomOrder : list) {
//			System.out.print(aRoomOrder.getOrderId() + ",");
//			System.out.print(aRoomOrder.getUserId() + ",");
//			System.out.print(aRoomOrder.getVenId() + ",");
//			System.out.print(aRoomOrder.getCouponNo() + ",");
//			System.out.print(aRoomOrder.getCustomerName() + ",");
//			System.out.print(aRoomOrder.getCustomerPhone() + ",");
//			System.out.println(aRoomOrder.getCustomerEmail());
//			System.out.println(aRoomOrder.getCheckinDate());
//			System.out.println(aRoomOrder.getCheckoutDate());
//			System.out.println(aRoomOrder.getOrderTime());
//			System.out.println(aRoomOrder.getBonusPointsUse());
//			System.out.println(aRoomOrder.getOrderCharge());
//			System.out.println(aRoomOrder.getOrderChargeDiscount());
//			System.out.println(aRoomOrder.getOrderStatus());
//			System.out.println(aRoomOrder.getOrderCancel());
//			System.out.println(aRoomOrder.getOrderRefund());
//			System.out.println(aRoomOrder.getScore());
//			System.out.println(aRoomOrder.getReviews());
//			System.out.println(aRoomOrder.getReviewsTime());
//			System.out.println();
//		}
	}
}
