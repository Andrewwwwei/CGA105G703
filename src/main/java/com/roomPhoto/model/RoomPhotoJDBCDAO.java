package com.roomPhoto.model;

import java.util.*;

import com.orderDetail.model.OrderDetailJDBCDAO;
import com.orderDetail.model.OrderDetailVO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class RoomPhotoJDBCDAO implements RoomPhoto_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = 
			"INSERT INTO room_photo (room_id,room_photo) VALUES (?, ?)";
	private static final String GET_ALL_STMT = 
			"SELECT room_photo_id,room_id,room_photo FROM room_photo order by room_photo_id";
	private static final String GET_ONE_STMT = 
			"SELECT room_photo_id,room_id,room_photo FROM room_photo where room_photo_id = ?";
	private static final String DELETE = 
			"DELETE FROM room_photo where room_photo_id = ?";
	private static final String UPDATE = 
			"UPDATE room_photo set room_id=?, room_photo=? where room_photo_id = ?";
	private static final String GET_ONE_ROOM = 
			"SELECT room_photo_id,room_id,room_photo FROM room_photo where room_id = ?";
	
	@Override
	public void insert(RoomPhotoVO roomPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, roomPhotoVO.getRoomId());
			pstmt.setBytes(2, roomPhotoVO.getRoomPhoto());
			
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
	public void update(RoomPhotoVO roomPhotoVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setInt(1, roomPhotoVO.getRoomId());
			pstmt.setBytes(2, roomPhotoVO.getRoomPhoto());
			pstmt.setInt(3, roomPhotoVO.getRoomPhotoId());
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
	public void delete(Integer roomPhotoId) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setInt(1, roomPhotoId);
			
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
	public RoomPhotoVO findByPrimaryKey(Integer roomPhotoId) {

		RoomPhotoVO roomPhotoVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, roomPhotoId);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getInt("room_photo_id"));
				roomPhotoVO.setRoomId(rs.getInt("room_id"));
				roomPhotoVO.setRoomPhoto(rs.getBytes("room_photo"));
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
	
		return roomPhotoVO;
	}
	@Override
	public List<RoomPhotoVO> getAll() {

		List<RoomPhotoVO> list = new ArrayList<RoomPhotoVO>();
		RoomPhotoVO roomPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getInt("room_photo_id"));
				roomPhotoVO.setRoomId(rs.getInt("room_id"));
				roomPhotoVO.setRoomPhoto(rs.getBytes("room_photo"));
				list.add(roomPhotoVO);
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
	public List<RoomPhotoVO> getARoom(Integer roomId) {

		List<RoomPhotoVO> list = new ArrayList<RoomPhotoVO>();
		RoomPhotoVO roomPhotoVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ROOM);
			pstmt.setInt(1, roomId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				roomPhotoVO = new RoomPhotoVO();
				roomPhotoVO.setRoomPhotoId(rs.getInt("room_photo_id"));
				roomPhotoVO.setRoomId(rs.getInt("room_id"));
				roomPhotoVO.setRoomPhoto(rs.getBytes("room_photo"));
				list.add(roomPhotoVO);
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
		RoomPhotoJDBCDAO dao = new RoomPhotoJDBCDAO();
		
		// 新增
//		int j = 1;
//		for(int i = 1; i <= 150; i++ ) {
//			try (InputStream in = Files.newInputStream(Path.of("room-pic/roomPic (" + i + ").jpg"))){
//				byte[] buf = new byte[in.available()];  // byte[] buf = in.readAllBytes();  // Java 9 ���s��k
//				in.read(buf);
//				RoomPhotoVO roomPhotoVO = new RoomPhotoVO();
//				roomPhotoVO.setRoomId(j);
//				roomPhotoVO.setRoomPhoto(buf);
//				dao.insert(roomPhotoVO);
//				if(i % 5 == 0) {
//					j++;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
		
		// 修改
		int j = 1;
		for(int i = 1; i <= 155; i++ ) {
			try (InputStream in = Files.newInputStream(Path.of("room-pic/roomPic (" + i + ").jpg"))){
				byte[] buf = new byte[in.available()];  // byte[] buf = in.readAllBytes();  // Java 9 ���s��k
				in.read(buf);
				RoomPhotoVO roomPhotoVO1 = new RoomPhotoVO();
				roomPhotoVO1.setRoomPhotoId(i);
				roomPhotoVO1.setRoomId(j);
				roomPhotoVO1.setRoomPhoto(buf);
				dao.update(roomPhotoVO1);
				if(i % 5 == 0) {
					j++;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		try (InputStream in = Files.newInputStream(Path.of("room-pic/2004.jpg"))){
//			byte[] buf = new byte[in.available()]; 
//			in.read(buf);
//			RoomPhotoVO roomPhotoVO1 = new RoomPhotoVO();
//			roomPhotoVO1.setRoomPhotoId(10);
//			roomPhotoVO1.setRoomId(1);
//			roomPhotoVO1.setRoomPhoto(buf);
//			dao.update(roomPhotoVO1);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 刪除
//		dao.delete(8);
		
		// 查詢
//		RoomPhotoVO roomPhoto2 = dao.findByPrimaryKey(3);
//		System.out.print(roomPhoto2.getRoomPhotoId() + ",");
//		System.out.print(roomPhoto2.getRoomId() + ",");
//		System.out.println("---------------------");
//		try (FileOutputStream fos = new FileOutputStream(new File("Path"))){
//			fos.write(roomPhoto2.getRoomPhoto());
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		// 查詢
//		List<RoomPhotoVO> list = dao.getAll();
//		for (RoomPhotoVO aOrderDetail : list) {
//			System.out.print(aOrderDetail.getRoomPhotoId() + ",");
//			System.out.print(aOrderDetail.getRoomId() + ",");
//			System.out.println();
//		}

	}
	
}
