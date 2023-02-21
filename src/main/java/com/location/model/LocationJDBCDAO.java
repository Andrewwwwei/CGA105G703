package com.location.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import com.locationPic.model.LocationPicJDBCDAO;
import com.locationPic.model.LocationPicVO;

public class LocationJDBCDAO implements LocationDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "02021";

	private static final String INSERT_STMT = "INSERT INTO location (USER_ID,LOC_NAME,LONGITUDE,LATITUDE,LOC_ADDRESS,LOC_PHONE,LOC_STATUS) VALUES (?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE location set USER_ID=?, LOC_NAME=?, LONGITUDE=?, LATITUDE=?, LOC_ADDRESS=?,LOC_PHONE=?,LOC_STATUS=? where LOC_ID = ?";
	private static final String DELETE = "DELETE FROM location where LOC_ID = ?";
	private static final String GET_ALL_STMT = "SELECT LOC_ID,USER_ID,LOC_NAME,LONGITUDE,LATITUDE,LOC_ADDRESS,LOC_PHONE,LOC_STATUS FROM location where LOC_STATUS != 2 order by LOC_ID desc";
	private static final String GET_ONE_STMT = "SELECT LOC_ID,USER_ID,LOC_NAME,LONGITUDE,LATITUDE,LOC_ADDRESS,LOC_PHONE,LOC_STATUS FROM location where LOC_ID = ?";
	private static final String GET_GROUP ="SELECT LOC_ID,USER_ID,LOC_NAME,LONGITUDE,LATITUDE,LOC_ADDRESS,LOC_PHONE,LOC_STATUS FROM location WHERE concat(LOC_NAME,LOC_ADDRESS,LOC_PHONE) like ? and LOC_STATUS in(?,?)";
	private static final String GET_FK_USERID = "SELECT LOC_ID,USER_ID,LOC_NAME,LONGITUDE,LATITUDE,LOC_ADDRESS,LOC_PHONE,LOC_STATUS FROM location WHERE USER_ID=? order by LOC_ID desc";

	@Override
	public String insertHasPic(LocationVO locationVO, Collection<Part> locPic) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String nextLocID = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);

			con.setAutoCommit(false);
			String cols[] = { "LOC_ID" };
			pstmt = con.prepareStatement(INSERT_STMT, cols);
			pstmt.setObject(1, locationVO.getUserId());
			pstmt.setString(2, locationVO.getLocName());
			pstmt.setString(3, locationVO.getLongitude());
			pstmt.setString(4, locationVO.getLatitude());
			pstmt.setString(5, locationVO.getLocAddress());
			pstmt.setString(6, locationVO.getLocPhone());
			pstmt.setInt(7, locationVO.getLocStatus());
			pstmt.executeUpdate();

			// 得到對應的自增PK
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				nextLocID = rs.getString(1);
				System.out.println("自增PK : " + nextLocID + "(成功新增)");
			} else {
				System.out.println("新增失敗 沒有PK");
			}

			LocationPicJDBCDAO locPicDAO = new LocationPicJDBCDAO();
			LocationPicVO locPicVO = new LocationPicVO();
			for (Part pic : locPic) {
				String filename = pic.getSubmittedFileName();
				if (filename != null && filename.length() != 0 && pic.getContentType() != null) {
					try {
						InputStream ins = pic.getInputStream();
						byte[] data = new byte[ins.available()];
						data = ins.readAllBytes();

						data = pic.getInputStream().readAllBytes();
						locPicVO.setLocId(Integer.parseInt(nextLocID));
						locPicVO.setLocPic(data);
						locPicDAO.insertHasPic(locPicVO, con);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			con.commit();
			con.setAutoCommit(true);

			System.out.println("成功!!");
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("Couldn't rollback" + e1.getMessage());
			}
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException("Couldn't rollback" + e1.getMessage());
			}
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
		return nextLocID;
	}

	@Override
	public void update(LocationVO locationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setObject(1, locationVO.getUserId());
			pstmt.setString(2, locationVO.getLocName());
			pstmt.setString(3, locationVO.getLongitude());
			pstmt.setString(4, locationVO.getLatitude());
			pstmt.setString(5, locationVO.getLocAddress());
			pstmt.setString(6, locationVO.getLocPhone());
			pstmt.setInt(7, locationVO.getLocStatus());
			pstmt.setInt(8, locationVO.getLocId());
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
	public void delete(Integer locId) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, locId);

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
	public LocationVO findByPrimaryKey(Integer locId) {
		LocationVO locationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, locId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocId(rs.getInt("LOC_ID"));
				locationVO.setUserId(rs.getInt("USER_ID"));
				locationVO.setLocName(rs.getString("LOC_NAME"));
				locationVO.setLongitude(rs.getString("LONGITUDE"));
				locationVO.setLatitude(rs.getString("LATITUDE"));
				locationVO.setLocAddress(rs.getString("LOC_ADDRESS"));
				locationVO.setLocPhone(rs.getString("LOC_PHONE"));
				locationVO.setLocStatus(rs.getInt("LOC_STATUS"));
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
		return locationVO;
	}

	@Override
	public List<LocationVO> getGroup(String searchWord, Integer... locStatus){
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_GROUP);
			pstmt.setString(1, "%"+searchWord+"%");
			pstmt.setInt(2, locStatus[0]);
			if (locStatus.length == 1) {
				pstmt.setObject(3, null);
			}else {
				pstmt.setInt(3, locStatus[1]);				
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocId(rs.getInt("LOC_ID"));
				locationVO.setUserId(rs.getInt("USER_ID"));
				locationVO.setLocName(rs.getString("LOC_NAME"));
				locationVO.setLongitude(rs.getString("LONGITUDE"));
				locationVO.setLatitude(rs.getString("LATITUDE"));
				locationVO.setLocAddress(rs.getString("LOC_ADDRESS"));
				locationVO.setLocPhone(rs.getString("LOC_PHONE"));
				locationVO.setLocStatus(rs.getInt("LOC_STATUS"));

				list.add(locationVO); // Store the row in the list
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
	
	@Override
	public List<LocationVO> findByForeignKey(Integer userId){
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_FK_USERID);
			pstmt.setInt(1, userId);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				locationVO = new LocationVO();
				locationVO.setLocId(rs.getInt("LOC_ID"));
				locationVO.setUserId(rs.getInt("USER_ID"));
				locationVO.setLocName(rs.getString("LOC_NAME"));
				locationVO.setLongitude(rs.getString("LONGITUDE"));
				locationVO.setLatitude(rs.getString("LATITUDE"));
				locationVO.setLocAddress(rs.getString("LOC_ADDRESS"));
				locationVO.setLocPhone(rs.getString("LOC_PHONE"));
				locationVO.setLocStatus(rs.getInt("LOC_STATUS"));

				list.add(locationVO); // Store the row in the list
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
	
	@Override
	public List<LocationVO> getAll() {
		List<LocationVO> list = new ArrayList<LocationVO>();
		LocationVO locationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			//GETALL but not contain status is 2 cuz 2 is only user read
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
					locationVO = new LocationVO();
					locationVO.setLocId(rs.getInt("LOC_ID"));
					locationVO.setUserId(rs.getInt("USER_ID"));
					locationVO.setLocName(rs.getString("LOC_NAME"));
					locationVO.setLongitude(rs.getString("LONGITUDE"));
					locationVO.setLatitude(rs.getString("LATITUDE"));
					locationVO.setLocAddress(rs.getString("LOC_ADDRESS"));
					locationVO.setLocPhone(rs.getString("LOC_PHONE"));
					locationVO.setLocStatus(rs.getInt("LOC_STATUS"));
					
					list.add(locationVO); // Store the row in the list					
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
	
}
