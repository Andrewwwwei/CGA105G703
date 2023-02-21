<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.actMessage.model.*"%>

<%
    ActMessageService actSvc = new ActMessageService();
    List<ActMessageVO> list = actSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>7 Tour | �ȹC�W��</title>
  <link rel="stylesheet" href="css/back.css">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <style type="text/css">
  </style>
  <!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
</head>
<body bgcolor='white'>
<!-- ---------------------aside body�� --------------------->
   <c:import url="/back-end/sidebar.jsp"></c:import>

    <!-- ---------------------function body�� --------------------->
    <div class="function" >

     <div class="box">
    	<h6 align="left"><a href='addMessage.jsp'>�s�W���ίd��</a></h6>
    </div>
 
 	<FORM METHOD="post" ACTION="actMessage.do" >
        <b>�T���s���d��:</b>
        <input type="text" name="messageId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
      
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red"></font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

  <jsp:useBean id="actMessageSvc" scope="page" class="com.actMessage.model.ActMessageService" />
    </div>
    <!-- ---------------------listAllMessage body�� --------------------->
    <div class="workplace">
        <b>�Ҧ��T�����</b>
    <table id="myTable" class="display">
  <thead bgcolor="#C0C0C0">
	<tr>
		<th>�d���s��</th>
		<th>���ά��ʽs��</th>
		<th>�|���s��</th>
		<th>�d�����e</th>
		<th>�d���ɶ�</th>
		<th>�ק���</th>
		<th>�R�����</th>
	</tr>
   </thead>
	<%@ include file="page1.file" %> 
   <tbody>
	<c:forEach var="actMessageVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr>
			<td>${actMessageVO.messageId}</td>
			<td>${actMessageVO.activityId}</td>
			<td>${actMessageVO.userId}</td>
			<td>${actMessageVO.messageContent}</td>
			<td>${actMessageVO.messageContentTimeStr}</td>			
			<td>
			
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/message/actMessage.do"  style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="messageId"  value="${actMessageVO.messageId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/message/actMessage.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="messageId"  value="${actMessageVO.messageId}">
			     <input type="hidden" name="action" value="delete">
			   </FORM>
			</td>
		</tr>
	</c:forEach>
   </tbody>	
</table>
<%@ include file="page2.file" %>
<!-- datatable�M�� -->
<script >
$(document).ready( function () {
    $('#myTable').DataTable();
} );
</script>




</body>
</html>