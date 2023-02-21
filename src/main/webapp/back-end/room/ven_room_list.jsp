<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- ===================== Bootstrap 5 CSS ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- ===================== fontawesome ========================== -->
    <script src="https://kit.fontawesome.com/37ac1c7b87.js" crossorigin="anonymous"></script>
    
    <!-- ===================== jquery ========================== -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    

    <title>房型管理</title>
    
    <style>
    	.btn-outline-primary{
    		background-color: #060C3C;
    		color: #FFF;
    		border: none;
    	}
    	.btn-outline-primary:hover{
    		background-color: #060C3C;
    		color: #FFF;
    		border: none;
    		filter: drop-shadow(2px 2px 2px black);
    	}
    	.toggle:hover{
    		cursor: pointer;
    	}
    	.room:hover{
    		background-color: #DCE2EB;
    	}
    	.swal-title {
		  margin-bottom: 28px;
		}
		#preloader {
            position: fixed;
            width: 100%;
            height: 100%;
            left: 0%;
            top: 0%;
            background-color: white; 
            opacity: 0.5;
            z-index: 9998;
        }
        #load{
            position:fixed;
            top:50%;
            left:50%; 
            margin-left:-150px;
            margin-top:-150px;
            z-index: 9999;
        }
    </style>
</head>

<body>
	<c:import url="/back-end/sidebar.jsp" ></c:import>
    <div class="container mt-5" style="margin-left: 250px; width: 1000px">
    	<div><a class="btn btn-outline-primary" href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp"><i class="fa-solid fa-arrow-left-long"></i>&emsp;返回廠商列表</a></div>
    	<div class="my-4 d-flex">
    		<h5 class="text-center col-10">${venName}</h5>
    		<div class="col-2 text-end"><a class="btn btn-success" href="<%=request.getContextPath()%>/RoomBackEnd?action=toAddRoom&venId=${venId}"><i class="fa-solid fa-plus"></i>&emsp;新增房型</a></div>
    	</div>
		<!-- tables -->
		<div class="tab-content my-3" id="order-tables">
	        <table class="col-10 table text-center align-middle">
	            <thead style="background-color: #BFCCDC;">
	                <tr>
	                    <th scope="col" class="col-1">房型編號</th>
						<th scope="col">房型名稱</th>
						<th scope="col" class="col-1">可住人數</th>
						<th scope="col">狀態</th>
						<th scope="col" class="col-1">價格</th>
						<th scope="col">房型圖片</th>
						<th scope="col">修改</th>
	                </tr>
	            </thead>
	            <tbody>
	            <c:forEach var="room" items="${roomVOList}">
	                <tr class="room">
	                	<td class="toggle">${room.roomId}</td>
						<td class="toggle">${room.roomName}</td>
						<td class="toggle">${room.roomPeople}人</td>
						<td>
							<c:if test="${room.roomUpdate == 0}"><a class="btn btn-secondary">已下架</a></c:if>
							<c:if test="${room.roomUpdate == 1}"><a class="btn btn-outline-success">已上架</a></c:if>
						</td>
						<td class="toggle">$<fmt:formatNumber type="number" pattern="#,###" value="${room.roomPrice}" /></td>
						<td><a class="btn btn-outline-primary" href="<%=request.getContextPath()%>/RoomBackEnd?action=toRoomPic&roomId=${room.roomId}">查看/新增圖片</a></td>
						<td><a class="btn btn-outline-primary" href="<%=request.getContextPath()%>/RoomBackEnd?action=toUpdateRoom&roomId=${room.roomId}">修改</a></td>
	                </tr>
	                
	                <tr class="detailTitle" style="display: none; background-color: #7198AC;">
						<th scope="col">房間數量</th>
						<th scope="col">坪數</th>
						<th scope="col" colspan=2>房型設施</th>
						<th scope="col" colspan=2>房型介紹</th>
						<th scope="col">瀏覽次數</th>
	                </tr>
	                <tr class="detail" style="display: none; background-color: #F3F5F8;">
						<td>${room.roomAmount}間</td>
						<td>${room.roomArea}坪</td>
						<td colspan=2 style="width: 35%; text-align: left;">
							<c:if test="${room.breakfast == 1}">
			                	<span class="me-3"><i class="fa-solid fa-utensils"></i> 早餐</span>
			                </c:if>
			                <c:if test="${room.airCondotioner == 1}">
				                <span class="me-3"><i class="fa-regular fa-snowflake"></i> 空調</span>
			                </c:if>
			                <c:if test="${room.wifi == 1}">
				                <span class="me-3"><i class="fa-solid fa-wifi"></i> WIFI</span>
			                </c:if>
			                <c:if test="${room.television == 1}">
				                <span class="me-3"><i class="fa-solid fa-tv"></i> 電視</span>
			                </c:if>
			                <c:if test="${room.bathroom == 1}">
				                <span class="me-3"><i class="fa-solid fa-shower"></i> 私人衛浴</span>
			                </c:if>
			                <c:if test="${room.toiletries == 1}">
				                <span class="me-3"><i class="fa-solid fa-check"></i> 免費盥洗用品</span>
			                </c:if>
			                <c:if test="${room.safebox == 1}">
				                <span class="me-3"><i class="fa-solid fa-check"></i> 保險箱</span>
			                </c:if>
			                <c:if test="${room.fridge == 1}">
				                <span class="me-3"><i class="fa-solid fa-check"></i> 冰箱</span>
			                </c:if>
			                <c:if test="${room.waterBoiler == 1}">
				                <span class="me-3"><i class="fa-solid fa-check"></i> 電熱水壺</span>
			                </c:if>
						</td>
						<td colspan=2>${room.roomIntro}</td>
						<td>${room.roomView}次</td>
	                </tr>
	          	</c:forEach>
	        	</tbody>
	      	</table>
		</div>
	</div>
	
	<!-- loading -->
	<div id="preloader"></div>
    <lottie-player id="load" src="https://assets10.lottiefiles.com/packages/lf20_ztbbzkjc.json"  background="transparent"  speed="1"  style="width: 300px; height: 300px;" loop autoplay></lottie-player>
    
    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
    <!-- sweet alert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<!-- ===================== lottie-player ========================== -->
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
	<script>
        $(document).ready(function () {
        	//loading隱藏
        	$('#preloader').hide();
    	    $('#load').hide();
        	//明細toggle
            $('.toggle').click(function () {
                $(this).closest('.room').next('.detailTitle').toggle();
                $(this).closest('.room').next('.detailTitle').next('.detail').toggle();
            });
            //下架房型
            $(document).on('click', '.btn-outline-success', function(){
            	const btn = this;
				let rmId = $(this).parent().prev().prev().prev().text();
				$.ajax({
					beforeSend: function () {
                        $('#preloader').show();
                        $('#load').show();
                     },
	                url: '<%=request.getContextPath()%>/RoomBackEnd',
	                method: 'POST',
	                data: {
	                    action: 'offShow',
	                    roomId: rmId,
	                },
	                success:function(res){
	                	if(res === 'ok'){
	                		$('#preloader').hide();
	                	    $('#load').hide();
	                		swal({
	            	            title: "已下架房型",
	            	            icon: "success",
	            	            buttons: false,
	            	            timer: 1200
	            	         });
	                		btn.outerHTML = '<a class="btn btn-secondary">已下架</a>';
	                	}
	                },
	                error:function(err){console.log(err)},
	            });
			});
            //下架房型
            $(document).on('click', '.btn-secondary', function(){
            	const btn = this;
				let rmId = $(this).parent().prev().prev().prev().text();
				$.ajax({
					beforeSend: function () {
                        $('#preloader').show();
                        $('#load').show();
                     },
	                url: '<%=request.getContextPath()%>/RoomBackEnd',
	                method: 'POST',
	                data: {
	                    action: 'onShow',
	                    roomId: rmId,
	                },
	                success:function(res){
	                	if(res === 'ok'){
	                		$('#preloader').hide();
	                	    $('#load').hide();
	                		swal({
	            	            title: "已上架房型",
	            	            icon: "success",
	            	            buttons: false,
	            	            timer: 1200
	            	         });
	                		btn.outerHTML = '<a class="btn btn-outline-success">已上架</a>'
	                	}
	                },
	                error:function(err){console.log(err)},
	            });
			});
            
            <c:if test="${not empty addRoom}">
	            swal({
		            title: "房型新增成功",
		            icon: "success",
		            buttons: false,
		            timer: 1200
		         });
            </c:if>
            
            <c:if test="${not empty updateRoom}">
	            swal({
		            title: "房型修改成功",
		            icon: "success",
		            buttons: false,
		            timer: 1200
		         });
            </c:if>
            
            <c:if test="${not empty updateError}">
	            swal({
		            title: "${updateError}",
		            icon: "error",
		            buttons: false,
		            timer: 1200
		         });
            </c:if>
        });

    </script>
</body>

</html>