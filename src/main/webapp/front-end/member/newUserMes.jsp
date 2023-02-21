<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>
<% 
Object  mes = session.getAttribute("userAccount");
String userAccount=mes.toString();

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>newUserMes</title>
</head>
<body>
				<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/usersServlet" name="mes">
					 <input	id="userAccount" type="text" name="userAccount" value="<%=userAccount%>"> 
					 <input type="hidden" name="action"	value="NewuserMail">
					  <input type="submit"value="送出">
				</FORM>
</body>
<script>
setTimeout('myrefresh()',500);
function myrefresh()
{
	document.mes.submit();
}
</script>
</html>