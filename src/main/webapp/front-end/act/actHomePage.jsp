<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.act.model.*"%>

<% 
	ActService actSvc = new ActService();
	List list = actSvc.getAll();
	request.setAttribute("list", list);
	
%>
						<!-- 揪團首頁 -->
<!DOCTYPE html>
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
		.row{
			text-align: center;
		
		}
	</style>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
	
	
						<!-- 整個頁面 -->
<div class="container"  background-color:#f0f0f0;   flex-wrap: wrap;">
		
  <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
  	<div class="carousel-indicators">
    	<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
    	<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1" aria-label="Slide 2"></button>
    	<button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2" aria-label="Slide 3"></button>
    </div>
  
  <div class="carousel-inner">
    <div class="carousel-item active" data-bs-interval="10000">
      	 <img src="images/travel1.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
          <h5></h5>
      </div>
    </div>
    
    <div class="carousel-item" data-bs-interval="2000">
      	 <img src="images/travel2.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
      	 <h6></h6>
      </div>
    </div>
    
    <div class="carousel-item">
        <img src="images/travel3.jpg" class="d-block w-100" alt="...">
      <div class="carousel-caption d-none d-md-block">
         <h6></h6>
      </div>
     
    </div>
    
    
  </div>
  
  	<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="prev">
    	<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    	<span class="visually-hidden">Previous</span>
  	</button>
  
  	<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleDark" data-bs-slide="next">
   	 	<span class="carousel-control-next-icon" aria-hidden="true"></span>
    	<span class="visually-hidden">Next</span>
  	</button>
  </div>
  
<%-- <a href="<%=request.getContextPath()%>/front-end/member/memberHome.jsp">我的揪團</a> --%>
      
</div>
<br>
						<h1 align="center">揪團列表</h1>
							<!-- 點選我的揪團 -->

<%-- 	<FORM method="post" action="<%=request.getContextPath()%>/back-end/act/act.do"> --%>
<!-- 		<input type="submit" value="申請加入揪團列表"> -->
<%--  		<input type="hidden" name="userId" value="${sessionScope.usersVO.userId}"> --%>
<!-- 		<input type="hidden" name="userId" value="2"> -->
<!-- 		<input type="hidden" name="action" value="getMyJoinAct"> -->
	
<!-- 	</FORM> -->



						<!-- 		揪團列表		 -->
		<div class="actContents d-flex justify-content-evenly " style="display:flex" id="actContent">
			<c:forEach var="actVO" items="${list}" >
				<div class="card shadow p-3 mb-5 bg-body rounded" style="width: 18rem; ">
<%-- 					<img src="${pageContext.request.contextPath}/back-end/act/act.do?action=getImg&activity_id=${actVO.activity_id}" class="card-img-top" alt="..."> --%>
					<div class="card-body">
							<h5 class="card-title">${actVO.activityTitle}</h5>
							<p class="card-text">${actVO.activityContent}</p>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/act/act.do">
								<input type="submit" value="揪團詳細內容">
								<input type="hidden" name="activityId" value="${actVO.activityId}">
								<input type="hidden" name="action" value="SelectOneAct">
						</FORM>
					</div>
				</div>
			</c:forEach>
		</div>	

	
		<!-- 最底層間距 -->
		<div class="Bottom distance"></div>

		
	
	<!-- include footer -->
			<c:import url="/front-end/footer.jsp" ></c:import>
</body>
</html>