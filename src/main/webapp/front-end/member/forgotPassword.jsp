<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset='utf-8'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Snippet - BBBootstrap</title>
<link href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'rel='stylesheet'>
<link href='' rel='stylesheet'>
<script type='text/javascript'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<style>
body {
	background-position: center;
	background-color: #eee;
	background-repeat: no-repeat;
	background-size: cover;
	color: #505050;
	font-family: "微軟正黑體";
	font-size: 14px;
	font-weight: normal;
	line-height: 3.5;
	text-transform: none
}

.padding-bottom-3x {
	
	padding-bottom: 72px !important;
	width:800px;
	
}

.card-footer {
	
	background-color: #fff
}

.btn {
	font-size: 13px
}

.form-control:focus {
	color: #495057;
	background-color: #fff;
	border-color: #76b7e9;
	outline: 0;
	box-shadow: 0 0 0 0px #28a745
}
.change {
    color: rgb(70, 91, 212);
    box-shadow: 0px 0px 2px 2px rgb(70, 91, 212);
}
</style>
</head>
<body oncontextmenu='return false' class='snippet-body'>
	<div class="container padding-bottom-3x mb-2 mt-5">
		<div class="row justify-content-center"style="position: relative;">
			<div class="col-lg-8 col-md-10" style="position: absolute;top:80px">
				
				<form class="card mt-4" action="<%=request.getContextPath()%>/forgotPassword" method="POST">
					<div class="card-body"style="border-radius: 10px;">
						<div class="form-group" >
							<label><img src="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg" alt=""style="width:200px"></label> 
							<label id="userAccount_border" for="userAccount" style="width:327px;height:37px;padding:0;margin: 0;border-radius: 5px;position:relative;background: #d2d3d379;"><svg style="display: inline-block;"xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
								class="bi bi-envelope-fill" viewBox="0 0 16 16">
								<path
									d="M.05 3.555A2 2 0 0 1 2 2h12a2 2 0 0 1 1.95 1.555L8 8.414.05 3.555ZM0 4.697v7.104l5.803-3.558L0 4.697ZM6.761 8.83l-6.57 4.027A2 2 0 0 0 2 14h12a2 2 0 0 0 1.808-1.144l-6.57-4.027L8 9.586l-1.239-.757Zm3.436-.586L16 11.801V4.697l-5.803 3.546Z" />
							</svg>  <input	style="width:300px;display: inline-block;position: absolute;left:25px;border: none;background: transparent;" class="form-control" type="text" name="email" id="userAccount" placeholder="請輸入電子信箱"required=""autofocus>
								
						</label>
							<h5 style="color:rgb(40, 179, 36)">忘記密碼?</h5>
								<p	class="form-text text-muted">如果變更密碼，請輸入之前設定的電子信箱帳號。</p>
								<p	class="form-text text-muted">系統將會寄一封驗證信至您的信箱裡</p>
						</div>
					</div>
					<div class="card-footer"style="border-radius: 10px;">
						<button class="btn btn-success" type="submit"style="background-color:blue;border-radius: 10px;">獲得新密碼</button>
						<button id="returnLogin"class="btn btn-danger" type="button"style="background-color:purple;border-radius: 10px;">回到登入</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type='text/javascript'
		src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
	<script type='text/javascript' src=''></script>
	<script type='text/javascript' src=''></script>
	<script type='text/Javascript'></script>
	<script>//==============================================================外框發亮
    //=============================信箱
    let userAccount_border = document.querySelector("#userAccount_border");//div外框
    document.addEventListener("click", function (e) {
        if (e.target.id == "userAccount") {//input

            userAccount_border.className = "change";//前面宣告變數名，後面特效css
        } else {
            userAccount_border.className = "userAccount_border";//div外框
        }
    });
    
    let returnLogin=document.getElementById("returnLogin").addEventListener("click", function () 
    		{
    	window.location.assign("<%=request.getContextPath()%>/front-end/member/login.jsp");
    	});
    </script>
</body>
</html>