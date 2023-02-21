package com.act.model;

import java.util.*;

import com.actMember.model.ActMemberVO;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActJDBCDAO implements ActDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO activity (user_id,activity_title,activity_content,current_number,max_people,registration_time,registration_end,"
			+ "trip_start,trip_end,activity_the_cover,activity_state) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT activity_id , user_id,activity_title,activity_content,current_number,max_people,registration_time,registration_end,trip_start,trip_end,activity_the_cover,activity_state FROM activity order by activity_id";
	private static final String GET_ONE_STMT = "SELECT activity_id , user_id,activity_title,activity_content,current_number,max_people,registration_time,registration_end,trip_start,trip_end,activity_the_cover,activity_state FROM activity where activity_id = ?";
	private static final String GET_BY_LEADER = "SELECT activity_id , user_id,activity_title,activity_content,current_number,max_people,registration_time,registration_end,trip_start,trip_end,activity_the_cover,activity_state FROM activity where user_id = ?";
	private static final String DELETE = "DELETE FROM activity where activity_id = ?";
	private static final String UPDATE = "UPDATE activity set user_id=?, activity_title=? , activity_content=? , current_number=? , max_people=? , registration_time=? , registration_end=? , trip_start=? , trip_end=? , activity_the_cover=? , activity_state=? where activity_id = ?";
//	private static final String GET_ONE_TRIP = "SELECT activity_id , trip_id, user_id,activity_title,activity_content,current_number,max_people,registration_time,registration_end,trip_start,trip_end,activity_the_cover,activity_state FROM activity where trip_id = ?";
	private static final String GET_ONE_ACTCONTENT ="SELECT * from activity where activity_id = ?";
	private static final String GET_IMG = "SELECT FROM activity where activity_the_cover = ?";
//	private static final String GET_BY_ACTIVITYLOCATION = "SELECT * from activity where activity_location = '?'";
//	private static final String GET_ACT_ByActivityId_STMT ="SELECT userId, activityId, activityNotice, evaluationContent, evaluationScore, evaluationTime from member where userid = ? order by userid";
	
	@Override
	public ActVO getOneActContent(Integer activityId) {
		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ACTCONTENT);

			pstmt.setInt(1, activityId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actVO = new ActVO();
				actVO.setActivityId(rs.getInt("activity_id"));
				actVO.setTripId(rs.getInt("trip_id"));
				actVO.setUserId(rs.getInt("user_id"));
				actVO.setActivityTitle(rs.getString("activity_title"));
				actVO.setActivityContent(rs.getString("activity_content"));
				actVO.setCurrentNumber(rs.getInt("current_number"));
				actVO.setMaxPeople(rs.getInt("max_people"));
				actVO.setRegistrationTime(rs.getDate("registration_time"));
				actVO.setRegistrationEnd(rs.getDate("registration_end"));
				actVO.setTripStart(rs.getDate("trip_start"));
				actVO.setTripEnd(rs.getDate("trip_end"));
				actVO.setActivityTheCover(rs.getBytes("activity_the_cover")); // ActVO�h�ؤF�@�ӫغc�l116��
				actVO.setActivityState(rs.getInt("activity_state"));
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
		return actVO;
	}
	


	
//	@Override
//	public List<ActVO> getOneTrip(Integer tripId) {
//		
//		List<ActVO> actVOList = new ArrayList<ActVO>();
//		ActVO actVO = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_ONE_TRIP);
//
//			pstmt.setInt(1,tripId);
//
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				actVO = new ActVO();
//				actVO.setActivityId(rs.getInt("activity_id"));
//				actVO.setTripId(rs.getInt("trip_id"));
//				actVO.setUserId(rs.getInt("user_id"));
//				actVO.setActivityTitle(rs.getString("activity_title"));
//				actVO.setActivityContent(rs.getString("activity_content"));
//				actVO.setCurrentNumber(rs.getInt("current_number"));
//				actVO.setMaxPeople(rs.getInt("max_people"));
//				actVO.setRegistrationTime(rs.getDate("registration_time"));
//				actVO.setRegistrationEnd(rs.getDate("registration_end"));
//				actVO.setTripStart(rs.getDate("trip_start"));
//				actVO.setTripEnd(rs.getDate("trip_end"));
//				actVO.setActivityTheCover(rs.getBytes("activity_the_cover")); // ActVO�h�ؤF�@�ӫغc�l116��
//				actVO.setActivityState(rs.getInt("activity_state"));
//				actVOList.add(actVO);
//			}
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return actVOList;
//	
//	}

	
	
	@Override
	public void insert(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

//			pstmt.setInt(1, actVO.getTripId());
			pstmt.setInt(1, actVO.getUserId());
			pstmt.setString(2, actVO.getActivityTitle());
			pstmt.setString(3, actVO.getActivityContent());
			pstmt.setInt(4, actVO.getCurrentNumber()); 
			pstmt.setInt(5, actVO.getMaxPeople()); 
			pstmt.setDate(6, actVO.getRegistrationTime());
			pstmt.setDate(7, actVO.getRegistrationEnd());
			pstmt.setDate(8, actVO.getTripStart());
			pstmt.setDate(9, actVO.getTripEnd());
			pstmt.setBytes(10, actVO.getActivityTheCover());
			pstmt.setInt(11, actVO.getActivityState());

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
	public void update(ActVO actVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

//			pstmt.setInt(1, actVO.getTripId());
			pstmt.setInt(1, actVO.getUserId());
			pstmt.setString(2, actVO.getActivityTitle());
			pstmt.setString(3, actVO.getActivityContent());
			pstmt.setInt(4, actVO.getCurrentNumber()); // ActVo設定建構子在100行
			pstmt.setInt(5, actVO.getMaxPeople()); // ActVo設定建構子在104行
			pstmt.setDate(6, actVO.getRegistrationTime());
			pstmt.setDate(7, actVO.getRegistrationEnd());
			pstmt.setDate(8, actVO.getTripStart());
			pstmt.setDate(9, actVO.getTripEnd());
			pstmt.setBytes(10, actVO.getActivityTheCover());
			pstmt.setInt(11, actVO.getActivityState());
			pstmt.setInt(12, actVO.getActivityId());

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
	public void delete(Integer activityId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, activityId);

			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ActVO findByPrimaryKey(Integer activityId) {

		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activityId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actVO = new ActVO();
				actVO.setActivityId(rs.getInt("activity_id"));
//				actVO.setTripId(rs.getInt("trip_id"));
				actVO.setUserId(rs.getInt("user_id"));
				actVO.setActivityTitle(rs.getString("activity_title"));
				actVO.setActivityContent(rs.getString("activity_content"));
				actVO.setCurrentNumber(rs.getInt("current_number"));
				actVO.setMaxPeople(rs.getInt("max_people"));
				actVO.setRegistrationTime(rs.getDate("registration_time"));
				actVO.setRegistrationEnd(rs.getDate("registration_end"));
				actVO.setTripStart(rs.getDate("trip_start"));
				actVO.setTripEnd(rs.getDate("trip_end"));
				actVO.setActivityTheCover(rs.getBytes("activity_the_cover")); // ActVO�h�ؤF�@�ӫغc�l116��
				actVO.setActivityState(rs.getInt("activity_state"));
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
		return actVO;
	}


	@Override
	public List<ActVO> getAll() {

		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actVO = new ActVO();
				actVO.setActivityId(rs.getInt("activity_id"));
//				actVO.setTripId(rs.getInt("trip_id"));
				actVO.setUserId(rs.getInt("user_id"));
				actVO.setActivityTitle(rs.getString("activity_title"));
				actVO.setActivityContent(rs.getString("activity_content"));
				actVO.setCurrentNumber(rs.getInt("current_number"));
				actVO.setMaxPeople(rs.getInt("max_people"));
				actVO.setRegistrationTime(rs.getDate("registration_time"));
				actVO.setRegistrationEnd(rs.getDate("registration_end"));
				actVO.setTripStart(rs.getDate("trip_start"));
				actVO.setTripEnd(rs.getDate("trip_end"));
				actVO.setActivityTheCover(rs.getBytes("activity_the_cover")); // ActVO�h�ؤF�@�ӫغc�l116��
				actVO.setActivityState(rs.getInt("activity_state"));
//				actVO.setActivityLocation(rs.getString("activity_location"));
				list.add(actVO); // Store the row in the list
			}
			;
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ActVO> getLeaderAct(Integer userId) {
		
		List<ActVO> list = new ArrayList<ActVO>();
		ActVO actVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_LEADER);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				actVO = new ActVO();
				actVO.setActivityId(rs.getInt("activity_id"));
//				actVO.setTripId(rs.getInt("trip_id"));
				actVO.setUserId(rs.getInt("user_id"));
				actVO.setActivityTitle(rs.getString("activity_title"));
				actVO.setActivityContent(rs.getString("activity_content"));
				actVO.setCurrentNumber(rs.getInt("current_number"));
				actVO.setMaxPeople(rs.getInt("max_people"));
				actVO.setRegistrationTime(rs.getDate("registration_time"));
				actVO.setRegistrationEnd(rs.getDate("registration_end"));
				actVO.setTripStart(rs.getDate("trip_start"));
				actVO.setTripEnd(rs.getDate("trip_end"));
				actVO.setActivityTheCover(rs.getBytes("activity_the_cover")); // ActVO�h�ؤF�@�ӫغc�l116��
				actVO.setActivityState(rs.getInt("activity_state"));
//				actVO.setActivityLocation(rs.getString("activity_location"));
				list.add(actVO); // Store the row in the list
			}
			;
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	
//	@Override
//	public List<ActVO> getByActivityLocation(String activityLocation){
//		List<ActVO> getActivityTitle = new ArrayList<ActVO>();
//		ActVO actVO = null;
//		Connection con =null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(GET_BY_ACTIVITYLOCATION);
//
//			pstmt.setString(1,activityLocation);
//
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				actVO.setActivityId(rs.getInt("activity_id"));
//				actVO.setTripId(rs.getInt("trip_id"));
//				actVO.setUserId(rs.getInt("user_id"));
//				actVO.setActivityTitle(rs.getString("activity_title"));
//				actVO.setActivityContent(rs.getString("activity_content"));
//				actVO.setCurrentNumber(rs.getInt("current_number"));
//				actVO.setMaxPeople(rs.getInt("max_people"));
//				actVO.setRegistrationTime(rs.getDate("registration_time"));
//				actVO.setRegistrationEnd(rs.getDate("registration_end"));
//				actVO.setTripStart(rs.getDate("trip_start"));
//				actVO.setTripEnd(rs.getDate("trip_end"));
//				actVO.setActivityTheCover(rs.getBytes("activity_the_cover")); // ActVO�h�ؤF�@�ӫغc�l116��
//				actVO.setActivityState(rs.getInt("activity_state"));
////				actVO.setActivityLocation(rs.getString("activity_location"));
//				getActivityTitle.add(actVO);
//	
//			}
//
//		}catch(ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//		return getActivityTitle;
//	}
	
	public static void main(String[] args) {

		ActJDBCDAO dao = new ActJDBCDAO();

		// 新增
//		ActVO actVO = new ActVO();
////    actVO.setTripId(1);
//		actVO.setUserId(1);
//		actVO.setActivityTitle("");
//		actVO.setActivityContent("");
//		actVO.setCurrentNumber(1);
//		actVO.setMaxPeople(1);
//		actVO.setRegistrationTime(java.sql.Date.valueOf("2023-01-05"));
//		actVO.setRegistrationEnd(java.sql.Date.valueOf("2023-01-10"));
//		actVO.setTripStart(java.sql.Date.valueOf("2023-01-15"));
//		actVO.setTripEnd(java.sql.Date.valueOf("2023-01-20"));
//		actVO.setActivityTheCover(null);
//		actVO.setActivityState(1);
//
//		dao.insert(actVO);

		// 修改
//		ActVO actVO1 = new ActVO();
//		actVO1.setActivityId(1);
////	actVO1.setTripId(1);
//		actVO1.setUserId(1);
//		actVO1.setActivityTitle("999999");
//		actVO1.setActivityContent("9999999");
//		actVO1.setCurrentNumber(1);
//		actVO1.setMaxPeople(1);
//		actVO1.setRegistrationTime(java.sql.Date.valueOf("2023-01-05"));
//		actVO1.setRegistrationEnd(java.sql.Date.valueOf("2023-01-10"));
//		actVO1.setTripStart(java.sql.Date.valueOf("2023-01-15"));
//		actVO1.setTripEnd(java.sql.Date.valueOf("2023-01-20"));
//		actVO1.setActivityTheCover(null);
//		actVO1.setActivityState(1);
//		dao.update(actVO1);
//
//		// 刪除
////		dao.delete(2);
//		// 查詢
//		ActVO actVO2 = dao.findByPrimaryKey(2);
//		System.out.println(actVO2.getActivityId());
////	System.out.println(actVO2.getTripId());
//		System.out.println(actVO2.getUserId());
//		System.out.println(actVO2.getActivityTitle());
//		System.out.println(actVO2.getActivityContent());
//		System.out.println(actVO2.getCurrentNumber());
//		System.out.println(actVO2.getMaxPeople());
//		System.out.println(actVO2.getRegistrationTime());
//		System.out.println(actVO2.getRegistrationEnd());
//		System.out.println(actVO2.getTripStart());
//		System.out.println(actVO2.getTripEnd());
//		System.out.println(actVO2.getActivityTheCover());
//		System.out.println(actVO2.getActivityState());
//		System.out.println("---------------------");
//		
		// 查詢
		List<ActVO> list = dao.getAll();
		for(ActVO act : list) {
		System.out.println(act.getActivityId());
//		System.out.println(act.getTripId());
		System.out.println(act.getUserId());
		System.out.println(act.getActivityTitle());
		System.out.println(act.getActivityContent());
		System.out.println(act.getCurrentNumber());
		System.out.println(act.getMaxPeople());
		System.out.println(act.getRegistrationTime());
		System.out.println(act.getRegistrationEnd());
		System.out.println(act.getTripStart());
		System.out.println(act.getTripEnd());
		System.out.println(act.getActivityTheCover());
		System.out.println(act.getActivityState());
		System.out.println("================================");
		}
	}




	@Override
	public List<ActVO> getOneTrip(Integer tripId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ActVO> getImgs(Integer activityId){
		
		List<ActVO> actVOList = new ArrayList<ActVO>();
		ActVO actVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_IMG);

			pstmt.setInt(1, activityId);
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				actVO = new ActVO();
				actVO.setActivityId(rs.getInt("activity_id"));
				actVO.setTripId(rs.getInt("trip_id"));
				actVO.setUserId(rs.getInt("user_id"));
				actVO.setActivityTitle(rs.getString("activity_title"));
				actVO.setActivityContent(rs.getString("activity_content"));
				actVO.setCurrentNumber(rs.getInt("current_number"));
				actVO.setMaxPeople(rs.getInt("max_people"));
				actVO.setRegistrationTime(rs.getDate("registration_time"));
				actVO.setRegistrationEnd(rs.getDate("registration_end"));
				actVO.setTripStart(rs.getDate("trip_start"));
				actVO.setTripEnd(rs.getDate("trip_end"));
				actVO.setActivityTheCover(rs.getBytes("activity_the_cover")); // ActVO�h�ؤF�@�ӫغc�l116��
				actVO.setActivityState(rs.getInt("activity_state"));
				actVOList.add(actVO);
			}
			
		}catch (ClassNotFoundException e) {
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
		return actVOList;
		
	}









	







	

	
}