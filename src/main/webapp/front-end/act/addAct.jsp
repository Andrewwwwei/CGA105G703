<%@page import="com.act.model.ActVO"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.act.model.*"%>

<%
if(request.getSession().getAttribute("usersVO") == null){
	response.sendRedirect(request.getContextPath() + "/front-end/member/login.jsp");
}
ActVO actVO = (ActVO) request.getAttribute("actVO");
%>


                     <!-- 新增揪團 -->

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
  	<link rel="stylesheet" href="/resources/demos/style.css">
 	<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
 	<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
 	<title>7 Tour | 旅遊規劃</title>
	<style>
		.container{
			/*整個頁面*/
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
  			/*文字水平置中*/
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


<!-- 	<table >		 -->
<!-- 		<tr><td><h3>行程編號新增</h3> -->
<!-- 	</table> -->


<div>
<br>
	<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/act/act.do" enctype="multipart/form-data">
		<table align="center" style=" height: 800px; width: 800px">
					
<%-- 		<input type="hidden" name="tripId" size="45" value="<%=(actVO == null) ? "1" : actVO.getTripId()%>"/> --%>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
		
			<tr><td style=" width:150px; height:100px;"><font size="6" >新增揪團</font></td></tr>
			
			<tr><td><font face="DFKai-sb">揪團標題:</font></td>
				<td>
					<input type="text" name="activityTitle" size="45" 
					value="<%=(actVO == null) ? "" : actVO.getActivityTitle()%>" />
				</td>
			</tr>
				<tr>
		
			<tr><td><font face="DFKai-sb">揪團活動內容:</font></td>
				<td><input type="text" name="activityContent" size="45"
						value="<%=(actVO == null) ? "" : actVO.getActivityContent()%>" />
				</td>
			</tr>

			<tr><td><font face="DFKai-sb">當前參與人數:</font></td>
				<td>
					<input type="number" oninput="if(value<1)value=1" name="currentNumber" size="45"
						value="<%=(actVO == null) ? "1" : actVO.getCurrentNumber()%>" />
				</td>
			</tr>

			<tr><td><font face="DFKai-sb">揪團人數上限:</font></td>
				<td>
					<input type="number" oninput="if(value<1)value=1"  name="maxPeople" size="45"
						value="<%=(actVO == null) ? "1" : actVO.getMaxPeople()%>" />
				</td>
			</tr>

			<tr><td><font face="DFKai-sb">報名開始時間:</font></td>
				<td>
					<input name="registrationTime"   type="date" />
				</td>
			</tr>
			<tr><td><font face="DFKai-sb">報名結束時間:</font></td>
					<td>
						<input name="registrationEnd"  type="date"/>
				</td>
			</tr>

			<tr><td><font face="DFKai-sb">行程開始時間:</font></td>
				<td>
					<input name="tripStart"  type="date" />
				</td>
			</tr>

			<tr><td><font face="DFKai-sb">行程結束時間:</font></td>
				<td>
					<input name="tripEnd"  type="date"/>
				</td>
			</tr>

			<tr><td><font face="DFKai-sb">揪團封面:</font></td>
				<td>
					<input  name="activityTheCover" type="file" accept="image/*"
						value="<%=(actVO == null) ? "" : actVO.getActivityTheCover()%>" />
				</td>
			</tr>
			
			<tr><td><font face="DFKai-sb">揪團當前狀態:</font></td>
				<td>
						<input type="text" name="activityState" size="5"
						value="<%=(actVO == null) ? "揪團中" : actVO.getActivityState()%>" />
				</td> 
			</tr>

			<tr><td><font face="DFKai-sb">送出:</font></td>
						<td>
								<input type="hidden" name="activityId" value="${actVO.activityId}">
								<input type="hidden" name="action" value="activityInsert">
								<input type="submit" value="送出"> 
 	  
						</td>
			</tr>

			
<!-- 			<tr> -->
<!-- 				<td></td> -->
<!-- 					<td> -->
<%-- 						<a href="<%=request.getContextPath()%>/front-end/act/actHomePage.jsp">回首頁</a> --%>
<!-- 					</td> -->
<!-- 			</tr> -->
		</table>
	</form>
</div>	
	<!-- include footer -->
</body>
			<c:import url="/front-end/footer.jsp" ></c:import>
<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
  <script>
  $( function() {
      $("#txt_DATE").datepicker({ dateFormat: 'yy-mm-dd' }).val();
      $("#txt_DATE2").datepicker({ dateFormat: 'yy-mm-dd' }).val();
      $("#txt_DATE3").datepicker({ dateFormat: 'yy-mm-dd' }).val();
      $("#txt_DATE4").datepicker({ dateFormat: 'yy-mm-dd' }).val();
  } );
  </script>
</html>