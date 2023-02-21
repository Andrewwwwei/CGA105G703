<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
	<head>
		<!-- MDB -->
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet" />
	    <!-- Google Fonts -->
	    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet" />
	    <!-- MDB -->
	    <link href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css" rel="stylesheet" />
	    <!-- MDB -->
  		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>
		<!-- MDB -->
		<!-- JQuery -->
		<script src="https://code.jquery.com/jquery-3.6.3.min.js"
  				integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
  				crossorigin="anonymous"></script>
		<!-- bootstrap Icon -->
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
		

		<!-- dataTable -->
		<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
		<script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
		<script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>
	
		<!-- sweetAleart -->
		<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
		
		<link rel="website icon" href="../sources/images/logo.png">
		<link rel="stylesheet" href="../sources/css/back.css">
		<link rel="stylesheet" href="css/decShowOne.css">
		<link rel="icon" href="../sources/images/logo.ico" type="image/x-icon" />
		<!-- lottiefiles -->
		<script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
		<title>7 Tour : 公告管理</title>
		

	</head>
	<body onload="connect() , hideLottie()" onunload="disconnect()">
		<%@ include file="/back-end/sidebar.jsp"%>
		<div class="mainPage">
		
		<!-- info ------------------------------------------------------------- -->	
		
			<nav style="--bs-breadcrumb-divider: url(&#34;data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='currentColor'/%3E%3C/svg%3E&#34;);" aria-label="breadcrumb">
  				<ol class="breadcrumb">
    				<li class="breadcrumb-item"><a href="#">Home</a></li>
    				<li class="breadcrumb-item"><a href="<%=request.getContextPath()%>/back-end/declaration/?action=aside">公告管理</a></li>
   					<li class="breadcrumb-item active" aria-current="page">公告資訊</li>
				</ol>
			</nav>
			
			<div class="maintop info p-3 mb-2 bg-light text-dark shadow p-3 mb-5 bg-body rounded">
				<h3>單一公告管理</h3>
				<div class="info-button">
					<button type="button" class="btn btn-primary" data-mdb-toggle="modal" data-mdb-target="#announceModal" style="background-color: #041427"> Announce </button>
				</div>
			</div>
			
				
		<!-- Content ------------------------------------------------------------- -->	
		
			<div class="listOneContent p-3 mb-2 bg-light text-dark shadow p-3 mb-5 bg-body rounded">

				<div class="contentID">				
				<h4>公告編號 : ${decVO.declarationID}</h4>
				</div>
				<div class="horizon "><hr></div>

				<div class="contentMain">
					
					<table class="table  table-hover ">	
						<tbody>
							<tr>
								<td class="tableTitle col-3">公告編號 </td>
								<td class="col-1"> : </td>
								<td class="col-5 text-wrap"><p>${decVO.declarationID}</p></td>
							</tr>
							<tr>
								<td class="tableTitle col-3">公告標題 </td>								
								<td class="col-1"> : </td>		
								<td class="col-5"><p>${decVO.title}</p></td>							
							</tr>
							<tr>
								<td class="tableTitle col-3">公告內容 </td>
								<td class="col-1"> : </td>
								<td class="col-5"><p>${decVO.content}</p></td>							
							</tr>
							<tr>
								<td class="tableTitle col-3">公告圖示  </td>								
								<td class="col-1"> : </td>
								<td class="col-5">
									<c:if test="${not empty decVO.pic}">
										<img style="width: 60%;" src="<%=request.getContextPath()%>/back-end/declaration/?action=showPic&decID=${decVO.declarationID}">
									</c:if>
									<c:if test="${empty decVO.pic}">
										尚未上傳
									</c:if>
								</td>							
							</tr>
							<tr>
								<td class="tableTitle col-3">最後更新日期  </td>								
								<td class="col-1"> : </td>
								<td class="col-5"><p>${decVO.date}</p></td>							
							</tr>
						</tbody>
					
					</table>	
					
				</div>
<!-- -- --------------------------------------->		
				<div class="horizon"><hr></div>		
				<div class="text-right">
					<button type="button" id="update-btn" class="btn btn-secondary" data-mdb-toggle="modal" data-mdb-target="#updateForm"> Update </button>			
					<button type="button" id="announce-btn" class="btn btn-primary " data-mdb-toggle="modal" data-mdb-target="#announceModal"> Announce </button>
				</div>
			</div>
			
		<!-- Update ------------------------------------------------------------- -->	
	
			<div class="modal fade .modal-fullscreen-md-down" id="updateForm" tabindex="-1"  aria-hidden="true">
				<div class="modal-dialog">
				    <div class="modal-content">
				 	  	<div class="modal-header">
				   		    <h5 class="modal-title"> 更新公告內容 :</h5>
				       		<button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
				     	</div>
				     	
				      	<form method="post" action="<%=request.getContextPath()%>/back-end/declaration/" enctype="multipart/form-data">
					      	<div class="modal-body">
					      		
					        	<div class="mb-3">
					        	公告編號 : ${decVO.declarationID}
					        	</div>
								
					        	<div class="mb-3">
					        		<label for="update-title" class="col-form-label">公告標題 :</label><p style="color:red;">${updateErrorMsgs.title}</p>
					        		<input type="text" class="form-control" id="update-title" name="decTitle" value="${decVO.title}" maxlength="100">
					        	</div>

					        	<div class="mb-3">
					        		<label for="update-content" class="col-form-label">公告內容 :</label><p style="color:red;">${updateErrorMsgs.content}</p>
									<textarea class="form-control" id="update-content" name="decContent" maxlength="200"> ${decVO.content}</textarea>
					        	</div>
			
					        	<div class="mb-3">
					        		<label for="update-pic" class="col-form-label">公告圖示 :</label>
					        		<input type="file" class="form-control" id="update-pic" name="decPic" accept=".jpg,.png,.jpeg">
					        	</div>
								
								<input type="hidden" name="action" value="update" >
								<input type="hidden" name="decID" id="setUpdateID" value="${decVO.declarationID}">
					      	</div>
					      	
					      	<div class="modal-footer">
		        				<button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">關閉</button>
					        	<input type="submit" class="btn btn-primary" value="更新">
					      	</div>
					    </form>
				    </div>
				</div>
			</div>
		<!-- Announce -->
			<div class="modal fade modal-lg" id="announceModal" tabindex="-1" aria-labelledby="announceModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						
						<div class="modal-header">
							<h5 class="modal-title" id="announceModal">發送公告</h5>
							<button type="button" class="btn-close" data-mdb-dismiss="modal" aria-label="Close"></button>
						</div>
						
							<div class="modal-body" id="modal-body">
								<div id="location">
									<div class="ulocation px-3">
										<label class="px-3" for="north"> <input type="checkbox" class="input-header location-input" id="north" value="北部" hidden><p class="p-header">北部</p></label>
										<label class="px-3" for="kel"><input type="checkbox" class="north-input location-input" id="kel" name="location" value="基隆">基隆</label>
										<label class="px-3" for="ila"><input type="checkbox" class="north-input location-input" id="ila" name="location" value="宜蘭">宜蘭</label>
										<label class="px-3" for="tpe"><input type="checkbox" class="north-input location-input" id="tpe" name="location" value="台北">臺北</label>
										<label class="px-3" for="ntpc"><input type="checkbox" class="north-input location-input" id="ntpc" name="location" value="新北">新北</label>
										<label class="px-3" for="tyn"><input type="checkbox" class="north-input location-input" id="tyn" name="location" value="桃園">桃園</label>
										<label class="px-3" for="hsc"><input type="checkbox" class="north-input location-input" id="hsc" name="location" value="新竹">新竹</label>
									</div>
						
									<div class="ulocation px-3">
										<label class="px-3" for="midd"> <input type="checkbox" class="input-header location-input" id="midd" value="中部" hidden><p class="p-header">中部</p></label>
										<label class="px-3" for="mal"><input type="checkbox" class="midd-input location-input" id="mal" name="location" value="苗栗">苗栗</label>
										<label class="px-3" for="txg"><input type="checkbox" class="midd-input location-input" id="txg" name="location" value="臺中">臺中</label>
										<label class="px-3" for="cwh"><input type="checkbox" class="midd-input location-input" id="cwh" name="location" value="彰化">彰化</label>
										<label class="px-3" for="yun"><input type="checkbox" class="midd-input location-input" id="yun" name="location" value="雲林">雲林</label>
										<label class="px-3" for="ntc"><input type="checkbox" class="midd-input location-input" id="ntc" name="location" value="南投">南投</label>
									</div>
									
									<div class="ulocation px-3">
										<label class="px-3" for="south"> <input type="checkbox" class="input-header location-input" id="south" value="南部" hidden><p class="p-header">南部</p></label>
										<label class="px-3" for="cyi"><input type="checkbox" class="south-input location-input" id="cyi" name="location" value="嘉義">嘉義</label>
										<label class="px-3" for="tnn"><input type="checkbox" class="south-input location-input" id="tnn" name="location" value="臺南">臺南</label>
										<label class="px-3" for="khh"><input type="checkbox" class="south-input location-input" id="khh" name="location" value="高雄">高雄</label>
										<label class="px-3" for="pif"><input type="checkbox" class="south-input location-input" id="pif" name="location" value="屏東">屏東</label>									
									</div>
								
									<div class="ulocation px-3">
										<label class="px-3" for="east"> <input type="checkbox" class="input-header location-input" id="east" value="東部" hidden><p class="p-header">東部</p></label>
										<label class="px-3" for="hun"><input type="checkbox" class="east-input location-input" id="hun" name="location" value="花蓮">花蓮</label>
										<label class="px-3" for="ttc"><input type="checkbox" class="east-input location-input" id="ttc" name="location" value="臺東">臺東</label>
									</div>
																	
									<div class="ulocation px-3 pb-3">
										<label class="px-3" for="island"> <input type="checkbox" class="input-header location-input" id="island" value="離島" hidden><p class="p-header">離島</p></label>
										<label class="px-3" for="peh"><input type="checkbox" class="island-input location-input" id="peh" name="location" value="澎湖">澎湖</label>
										<label class="px-3" for="mfk"><input type="checkbox" class="island-input location-input" id="mfk" name="location" value="連江">連江</label>
										<label class="px-3" for="lnn"><input type="checkbox" class="island-input location-input" id="lnn" name="location" value="金門">金門</label>
									</div>                                                                          
						        </div>                                
								
								<div id="member" class="hstack gap-1 me-auto">
									
									<div id="mem-list" class="vstack gap-2 col-md-5 mx-auto">
										<p id="list-header" class="p-header">會員名單 : </p>
										
										<div id="list-contents" class="scrollable">
										
										</div>
									</div>
									
									<div class="vr vr-blurry"></div>
									
									
									<div id="mem-aria" class="vstack gap-2 col-md-5 mx-auto">
										<label id="list-label" class="">加入名單 :
											<input type="text" placeholder="請輸入會員編號" id="list-input" style="margin : 10px auto ;width:60%">
										</label>
										
										<div id="add-list" class = "scrollable">
											
										</div>
									
									
									</div>
								</div>
								
							</div>
						
							<div class="modal-footer">
							 	<button type="button" class="btn btn-secondary" data-mdb-dismiss="modal">Close</button>
					    		<input type="hidden" name="action" value="announce">					
					    		<input type="hidden" name="declarationID" value="${decVO.declarationID}">					
								<button type="button" onclick="announce()" class="btn btn-primary" >Announce</button>
							</div>
					</div>
				</div>
			</div>
				
				
		</div>
		
		<!-- lottie-file  -->
		<div id="preloader" >		
		<lottie-player id="load" src="https://assets1.lottiefiles.com/packages/lf20_a2chheio.json"   speed="1"  style="width: 300px; height: 300px;"  loop  autoplay></lottie-player>
		</div>
		
		<script type="text/javascript" src="js/showOnePage.js"></script>
		<script type="text/javascript" src="js/decNotions.js"></script>
		
		
		
		<script>
		//WebSocket
		let MyPoint = "/DeclarationWS/0";
		let host = window.location.host;
		let path = window.location.pathname;
		let webCtx = path.substring(0, path.indexOf('/', 1));
		let endPointURL = "ws://" + window.location.host + webCtx + MyPoint ;
		var webSocket = null;
		
		// connect
		function connect(){
			// create a websocket
			if(webSocket == null){
				webSocket = new WebSocket(endPointURL);				
				webSocket.onopen = function(event) {
					console.log("Connect Success!");	
				};
	
				webSocket.onclose = function(event) {
					console.log("Disconnected!");
				};
			}
		}
		
		// disconnect
		function disconnect(){
			webSocket.close();
		}
		// announce ajax
		function announce (){
			 $('#preloader').show();
             $('#load').show();
			let memIds = $("input.addedcard").map(function() {
				let val =  $(this).val();
			    return  val;
			}).get();
			
			let locations = $('.location-input:checked').map(function() {
				let val =  $(this).val();
			    return  val;			
			    }).get();

			let memIdsJson =  JSON.stringify(memIds);
			let locationsJson =  JSON.stringify(locations);
			
			$.ajax({
			      url: "${pageContext.request.contextPath}/back-end/declaration/ajax",
			      type: "post",
			      data: {
			        ajaxAction:'annouceJson',
			        memIDs:memIdsJson,
			        locations:locationsJson,
			      },
			      success: function(data) {
			    	  
			            if( data.length === 0){
			       setTimeout(() => {
			              hideLottie();
			              AnnounceFail2('發佈','${decVO.declarationID}');
			              
			        }, 1000
			       );
			       return;
			      }
			    		let title = '${decVO.title}';
						let content = '${decVO.content}';
						let id = '${decVO.declarationID}';
						let status = '0';
						let WSdata = { 'title' : title , 'content' : content , 'list': data , 'status':status , 'id':id};
						let WSdataJSON = JSON.stringify(WSdata);
												
												
						webSocket.send(WSdataJSON);
						setTimeout(() => {
							  hideLottie();
							  // add Toast here
							  AnnounceSuccess('發佈','${decVO.declarationID}');
							  console.log("Pause finished.");
							}, 1100
						);
			      },
			      error: function(xhr, status, error) {
			    	    fail('發佈');
			    	    console.log("Error: " + error);
			    	  }
				});
		}
		 
		// usersQuery ajax			
			$(document).ready(function() {
				$("#list-input").on("keyup", function() {
				    var input = $(this).val();
				    var count = 0;
				    $.ajax({
				      url: "${pageContext.request.contextPath}/back-end/declaration/ajax",
				      type: "post",
				      data: {
				        input:input,
				        ajaxAction : 'usersJson',
				        	
				      },
				      success: function(data) {
							var addlist = $("#add-list");
							addlist.empty();
							$.each(data,function(index,object){
								
									let contentHtml = '<div class="card hoverable rounded-6 row m-2 bg-light bg-opacity-10" style="max-width: 360px;"><div class="hstack gap-1 me-auto">';
									contentHtml += '<div class="card-body col p-1 me-auto">';
									contentHtml += '<p class="card-text m-0 fw-bolder ">帳號: ' + object.userAccount + '</p>';
									contentHtml += '<p class="card-text m-0 fw-bolder ms-3 small">名稱: ' + object.userName + "</p>";
									contentHtml += '<p class="card-text m-0 ms-3 text-muted small">地址: ' + object.userAddress + "</p>";
									contentHtml += '<p class="card-text m-0 ms-3 text-muted small">電話: ' + object.userPhone + "</p>";
									contentHtml += '<p class="card-text m-0 ms-3 text-muted small"><small class="text-muted">性別: ' + object.userGender + "</small></p>";
									contentHtml += '<p class="card-text m-0 ms-3 text-muted small"><small class="text-muted card-id" id="card-id-'+ object.userId +'">會員編號: ' + object.userId + "</small></p></div>";
												
									contentHtml += '<div class="text-center"><button id="addBtn'+ object.userId +'" type="button" class="btn btn-primary btn-floating" onclick="addMem('+object.userId+')"><i class="fas fa-plus"></i></button></div></div></div>';								
									addlist.append(contentHtml);
				
							});
				      },
				      error: function(xhr, status, error) {
				    	    console.log("Error: " + error);
				    	  }
					});
				    
				    
				});
			});
			
			function hideLottie(){
                $('#preloader').hide();
                $('#load').hide();
			}
		</script>	

	</body>
</html>