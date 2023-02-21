<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actMessage.model.*"%>

<%
ActMessageVO actMessageVO = (ActMessageVO) request.getAttribute("actMessageVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>


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
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>


<title>訊息資料修改 - update_message_input.jsp</title>
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

<table id="table-1">
	<tr><td>
		 <h3>訊息資料修改 - update_message_input.jsp</h3>
		 <h4><a href="select_messageHome.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>訊息修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="actMessage.do" name="form1">
 <table>
	<tr>
		<td>留言編號:<font color=red><b>*</b></font></td>
		<td><%=actMessageVO.getMessageId()%></td>
	</tr>
	
	<tr>
		<td>揪團活動編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="activityId" size="45" value="<%=actMessageVO.getActivityId()%>" /></td>
	</tr>
	
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="userId" size="45" value="<%=actMessageVO.getUserId()%>" /></td>
	</tr>
	
	<tr>
		<td>留言內容:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="messageContent" size="45" value="<%=actMessageVO.getMessageContent()%>" /></td>
	</tr>

   <tr>
		<td>修改</td>
			<td>		 
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="messageId" value="<%=actMessageVO.getMessageId()%>">
				<input type="submit" value="送出修改">
			</td>
	</tr>

	<tr>
		<td></td>
				<td>
				<a href="select_ActHome.jsp">回首頁</a>
			</td>
	</tr>
	
 </table>
</FORM>

</body >
</html>