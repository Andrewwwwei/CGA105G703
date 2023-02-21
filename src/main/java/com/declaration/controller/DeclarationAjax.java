package com.declaration.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.jedis.*;
import com.declaration.model.DeclarationService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.Users.model.UsersVO;




@WebServlet("/back-end/declaration/ajax")
//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@MultipartConfig()
public class DeclarationAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		String action = request.getParameter("ajaxAction");
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		if("usersJson".equals(action)) {
			List<UsersVO> list = null;
		    String input = request.getParameter("input").trim();
	
		    String sqlCondition = " WHERE USER_ID LIKE '%"+input+"%' OR USER_NAME LIKE '%"+input+"%'";
		    
		    DeclarationService decSvc = new DeclarationService();
		    list = decSvc.getUsers(sqlCondition);
		    
		    String json = gson.toJson(list);
		    response.getWriter().write(json);
		    return;
		}
		if("annouceJson".equals(action)) {
			List<Integer> announcelist = new ArrayList<>();
			String memIDs = request.getParameter("memIDs");
			String locations = request.getParameter("locations");
			
			int[] memIDArray = gson.fromJson(memIDs, int[].class);
			String[] locationsArray = gson.fromJson(locations, String[].class);

			DeclarationService decSvc = new DeclarationService();
			List<UsersVO> userList = decSvc.getUsers("");
			
			for(String loc : locationsArray) {
				List<Integer> filterList = userList.stream()
													.filter( user -> user.getUserAddress().contains(loc) )
													.map(user -> user.getUserId())
													.collect(Collectors.toList());
				announcelist.addAll(filterList);
			}
			for(int id : memIDArray) {
				List<Integer> filterList = userList.stream()
						.filter( user -> user.getUserId()==id )
						.map(user -> user.getUserId())
						.collect(Collectors.toList());
				announcelist.addAll(filterList);
			}
			
			
		    String annouceListJson = gson.toJson(announcelist);

		    response.getWriter().write(annouceListJson);
			return;
		}
		if("notice".equals(action)) {
			 String userID = request.getParameter("userId");
			 List<String> notice = JedisHandler.getNotice(userID);
			 response.getWriter().write(gson.toJson(notice));
		}
	}
}
