package com.stock.model;

import java.util.*;

import com.orderDetail.model.OrderDetailJDBCDAO;
import com.orderDetail.model.OrderDetailVO;

import java.sql.*;
import java.time.*;

public class StockJDBCDAO implements Stock_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
			"INSERT INTO stock (room_id,stay_date,room_rest) VALUES (?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT room_id,stay_date,room_rest FROM stock order by room_id, stay_date";
	private static final String GET_ONE_STMT = 
			"SELECT room_id,stay_date,room_rest FROM stock where room_id = ? AND stay_date = ?";
	private static final String GET_BY_ROOMID = 
			"SELECT room_id,stay_date,room_rest FROM stock where room_id = ?";
	private static final String DELETE = 
			"DELETE FROM stock where room_id = ? AND stay_date = ?";
	private static final String UPDATE = 
			"UPDATE stock set room_rest=? where room_id = ? AND stay_date = ?";
	
	@Override
	public void insert(StockVO stockVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, stockVO.getRoomId());
			pstmt.setObject(2, stockVO.getStayDate());
			pstmt.setInt(3, stockVO.getRoomRest());
			
			pstmt.executeUpdate();
			
			// Handle any driver errors
		}catch (ClassNotFoundException e) {
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
	public void update(StockVO stockVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, stockVO.getRoomRest());
			pstmt.setInt(2, stockVO.getRoomId());
			pstmt.setObject(3, stockVO.getStayDate());
			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
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
	public void update(StockVO stockVO, Connection con) {

		PreparedStatement pstmt = null;
		
		try {

			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, stockVO.getRoomRest());
			pstmt.setInt(2, stockVO.getRoomId());
			pstmt.setObject(3, stockVO.getStayDate());
			pstmt.executeUpdate();
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-user");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}
		
	}
	@Override
	public void delete(Integer roomId, LocalDate stayDate) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomId);
			pstmt.setObject(2, stayDate);
			
			pstmt.executeUpdate();
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public StockVO findByPrimaryKey(Integer roomId, LocalDate stayDate) {

		StockVO stockVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, roomId);
			pstmt.setObject(2, stayDate);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				stockVO = new StockVO();
				stockVO.setRoomId(rs.getInt("room_id"));
				stockVO.setStayDate(rs.getObject("stay_date", LocalDate.class));
				stockVO.setRoomRest(rs.getInt("room_rest"));
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	
		return stockVO;
	}
	@Override
	public List<StockVO> getAll() {

		List<StockVO> list = new ArrayList<StockVO>();
		StockVO stockVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				stockVO = new StockVO();
				stockVO.setRoomId(rs.getInt("room_id"));
				stockVO.setStayDate(rs.getObject("stay_date", LocalDate.class));
				stockVO.setRoomRest(rs.getInt("room_rest"));
				list.add(stockVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return list;
	}
	@Override
	public List<StockVO> getByRoomId(Integer roomId) {
		
		List<StockVO> list = new ArrayList<StockVO>();
		StockVO stockVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_ROOMID);
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				stockVO = new StockVO();
				stockVO.setRoomId(rs.getInt("room_id"));
				stockVO.setStayDate(rs.getObject("stay_date", LocalDate.class));
				stockVO.setRoomRest(rs.getInt("room_rest"));
				list.add(stockVO);
			}
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		
		return list;
	}
	
	public static void main(String[] args) {
		StockJDBCDAO dao = new StockJDBCDAO();
		
		// 新增
		int rest[] = {10,5,5,8,15,10,4,2,8,6,2,5,5,8,
				8,3,5,5,2,5,2,5,5,5,5,5,5,5,5,5,5};
		LocalDate localDate = LocalDate.now();
		for(int i = 0; i < rest.length; i++) {
			localDate = LocalDate.now();
			for(int j = 1; j <= 210; j++) {
				StockVO stockVO = new StockVO();
				stockVO.setRoomId(i + 1);
				stockVO.setStayDate(localDate);
				stockVO.setRoomRest(rest[i]);
				dao.insert(stockVO);
				localDate = localDate.plusDays(1);
			}
			System.out.println("room" + i + " OK");
		}
		
		
		// 修改
//		StockVO stockVO1 = new StockVO();
//		stockVO1.setRoomId(2);
//		stockVO1.setStayDate(LocalDate.of(2023, 1, 4));
//		stockVO1.setRoomRest(5);
//		dao.update(stockVO1);
		
//		// 刪除
//		dao.delete(2,LocalDate.of(2023, 1, 4));
		
//		// 查詢
//		StockVO stockVO2 = dao.findByPrimaryKey(1,LocalDate.of(2023, 1, 4));
//		System.out.print(stockVO2.getRoomId() + ",");
//		System.out.print(stockVO2.getStayDate() + ",");
//		System.out.print(stockVO2.getRoomRest() + ",");
//		System.out.println("---------------------");
		
//		// 查詢
//		List<StockVO> list = dao.getAll();
//		for (StockVO aStock : list) {
//			System.out.print(aStock.getRoomId() + ",");
//			System.out.print(aStock.getStayDate() + ",");
//			System.out.print(aStock.getRoomRest() + ",");
//			System.out.println();
//		}

	}
	
}
