package com.article.model;

import java.sql.*;
import java.io.*;

class PhotoWrite2 {

	public static void main(String argv[]) {
		Connection con = null;
		PreparedStatement pstmt = null;
		InputStream fin = null;
		String url = "jdbc:mysql://localhost:3306/cga105_g7?serverTimezone=Asia/Taipei";
		String userid = "root";
		String passwd = "02021";
		String photos = "/src/main/webapp/front-end/article/image/artpic_person"; //測試用圖片已置於【專案錄徑】底下的【/7tour/forum_assert/article/artpic】目錄內
		String update = "update users set user_pic =? where user_Id=?";

		int count =1 ;
		try {
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(update);
			File[] photoFiles = new File(photos).listFiles();
			for (File f : photoFiles) {
				fin = new FileInputStream(f);
				pstmt = con.prepareStatement(update);
				pstmt.setInt(2, count);
				pstmt.setBinaryStream(1, fin);
				pstmt.executeUpdate();
				count++;
				System.out.print(" update the database...");
				System.out.println(f.toString());
			}

			fin.close();
			pstmt.close();
			System.out.println("加入圖片-更新成功.........");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
