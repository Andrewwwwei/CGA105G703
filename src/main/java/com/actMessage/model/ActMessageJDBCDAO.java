package com.actMessage.model;

import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActMessageJDBCDAO implements ActMessageDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO activity_message (activity_id,user_id,message_content,message_content_time) VALUES (?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT message_id,activity_id,user_id,message_content,message_content_time FROM activity_message order by message_id";
	private static final String GET_ONE_STMT = "SELECT message_id,activity_id,user_id,message_content,message_content_time FROM activity_message where message_id = ?";
	private static final String DELETE = "DELETE FROM activity_message where message_id = ?";
	private static final String UPDATE = "UPDATE activity_message set activity_id=?, user_id=?, message_content=?, message_content_time=? where message_id = ?";

	@Override
	public void insert(ActMessageVO actMessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, actMessageVO.getActivityId());
			pstmt.setInt(2, actMessageVO.getUserId());
			pstmt.setString(3, actMessageVO.getMessageContent());
			pstmt.setObject(4, actMessageVO.getMessageContentTime());

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
	public void update(ActMessageVO actMessageVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, actMessageVO.getActivityId());
			pstmt.setInt(2, actMessageVO.getUserId());
			pstmt.setString(3, actMessageVO.getMessageContent());
			pstmt.setObject(4, actMessageVO.getMessageContentTime());
			pstmt.setInt(5, actMessageVO.getMessageId());
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
	public void delete(Integer messageId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, messageId);

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
	public ActMessageVO findByPrimaryKey(Integer messageId) {

		ActMessageVO actMessageVO = null ;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, messageId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actMessageVO = new ActMessageVO();
				actMessageVO.setMessageId(rs.getInt("message_id"));
				actMessageVO.setActivityId(rs.getInt("activity_id"));
				actMessageVO.setUserId(rs.getInt("user_id"));
				actMessageVO.setMessageContent(rs.getString("message_content"));
				
				LocalDateTime messageContentTime = rs.getObject("message_content_time", LocalDateTime.class);
				actMessageVO.setMessageContentTime(messageContentTime);
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String messageContentTimeStr = messageContentTime.format(format);
				actMessageVO.setMessageContentTimeStr(messageContentTimeStr);
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
		return actMessageVO;
	}

	@Override
	public List<ActMessageVO> getAll() {
		List<ActMessageVO> list = new ArrayList<ActMessageVO>();
		ActMessageVO actMessageVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actMessageVO = new ActMessageVO();
				actMessageVO.setMessageId(rs.getInt("message_id"));
				actMessageVO.setActivityId(rs.getInt("activity_id"));
				actMessageVO.setUserId(rs.getInt("user_id"));
				actMessageVO.setMessageContent(rs.getString("message_content"));
				
				LocalDateTime messageContentTime = rs.getObject("message_content_time", LocalDateTime.class);
				actMessageVO.setMessageContentTime(messageContentTime);
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String messageContentTimeStr = messageContentTime.format(format);
				actMessageVO.setMessageContentTimeStr(messageContentTimeStr);
				list.add(actMessageVO); // Store the row in the list
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

		ActMessageJDBCDAO dao = new ActMessageJDBCDAO();

		// 新增
//		ActMessageVO actMessageVO = new ActMessageVO();
//		actMessageVO.setActivityId(1);
//		actMessageVO.setUserId(1);
//		actMessageVO.setMessageContent("123");
//		actMessageVO.setMessageContentTime(java.sql.Date.valueOf("2023-03-05"));
//
//		dao.insert(actMessageVO);

//		// 修改
//		ActMessageVO actMessageVO1 = new ActMessageVO();
//		actMessageVO1.setActivityId(1);
//		actMessageVO1.setUserId(1);
//		actMessageVO1.setMessageContent("");
//		actMessageVO1.setMessageContentTime(java.sql.Date.valueOf("2023-03-05"));
//
//		dao.update(actMessageVO1);
//
//		// 刪除
//		dao.delete(1);
//
//		// 查詢
//		ActMessageVO actMessageVO2 = dao.findByPrimaryKey(1);
//		System.out.println(actMessageVO2.getMessageId());
//		System.out.println(actMessageVO2.getActivityId());
//		System.out.println(actMessageVO2.getUserId());
//		System.out.println(actMessageVO2.getMessageContent());
//		System.out.println(actMessageVO2.getMessageContentTime());
//		System.out.println("---------------------");
//
//		// 查詢
		List<ActMessageVO> list = dao.getAll();
		for (ActMessageVO actMessage : list) {
			System.out.println(actMessage.getMessageId());
			System.out.println(actMessage.getActivityId());
			System.out.println(actMessage.getUserId());
			System.out.println(actMessage.getMessageContent());
			System.out.println(actMessage.getMessageContentTime());
			System.out.println("---------------------");
		}
	}
}