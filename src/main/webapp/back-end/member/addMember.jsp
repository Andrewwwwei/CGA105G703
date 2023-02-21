<%@page import="com.actMember.model.ActMemberVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actMember.model.*"%>

<%
ActMemberVO actMemberVO = (ActMemberVO) request.getAttribute("actMemberVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<!-- jquery -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

<title>揪團成員 </title>

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

	<table >		
		<tr><td><h3>揪團成員新增</h3>
	</table>

	<h3>新增留言</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form METHOD="post" ACTION="actMember.do" >

		<table>
			<tr>
				<td>會員編號:<font color=red><b>*</b></font></td>
					<td><input type="text" name="tripId" size="45"
							value="<%=(actMemberVO == null) ? "1" : actMemberVO.getUserId()%>"></td>
			</tr>
			
			<tr>
				<td>揪團活動編號:<font color=red><b>*</b></font></td>
					<td><input type="text" name="tripId" size="45"
							value="<%=(actMemberVO == null) ? "1" : actMemberVO.getActivityId()%>"></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>揪團通知</td> -->
<!-- 				<td> -->
<!-- 					<input type="text" name="activityNotice" size="45" -->
<%-- 							value="<%=(actMemberVO == null) ? "通知已取消" : actMemberVO.getActivityNotice()%>"> --%>
<!-- 				</td> -->
<!-- 			</tr> -->
			<tr>
				<td>評價內容</td>
				<td>
					<input type="text" name="evaluationContent" size="45"
							value="<%=(actMemberVO == null) ? "此揪團非常好" : actMemberVO.getEvaluationContent()%>">
				</td>
			</tr>
			<tr>
				<td>評價分數</td>
				<td>
					<select size="1" name="evaluationScoreNember">	
						<option value= "0">1</option>
						<option value= "1">1</option>
						<option value= "2">2</option>
						<option value= "3">3</option>
						<option value= "4">4</option>
						<option value= "5">5</option>
					</select>
	
				</td>
			</tr>

			<tr>
				<td>送出:</td>
				<td>
					<input type="hidden" name="action" value="actMemberInsert" />														
					<input type="submit" value="送出" />
				</td>
			</tr>
		
			<tr>
				<td></td>
					<td>
						<a href="<%=request.getContextPath()%>/back-end/member/select_memberHome.jsp">回首頁</a>
					</td>
			</tr>
		</table>
	</form>
	
</body>
</html>