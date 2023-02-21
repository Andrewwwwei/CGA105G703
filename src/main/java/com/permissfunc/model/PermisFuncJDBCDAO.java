package com.permissfunc.model;

	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

	public class PermisFuncJDBCDAO implements PermisFuncDAO_interface{
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
		String userid = "root";
		String passwd = "02021";

		private static final String INSERT_permissFunc = 
			"INSERT INTO permission_func (func_name) VALUES (?)";
		private static final String GET_ALL_permissFunc = 
			"SELECT func_id,func_name FROM permission_func order by func_id";
		private static final String GET_ONE_permissFunc = 
			"SELECT func_id,func_name FROM permission_func where func_id = ?";
		private static final String DELETE_permissFunc = 
			"DELETE FROM permission_func where func_id = ?";
		private static final String UPDATE_permissFunc = 
			"UPDATE permission_func set func_name=? where func_id = ?";

		@Override
		public void insertFunc(PermisFuncVO permisFuncVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(INSERT_permissFunc);

				pstmt.setString(1, permisFuncVO.getFuncName());

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
		public void updateFunc(PermisFuncVO permisFuncVO) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(UPDATE_permissFunc);
				
				pstmt.setString(1, permisFuncVO.getFuncName());
				pstmt.setInt(2, permisFuncVO.getFuncId());
		
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
		public void deleteFunc(Integer func_id) {

			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement( DELETE_permissFunc);

				pstmt.setInt(1, func_id);

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
		public PermisFuncVO findOneFunc(Integer func_id) {

			PermisFuncVO permisFuncVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ONE_permissFunc);

				pstmt.setInt(1,func_id);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo �]�٬� Domain objects
					permisFuncVO = new PermisFuncVO();
					permisFuncVO.setFuncId(rs.getInt("func_id"));
					permisFuncVO.setFuncName(rs.getString("func_name"));
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
			return permisFuncVO;
		}

		@Override
		public List<PermisFuncVO> getAllFunc() {
			List<PermisFuncVO> list = new ArrayList<PermisFuncVO>();
			PermisFuncVO permisFuncVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(url, userid, passwd);
				pstmt = con.prepareStatement(GET_ALL_permissFunc);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVO �]�٬� Domain objects
					permisFuncVO = new PermisFuncVO();
					permisFuncVO.setFuncId(rs.getInt("func_id"));
					permisFuncVO.setFuncName(rs.getString("func_name"));
					list.add(permisFuncVO); // Store the row in the list
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

		
			PermisFuncJDBCDAO dao = new PermisFuncJDBCDAO();

			// 新增 ok
//			PermisFuncVO permisFuncVO1 = new PermisFuncVO();
//			permisFuncVO1.setFuncName("TEST");
//			dao.insert(permisFuncVO1);


			// 修改 ok
			PermisFuncVO perfunc = new PermisFuncVO();
			perfunc.setFuncId(18);
			perfunc.setFuncName("TEST2");

			dao.updateFunc(perfunc);

			// 刪除 ok
//			dao.delete(23);

			// 單一查詢 ok
//			PermisFuncVO findfunc = dao.findByPrimaryKey(15);
//			System.out.print(findfunc.getFuncId() + ",");
//			System.out.print(findfunc.getFuncName() + ",");
//
//
//			System.out.println("---------------------");

			// 全部查詢
//			List<PermisFuncVO> list = dao.getAll();
//			for (PermisFuncVO allFuc : list) {
//				System.out.print(allFuc.getFuncId() + ",");
//				System.out.print(allFuc.getFuncName() + ",");
//
//			}
			}
		}


	
		


