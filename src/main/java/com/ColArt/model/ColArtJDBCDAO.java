package com.ColArt.model;

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

public class ColArtJDBCDAO implements ColArtDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO COL_ART (ART_ID,USER_ID) VALUES ( ?, ?)";
	private static final String GET_ALL_STMT = "select col.USER_ID,col.ART_ID , ac.USER_ID , ac.ART_TITLE , ac.ART_CONTENT , ac.ART_LIKES_COUNT , ac.ART_SHOW_COUNT,ac.ART_TIME,us.USER_ACCOUNT from article ac INNER JOIN col_art col on col.ART_ID = ac.ART_ID  JOIN Users us on us.USER_ID = ac.USER_ID WHERE col.USER_ID= ? ; ";
	private static final String GET_ONE_STMT = "SELECT ART_ID,USER_ID FROM COL_ART WHERE ART_ID=? AND USER_ID=?";
	private static final String DELETE = "DELETE FROM COL_ART where ART_ID = ? AND USER_ID = ?";
	private static final String UPDATE = "UPDATE COL_ART set ART_ID = ?,USER_ID = ? where ART_ID=? AND USER_ID=?";

	@Override
	public void insert(ColArtVO colArtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
	Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);


			pstmt.setInt(1, colArtVO.getArtId());
			pstmt.setInt(2, colArtVO.getUserId());
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
	public void update(ColArtVO colArtVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, colArtVO.getArtId());
			pstmt.setInt(2, colArtVO.getUserId());
			pstmt.setInt(3, colArtVO.getArtIdW());
			pstmt.setInt(4, colArtVO.getUserIdW());
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
	public void delete(Integer ART_ID,Integer USER_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, ART_ID);
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
	public ColArtVO findByPrimaryKey(Integer USER_ID) {
		ColArtVO colArtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
				
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			
			pstmt.setInt(1, USER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				colArtVO = new ColArtVO();
				colArtVO.setArtId(rs.getInt("ART_ID"));
				colArtVO.setUserId(rs.getInt("USER_ID"));
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
		return colArtVO;
	}
	
	@Override
	public ColArtVO getColArt(Integer userId,Integer artId) {
		ColArtVO colArtVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
				
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, userId);
			pstmt.setInt(2, artId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				colArtVO = new ColArtVO();
				colArtVO.setArtId(rs.getInt("USER_ID"));
				colArtVO.setUserId(rs.getInt("ART_ID"));
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
		return colArtVO;
	}

	@Override
	public List<ColArtVO> getAll(Integer USER_ID) {
		List<ColArtVO> list = new ArrayList<ColArtVO>();
		ColArtVO colArtVO = null;

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

				colArtVO = new ColArtVO();
				colArtVO.setUserId(rs.getInt("col.USER_ID"));
				colArtVO.setArtId(rs.getInt("col.ART_ID"));
				colArtVO.setArUserId(rs.getInt("ac.USER_ID"));
				colArtVO.setArtTitle(rs.getString("ac.ART_TITLE"));
				colArtVO.setArtContent(rs.getString("ac.ART_CONTENT"));
				colArtVO.setArtLikesCount(rs.getInt("ac.ART_LIKES_COUNT"));
				colArtVO.setArtShowCount(rs.getInt("ac.ART_SHOW_COUNT"));
				colArtVO.setArtTime(rs.getTimestamp("ac.ART_TIME"));
				colArtVO.setUsuserAccount(rs.getString("us.USER_ACCOUNT"));
				list.add(colArtVO); // Store the row in the list
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

		ColArtJDBCDAO dao = new ColArtJDBCDAO();
		// 新增
//		ColArtVO colArtVO1 = new ColArtVO();
//		colArtVO1.setArtId(1);
//		colArtVO1.setUserId(3);
//		dao.insert(colArtVO1);

//		//修改
//		ColArtVO colArtVO2 = new ColArtVO();
//		colArtVO2.setArtId(1);
//		colArtVO2.setUserId(3);
//		colArtVO2.setArtIdW(2);//where=?
//		colArtVO2.setUserIdW(4);//where=?
//		dao.update(colArtVO2);

//		// 刪除
//		dao.delete(1,3);//雙PK

		// 查詢
//		ColArtVO colArtVO3 = dao.findByPrimaryKey(2,4);//雙PK
//		System.out.print(colArtVO3.getArtId() + ",");
//		System.out.print(colArtVO3.getUserId() + ",");
//		System.out.println("---------------------");

		// 查詢all
		List<ColArtVO> list = dao.getAll(1);
		for (ColArtVO colArt : list) {
		System.out.print(colArt.getUserId() + ",");
		System.out.print(colArt.getArtId() + ",");
		System.out.print(colArt.getArUserId() + ",");
		System.out.print(colArt.getArtTitle() + ",");
		System.out.print(colArt.getArtContent() + ",");
		System.out.print(colArt.getArtLikesCount() + ",");
		System.out.print(colArt.getArtShowCount() + ",");
		System.out.print(colArt.getArtTime() + ",");
		System.out.print(colArt.getUsuserAccount() + ",");
}
	}
}
