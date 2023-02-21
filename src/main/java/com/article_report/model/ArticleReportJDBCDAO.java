package com.article_report.model;

import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

public class ArticleReportJDBCDAO implements ArticleReportDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String GET_ONE_STMT = "select art_rp_id, user_id, art_id, rp_reason, emp_no, rp_time, rp_done_time, rp_status, rp_note,  rp_content from article_report  where art_rp_id = ?";
	private static final String GET_ALL_STMT = "select art_rp_id, user_id, art_id, rp_reason, emp_no, rp_time, rp_done_time, rp_status, rp_note,  rp_content  from article_report order by rp_done_time desc";
	private static final String GET_ALL_SHOW_STATUS_STMT = "select art_rp_id, user_id, art_id, rp_reason, emp_no, rp_time, rp_done_time, rp_status, rp_note,  rp_content  from article_report where  rp_status=0  or   rp_status=2  order by rp_done_time desc";
	private static final String INSERT_STMT = "insert into article_report ( user_id, rp_reason,  rp_content, rp_status, art_id ) values ( ?,?,?,?,?);";
	private static final String UPDATE = "update article_report set user_id=?, art_id=?, rp_reason=?, rp_status=?, rp_note=?, rp_content=? where art_rp_id = ?";

	@Override
	public ArticleReportVO findByPrimaryKey(Integer artRpId) {

		ArticleReportVO articlereportVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, artRpId);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				articlereportVO = new ArticleReportVO();
				articlereportVO.setArtRpId(rs.getInt("art_rp_id"));
				articlereportVO.setUserId(rs.getInt("user_id"));
				articlereportVO.setArtId(rs.getInt("art_id"));
				articlereportVO.setRpReason(rs.getInt("rp_reason"));
				articlereportVO.setEmpno(rs.getInt("emp_no"));
				articlereportVO.setRpTime(rs.getObject("rp_time", LocalDateTime.class));
				articlereportVO.setRpDoneTime(rs.getObject("rp_done_time", LocalDateTime.class));
				articlereportVO.setRpStatus(rs.getInt("rp_status"));
				articlereportVO.setRpNote(rs.getString("rp_note"));
				articlereportVO.setRpContent(rs.getString("rp_content"));
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
		return articlereportVO;
	}

	@Override
	public List<ArticleReportVO> getAll() {
		List<ArticleReportVO> list = new ArrayList<ArticleReportVO>();
		ArticleReportVO articlereportVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articlereportVO = new ArticleReportVO();
				articlereportVO.setArtRpId(rs.getInt("art_rp_id"));
				articlereportVO.setUserId(rs.getInt("user_id"));
				articlereportVO.setArtId(rs.getInt("art_id"));
				articlereportVO.setRpReason(rs.getInt("rp_reason"));
				articlereportVO.setEmpno(rs.getInt("emp_no"));
				articlereportVO.setRpTime(rs.getObject("rp_time", LocalDateTime.class));
				articlereportVO.setRpDoneTime(rs.getObject("rp_done_time", LocalDateTime.class));
				articlereportVO.setRpStatus(rs.getInt("rp_status"));
				articlereportVO.setRpNote(rs.getString("rp_note"));
				articlereportVO.setRpContent(rs.getString("rp_content"));
				list.add(articlereportVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<ArticleReportVO> getAllShowStatus() {
		List<ArticleReportVO> list = new ArrayList<ArticleReportVO>();
		ArticleReportVO articlereportVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_SHOW_STATUS_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				articlereportVO = new ArticleReportVO();
				articlereportVO.setArtRpId(rs.getInt("art_rp_id"));
				articlereportVO.setUserId(rs.getInt("user_id"));
				articlereportVO.setArtId(rs.getInt("art_id"));
				articlereportVO.setRpReason(rs.getInt("rp_reason"));
				articlereportVO.setEmpno(rs.getInt("emp_no"));
				articlereportVO.setRpTime(rs.getObject("rp_time", LocalDateTime.class));
				articlereportVO.setRpDoneTime(rs.getObject("rp_done_time", LocalDateTime.class));
				articlereportVO.setRpStatus(rs.getInt("rp_status"));
				articlereportVO.setRpNote(rs.getString("rp_note"));
				articlereportVO.setRpContent(rs.getString("rp_content"));
				list.add(articlereportVO); // Store the row in the list
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void insert(ArticleReportVO articlereportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
	
			pstmt.setInt(1, articlereportVO.getUserId());
			pstmt.setInt(2, articlereportVO.getRpReason());
			pstmt.setString(3, articlereportVO.getRpContent());
			pstmt.setInt(4, articlereportVO.getRpStatus());
			pstmt.setInt(5, articlereportVO.getArtId());
			
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
	public void update(ArticleReportVO articlereportVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articlereportVO.getUserId());
			pstmt.setInt(2, articlereportVO.getArtId());
			pstmt.setInt(3, articlereportVO.getRpReason());
			pstmt.setInt(4, articlereportVO.getRpStatus());
			pstmt.setString(5, articlereportVO.getRpNote());
			pstmt.setString(6, articlereportVO.getRpContent());
			pstmt.setInt(7, articlereportVO.getArtRpId());
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

	public static void main(String[] args) {

		ArticleReportJDBCDAO dao = new ArticleReportJDBCDAO();

//		ArticleReportVO articlereportVO1 = new ArticleReportVO();
//		articlereportVO1.setUserId(1);
//		articlereportVO1.setArtId(1);
//		articlereportVO1.setRpReason(1);
//		articlereportVO1.setRpStatus(2);	
//		articlereportVO1.setEmpno(300005);
//		articlereportVO1.setRpContent("阿阿阿");
//		dao.insert(articlereportVO1);

		try {
			ArticleReportVO articlereportVO2 = new ArticleReportVO();
			articlereportVO2.setUserId(1);
			articlereportVO2.setArtId(1);
			articlereportVO2.setRpReason(0);
			articlereportVO2.setRpNote("此會員文章重複張貼多次,已登記");
			articlereportVO2.setRpStatus(1);
			articlereportVO2.setRpNote("22");
			articlereportVO2.setRpContent("23232323");
			articlereportVO2.setArtRpId(1);
			dao.update(articlereportVO2);
		} catch (Exception e) {
			e.printStackTrace();
		}

//		dao.delete(2);

//		ArticleReportVO articlereportVO3 = dao.findByPrimaryKey(1);
//		System.out.print(articlereportVO3.getUserId() + ",");
//		System.out.print(articlereportVO3.getArtId() + ",");
//		System.out.print(articlereportVO3.getRpReason()+ ",");
//		System.out.print(articlereportVO3.getEmpno() + ",");
//		System.out.print(articlereportVO3.getRpTime() + ",");
//		System.out.print(articlereportVO3.getRpdoneTime() + ",");
//		System.out.print(articlereportVO3.getRpStatus()+ ",");
//		System.out.print(articlereportVO3.getRpNote()+ ",");
//		System.out.println("---------------------");

//		List<ArticleReportVO> list = dao.getAll();
//		for (ArticleReportVO aArt : list) {
//			System.out.print(aArt.getArtRpId() + ",");
//			System.out.print(aArt.getUserId()+ ",");
//			System.out.print(aArt.getArtId() + ",");
//			System.out.print(aArt.getRpReason()+ ",");
//			System.out.print(aArt.getEmpno() + ",");
//			System.out.print(aArt.getRpTime()+ ",");
//			System.out.print(aArt.getRpdoneTime()+ "," );
//			System.out.print(aArt.getRpStatus()+ "," );
//			System.out.print(aArt.getRpNote());
//			System.out.println();
//		}
	}
}