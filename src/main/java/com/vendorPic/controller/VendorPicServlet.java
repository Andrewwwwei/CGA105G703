package com.vendorPic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vendorPic.model.VendorPicService;
import com.vendorPic.model.VendorPicVO;

@WebServlet("/VendorPic")
public class VendorPicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if("getPicById".equals(action)) {
			res.setContentType("image/jpeg");
			Integer venPicid = Integer.parseInt(req.getParameter("venPicid"));
			VendorPicService vendorPicService = new VendorPicService();
			VendorPicVO vendorPicVO = vendorPicService.getOneVendorpic(venPicid);
			byte[] pic = vendorPicVO.getVenPic();
			try (ServletOutputStream out = res.getOutputStream()){
				out.write(pic);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
			
		
		
	}

}
