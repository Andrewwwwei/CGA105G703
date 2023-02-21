<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM 揪團成員: Home</title>
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

<p>This is the Home page for IBM 揪團成員: Home</p>

<h3>揪團成員:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="member" items="${errorMsgs}">
			<li style="color:red">${member}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllMember.jsp'>List</a> all Member.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="actMember.do" >
        <b>輸入會員編號:</b>
        <input type="text" name="userId">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <jsp:useBean id="actMemberSvc" scope="page" class="com.actMember.model.ActMemberService" />
   
 
</ul>

<h3>成員管理</h3>

<ul>
  <li><a href='addMember.jsp'>Add</a> a new addMember.</li>
</ul>

</body>
</html>