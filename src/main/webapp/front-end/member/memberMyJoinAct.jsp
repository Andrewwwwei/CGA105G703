<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.*"%>
<%@ page import="com.actMember.model.*"%>
<%@ page import="com.act.model.*"%>
 <jsp:useBean id="userSvc" scope="page" class="com.Users.model.UsersService" />
 <jsp:useBean id="actMemberSvc" scope="page" class="com.actMember.model.ActMemberService" />
 

				<!--   -->
<html>
<head>
<meta charset="UTF-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  	<link rel="stylesheet" href="./css/header.css">
	<link rel="website icon" href="./images/bgremove_Logo.jpg">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
    integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
	<!--   dataTables -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
 	<title>7 Tour | 旅遊規劃</title>
	<style>
		.container{
			/*整個頁面*/
			display : flex;
			flex-direction : column;
			justify-content: space-between; 
			flex-wrap: wrap;
		}
		.location{
			margin-top:50px;
			width: 900px;
			display: table-cell;
  			vertical-align: middle;
  			/*文字水平置中*/
			text-align: center;
		}
		.clearfix:after {
        	content:"";
        	display:table;
        	clear:both;
    	}
    	a {
 			 color: black;
 			 text-decoration: none;
		}
		#actContent{
			display: flex;
			justify-content:space-around;
			flex-wrap : wrap;
			
		}
		.card{
			margin-bottom: 50px;
		}
		.Bottom distance::after{
			width: 100%;
			height: 50px;
		 	content:"";
  			display: block;
  			clear:both;
		}

	</style>
</head>
<body>
<c:import url="/front-end/header.jsp" ></c:import>
    <main>
  <div class="container py-4">
    <header class="pb-3 mb-4 border-bottom">
      <a href="/" class="d-flex align-items-center text-dark text-decoration-none">
      </a>
    </header>

    <div class="px-1 mb-4 bg-light rounded-3">
    
  
      <div class="container-fluid p-0">
        <h1 class="display-5 fw-bold">我加入的揪團列表</h1>
        <div class="table-responsive" style="border: 1px solid #ccc;"></div>
        
		<table id="myTable" class="display" style="width: 100%">
		  <thead bgcolor="#C0C0C0">
	        	 <tr>
	        	 	<th>揪團封面</th>
					<th>揪團標題</th>
					<th>當前參與人數</th>
					<th>揪團人數上限</th>
					<th>報名開始時間</th>
					<th>報名結束時間</th>
					<th>行程開始時間</th>
					<th>行程結束時間</th>
					<th>揪團內容</th>
					<th>申請狀態</th>
	        	</tr>
        	</thead>
        	<tbody>
        	   <c:forEach var="actVO" items="${actVOList}">
        	  <tr>
        	   	 <td> <img src="<%=request.getContextPath()%>/back-end/act/act.do?action=showFirstImages&activityId=${actVO.activityId}" style="width:200px"/></td> 
        	     <td><p class="fs-5">${actVO.activityTitle}</p></td> 
        	     <td><p class="fs-5">${actVO.currentNumber}</p></td> 
        	     <td><p class="fs-5">${actVO.maxPeople}</p></td> 
        	     <td><p>${actVO.registrationTime}</p></td> 
        	     <td><p>${actVO.registrationEnd}</p></td> 
        	     <td><p>${actVO.tripStart}</p></td> 
        	     <td><p>${actVO.tripEnd}</p></td> 
        	     <td><p>${actVO.activityContent}</p></td> 
        	     <td><p>
	        	     <c:if test="${actMemberSvc.getOneActMember(sessionScope.usersVO.userId, actVO.activityId).memberStatus == 0}">
						申請中
					 </c:if>
					 <c:if test="${actMemberSvc.getOneActMember(sessionScope.usersVO.userId, actVO.activityId).memberStatus == 1}">
						已加入
					 </c:if>
					 <c:if test="${actMemberSvc.getOneActMember(sessionScope.usersVO.userId, actVO.activityId).memberStatus == 2}">
						已拒絕
					 </c:if>
        	     </p></td> 
 	  		   </tr>
    		</c:forEach>
    		</tbody>	
          </table>
          
      </div>
    </div>
  </div>
</main>
<!-- 			會員加入表格 -->


  	<div class="d-grid gap-2 d-md-flex justify-content-md-end">


	</div>
		<!-- include footer -->
			<c:import url="/front-end/footer.jsp" ></c:import>
<!-- datatable套版 -->
<script >
$(document).ready( function () {
    $('#myTable').DataTable();
} );

</script>
</body>
</html>