package com.permission.model;

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

import jdbcUtil_CompositeQuery_Permission.jdbcUtil_CompositeQuery_Permission;





public class PermissionJDBCDAO  implements PermissionDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_PERMIS = 
		"INSERT INTO permission (func_id,emp_no) VALUES (?, ?)";
	private static final String GET_ALL_PERMIS = 
		"SELECT * FROM permission order by emp_no, func_id";
	private static final String GET__PERMISBYFUNC = 
		"SELECT * FROM permission where func_id = ?";
	private static final String GET__PERMISBYEMP = 
		"SELECT * FROM permission where emp_no =? ";
		private static final String DELETE_PERMIS = 
		"DELETE FROM permission where emp_no =? and func_id = ?";

	@Override
	public void insert(PermissionVO permissionVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_PERMIS);

			pstmt.setInt(1, permissionVO.getFuncId());
			pstmt.setInt(2, permissionVO.getEmpNo());
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
	public void delete(Integer empNo, Integer funcId) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_PERMIS);

			pstmt.setInt(1, empNo);
			pstmt.setInt(2, funcId);
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
	
	public List<PermissionVO> findPermisByEmp(Integer empNo) {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO permissionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET__PERMISBYEMP);
			pstmt.setInt(1,empNo);
			rs = pstmt.executeQuery();
			
			

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				permissionVO = new PermissionVO();
				permissionVO.setEmpNo(rs.getInt("emp_no"));
				permissionVO.setFuncId(rs.getInt("func_id"));
				list.add(permissionVO); // Store the row in the list
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
	
	public List<PermissionVO> findPermisByFunc(Integer funcId) {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO permissionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET__PERMISBYFUNC);
			
			pstmt.setInt(1,funcId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO �]�٬� Domain objects
				permissionVO = new PermissionVO();
				permissionVO.setEmpNo(rs.getInt("emp_no"));
				permissionVO.setFuncId(rs.getInt("func_id"));
				list.add(permissionVO); // Store the row in the list
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
	public List<PermissionVO> getAllPermis() {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO permissionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_PERMIS);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				permissionVO = new PermissionVO();
				permissionVO.setEmpNo(rs.getInt("emp_no"));
				permissionVO.setFuncId(rs.getInt("func_id"));
				list.add(permissionVO); // Store the row in the list
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
	
	public List<PermissionVO> getAllPermis(Map<String, String[]> map) {
		List<PermissionVO> list = new ArrayList<PermissionVO>();
		PermissionVO permissionVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from cga105_g7.permission "
					+ jdbcUtil_CompositeQuery_Permission.get_WhereCondition(map)
					+ "order by func_id";
			
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				permissionVO = new PermissionVO();
				permissionVO.setFuncId(rs.getInt("func_id"));
				permissionVO.setEmpNo(rs.getInt("emp_no"));
				list.add(permissionVO); // Store the row in the List
					
		}
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

	
	
	
	
	
	

	public static void main(String[] args) {

		PermissionJDBCDAO dao = new PermissionJDBCDAO();

//		// 新增OK
//		PermissionVO PermissionVO1 = new PermissionVO();
//		PermissionVO1.setEmpNo(300001);
//		PermissionVO1.setFuncId(12);
//
//		dao.insert(PermissionVO1);



		// 刪除ok
		dao.delete(300003,11);
//
//		// 以員工查詢
		
//		List<PermissionVO> list = dao.findPermisByFunc(12);
//		for (PermissionVO aPer : list) {
//			System.out.print(aPer.getFuncId() + ",");
//			System.out.print(aPer.getEmpNo() + ",");
//	
//			System.out.println();
//		}
//
		// ok
//		List<PermissionVO> list = dao.getAllPermis();
//		for (PermissionVO aPer : list) {
//			System.out.print(aPer.getFuncId() + ",");
//			System.out.print(aPer.getEmpNo() + ",");
//	
//			System.out.println();
//		}
	}

	
}
