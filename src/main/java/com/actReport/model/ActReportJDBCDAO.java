package com.actReport.model;

import java.util.*;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ActReportJDBCDAO implements ActReportDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
		"INSERT INTO activity_report (activity_id,report_user_id,emp_no,report_content,report_status,report_time,report_matter,report_finish_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT activity_report_id,activity_id,report_user_id,emp_no,report_content,report_status,report_time,report_matter,report_finish_time FROM activity_report order by activity_report_id";
	private static final String GET_ONE_STMT = 
		"SELECT activity_report_id,activity_id,report_user_id,emp_no,report_content,report_status,report_time,report_matter,report_finish_time FROM activity_report where activity_report_id = ?";
	private static final String DELETE = 
		"DELETE FROM activity_report where activity_report_id = ?";
	private static final String UPDATE = 
		"UPDATE activity_report set activity_id=?, report_user_id=?, emp_no=?, report_content=?, report_status=?, report_time=?, report_matter=?, report_finish_time=? where activity_report_id = ?";

	@Override
	public void insert(ActReportVO actReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

		
			pstmt.setInt(1, actReportVO.getActivityId());
			pstmt.setInt(2, actReportVO.getReportUserId());
			pstmt.setInt(3, actReportVO.getEmpNo());
			pstmt.setString(4, actReportVO.getReportContent());
			pstmt.setInt(5, actReportVO.getReportStatus());
			pstmt.setObject(6, actReportVO.getReportTime());
			pstmt.setInt(7, actReportVO.getReportMatter());
			pstmt.setObject(8, actReportVO.getReportFinishTime());

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
	public void update(ActReportVO actReportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

		
			pstmt.setInt(1, actReportVO.getActivityId());
			pstmt.setInt(2, actReportVO.getReportUserId());
			pstmt.setInt(3, actReportVO.getEmpNo());
			pstmt.setString(4, actReportVO.getReportContent());
			pstmt.setInt(5, actReportVO.getReportStatus());
			pstmt.setObject(6, actReportVO.getReportTime());
			pstmt.setInt(7, actReportVO.getReportMatter());
			pstmt.setObject(8, actReportVO.getReportFinishTime());
			pstmt.setInt(9, actReportVO.getActivityReportId());
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
	public void delete(Integer activityReportId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, activityReportId);

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
	public ActReportVO findByPrimaryKey(Integer activityReportId) {

		ActReportVO actReportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, activityReportId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				actReportVO = new ActReportVO();
				actReportVO.setActivityReportId(rs.getInt("activity_report_id"));
				actReportVO.setActivityId(rs.getInt("activity_id"));
				actReportVO.setReportUserId(rs.getInt("report_user_id"));
				actReportVO.setEmpNo(rs.getInt("emp_no"));
				actReportVO.setReportContent(rs.getString("report_content"));
				actReportVO.setReportStatus(rs.getInt("report_status"));
				actReportVO.setReportMatter(rs.getInt("report_matter"));
				//系統自動設定當前時間
				LocalDateTime reportTime = rs.getObject("report_time", LocalDateTime.class); 
				actReportVO.setReportTime(reportTime);
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); //格式化處理
				String reportTimeStr = reportTime.format(format); // 取值轉成str
				actReportVO.setReportTimeStr(reportTimeStr);
				//系統自動設定當前時間
				LocalDateTime reportFinishTime = rs.getObject("report_finish_time", LocalDateTime.class);
				actReportVO.setReportTime(reportFinishTime);
				DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");//格式化處理
				String reportFinishTimeStr = reportFinishTime.format(format2);
				actReportVO.setReportTimeStr(reportFinishTimeStr);

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
		return actReportVO;
	}

	@Override
	public List<ActReportVO> getAll() {
		List<ActReportVO> list = new ArrayList<ActReportVO>();
		ActReportVO actReportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				actReportVO = new ActReportVO();
				actReportVO.setActivityReportId(rs.getInt("activity_report_id"));
				actReportVO.setActivityId(rs.getInt("activity_id"));
				actReportVO.setReportUserId(rs.getInt("report_user_id"));
				actReportVO.setEmpNo(rs.getInt("emp_no"));
				actReportVO.setReportContent(rs.getString("report_content"));
				actReportVO.setReportStatus(rs.getInt("report_status"));
				actReportVO.setReportMatter(rs.getInt("report_matter"));
				//系統自動設定當前時間
				LocalDateTime reportTime = rs.getObject("report_time", LocalDateTime.class); 
				actReportVO.setReportTime(reportTime);
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); //格式化處理
				String reportTimeStr = reportTime.format(format); // 取值轉成str
				actReportVO.setReportTimeStr(reportTimeStr);
				//系統自動設定當前時間
				LocalDateTime reportFinishTime = rs.getObject("report_finish_time", LocalDateTime.class);
				actReportVO.setReportFinishTime(reportFinishTime);
				DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");//格式化處理
				String reportFinishTimeStr = reportFinishTime.format(format2);
				actReportVO.setReportFinishTimeStr(reportFinishTimeStr);
				
				list.add(actReportVO); // Store the row in the list
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

		ActReportJDBCDAO dao = new ActReportJDBCDAO();

		// 新增
//		ActReportVO actReportVO = new ActReportVO();
//		actReportVO.setActivityId(1);
//		actReportVO.setReportUserId(1);
//		actReportVO.setEmpNo(300000);
//		actReportVO.setReportContent("");
//		actReportVO.setReportStatus(1);
//		actReportVO.setReportTime(java.sql.Date.valueOf("2023-04-05"));
//		actReportVO.setReportMatter(1);
//		actReportVO.setReportFinishTime(java.sql.Date.valueOf("2023-04-10"));
//		
//		dao.insert(actReportVO);
//
//		// 修改
//		ActReportVO actReportVO1 = new ActReportVO();
//		actReportVO1.setActivityId(1);
//		actReportVO1.setReportUserId(1);
//		actReportVO1.setEmpNo(1);
//		actReportVO1.setReportContent("test");
//		actReportVO1.setReportStatus(5);
//		actReportVO1.setReportTime(java.sql.Date.valueOf("2023-04-05"));
//		actReportVO1.setReportMatter(6);
//		actReportVO1.setReportFinishTime(java.sql.Date.valueOf("2023-04-10"));
//		actReportVO1.setActivityReportId(11);
//		dao.update(actReportVO1);
		
		
		// 刪除
//		dao.delete(7014);

		// 查詢
//		ActReportVO actReportVO2 = dao.findByPrimaryKey(11);
//		System.out.print(actReportVO2.getActivityReportId() + ",");
//		System.out.print(actReportVO2.getActivityId() + ",");
//		System.out.print(actReportVO2.getReportUserId() + ",");
//		System.out.print(actReportVO2.getEmpNo() + ",");
//		System.out.print(actReportVO2.getReportContent() + ",");
//		System.out.print(actReportVO2.getReportStatus() + ",");
//		System.out.print(actReportVO2.getReportTime() + ",");
//		System.out.print(actReportVO2.getReportMatter() + ",");
//		System.out.println(actReportVO2.getReportFinishTime());
//		System.out.println("---------------------");

//		//查詢
		List<ActReportVO> list = dao.getAll();
		for (ActReportVO actReport : list) {
			System.out.print(actReport.getActivityReportId() + ",");
			System.out.print(actReport.getActivityId() + ",");
			System.out.print(actReport.getReportUserId() + ",");
			System.out.print(actReport.getEmpNo() + ",");
			System.out.print(actReport.getReportContent() + ",");
			System.out.print(actReport.getReportStatus() + ",");
			System.out.print(actReport.getReportTime() + ",");
			System.out.print(actReport.getReportMatter() + ",");
			System.out.println(actReport.getReportFinishTime());
			System.out.println("---------------------");
		}
	}

}