package com.declaration.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.declaration.model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.Users.model.UsersVO;

import redis.clients.jedis.Jedis;



//@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@MultipartConfig()
@WebServlet(urlPatterns = {"/back-end/declaration/"})
public class DeclarationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		DeclarationService decSvc = new DeclarationService();
		List<DeclarationVO> listAll ;
		
		if("announce".equals(action)) {
			//	Jedis
			Jedis jedis = new Jedis("localhost", 6379);
			//	Gson
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			//	Parameter
			String[] locationStrArr =  request.getParameterValues("location");
			String[] memStrArr = request.getParameterValues("memID");
			String decIdStr = request.getParameter("declarationID");
			

	        
			//	decID get VO
			int decID = Integer.valueOf(decIdStr);
			DeclarationVO decVO = decSvc.getOneDeclaration(decID);
			
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	        java.util.Date date = new java.util.Date();
	        String now = dateFormat.format(date);
	        decVO.setAnnounceTime(now);
			decVO.setDate(null);
			//	sql get memList
			//  *** 有空改成 VO.getAddress.contain(""); 判斷  
			
			/*	List<UsersVO> memList = decSvc.getUsers("");
			 * 	List<UsersVO> announceMemList = new ArrayList<UsersVO>() ;
			 *  
			 *  for(location : locationStrArr){
			 * 		for( e : memList){
			 * 			e.getAddress().contain(location);
			 * 			announceMemList.add(e);
			 * 		}
			 * 	}
			 * 	
			 * 	for(id : memStrArr){
			 * 		UsersVO user = usersSvc.getUsers(id);
			 * 		announceMemList.add(user);
			 * 	}
			 * 	
			 * 	
			 * 
			 * */
			String sqlCondition = "" ;
			
			if ( locationStrArr != null) {
			    List<String> selected = Arrays.asList(locationStrArr);
			    for(String str : selected) {
			    	if (sqlCondition.length() == 0) 
			    		sqlCondition += " WHERE USER_ADDRESS LIKE '%"+str+"%'";
			    	else{
			    	sqlCondition += " OR USER_ADDRESS LIKE '%"+str+"%'";
			    	}
			    }
			}
			if ( memStrArr != null) {
			    List<String> selected = Arrays.asList(memStrArr);
			    for(String str : selected) {
			    	if (sqlCondition.length() == 0) 
			    		sqlCondition += " WHERE USER_ID = "+str;
			    	else{
			    	sqlCondition += " OR USER_ID = "+str;
			    	}
			    }
			}
			System.out.println(sqlCondition);
			List<UsersVO> announceMemList = decSvc.getUsers(sqlCondition);
			String decVOStr = gson.toJson(decVO);
			
		    // memList forEach  store the decVO Info into Redis.
		    for(UsersVO user : announceMemList) {
		    	int id = user.getUserId();	
		    	jedis.lpush("dec:"+id, decVOStr);
		    }		    return;
		}
		
		if("aside".equals(action)) {
			listAll = decSvc.getAll();
			request.setAttribute("list", listAll);
			RequestDispatcher failureView = request
					.getRequestDispatcher("/back-end/declaration/declarationMainPage.jsp");
			failureView.forward(request, response);
			return;
		}
		if("showPic".equals(action)) {
			Integer id = Integer.valueOf(request.getParameter("decID"));
			byte[] pic = decSvc.getPic(id);
			if(pic == null || pic.length == 0) {
				PrintWriter out = response.getWriter();
				out.print("無此圖片");
				return;
			}
			OutputStream outs = response.getOutputStream();
			outs.write(pic);
			outs.flush();
			return;
		}
		
		
		
		if ("insert".equals(action)) { 
			Map<String,String> insertErrorMsgs = new LinkedHashMap<String,String>();
			request.setAttribute("insertErrorMsgs", insertErrorMsgs);
			String title = request.getParameter("decTitle");
			String content = request.getParameter("decContent");
			
			// Validation
				if(title == null || title.trim().length()==0) 
					insertErrorMsgs.put("title","請輸入標題 !");
				if(content == null || content.trim().length()==0)
					insertErrorMsgs.put("content", "請輸入內容 !");

				DeclarationVO decVO = new DeclarationVO();
				listAll = decSvc.getAll();
				request.setAttribute("list", listAll);
				request.setAttribute("decVO", decVO);
				decVO.setTitle(title);
				decVO.setContent(content);
				
				if (!insertErrorMsgs.isEmpty()) {
					request.setAttribute("modalStatus", 1);
					
					RequestDispatcher failureView = request
							.getRequestDispatcher("/back-end/declaration/declarationMainPage.jsp");
					failureView.forward(request, response);
					
					return;
				}
				
				Part picPart = request.getPart("decPic");
				
			//Insert without pic 
				if(picPart == null || picPart.getSize() ==0) {
					decVO.setDeclarationID((decSvc.addDeclaration( title, content, null).getDeclarationID()));
					insertErrorMsgs.clear();
					request.setAttribute("modalStatus", 5);
					listAll = decSvc.getAll();
					request.setAttribute("list", listAll);
					request.setAttribute("modalStatus", 5);
			// forward
					RequestDispatcher successView = request.getRequestDispatcher("/back-end/declaration/declarationMainPage.jsp");
					successView.forward(request, response);
					return;
				}
				
			//Insert
				InputStream in = picPart.getInputStream();
				byte[] pic = new byte[in.available()];
				pic = in.readAllBytes();
				
				int id = decSvc.addDeclaration(title, content, pic).getDeclarationID();
				decVO.setDeclarationID(id);
				insertErrorMsgs.clear();
				listAll = decSvc.getAll();
				request.setAttribute("list", listAll);
				request.setAttribute("modalStatus", 5);
			// forward
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/declaration/declarationMainPage.jsp"); // 成功轉交 listOneEmp.jsp
				successView.forward(request, response);
				return;
		}
		
		if("update".equals(action)) {
			Map<String,String> updateErrorMsgs = new LinkedHashMap<String, String>();
			request.setAttribute("updateErrorMsgs", updateErrorMsgs);
			Integer id = Integer.valueOf(request.getParameter("decID"));
			String title = request.getParameter("decTitle");
			String content = request.getParameter("decContent");
			Part picPart = request.getPart("decPic");
			
			DeclarationVO decVO;
			//錯誤判斷
			if (title == null || (title.trim()).length() == 0) {
				updateErrorMsgs.put("title","請輸入公告標題");
			}
			if (content == null || (content.trim()).length() == 0) {
				updateErrorMsgs.put("content","請輸入公告內容");
			}

			if(!updateErrorMsgs.isEmpty()) {
				decVO = new DeclarationVO();
				decVO.setDeclarationID(id);
				decVO.setTitle(title);
				decVO.setContent(content);
				
				updateErrorMsgs.clear();
				listAll = decSvc.getAll();
				request.setAttribute("list", listAll);
				request.setAttribute("decVO", decVO);
				request.setAttribute("modalStatus", 3);
				RequestDispatcher failureView = 
						request.getRequestDispatcher("/back-end/declaration/declarationMainPage.jsp");
				failureView.forward(request, response);
				return;
			}

			decVO = new DeclarationVO();
			decVO.setDeclarationID(id);
			decVO.setTitle(title);
			decVO.setContent(content);
			
			// 流程判斷 (1.沒上傳圖片 修改 2. 有上傳圖片 修改)
			if(picPart == null || picPart.getSize() ==0) {
				decSvc = new DeclarationService();
				decVO.setPic(decSvc.getPic(id));
				decSvc.updateDeclaration(decVO);
				
				updateErrorMsgs.clear();
				listAll = decSvc.getAll();
				request.setAttribute("list", listAll);
				request.setAttribute("decID", id);
				request.setAttribute("modalStatus", 6);
				RequestDispatcher successView = request.getRequestDispatcher("/back-end/declaration/declarationMainPage.jsp");
				successView.forward(request, response);
				return;
			}
			
			InputStream in = picPart.getInputStream();
			byte[] pic = new byte[in.available()];
			pic = in.readAllBytes();
			decVO.setPic(pic);			
			decSvc = new DeclarationService();
			decSvc.updateDeclaration(decVO);
			updateErrorMsgs.clear();
			listAll = decSvc.getAll();
			request.setAttribute("list", listAll);
			request.setAttribute("decID", id);
			request.setAttribute("modalStatus", 6);
			RequestDispatcher successView = request.getRequestDispatcher("/back-end/declaration/declarationMainPage.jsp");
			successView.forward(request, response);
			return;
		} 

		
	
		if ("showOne".equals(action)) { // 來自select_page.jsp的請求
		
			Integer id = Integer.valueOf(request.getParameter("decID"));				
			// 查詢 by ID
			DeclarationVO decVO = decSvc.getOneDeclaration(id);
		
			request.setAttribute("decVO", decVO);
			String url =  "/back-end/declaration/declarationInfo.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
			return;
		}
		
		if("delete".equals(action)) {
			Map<String,String> deleteErrorMsgs = new LinkedHashMap<String,String>();
			Integer id = Integer.valueOf(request.getParameter("decID").trim());
			try {
				decSvc.deleteStatus(id);
			}catch(Exception e) {
				deleteErrorMsgs.put("deleteError", "刪除失敗");
			}
			listAll = decSvc.getAll();
			request.setAttribute("list", listAll);
			request.setAttribute("deleteInfo", id);
			request.setAttribute("modalStatus", 2);
			String url = "/back-end/declaration/declarationMainPage.jsp";
			RequestDispatcher successView = request.getRequestDispatcher(url);
			successView.forward(request, response);
		}
	}

}
