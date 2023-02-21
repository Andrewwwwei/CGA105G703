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
  <title>7 Tour | �ȹC�W��</title>
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

    <!-- ---------------------function body�� --------------------->
    <div class="function" >

	  <FORM METHOD="post" ACTION="act.do" >
        <b>���ά��ʽs���d��:</b>
        <input type="text" name="activityId"  value="${activityId}" style="width:130px;"><font color=red>${errorMsgs}</font> 
		<input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
      </FORM>

   <ul>
   <li><a href="<%=request.getContextPath()%>/back-end/report/select_actReportHome.jsp">�Ҧ����|�M��</a></li>
    </ul>
  			


	

    </div>

    <!-- ---------------------listAllAct body�� --------------------->
  <div class="workplace">
    <b>�Ҧ����θ��</b>
   				 <!--   dataTables �M�J-->
 <table id="myTable" class="display">
  <thead bgcolor="#C0C0C0">
	<tr>
		<th>���ά��ʽs��</th>
<!-- 		<th>��{�s��</th> -->
		<th>�|���s��</th>
		<th>���μ��D</th>
		<th>���ά��ʤ��e</th>
		<th>��e�ѻP�H��</th>
		<th>���ΤH�ƤW��</th>
		<th>���W�}�l�ɶ�</th>
		<th>���W�����ɶ�</th>
		<th>��{�}�l�ɶ�</th>
		<th>��{�����ɶ�</th>
		<th>���Ϋʭ�</th>
		<th>���η�e���A</th>
		<th>�R�����</th>
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
				test="${empty actVO.activityTheCover}">�L�Ϥ�
				</c:if>
			</td>
						<!-- ���Ϊ��A -->
				<c:if test="${actVO.activityState == 0}">
					<td>���Τ�</td>
				</c:if>
				<c:if test="${actVO.activityState == 1}">
					<td>�w����</td>
				</c:if>
				<c:if test="${actVO.activityState == 2}">
					<td>���|���\</td>
				</c:if>

			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/act/act.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="activityId"  value="${actVO.activityId}">
			     <input type="hidden" name="action" value="delete">
			   </FORM>
			</td>
		</tr>
	</c:forEach>
	
	</tbody>	
</table>
		<%@ include file="page2.file" %>
			<!-- datatable �y�k -->
		<script >
			$(document).ready( function () {
   			 $('#myTable').DataTable();
			} );
		</script>
  
  
  
  </div>
</body>
</html>