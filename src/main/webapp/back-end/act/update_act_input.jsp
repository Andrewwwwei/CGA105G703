<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
  ActVO actVO = (ActVO) request.getAttribute("actVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
%>
<html>
<head>
	<!-- jquery -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<title>���θ�ƭק�</title>

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
<!-- 	�e�����|�K��� -->
<div class="container-fluid" >
 <table>
	<tr>
		<td>
		 	<h3 align="center">���ά��ʭק�</h3>
		 	<h3><a href="select_ActHome.jsp">�^����</a></h3>
		</td>
	</tr>
 </table>
 <div class="container-fluid" style="background-color:#BFCCDC;padding:10px;margin-bottom:5px;" >
    <h3 align="center">��ƭק�</h3>
 </div>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

 <FORM METHOD="post" ACTION="act.do" enctype="multipart/form-data" name="form1">
  <table>
	<tr>
		<td>���ά��ʽs��:<font color=red><b>*</b></font></td>
			<td><%=actVO.getActivityId()%></td>
	</tr>
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
			<td><%=actVO.getUserId()%></td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>��{�s��:</td> -->
<%-- 			<td><input type="TEXT" name="tripId" size="45" value="<%=actVO.getTripId()%>" /></td> --%>
<!-- 	</tr> -->
	

	<tr>
		<td>���μ��D:</td>
			<td><input type="TEXT" name="activityTitle" size="45" value="<%=actVO.getActivityTitle()%>" /></td>
	</tr>
	
	<tr>
		<td>���ά��ʤ��e:</td>
			<td><input type="TEXT" name="activityContent" size="45"	value="<%=actVO.getActivityContent()%>" /></td>
	</tr>
	
	<tr>
		<td>��e�ѻP�H��:</td>
			<td>
				<input type="number" oninput="if(value<1)value=1" name="currentNumber" size="45"
						value="<%=(actVO == null) ? "1" : actVO.getCurrentNumber()%>" />
			</td>
	</tr>

	<tr>
		<td>���ΤH�ƤW��:</td>
			<td>
				<input type="number" oninput="if(value<1)value=1"  name="maxPeople" size="45"
					value="<%=(actVO == null) ? "15" : actVO.getMaxPeople()%>" />
			</td>
	</tr>
	
	<tr>
		<td>���W�}�l�ɶ�:</td>
			<td><input name="registrationTime" id="txt_DATE" type="date" /></td>
	</tr>

	<tr>
		<td>���W�����ɶ�:</td>
			<td><input name="registrationEnd" id="txt_DATE2" type="date" /></td>
	</tr>
	
	<tr>
		<td>��{�}�l�ɶ�:</td>
			<td><input name="tripStart" id="txt_DATE3" type="date" /></td>
	</tr>
	
	<tr>
		<td>��{�����ɶ�:</td>
			<td><input name="tripEnd" id="txt_DATE4" type="date" /></td>
	</tr>
	
	<tr>
		<td>���Ϋʭ�:</td>
			<td><input type="file" name="activityTheCover" size="45" value="<%=actVO.getActivityTheCover()%>" /></td>
	</tr>
	
	<tr>
		<td>���η�e���A:</td>
		 	<td>
				<select size="1" name="activityState" >	
						<option value= "0">���Τ�</option>
						<option value= "1">�w����</option>
						<option value= "2">���|���\</option>
				
				</select> 
		 	</td>	
	</tr>
	
	<tr>
		<td>�ק�</td>
			<td>		 
		
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="activityId" value="<%=actVO.getActivityId()%>">
				<input type="submit" value="�e�X">
			</td>
	</tr>

	<tr>
		<td></td>
				<td>
				<a href="select_ActHome.jsp">�^����</a>
			</td>
	</tr>

  </table>
 </FORM>
</div>	
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
// $( function() {
//     $("#txt_DATE").datepicker({ dateFormat: 'yy-mm-dd' }).val();
//     $("#txt_DATE2").datepicker({ dateFormat: 'yy-mm-dd' }).val();
//     $("#txt_DATE3").datepicker({ dateFormat: 'yy-mm-dd' }).val();
//     $("#txt_DATE4").datepicker({ dateFormat: 'yy-mm-dd' }).val();
// } );
</script>
</html>