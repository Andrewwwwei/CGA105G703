package com.vendor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbcUtil_CompositeQuery_Vendor.jdbcUtil_CompositeQuery_Vendor;

public class VendorJDBCDAO implements VendorDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
		"INSERT INTO vendor (ven_pw,ven_name,ven_taxnum,ven_email,ven_tel,ven_location,ven_win,ven_wintel,ven_intro,ven_canpolicy,ven_notice,ven_bank,ven_status,ven_score_people,ven_joindate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT ven_id,ven_pw,ven_name,ven_taxnum,ven_email,ven_tel,ven_location,ven_win,ven_wintel,ven_intro,ven_canpolicy,ven_notice,ven_bank,ven_status,ven_score,ven_score_people,ven_joindate FROM vendor order by ven_id";
	private static final String GET_ONE_STMT = 
		"SELECT ven_id,ven_pw,ven_name,ven_taxnum,ven_email,ven_tel,ven_location,ven_win,ven_wintel,ven_intro,ven_canpolicy,ven_notice,ven_bank,ven_status,ven_score,ven_score_people,ven_joindate FROM vendor where ven_id = ?";
	private static final String DELETE = 
		"DELETE FROM vendor where ven_id = ?";
	private static final String UPDATE = 
		"UPDATE vendor set ven_pw =?,ven_name=?,ven_taxnum=?,ven_email=?,ven_tel=?,ven_location=?,ven_win=?,ven_wintel=?,ven_intro=?,ven_canpolicy=?,ven_notice=?,ven_bank=?,ven_status=?,ven_score=?,ven_score_people=?,ven_joindate=? where ven_id = ?";
	private static final String GET_BY_KEYWORD = 
		"SELECT ven_id,ven_pw,ven_name,ven_taxnum,ven_email,ven_tel,ven_location,ven_win,ven_wintel,ven_intro,ven_canpolicy,ven_notice,ven_bank,ven_status,ven_score,ven_score_people,ven_joindate FROM vendor where VEN_NAME LIKE ? OR VEN_LOCATION LIKE ?";
	private static final String GET_BY_TAX = 
		    "SELECT * FROM vendor  where ven_taxnum = ?";
		

	@Override
	public void insert(VendorVO vendorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, vendorVO.getVenPw());
			pstmt.setString(2, vendorVO.getVenName());
			pstmt.setString(3, vendorVO.getVenTaxnum());
			pstmt.setString(4,vendorVO.getVenEmail());
			pstmt.setString(5, vendorVO.getVenTel());
			pstmt.setString(6, vendorVO.getVenLocation());
			pstmt.setString(7, vendorVO.getVenWin());
			pstmt.setString(8, vendorVO.getVenWintel());
			pstmt.setString(9, vendorVO.getVenIntro());
			pstmt.setString(10,vendorVO.getVenCanpolicy());
			pstmt.setString(11, vendorVO.getVenNotice());
			pstmt.setString(12, vendorVO.getVenBank());
			pstmt.setInt(13, vendorVO.getVenStatus());
			pstmt.setInt(14, vendorVO.getVenScorePeople());
			pstmt.setObject(15, vendorVO.getVenJoinDate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
	public void update(VendorVO vendorVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, vendorVO.getVenPw());
			pstmt.setString(2, vendorVO.getVenName());
			pstmt.setString(3, vendorVO.getVenTaxnum());
			pstmt.setString(4,vendorVO.getVenEmail());
			pstmt.setString(5, vendorVO.getVenTel());
			pstmt.setString(6, vendorVO.getVenLocation());
			pstmt.setString(7, vendorVO.getVenWin());
			pstmt.setString(8, vendorVO.getVenWintel());
			pstmt.setString(9, vendorVO.getVenIntro());
			pstmt.setString(10,vendorVO.getVenCanpolicy());
			pstmt.setString(11, vendorVO.getVenNotice());
			pstmt.setString(12, vendorVO.getVenBank());
			pstmt.setInt(13, vendorVO.getVenStatus());
			pstmt.setDouble(14, vendorVO.getVenScore());
			pstmt.setInt(15, vendorVO.getVenScorePeople());
			pstmt.setDate(16, vendorVO.getVenJoinDate());
			pstmt.setInt(17, vendorVO.getVenId());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
	public void delete(Integer ven_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ven_id);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
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
	public VendorVO findByPrimaryKey(Integer ven_id) {

		VendorVO vendorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, ven_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				vendorVO = new VendorVO();
//				vendorVO.setVenId(ven_id);
				vendorVO.setVenId(rs.getInt("ven_id"));				
				vendorVO.setVenPw(rs.getString("ven_pw"));
				vendorVO.setVenName(rs.getString("ven_name"));
				vendorVO.setVenTaxnum(rs.getString("ven_taxnum"));
				vendorVO.setVenEmail(rs.getString("ven_email"));
				vendorVO.setVenTel(rs.getString("ven_tel"));
				vendorVO.setVenLocation(rs.getString("ven_location"));
				vendorVO.setVenWin(rs.getString("ven_win"));
				vendorVO.setVenWintel(rs.getString("ven_wintel"));
				vendorVO.setVenIntro(rs.getString("ven_intro"));
				vendorVO.setVenCanpolicy(rs.getString("ven_canpolicy"));
				vendorVO.setVenNotice(rs.getString("ven_notice"));
				vendorVO.setVenBank(rs.getString("ven_bank"));
				vendorVO.setVenStatus(rs.getInt("ven_status"));
				vendorVO.setVenScore(rs.getFloat("ven_score"));
				vendorVO.setVenScorePeople(rs.getInt("ven_score_people"));
				vendorVO.setVenJoinDate(rs.getDate("ven_joindate"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return vendorVO;
	}

	@Override
	public List<VendorVO> getAll() {
		List<VendorVO> list = new ArrayList<VendorVO>();
		VendorVO vendorVO = null;

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
				vendorVO = new VendorVO();
				vendorVO.setVenId(rs.getInt("ven_id"));
				vendorVO.setVenPw(rs.getString("ven_pw"));
				vendorVO.setVenName(rs.getString("ven_name"));
				vendorVO.setVenTaxnum(rs.getString("ven_taxnum"));
				vendorVO.setVenEmail(rs.getString("ven_email"));
				vendorVO.setVenTel(rs.getString("ven_tel"));
				vendorVO.setVenLocation(rs.getString("ven_location"));
				vendorVO.setVenWin(rs.getString("ven_win"));
				vendorVO.setVenWintel(rs.getString("ven_wintel"));
				vendorVO.setVenIntro(rs.getString("ven_intro"));
				vendorVO.setVenCanpolicy(rs.getString("ven_canpolicy"));
				vendorVO.setVenNotice(rs.getString("ven_notice"));
				vendorVO.setVenBank(rs.getString("ven_bank"));
				vendorVO.setVenStatus(rs.getInt("ven_status"));
				vendorVO.setVenScore(rs.getFloat("ven_score"));
				vendorVO.setVenScorePeople(rs.getInt("ven_score_people"));
				vendorVO.setVenJoinDate(rs.getDate("ven_joindate"));
				list.add(vendorVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<VendorVO> getAll(Map<String, String[]> map) {
		List<VendorVO> list = new ArrayList<VendorVO>();
		VendorVO vendorVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from cga105_g7.vendor "
		          + jdbcUtil_CompositeQuery_Vendor.get_WhereCondition(map)
		          + "order by ven_id";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				vendorVO = new VendorVO();
				vendorVO.setVenId(rs.getInt("ven_id"));
				vendorVO.setVenPw(rs.getString("ven_pw"));
				vendorVO.setVenName(rs.getString("ven_name"));
				vendorVO.setVenTaxnum(rs.getString("ven_taxnum"));
				vendorVO.setVenEmail(rs.getString("ven_email"));
				vendorVO.setVenTel(rs.getString("ven_tel"));
				vendorVO.setVenLocation(rs.getString("ven_location"));
				vendorVO.setVenWin(rs.getString("ven_win"));
				vendorVO.setVenWintel(rs.getString("ven_wintel"));
				vendorVO.setVenIntro(rs.getString("ven_intro"));
				vendorVO.setVenCanpolicy(rs.getString("ven_canpolicy"));
				vendorVO.setVenNotice(rs.getString("ven_notice"));
				vendorVO.setVenBank(rs.getString("ven_bank"));
				vendorVO.setVenStatus(rs.getInt("ven_status"));
				vendorVO.setVenScore(rs.getFloat("ven_score"));
				vendorVO.setVenScorePeople(rs.getInt("ven_score_people"));
				vendorVO.setVenJoinDate(rs.getDate("ven_joindate"));
				list.add(vendorVO); // Store the row in the list

			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public List<VendorVO> getByKeyword(String keyword) {
		List<VendorVO> list = new ArrayList<VendorVO>();
		VendorVO vendorVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_KEYWORD);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				vendorVO = new VendorVO();
				vendorVO.setVenId(rs.getInt("ven_id"));
				vendorVO.setVenPw(rs.getString("ven_pw"));
				vendorVO.setVenName(rs.getString("ven_name"));
				vendorVO.setVenTaxnum(rs.getString("ven_taxnum"));
				vendorVO.setVenEmail(rs.getString("ven_email"));
				vendorVO.setVenTel(rs.getString("ven_tel"));
				vendorVO.setVenLocation(rs.getString("ven_location"));
				vendorVO.setVenWin(rs.getString("ven_win"));
				vendorVO.setVenWintel(rs.getString("ven_wintel"));
				vendorVO.setVenIntro(rs.getString("ven_intro"));
				vendorVO.setVenCanpolicy(rs.getString("ven_canpolicy"));
				vendorVO.setVenNotice(rs.getString("ven_notice"));
				vendorVO.setVenBank(rs.getString("ven_bank"));
				vendorVO.setVenStatus(rs.getInt("ven_status"));
				vendorVO.setVenScore(rs.getFloat("ven_score"));
				vendorVO.setVenScorePeople(rs.getInt("ven_score_people"));
				vendorVO.setVenJoinDate(rs.getDate("ven_joindate"));
				list.add(vendorVO); // Store the row in the list
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public VendorVO findByTax(String ven_taxnum) {
		VendorVO vendorVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_TAX);

			pstmt.setString(1, ven_taxnum);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				vendorVO = new VendorVO();
				vendorVO.setVenId(rs.getInt("ven_id"));				
				vendorVO.setVenPw(rs.getString("ven_pw"));
				vendorVO.setVenName(rs.getString("ven_name"));
				vendorVO.setVenTaxnum(rs.getString("ven_taxnum"));
				vendorVO.setVenEmail(rs.getString("ven_email"));
				vendorVO.setVenTel(rs.getString("ven_tel"));
				vendorVO.setVenLocation(rs.getString("ven_location"));
				vendorVO.setVenWin(rs.getString("ven_win"));
				vendorVO.setVenWintel(rs.getString("ven_wintel"));
				vendorVO.setVenIntro(rs.getString("ven_intro"));
				vendorVO.setVenCanpolicy(rs.getString("ven_canpolicy"));
				vendorVO.setVenNotice(rs.getString("ven_notice"));
				vendorVO.setVenBank(rs.getString("ven_bank"));
				vendorVO.setVenStatus(rs.getInt("ven_status"));
				vendorVO.setVenScore(rs.getFloat("ven_score"));
				vendorVO.setVenScorePeople(rs.getInt("ven_score_people"));
				vendorVO.setVenJoinDate(rs.getDate("ven_joindate"));

			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return vendorVO;
	}
	
	@Override
	public void update(VendorVO vendorVO, Connection con) {
		PreparedStatement pstmt = null;

		try {

			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, vendorVO.getVenPw());
			pstmt.setString(2, vendorVO.getVenName());
			pstmt.setString(3, vendorVO.getVenTaxnum());
			pstmt.setString(4,vendorVO.getVenEmail());
			pstmt.setString(5, vendorVO.getVenTel());
			pstmt.setString(6, vendorVO.getVenLocation());
			pstmt.setString(7, vendorVO.getVenWin());
			pstmt.setString(8, vendorVO.getVenWintel());
			pstmt.setString(9, vendorVO.getVenIntro());
			pstmt.setString(10,vendorVO.getVenCanpolicy());
			pstmt.setString(11, vendorVO.getVenNotice());
			pstmt.setString(12, vendorVO.getVenBank());
			pstmt.setInt(13, vendorVO.getVenStatus());
			pstmt.setDouble(14, vendorVO.getVenScore());
			pstmt.setInt(15, vendorVO.getVenScorePeople());
			pstmt.setDate(16, vendorVO.getVenJoinDate());
			pstmt.setInt(17, vendorVO.getVenId());

			pstmt.executeUpdate();
			
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
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

		VendorJDBCDAO dao = new VendorJDBCDAO();

		//新增
//		VendorVO vendorVO1 = new VendorVO();
//		vendorVO1.setVenPw("3333");
//		vendorVO1.setVenName("test");
//		vendorVO1.setVenTaxnum("2222");
//		vendorVO1.setVenEmail("chia@gmail.com");
//		vendorVO1.setVenTel("222222");
//		vendorVO1.setVenLocation("22222");
//		vendorVO1.setVenWin("2222");
//		vendorVO1.setVenWintel("222");
//		vendorVO1.setVenIntro("2222");
//		vendorVO1.setVenCanpolicy("2222");
//		vendorVO1.setVenNotice("2222");
//		vendorVO1.setVenStatus(0);
//		vendorVO1.setVenBank("222");		
//		vendorVO1.setVenScore((float) 10);
//		vendorVO1.setVenScorePeople(10);
//		vendorVO1.setVenJoinDate(java.sql.Date.valueOf("2005-01-01"));		
//
//
//		dao.insert(vendorVO1);

	
//
//		//刪除
//		dao.delete(500013);
//
//		// 查詢
//		VendorVO vendorVO3 = dao.findByPrimaryKey(500010);
//		System.out.print(vendorVO3.getVenId() + ",");
//		System.out.print(vendorVO3.getVenPw() + ",");
//		System.out.print(vendorVO3.getVenName() + ",");
//		System.out.print(vendorVO3.getVenTaxnum() + ",");
//		System.out.print(vendorVO3.getVenEmail() + ",");
//		System.out.print(vendorVO3.getVenTel() + ",");
//		System.out.print(vendorVO3.getVenLocation() + ",");
//		System.out.print(vendorVO3.getVenWin() + ",");
//		System.out.print(vendorVO3.getVenWintel() + ",");
//		System.out.print(vendorVO3.getVenIntro() + ",");
//		System.out.print(vendorVO3.getVenCanpolicy() + ",");
//		System.out.print(vendorVO3.getVenNotice() + ",");
//		System.out.print(vendorVO3.getVenBank() + ",");
//		System.out.print(vendorVO3.getVenStatus() + ",");
//		System.out.print(vendorVO3.getVenScore() + ",");
//		System.out.print(vendorVO3.getVenScorePeople() + ",");
//		System.out.print(vendorVO3.getVenJoinDate() + ",");
//
//		System.out.println("---------------------");

//		// 查詢全部
//		List<VendorVO> list = dao.getAll();
//		for (VendorVO aVen : list) {
//		System.out.print(aVen.getVenId() + ",");
//		System.out.print(aVen.getVenPw() + ",");
//		System.out.print(aVen.getVenName() + ",");
//		System.out.print(aVen.getVenTaxnum() + ",");
//		System.out.print(aVen.getVenEmail() + ",");
//		System.out.print(aVen.getVenTel() + ",");
//		System.out.print(aVen.getVenLocation() + ",");
//		System.out.print(aVen.getVenWin() + ",");
//		System.out.print(aVen.getVenWintel() + ",");
//		System.out.print(aVen.getVenIntro() + ",");
//		System.out.print(aVen.getVenCanpolicy() + ",");
//		System.out.print(aVen.getVenNotice() + ",");
//		System.out.print(aVen.getVenBank() + ",");
//		System.out.print(aVen.getVenStatus() + ",");
//		System.out.print(aVen.getVenScore() + ",");
//		System.out.print(aVen.getVenScorePeople() + ",");
//		System.out.print(aVen.getVenJoinDate() + ",");
//			System.out.println();
		
		//關鍵字查詢
//		List<VendorVO> list = dao.getByKeyword("台中");
//		for (VendorVO aVen : list) {
//			System.out.print(aVen.getVenId() + ",");
//			System.out.print(aVen.getVenPw() + ",");
//			System.out.print(aVen.getVenName() + ",");
//			System.out.print(aVen.getVenTaxnum() + ",");
//			System.out.print(aVen.getVenEmail() + ",");
//			System.out.print(aVen.getVenTel() + ",");
//			System.out.print(aVen.getVenLocation() + ",");
//			System.out.print(aVen.getVenWin() + ",");
//			System.out.print(aVen.getVenWintel() + ",");
//			System.out.print(aVen.getVenIntro() + ",");
//			System.out.print(aVen.getVenCanpolicy() + ",");
//			System.out.print(aVen.getVenNotice() + ",");
//			System.out.print(aVen.getVenBank() + ",");
//			System.out.print(aVen.getVenStatus() + ",");
//			System.out.print(aVen.getVenScore() + ",");
//			System.out.print(aVen.getVenScorePeople() + ",");
//			System.out.print(aVen.getVenJoinDate() + ",");
//			System.out.println();
//		}
		
	}
}
