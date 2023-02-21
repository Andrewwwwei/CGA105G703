package com.roomPhoto.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

import com.roomPhoto.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)     
@WebServlet("/RoomPhoto")
public class RoomPhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		doPost(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException , IOException{
		
		req.setCharacterEncoding("utf-8");
		String action = req.getParameter("action");

		// 顯示第一張圖片
		if ("showFirstImages".equals(action)) {
			res.setContentType("image/jpeg");
			/*************************** 1.接收請求參數 ****************************************/
			Integer roomId = new Integer(req.getParameter("roomId"));
			/***************************
			 * 2.開始查詢資料，篩選資料
			 ****************************************/
			RoomPhotoService roomPhotoService = new RoomPhotoService();
			List<RoomPhotoVO> images = roomPhotoService.getThisRoomPhoto(roomId);
			/*************************** 3.輸出圖片 ************/
			byte[] content = images.get(0).getRoomPhoto();
			try (ServletOutputStream out = res.getOutputStream()){
				out.write(content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		// 顯示指定ID圖片
		if ("showImagesByPicId".equals(action)) {
			res.setContentType("image/jpeg");
			/*************************** 1.接收請求參數 ****************************************/
			Integer roomPhotoId = new Integer(req.getParameter("roomPhotoId"));
			/***************************
			 * 2.開始查詢資料，篩選資料
			 ****************************************/
			RoomPhotoService roomPhotoService = new RoomPhotoService();
			RoomPhotoVO roomPhotoVO = roomPhotoService.getOneRoomPhoto(roomPhotoId);
			/*************************** 3.輸出圖片 ************/
			byte[] content = roomPhotoVO.getRoomPhoto();
			try (ServletOutputStream out = res.getOutputStream()){
				out.write(content);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		if("insertRoomPic".equals(action)) { // 新增房型圖片
			Integer roomId = Integer.parseInt(req.getParameter("roomId"));
			try {
				Collection<Part> parts = req.getParts(); 
				for (Part part : parts) {
					String type = part.getContentType();
					if("image/jpeg".equals(type) || "image/png".equals(type)) {
						InputStream in = part.getInputStream();
						byte[] buf = new byte[in.available()];   // byte[] buf = in.readAllBytes();  // Java 9 的新方法
						in.read(buf);
						in.close();
						RoomPhotoService roomPhotoService = new RoomPhotoService();
						roomPhotoService.addRoomPhoto(roomId, buf);
					}else if("application/octet-stream".equals(type)) {
						String url = "/RoomBackEnd?action=toRoomPic&roomId=" + roomId + "&upload=empty";
						req.getRequestDispatcher(url).forward(req, res);
						return;
					}
				}
				String url = "/RoomBackEnd?action=toRoomPic&roomId=" + roomId + "&upload=ok";
				req.getRequestDispatcher(url).forward(req, res);
				return;
			}catch (IllegalStateException e) {
				req.setAttribute("errorMsg", "檔案大小過大");
				String url = "/RoomBackEnd?action=toRoomPic&roomId=" + roomId + "&upload=big";
				req.getRequestDispatcher(url).forward(req, res);
				return;
			}
		}
		
		if("deleteRoomPic".equals(action)) { //刪除房型圖片
			Integer roomPhotoId = Integer.parseInt(req.getParameter("roomPhotoId"));
			RoomPhotoService roomPhotoService = new RoomPhotoService();
			Integer roomId = roomPhotoService.getOneRoomPhoto(roomPhotoId).getRoomId();
			roomPhotoService.deleteRoomPhoto(roomPhotoId);
			String url = "/RoomBackEnd?action=toRoomPic&roomId=" + roomId + "&upload=delete";
			req.getRequestDispatcher(url).forward(req, res);
			return;
		}
	}
}
