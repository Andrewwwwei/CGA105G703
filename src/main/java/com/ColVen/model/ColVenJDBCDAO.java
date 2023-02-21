package com.ColVen.model;

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

public class ColVenJDBCDAO implements ColVenDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO COL_VEN (VEN_ID,USER_ID) VALUES ( ?, ?)";
	private static final String GET_ALL_STMT = "select col.USER_ID,col.VEN_ID ,vd.VEN_NAME ,vd.VEN_TEL,vd.VEN_LOCATION,vd.VEN_WIN,vd.VEN_INTRO,vd.VEN_SCORE ,vd.VEN_SCORE_PEOPLE from vendor vd INNER JOIN col_ven col on col.VEN_ID = vd.VEN_ID  WHERE col.USER_ID= ? ; ";
	private static final String GET_ONE_STMT = "SELECT VEN_ID,USER_ID FROM COL_VEN WHERE VEN_ID=? AND USER_ID=?";
	private static final String DELETE = "DELETE FROM COL_VEN where VEN_ID = ? AND USER_ID = ?";
	private static final String UPDATE = "UPDATE COL_VEN set VEN_ID = ?,USER_ID = ? where VEN_ID = ? AND USER_ID = ?";

	@Override
	public void insert(ColVenVO colVenVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
	Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setInt(1, colVenVO.getVenId());
			pstmt.setInt(2, colVenVO.getUserId());
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
	public void update(ColVenVO colVenVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, colVenVO.getVenId());
			pstmt.setInt(2, colVenVO.getUserId());
			pstmt.setInt(3, colVenVO.getVenIdW());
			pstmt.setInt(4, colVenVO.getUserIdW());
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
	public void delete(Integer VEN_ID,Integer USER_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, VEN_ID);
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
	public ColVenVO findByPrimaryKey(Integer VEN_ID,Integer USER_ID) {
		ColVenVO colVenVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
				
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, VEN_ID);
			pstmt.setInt(2, USER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				colVenVO = new ColVenVO();
				colVenVO.setVenId(rs.getInt("VEN_ID"));
				colVenVO.setUserId(rs.getInt("USER_ID"));
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
		return colVenVO;
	}

	@Override
	public List<ColVenVO> getAll(Integer USER_ID) {
		List<ColVenVO> list = new ArrayList<ColVenVO>();
		ColVenVO colVenVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, USER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				colVenVO = new ColVenVO();
				colVenVO.setUserId(rs.getInt("col.USER_ID"));
				colVenVO.setVenId(rs.getInt("col.VEN_ID"));
				colVenVO.setVenName(rs.getString("vd.VEN_NAME"));
				colVenVO.setVenTel(rs.getString("vd.VEN_TEL"));
				colVenVO.setVenLocation(rs.getString("vd.VEN_LOCATION"));
				colVenVO.setVenWin(rs.getString("vd.VEN_WIN"));
				colVenVO.setVenIntro(rs.getString("vd.VEN_INTRO"));
				colVenVO.setVenScore(rs.getString("vd.VEN_SCORE"));
				colVenVO.setVenScorePeople(rs.getString("vd.VEN_SCORE_PEOPLE"));
				
				list.add(colVenVO); // Store the row in the list
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

		ColVenJDBCDAO dao = new ColVenJDBCDAO();
		// 新增
//		ColVenVO colVenVO1 = new ColVenVO();
//		colVenVO1.setVenId(1);
//		colVenVO1.setUserId(3);
//		dao.insert(colVenVO1);

//		//修改
//		ColVenVO colVenVO2 = new ColVenVO();
//		colVenVO2.setVenId(2);
//		colVenVO2.setUserId(4);
//		colVenVO2.setVenIdW(1);//where=?
//		colVenVO2.setUserIdW(3);//where=?
//		dao.update(colVenVO2);

//		// 刪除
//		dao.delete(2,4);//雙PK

		// 查詢
//		ColVenVO colVenVO3 = dao.findByPrimaryKey(2,4);//雙PK
//		System.out.print(colVenVO3.getVenId() + ",");
//		System.out.print(colVenVO3.getUserId() + ",");
//		System.out.println("---------------------");

//		// 查詢all
//		List<ColVenVO> list = dao.getAll();
//		for (ColVenVO colVenVO : list) {
//		System.out.print(colVenVO.getVenId() + ",");
//		System.out.print(colVenVO.getUserId() + ",");
//		}
	}
}
