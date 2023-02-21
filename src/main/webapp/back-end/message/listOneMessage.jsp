<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actMessage.model.*"%>

<%
ActMessageVO actMessageVO = (ActMessageVO) request.getAttribute("actMessageVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<html>
<head>
<meta charset="BIG5">
<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<title>訊息資料</title>
<style>
  table#table-1 {
	background-color: #00FFFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	width: 1270px;
	background-color: #CDCDCD;
	margin-top: 5px;
	margin-bottom: 5px;
	hight: 250px;
  }
  table, th, td {
    border: 1px solid ;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>
</head>
<body bgcolor='BFCCDC'>

<table id="#table-1">
	<tr><td>
		 <h3>訊息資料</h3>
		 <h4><a href="select_messageHome.jsp"><img src="images/logo.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table id="myTable" class="display">
 <thead bgcolor="#DCDCDC">
	<tr>
		<th>留言編號</th>
		<th>揪團留言編號</th>
		<th>會員編號</th>
		<th>留言內容</th>
		<th>留言時間</th>
	</tr>
 </thead>
	
 <tbody>
	<tr>
		<td><%=actMessageVO.getMessageId()%></td>
		<td><%=actMessageVO.getActivityId()%></td>
		<td><%=actMessageVO.getUserId()%></td>
		<td><%=actMessageVO.getMessageContent()%></td>
		<td><%=actMessageVO.getMessageContentTimeStr()%></td>
	</tr>
  </tbody>
		<script >
			$(document).ready( function () {
   			 $('#myTable').DataTable();
			} );
		</script>
</table>
</body>
</html>