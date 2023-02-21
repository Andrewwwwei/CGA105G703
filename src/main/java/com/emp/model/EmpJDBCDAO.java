package com.emp.model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.Users.model.UsersVO;

import jdbc.util.CompositeQuery_Employee.jdbcUtil_CompositeQuery_Employee;

public class EmpJDBCDAO implements EmpDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE (EMP_PW, EMP_NAME, EMP_DEP, JOB_TITLE, EMP_IDNUM, EMP_EMAIL, EMP_TEL, EMP_STATUS, EMP_HIREDATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT EMP_NO, EMP_PW, EMP_NAME, EMP_DEP, JOB_TITLE, EMP_IDNUM, EMP_EMAIL, EMP_TEL, EMP_STATUS, EMP_HIREDATE FROM EMPLOYEE order by EMP_NO";
	private static final String GET_ONE_STMT = "SELECT EMP_NO, EMP_PW, EMP_NAME, EMP_DEP, JOB_TITLE, EMP_IDNUM, EMP_EMAIL, EMP_TEL, EMP_STATUS, EMP_HIREDATE FROM EMPLOYEE where EMP_NO = ? ";
	private static final String DELETE = "DELETE FROM EMPLOYEE where EMP_NO = ?";
	private static final String UPDATE = "UPDATE EMPLOYEE SET EMP_PW= ?,EMP_NAME= ?,EMP_DEP= ?,JOB_TITLE= ?,EMP_IDNUM= ?,EMP_TEL= ?,EMP_STATUS= ?,EMP_HIREDATE= ? where EMP_EMAIL = ? " ;
	private static final String GET_ONE_BY_EMP_EMAIL = "SELECT EMP_NO, EMP_PW, EMP_NAME, EMP_DEP, JOB_TITLE, EMP_IDNUM, EMP_EMAIL, EMP_TEL, EMP_STATUS, EMP_HIREDATE FROM EMPLOYEE where EMP_EMAIL = ? ";

	@Override
	public void insert(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setString(1, empVO.getEmpPw());
			pstmt.setString(2, empVO.getEmpName());
			pstmt.setString(3, empVO.getEmpDep());
			pstmt.setString(4, empVO.getJobTitle());
			pstmt.setString(5, empVO.getEmpIdnum());
			pstmt.setString(6, empVO.getEmpEmail());
			pstmt.setString(7, empVO.getEmpTel());
			pstmt.setInt(8, empVO.getEmpStatus());
			pstmt.setDate(9, empVO.getEmpHiredate());
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
	public void update(EmpVO empVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, empVO.getEmpPw());
			pstmt.setString(2, empVO.getEmpName());
			pstmt.setString(3, empVO.getEmpDep());
			pstmt.setString(4, empVO.getJobTitle());
			pstmt.setString(5, empVO.getEmpIdnum());
			pstmt.setString(6, empVO.getEmpTel());
			pstmt.setInt(7, empVO.getEmpStatus());
			pstmt.setDate(8, empVO.getEmpHiredate());
			pstmt.setString(9, empVO.getEmpEmail());
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
	public void delete(Integer EMP_NO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			pstmt.setInt(1, EMP_NO);
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
	public EmpVO findByPrimaryKey(Integer empNo) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, empNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpNo(rs.getInt("EMP_NO"));
				empVO.setEmpPw(rs.getString("EMP_PW"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpDep(rs.getString("EMP_DEP"));
				empVO.setJobTitle(rs.getString("JOB_TITLE"));
				empVO.setEmpIdnum(rs.getString("EMP_IDNUM"));
				empVO.setEmpEmail(rs.getString("EMP_EMAIL"));
				empVO.setEmpTel(rs.getString("EMP_TEL"));
				empVO.setEmpStatus(rs.getInt("EMP_STATUS"));
				empVO.setEmpHiredate(rs.getDate("EMP_HIREDATE"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll(Map<String, String[]> map) {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from cga105_g7.employee "
		          + jdbcUtil_CompositeQuery_Employee.get_WhereCondition(map)
		          + "order by emp_no";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpNo(rs.getInt("emp_no"));
				empVO.setEmpName(rs.getString("emp_name"));
				empVO.setEmpIdnum(rs.getString("emp_idnum"));
				empVO.setEmpStatus(rs.getInt("emp_status"));
				empVO.setEmpDep(rs.getString("emp_dep"));
				empVO.setJobTitle(rs.getString("job_title"));
				empVO.setEmpTel(rs.getString("emp_tel"));
				empVO.setEmpEmail(rs.getString("emp_email"));
				empVO.setEmpHiredate(rs.getDate("emp_hiredate"));
				list.add(empVO); // Store the row in the List
			}
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
							
			// Handle any SQL errors
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
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpNo(rs.getInt("EMP_NO"));
				empVO.setEmpPw(rs.getString("EMP_PW"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpDep(rs.getString("EMP_DEP"));
				empVO.setJobTitle(rs.getString("JOB_TITLE"));
				empVO.setEmpIdnum(rs.getString("EMP_IDNUM"));
				empVO.setEmpEmail(rs.getString("EMP_EMAIL"));
				empVO.setEmpTel(rs.getString("EMP_TEL"));
				empVO.setEmpStatus(rs.getInt("EMP_STATUS"));
				empVO.setEmpHiredate(rs.getDate("EMP_HIREDATE"));
				list.add(empVO); // Store the row in the list
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

//	public boolean login(String EMP_EMAIL, String EMP_PW) {
//		boolean list = true;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		try {
//			Class.forName(driver);
//			con = DriverManager.getConnection(url, userid, passwd);
//			pstmt = con.prepareStatement(LOGIN);
//			pstmt.setString(1, EMP_EMAIL);
//			pstmt.setString(2, EMP_PW);
//			rs = pstmt.executeQuery();
//			if (rs.next()) {
//				list = true;
//			} else {
//				list = false;
//			}
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
//		}
//		return list;
//	}



	@Override
	public EmpVO findByEmpEmail(String EMP_EMAIL) {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_BY_EMP_EMAIL);
			pstmt.setString(1, EMP_EMAIL);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				empVO = new EmpVO();
				empVO.setEmpNo(rs.getInt("EMP_NO"));
				empVO.setEmpPw(rs.getString("EMP_PW"));
				empVO.setEmpName(rs.getString("EMP_NAME"));
				empVO.setEmpDep(rs.getString("EMP_DEP"));
				empVO.setJobTitle(rs.getString("JOB_TITLE"));
				empVO.setEmpIdnum(rs.getString("EMP_IDNUM"));
				empVO.setEmpEmail(rs.getString("EMP_EMAIL"));
				empVO.setEmpTel(rs.getString("EMP_TEL"));
				empVO.setEmpStatus(rs.getInt("EMP_STATUS"));
				empVO.setEmpHiredate(rs.getDate("EMP_HIREDATE"));
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
		return empVO;
	}

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmpPw("111111");
//		empVO1.setEmpName("小吳");
//		empVO1.setEmpDep("網站管理部");
//		empVO1.setJobTitle("小編");
//		empVO1.setEmpIdnum("A22345678");
//		empVO1.setEmpEmail("q111111111p@yahoo.com.tw");
//		empVO1.setEmpTel("0912345678");
//		empVO1.setEmpStatus(1);
//		empVO1.setEmpHiredate(sqlDate);
//		dao.insert(empVO1);

//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpPw("222222");
//		empVO2.setEmpName("大吳");
//		empVO2.setEmpDep("網站管理部");
//		empVO2.setJobTitle("大編");
//		empVO2.setEmpIdnum("A11111111");
//		empVO2.setEmpTel("0923456789");
//		empVO2.setEmpStatus(1);
//		empVO2.setEmpHiredate(sqlDate);
//		empVO2.setEmpEmail("q1@yahoo.com.tw");
//		dao.update(empVO2);

//		dao.delete(300017);

//		EmpVO empVO3 = dao.findByPrimaryKey("大吳");
//		System.out.println(empVO3.getEmpNo() + ",");
//		System.out.println(empVO3.getEmpPw() + ",");
//		System.out.println(empVO3.getEmpName() + ",");
//		System.out.println(empVO3.getEmpDep() + ",");
//		System.out.println(empVO3.getJobTitle() + ",");
//		System.out.println(empVO3.getEmpIdnum() + ",");
//		System.out.println(empVO3.getEmpEmail()+",");
//		System.out.println(empVO3.getEmpTel()+",");
//		System.out.println(empVO3.getEmpStatus()+",");
//		System.out.println(empVO3.getEmpHiredate()+",");
//		System.out.println("---------------------");

//		EmpVO empVO5 = dao.findByEmpEmail("q1@yahoo.com.tw");
//		System.out.println(empVO5.getEmpNo() + ",");
//		System.out.println(empVO5.getEmpPw() + ",");
//		System.out.println(empVO5.getEmpName() + ",");
//		System.out.println(empVO5.getEmpDep() + ",");
//		System.out.println(empVO5.getJobTitle() + ",");
//		System.out.println(empVO5.getEmpIdnum() + ",");
//		System.out.println(empVO5.getEmpEmail()+",");
//		System.out.println(empVO5.getEmpTel()+",");
//		System.out.println(empVO5.getEmpStatus()+",");
//		System.out.println(empVO5.getEmpHiredate()+",");
//		System.out.println("---------------------");

		
//	System.out.println(dao.login("q1@yahoo.com.tw", "222222"));

	}
}
