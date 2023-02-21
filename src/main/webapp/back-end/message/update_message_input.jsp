<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.actMessage.model.*"%>

<%
ActMessageVO actMessageVO = (ActMessageVO) request.getAttribute("actMessageVO"); //EmpServlet.java (Concroller) �s�Jreq��empVO���� (�]�A�������X��empVO, �]�]�A��J��ƿ��~�ɪ�empVO����)
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


<title>�T����ƭק� - update_message_input.jsp</title>
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
		 <h3>�T����ƭק� - update_message_input.jsp</h3>
		 <h4><a href="select_messageHome.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>�T���ק�:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="actMessage.do" name="form1">
 <table>
	<tr>
		<td>�d���s��:<font color=red><b>*</b></font></td>
		<td><%=actMessageVO.getMessageId()%></td>
	</tr>
	
	<tr>
		<td>���ά��ʽs��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="activityId" size="45" value="<%=actMessageVO.getActivityId()%>" /></td>
	</tr>
	
	<tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="userId" size="45" value="<%=actMessageVO.getUserId()%>" /></td>
	</tr>
	
	<tr>
		<td>�d�����e:<font color=red><b>*</b></font></td>
		<td><input type="TEXT" name="messageContent" size="45" value="<%=actMessageVO.getMessageContent()%>" /></td>
	</tr>

   <tr>
		<td>�ק�</td>
			<td>		 
				<input type="hidden" name="action" value="update">
				<input type="hidden" name="messageId" value="<%=actMessageVO.getMessageId()%>">
				<input type="submit" value="�e�X�ק�">
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

</body >
</html>