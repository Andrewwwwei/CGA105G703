<%@page import="com.act.model.ActVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
ActVO actVO = (ActVO) request.getAttribute("actVO");
%>


                     <!-- �s�W���� -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
	<!--     bootstrap -->
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  	<link rel="stylesheet" href="./css/header.css">
	<link rel="website icon" href="./images/bgremove_Logo.jpg">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
    integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
	<!-- jquery -->
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
 	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
 	<title>7 Tour | �ȹC�W��</title>
	<style>
		.container{
			/*��ӭ���*/
			display : flex;
			flex-direction : column;
			justify-content: space-between; 
			flex-wrap: wrap;
		}
		.location{
			margin-top:50px;
			width: 900px;
			display: table-cell;
  			vertical-align: middle;
  			/*��r�����m��*/
			text-align: center;
		}
		.clearfix:after {
        	content:"";
        	display:table;
        	clear:both;
    	}
    	a {
 			 color: black;
 			 text-decoration: none;
		}
		#actContent{
			display: flex;
			justify-content:space-around;
			flex-wrap : wrap;
			
		}
		.card{
			margin-bottom: 50px;
		}
		.Bottom distance::after{
			width: 100%;
			height: 50px;
		 	content:"";
  			display: block;
  			clear:both;
		}
		.row{
			text-align: center;
		
		}
	</style>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
  <!-- 	�e�����|�K��� -->
<div class="container-fluid mt-5">
<!-- 	<table >		 -->
<!-- 		<tr><td><h3>��{�s���s�W</h3> -->
<!-- 	</table> -->

<div class="container-fluid" style="background-color:#BFCCDC;padding:10px;margin-bottom:5px;" >
	<h3 align="center" >�קﴪ��</h3>
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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/act/act.do" enctype="multipart/form-data" name="form1">
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
					value="<%=(actVO == null) ? "1" : actVO.getMaxPeople()%>" />
			</td>
	</tr>
	
	<tr>
		<td>���W�}�l�ɶ�:</td>
			<td><input name="registrationTime"  type="date" /></td>
	</tr>

	<tr>
		<td>���W�����ɶ�:</td>
			<td><input name="registrationEnd"  type="date" /></td>
	</tr>
	
	<tr>
		<td>��{�}�l�ɶ�:</td>
			<td><input name="tripStart"  type="date" /></td>
	</tr>
	
	<tr>
		<td>��{�����ɶ�:</td>
			<td><input name="tripEnd"  type="date" /></td>
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
<!-- 						<option value= "1">�w����</option> -->
<!-- 						<option value= "2">���|���\</option> -->
				</select> 
		 	</td>	
	</tr>
	
	<tr>
		<td>�ק�</td>
			<td>		 
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="activityId" value="<%=actVO.getActivityId()%>">
				<input type="hidden" name="userId" value="<%=actVO.getUserId()%>">
				<input type="submit" value="�e�X">
			</td>
	</tr>


  </table>
 </FORM>
</div>	


	<!-- include footer -->
			<c:import url="/front-end/footer.jsp" ></c:import>
</body>
<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
  <script>
  $( function() {
      $("#txt_DATE").datepicker({ dateFormat: 'yy-mm-dd' }).val();
      $("#txt_DATE2").datepicker({ dateFormat: 'yy-mm-dd' }).val();
      $("#txt_DATE3").datepicker({ dateFormat: 'yy-mm-dd' }).val();
      $("#txt_DATE4").datepicker({ dateFormat: 'yy-mm-dd' }).val();
  } );
  </script>
</html>