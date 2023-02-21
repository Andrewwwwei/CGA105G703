package com.article.model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Art_Pic {
	private Integer artPicId;
	private byte[] artPic;
	
	public Integer getArtPicId() {
		return artPicId;
	}
	public void setArtPicId(Integer artPicId) {
		this.artPicId = artPicId;
	}
	public byte[] getArtPic() {
		return artPic;
	}
	public void setArtPic(byte[] artPic) {
		this.artPic = artPic;
	}



	public String insertArtPic(Art_Pic artPic) {
		Connection con =null;
		PreparedStatement pstmt = null;
		String sql = "update article_pic set ART_PIC=? where ART_PIC_ID=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cga105_g7","root","02021");
			pstmt = con.prepareStatement(sql);
			pstmt.setBytes(1,artPic.getArtPic());
			pstmt.setInt(2, artPic.getArtPicId());
			pstmt.executeUpdate();
			return "成功";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "失敗";
		}catch (SQLException e) {
			e.printStackTrace();
			return "失敗";
		}finally {
			try {
				if(pstmt != null) {
					pstmt.close();					
				}
				if(con != null) {
					con.close();					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public byte[] getPic(String path) {
		File file = new File(path);
		try(
				FileInputStream fis = new FileInputStream(file);
				BufferedInputStream bis = new BufferedInputStream(fis);				
			) {
			byte[] data = new byte[bis.available()];
			data = bis.readAllBytes();
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return new byte[0];
		}catch (IOException e) {
			e.printStackTrace();
			return new byte[0];
		}
		
	}
	
	public static void main(String[] args) {
		for(int i = 1; i<=40; i++) {
			Art_Pic art = new Art_Pic();
			String path = "C:\\CGA105_WebApp\\eclipse_WTP_workspace1\\CGA105G7\\src\\main\\webapp\\7tour\\forum_assert\\article\\artpic\\" + i + ".png";
			art.setArtPicId(i);
			art.setArtPic(art.getPic(path));
			System.out.println(art.insertArtPic(art));			
		}
		
	}
}


























