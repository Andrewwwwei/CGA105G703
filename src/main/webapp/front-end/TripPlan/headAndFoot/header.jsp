<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
    integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/TripPlan/css/jquery.datetimepicker.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/TripPlan/css/header.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/TripPlan/css/userTripAll.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/TripPlan/css/tripPlan.css">
  <link rel="stylesheet" href="<%=request.getContextPath() %>/front-end/TripPlan/LeafletMarkers/css/leaflet.extra-markers.min.css">
  <link rel="icon" href="<%=request.getContextPath() %>/front-end/images/Logo.png">
   <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.3/jquery.min.js"
    integrity="sha512-STof4xm1wgkfm7heWqFJVn58Hm3EtS31XFaagaa8VMReCXAkQnJZ+jEy8PCC/iT18dFy95WcExNHFTqLyp72eQ=="
    crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="<%=request.getContextPath() %>/front-end/TripPlan/js/notion.js"></script>
     <title>7 Tour | 旅遊網站 </title>
    
<!-- below is tripPlan  -->
<link rel="stylesheet" href="https://unpkg.com/leaflet@1.9.3/dist/leaflet.css"
    integrity="sha256-kLaT2GOSpHechhsozzB+flnD+zUyjE2LlfWPgU04xyI=" crossorigin="" />
    <style>
    .hoverable{
		 opacity: 0.7;
		  transition: all 0.1s ease-in-out;
		}
		
		.hoverable:hover {
		  opacity: 1;
		  background-color: #EEEEEE;
		  border: 2px solid #BDBDBD;
		}
    </style>
</head>  

<body onload="connect()" style="overflow: auto">
  <!-- nav start -->
  <nav class="navbar navbar-expand-lg navbar-light bg-cblue sticky-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="<%=request.getContextPath() %>/front-end/index.jsp">
        <img src="<%=request.getContextPath() %>/front-end/images/headerLogo.png" id="logo">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="<%=request.getContextPath()%>/article?action=forumHome&userId=${sessionScope.usersVO.getUserId()}">論壇</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/article?action=forumHome&userId=${sessionScope.usersVO.getUserId()}">論壇首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath() %>/article?action=userPosted&userId=${sessionScope.usersVO.getUserId()}">文章列表</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">訂房</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/Room?action=toRoomIndex">訂房首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/RoomOrder?action=toThisUserOrder">我的訂單</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/ShoppingCart?action=showCart">購物車</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">揪團</a>
            <ul class="bg-cblue hover-list">
 			  <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/front-end/act/actHomePage.jsp">揪團首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/front-end/act/addAct.jsp">建立揪團</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/back-end/act/act.do?action=getLeaderAct">我的揪團</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/back-end/act/act.do?action=getMyJoinAct">參團紀錄</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="<%=request.getContextPath()%>/front-end/Faq/FaqPage.jsp">FAQ</a>
          </li>
        </ul>
        
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item bg-dblue rounded mx-3 input-group">
            <form action="tripLoc.do" method="post" class="d-flex" id="search-loc">
            </form>
          </li>

<!--    notice button -->
          <li class="nav-item user-msg">
           <button type="button" class="btn btn-primary" data-bs-toggle="modal" id="noticeBtn" data-bs-target="#staticBackdrop">
  				<i class="far fa-comment-alt "></i>
			</button>
          </li>

          <li class="nav-item mx-3 user-info">
            <img src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='${usersVO.userAccount}'" alt="會員照片" class="user-pic">
            <ul class="bg-cblue info-list">
           <!-- 基本資料 -->	
              <li id="info">
              		<a href="" class="d-block info-item text-decoration-none link-dark">會員基本資料</a>
              </li>
			  <!-- 我的行程 -->	
              <li id="trip"><a href="<%=request.getContextPath()%>/front-end/TripPlan/userTripAll.jsp" class="d-block info-item text-decoration-none link-dark">我的行程</a></li>
			  <!-- 我的收藏 -->	
			  <li id="collection" name="collection">
				 <form id="Collection" name="Collection" METHOD="post" ACTION="<%=request.getContextPath()%>/collectionServlet">
				 	<input type="hidden" name="action" value="collection">
					 <a href="<%=request.getContextPath()%>/collectionServlet?action=collection" class="d-block info-item text-decoration-none link-dark">我的收藏</a>
			 	</form>
			 </li>
			  <!-- 我的訂單 -->	
              
              <li><a href="<%=request.getContextPath()%>/RoomOrder?action=toThisUserOrder" class="d-block info-item text-decoration-none link-dark">我的訂單</a></li>
			  <!-- 登出 -->	
			  <li id="loginout">
              	<form id="login_out" name="login_out" ACTION="<%=request.getContextPath()%>/usersServlet">
	              <input type="hidden" name="action" value="loginout">
	              <a href="#" class="d-block info-item text-decoration-none link-dark">登出</a>
             	 </form>
              </li>
           <!-- 基本資料 -->
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  
  
    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-scrollable">
		    <div class="modal-content">
		    
		    	<div class="modal-header">
					<h5 class="modal-title" id="noticeLabel">會員通知</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>

				<div class="modal-body " id="noticeList">
				
				
				
				</div>

				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
				</div>
		    
		      
		      
		    </div>
		  </div>
		</div>
		
		<script>
		 let info = document.getElementById("info").addEventListener("click", function () {
		      window.open("<%=request.getContextPath()%>/front-end/member/userInfo.jsp ", "userInfo", config = "height=540,width=445, top = 100, left = 700"); 
		     });
		$(document).ready(function() {
			$("#noticeBtn").on("click",function() {
				console.log('ajax');
				$.ajax({
					url: "${pageContext.request.contextPath}/back-end/declaration/ajax",
					type : "post",
					data : {
						ajaxAction : 'notice',
						userId : '${usersVO.userId}'
					},
					success : function(data) {
						console.log('sucess');
						console.log(data);
						let noticeList = $("#noticeList");
						noticeList.empty();
						
						$.each( data , function( index , obj) {
							let notice  = JSON.parse(obj);
							noticeList = $('#noticeList');
							console.log("tripID");
							console.log(notice.tripId);
							console.log(notice);
							if(notice.status === "0"){
								let contentHtml = '<div class="card hoverable rounded-6 row m-2 mx-auto bg-light bg-opacity-10" style="max-width: 360px;"><div class="hstack gap-1 me-auto">';
						        if( "pic" in notice && notice.pic !== null  ){
						         contentHtml += '<img class="mx-auto my-auto" style="width:150px;" src="data:/jpg;base64,'+ notice.pic + '" />';
						        }
						        contentHtml += '<div class="card-body col p-1 me-auto">';
						        contentHtml += '<p class="card-text m-0 fw-bolder ">'
						          + notice.title
						          + '</p>';
						        contentHtml += '<p class="card-text m-0 fw-bolder ms-3 small">'
						          + notice.content
						          + "</p>";
						        contentHtml += '</div></div>';
						        noticeList.append(contentHtml);
							}else{
								let isMbr = '';
								if(notice.status === "1"){
									isMbr = `
						                <button class="btn btn-primary" onclick="changeStatus(`+index + `,` + notice.tripId +`,2,this)">接受</button>
						                <button class="btn btn-danger" onclick="changeStatus(`+index + `,` + notice.tripId +`,3,this)">拒絕</button>
						                `;
								}else if (notice.status === "2") {
									isMbr = `<div class="ms-auto">以接受此封邀請</div>
									<a href="${pageContext.request.contextPath}/front-end/TripPlan/tripPlan.jsp?TRIP_ID=`+notice.tripId+`" class="btn btn-primary ms-auto">進入此行程</a>`;
								}else if(notice.status === "3"){
									isMbr = `<div class="ms-auto">已拒絕此封邀請</div>`;
								}
					               let contantHtml = `<div class="card hoverable rounded-6 row m-2 mx-auto bg-light bg-opacity-10" style="max-width: 360px;"><div class="hstack gap-1 me-auto">
					                <div class="card-body col p-1 me-auto">
					                <p class="card-text m-0 fw-bolder ">`+notice.title+`</p>
					                <p class="card-text m-0 fw-bolder ms-3 small">`+notice.content+`</p>
					                <div class="ms-auto">`+isMbr+`</div>
					               </div></div>`;
					               noticeList.append(contantHtml);								
							}
							
					         
						})
					},
					error : function(xhr,status, error) {
						console.log("Error: "+ error);
					},
				});
			})
		})
		
		
		// index : start with '0' end with '24'
   function changeStatus(index,tripId,status,e){
			console.log(e);
			console.log(e.closest('div'));
     $.ajax({
          url: "${pageContext.request.contextPath}/front-end/TripPlan/tripMbr.do",
          type: "post",
          data: {
           userId: '${usersVO.userId}', 
           index : index,
           status : status,
           action: 'updateInvite',
           tripId:tripId
          },
          success: function(data) {
        	console.log('status changed');
        	console.log(status);
        	if(status === 2){
        		e.closest('div').innerHTML=`<div class="ms-auto">以接受此封邀請</div>
			<a href="${pageContext.request.contextPath}/front-end/TripPlan/tripPlan.jsp?TRIP_ID=`+tripId+`" class="btn btn-primary ms-auto">進入此行程</a>`;
        	}
        	if(status === 3){
        		e.closest('div').innerHTML=`已拒絕此封邀請`;
        	}
        	
          },
          error: function(xhr, status, error) {
             console.log("Error: " + error);
          }
    });
   }
		
	</script>

	<script>
		let MyPoint = "/DeclarationWS/${usersVO.userId}";
		let host = window.location.host;
		let path = window.location.pathname;
		let webCtx = path.substring(0, path.indexOf('/', 1));
		let endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		var webSocket;

		// disconnect
		function disconnect() {
			webSocket.close();
		}
		// connect
		function connect() {
			// create a websocket
			if (webSocket == null) {
				webSocket = new WebSocket(endPointURL);
				webSocket.onopen = function(event) {
					console.log("Connect Success!");
				};

				webSocket.onmessage = function(event) {
					console.log('onMessage');
					onMess();
				};

				webSocket.onclose = function(event) {
					console.log("Disconnected!");
				};
			}
		}
	</script>
  <!-- nav end -->