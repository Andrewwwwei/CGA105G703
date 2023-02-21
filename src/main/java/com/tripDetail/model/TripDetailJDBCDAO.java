package com.tripDetail.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.location.model.LocationVO;
import com.locationPic.model.LocationPicVO;

public class TripDetailJDBCDAO implements TripDetailDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";
	
	private static final String INSERT_STMT = 
			"INSERT INTO trip_detail (TRIP_ID,LOC_ID,ARRIVAL_TIME,LEAVE_TIME) VALUES (?,?,?,?)";
	private static final String DELETE = 
			"DELETE FROM trip_detail where TRIP_DETAIL_ID = ?";
	private static final String DELETE_DATE = 
			"DELETE FROM trip_detail WHERE TRIP_ID=? AND DATE(ARRIVAL_TIME)=?;";
	private static final String GET_ONE_STMT = 
			"SELECT TRIP_DETAIL_ID,TRIP_ID,LOC_ID,ARRIVAL_TIME,LEAVE_TIME FROM trip_detail where TRIP_DETAIL_ID = ?";
	private static final String GET_ALL_FORTRIP = 
			"SELECT TRIP_DETAIL_ID,TRIP_ID,LOC_ID,ARRIVAL_TIME,LEAVE_TIME FROM trip_detail WHERE TRIP_ID=? AND DATE(ARRIVAL_TIME)=? ORDER BY ARRIVAL_TIME";
	private static final String GET_ALL_TRIPID=
			"SELECT TRIP_DETAIL_ID,TRIP_ID,LOC_ID,ARRIVAL_TIME,LEAVE_TIME "
			+"FROM trip_detail WHERE TRIP_ID=? AND DATE(ARRIVAL_TIME) BETWEEN ? AND ? ORDER BY ARRIVAL_TIME";
	
	@Override
	public void insert(TripDetailVO tripDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, tripDetailVO.getTripId());
			pstmt.setInt(2, tripDetailVO.getLocId());
			pstmt.setTimestamp(3, tripDetailVO.getArrivalTime());
			pstmt.setTimestamp(4, tripDetailVO.getLeaveTime());
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
	public void delete(Integer tripDetailId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, tripDetailId);

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
	public TripDetailVO findByPrimaryKey(Integer tripDetailId) {
		TripDetailVO tripDetailVO =null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, tripDetailId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripDetailVO = new TripDetailVO();
				tripDetailVO.setTripDatailId(rs.getInt("TRIP_DETAIL_ID"));
				tripDetailVO.setTripId(rs.getInt("TRIP_ID"));
				tripDetailVO.setLocId(rs.getInt("LOC_ID"));
				tripDetailVO.setArrivalTime(rs.getTimestamp("ARRIVAL_TIME"));
				tripDetailVO.setLeaveTime(rs.getTimestamp("LEAVE_TIME"));
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
		return tripDetailVO;
	}

	@Override
	public List<TripDetailVO> getAll_ForTRIP(Integer tripId,Date date){
		List<TripDetailVO> list = new ArrayList<TripDetailVO>();
		TripDetailVO tripDetailVO =null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_FORTRIP);
			pstmt.setInt(1, tripId);
			pstmt.setDate(2, date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripDetailVO = new TripDetailVO();
				tripDetailVO.setTripDatailId(rs.getInt("TRIP_DETAIL_ID"));
				tripDetailVO.setTripId(rs.getInt("TRIP_ID"));
				tripDetailVO.setLocId(rs.getInt("LOC_ID"));
				tripDetailVO.setArrivalTime(rs.getTimestamp("ARRIVAL_TIME"));
				tripDetailVO.setLeaveTime(rs.getTimestamp("LEAVE_TIME"));
				
				list.add(tripDetailVO); // Store the row in the list
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
	public void deleteByDate(Integer tripId, Date date) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_DATE);

			pstmt.setInt(1, tripId);
			pstmt.setDate(2, date);

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
	public List<TripDetailVO> getAllByForeignKey(Integer tripId,String startDate,String endDate) {
		List<TripDetailVO> list = new ArrayList<TripDetailVO>();
		TripDetailVO tripDetailVO =null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_TRIPID);
			pstmt.setInt(1, tripId);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				tripDetailVO = new TripDetailVO();
				tripDetailVO.setTripDatailId(rs.getInt("TRIP_DETAIL_ID"));
				tripDetailVO.setTripId(rs.getInt("TRIP_ID"));
				tripDetailVO.setLocId(rs.getInt("LOC_ID"));
				tripDetailVO.setArrivalTime(rs.getTimestamp("ARRIVAL_TIME"));
				tripDetailVO.setLeaveTime(rs.getTimestamp("LEAVE_TIME"));
				
				list.add(tripDetailVO); // Store the row in the list
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
