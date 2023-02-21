<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.emp.model.*"%>
<%
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"> -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/back.css">
  <title>7 Tour | 會員功能頁 </title>
  <style>
	 #userMes{
		 position:absolute;
		 width:400px;
		 top :150px;
		 height:400px;
		 left:300px;
		 opacity:0.3;
		 border-radius:20px;
		 font-size:50px;
		 border-color:white;
	 }  
	 #userMes:hover{
		opacity:0.6;
		cursor:pointer;
	 } 
	 #userStatus{
		 position:absolute;
		 width:400px;
		 top :150px;
		 height:400px;
		 left:900px;
		 opacity:0.3;
		 border-radius:20px;
		 font-size:50px;
		 border-color:white;
	 } 
	 #userStatus:hover{
		opacity:0.6;
		cursor:pointer;
	 } 
	 body {
    	height: 100%;
	 }
	 
 </style>
</head>

<body>
 <img src="<%=request.getContextPath()%>/back-end/emp/images/bgIMG.jpg" style="height: 100%; width: 100%;">
<form id="user_Mes" name="user_Mes" method="post" ACTION="<%=request.getContextPath()%>/EmpServlet">
<input type="hidden" name="action" value="userMes">
 <button id="userMes">會員訊息通知</button></form>
<form id="user_Status" name="user_Status" method="post" ACTION="<%=request.getContextPath()%>/EmpServlet">
<input type="hidden" name="action" value="userStatus">
<button id="userStatus">會員停權系統</button></form>
 <c:import url="/back-end/sidebar.jsp"></c:import>

 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
 <srcipt let userMes=document.getElementById("userMes").addEventListener("click", function () {document.user_Mes.submit();});></srcipt>
 <srcipt let userStatus=document.getElementById("userStatus").addEventListener("click", function () {document.user_Status.submit();});></srcipt>
</body>
 
 </html>