<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>新增/查看 房型照片</title>
	<!-- ===================== Bootstrap 5 CSS ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- ===================== fontawesome ========================== -->
    <script src="https://kit.fontawesome.com/37ac1c7b87.js" crossorigin="anonymous"></script>
	<style>
		.main-content{
			margin-left: 90px;
		}
		.card {
			background-color: #f7f6f2;
		}
		.upload-pic{
			width: 95%;
		}
		.imgs {
			background-color: #fff;
		}
		.col-form-label {
			font-size: 18px;
			color: #30504F;
			text-align: right;
			padding-top: 13px;
		}
		ul {
			display: inline-block;
		}
		.mainTitle {
 			font-weight: 600; 
			color: #30504F;
  			padding: 5px; 
		}
		.subtitle {
			color: #996A4D;
			padding-bottom: 10px;
		}
		.form-select {
			color: #30504F;
			font-size: 16px;
 			height: 50px;  
		}
		textarea.form-control {
			height: auto;
			font-size: 16px;
		}
		.img-container {
			width: 45%;
			position: relative;
			margin: 0 auto;
			display: inline-block;
		}
		img {
			transition: 0.5s;
			max-width: 100%;
		}
		.delete{
		    transition: 0.5s;
		    opacity: 0;
		    visibility: hidden;
		    position: absolute;
		    bottom: 0;
		    width: 100%;
		    text-align: center;
		}
		.img-container:hover .delete {
			bottom: 30px;
			opacity: 1;
			visibility: visible;
			cursor: pointer;
		}
		.img-container:hover img {
			transition: 0.5s;
			opacity: 0.6;
		}
		.img-preview {
			width: 45%;
			position: relative;
			margin: 0 auto;
		}
		.img-preview:hover .delete2 {
			bottom: 30px;
			opacity: 1;
			visibility: visible;
			cursor: pointer;
		}
		.img-preview:hover img {
			transition: 0.5s;
			opacity: 0.6;
		}
		.delete2{
		    transition: 0.5s;
		    opacity: 0;
		    visibility: hidden;
		    position: absolute;
		    bottom: 0;
		    left:28%;
		    width: 100%;
		    text-align: center;
		    display:block;
		    z-index: 5;
		}
		.btn-delete {
			background-color: #060C3C;
			padding: 10px;
			border-radius: 30px;
		}
 		.btn-delete:hover img {
 			transition: 0.5s;
 		} 
 		.btn:hover {
		    color: #FFF;
		}
		.preview div {
			display: inline-block;
			width: 45%;
			margin: 5px;
		}
		.main-content {
		    display: block;
		    width: 100%;
		    overflow-x: auto;
		    padding: 6px 89px 45px 160px;
		    -webkit-overflow-scrolling: touch;
        }
       .card {
			background-color: #f7f6f2;
			margin: 45px auto;
		}
		.imgs {
			background-color: #fff;
		}
		.col-form-label {
			font-size: 20px;
			color: #30504F;
			text-align: right;
			padding-top: 13px;
		}
		ul {
			display: inline-block;
		}
		textarea.form-control {
			height: auto;
			font-size: 16px;
		}
		.btn-primary1 {
		    background-color: #060D3C;
		    padding: 0 10px;
		    border-radius: 2px 2px 2px 2px;
		    -moz-border-radius: 2px 2px 2px 2px;
		    -webkit-border-radius: 12px;
		    border: none;
		    display: inline-block;
		    line-height: 42px;
		    color: #fff;
		    margin-top: 10px;
		    width: 100px;
		}
		.btn-secondary1 {
		    color: #fff;
		    padding: 2px 20px;
		    margin: 60px 60px 0px 60px;
		    border-radius: 3px 3px 3px 3px;
		    -moz-border-radius: 3px 3px 3px 3px;
		    -webkit-border-radius: 12px;
		    height: 48px;
		    line-height: 39px;
		    display: inline-block;
		    margin-right: 17px;
		    background-color: #060D3C;
		}
		.btn:hover{
			filter: drop-shadow(2px 2px 2px gray);
		}
		.swal-title {
		  margin-bottom: 28px;
		}
	</style>
</head>

<body>
	<c:import url="/back-end/sidebar.jsp" ></c:import>

	<div class="main-content">
		<a class="btn btn-secondary1 light" href="<%=request.getContextPath()%>/RoomBackEnd?action=goThisVenRoomType&venId=${venId}"><i class="fa-solid fa-arrow-rotate-left"></i>&emsp;回房型列表</a>
			<div class="card col-xl-11">
				<h5 class="col-xl-12 fw-bold text-center" style="margin: 10px auto;">${venName}&emsp;${roomName}</h5>
				<div class="card-body d-flex justify-content-around">
					<div class="col-xl-6">
						<h5 class="title d-flex justify-content-center mainTitle">照片列表</h5>
							<c:if test="${not empty photoIdList}">
								<h5 class="title d-flex justify-content-center subtitle">共${photoIdList.size()}張照片</h5>
							</c:if>
							<c:if test="${empty photoIdList}">
								<h5 class="title d-flex justify-content-center subtitle">還沒有照片喔</h5>
							</c:if>
						<c:forEach var="photoId" items="${photoIdList}">
							<div class="mb-3 img-container">
								<img src="<%=request.getContextPath()%>/RoomPhoto?action=showImagesByPicId&roomPhotoId=${photoId}">
								<div class="delete">
									<a class="btn-delete" href="<%=request.getContextPath()%>/RoomPhoto?action=deleteRoomPic&roomPhotoId=${photoId}">
										<i class="fa-solid fa-trash-can fs-5 text-white"></i>
									</a>
								</div>
							</div>
						</c:forEach>
					</div>
	
					<div class="col-xl-6 imgs">
						<h5 class="title d-flex justify-content-center mainTitle">新增照片</h5>
						<form method="post" action="<%=request.getContextPath()%>/RoomPhoto" enctype="multipart/form-data">
							<div class="pic-upload">
								<input type="file" class="form-control upload-pic m-2 pe-0" id="formFile" name="imgFile" accept="image/*" multiple>
							</div>
							<div class="preview"></div>
							
							<div class="mb-3 d-flex justify-content-center align-items-center">
								<input type="hidden" name="roomId" value="${roomId}">
								<input type="hidden" name="action" value="insertRoomPic"> 
 								<button type="submit" class="btn btn-primary1 col-lg-4">新增</button>	
							</div>
						</form>
					</div>
					
				</div>
			</div>
	</div>
	 <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
    <!-- jquery -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <!-- sweet alert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		$(document).ready(function() {
		///////////上傳圖片預覽
           let upload = document.querySelector('#formFile');
           upload.addEventListener('change', function(){
            	const fileArr = this.files;
            	let preview = $('.preview')[0];
            	preview.innerHTML = '';
            	for(let file of fileArr){
            		if (file.type.indexOf('image') >= 0) {
            			preview.innerHTML += '<div class="img-preview"><img src="' + URL.createObjectURL(file) + '" class="previewImg"><div class="delete2"><a class="btn-delete trash"><i class="fa-solid fa-trash-can fs-5 text-white"></i></a></div></div>';
            		}else{
            			preview.innerHTML = '<span class="text-danger">僅可上傳圖片檔案</span>';
            			break;
            		}
            	}
       /////////刪除預覽圖片
            	$('.trash').click(function(){
            		let input = document.querySelector('#formFile');
            		let index = $('.trash').toArray().indexOf(this);
            		const dt = new DataTransfer();
            		for (let i = 0; i < input.files.length; i++) {
                        const file = input.files[i];
                        if (index !== i)
                      	dt.items.add(file); 
                    }
            		input.files = dt.files;
            		$(this).parent().parent().remove();
            	});
            }) 
		});
		//上傳失敗
		<c:if test="${not empty errorMsg}">
			swal({
	            title: "${errorMsg}",
	            icon: "error",
	            buttons: false,
	            timer: 1500
	         });
		</c:if>
		//上傳成功
		<c:if test="${not empty upload}">
			swal({
	            title: "圖片上傳成功！",
	            icon: "success",
	            buttons: false,
	            timer: 1500
	         });
		</c:if>
		//刪除成功
		<c:if test="${not empty delete}">
			swal({
	            title: "圖片已刪除",
	            icon: "success",
	            buttons: false,
	            timer: 1500
	         });
		</c:if>
	</script>
</body>
</html>