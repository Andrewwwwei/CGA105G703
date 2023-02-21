<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actMember.model.*"%>
<%@ page import="java.time.*"%>
<%@ page import="java.time.format.*"%>
<%
ActMemberVO actMemberVO = (ActMemberVO) request.getAttribute("actMemberVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
pageContext.setAttribute("time", actMemberVO.getEvaluationTime().format(format));
 
%>
<html>
<head>
<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<title>���Φ��� - listOneMember.jsp</title>

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
	<tr><td>
		 <h3>���Φ���</h3>
		 <h4><a href="<%=request.getContextPath()%>/back-end/member/select_memberHome.jsp">�^����</a></h4>
	</td></tr>
</table>

<table id="myTable" class="display">
 <thead bgcolor="#DCDCDC">
	<tr>
		<th>�|���s��</th>
		<th>���ά��ʽs��</th>
		<th>���γq��</th>
		<th>�������e</th>
		<th>��������</th>
		<th>�̫�ק�ɶ�</th>
	<tr>
 </thead>
   <tbody>
	   <tr>
	   
			<td>${actMemberVO.userId}</td>
			<td>${actMemberVO.activityId}</td>
			<td>${actMemberVO.activityNotice}</td>
			<td>${actMemberVO.evaluationContent}</td>
			<td>${actMemberVO.evaluationScore}</td>			
			<td>${time}<td>				
			<input type="hidden" name="evaluationTime" value="<%=actMemberVO.getEvaluationTime()%>">
			</td>
			
						
	   </tr>
	</tbody>
</table>
	<!--   dataTables -->
	<script >
			$(document).ready( function () {
   			 $('#myTable').DataTable();
			} );
		</script>
</body>
</html>