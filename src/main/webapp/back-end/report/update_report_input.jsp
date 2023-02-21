<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actReport.model.*"%>

<%
ActReportVO actReportVO = (ActReportVO) request.getAttribute("actReportVO"); //EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
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

  <title>揪團檢舉資料修改 - update_report_input.jsp</title>

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
	<tr><td>
		 <h3 align="conter">揪團檢舉資料審核</h3>
		 <h4><a href="select_actReportHome.jsp">回首頁</a></h4>
	</td></tr>
</table>

<h3 align="center">資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="actReport.do">
<table>
	<tr>
		<td>檢舉揪團明細編號:<font color=red><b>*</b></font></td>
		<td><%=actReportVO.getActivityReportId()%></td>
	</tr>
	
	<tr>
		<td>揪團活動編號:<font color=red><b>*</b></font></td>
		<td><%=actReportVO.getActivityId()%></td>
	</tr>
	
	<tr>
		<td>檢舉人會員編號:<font color=red><b>*</b></font></td>
		<td><%=actReportVO.getReportUserId()%></td>
	</tr>
	
	<tr>
		<td>負責員工編號:<font color=red><b>*</b></font></td>
		<td><%=actReportVO.getEmpNo()%></td>
	</tr>
	
	<tr>
		<td>檢舉內容:<font color=red><b>*</b></font></td>
		<td><%=actReportVO.getReportContent()%></td>
	</tr>
			
	<tr>
		<td>檢舉審核:</td>
			<td>
				<select size="1" name="reportStatus">	
					<option value= "0">未處理</option>
					<option value= "1">通過</option>
					<option value= "2">不通過</option>
				</select>
			</td>
	</tr>
	
	<tr>
		<td>檢舉事項:</td>
			<td>
				<select size="1" name="reportMatter">	
						<option value= "0">騷擾行為</option>
						<option value= "1">疑似詐騙內容</option>
						<option value= "2">標題與活動內容不符</option>
						<option value= "3">其他</option>
				</select>
			</td>
	</tr>
		
			<tr>
		<td>修改</td>
			<td>		 
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="activityReportId" value="<%=actReportVO.getActivityReportId()%>">
				<input type="hidden" name="activityId" value="<%=actReportVO.getActivityId()%>">
				<input type="hidden" name="reportUserId" value="<%=actReportVO.getReportUserId()%>">
				<input type="hidden" name="empNo" value="<%=actReportVO.getEmpNo()%>">
				<input type="hidden" name="reportContent" value="<%=actReportVO.getReportContent()%>">
				<input type="hidden" name="reportStatus" value="<%=actReportVO.getReportStatus()%>">
				<input type="hidden" name="reportMatter" value="<%=actReportVO.getReportMatter()%>">

				<input type="submit" value="送出">
			</td>
	</tr>

	<tr>
		<td></td>
				<td>
				<a href="select_actReportHome.jsp">回首頁</a>
			</td>
	</tr>

</table>


</FORM>
</body>

</html>