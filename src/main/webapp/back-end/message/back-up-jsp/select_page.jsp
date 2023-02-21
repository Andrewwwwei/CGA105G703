<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM ���ίd��: Home</title>
<style>

  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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
</head>
<body bgcolor='white'>
	
	<table id="table-1">
   <tr><td><h3>IBM Message: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM ���ίd��: Home</p>

<h3>��Ưd��:</h3>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMessage.jsp'>List</a> all Message.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="actMessage.do" >
        <b>��J�d���s��:</b>
        <input type="text" name="messageId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="actMessageSvc" scope="page" class="com.actMessage.model.ActMessageService" />
   
 
</ul>

<h3>���u�޲z</h3>

<ul>
  <li><a href='addMessage.jsp'>Add</a> a new addMessage.</li>
</ul>

</body>
</html>