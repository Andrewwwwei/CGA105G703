package com.tripMember.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tripDetail.model.TripDetailVO;

public class TripMemberJDBCDAO implements TripMemberDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";
	
	private static final String INSERT_STMT = 
			"INSERT INTO trip_member (TRIP_ID,USER_ID,IS_MBR) VALUES (?,?,?)";
	private static final String UPDATE = 
			"UPDATE trip_member set TRIP_ID=?, USER_ID=?, IS_MBR=? where TRIP_MBR_ID = ?";
	private static final String DELETE = 
			"DELETE FROM trip_member where TRIP_MBR_ID = ?";
	private static final String GET_GROUP_STMT = 
			"SELECT TRIP_MBR_ID,TRIP_ID,USER_ID,IS_MBR FROM trip_member where TRIP_ID = ?";
	private static final String GET_ONE_STMT =
			"SELECT TRIP_MBR_ID,TRIP_ID,USER_ID,IS_MBR FROM TRIP_MEMBER WHERE TRIP_ID=? AND USER_ID = ?";
	
	@Override
	public void insert(TripMemberVO tripMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, tripMemberVO.getTripId());
			pstmt.setInt(2, tripMemberVO.getUserId());
			pstmt.setBoolean(3, tripMemberVO.getIsMbr());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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
	public void jointInsert(TripMemberVO tripMemberVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, tripMemberVO.getTripId());
			pstmt.setInt(2, tripMemberVO.getUserId());
			pstmt.setBoolean(3, tripMemberVO.getIsMbr());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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
	public void update(TripMemberVO tripMemberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, tripMemberVO.getTripId());
			pstmt.setInt(2, tripMemberVO.getUserId());
			pstmt.setBoolean(3, tripMemberVO.getIsMbr());
			pstmt.setInt(4, tripMemberVO.getTripMbrId());
		
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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
	public void delete(Integer tripMbrId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tripMbrId);

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
	public List<TripMemberVO> findByTripId(Integer tripId) {
		List<TripMemberVO> list = new ArrayList<TripMemberVO>();
		TripMemberVO tripMemberVO =null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GROUP_STMT);

			pstmt.setInt(1, tripId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripMemberVO = new TripMemberVO();
				tripMemberVO.setTripMbrId(rs.getInt("TRIP_MBR_ID"));
				tripMemberVO.setTripId(rs.getInt("TRIP_ID"));
				tripMemberVO.setUserId(rs.getInt("USER_ID"));
				tripMemberVO.setIsMbr(rs.getBoolean("IS_MBR"));
				
				list.add(tripMemberVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	
	public TripMemberVO getOneMbr(Integer tripId, Integer userId) {
		TripMemberVO tripMemberVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tripId);
			pstmt.setInt(2, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripMemberVO = new TripMemberVO();
				tripMemberVO.setTripMbrId(rs.getInt("TRIP_MBR_ID"));
				tripMemberVO.setTripId(rs.getInt("TRIP_ID"));
				tripMemberVO.setUserId(rs.getInt("USER_ID"));
				tripMemberVO.setIsMbr(rs.getBoolean("IS_MBR"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
		
		
		return tripMemberVO;
	}
}
