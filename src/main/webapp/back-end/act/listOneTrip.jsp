<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>
<%@ page import="java.util.List"%>


<%

//   List<ActVO> actVOList = (List)request.getAttribute("actVOList"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>
<!-- 		暫時不與行程串接 -->
<html>
<head>
<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<title>揪團活動資料 - listOneTrip.jsp</title>

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

<table id="#table-1" >
	<tr><td>
		 <h3>揪團行程資料 - listOneTrip.jsp</h3>
		 <h4><a href="select_ActHome.jsp"><img src="images/Logo.png" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table id="myTable" class="display">
  <thead bgcolor="#DCDCDC">
	<tr>
		<th>揪團活動編號</th>
		<th>行程編號</th>
		<th>會員編號</th>
		<th>揪團標題</th>
		<th>揪團活動內容</th>
		<th>當前參與人數</th>
		<th>揪團人數上限</th>
		<th>報名開始時間</th>
		<th>報名結束時間</th>
		<th>行程開始時間</th>
		<th>行程結束時間</th>
		<th>揪團封面</th>
		<th>揪團當前狀態</th>
	</tr>
   </thead>
   
   	<tbody>
		<c:forEach var="actVO" items="${actVOList}">
	<tr>
		<td>${actVO.getActivityId()}</td>
		<td>${actVO.getTripId()}</td>
		<td>${actVO.getUserId()}</td>
		<td>${actVO.getActivityTitle()}</td>
		<td>${actVO.getActivityContent()}</td>
		<td>${actVO.getCurrentNumber()}</td>
		<td>${actVO.getMaxPeople()}</td>
		<td>${actVO.getRegistrationTime()}</td>
		<td>${actVO.getRegistrationEnd()}</td>
		<td>${actVO.getTripStart()}</td>
		<td>${actVO.getTripEnd()}</td>
		
		<td>
			<c:if test="${not empty actVO.activityTheCover}">
			<img src="<%=request.getContextPath()%>/back-end/act/act.do?activityId=${actVO.activityId}&action=showFirstImages" style="width: 100%;">
			</c:if>
		</td>
		
		<td>${actVO.activityState}</td>
		
	</tr>
		</c:forEach>
	</tbody>	
</table>
	<script >
			$(document).ready( function () {
   			 $('#myTable').DataTable();
			} );
	</script>
</body>
</html>