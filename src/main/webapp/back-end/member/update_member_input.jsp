<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actMember.model.*"%>

<%
ActMemberVO actMemberVO = (ActMemberVO) request.getAttribute("actMemberVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
  
<title>成員資料修改</title>

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


<table>
	<tr>
		<td>
		 	<h3>成員資料修改</h3>
		</td>
	</tr>
</table>

<h3 align="center">資料修改</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="massage" items="${errorMsgs}">
			<li style="color:red">${massage}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="actMember.do" name="form1">
<table>
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=actMemberVO.getUserId()%></td>
	</tr>
	
	<tr>
		<td>揪團活動編號:<font color=red><b>*</b></font></td>
		<td><%=actMemberVO.getActivityId()%></td>
	</tr>
	
	<tr>
		<td>評價:<font color=red><b>*</b></font></td>
			<td>
					<select size="1" name="evaluationScoreNember">	
						<option value= "0">0</option>
						<option value= "1">1</option>
						<option value= "2">2</option>
						<option value= "3">3</option>
						<option value= "4">4</option>
						<option value= "5">5</option>
					</select>
			</td>
	</tr>
	
	<tr>
		<td>揪團通知:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="activityNotice" size="45" value="<%=actMemberVO.getActivityNotice()%>" /></td>
	</tr>
	
	<tr>
		<td>揪團內容:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="evaluationContent" size="45" value="<%=actMemberVO.getEvaluationContent()%>" /></td>
	</tr>
	<tr>
		<td>成員狀態:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="memberStatus">	
						<option value= "0">申請中</option>
						<option value= "1">確認加入</option>
						<option value= "2">已拒絕</option>
			</select>
		</td>
	</tr>


	<tr>
		<td>修改</td>
			<td>		 
		
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="userId" value="<%=actMemberVO.getUserId()%>">
				<input type="hidden" name="activityId" value="<%=actMemberVO.getActivityId()%>">
				<input type="hidden" name="userId" value="<%=actMemberVO.getUserId()%>">
				<input type="submit" value="送出修改">
			</td>
	</tr>

	<tr>
		<td></td>
				<td>
				<a href="<%=request.getContextPath()%>/back-end/member/select_memberHome.jsp">回首頁</a>
			</td>
	</tr>
</table>


</FORM>
</body >
</html>