package com.trip.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tripMember.model.TripMemberJDBCDAO;
import com.tripMember.model.TripMemberVO;

public class TripJDBCDAO implements TripDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";
	
	private static final String INSERT_STMT = 
			"INSERT INTO trip (TRIP_NAME,START_DATE,END_DATE,COVER_PIC,NOTE) VALUES (?,?,?,?,?)";
		private static final String GET_ONE_STMT = 
			"SELECT TRIP_ID,TRIP_NAME,START_DATE,END_DATE,COVER_PIC,NOTE FROM trip where TRIP_ID = ?";
		private static final String DELETE = 
			"DELETE trip, trip_member, trip_detail FROM trip LEFT JOIN trip_member ON trip_member.TRIP_ID = trip.TRIP_ID LEFT JOIN trip_detail ON trip_detail.TRIP_ID = trip.TRIP_ID WHERE trip.TRIP_ID = ?;";
		private static final String UPDATE = 
			"UPDATE trip set TRIP_NAME=?, START_DATE=?, END_DATE=?, COVER_PIC=?, NOTE=? where TRIP_ID = ?";
		private static final String GET_ALL_STMT = 
				"SELECT trip.TRIP_ID, trip.TRIP_NAME, trip.START_DATE, trip.END_DATE, trip.COVER_PIC, trip.NOTE FROM trip left join trip_member on trip.TRIP_ID = trip_member.TRIP_ID where trip_member.USER_ID = ? AND trip_member.IS_MBR = 1 order by TRIP_ID";
	
	@Override
	public void insert(TripVO tripVO, Integer userId) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			
			con.setAutoCommit(false);
			String cols[] = { "TRIP_ID" };
			
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setString(1, tripVO.getTripName());
			pstmt.setDate(2, tripVO.getStartDate());
			pstmt.setDate(3, tripVO.getEndDate());
			pstmt.setBytes(4, tripVO.getCoverPic());
			pstmt.setString(5, tripVO.getNote());
			pstmt.executeUpdate();
			
			//得到對應的自增PK
			String nextTripID = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextTripID = rs.getString(1);
				System.out.println("自增PK : " + nextTripID + "(新增成功)");
			}else {
				System.out.println("新增失敗 沒有PK");
			}
			
			//新增tripmbr
			TripMemberJDBCDAO tripmbrDAO = new TripMemberJDBCDAO();
			TripMemberVO tripMbrVO = new TripMemberVO();
			tripMbrVO.setTripId(Integer.parseInt(nextTripID));
			tripMbrVO.setUserId(userId);
			tripMbrVO.setIsMbr(true);
			tripmbrDAO.jointInsert(tripMbrVO, con);
			
			con.commit();
			con.setAutoCommit(true);
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("Couldn't rollback" + e1.getMessage());
			}
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("Couldn't rollback" + e1.getMessage());
			}
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
	public void update(TripVO tripVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setString(1, tripVO.getTripName());
			pstmt.setDate(2, tripVO.getStartDate());
			pstmt.setDate(3, tripVO.getEndDate());
			pstmt.setBytes(4, tripVO.getCoverPic());
			pstmt.setString(5, tripVO.getNote());
			pstmt.setInt(6, tripVO.getTripId());
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
	public void delete(Integer tripId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			con.createStatement().execute("SET FOREIGN_KEY_CHECKS=0;");
			con.setAutoCommit(false);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tripId);			
			pstmt.executeUpdate();
			con.createStatement().execute("SET FOREIGN_KEY_CHECKS=1;");
			con.setAutoCommit(true);

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("Couldn't rollback" + e1.getMessage());
			}
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("Couldn't rollback" + e1.getMessage());
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

	@Override
	public TripVO findByPrimaryKey(Integer tripId) {
		TripVO tripVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tripId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripVO = new TripVO();
				tripVO.setTripId(rs.getInt("TRIP_ID"));
				tripVO.setTripName(rs.getString("TRIP_NAME"));
				tripVO.setStartDate(rs.getDate("START_DATE"));
				tripVO.setEndDate(rs.getDate("END_DATE"));
				tripVO.setCoverPic(rs.getBytes("COVER_PIC"));
				tripVO.setNote(rs.getString("NOTE"));
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
		return tripVO;
	}

	@Override
	public List<TripVO> getAll(Integer userId) {
		List<TripVO> list = new ArrayList<TripVO>();
		TripVO tripVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				tripVO = new TripVO();
				tripVO.setTripId(rs.getInt("TRIP_ID"));
				tripVO.setTripName(rs.getString("TRIP_NAME"));
				tripVO.setStartDate(rs.getDate("START_DATE"));
				tripVO.setEndDate(rs.getDate("END_DATE"));
				tripVO.setCoverPic(rs.getBytes("COVER_PIC"));
				tripVO.setNote(rs.getString("NOTE"));

				list.add(tripVO); // Store the row in the list
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
}
