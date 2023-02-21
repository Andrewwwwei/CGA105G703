<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.emp.model.*"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
Object str = session.getAttribute("reportTotalCount");
Integer reportTotalCount = Integer.valueOf(str.toString());
UsersService usersSvc = new UsersService();
List<UsersVO> list = usersSvc.getAllByReportTotalCount(reportTotalCount);
pageContext.setAttribute("list",list);
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
    padding: 3px;
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
				<th style="color:white">批次處理</th>
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
				<th style="color:white">檢舉次數</th>
			</tr>
			<%@ include file="page1.file" %> 
			<c:forEach var="UsersVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				
				<tr>
					<td>
					  <FORM id="sendOne"METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
					     <input type="submit" value="確定">
					     <input type="hidden" name="userAccount"  value='${UsersVO.userAccount}'>
					     <input type="hidden" name="action" value="suspensionOne"></FORM>
					</td>
					<td>
					  <FORM id="sendAll"METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
					     <input class="checkAll"type="checkbox" name="userAccountAll"  value="${UsersVO.userAccount}" style="width:25px;height:25px">
					     <input type="hidden" name="action" value="suspensionAll">
					  </FORM>
					</td>
					<td>
					  <FORM id="sendOne"METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" style="margin-bottom: 0px;">
					     <input type="submit" value="確定">
					     <input type="hidden" name="userAccount"  value='${UsersVO.userAccount}'>
					     <input type="hidden" name="action" value="disSuspensionOne"></FORM>
					</td>
					<td class="status" style="color:white">${UsersVO.userStatus}</td>
					<td style="color:white"><img id="userPic" src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='${UsersVO.userAccount}'" alt=""></td>
					<td style="color:white">${UsersVO.userAccount}</td>
					<td style="color:white">${UsersVO.userName}</td>
					<td style="color:white">${UsersVO.userNickname}</td>
					<td style="color:white"><fmt:formatDate value='${UsersVO.userRegistrationDate}' pattern="yyyy-MM-dd HH:mm:ss "/></td>
					<td class="count" style="color:white">${UsersVO.userForumLevel}</td>
					<td style="color:white">${UsersVO.artCount}</td>
					<td style="color:white">${UsersVO.likeTotalCount}</td>
					<td style="color:white">${UsersVO.alltogetherScore}</td>
					<td style="color:white">${UsersVO.alltogetherScorePeople}</td>
					<td style="color:white">${UsersVO.reportTotalCount}</td>
					
				</tr>
			</c:forEach>
		</table>
		<div>
			<input id="chAll"style="width:80px;height:40px;border-radius:10px;background-color:lightgreen;font-size:18px" value="批次勾選">
			<input id="disChAll"style="width:80px;height:40px;border-radius:10px;background-color:lightyellow;font-size:18px" value="批次取消">
			<input id="chAllSend"type="button" style="width:80px;height:40px;border-radius:10px;background-color:lightblue;font-size:18px" value="送出">
		</div>
		<%@ include file="page2.file" %>
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
 <script> 
 			let checkAll=document.getElementsByClassName("checkAll");
 			let chAll=document.getElementById("chAll").addEventListener("click", function () {
			for(let i = 0;i<checkAll.length;i++){
    		document.getElementsByClassName('checkAll')[i].checked=true;
      		}
		});
 			let disChAll=document.getElementById("disChAll").addEventListener("click", function () {
 				for(let i = 0;i<checkAll.length;i++){
 	    		document.getElementsByClassName('checkAll')[i].checked=false;
 	      		}
 			});
 </script>
 <script>let chAllSend=document.getElementById('chAllSend').addEventListener("click", function () {
				document.getElementById('sendAll').submit();
 });	
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