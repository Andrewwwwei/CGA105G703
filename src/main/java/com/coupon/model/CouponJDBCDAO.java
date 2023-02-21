package com.coupon.model;

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

public class CouponJDBCDAO implements CouponDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO COUPON (COUPON_TITLE,COUPON_TYPE,COUPON_CONTENT,COUPON_START,COUPON_END,COUPON_IDENTITY,COUPON_DISCOUNT,COUPON_COUNT,COUPON_SELECTOR,COUPON_PER_AMOUNT) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT COUPON_NO,COUPON_TITLE,COUPON_TYPE,COUPON_CONTENT,COUPON_START,COUPON_END,COUPON_IDENTITY,COUPON_DISCOUNT,COUPON_COUNT,COUPON_SELECTOR,COUPON_PER_AMOUNT FROM COUPON order by COUPON_NO";
	private static final String GET_ONE_STMT = "SELECT COUPON_NO,COUPON_TITLE,COUPON_TYPE,COUPON_CONTENT,COUPON_START,COUPON_END,COUPON_IDENTITY,COUPON_DISCOUNT,COUPON_COUNT,COUPON_SELECTOR,COUPON_PER_AMOUNT FROM COUPON WHERE COUPON_NO=?";
	private static final String DELETE = "DELETE FROM COUPON where COUPON_NO = ?";
	private static final String UPDATE = "UPDATE COUPON set COUPON_TITLE = ?,COUPON_TYPE = ?,COUPON_CONTENT = ?,COUPON_START= ?,COUPON_END = ?,COUPON_IDENTITY = ?,COUPON_DISCOUNT = ?,COUPON_COUNT = ?,COUPON_SELECTOR = ?,COUPON_PER_AMOUNT = ? where COUPON_NO=?";

	@Override
	public void insert(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
	Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setString(1, couponVO.getCouponTitle());
			pstmt.setInt(2, couponVO.getCouponType());
			pstmt.setString(3, couponVO.getCouponContent());
			pstmt.setTimestamp(4, couponVO.getCouponStart());
			pstmt.setTimestamp(5, couponVO.getCouponEnd());
			pstmt.setInt(6, couponVO.getCouponIdentity());
			pstmt.setFloat(7, couponVO.getCouponDiscount());
			pstmt.setInt(8, couponVO.getCouponCount());
			pstmt.setInt(9, couponVO.getCouponSelector());
			pstmt.setInt(10, couponVO.getCouponPerAmout());
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
	public void update(CouponVO couponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, couponVO.getCouponTitle());
			pstmt.setInt(2, couponVO.getCouponType());
			pstmt.setString(3, couponVO.getCouponContent());
			pstmt.setTimestamp(4, couponVO.getCouponStart());
			pstmt.setTimestamp(5, couponVO.getCouponEnd());
			pstmt.setInt(6, couponVO.getCouponIdentity());
			pstmt.setFloat(7, couponVO.getCouponDiscount());
			pstmt.setInt(8, couponVO.getCouponCount());
			pstmt.setInt(9, couponVO.getCouponSelector());
			pstmt.setInt(10, couponVO.getCouponPerAmout());
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
	public void delete(Integer COUPON_NO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, COUPON_NO);

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
	public CouponVO findByPrimaryKey(Integer COUPON_NO) {
		CouponVO couponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
				
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, COUPON_NO);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				couponVO = new CouponVO();
				couponVO.setCouponNo(rs.getInt("COUPON_NO"));
				couponVO.setCouponTitle(rs.getString("COUPON_TITLE"));
				couponVO.setCouponType(rs.getInt("COUPON_TYPE"));
				couponVO.setCouponContent(rs.getString("COUPON_CONTENT"));
				couponVO.setCouponStart(rs.getTimestamp("COUPON_START"));
				couponVO.setCouponEnd(rs.getTimestamp("COUPON_END"));
				couponVO.setCouponIdentity(rs.getInt("COUPON_IDENTITY"));
				couponVO.setCouponDiscount(rs.getFloat("COUPON_DISCOUNT"));
				couponVO.setCouponCount(rs.getInt("COUPON_COUNT"));
				couponVO.setCouponSelector(rs.getInt("COUPON_SELECTOR"));
				couponVO.setCouponPerAmout(rs.getInt("COUPON_PER_AMOUNT"));
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
		return couponVO;
	}

	@Override
	public List<CouponVO> getAll() {
		List<CouponVO> list = new ArrayList<CouponVO>();
		CouponVO couponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				couponVO = new CouponVO();
				couponVO.setCouponNo(rs.getInt("COUPON_NO"));
				couponVO.setCouponTitle(rs.getString("COUPON_TITLE"));
				couponVO.setCouponType(rs.getInt("COUPON_TYPE"));
				couponVO.setCouponContent(rs.getString("COUPON_CONTENT"));
				couponVO.setCouponStart(rs.getTimestamp("COUPON_START"));
				couponVO.setCouponEnd(rs.getTimestamp("COUPON_END"));
				couponVO.setCouponIdentity(rs.getInt("COUPON_IDENTITY"));
				couponVO.setCouponDiscount(rs.getFloat("COUPON_DISCOUNT"));
				couponVO.setCouponCount(rs.getInt("COUPON_COUNT"));
				couponVO.setCouponSelector(rs.getInt("COUPON_SELECTOR"));
				couponVO.setCouponPerAmout(rs.getInt("COUPON_PER_AMOUNT"));
			
				list.add(couponVO); // Store the row in the list
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

	public static void main(String[] args) {

		CouponJDBCDAO dao = new CouponJDBCDAO();
		// 新增
//		CouponVO couponVO1 = new CouponVO();
//		couponVO1.setCouponTitle("清明節優惠");
//		couponVO1.setCouponType(0);
//		couponVO1.setCouponContent("清明慎終追遠，當然要折價");
//		couponVO1.setCouponStart(java.sql.Timestamp.valueOf("2023-01-01 00:00:00"));
//		couponVO1.setCouponEnd(java.sql.Timestamp.valueOf("2023-12-31 23:59:59"));
//		couponVO1.setCouponIdentity(0);
//		couponVO1.setCouponDiscount((float) 0.8);
//		couponVO1.setCouponCount(1);
//		couponVO1.setCouponSelector(5000);
//		couponVO1.setCouponPerAmout(2);
//		dao.insert(couponVO1);
////
//		//修改
//		CouponVO couponVO2 = dao.findByPrimaryKey(2);
//		couponVO2.setCOUPON_TITLE("母親節優惠");
//		couponVO2.setCOUPON_TYPE(1);
//		couponVO2.setCOUPON_CONTENT("母親節回饋辛苦媽媽，當然要折價");
//		couponVO2.setCOUPON_START(java.sql.Timestamp.valueOf("2024-01-01 00:00:00"));
//		couponVO2.setCOUPON_END(java.sql.Timestamp.valueOf("2024-12-31 23:59:59"));
//		couponVO2.setCOUPON_IDENTITY(1);
//		couponVO2.setCOUPON_DISCOUNT((float) 0.7);
//		couponVO2.setCOUPON_COUNT(2);
//		couponVO2.setCOUPON_SELECTOR(10000);
//		couponVO2.setCOUPON_PER_AMOUNT(3);
//		dao.update(couponVO2);

//		// 刪除
//		dao.delete(2);

		// 查詢
		CouponVO couponVO3 = dao.findByPrimaryKey(55);
		System.out.print(couponVO3.getCouponNo() + ",");
		System.out.print(couponVO3.getCouponTitle() + ",");
		System.out.print(couponVO3.getCouponType() + ",");
		System.out.print(couponVO3.getCouponContent() + ",");
		System.out.print(couponVO3.getCouponStart() + ",");
		System.out.print(couponVO3.getCouponEnd() + ",");
		System.out.print(couponVO3.getCouponIdentity() + ",");
		System.out.print(couponVO3.getCouponDiscount() + ",");
		System.out.print(couponVO3.getCouponCount() + ",");
		System.out.print(couponVO3.getCouponSelector() + ",");
		System.out.print(couponVO3.getCouponPerAmout() + ",");
		System.out.println("---------------------");

		// 查詢all
		List<CouponVO> list = dao.getAll();
		for (CouponVO Coupon : list) {
		System.out.print(Coupon.getCouponNo() + ",");
		System.out.print(Coupon.getCouponTitle() + ",");
		System.out.print(Coupon.getCouponType() + ",");
		System.out.print(Coupon.getCouponContent() + ",");
		System.out.print(Coupon.getCouponStart() + ",");
		System.out.print(Coupon.getCouponEnd() + ",");
		System.out.print(Coupon.getCouponIdentity() + ",");
		System.out.print(Coupon.getCouponDiscount() + ",");
		System.out.print(Coupon.getCouponCount() + ",");
		System.out.print(Coupon.getCouponSelector() + ",");
		System.out.print(Coupon.getCouponPerAmout() + ",");
		}
	}
}
