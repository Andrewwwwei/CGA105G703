package com.actMember.model;

import java.util.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActMemberJDBCDAO implements ActMemberDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
		"INSERT INTO activity_member (user_id,activity_id,activity_notice,evaluation_content,evaluation_score,evaluation_time,member_status) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT * FROM activity_member order by user_id";
	private static final String GET_ONE_STMT = 
		"SELECT * FROM activity_member where user_id = ? AND activity_id = ?";
	private static final String DELETE = 
		"DELETE FROM activity_member where user_id = ?";
	private static final String UPDATE = 
		"UPDATE activity_member set activity_notice=?, evaluation_content=?, evaluation_score=?, evaluation_time=? , member_status=? where user_id = ? and activity_id=?";
	private static final String GET_ONE_MEMBER =
		"INSERT INTO activity_member (user_id,activity_id,evaluation_time) VALUES (?,?,?)"; 
	private static final String GET_NOTICE =
		"INSERT INTO activity_member(user_id,activity_id,activityNotice,evaluation_time) VALUES (? ,? ,? ,?)";
	private static final String GET_BY_ACT = 
		"SELECT * FROM activity_member where activity_id = ?";
	private static final String GET_BY_USERID = 
			"SELECT * FROM activity_member where user_id = ?";
	
	public void getNotice(ActMemberVO actMemberVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_NOTICE);
			
			pstmt.setInt(1, actMemberVO.getUserId());
			pstmt.setInt(2, actMemberVO.getActivityId());
			pstmt.setString(3, actMemberVO.getActivityNotice());
			pstmt.setObject(4, actMemberVO.getEvaluationTime());
			

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
	public void OneMemberInsert(ActMemberVO actMemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_MEMBER);
			
			pstmt.setInt(1, actMemberVO.getUserId());
			pstmt.setInt(2, actMemberVO.getActivityId());
			pstmt.setObject(3, actMemberVO.getEvaluationTime());
			

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
	public void insert(ActMemberVO actMemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, actMemberVO.getUserId());
			pstmt.setInt(2, actMemberVO.getActivityId());
			pstmt.setString(3, actMemberVO.getActivityNotice());
			pstmt.setString(4, actMemberVO.getEvaluationContent());
			pstmt.setObject(5, actMemberVO.getEvaluationScore());
			pstmt.setObject(6, actMemberVO.getEvaluationTime());
			pstmt.setInt(7, actMemberVO.getMemberStatus());
			

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
	public void update(ActMemberVO actMemberVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, actMemberVO.getActivityNotice());
			pstmt.setString(2, actMemberVO.getEvaluationContent());
			pstmt.setInt(3, actMemberVO.getEvaluationScore());
			pstmt.setObject(4, actMemberVO.getEvaluationTime());
			pstmt.setInt(5, actMemberVO.getMemberStatus());
			pstmt.setInt(6, actMemberVO.getUserId());
			pstmt.setInt(7, actMemberVO.getActivityId());
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
	public void delete(Integer userId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, userId);

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
	public ActMemberVO findByPrimaryKey(Integer userId , Integer activityId) {

		ActMemberVO actMemberVO = new ActMemberVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, userId);
			pstmt.setInt(2, activityId);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actMemberVO.setUserId(rs.getInt("user_id"));
				actMemberVO.setActivityId(rs.getInt("activity_id"));
				actMemberVO.setActivityNotice(rs.getString("activity_notice"));
				actMemberVO.setEvaluationContent(rs.getString("evaluation_content"));
				actMemberVO.setEvaluationScore(rs.getInt("evaluation_score"));
				actMemberVO.setMemberStatus(rs.getInt("member_status"));
				actMemberVO.setEvaluationTime(rs.getObject("EVALUATION_TIME", LocalDateTime.class));
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
		return actMemberVO;
	}

	@Override
	public List<ActMemberVO> getAll() {
		List<ActMemberVO> list = new ArrayList<ActMemberVO>();
		ActMemberVO actMemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actMemberVO = new ActMemberVO();
				actMemberVO.setUserId(rs.getInt("user_id"));
				actMemberVO.setActivityId(rs.getInt("activity_id"));
				actMemberVO.setActivityNotice(rs.getString("activity_notice"));
				actMemberVO.setEvaluationContent(rs.getString("evaluation_content"));
				actMemberVO.setEvaluationScore(rs.getInt("evaluation_score"));
				actMemberVO.setMemberStatus(rs.getInt("member_status"));
				actMemberVO.setEvaluationTime(rs.getObject("evaluation_time", LocalDateTime.class));
				list.add(actMemberVO); // Store the row in the list
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
	public List<ActMemberVO> getThisActMembers(Integer activityId) {
		List<ActMemberVO> list = new ArrayList<ActMemberVO>();
		ActMemberVO actMemberVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ACT);
			pstmt.setInt(1, activityId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				actMemberVO = new ActMemberVO();
				actMemberVO.setUserId(rs.getInt("user_id"));
				actMemberVO.setActivityId(rs.getInt("activity_id"));
				actMemberVO.setActivityNotice(rs.getString("activity_notice"));
				actMemberVO.setEvaluationContent(rs.getString("evaluation_content"));
				actMemberVO.setEvaluationScore(rs.getInt("evaluation_score"));
				actMemberVO.setMemberStatus(rs.getInt("member_status"));
				actMemberVO.setEvaluationTime(rs.getObject("evaluation_time", LocalDateTime.class));
				list.add(actMemberVO); // Store the row in the list
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
	public List<ActMemberVO> getByUserId(Integer userId) {
		List<ActMemberVO> list = new ArrayList<ActMemberVO>();
		ActMemberVO actMemberVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_USERID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				actMemberVO = new ActMemberVO();
				actMemberVO.setUserId(rs.getInt("user_id"));
				actMemberVO.setActivityId(rs.getInt("activity_id"));
				actMemberVO.setActivityNotice(rs.getString("activity_notice"));
				actMemberVO.setEvaluationContent(rs.getString("evaluation_content"));
				actMemberVO.setEvaluationScore(rs.getInt("evaluation_score"));
				actMemberVO.setMemberStatus(rs.getInt("member_status"));
				actMemberVO.setEvaluationTime(rs.getObject("evaluation_time", LocalDateTime.class));
				list.add(actMemberVO); // Store the row in the list
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

	public static void main(String[] args) {

		ActMemberJDBCDAO dao = new ActMemberJDBCDAO();

		// 新增
//		ActMemberVO actMemberVO = new ActMemberVO();
//		actMemberVO.setUserId(2);
//		actMemberVO.setActivityId(2);
//		actMemberVO.setActivityNotice("活動開始");
//		actMemberVO.setEvaluationContent("活動不錯唷");
//		actMemberVO.setEvaluationScore(3);
//		actMemberVO.setEvaluationTime(java.sql.Date.valueOf("2023-02-05"));
//		actMemberVO.setsetMemberStatus();
//		dao.insert(actMemberVO);

//		// 修改
//		ActMemberVO actMemberVO1 = new ActMemberVO();
//		actMemberVO1.setUserId(1);
//		actMemberVO1.setActivityId(1);
//		actMemberVO1.setActivityNotice("活動結束");
//		actMemberVO1.setEvaluationContent("");
//		actMemberVO1.setEvaluationScore(1);
//		actMemberVO1.setEvaluationTime(LocalDateTime.now());
//		actMemberVO1.setMemberStatus(0);
//		dao.update(actMemberVO1);
//
//		// 刪除
//		dao.delete(1);
//
//		// 查詢
//		ActMemberVO actMemberVO2 = dao.findByPrimaryKey(2,2);
//		System.out.println(actMemberVO2.getUserId()+",");
//		System.out.println(actMemberVO2.getActivityId() + ",");
//		System.out.println(actMemberVO2.getActivityNotice() + ",");
//		System.out.println(actMemberVO2.getEvaluationContent() + ",");
//		System.out.println(actMemberVO2.getEvaluationScore() + ",");
//		System.out.println(actMemberVO2.getEvaluationTime() + ",");
//		System.out.println(actMemberVO2.getMemberStatus() + ",");
//		System.out.println("---------------------");

//		//查詢
//		List<ActMemberVO> list = dao.getAll();
//		for (ActMemberVO actMember : list) {
//			System.out.println(actMember.getUserId());
//			System.out.println(actMember.getActivityId());
//			System.out.println(actMember.getActivityNotice());
//			System.out.println(actMember.getEvaluationContent());
//			System.out.println(actMember.getEvaluationScore());
//			System.out.println(actMember.getEvaluationTime());
//			System.out.println(actMember.getMemberStatus());
//			System.out.println("---------------------");
//		}
		
		
	}




}