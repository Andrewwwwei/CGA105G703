<%@page import="com.act.model.ActVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>


<%
ActVO actVO = (ActVO) request.getAttribute("actVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<!-- bootstrap -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper.min.js" integrity="sha384-W8fXfP3gkOKtndU4JGtKDvXbO53Wy8SZCQHczT5FMiiqmQfUpWbYdTil/SxwZgAN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js" integrity="sha384-skAcpIdS7UcVUC05LJ9Dxay8AXcDYfBJqt1CJ85S/CFujBsIzCIv+l9liuYLaMQ/" crossorigin="anonymous"></script>
<!-- jquery -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
  
<title>��{�s���s�W </title>

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
	<table >		
		<tr><td><h3>����ID�s���s�W</h3>
	</table>

<div class="container-fluid" style="background-color:#BFCCDC;padding:10px;margin-bottom:5px;" >
	<h3 align="center" >�s�W���</h3>
</div>
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<form METHOD="post" ACTION="act.do" enctype="multipart/form-data">

		<table class="word">
<!-- 			<tr> -->
<!-- 				<td>��{�s��</td> -->
<!-- 				<td> -->
<!-- 					<input type="text" name="tripId" size="45" -->
<%-- 							value="<%=(actVO == null) ? "1" : actVO.getTripId()%>"> --%>
<!-- 				</td> -->
<!-- 			</tr> -->

			<tr>
				<td>�|���s��:</td>
				<td>
					<input type="text" name="userId" size="45"
							value="<%=(actVO == null) ? "2" : actVO.getUserId()%>" />
				</td>
			</tr>

			<tr>
				<td>���μ��D:</td>
				<td >
					<input type="text" name="activityTitle" size="45" 
							value="<%=(actVO == null) ? "�򶩤T��C" : actVO.getActivityTitle()%>" />
				</td>
			</tr>
			
		
			<tr>
				<td>���ά��ʤ��e:</td>
				<td>
					<input type="text" name="activityContent" size="45"
						value="<%=(actVO == null) ? "�򶩼q�f,�����],�K��l�]��" : actVO.getActivityContent()%>" />
				</td>
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
				<td>
					<input name="registrationTime" id="txt_DATE"  type="date"/>
				</td>
			</tr>
			<tr>
				<td>���W�����ɶ�:</td>
					<td>
						<input name="registrationEnd" id="txt_DATE2" type="date"/>
				</td>
			</tr>

			<tr>
				<td>��{�}�l�ɶ�:</td>
				<td>
					<input name="tripStart" id="txt_DATE3" type="date" />
				</td>
			</tr>

			<tr>
				<td>��{�����ɶ�:</td>
				<td>
					<input name="tripEnd" id="txt_DATE4" type="date"/>
				</td>
			</tr>

			<tr>
				<td>���Ϋʭ�:</td>
				<td>
					<input  name="activityTheCover" type="file" accept="image/*"
						value="<%=(actVO == null) ? "" : actVO.getActivityTheCover()%>" />
				</td>
			</tr>
			
			<tr>
				<td>���η�e���A:</td>
				<td>
					<select size="1" name="activityState">
						<option value="0">���Τ�</option>
						<option value="1">�w����</option>
						<option value="2">���|���\</option>
					
					
					</select>
				</td> 
			</tr>

			<tr>
				<td>�e�X:</td>
				<td>
					<input type="hidden" name="action" value="activityInsert" />
					<input type="submit" value="�e�X" />
				</td>
			</tr>
			
			<tr>
				<td></td>
					<td>
						<a href="select_ActHome.jsp">�^����</a>
					</td>
			</tr>
		</table>
	</form>
</div>	
</body>


<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
  <script>
//   $( function() {
//       $("#txt_DATE").datepicker({ dateFormat: 'yy-mm-dd' }).val();
//       $("#txt_DATE2").datepicker({ dateFormat: 'yy-mm-dd' }).val();
//       $("#txt_DATE3").datepicker({ dateFormat: 'yy-mm-dd' }).val();
//       $("#txt_DATE4").datepicker({ dateFormat: 'yy-mm-dd' }).val();
//   } );
  </script>
</html>