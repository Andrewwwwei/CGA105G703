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
  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/back.css">
  <title>7 Tour | 後臺首頁 </title>
</head>

<body>
	<c:import url="/back-end/sidebar.jsp"></c:import>
    <img src="<%=request.getContextPath()%>/back-end/emp/images/bgIMG.jpg" style="height: 100%; width: 100%;">
</body>
 </html>