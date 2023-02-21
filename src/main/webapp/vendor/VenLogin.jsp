<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>廠商登入</title>
    <!-- ===================== Bootstrap 5 ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
        
    <style>
		div.login {
			display: flex;
			flex-direction: column;
			align-items: center;
		}
		
		input{
		   text-align:center;
		}
		
		div.login button{
			background-color: transparent;
			margin-top:15px;
			font-size:15px;
		}
		
		div.login button:hover{
			background-color: #041427;
			color:white;
		}
	 </style>
</head>
<body>
    <c:import url="/vendor/company_header.jsp" ></c:import>
    <div class="login">
    	<h4 class="text-center mt-5 fw-bold">廠商登入系統</h4>
        <img src="<%=request.getContextPath()%>/vendor/images/Head.png" width="10%" class="my-3">
        <form method="post" action="<%=request.getContextPath()%>/Vendor" autocomplete="off">
            <input type="text" name="venId" style=" border-radius: 10px;" placeholder="請輸入廠商編號" class="my-2"><br>
            <input type="password" name="password" style=" border-radius: 10px;" placeholder="請輸入密碼" class="my-2"><br>
            <input type="hidden" name="action" value="vendorLogin">
            <div class="text-center">
            <button type="submit" style="border-radius: 10px;">登入</button>
            </div>   
        </form>
        <div class="mt-2 text-center text-danger fw-bold">${errorMsgs}</div>
    </div>
    <c:import url="/vendor/company_footer.jsp" ></c:import>   
</body>
</html>