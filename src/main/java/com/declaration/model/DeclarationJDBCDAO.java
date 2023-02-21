package com.declaration.model;

import java.util.*;

import com.Users.model.UsersVO;

import java.sql.*;

public class DeclarationJDBCDAO implements DeclarationDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
		"INSERT INTO declaration (declaration_title,declaration_content,declaration_pic) VALUES (?, ?, ?)";
	private static final String GET_PAGE = 
			"SELECT * FROM declaration WHERE  DECLARATION_DATE <= (SELECT declaration_date FROM declaration where declaration_status != 4 ORDER BY DECLARATION_DATE DESC LIMIT ?, 1) AND declaration_status != 4 ORDER BY DECLARATION_DATE DESC  LIMIT 5";
	private static final String GET_ALL_STMT = 
		"SELECT declaration_id ,declaration_title,declaration_content,declaration_pic,declaration_status,declaration_date FROM declaration WHERE declaration_status != 4 order by declaration_date DESC";
	private static final String GET_ONE_STMT = 
		"SELECT declaration_id ,declaration_title,declaration_content,declaration_pic,declaration_status,declaration_date FROM declaration where declaration_id = ?";
	private static final String GET_ONE_BY_TITLE_STMT =
		"SELECT DECLARATION_ID,DECLARATION_TITLE,declaration_content,declaration_pic,declaration_status,declaration_date FROM declaration where declaration_TITLE = ?";
	private static final String DELETE = 
		"DELETE FROM declaration where declaration_id = ?";
	private static final String UPDATE = 
		"UPDATE declaration set declaration_title=?, declaration_content=?, declaration_pic=? where declaration_id = ?";
	private static final String DELETE_STATUS = 
			"UPDATE DECLARATION SET DECLARATION_STATUS = 4 WHERE DECLARATION_ID = ?";
	private static final String GET_PIC = 
			"SELECT DECLARATION_PIC FROM DECLARATION WHERE DECLARATION_ID = ?";
	
	private static final String SQL_GET_USERS =
			"SELECT * FROM USERS ";

	
	@Override
	public List<UsersVO> getUsers(String input){
		List<UsersVO> list = new ArrayList<UsersVO>();
		UsersVO userVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;

		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(SQL_GET_USERS + input);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				userVO = new UsersVO();
				
				userVO.setUserId(rs.getInt("user_id"));
				userVO.setUserAccount(rs.getString("user_account"));
				userVO.setUserName(rs.getString("user_name"));
				userVO.setUserPhone(rs.getString("user_phone"));
				userVO.setUserAddress(rs.getString("user_address"));
				userVO.setUserGender(rs.getString("user_gender"));
				userVO.setUserPic(rs.getBytes("user_pic"));
				list.add(userVO); 	
			}
			return list;
		} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	} 
	
	
	
	@Override
	public byte[] getPic(Integer declarationID) {
		byte[] pic = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_PIC);
			pstmt.setInt(1, declarationID);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {		
				pic = rs.getBytes("declaration_pic");
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return pic;
	}
	
	@Override
	public void deleteStatus(Integer declarationID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_STATUS);
			pstmt.setInt(1, declarationID);
			pstmt.executeUpdate();
		}catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load datebase driver. "
					+ e.getMessage());
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
	public int insert(DeclarationVO declarationVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT,Statement.RETURN_GENERATED_KEYS);

			pstmt.setString(1, declarationVO.getTitle());
			pstmt.setString(2, declarationVO.getContent());
			pstmt.setBytes(3, declarationVO.getPic());

			pstmt.executeUpdate();

			int id=0;
	        ResultSet resultSet = pstmt.getGeneratedKeys();
	        if (resultSet.next()) {
	            id = resultSet.getInt(1);
	        }
			return id;
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
	public void update(DeclarationVO declarationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, declarationVO.getTitle());
			pstmt.setString(2, declarationVO.getContent());
			pstmt.setBytes(3, declarationVO.getPic());
			pstmt.setInt(4, declarationVO.getDeclarationID());

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
	public void delete(Integer declarationID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, declarationID);

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
	public List<DeclarationVO> findPage(Integer currPage) {
		List<DeclarationVO> list = new ArrayList<DeclarationVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_PAGE);
			pstmt.setInt(1, currPage-1);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				DeclarationVO decVO = new DeclarationVO();		
				decVO.setDeclarationID(rs.getInt("declaration_id"));
				decVO.setTitle(rs.getString("declaration_title"));
				decVO.setContent(rs.getString("declaration_content"));
				decVO.setPic(rs.getBytes("declaration_pic"));
				decVO.setStatus(rs.getInt("declaration_status"));
				decVO.setDate(rs.getDate("declaration_date"));
				list.add(decVO);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public DeclarationVO findByPrimaryKey(Integer declarationID) {

		DeclarationVO declarationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, declarationID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				declarationVO = new DeclarationVO();
						
				declarationVO.setDeclarationID(rs.getInt("declaration_id"));
				declarationVO.setTitle(rs.getString("declaration_title"));
				declarationVO.setContent(rs.getString("declaration_content"));
				declarationVO.setPic(rs.getBytes("declaration_pic"));
				declarationVO.setStatus(rs.getInt("declaration_status"));
				declarationVO.setDate(rs.getDate("declaration_date"));
				
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
		return declarationVO;
	}

	@Override
	public List<DeclarationVO> getAll() {
		List<DeclarationVO> list = new ArrayList<DeclarationVO>();
		DeclarationVO declarationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				declarationVO = new DeclarationVO();
				declarationVO.setDeclarationID(rs.getInt("declaration_id"));
				declarationVO.setTitle(rs.getString("declaration_title"));
				declarationVO.setContent(rs.getString("declaration_content"));
				declarationVO.setPic(rs.getBytes("declaration_pic"));
				declarationVO.setStatus(rs.getInt("declaration_status"));
				declarationVO.setDate(rs.getDate("declaration_date"));
				
				list.add(declarationVO); // Store the row in the list
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
	public DeclarationVO findByTitle(String title) {
		DeclarationVO declarationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_TITLE_STMT);

			pstmt.setString(1, title);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				declarationVO = new DeclarationVO();
						
				declarationVO.setDeclarationID(rs.getInt("declaration_id"));
				declarationVO.setTitle(rs.getString("declaration_title"));
				declarationVO.setContent(rs.getString("declaration_content"));
				declarationVO.setPic(rs.getBytes("declaration_pic"));
				declarationVO.setStatus(rs.getInt("declaration_status"));
				declarationVO.setDate(rs.getDate("declaration_date"));
				
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		return declarationVO;
	}

	public static void main(String[] args) {

		DeclarationJDBCDAO dao = new DeclarationJDBCDAO();
	
		// insert
//		DeclarationVO declarationVO1 = new DeclarationVO();
//		declarationVO1.setTitle("test 9");
//		declarationVO1.setContent("insert test 9 ");
//		declarationVO1.setPic(null);
//		declarationVO1.setDate(java.sql.Date.valueOf("2005-01-01"));
//		declarationVO1.setStatus(0);
//		int a = dao.insert(declarationVO1);
//		System.out.println(a);
		
//		DeclarationVO declarationVO2 = new DeclarationVO();
//		declarationVO2.setTitle("test 1");
//		declarationVO2.setContent("insert test 1 ");
//		declarationVO2.setPic(null);
//		declarationVO2.setStatus(0);
//		declarationVO2.setDate(java.sql.Date.valueOf("2005-01-01"));
//		dao.insert(declarationVO2);

		// update
//		DeclarationVO declarationVO3 = new DeclarationVO();
//		declarationVO3.setDeclarationID(1);
//		declarationVO3.setTitle("test 1");
//		declarationVO3.setContent("update test 1  !!!!!!!!");
//		declarationVO3.setPic(null);
//		declarationVO3.setStatus(0);
//		declarationVO3.setDate(java.sql.Date.valueOf("2005-01-01"));
//		
//		dao.update(declarationVO3);

		// delete
//		dao.delete(10);
//		dao.delete(11);

		// search one date
//		int id = 1;
//		DeclarationVO declarationVO4 = dao.findByPrimaryKey(id);
//		System.out.println("---------------------");
//		System.out.println("ID : "+declarationVO4.getDeclarationID());
//		System.out.println("Title : "+declarationVO4.getTitle());
//		System.out.println("Content : "+declarationVO4.getContent());
////		System.out.print(aDeclaration.getPic());
//		System.out.println("Status : "+declarationVO4.getStatus());
//		System.out.println("Date : "+declarationVO4.getDate());
//		System.out.println("---------------------");
//		System.out.println();

		// search list
//		List<DeclarationVO> list = dao.getAll();
//		for (DeclarationVO aDeclaration : list) {
//			System.out.println("---------------------");
//			System.out.println("ID : "+aDeclaration.getDeclarationID());
//			System.out.println("Title : "+aDeclaration.getTitle());
//			System.out.println("Content : "+aDeclaration.getContent());
////			System.out.print(aDeclaration.getPic());
//			System.out.println("Status : "+aDeclaration.getStatus());
//			System.out.println("Date : "+aDeclaration.getDate());
//			System.out.println("---------------------");
//			System.out.println();
//		}
		
		// search list
//		List<UsersVO> list = dao.getUsers("王");
//		for (UsersVO aDeclaration : list) {
//			System.out.println("---------------------");
//			System.out.println("ID : "+aDeclaration.getUserId());
//			System.out.println("Account : "+aDeclaration.getUserAccount());
//			System.out.println("Name : "+aDeclaration.getUserName());
//			System.out.println("Phone : "+aDeclaration.getUserPhone());
//			System.out.println("Address : "+aDeclaration.getUserAddress());
//			System.out.println("Gender : "+aDeclaration.getUserGender());
//			System.out.println("---------------------");
//			System.out.println();
//		}
	}
	


}