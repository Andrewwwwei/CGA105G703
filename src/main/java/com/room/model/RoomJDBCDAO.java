package com.room.model;

import java.sql.*;
import java.util.*;


public class RoomJDBCDAO implements Room_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
			"INSERT INTO room (ven_id,room_name,room_amount,room_people,room_price,room_area,room_intro,room_update,room_view,"
					+ "breakfast,air_condotioner,wifi,television,safebox,fridge,water_boiler,bathroom,toiletries)" 
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT room_id,ven_id,room_name,room_amount,room_people,room_price,room_area,room_intro,room_update,room_view,"
			+ "breakfast,air_condotioner,wifi,television,safebox,fridge,water_boiler,bathroom,toiletries FROM room order by room_id";
	private static final String GET_ONE_STMT = 
			"SELECT room_id,ven_id,room_name,room_amount,room_people,room_price,room_area,room_intro,room_update,room_view,"
			+ "breakfast,air_condotioner,wifi,television,safebox,fridge,water_boiler,bathroom,toiletries FROM room where room_id = ?";
	private static final String DELETE = 
			"DELETE FROM room where room_id = ?";
	private static final String UPDATE = 
			"UPDATE room set ven_id=?, room_name=?, room_amount=?, room_people=?, room_price=?, room_area=?, room_intro=?, room_update=?, room_view=?, "
			+ "breakfast=?, air_condotioner=?, wifi=?, television=?, safebox=?, fridge=?, water_boiler=?, bathroom=?, toiletries=? where room_id = ?";
	private static final String GET_BY_VEN = 
			"SELECT room_id,ven_id,room_name,room_amount,room_people,room_price,room_area,room_intro,room_update,room_view,"
			+ "breakfast,air_condotioner,wifi,television,safebox,fridge,water_boiler,bathroom,toiletries FROM room where ven_id = ?";
	@Override
	public void insert(RoomVO roomVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, roomVO.getVenId());
			pstmt.setString(2, roomVO.getRoomName());
			pstmt.setInt(3, roomVO.getRoomAmount());
			pstmt.setInt(4, roomVO.getRoomPeople());
			pstmt.setInt(5, roomVO.getRoomPrice());
			pstmt.setInt(6, roomVO.getRoomArea());
			pstmt.setString(7, roomVO.getRoomIntro());
			pstmt.setInt(8, roomVO.getRoomUpdate());
			pstmt.setInt(9, roomVO.getRoomView());
			pstmt.setInt(10, roomVO.getBreakfast());
			pstmt.setInt(11, roomVO.getAirCondotioner());
			pstmt.setInt(12, roomVO.getWifi());
			pstmt.setInt(13, roomVO.getTelevision());
			pstmt.setInt(14, roomVO.getSafebox());
			pstmt.setInt(15, roomVO.getFridge());
			pstmt.setInt(16, roomVO.getWaterBoiler());
			pstmt.setInt(17, roomVO.getBathroom());
			pstmt.setInt(18, roomVO.getToiletries());
			
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
	public void update(RoomVO roomVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomVO.getVenId());
			pstmt.setString(2, roomVO.getRoomName());
			pstmt.setInt(3, roomVO.getRoomAmount());
			pstmt.setInt(4, roomVO.getRoomPeople());
			pstmt.setInt(5, roomVO.getRoomPrice());
			pstmt.setInt(6, roomVO.getRoomArea());
			pstmt.setString(7, roomVO.getRoomIntro());
			pstmt.setInt(8, roomVO.getRoomUpdate());
			pstmt.setInt(9, roomVO.getRoomView());
			pstmt.setInt(10, roomVO.getBreakfast());
			pstmt.setInt(11, roomVO.getAirCondotioner());
			pstmt.setInt(12, roomVO.getWifi());
			pstmt.setInt(13, roomVO.getTelevision());
			pstmt.setInt(14, roomVO.getSafebox());
			pstmt.setInt(15, roomVO.getFridge());
			pstmt.setInt(16, roomVO.getWaterBoiler());
			pstmt.setInt(17, roomVO.getBathroom());
			pstmt.setInt(18, roomVO.getToiletries());
			pstmt.setInt(19, roomVO.getRoomId());
			
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
	public void delete(Integer roomId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomId);
			
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
	public RoomVO findByPrimaryKey(Integer roomId) {

		RoomVO roomVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, roomId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getInt("room_id"));
				roomVO.setVenId(rs.getInt("ven_id"));
				roomVO.setRoomName(rs.getString("room_name"));
				roomVO.setRoomAmount(rs.getInt("room_amount"));
				roomVO.setRoomPeople(rs.getInt("room_people"));
				roomVO.setRoomPrice(rs.getInt("room_price"));
				roomVO.setRoomArea(rs.getInt("room_area"));
				roomVO.setRoomIntro(rs.getString("room_intro"));
				roomVO.setRoomUpdate(rs.getInt("room_update"));
				roomVO.setRoomView(rs.getInt("room_view"));
				roomVO.setBreakfast(rs.getInt("breakfast"));
				roomVO.setAirCondotioner(rs.getInt("air_condotioner"));
				roomVO.setWifi(rs.getInt("wifi"));
				roomVO.setTelevision(rs.getInt("television"));
				roomVO.setSafebox(rs.getInt("safebox"));
				roomVO.setFridge(rs.getInt("fridge"));
				roomVO.setWaterBoiler(rs.getInt("water_boiler"));
				roomVO.setBathroom(rs.getInt("bathroom"));
				roomVO.setToiletries(rs.getInt("toiletries"));
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
	
		return roomVO;
	}
	@Override
	public List<RoomVO> getAll() {

		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getInt("room_id"));
				roomVO.setVenId(rs.getInt("ven_id"));
				roomVO.setRoomName(rs.getString("room_name"));
				roomVO.setRoomAmount(rs.getInt("room_amount"));
				roomVO.setRoomPeople(rs.getInt("room_people"));
				roomVO.setRoomPrice(rs.getInt("room_price"));
				roomVO.setRoomArea(rs.getInt("room_area"));
				roomVO.setRoomIntro(rs.getString("room_intro"));
				roomVO.setRoomUpdate(rs.getInt("room_update"));
				roomVO.setRoomView(rs.getInt("room_view"));
				roomVO.setBreakfast(rs.getInt("breakfast"));
				roomVO.setAirCondotioner(rs.getInt("air_condotioner"));
				roomVO.setWifi(rs.getInt("wifi"));
				roomVO.setTelevision(rs.getInt("television"));
				roomVO.setSafebox(rs.getInt("safebox"));
				roomVO.setFridge(rs.getInt("fridge"));
				roomVO.setWaterBoiler(rs.getInt("water_boiler"));
				roomVO.setBathroom(rs.getInt("bathroom"));
				roomVO.setToiletries(rs.getInt("toiletries"));
				list.add(roomVO);
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
	
	public List<RoomVO> getAllByVen(Integer venId) {
		
		List<RoomVO> list = new ArrayList<RoomVO>();
		RoomVO roomVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_BY_VEN);
			pstmt.setInt(1, venId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomVO = new RoomVO();
				roomVO.setRoomId(rs.getInt("room_id"));
				roomVO.setVenId(rs.getInt("ven_id"));
				roomVO.setRoomName(rs.getString("room_name"));
				roomVO.setRoomAmount(rs.getInt("room_amount"));
				roomVO.setRoomPeople(rs.getInt("room_people"));
				roomVO.setRoomPrice(rs.getInt("room_price"));
				roomVO.setRoomArea(rs.getInt("room_area"));
				roomVO.setRoomIntro(rs.getString("room_intro"));
				roomVO.setRoomUpdate(rs.getInt("room_update"));
				roomVO.setRoomView(rs.getInt("room_view"));
				roomVO.setBreakfast(rs.getInt("breakfast"));
				roomVO.setAirCondotioner(rs.getInt("air_condotioner"));
				roomVO.setWifi(rs.getInt("wifi"));
				roomVO.setTelevision(rs.getInt("television"));
				roomVO.setSafebox(rs.getInt("safebox"));
				roomVO.setFridge(rs.getInt("fridge"));
				roomVO.setWaterBoiler(rs.getInt("water_boiler"));
				roomVO.setBathroom(rs.getInt("bathroom"));
				roomVO.setToiletries(rs.getInt("toiletries"));
				list.add(roomVO);
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
		RoomJDBCDAO dao = new RoomJDBCDAO();
		
		// 新增
//		RoomVO roomVO = new RoomVO();
//		roomVO.setVenId(500000);
//		roomVO.setRoomName("");
//		roomVO.setRoomAmount(1);
//		roomVO.setRoomPeople(2);
//		roomVO.setRoomPrice(1000);
//		roomVO.setRoomArea(10);
//		roomVO.setRoomIntro("");
//		roomVO.setRoomUpdate(1);
//		roomVO.setRoomView(0);
//		roomVO.setBreakfast(0);
//		roomVO.setAirCondotioner(1);
//		roomVO.setWifi(1);
//		roomVO.setTelevision(1);
//		roomVO.setSafebox(0);
//		roomVO.setFridge(0);
//		roomVO.setWaterBoiler(1);
//		roomVO.setBathroom(1);
//		roomVO.setToiletries(1);
//		dao.insert(roomVO);
		
		
		// 修改
//		RoomVO roomVO1 = new RoomVO();
//		roomVO1.setRoomId(6);
//		roomVO1.setVenId(500000);
//		roomVO1.setRoomName("恩恩");
//		roomVO1.setRoomAmount(1);
//		roomVO1.setRoomPeople(2);
//		roomVO1.setRoomPrice(1000);
//		roomVO1.setRoomArea(10);
//		roomVO1.setRoomIntro("");
//		roomVO1.setRoomUpdate(1);
//		roomVO1.setRoomView(0);
//		roomVO1.setBreakfast(0);
//		roomVO1.setAirCondotioner(1);
//		roomVO1.setWifi(1);
//		roomVO1.setTelevision(1);
//		roomVO1.setSafebox(0);
//		roomVO1.setFridge(0);
//		roomVO1.setWaterBoiler(1);
//		roomVO1.setBathroom(1);
//		roomVO1.setToiletries(1);
//		dao.update(roomVO1);
		
		// 刪除
//		dao.delete(6);
		
		// 查詢
//		RoomVO roomVO2 = dao.findByPrimaryKey(3);
//		System.out.print(roomVO2.getRoomId() + ",");
//		System.out.print(roomVO2.getVenId() + ",");
//		System.out.print(roomVO2.getRoomName() + ",");
//		System.out.print(roomVO2.getRoomAmount() + ",");
//		System.out.print(roomVO2.getRoomPeople() + ",");
//		System.out.print(roomVO2.getRoomPrice() + ",");
//		System.out.println(roomVO2.getRoomArea());
//		System.out.println(roomVO2.getRoomIntro());
//		System.out.println(roomVO2.getRoomUpdate());
//		System.out.println(roomVO2.getRoomView());
//		System.out.println(roomVO2.getBreakfast());
//		System.out.println(roomVO2.getAirCondotioner());
//		System.out.println(roomVO2.getWifi());
//		System.out.println(roomVO2.getTelevision());
//		System.out.println(roomVO2.getSafebox());
//		System.out.println(roomVO2.getFridge());
//		System.out.println(roomVO2.getWaterBoiler());
//		System.out.println(roomVO2.getBathroom());
//		System.out.println(roomVO2.getToiletries());
//		System.out.println("---------------------");
		
		// 查詢
		List<RoomVO> list = dao.getAll();
		for (RoomVO aRoom : list) {
			System.out.print(aRoom.getRoomId() + ",");
			System.out.print(aRoom.getVenId() + ",");
			System.out.print(aRoom.getRoomName() + ",");
			System.out.print(aRoom.getRoomAmount() + ",");
			System.out.print(aRoom.getRoomPeople() + ",");
			System.out.print(aRoom.getRoomPrice() + ",");
			System.out.print(aRoom.getRoomArea() + ",");
			System.out.print(aRoom.getRoomIntro() + ",");
			System.out.print(aRoom.getRoomUpdate() + ",");
			System.out.print(aRoom.getRoomView() + ",");
			System.out.print(aRoom.getBreakfast() + ",");
			System.out.print(aRoom.getAirCondotioner() + ",");
			System.out.print(aRoom.getWifi() + ",");
			System.out.print(aRoom.getTelevision() + ",");
			System.out.print(aRoom.getSafebox() + ",");
			System.out.print(aRoom.getFridge() + ",");
			System.out.print(aRoom.getWaterBoiler() + ",");
			System.out.print(aRoom.getBathroom() + ",");
			System.out.print(aRoom.getToiletries() + ",");
			System.out.println();
		}

	}
}



