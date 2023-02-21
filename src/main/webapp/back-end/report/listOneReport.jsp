<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actReport.model.*"%>


<%
ActReportVO actReportVO = (ActReportVO) request.getAttribute("actReportVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<title>揪團檢舉資料</title>

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
	width: 1180px;
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
  .right{
  	position: absolute; 
  	left: 230px; top:50px;
  }
</style>

</head>
<body bgcolor='BFCCDC'>
<c:import url="/back-end/sidebar.jsp"></c:import>
 <div class="right">
<table id="#table-1">
	<tr><td>
		 <h3>揪團檢舉資料</h3>
		 <h4><a href="select_actReportHome.jsp"><img src="images/logo.jpg" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>


<table id="myTable" class="display">
 <thead bgcolor="#DCDCDC">
	<tr>
		<th>檢舉揪團明細編號</th>
		<th>揪團活動編號</th>
		<th>揪團人員會員編號</th>
		<th>負責員工編號</th>
		<th>檢舉內容</th>
		<th>檢舉審核</th>
		<th>檢舉時間</th>
		<th>檢舉事項</th>
		<th>處理完成時間</th>
	</tr>
  </thead>
  
  <tbody>
		<forEach var="actReportVO" items="${actReportVO}">
	<tr>
		<td>${actReportVO.activityReportId}</td>
		<td>${actReportVO.activityId}</td>
		<td>${actReportVO.reportUserId}</td>
		<td>${actReportVO.empNo}</td>
		<td>${actReportVO.reportContent}</td>
				<!-- 檢舉審核 -->
			<c:if test="${actReportVO.reportStatus == 0}">
				<th>未處理</th>
			</c:if>
			
			<c:if test="${actReportVO.reportStatus == 1}">
				<th>通過</th>	
			</c:if>
			
			<c:if test="${actReportVO.reportStatus == 2}">
				<th>不通過</th>
			</c:if>
			
		<td>${actReportVO.reportTimeStr}</td>
				<!-- 檢舉事項 -->
			<c:if test="${actReportVO.reportMatter == 0}">
				<th>騷擾行為</th>
			</c:if>
			
			<c:if test="${actReportVO.reportMatter == 1}">
				<th>疑似詐騙內容</th>
			</c:if>
				
			<c:if test="${actReportVO.reportMatter == 2}">
				<th>標題與活動內容不符</th>
			</c:if>
			
			<c:if test="${actReportVO.reportMatter == 3}">
				<th>其他</th>
			</c:if>
	
		<td>${actReportVO.reportFinishTimeStr}</td>
		
	</tr>
		</forEach>
	</tbody>
</table>
</div>
		<script >
			$(document).ready( function () {
   			 $('#myTable').DataTable();
			} );
		</script>
</body>
</html>