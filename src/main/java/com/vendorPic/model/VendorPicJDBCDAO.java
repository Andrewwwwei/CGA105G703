package com.vendorPic.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VendorPicJDBCDAO implements VendorPicDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO vendor_pic (ven_id,ven_pic) VALUES (?, ?)";
	private static final String GET_ALL_STMT = "SELECT ven_picid,ven_id,ven_pic FROM vendor_pic order by ven_picid";
	private static final String GET_ONE_STMT = "SELECT ven_picid,ven_id,ven_pic FROM vendor_pic where ven_picid = ?";
	private static final String DELETE = "DELETE FROM vendor_pic where ven_picid = ?";
	private static final String UPDATE = "UPDATE vendor_pic set ven_id =?,ven_pic=? where ven_picid = ?";
	private static final String GET_BY_VEN = "SELECT ven_picid,ven_id,ven_pic FROM vendor_pic where ven_id = ?";

	@Override
	public void insert(VendorPicVO vendorPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, vendorPicVO.getVenId());
			pstmt.setBytes(2, vendorPicVO.getVenPic());

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
	public void update(VendorPicVO vendorPicVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, vendorPicVO.getVenId());
			pstmt.setBytes(2, vendorPicVO.getVenPic());
			pstmt.setInt(3, vendorPicVO.getVenPicid());

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
	public void delete(Integer ven_picid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ven_picid);

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
	public VendorPicVO findByPrimaryKey(Integer ven_picid) {

		VendorPicVO vendorpicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ven_picid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				vendorpicVO = new VendorPicVO();
				vendorpicVO.setVenPicid(rs.getInt("VEN_PICID"));
				vendorpicVO.setVenId(rs.getInt("ven_id"));
				vendorpicVO.setVenPic(rs.getBytes("ven_pic"));

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
		return vendorpicVO;
	}

	@Override
	public List<VendorPicVO> getAll() {
		List<VendorPicVO> list = new ArrayList<VendorPicVO>();
		VendorPicVO vendorPicVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				vendorPicVO = new VendorPicVO();
				vendorPicVO.setVenPicid(rs.getInt("VEN_PICID"));
				vendorPicVO.setVenId(rs.getInt("ven_id"));
				vendorPicVO.setVenPic(rs.getBytes("ven_pic"));

				list.add(vendorPicVO); // Store the row in the list
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
	
	public List<VendorPicVO> getAVendor(Integer venId) {
		List<VendorPicVO> list = new ArrayList<VendorPicVO>();
		VendorPicVO vendorPicVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_VEN);
			pstmt.setInt(1, venId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vendorPicVO = new VendorPicVO();
				vendorPicVO.setVenPicid(rs.getInt("VEN_PICID"));
				vendorPicVO.setVenId(rs.getInt("ven_id"));
				vendorPicVO.setVenPic(rs.getBytes("ven_pic"));
				
				list.add(vendorPicVO); // Store the row in the list
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

		VendorPicJDBCDAO dao = new VendorPicJDBCDAO();

		// 新增
		VendorPicVO vendorpicVO1 = new VendorPicVO();
		int picount = 0;
		for (int vpid = 500000; vpid <= 500009; vpid++) {
			for (int count = 1; count <= 5; count++) {
				vendorpicVO1.setVenId(vpid);
				picount += 1;
				String pic2 = picount + ".jpg";
				File file = new File(pic2);
				byte[] pic = new byte[(int) file.length()];
				FileInputStream fis;

				try {
					fis = new FileInputStream(file);
					fis.read(pic);
					vendorpicVO1.setVenPic(pic);
					fis.close();
				} catch (Exception e) {

					e.printStackTrace();
				}

				dao.insert(vendorpicVO1);

			}
		}

//		vendorpicVO1.setVenId(vpid);
//		File file = new File("C:\\Users\\Tibame_T14\\Pictures\\vendorPic\\4.jpg");
//		byte[] pic = new byte[(int)file.length()];
//		FileInputStream fis;
//		
//		try {
//			fis = new FileInputStream(file);
//			fis.read(pic);
//			vendorpicVO1.setVenPic(pic);
//			fis.close();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		} 
//		
//		dao.insert(vendorpicVO1);

		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpno(7001);
//		empVO2.setEname("將");
//		empVO2.setJob("MANAGER2");
//		empVO2.setHiredate(java.sql.Date.valueOf("2002-01-01"));
//		empVO2.setSal(new Double(20000));
//		empVO2.setComm(new Double(200));
//		empVO2.setDeptno(20);
//		dao.update(empVO2);

		// 刪除
//		dao.delete(7014);

		// 單一查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(7001);
//		System.out.print(empVO3.getEmpno() + ",");
//		System.out.print(empVO3.getEname() + ",");
//		System.out.print(empVO3.getJob() + ",");
//		System.out.print(empVO3.getHiredate() + ",");
//		System.out.print(empVO3.getSal() + ",");
//		System.out.print(empVO3.getComm() + ",");
//		System.out.println(empVO3.getDeptno());
//		System.out.println("---------------------");

//		// 全部查詢
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpno() + ",");
//			System.out.print(aEmp.getEname() + ",");
//			System.out.print(aEmp.getJob() + ",");
//			System.out.print(aEmp.getHiredate() + ",");
//			System.out.print(aEmp.getSal() + ",");
//			System.out.print(aEmp.getComm() + ",");
//			System.out.print(aEmp.getDeptno());
//			System.out.println();
//		}
	}
}
