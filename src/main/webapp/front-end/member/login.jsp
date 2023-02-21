<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.Users.model.*"%>
<% 
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
if(usersVO!=null){
Byte UserStatus=usersVO.getUserStatus();
if(UserStatus==1)
	request.getSession().invalidate();
response.sendRedirect(request.getContextPath() + "/front-end/member/login.jsp");
}
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<title>7Tour Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
<link
	href="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
</head>

<body>

	<div class="container bootstrap snippets bootdey">
		<div class="row">
			<c:if test="${not empty errorMsgs}">
				<ul id="errorMes" style="display: inline-block">
					<c:forEach var="message" items="${errorMsgs}">
						<h3>${message}</h3>
						<p>
							您的個人活動已違反了我們的<u>使用條例</u>
						</p>
						<p>
							如有任何問題，請來信聯絡服務人員</u>
						</p>
					</c:forEach>
				</ul>
			</c:if>
			<c:if test="${not empty statusSuccess}">
				<p class='text-danger ml-1'style='position: absolute;left:330px;top:275px;border:3px solid red;width:220px;height:150px;border-radius:10px;font-size:30px'>${statusSuccess}</p>
			</c:if>
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<h1 class="text-center login-title">
					想尋找更多嗎? <a href="<%=request.getContextPath()%>/front-end/index.jsp"
						target="__blank">7Tour</a>
				</h1>
				<div class="account-wall">
					<img class="profile-img"
						src="<%=request.getContextPath()%>/front-end/member/images/Tour(circle).jpg"
						alt="">
					<form id="loginform" class="form-signin" METHOD="post"
						ACTION="<%=request.getContextPath()%>/usersServlet">
						<input type="text" class="form-control" placeholder="請輸入電子信箱"
							required autofocus name="userAccount"> <input
							type="password" class="form-control" placeholder="請輸入密碼" required
							name="password"> <input type="hidden" name="action"
							value="login">
						<button class="btn btn-lg btn-primary btn-block" type="button"
							id="sendBotton">Sign in</button>
						<label class="checkbox pull-left"> <input type="checkbox"
							value="remember-me"> Remember me
						</label> <a
							href="<%=request.getContextPath()%>/front-end/member/addMember.jsp"
							class="pull-right need-help">還不是會員嗎? 立即註冊 7Tour 帳號 </a><span
							class="clearfix"></span>
					</form>
				</div>
				<a href="forgotPassword.jsp" class="text-center new-account">忘記密碼? </a>
			</div>
		</div>
	</div>
	<input type="hidden" id="checksend" value="1">

	<style type="text/css">
body {
	margin-top: 20px;
	background: #ddd;
	background-image: url(<%=request.getContextPath()%>/front-end/member/images/login.jpeg);
	background-size: 100%;
	background-repeat: no-repeat;
}

#errorMes {
	position: absolute;
	right: 200px;
	top: 275px;
	color: red;
	font-size: 18px;
	list-style-type: none;
	border: 3px solid red;
	border-radius: 10px;
	padding: 10px;
}

body .text-center>a {
	font-size: 48px;
	color: purple;
}

body .text-center>a:hover {
	color: rgba(236, 126, 9, 0.945);
	text-decoration: none;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	font-size: 16px;
	height: auto;
	padding: 10px;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="text"] {
	margin-bottom: -1px;
	border-bottom-left-radius: 0;
	border-bottom-right-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.account-wall {
	/* 登入邊框 */
	margin-top: 20px;
	padding: 40px 0px 20px 0px;
	background-color: #f7f7f7;
	-moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	-webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	border-radius: 10px;
}

.login-title {
	color: #555;
	font-size: 18px;
	font-weight: 400;
	display: block;
}

.profile-img {
	width: 96px;
	height: 96px;
	margin: 0 auto 10px;
	display: block;
	-moz-border-radius: 50%;
	-webkit-border-radius: 50%;
	border-radius: 50%;
}

.need-help {
	margin: 15px 40px 0px 0px;
	color: #0CE9EB;
}

.new-account {
	display: block;
	margin-top: 10px;
	color: red;
}
</style>

	<script type="text/javascript">
        let sendBotton=document.getElementById("sendBotton").addEventListener("click",function(){
           if(document.getElementById("checksend").value!="1"){
            return false;
        } else{
            let random =Math.floor(Math.random()*10)+1;
            if(random<2){
                document.getElementById("checksend").value="0";
            openUrlInNewWindow("loading.html", 515, 705, true).then(() => {
            });}
            if(document.getElementById("checksend").value!="0"){
         document.getElementById("loginform").submit();
         }
        }

        });
function openUrlInNewWindow(url, width, height, mask) {
            let maskLayer = null;
            if (mask) {
                maskLayer = document.createElement('div');
                maskLayer.style.position = 'absolute';
                maskLayer.style.zIndex = 65535;
                maskLayer.style.opacity = 0.7;
                maskLayer.style.top = 0;
                maskLayer.style.right = 0;
                maskLayer.style.width = '100vw';
                maskLayer.style.height = '100vh';
                maskLayer.style.backgroundColor = 'black';
                document.body.appendChild(maskLayer);
            }
            let promise = new Promise((resolve, reject) => {
                let w = Math.round(width || window.innerWidth * 0.99);
                let h = Math.round(height || window.innerHeight * 0.99);
                let newWin = window.open(url, '_blank', 'width=' + w + ',height=' + h + ',top=100,left=500,status=no,titlebar=no,toolbar=no,directories=no,location=no,menubar=no');
                let hnd = setInterval(function () {
                    try {
                        if (newWin.closed) {
                            clearInterval(hnd);
                            resolve();
                            maskLayer && maskLayer.remove();
                        }
                    }
                    catch {
                        clearInterval(hnd);
                        reject();
                        maskLayer && maskLayer.remove();
                    }
                }, 100);
            });
            return promise;
        }
    </script>
</body>

</html>