<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset='utf-8'>
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>newPassword</title>
<link
	href='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css'
	rel='stylesheet'>
<link
	href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.0.3/css/font-awesome.css'
	rel='stylesheet'>
<script type='text/javascript'
	src='https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js'></script>
<style>
	html {
    background-color:rgb(234, 234, 234);
}
.placeicon {
	font-family: "微軟正黑體";
}

.custom-control-label::before {
	background-color: #dee2e6;
	border: #dee2e6
}
</style>
</head>
<body oncontextmenu='return false' class='snippet-body bg-info'>
	<link rel="stylesheet"
		href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.1.1/bootstrap-social.css">
	<div style="background-color: rgb(234, 234, 234);">
		<!-- Container containing all contents -->
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-12 col-md-9 col-lg-7 col-xl-6 mt-5"style="position: relative;">
					<!-- White Container -->
					<div class="container bg-white rounded mt-2 mb-2 px-0"style="width:500px;position:absolute;top:80px">
						<!-- Main Heading -->
						<div class="row justify-content-center align-items-center pt-3">
							<label for=""><img src="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg" style="width:200px" alt=""></label>
						</div>
						<div class="pt-3 pb-3">
							<form class="form-horizontal" action="<%=request.getContextPath()%>/newPassword" method="POST">
								<!-- User Name Input -->
								<div class="form-group row justify-content-center px-3">
									<div class="col-9 px-0"style="position: relative;">
										<div style="position: absolute;top:10px;left:-15px"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
											class="bi bi-file-ppt-fill" viewBox="0 0 16 16">
											<path d="M8.188 8.5H7V5h1.188a1.75 1.75 0 1 1 0 3.5z" />
											<path
												d="M4 0h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm3 4a1 1 0 0 0-1 1v6.5a.5.5 0 0 0 1 0v-2h1.188a2.75 2.75 0 0 0 0-5.5H7z" />
										</svg></div>
										<div style="margin:0 0 0 10px"><input style="width: 373px;"type="text" name="password" placeholder="請輸入新密碼"
											class="form-control border-info placeicon"></div>
									</div>
								</div>
								<!-- Password Input -->
								<div class="form-group row justify-content-center px-3"style="position: relative;"><div style="display: inline-block;position: absolute;top:10px;left:63px"><svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor"
										class="bi bi-file-ppt-fill" viewBox="0 0 16 16">
										<path d="M8.188 8.5H7V5h1.188a1.75 1.75 0 1 1 0 3.5z" />
										<path
											d="M4 0h8a2 2 0 0 1 2 2v12a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V2a2 2 0 0 1 2-2zm3 4a1 1 0 0 0-1 1v6.5a.5.5 0 0 0 1 0v-2h1.188a2.75 2.75 0 0 0 0-5.5H7z" />
									</svg></div>
									<div class="col-9 px-0" style="display: inline-block;margin:0 0 0 20px">
										<input type="password" name="confPassword"
											placeholder="請再次輸入新密碼"
											class="form-control border-info placeicon">
									</div>
								</div>
								<div style="margin: 0 0 0 70px;"><p	class="form-text text-muted" style="position:relative;">請輸入新密碼登入</p><%
		  			if(request.getAttribute("statusfail")!=null)
		  			{
		  				out.print("<p class='text-danger ml-1'style='position: absolute;left:240px;top:215px'>"+request.getAttribute("statusfail")+"</p>");
		  			}
		  
		  %>
								</div>
								<!-- Log in Button -->
								<div class="form-group row justify-content-center">
									<div class="col-3 px-3 mt-3">
										<input type="submit" value="更新密碼"
											class="btn btn-block btn-info">
									</div>
								</div>
							</form>
						</div>
						<!-- Alternative Login -->
						<div class="mx-0 px-0 bg-light">
							
						
							<!-- Register Now -->
							<div class="pt-2">
								<div class="row justify-content-center">
									<h5>
										還沒有帳號嗎?<span><a href="#"
											class="text-danger"> 請加入我們!</a></span>
									</h5>
								</div>
								<div
									class="row justify-content-center align-items-center pt-4 pb-5">
									<div class="col-4">
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type='text/javascript'
		src='https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.bundle.min.js'></script>
	
</body>
</html>