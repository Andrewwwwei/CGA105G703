<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>


<%
    ActService actSvc = new ActService();
    List<ActVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
 	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>

	<title>所有揪團資料 </title>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr>
		<td>
		 	<h3>所有揪團資料 </h3>
		 	<h4><a href="select_ActHome.jsp"><img src="images/Logo.png" width="100" height="32" border="0">回首頁</a></h4>
		</td>
	</tr>
</table>

<table id="myTable" class="display">
  <thead bgcolor="#C0C0C0">
	<tr>
		<th>揪團活動編號</th>
<!-- 		<th>行程編號</th> -->
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
		<th>修改資料</th>
		<th>刪除資料</th>
	</tr>
  </thead>
	<%@ include file="page1.file" %> 
  <tbody>
	<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${actVO.activityId}</td>
<%-- 			<td>${actVO.tripId}</td> --%>
			<td>${actVO.userId}</td>
			<td>${actVO.activityTitle}</td>
			<td>${actVO.activityContent}</td>
			<td>${actVO.currentNumber}</td> 
			<td>${actVO.maxPeople}</td>
			<td>${actVO.registrationTime}</td>
			<td>${actVO.registrationEnd}</td>
			<td>${actVO.tripStart}</td>
			<td>${actVO.tripEnd}</td>
			
			<td>
				<c:if test="${not empty actVO.activityTheCover}">
				<img src="<%=request.getContextPath()%>/back-end/act/act.do?activityId=${actVO.activityId}&action=showFirstImages" style="width: 100%;">
				</c:if>
				
				<c:if 
				test="${empty actVO.activityTheCover}">無圖片
				</c:if>
			</td>
			
			<td>${actVO.activityState}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/act/act.do"  style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="activityId"  value="${actVO.activityId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
			    </FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/act/act.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="activityId"  value="${actVO.activityId}">
			     <input type="hidden" name="action" value="delete">
			   </FORM>
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