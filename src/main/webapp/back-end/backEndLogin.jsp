<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>7Tour BackEndLogin </title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
    <link href="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
    <style>
        body {
            margin-top: 50px;
            background: #ddd;
            background-image: url(<%=request.getContextPath()%>/back-end/images/backendLogin.jpg);
            background-size: 100% 140%;
            background-repeat: no-repeat;
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

        .form-signin .form-signin-heading,
        .form-signin .checkbox {
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
            background:rgba(255, 255, 255, 0.3);
            -moz-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            -webkit-box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
        
        }
        .account-wall #title{
            font-family:微軟正黑體;
	        font-size: 26px;
	        text-align: center;
        }
        .login-title {
            color: #555;
            font-size: 18px;
            font-weight: 400;
            display: block;
        }

        .profile-img {
            width: 300px;
          height:100px;
            margin: 0px auto 10px;
            display: block;
        }

        .need-help {
            margin-top: 10px;
        }

        .new-account {
            display: block;
            margin-top: 10px;
        }
        .text-center {
			color: red;
	    }
    </style>
</head>

<body>

    <div class="container bootstrap snippets bootdey">
        <div class="row">
            <div class="col-sm-6 col-md-4 col-md-offset-4" style="margin-bottom: 15px;">
                
                <div class="account-wall">
                    <img class="profile-img" src="<%=request.getContextPath()%>/back-end/images/bgremove_Logo.jpg" alt=""><p id="title">7Tour員工登入系統</p>
                    <div style="color:red; text-align: center; font-weight: bold;">${errorMsg}</div>
                    <form class="form-signin"  METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet">
						<input type="text" class="form-control" placeholder="請輸入員工編號" required autofocus name="empNo" value="${empNo}">                        <input type="password" class="form-control" placeholder="請輸入密碼" required name="empPw">
                        <input type="hidden" name="action" value="backEndlogin">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">
                            登入</button>
                        <label class="checkbox pull-left">
                            <input type="checkbox" value="remember-me">
                            Remember me
                        </label>
                        <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                    </form>
                </div>
                <a href="#" class="text-center new-account" style="font-weight: bold;">建立新員工帳號 </a>
            </div>
        </div>
    </div>

</body>

</html>