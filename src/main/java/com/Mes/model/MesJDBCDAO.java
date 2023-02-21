package com.Mes.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesJDBCDAO implements MesDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO MES (USER_ID,SEND_TITLE,SEND_CONTENT,SEND_PIC,SEND_TIME,READ_MESSENG) VALUES ( ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT MES_ID,USER_ID,SEND_TITLE,SEND_CONTENT,SEND_PIC,SEND_TIME,READ_MESSENG FROM MES WHERE USER_ID= ?  ORDER by SEND_TIME DESC ; " ;
	private static final String GET_ONE_STMT = "SELECT MES_ID,USER_ID,SEND_TITLE,SEND_CONTENT,SEND_PIC,SEND_TIME,READ_MESSENG FROM MES WHERE MES_ID= ? " ;
	private static final String DELETE = "DELETE FROM MES where MES_ID = ?";
	private static final String UPDATE = "UPDATE MES set USER_ID = ? , SEND_TITLE=? , SEND_CONTENT= ?, SEND_PIC = ?, SEND_TIME = ? ,READ_MESSENG= ? where MES_ID = ?";
	private static final String UPDATEREADMES = "UPDATE MES SET READ_MESSENG  = '0'  WHERE USER_ID like ? " ;
	@Override
	public void insert(MesVO mesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
	Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, mesVO.getUserId());
			pstmt.setString(2, mesVO.getSendTitle());
			pstmt.setString(3, mesVO.getSendContent());
			pstmt.setBytes(4, mesVO.getSendPic());
			pstmt.setTimestamp(5, mesVO.getSendTime());
			pstmt.setByte(6, mesVO.getReadMesseng());
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
	public void update(MesVO mesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, mesVO.getUserId());
			pstmt.setString(2, mesVO.getSendTitle());
			pstmt.setString(3, mesVO.getSendContent());
			pstmt.setBytes(4, mesVO.getSendPic());
			pstmt.setTimestamp(5, mesVO.getSendTime());
			pstmt.setByte(6, mesVO.getReadMesseng());
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
	public void updateReadMes(MesVO mesVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATEREADMES);

			pstmt.setInt(1, mesVO.getUserId());

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
	public void delete(Integer MES_ID) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, MES_ID);

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
	public MesVO findByPrimaryKey(Integer MES_ID) {
		MesVO mesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
				
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, MES_ID);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				mesVO = new MesVO();
				mesVO.setMesId(rs.getInt("MES_ID"));
				mesVO.setUserId(rs.getInt("USER_ID"));
				mesVO.setSendTitle(rs.getString("SEND_TITLE"));
				mesVO.setSendContent(rs.getString("SEND_CONTENT"));
				mesVO.setSendPic(rs.getBytes("SEND_PIC"));
				mesVO.setSendTime(rs.getTimestamp("SEND_TIME"));
				mesVO.setReadMesseng(rs.getByte("READ_MESSENG"));
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
		return mesVO;
	}

	@Override
	public List<MesVO> getAll(Integer USER_ID) {
		List<MesVO> list = new ArrayList<MesVO>();
		MesVO mesVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			pstmt.setInt(1, USER_ID);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				mesVO = new MesVO();
				mesVO.setMesId(rs.getInt("MES_ID"));
				mesVO.setUserId(rs.getInt("USER_ID"));
				mesVO.setSendTitle(rs.getString("SEND_TITLE"));
				mesVO.setSendContent(rs.getString("SEND_CONTENT"));
				mesVO.setSendPic(rs.getBytes("SEND_PIC"));
				mesVO.setSendTime(rs.getTimestamp("SEND_TIME"));
				mesVO.setReadMesseng(rs.getByte("READ_MESSENG"));
				list.add(mesVO); // Store the row in the list
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

		MesJDBCDAO dao = new MesJDBCDAO();
		java.util.Date utilDate = new java.util.Date();
		java.sql.Timestamp sqlTimestamp = new java.sql.Timestamp(utilDate.getTime());
		// 新增
		try (InputStream in = Files.newInputStream(Path.of("images/1.png"))){
			byte[] buf = new byte[in.available()];
			in.read(buf);
			MesVO mesVO1 = new MesVO();
			mesVO1.setUserId(1);
			mesVO1.setSendTitle("站務通知");
			mesVO1.setSendContent("請問一下");
			mesVO1.setSendPic(buf);
			mesVO1.setSendTime(sqlTimestamp);
			mesVO1.setReadMesseng((byte)0);
			dao.insert(mesVO1);
		} catch (IOException e) {
			e.printStackTrace();
		}
//		//修改
//		try (InputStream in = Files.newInputStream(Path.of("images/5.png"))){
//			byte[] buf = new byte[in.available()]; 
//			in.read(buf);
//		MesVO mesVO2 = new MesVO();
//		mesVO2.setUserId(10);
//		mesVO2.setSendContent("謝謝完成");
//		mesVO2.setSendTime(sqlTimestamp);
//		mesVO2.setReadMesseng(true);
//		mesVO2.setMesId(14);
//		dao.update(mesVO2);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		MesVO mesVO4 = new MesVO();
		mesVO4.setUserId(1);
		dao.updateReadMes(mesVO4);

		// 刪除
//		dao.delete(13);

		// 查詢
//		MesVO mesVO3 = dao.findByPrimaryKey(1);
//		System.out.print(mesVO3.getMesId() + ",");
//		System.out.print(mesVO3.getUserId() + ",");
//		System.out.print(mesVO3.getSendContent() + ",");
//		System.out.print(mesVO3.getSendTime() + ",");
//		System.out.print(mesVO3.getReadMesseng() + ",");
//		System.out.println("---------------------");

		// 查詢all
//		List<MesVO> list = dao.getAll(1);
//		for (MesVO Mes : list) {
//			System.out.print(Mes.getMesId() + ",");
//			System.out.print(Mes.getUserId() + ",");
//			System.out.print(Mes.getSendTitle() + ",");
//			System.out.print(Mes.getSendContent() + ",");
//			System.out.print(Mes.getSendTime() + ",");
//			System.out.print(Mes.getReadMesseng() + ",");
//			System.out.println();
//		}
	}
}
