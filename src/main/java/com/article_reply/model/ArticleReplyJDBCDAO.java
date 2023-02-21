package com.article_reply.model;

import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

public class ArticleReplyJDBCDAO implements ArticleReplyDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String GET_ONE_STMT = 
			"select art_reply_id, art_id, user_id, reply_content, reply_time, reply_status  from article_reply where art_reply_id = ?";
	private static final String GET_ALL_STMT = 
		   "select art_reply_id, art_id, user_id, reply_content, reply_time, reply_status  from article_reply order by art_reply_id desc";
	private static final String GET_ONE_POST_ALLMSG = 
			"select art_reply_id, art_id, user_id, reply_content, reply_time, reply_status  from article_reply where art_id = ? and reply_status = 0 order by reply_time";
	private static final String INSERT_STMT = 
			"insert into article_reply (art_id,user_id, reply_content, reply_status) values (?, ?, ?, ?);";
	private static final String UPDATE_ARTREPLYSTATUS =
			"update article_reply set reply_status=? where art_reply_id = ?";
	private static final String UPDATE =
			"update article_reply set art_id=? user_id=? reply_content=?  reply_status=? where art_reply_id = ?";
	
	@Override
	public ArticleReplyVO findByPrimaryKey(Integer artreplyid) {

		ArticleReplyVO articleReplyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, artreplyid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleReplyVO = new ArticleReplyVO();
				articleReplyVO.setArtReplyId(rs.getInt("ART_REPLY_ID"));
				articleReplyVO.setArtId(rs.getInt("ART_ID"));
				articleReplyVO.setUserId(rs.getInt("USER_ID"));
				articleReplyVO.setReplyContent(rs.getString("REPLY_CONTENT"));;
				articleReplyVO.setReplyTime(rs.getObject("REPLY_TIME", LocalDateTime.class));
				articleReplyVO.setReplyStatus(rs.getInt("REPLY_STATUS"));
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
		return articleReplyVO;
	}
	
	public List<ArticleReplyVO> findArtPostMsg(Integer artId) {

		List<ArticleReplyVO> list = new ArrayList<ArticleReplyVO>();

		ArticleReplyVO articleReplyVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_POST_ALLMSG);
			pstmt.setInt(1, artId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleReplyVO = new ArticleReplyVO();
				articleReplyVO.setArtReplyId(rs.getInt("ART_REPLY_ID"));
				articleReplyVO.setArtId(rs.getInt("ART_ID"));
				articleReplyVO.setUserId(rs.getInt("USER_ID"));
				articleReplyVO.setReplyContent(rs.getString("REPLY_CONTENT"));;
				articleReplyVO.setReplyTime(rs.getObject("REPLY_TIME", LocalDateTime.class));
				articleReplyVO.setReplyStatus(rs.getInt("REPLY_STATUS"));
				list.add(articleReplyVO);
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
	public List<ArticleReplyVO> getAll() {
		List<ArticleReplyVO> list = new ArrayList<ArticleReplyVO>();
		ArticleReplyVO articleReplyVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleReplyVO = new ArticleReplyVO();
				articleReplyVO.setArtReplyId(rs.getInt("ART_REPLY_ID"));
				articleReplyVO.setArtId(rs.getInt("ART_ID"));
				articleReplyVO.setUserId(rs.getInt("USER_ID"));
				articleReplyVO.setReplyContent(rs.getString("REPLY_CONTENT"));;
				articleReplyVO.setReplyTime(rs.getObject("REPLY_TIME", LocalDateTime.class));
				articleReplyVO.setReplyStatus(rs.getInt("REPLY_STATUS"));
				list.add(articleReplyVO);// Store the row in the list
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
	public void insert(ArticleReplyVO articleReplyVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleReplyVO.getArtId());
			pstmt.setInt(2, articleReplyVO.getUserId());
			pstmt.setString(3, articleReplyVO.getReplyContent());
			pstmt.setInt(4, articleReplyVO.getReplyStatus());
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
	public void updateReplyStatus (ArticleReplyVO articleReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ARTREPLYSTATUS);
			
			pstmt.setInt(1, articleReplyVO.getReplyStatus());
			pstmt.setInt(2, articleReplyVO.getArtReplyId());
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
	public void update (ArticleReplyVO articleReplyVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, articleReplyVO.getArtId());
			pstmt.setInt(2, articleReplyVO.getUserId());
			pstmt.setString (3, articleReplyVO.getReplyContent());
			pstmt.setInt(4, articleReplyVO.getReplyStatus());
			pstmt.setInt(5, articleReplyVO.getArtReplyId());
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

		ArticleReplyJDBCDAO dao = new ArticleReplyJDBCDAO();
		
		
		ArticleReplyVO articleReplyVO1 = new ArticleReplyVO();
		articleReplyVO1.setArtId(1);
		articleReplyVO1.setUserId(1);
		articleReplyVO1.setReplyContent("我喜翻");;
		articleReplyVO1.setReplyStatus(0);
		dao.insert(articleReplyVO1);

//		ArticleReplyVO articleReplyVO3 = dao.findByPrimaryKey(12);
//		System.out.print(articleReplyVO3.getArtId() + ",");
//		System.out.print(articleReplyVO3.getUserId() + ",");
//		System.out.print(articleReplyVO3.getReplycontent() + ",");
//		System.out.print(articleReplyVO3.getReplytime() + ",");
//		System.out.print(articleReplyVO3.getReplystatus() + ",");
//		System.out.println("---------------------");

//		List<ArticleReplyVO> list = dao.getAll();
//		for (ArticleReplyVO aArtReply : list) {
//			System.out.print(aArtReply.getArtreplyid() + ",");
//			System.out.print(aArtReply.getArtId() + ",");
//			System.out.print(aArtReply.getUserId()+ ",");
//			System.out.print(aArtReply.getReplycontent()+ ",");
//			System.out.print(aArtReply.getReplytime() + ",");
//			System.out.print(aArtReply.getReplystatus()+ ",");
//			System.out.println();
//		}
	}
}