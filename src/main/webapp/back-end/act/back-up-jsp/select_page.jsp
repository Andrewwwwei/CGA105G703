<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="back.css">

  <style type="text/css">
   


  </style>

</head>
<title>IBM Emp: Home</title>

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
   <tr><td><h3>IBM Act: ���ά��ʫ�x</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Act: Home</p>

<h3>���ά��ʬd��:</h3>

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
  <li><a href='listAllAct.jsp'>List</a> all Emps.  <br><br></li>
  
  <li>
    <FORM METHOD="post" ACTION="act.do" >
        <b>��J���ά��ʽs��:</b>
        <input type="text" name="activityId" >
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>
  
 <li>
     <FORM METHOD="post" ACTION="act.do" >
       <b>��@��{�s��:</b>
    	<input type="text" name="tripId" >
       <input type="hidden" name="action" value="get_One_Trip">
       <input type="submit" value="�e�X">
    </FORM>
  </li>

  <jsp:useBean id="actSvc" scope="page" class="com.act.model.ActService" />

  <li>
     <FORM METHOD="post" ACTION="act.do" >
       <b>��ܴ��ά��ʽs��:</b>
       <select size="1" name="activityId">
         <c:forEach var="actVO" items="${actSvc.all}" > 
          <option value="${actVO.activityId}">${actVO.activityId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
	
</ul>


<h3>��{�޲z</h3>

<ul>
  <li><a href='addAct.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>