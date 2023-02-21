<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.Users.model.*"%>
<%
  UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); 
%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html">
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
<title>Document</title>
</head>

<body>
	<form id="form" method="post"
		action="<%=request.getContextPath()%>/usersServlet"
		ENCTYPE="multipart/form-data" name="updatePassword">
		<img
			src="<%=request.getContextPath()%>/front-end/member/images/Logo.jpg"
			alt="" id="image">
		<p id="title">修改會員密碼</p>
		<input  value="<%=usersVO.getUserAccount()%>" name="userAccount" type="hidden"></input>
		<p id="userAccount" value="<%=usersVO.getUserAccount()%>" name="userAccount">會員帳號：<%=usersVO.getUserAccount()%></p>
		<div id="original_border">
			請輸入原本密碼：<input id="original" type="password" maxlength="15" name="originalUserPassword"></input>
		</div>
		<div id="newPassword_border">
			請輸入新密碼：<input id="newPassword" type="password" maxlength="15"name="newPassword"></input>
		</div>
		<div id="againNewPassword_border">
			請再輸入一次密碼：<input id="againNewPassword" type="password" maxlength="15"name="againNewPassword"></input>
		</div>
		
	<div id="commit_border" for="commit">
				<input type="button" value="確認" id="commit" onClick="check()">
			</div>
			 <input type="hidden" name="action" value="updatePassword">
	</form>
</body>
<script>

    //================================================================密碼格式
    function Userpassword() {
    	let pass = document.getElementById("original");
        if (pass.value == "") {
        	Swal.fire("原密碼必須由6~15字元的英文加數字組成");
            pass.focus();
            return false;
        }
        if (pass.value.length < 6 || original.value.length > 15) {
        	Swal.fire("原密碼必須由6~15字元的英文加數字組成");
            pass.focus();
            return false;
        }
        var score_i = 0, score_c = 0;
        var l_str = unescape('%27') + unescape('%22') + unescape('%2A') + unescape('%20');
        //%27=', %22=", %2A=*, %20= (空白)
        //escape函數：字串編碼(傳回以ISO Latin字元集來表示參數的十六進制編碼)
        return true;
    }
    function checkUserpassword() {
    	let pass = document.getElementById("newPassword");
        if (pass.value == "") {
        	Swal.fire("新密碼必須由6~15字元的英文加數字組成");
            pass.focus();
            return false;
        }
        if (pass.value.length < 6 || newPassword.value.length > 15) {
        	Swal.fire("新密碼必須由6~15字元的英文加數字組成");
            pass.focus();
            return false;
        }
        var score_i = 0, score_c = 0;
        var l_str = unescape('%27') + unescape('%22') + unescape('%2A') + unescape('%20');
        //%27=', %22=", %2A=*, %20= (空白)
        //escape函數：字串編碼(傳回以ISO Latin字元集來表示參數的十六進制編碼)
        return true;
    }
    //================================================================再次輸入密碼格式
    function checkUserpasswordAgain(item) {
    	pass = document.getElementById("againNewPassword");
        if (pass.value == "") {
        	Swal.fire("密碼必須和上一欄數值一樣");
           	pass.focus();
            return false;
        }
        if (pass.value != document.getElementById("newPassword").value) {
        	Swal.fire("密碼必須和上一欄數值一樣");
        	pass.focus();
            return false;
        }
        return true;
    }
    function check() {
    	if (!Userpassword(document.updatePassword.original)) return false;
        if (!checkUserpassword(document.updatePassword.newPassword)) return false;
        if (!checkUserpasswordAgain(document.updatePassword.againNewPassword)) return false;
         document.updatePassword.submit(); 
    }
 
</script>
<style>
#form {
background: -webkit-linear-gradient(right, rgb(193, 109, 163),
		rgba(7, 42, 244, 0.397));
	width: 400px;
	height: 500px;
	border: 3px solid rgb(19, 32, 151);
	position: relative;
}
#form #userAccount{
	font-size: 22px;
	color:red;
}
#form #image {
	width: 100px;
	margin: 0 0 0 20px;
}

#form #title {
	position: absolute;
	top: -25px;
	left: 160px;
	font-size: 26px;
}

#form #original_border {
margin:50px 0 50px 0;
	font-size: 30px;
}

#form #newPassword_border {
margin:50px 0 50px 0;
	font-size: 30px;
}

#form #againNewPassword_border {
margin:50px 0 50px 0;
	font-size: 30px;
}

#form #original_border #original {
	width: 100px;
	height: 30px;
	border-radius: 10px;
}

#form #newPassword_border #newPassword {
	width: 100px;
	height: 30px;
	border-radius: 10px;
}

#form #againNewPassword_border #againNewPassword {
	width: 100px;
	height: 30px;
	border-radius: 10px;
}
#form #commit_border {
	position: absolute;
	border-radius: 10px;
	buttom: 50px;
	right: 10px;
	width: 80px;
	height: 50px;
}

#form #commit_border #commit {
	border-radius: 10px;
	width: 60px;
	height: 50px;
	border: 1px solid rgb(22, 22, 23);
}
</style>

</html>