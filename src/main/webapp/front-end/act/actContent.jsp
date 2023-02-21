<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>


								<!-- 查看單一揪團頁面 -->
<html>
<head>
<meta charset="UTF-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
	<link rel="website icon" href="./images/bgremove_Logo.jpg">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
    integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
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
	<body>
		<c:import url="/front-end/header.jsp" ></c:import>
	  					 <!-- content -->
  <main>
  <div class="container py-4">
    <header class="pb-3 mb-4 border-bottom">
      <a href="/" class="d-flex align-items-center text-dark text-decoration-none">
      </a>
    </header>

    <div class="px-2 mb-4 bg-light rounded-3">
    
  
      <div class="container-fluid py-5 px-0">
        <h1 class="display-5 fw-bold">揪團內容</h1>
        	<table>
        	<thead class="text-center">
        		<tr>
				<th class="col-2">揪團封面</th>
        		<th class="col-1">揪團標題</th>
				<th class="col-1">當前參與人數</th>
				<th class="col-1">揪團人數上限</th>
				<th class="col-1">報名開始時間</th>
				<th class="col-1">報名結束時間</th>
				<th class="col-1">行程開始時間</th>
				<th class="col-1">行程結束時間</th>
				<th class="col-3">揪團活動內容</th>
        		</tr>
        	</thead>
        	<tbody>
        		<tr>
        	     <td> <img src="<%=request.getContextPath()%>/back-end/act/act.do?action=showFirstImages&activityId=${actVO.activityId}" style="width:200px"/></td> 
        	     <td> <p class="fs-5 fw-bold">${actVO.activityTitle}</p></td> 
        	     <td> <p class="fs-5 text-center">${actVO.currentNumber}</p></td> 
        	     <td><p class="fs-5 text-center">${actVO.maxPeople}</p></td> 
        	     <td> <p>${actVO.registrationTime}</p></td> 
        	     <td><p>${actVO.registrationEnd}</p></td> 
        	     <td> <p>${actVO.tripStart}</p></td> 
        	     <td><p>${actVO.tripEnd}</p></td> 
        	     <td> <p class="pt-3">${actVO.activityContent}</p></td> 
        	   </tr>
			</tbody>
        </table>
  

      </div>
   
    </div>


  </div>
</main>
<!-- content end -->

<!-- Button Modal 互動視窗-->

				<!-- 跳轉至actMemberJoin申請加入揪團頁 -->
	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
 	 <form method="post" action="<%=request.getContextPath()%>/back-end/act/act.do">
		<input type="submit" class="btn btn-primary" value="參加揪團">
		<input type="hidden" name="activityId" value="${actVO.activityId}">
		<input type="hidden" name="userId" value="${actVO.userId}">
		<input type="hidden" name="sendTitle" value="${actMes.sendTitle}">
		<input type="hidden" name="sendContent" value="${actMes.sendContent}">
		<input type="hidden" name="action" value="applyJoin">

	 </form>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal"> 揪團檢舉
		</button>

	</div>

<!-- Button Modal 內容-->
							<!-- 跳轉至揪團檢舉頁面 -->
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/report/actReport.do">
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">揪團檢舉</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <table>
        	<tr>
        		<td><b>檢舉事項</b>
        			<select size="1" name="reportMatter">
						<option value= "0">騷擾行為</option>
						<option value= "1">疑似詐騙內容</option>
						<option value= "2">標題與活動內容不符</option>
						<option value= "3">其他</option>
					</select>
				</td>
        	</tr>
        	
        	<tr>
        		<td>
        			<b>檢舉內容</b><input type="text" name="reportContent" size="45">
	        		</td>
        			
        	</tr>

        </table>
      </div>
      
     
      <div class="modal-footer">
<!--       	<input type="hidden" name="action" value="actReportInsert"> -->
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
<!--         <button type="button" class="btn btn-primary">送出</button> -->
			<input type="hidden" name="activityId" value="${actVO.activityId}">
			<input type="hidden" name="reportUserId" value="${reportContent}">
			<input type="hidden" name="action" value="actReportInsert">
        	<input type="submit" value="送出">
      </div>
     </FORM>
     
    </div>
  </div>
</div>


	<!-- include footer -->
			<c:import url="/front-end/footer.jsp" ></c:import>
	</body>
</head>