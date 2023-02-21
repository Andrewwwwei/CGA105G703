<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增房型</title>
	<!-- ===================== Bootstrap 5 CSS ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
     <!-- ===================== fontawesome ========================== -->
    <script src="https://kit.fontawesome.com/37ac1c7b87.js" crossorigin="anonymous"></script>
	<style>
		.main-content{
			margin-left: 230px;
		}
		.card {
			background-color: #f7f6f2;
			margin: 25px auto;
			position: relative;
		}
		.imgs {
			background-color: #fff;
		}
		.col-form-label {
			vertical-align:middle;
			color: #30504F;
			text-align: right;
		}
		input[type=checkbox] {
            width: 18px;
            height: 18px;
        }
		#errorMsgs{
			position: absolute;
			right: 180px;
			top:450px;
			z-index: 2;
		}
		li{
			font-size: 14px;
		}
		.btn-primary {
		    padding: 0 20px;
		    border-radius: 2px 2px 2px 2px;
		    -moz-border-radius: 2px 2px 2px 2px;
		    -webkit-border-radius: 12px;
		    border: none;
		    display: inline-block;
		    line-height: 42px;
		    color: #fff;
		    background-color: #7199AC;
		}
		.btn-primary:hover{
			color:black;
			background-color: #7199AC;
		}
		.btn:hover{
			filter: drop-shadow(2px 2px 2px #060C3C);
		}
		.btn-secondary1 {
		    color: #fff;
		    padding: 2px 20px;
		    border-radius: 3px 3px 3px 3px;
		    -moz-border-radius: 3px 3px 3px 3px;
		    -webkit-border-radius: 12px;
		    height: 48px;
		    line-height: 39px;
		    display: inline-block;
		    position: absolute;
		    left: 5px;
		    top: 5px;
		    z-index: 2;
		    background-color: #7199AC;
		}	
			
	</style>
		
</head>

<body>
	<c:import url="/back-end/sidebar.jsp" ></c:import>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
	<div id="errorMsgs">
		<div class="text-danger">請修正以下錯誤：</div>
		<ul type="circle" class="ps-3">
			<c:forEach var="message" items="${errorMsgs}">
				<li class="text-danger">${message}</li>
			</c:forEach>
		</ul>
	</div>
	</c:if>
	<div class="main-content">
		<div class="card col-xl-9">
			<a class="btn btn-secondary1 light" href="<%=request.getContextPath()%>/RoomBackEnd?action=goThisVenRoomType&venId=${venId}"><i class="fa-solid fa-arrow-left-long"></i>&emsp;回房型列表</a>
			<form method="post" action="<%=request.getContextPath()%>/RoomBackEnd" >
				<div class="card-body d-flex justify-content-center">
					<div class="col-xl-8">
						<div class="row mb-2">
						    <label class="col-sm-3 col-form-label">廠商名稱</label>
						    <div class="col-sm-8 my-auto text-center">${venName}</div>
					    </div>
						<div class="row mb-2">
						    <label for="roomName" class="col-sm-3 col-form-label">房型名稱</label>
						    <div class="col-sm-8">
						    	<input type="text" name="roomName" maxlength="20" class="form-control" id="roomName" value="${param.roomName}">
					    	</div>
						</div>
						<div class="row mb-2">
					    	<label for="people" class="col-sm-3 col-form-label">可住人數</label>
						    <div class="col-sm-8">
						    	 <select class="form-select" name="people" id="people">
			                        <option value="1">1人</option>
			                        <option value="2">2人</option>
			                        <option value="3">3人</option>
			                        <option value="4">4人</option>
			                        <option value="5">5人</option>
			                        <option value="6">6人</option>
			                        <option value="7">7人</option>
			                        <option value="8">8人</option>
			                        <option value="9">9人</option>
			                        <option value="10">10人</option>
			                    </select>
						    </div>
						</div>
						<div class="row mb-2">
						    <label for="roomAmount" class="col-sm-3 col-form-label">房間數量</label>
						    <div class="col-sm-8">
						    	<input type="text" name="roomAmount" class="form-control" id="roomAmount" value="${param.roomAmount}">
						    </div>
						</div>
					
						<div class="row mb-2">
						    <label for="price" class="col-sm-3 col-form-label">價格</label>
						    <div class="col-sm-8">
						    	<input type="text" name="price" class="form-control" id="price" value="${param.price}">
						    </div>
						</div>
						<div class="row mb-2">
						    <label for="roomArea" class="col-sm-3 col-form-label">坪數</label>
						    <div class="col-sm-8">
						    	<input type="text" name="roomArea" class="form-control" id="roomArea" value="${param.roomArea}">
						    </div>
						</div>
						<div class="row mb-2">
						    <label for="roomIntro" class="col-sm-3 col-form-label">房型介紹</label>
						    <div class="col-sm-8">
						    	<textarea name="roomIntro" rows="3" class="form-control" id="roomIntro" maxlength="500"></textarea>
						    </div>
						</div>
						<div class="row mb-2">
						    <label for="" class="col-sm-3 col-form-label py-0">房型設施</label>
						    <div class="col-sm-8">
								<label class="me-3 mb-2"><input type="checkbox" name="facilities" value="breakfast">早餐</label>
								<label class="me-3"><input type="checkbox" name="facilities" value="airCondotioner">空調</label>
								<label class="me-3"><input type="checkbox" name="facilities" value="wifi">WIFI</label>
								<label class="me-3"><input type="checkbox" name="facilities" value="television">電視</label>
								<label class="me-3 mb-2"><input type="checkbox" name="facilities" value="safebox">保險箱</label>
								<label class="me-3"><input type="checkbox" name="facilities" value="fridge">冰箱</label>
								<label class="me-3"><input type="checkbox" name="facilities" value="waterBoiler">電熱水壺</label>
								<label class="me-3 mb-2"><input type="checkbox" name="facilities" value="bathroom">私人衛浴</label>
								<label class="me-3"><input type="checkbox" name="facilities" value="toiletries">免費盥洗用品衛浴</label>
							</div>
						</div>
						<div class="row mb-2 d-flex justify-content-center">
							<input type="hidden" name="action" value="insertRoom">
							<input type="hidden" name="venId" value="${venId}">
							<button class="btn btn-primary col-lg-3" type="submit">新增</button>
		               	</div>
				</div>
            </div>
			</form>
		</div>
	</div>
	<!-- ====================== Bootstrap 5 JS =========================== -->
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
	<!-- ===================== jquery ========================== -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
	<script>
	 //表單記憶
		let people = "<c:out value="${param.people}"/>";
	    switch (people) {
	        case '1':
	            $('#people').val(1);
	            break;
	        case '2':
	            $('#people').val(2);
	            break;
	        case '3':
	            $('#people').val(3);
	            break;
	        case '4':
	            $('#people').val(4);
	            break;
	        case '4':
	            $('#people').val(4);
	            break;
	        case '4':
	            $('#people').val(4);
	            break;
	        case '5':
	            $('#people').val(5);
	            break;
	        case '6':
	            $('#people').val(6);
	            break;
	            break;
	        case '7':
	            $('#people').val(7);
	            break;
	            break;
	        case '8':
	            $('#people').val(8);
	            break;
	            break;
	        case '9':
	            $('#people').val(9);
	            break;
	        case '10':
	            $('#people').val(10);
	            break;
	    }
	    <c:if test="${not empty param.roomIntro}">
	    	$('#roomIntro').text('${param.roomIntro}');
	    </c:if>
	    <c:if test="${not empty facilities}">
	    	let facilityArr = [];
	    	<c:forEach var="facility" items="${facilities}">
	    		facilityArr.push('${facility}');
	    	</c:forEach>
	    		for(let name of facilityArr){
		    		switch (name) {
		    			case 'breakfast':
			    			$('[value="breakfast"]').prop("checked", true);
				            break;
		    			case 'airCondotioner':
			    			$('[value="airCondotioner"]').prop("checked", true);
				            break;
		    			case 'wifi':
			    			$('[value="wifi"]').prop("checked", true);
				            break;
		    			case 'television':
			    			$('[value="television"]').prop("checked", true);
				            break;
		    			case 'safebox':
			    			$('[value="safebox"]').prop("checked", true);
				            break;
		    			case 'fridge':
			    			$('[value="fridge"]').prop("checked", true);
				            break;
		    			case 'waterBoiler':
			    			$('[value="waterBoiler"]').prop("checked", true);
				            break;
		    			case 'bathroom':
			    			$('[value="bathroom"]').prop("checked", true);
				            break;
		    			case 'toiletries':
			    			$('[value="toiletries"]').prop("checked", true);
				            break;
		    		}
	    		}
	    </c:if>
	</script>
</body>
</html>