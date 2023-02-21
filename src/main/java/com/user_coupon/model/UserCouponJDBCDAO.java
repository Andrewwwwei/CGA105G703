package com.user_coupon.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserCouponJDBCDAO implements UserCouponDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO USER_COUPON (COUPON_NO,USER_ID,COUPON_QNT) VALUES ( ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT COUPON_NO,USER_ID,COUPON_QNT FROM USER_COUPON order by COUPON_NO";
	private static final String GET_ONE_STMT = "SELECT COUPON_NO,USER_ID,COUPON_QNT FROM USER_COUPON WHERE COUPON_NO=? AND USER_ID = ?";
	private static final String DELETE = "DELETE FROM USER_COUPON where COUPON_NO = ? AND USER_ID = ?";
	private static final String UPDATE = "UPDATE USER_COUPON set COUPON_QNT = ? where COUPON_NO = ? AND USER_ID = ?";
	private static final String GET_BY_USERID = "SELECT COUPON_NO,USER_ID,COUPON_QNT FROM USER_COUPON WHERE USER_ID = ?";

	@Override
	public void insert(UserCouponVO userCouponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
	Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setInt(1, userCouponVO.getCouponNo());
			pstmt.setInt(2, userCouponVO.getUserId());
			pstmt.setInt(3, userCouponVO.getCouponQnt());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(UserCouponVO userCouponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, userCouponVO.getCouponQnt());
			pstmt.setInt(2, userCouponVO.getCouponNo());
			pstmt.setInt(3, userCouponVO.getUserId());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer COUPON_NO,Integer USER_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, COUPON_NO);
			pstmt.setInt(2, USER_ID);
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public UserCouponVO findByPrimaryKey(Integer COUPON_NO,Integer USER_ID) {
		UserCouponVO userCouponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
				
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, COUPON_NO);
			pstmt.setInt(2, USER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				userCouponVO = new UserCouponVO();
				userCouponVO.setCouponNo(rs.getInt("COUPON_NO"));
				userCouponVO.setUserId(rs.getInt("USER_ID"));
				userCouponVO.setCouponQnt(rs.getInt("COUPON_QNT"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
		return userCouponVO;
	}

	@Override
	public List<UserCouponVO> getAll() {
		List<UserCouponVO> list = new ArrayList<UserCouponVO>();
		UserCouponVO userCouponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				userCouponVO = new UserCouponVO();
				userCouponVO.setCouponNo(rs.getInt("COUPON_NO"));
				userCouponVO.setUserId(rs.getInt("USER_ID"));
				userCouponVO.setCouponQnt(rs.getInt("COUPON_QNT"));
				list.add(userCouponVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public List<UserCouponVO> getByUserId(Integer userId) {
		List<UserCouponVO> list = new ArrayList<UserCouponVO>();
		UserCouponVO userCouponVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_USERID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				userCouponVO = new UserCouponVO();
				userCouponVO.setCouponNo(rs.getInt("COUPON_NO"));
				userCouponVO.setUserId(rs.getInt("USER_ID"));
				userCouponVO.setCouponQnt(rs.getInt("COUPON_QNT"));
				list.add(userCouponVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
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
	public void update2(UserCouponVO userCouponVO, Connection con) {
		PreparedStatement pstmt = null;

		try {
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, userCouponVO.getCouponQnt());
			pstmt.setInt(2, userCouponVO.getCouponNo());
			pstmt.setInt(3, userCouponVO.getUserId());
			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-user");
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

		UserCouponJDBCDAO dao = new UserCouponJDBCDAO();
		// 新增
//		UserCouponVO userCouponVO1 = new UserCouponVO();
//		userCouponVO1.setCouponNo(1);
//		userCouponVO1.setUserId(2);
//		userCouponVO1.setCouponQnt(1);
//		dao.insert(userCouponVO1);

//		//修改
//		UserCouponVO userCouponVO2 = new UserCouponVO();
//		userCouponVO2.setCouponNo(1);
//		userCouponVO2.setUserId(2);
//		userCouponVO2.setCouponQnt(2);
//		dao.update(userCouponVO2);

//		// 刪除
//		dao.delete(1,2);//雙PK

		// 查詢
		UserCouponVO userCouponVO3 = dao.findByPrimaryKey(1,1);//雙PK
		System.out.print(userCouponVO3.getCouponNo() + ",");
		System.out.print(userCouponVO3.getUserId() + ",");
		System.out.print(userCouponVO3.getCouponQnt() + ",");
		System.out.println("---------------------");

		// 查詢all
		List<UserCouponVO> list = dao.getAll();
		for (UserCouponVO Coupon : list) {
		System.out.print(Coupon.getCouponNo() + ",");
		System.out.print(Coupon.getUserId() + ",");
		System.out.print(Coupon.getCouponQnt() + ",");
		}
	}
}
