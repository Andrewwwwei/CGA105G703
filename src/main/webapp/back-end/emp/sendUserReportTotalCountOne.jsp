<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); 
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <!-- <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous"> -->
  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/back.css">
  <title>7 Tour | 發送個人訊息 </title>
  <style>
	#sendCountBorder{
	position:absolute;
	left:220px;
	top:50px;
	font-family:"微軟正黑體";
	
	}
	  #sendCountBorder #search{
	
	font-size:30px;
	
	}
	  #sendCount #userPic{
	 width:50px;
	 height:60px;
	 margin:0px;
	 padding:0px;
	}  
	  table {
		width: 1200px;
		margin-top: 5px;
		margin-bottom: 5px;
	  }
	  th{
	  background:#00BDBD;
	  }
	  table, th, td {
	    border: 1px solid #CCCCFF;
	  }
	  th {
	    padding: 5px;
	    text-align: center;
	  }
	td{
	text-align: center;
	}
 </style>
</head>

<body>
<div id="sendCount" style="background-color:black;width:100%;height:720px">
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<ul style="display:inline-block">
	    	<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red;font-size:28px;list-style-type: none;margin:-10px 0 0 240px;">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div id=sendCountBorder>
		<table>
			<tr>
				<th style="color:white">停權</th>
				<th style="color:white">復權</th>
				<th style="color:white">狀態</th>
				<th style="color:white">頭像</th>
				<th style="color:white">信箱</th>
				<th style="color:white">姓名</th>
				<th style="color:white">暱稱</th>
				<th style="color:white">註冊日</th>
				<th style="color:white">論壇等級</th>
				<th style="color:white">發文數</th>
				<th style="color:white">按讚數</th>
				<th style="color:white">揪團總評分</th>
				<th style="color:white">評分總人數</th>
				<th style="color:white">被檢舉次數</th>
			</tr>
				<tr>
					<td>
					  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
					     <input type="submit" value="確定">
					     <input type="hidden" name="userAccount"  value="<%=usersVO.getUserAccount()%>">
					     <input type="hidden" name="action" value="suspension"></FORM>
					</td>
					<td>
					  <FORM id="sendOne"METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
					     <input type="submit" value="確定">
					     <input type="hidden" name="userAccount"  value='<%=usersVO.getUserAccount()%>'>
					     <input type="hidden" name="action" value="disSuspensionOneForone"></FORM>
					</td>
					<td class="status" style="color:white"><%=usersVO.getUserStatus()%></td>
					<td style="color:white"><img id="userPic" src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='<%=usersVO.getUserAccount()%>'" alt=""></td>
					<td style="color:white"><%=usersVO.getUserAccount()%></td>
					<td style="color:white"><%=usersVO.getUserName()%></td>
					<td style="color:white"><%=usersVO.getUserNickname()%></td>
					<td style="color:white"><fmt:formatDate value="<%=usersVO.getUserRegistrationDate()%>" pattern="yyyy-MM-dd HH:mm:ss "/></td>
					<td class="count" style="color:white"><%=usersVO.getUserForumLevel()%></td>
					<td style="color:white"><%=usersVO.getArtCount()%></td>
					<td style="color:white"><%=usersVO.getLikeTotalCount()%></td>
					<td style="color:white"><%=usersVO.getAlltogetherScore()%></td>
					<td style="color:white"><%=usersVO.getAlltogetherScorePeople()%></td>
					<td style="color:white"><%=usersVO.getReportTotalCount()%></td>
				</tr>
		</table>
	</div>

</div>
   <c:import url="/back-end/sidebar.jsp"></c:import>
 
 <script> let count=document.getElementsByClassName("count");
 for(let i = 0;i<count.length;i++){
 	if(document.getElementsByClassName('count')[i].innerText==0){
 	document.getElementsByClassName('count')[i].textContent="一般";}
 	if(document.getElementsByClassName('count')[i].innerText==1){
 	document.getElementsByClassName('count')[i].textContent="VIP";}
 	}
 </script>
  <script>let status=document.getElementsByClassName('status');
 for(let i = 0;i<status.length;i++){
	 if(document.getElementsByClassName('status')[i].innerText==0){
		 document.getElementsByClassName('status')[i].innerText="正常";
		}else{
			document.getElementsByClassName('status')[i].innerText="停權";
			document.getElementsByClassName('status')[i].style.color="red";
			
			}
 }
 </script>
</body>  
  </html>