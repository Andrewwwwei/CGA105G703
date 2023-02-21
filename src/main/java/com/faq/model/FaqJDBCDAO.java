package com.faq.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import jdbc.util.CompositeQuery_Employee.jdbcUtil_CompositeQuery_Employee;

public class FaqJDBCDAO implements FaqDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";
	

	private static final String 
	INSERT_STMT ="INSERT INTO faq(faq_content,faq_title) VALUES(?,?)";
	private static final String
	GET_ONE_STMT = "SELECT faq_id,faq_content,faq_title FROM faq where faq_id=?";
	private static final String 
	GET_ALL_STMT ="SELECT faq_id,faq_content,faq_title FROM faq  order by faq_id";
	private static final String 
	DELETE = "DELETE FROM faq where  faq_id=?";
	private static final String
	UPDATE = "UPDATE faq set  faq_title=?,faq_content=? where  faq_id=?";
	
	@Override
	public void insert(FaqVO faqVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, faqVO.getFaqContent());
			pstmt.setString(2, faqVO.getFaqTitle());
			
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
	public void update(FaqVO faqVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, faqVO.getFaqTitle());
			pstmt.setString(2, faqVO.getFaqContent());
			pstmt.setInt(3, faqVO.getFaqId());
			
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
	public void delete(Integer faq_id) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, faq_id);

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
	public FaqVO findByPrimaryKey(Integer faq_id) {

		 FaqVO  faqVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, faq_id);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				faqVO = new FaqVO();
				faqVO.setFaqId(rs.getInt("faq_id"));
				faqVO.setFaqContent(rs.getString("faq_content"));
				faqVO.setFaqTitle(rs.getString("faq_title"));
				
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
		return faqVO;
	}

	@Override
	public List<FaqVO> getAll() {
		List<FaqVO> list = new ArrayList<FaqVO>();
		FaqVO faqVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				faqVO = new FaqVO();
				faqVO.setFaqId(rs.getInt("faq_id"));
				faqVO.setFaqContent(rs.getString("faq_content"));
				faqVO.setFaqTitle(rs.getString("faq_title"));
				
				list.add(faqVO); // Store the row in the list
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

		FaqJDBCDAO dao = new FaqJDBCDAO();		
//		FaqVO faqVO = new FaqVO();
//
//		faqVO.setFaqContent("Content測試");
//		faqVO.setFaqTitle("title測試");
//
//		dao.insert(faqVO);


		// 刪除 ok
//		dao.delete(5);



		// 查詢全部
//		List<FaqVO> list = dao.getAll();
//		for (FaqVO fEmp : list) {
//			System.out.print(fEmp.getFaqContent() + ",");
//			System.out.print(fEmp.getFaqTitle() + ",");
//			System.out.print(fEmp.getFaqId() + ",");
//			System.out.println("---------------------");
//		}
		
		FaqVO faqVO = new FaqVO();
		faqVO.setFaqId(3);	
		faqVO.setFaqContent("依據各住宿而定。預訂時，7 TOUR 將告知是否有線上付款未包含的稅費或額外費用，請於現場支付。");
		faqVO.setFaqTitle("有含稅及服務費嗎");
		dao.update(faqVO);
	}
}