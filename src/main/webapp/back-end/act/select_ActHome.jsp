<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>

<%
    ActService actSvc = new ActService();
    List<ActVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html lang="en">

<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>7 Tour | 旅遊規劃</title>
  <link rel="stylesheet" href="css/back.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <script src="https://cdn.datatables.net/1.13.2/css/jquery.dataTables.min.css"></script>	
  <script src="https://cdn.datatables.net/1.13.2/js/jquery.dataTables.min.js"></script>	
  
  <style type="text/css">
  </style>
<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
</head>


<body>

  <c:import url="/back-end/sidebar.jsp"></c:import>

  <div class="right">

    <!-- ---------------------function body區 --------------------->
    <div class="function" >

	  <FORM METHOD="post" ACTION="act.do" >
        <b>揪團活動編號查詢:</b>
        <input type="text" name="activityId"  value="${activityId}" style="width:130px;"><font color=red>${errorMsgs}</font> 
		<input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
      </FORM>

   <ul>
   <li><a href="<%=request.getContextPath()%>/back-end/report/select_actReportHome.jsp">所有檢舉清單</a></li>
    </ul>
  			


	

    </div>

    <!-- ---------------------listAllAct body區 --------------------->
  <div class="workplace">
    <b>所有揪團資料</b>
   				 <!--   dataTables 套入-->
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
		<th>刪除資料</th>
	</tr>
  </thead>
	<%@ include file="page1.file" %> 
	<tbody>
	<c:forEach var="actVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${actVO.activityId}</td>
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
						<!-- 揪團狀態 -->
				<c:if test="${actVO.activityState == 0}">
					<td>揪團中</td>
				</c:if>
				<c:if test="${actVO.activityState == 1}">
					<td>已取消</td>
				</c:if>
				<c:if test="${actVO.activityState == 2}">
					<td>檢舉成功</td>
				</c:if>

			
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
			<!-- datatable 語法 -->
		<script >
			$(document).ready( function () {
   			 $('#myTable').DataTable();
			} );
		</script>
  
  
  
  </div>
</body>
</html>