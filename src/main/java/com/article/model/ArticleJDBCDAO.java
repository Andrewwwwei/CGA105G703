package com.article.model;

import java.util.*;

import javax.servlet.http.Part;

import com.article_report.model.ArticleReportVO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDateTime;

public class ArticleJDBCDAO implements ArticleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String GET_ONE_STMT = // findByPrimaryKey
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic  from article  where art_id = ?";
	private static final String GET_ONE_ARTICLE_TOP_STMT = // findByTopArticle
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic  from article  where art_status = 0 and art_show_count>10000 order by art_show_count desc";
	private static final String GET_ONE_USER_STMT = // findByUserId
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic  from article where user_id = ? and art_status = 0 order by art_time desc";
	private static final String GET_ONE_POSTTYPE_STMT = // findByPostType
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic   from article where art_type_id=? ";
	private static final String GET_ONE_SEARCHKEYWORD_STMT = // searchByKeyWord
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic   from article where art_title like ? and art_content like ?";
	private static final String GET_ONE_ARTICLE_STMT = // findonePost
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic   from article  where art_id = ? and art_status = 0 order by art_show_count desc";
	private static final String GET_POST_MAN = // findpostman
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic  from article  where user_id = ?";
	private static final String GET_ALL_STMT =  //getall
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic  from article order by art_time";
	private static final String GET_ALL_SHOW_STATUS_STMT =  //getAllShowStatus
			"select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status, art_pic  from article where art_status=0  order by art_time desc";
	
	private static final String INSERT_STMT = "insert into article ( user_id, art_type_id, art_title, art_content, art_pic) values (?, ?, ?, ?, ?);";
	private static final String UPDATE_STMT = "update article set user_Id=? ,art_type_id=? ,art_title=? ,art_content=? ,art_likes_count=? , art_show_count=?  ,art_status=?   where art_id = ?";
	private static final String UPDATE_ARTICLE_STATUS = "update article set art_type_id = ?, art_status = ? where art_id = ?";
	private static final String UPDATE_ART_SHOW_COUNT = "update article set art_show_count = art_show_count +1 where art_id = ?";  // updateShowCount
	private static final String DELETE = "delete from article where art_id = ?";

	@Override
	public ArticleVO findByPrimaryKey(Integer artId) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver); // 找到驅動程式
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, artId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("Art_Pic"));
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
		return articleVO;
	}

	@Override
	public ArticleVO findPostMan(Integer userId) {

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver); // 找到驅動程式
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_POST_MAN);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("Art_Pic"));
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
		return articleVO;
	}

	@Override
	public List<ArticleVO> findByTopArticle() {

		List<ArticleVO> list = new ArrayList<ArticleVO>();

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ARTICLE_TOP_STMT);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("Art_Pic"));
				list.add(articleVO);
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	
	@Override
	public List<ArticleVO> getAllShowStatus() {
		
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_SHOW_STATUS_STMT);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("Art_Pic"));
				list.add(articleVO);
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	@Override
	public List<ArticleVO> findonePost(Integer artId) {

		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = new ArticleVO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver); // 找到驅動程式
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ARTICLE_STMT);

			pstmt.setInt(1, artId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("Art_Pic"));
				list.add(articleVO);
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
	public List<ArticleVO> findByUserId(Integer userId) {

		List<ArticleVO> list = new ArrayList<ArticleVO>();

		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_USER_STMT);

			pstmt.setInt(1, userId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("ART_PIC"));
				list.add(articleVO);
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
	public List<ArticleVO> findByPostType(Integer artTypeId) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_POSTTYPE_STMT);

			pstmt.setInt(1, artTypeId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("Art_Pic"));
				list.add(articleVO);
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

	@Override
	public List<ArticleVO> searchByKeyword(String keyword) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_SEARCHKEYWORD_STMT);

			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("art_id"));
				articleVO.setUserId(rs.getInt("user_id"));
				articleVO.setArtTypeId(rs.getInt("art_type_id"));
				articleVO.setArtTitle(rs.getString("art_title"));
				articleVO.setArtContent(rs.getString("art_content"));
				list.add(articleVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				articleVO.setArtPic(rs.getBytes("ART_PIC"));
				list.add(articleVO);
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
	public List<ArticleVO> getPowerAll(Map<String, String[]> map) {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select art_id, user_id, art_type_id, art_title, art_content, art_likes_count, art_show_count, art_time, art_edit_time, art_status from article  where art_status = 0 and art_type_id > 0 "
					+ JDBCUtil_CompositeQuery_ForumPost.get_WhereCondition(map) + "ORDER BY art_id  DESC";
			pstmt = con.prepareStatement(finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				articleVO = new ArticleVO();

				articleVO.setArtId(rs.getInt("ART_ID"));
				articleVO.setUserId(rs.getInt("USER_ID"));
				articleVO.setArtTypeId(rs.getInt("ART_TYPE_ID"));
				articleVO.setArtTitle(rs.getString("ART_TITLE"));
				articleVO.setArtContent(rs.getString("ART_CONTENT"));
				articleVO.setArtLikesCount(rs.getInt("ART_LIKES_COUNT"));
				articleVO.setArtShowCount(rs.getInt("ART_SHOW_COUNT"));
				articleVO.setArtTime(rs.getObject("ART_TIME", LocalDateTime.class));
				articleVO.setArtEditTime(rs.getObject("ART_EDIT_TIME", LocalDateTime.class));
				articleVO.setArtStatus(rs.getInt("ART_STATUS"));
				list.add(articleVO);
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
	public void insert(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, articleVO.getUserId());
			pstmt.setInt(2, articleVO.getArtTypeId());
			pstmt.setString(3, articleVO.getArtTitle());
			pstmt.setString(4, articleVO.getArtContent());
			pstmt.setBytes(5, articleVO.getArtPic());
			pstmt.executeUpdate();

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
	public void update(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_STMT);
            
			pstmt.setInt(1, articleVO.getUserId());
			pstmt.setInt(2, articleVO.getArtTypeId());
			pstmt.setString(3, articleVO.getArtTitle());
			pstmt.setString(4, articleVO.getArtContent());
			pstmt.setInt(5, articleVO.getArtLikesCount());
			pstmt.setInt(6, articleVO.getArtShowCount());
			pstmt.setInt(7, articleVO.getArtStatus());
			pstmt.setInt(8, articleVO.getArtId());
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
	public void updateArticleStatus(ArticleVO articleVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE_ARTICLE_STATUS);

			pstmt.setInt(1, articleVO.getArtId());
			pstmt.setInt(2, articleVO.getArtTypeId());
			pstmt.setInt(3, articleVO.getArtStatus());

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
	public void updateShowCount (Integer artId) {
		  Connection con = null;
		  PreparedStatement pstmt = null;

		  try {
		    Class.forName(driver);
		    con = DriverManager.getConnection(url, userid, passwd);
		    pstmt = con.prepareStatement(UPDATE_ART_SHOW_COUNT);
		    
		    pstmt.setInt(1, artId);
		    
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
	public void delete(Integer artId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, artId);

			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " // RuntimeException 如果不用就不用寫 因為這樣除錯不好除錯
					+ e.getMessage());
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

		ArticleJDBCDAO dao = new ArticleJDBCDAO();

		// 新增
//		ArticleVO articleVO1 = new ArticleVO();
//		articleVO1.setUserId(2);
//		articleVO1.setArtTypeId(1);
//		articleVO1.setArtTitle("南部二日遊");
//		articleVO1.setArtContent("救命喔怎麼這麼會玩e04");		
//		articleVO1.setArtLikesCount(1);
//		articleVO1.setArtShowCount(1);
//		articleVO1.setArtStatus(2);
//		dao.insert(articleVO1);

		// 修改
//		ArticleVO articleVO2 = new ArticleVO();	
//		articleVO2.setArtId(2);
//		articleVO2.setUserId(1);
//		articleVO2.setArtTypeId(1);
//		articleVO2.setArtTitle("南部二日遊222");
//		articleVO2.setArtContent("我想去高雄2222");		
//		articleVO2.setArtLikesCount(1);
//		articleVO2.setArtShowCount(1);
//		articleVO2.setArtStatus(2);
//		dao.update(articleVO2);

		// 刪除
//		dao.delete(5);

		// 查詢
		ArticleVO articleVO3 = dao.findByPrimaryKey(3);
		System.out.print(articleVO3.getUserId() + ",");
		System.out.print(articleVO3.getArtTypeId() + ",");
		System.out.print(articleVO3.getArtTitle() + ",");
		System.out.print(articleVO3.getArtContent() + ",");
		System.out.print(articleVO3.getArtLikesCount() + ",");
		System.out.print(articleVO3.getArtShowCount() + ",");
		System.out.print(articleVO3.getArtTime() + ",");
		System.out.print(articleVO3.getArtEditTime() + ",");
		System.out.println(articleVO3.getArtStatus());
		System.out.println(articleVO3.getArtPic());
		System.out.println("---------------------");

		// 查詢
//		List<ArticleVO> list = dao.getAll();
//		for (ArticleVO aArt : list) {
//			System.out.print(aArt.getArtId() + ",");
//			System.out.print(aArt.getUserId()+ ",");
//			System.out.print(aArt.getArtTypeId() + ",");
//			System.out.print(aArt.getArtTitle()+ ",");
//			System.out.print(aArt.getArtContent() + ",");
//			System.out.print(aArt.getArtLikesCount()+ ",");
//			System.out.print(aArt.getArtShowCount()+ "," );
//			System.out.print(aArt.getArtTime()+ "," );
//			System.out.print(aArt.getArtEditTime()+ "," );
//			System.out.println(aArt.getArtStatus());
//			System.out.println(aArt.getArtPic());
//			System.out.println();
//		}

//		List<ArticleVO> list = dao.getUserIdPosted(1);
//		for (ArticleVO aArt : list) {
//			System.out.print(aArt.getArtId() + ",");
//			System.out.print(aArt.getUserId()+ ",");
//			System.out.print(aArt.getArtTypeId() + ",");
//			System.out.print(aArt.getArtTitle()+ ",");
//			System.out.print(aArt.getArtContent() + ",");
//			System.out.print(aArt.getArtLikesCount()+ ",");
//			System.out.print(aArt.getArtShowCount()+ "," );
//			System.out.print(aArt.getArtTime()+ "," );
//			System.out.print(aArt.getArtEditTime()+ "," );
//			System.out.println(aArt.getArtStatus());
//			System.out.println();
//		}

	}

}