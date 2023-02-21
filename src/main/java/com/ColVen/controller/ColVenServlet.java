package com.ColVen.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ColVen.model.ColVenService;

@WebServlet("/ColVen")
public class ColVenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("addlike".equals(action)) {
			try {
				res.setContentType("text/plain");
				Integer venId = Integer.parseInt(req.getParameter("venId"));
				if(req.getParameter("userId").isEmpty()) {
					res.getWriter().write("login");
					return;
				}
				Integer userId = Integer.parseInt(req.getParameter("userId"));
				ColVenService colVenService = new ColVenService();
				colVenService.addColVen(venId, userId);
				res.getWriter().write("ok");
				return;
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		if("cancellike".equals(action)) {
			try {
				Integer venId = Integer.parseInt(req.getParameter("venId"));
				Integer userId = Integer.parseInt(req.getParameter("userId"));
				ColVenService colVenService = new ColVenService();
				colVenService.deleteColVen(venId, userId);
				res.setContentType("text/plain");
				res.getWriter().write("ok");
				return;
			}catch(Exception e) {
				e.printStackTrace();
				return;
			}
		}
	}

}
