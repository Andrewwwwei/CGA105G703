package com.locationPic.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Part;

import com.location.model.LocationVO;

public class LocationPicJDBCDAO implements LocationPicDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";
	
	private static final String INSERT_STMT = 
			"INSERT INTO location_pic (LOC_ID,LOC_PIC) VALUES (?,?)";
	private static final String DELETE = 
			"DELETE FROM location_pic where LOC_PIC_ID = ?";
	private static final String GET_GROUP_STMT = 
			"SELECT LOC_PIC_ID,LOC_ID,LOC_PIC FROM location_pic where LOC_ID = ?";
	private static final String GET_ONE_STMT = 
			"SELECT LOC_PIC_ID,LOC_ID,LOC_PIC FROM location_pic where LOC_PIC_ID = ?";
	
	@Override
	public void insert(LocationPicVO locationPicVO,Collection<Part> locPic) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			for(Part pic : locPic) {
				String filename = pic.getSubmittedFileName();
				if(filename != null && filename.length() != 0 && pic.getContentType() != null) {
					try {
						InputStream ins = pic.getInputStream();
						byte[] data = new byte[ins.available()];
						data = ins.readAllBytes();
						pstmt.setInt(1, locationPicVO.getLocId());
						pstmt.setBytes(2, data);
						pstmt.executeUpdate();
					} catch (IOException e) {
						System.out.println("圖片輸入錯誤");
						e.printStackTrace();
					}
					
				}
			}
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		}finally {
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
	public void insertHasPic(LocationPicVO locationPicVO, Connection con) {
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1, locationPicVO.getLocId());
			pstmt.setBytes(2, locationPicVO.getLocPic());
			pstmt.executeUpdate();
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
			// Clean up JDBC resources
		}
		
	}
	
	@Override
	public void delete(Integer locPicId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, locPicId);

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
	public List<LocationPicVO> findByForeignKey(Integer locId) {
		List<LocationPicVO> list = new ArrayList<LocationPicVO>();
		LocationPicVO locationPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GROUP_STMT);
			pstmt.setInt(1, locId);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				locationPicVO = new LocationPicVO();
				locationPicVO.setLocPicId(rs.getInt("LOC_PIC_ID"));
				locationPicVO.setLocId(rs.getInt("LOC_ID"));
				locationPicVO.setLocPic(rs.getBytes("LOC_PIC"));
				list.add(locationPicVO);
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public LocationPicVO findByPrimaryKey(Integer locPicId) {
		LocationPicVO locationPicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, locPicId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationPicVO = new LocationPicVO();
				locationPicVO.setLocPicId(rs.getInt("LOC_PIC_ID"));
				locationPicVO.setLocId(rs.getInt("LOC_ID"));
				locationPicVO.setLocPic(rs.getBytes("LOC_PIC"));
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
		return locationPicVO;
	}

}
