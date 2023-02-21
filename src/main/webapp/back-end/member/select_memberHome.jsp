<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>  
<%@ page import="java.util.*"%>
<%@ page import="com.actMember.model.*"%>
<%@ page import="java.time.*"%>


<%
	ActMemberService actMemberSvc = new ActMemberService();
    List<ActMemberVO> list = actMemberSvc.getAll();
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
  <style type="text/css">
  </style>
  <!--   dataTables -->
  <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
  <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
  <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
</head>

<body>
	
<!-- ---------------------aside body區 --------------------->
 <c:import url="/back-end/sidebar.jsp"></c:import>

    <!-- ---------------------function body區 --------------------->
    <div class="function" >
    	<div class="box">
    		<h6 align="left"><a href='addMember.jsp'>新增評價</a></h6>
    	</div>
    
    	<FORM METHOD="post" ACTION="actMember.do" >
        	<b>會員編號查詢:</b>
        		<input type="text" name="userId"  value="${userId}" style="width:130px;"><font color=red>${errorMsgs}</font> 
        		<input type="hidden" name="action" value="getOne_For_Display">
        		<input type="submit" value="送出">
    	</FORM>
 
<%--        <jsp:useBean id="actMemberSvc" scope="page" class="com.actMember.model.ActMemberService" />    --%>

    </div>

    <!-- ---------------------listAllAct body區 --------------------->
    <div class="workplace">
		<b>所有揪團成員列表</b>
 <table id="myTable" class="display">
  <thead bgcolor="#C0C0C0">
	<tr>
		<th>會員編號</th>
		<th>揪團活動編號</th>
		<th>揪團通知</th>
		<th>評價內容</th>
		<th>評價分數</th>
		<th>評價時間</th>
		<th>成員確認狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
  </thead>
  <tbody>
<%@ include file="page1.file" %>
  	<c:forEach var="actMemberVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${actMemberVO.userId}</td>
			<td>${actMemberVO.activityId}</td>
			<td>${actMemberVO.activityNotice}</td>
			<td>${actMemberVO.evaluationContent}</td>
			<td>${actMemberVO.evaluationScore}</td>			
			<td>
			<fmt:parseDate
              value="${actMemberVO.evaluationTime}"
              pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
              type="both" /> <fmt:formatDate pattern="yyyy-MM-dd HH:mm"
              value="${ parsedDateTime }" />
			</td>			
			<td>${actMemberVO.memberStatus}</td>			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actMember.do"  style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="userId"  value="${actMemberVO.userId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/actMember.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除">
			     <input type="hidden" name="userId"  value="${actMemberVO.userId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>

	</c:forEach>
	</tbody>	
<%@ include file="page2.file" %>
</table>
 </div>
 		<!-- datatable 語法 -->
		<script >
			$(document).ready( function () {
   			 $('#myTable').DataTable();
			} );
		</script>
</body>
</html>