<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.Users.model.*"%>
<%
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/back.css">
  <title>7 Tour | 會員停權系統 </title>
  <style>
	  #MesBorder{
	 border:1px solid white;
	 width:500px;
	 font-family:"微軟正黑體";
	 position:absolute;
	 font-size:20px;
	 font-weight:bold;
	top:100px;
	left:600px;
	border-radius:20px;
	
	  }
	#MesBorder #info{
	margin:30px 0px 0 50px;
	color:lightgray;
	}
	#MesBorder #sercher{
	height:50px;
	color:lightgray;
	margin:30px 0px 20px 50px;
	}
 </style>
</head>

<body>
<div id="Mes" style="background-color:black;width:1489px;height:753px">
	<div id="MesBorder">
		<h2 id="info">會員停權系統</h2>
		<h3 id="info">資料查詢:</h3>
		<%-- 錯誤表列 --%>
		<c:if test="${not empty errorMsgs}">
			<font style="color:red; font-size:20px;display:inline-block;margin:0 0 0px 45px;"></font>
			<ul style="display:inline-block">
			    <c:forEach var="message" items="${errorMsgs}">
					<li style="color:red;font-size:20px;list-style-type: none;margin:0 0 -20px -40px;">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
		<div id="sercher">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" >
		        <b id="b" >列出所有檢舉次數會員:</b>
		        <input id="userStatusButton" type="text" name="userStatus">
		        <input type="hidden" name="action" value="getAll_Total_Count_userstatus">
		        <input  type="submit" value="送出">
		    </FORM>
		</div>
	  	<div id="sercher">
	  		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" >
		        <b id="b" >輸入使用者Email:</b>
		        <input id="userEmailButton" type="text" name="userMail">
		        <input type="hidden" name="action" value="getOne_For_userMail_userstatus">
		        <input  type="submit" value="送出">
		    </FORM>
	  	</div>
			<jsp:useBean id="userSvc" scope="page" class="com.Users.model.UsersService" />
		<div id="sercher">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" >
		       <b>輸入使用者姓名:</b>
		       <input id="userNameButton" type="text" name="userName">
		       <input type="hidden" name="action" value="getOne_For_userName_userstatus">
		       <input  type="submit" value="送出">
		    </FORM>
	   	</div>
   	</div>
</div>

<c:import url="/back-end/sidebar.jsp"></c:import>
 
 <script>let userEmailButton=document.getElementById("userEmailButton").addEventListener("click", function () {document.getElementById("userNameButton").disabled = true;document.getElementById("userStatusButton").disabled = true;});</script>
 <script>let userNameButton=document.getElementById("userNameButton").addEventListener("click", function () {document.getElementById("userEmailButton").disabled = true;document.getElementById("userStatusButton").disabled = true;});</script>
 <script>let userStatusButton=document.getElementById("userStatusButton").addEventListener("click", function () {document.getElementById("userEmailButton").disabled = true;document.getElementById("userNameButton").disabled = true;});</script>
</body>  
 </html>