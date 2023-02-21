<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actReport.model.*"%>

<%
    ActReportService actSvc = new ActReportService();
    List<ActReportVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>



<!DOCTYPE html>
<html lang="en">

<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>7 Tour | 旅遊規劃</title>
  <link rel="stylesheet" href="css/back.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <style type="text/css">
  </style>
<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
</head>
<body>
 <c:import url="/back-end/sidebar.jsp"></c:import>

    <!-- ---------------------function body區 --------------------->
    <div class="function" >

    <FORM METHOD="post" ACTION="actReport.do" >
        <b>輸入檢舉揪團明細編號:</b>
        <input type="text" name="activityReportId"><font color=red>${errorMsgs}</font> 
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  
    <ul>
    <li><a href="<%=request.getContextPath()%>/back-end/act/select_ActHome.jsp">回首頁</a> </li>
    </ul>

  <jsp:useBean id="actReportSvc" scope="page" class="com.actReport.model.ActReportService" />


    </div>

    <!-- ---------------------listReport body區 --------------------->
    <div class="workplace">
        <b>所有檢舉清單</b>
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
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/report/actReport.do"  style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="activityReportId"  value="${actReportVO.activityReportId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			   </FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/report/actReport.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="activityReportId"  value="${actReportVO.activityReportId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
  </tbody>	
</table>
<%@ include file="page2.file" %>
<!-- datatable套版 -->
<script >
$(document).ready( function () {
    $('#myTable').DataTable();
} );
</script>



</body>

</html>