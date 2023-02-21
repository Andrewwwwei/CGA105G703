<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<!------ Include the above in your HEAD tag ---------->

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

<style type="text/css">
.form-gap {
    padding-top: 70px;
}
</style>
</head>

<body>
	<div class="form-gap"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-body">
					<label><img src="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg" alt=""style="width:200px;margin:0 0 0 68px"></label>
						<div class="text-center">
							<h1>
								找回密碼系統
							</h1>
							<h2 class="text-center">請輸入驗證碼</h2>
									<%
		  			if(request.getAttribute("message")!=null)
		  			{
		  				out.print("<p class='text-danger ml-1'>"+request.getAttribute("message")+"</p>");
		  			}
		  
		  %>
	
							<div class="panel-body">

								<form id="register-form" action="<%=request.getContextPath()%>/ValidateOtp" role="form" autocomplete="off"
									class="form" method="post"name="registerForm">

									<div class="form-group">
										<div class="input-group">
											
												<input class="input"type="text" required="required" maxlength="1"></input>
												<input class="input"type="text" required="required" maxlength="1"></input>
												<input class="input"type="text" required="required" maxlength="1"></input>
												<input class="input"type="text" required="required" maxlength="1"></input>
												<input class="input"type="text" required="required" maxlength="1"></input>
												<input	id="opt" name="otp" class="form-control" type="hidden" >
										</div>
									</div>
									<div class="form-group">
										<input id="sendOTP"name="recover-submit"
											class="btn btn-lg btn-primary btn-block"
											value="重置密碼" type="button">
									</div>

									<input type="hidden" class="hide" name="token" id="token"
										value="">
								</form>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<style>
body {
    font-family: "微軟正黑體";
    background-color: #eee;
}
.input{
text-align:center;
font-size:30px;
width:50px;
height:50px;
margin:0 0px 0 8.5px;
}
</style>
<script>
let total="";
let input=document.getElementsByClassName("input");
document.getElementById('sendOTP').addEventListener("click", function () {
    for(let i=0;i<input.length;i++){
        total=total+document.getElementsByClassName("input")[i].value;
	}document.getElementById("opt").value=total;
	document.registerForm.submit();
    });
</script>
</html>