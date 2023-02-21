<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actReport.model.*"%>


<%
    ActReportService actReportSvc = new ActReportService();
    List<ActReportVO> list = actReportSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
	<link rel="stylesheet" href="css/back.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>

	<title>所有檢舉揪團資料 - listAllReport.jsp</title>

</head>
<body bgcolor='white'>
<c:import url="/back-end/sidebar.jsp"></c:import>
 <div class="right" style="position: absolute; left: 230px; top:50px;">
	<table id="table-1">
	<tr>
			<td>
			 	<h3>所有檢舉揪團資料 </h3>
			 	<h4><a href="select_actReportHome.jsp"><img src="images/logo.jpg" width="100" height="32" border="0">回首頁</a></h4>
			</td>
		</tr>
	</table>
	
	<table id="myTable" class="display">
	  <thead bgcolor="#C0C0C0">
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
			<th>修改資料</th>
			<th>刪除資料</th>
		</tr>
	   </thead>
		<%@ include file="page1.file" %> 	  
	  <tbody>
		<c:forEach var="actReportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr>
				<td>${actReportVO.activityReportId}</td>
				<td>${actReportVO.activityId}</td>
				<td>${actReportVO.reportUserId}</td>
				<td>${actReportVO.empNo}</td>
				<td>${actReportVO.reportContent}</td>
				<td>${actReportVO.reportStatus}</td> 
				<td>${actReportVO.reportTimeStr}</td>
				<td>${actReportVO.reportMatter}</td>
				<td>${actReportVO.reportFinishTimeStr}</td>
				
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/report/actReport.do"  style="margin-bottom: 0px;">
				     <input type="submit" value="修改">
				     <input type="hidden" name="activityReportId"  value="${actReportVO.activityReportId}">
				     <input type="hidden" name="action"	value="getOne_For_Update">
				   </FORM>
				</td>
				
				<td>
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/report/actReport.do"  style="margin-bottom: 0px;">
				     <input type="submit" value="刪除">
				     <input type="hidden" name="activityReportId"  value="${actReportVO.activityReportId}">
				     <input type="hidden" name="action" value="delete"></FORM>
				</td>
			</tr>
		</c:forEach>
	  </tbody>	
	</table>
<%@ include file="page2.file" %>
</div>
<!-- datatable套版 -->
<script >
$(document).ready( function () {
    $('#myTable').DataTable();
} );
</script>
</body>
</html>