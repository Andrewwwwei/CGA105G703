<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>


<%
  UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); 
byte UserShopLevel=usersVO.getUserShopLevel();
String ShopLevel="";
if(UserShopLevel==1){
	ShopLevel="VIP";
}
if(UserShopLevel==0){
	ShopLevel="一般";
}
byte userForumLevel=usersVO.getUserForumLevel();
String ForumLevel="";
if(UserShopLevel==1){
	ForumLevel="達人";
}
if(UserShopLevel==0){
	ForumLevel="一般";
}
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
    <form id="form" method="post" action="<%=request.getContextPath()%>/usersServlet"  ENCTYPE="multipart/form-data" name="userInfo">
        <input type="hidden" name="action" value="userInfo">
        <img src="<%=request.getContextPath()%>/front-end/member/images/Logo.jpg" alt="" id="image">
        <p id="title">會員基本資料</p>
        <span><img src="getUserPic.jsp?UserAccount='<%=usersVO.getUserAccount()%>'" alt=""></span>
        <div id="blue"></div>
        <div id="gary"></div>
        <div id="email_border">信箱 : <input type="text" value="<%=usersVO.getUserAccount()%>" id="e-mail" disabled="disabled" name="userAccount"></div>
        <div id="name_border">姓名 : <input type="text" value="<%=usersVO.getUserName()%>" readonly="readonly" id="name" disabled="disabled">
        </div>
        <div id="nikeName_border">匿名: <input type="text" value="<%=usersVO.getUserNickname()%>"  id="nikeName" name="userNickname"maxlength="10"></div>
        <div id="brd_border">生日 : <input type="text" value="<%=usersVO.getUserBrthday()%>" readonly="readonly" id="brd" disabled="disabled"></div>
        <div id="phone_border">電話 : <input type="text" value="<%=usersVO.getUserPhone()%>"   id="phone" name="userPhone" maxlength="10"></div>
        <div id="gender_border">性別 : <input type="text" value="<%=usersVO.getUserGender()%>" readonly="readonly" id="gender"disabled="disabled"></div>
        <div id="registrayion_border">註冊日 : <input type="text" value="<%=usersVO.getUserRegistrationDate()%>" readonly="readonly"
                id="registrayion" disabled="disabled"></div>
        <div id="forumLevel_border">論壇等級 : <input type="text" value="<%=ForumLevel%>" readonly="readonly" id="forumLevel">
        </div>

        <div id="darkgary"></div>
        <div id="shopLevel_border">購買等級 : <input type="text" value="<%=ShopLevel%>" readonly="readonly" id="shopLevel">
        </div>
        <div id="garylarge"></div>
        <div id="bonus_border">紅利點數 : <input type="text" value="<%=usersVO.getBonusPoints()%>" readonly="readonly" id="bonus">
        </div>
        <div id="alltogeter_scope_border">揪團總評分 : <input type="text" value="<%=usersVO.getAlltogetherScore()%>" readonly="readonly"
                id="alltogeter_scope">
            <div id="alltogeter_scope_people_border">揪團評分總人數 : <input type="text" value="<%=usersVO.getAlltogetherScorePeople()%>"
                    readonly="readonly" id="alltogeter_scope_people">
            </div>
            <div id="art_count_border">發文章次數 : <input type="text" value="<%=usersVO.getArtCount()%>" readonly="readonly" id="art_count">
            </div>
            <div id="like_total_border">發文章按讚次數 : <input type="text" value="<%=usersVO.getLikeTotalCount()%>" readonly="readonly"
                    id="like_total">
            </div>
            <div id="purchase_border">購買累計金額 : <input type="text" value="<%=usersVO.getPurchaseTotal()%>" readonly="readonly" id="purchase">
            </div>
            <div id="report_border">被檢舉次數 : <input type="text" value="<%=usersVO.getReportTotalCount()%>" readonly="readonly" id="report">
            </div>
            <div id="passwordUpdate_border"for="passwordUpdate"><input type="button" value="修改密碼" id="passwordUpdate">
            </div>
            <div id="update_border"for="update"><input type="button" value="修改資料" id="update">
            </div>
            <div id="commit_border"for="commit"><input type="button" value="確認" id="commit"onClick="check()">
            </div>
            <input type="file" id="file" style="visibility:hidden" name="userPic">
             <input type="hidden" name="userAccount" value="<%=usersVO.getUserAccount()%>">
    </form>
</body>
<script>
let passwordUpdate=document.getElementById("passwordUpdate").addEventListener("click", function () {
	  window.open("<%=request.getContextPath()%>/front-end/member/updatePassword.jsp ", "updatePassword", config = "height=540,width=445, top = 100, left = 700"); 
	  window.close();   
});
let forumLevel = document.getElementById("forumLevel");

let shopLevel = document.getElementById("shopLevel");
    let update = document.getElementById("update");
    let commit = document.getElementById("commit");
    update.addEventListener("click", function () {
        document.getElementById("nikeName").style.readonly=true;
        document.getElementById("phone").style.readonly=true;
        document.getElementById("file").style.visibility = "visible";
        document.getElementById("nikeName").style.opacity = "1";
        document.getElementById("nikeName").style.backgroundColor = "white";
        document.getElementById("phone").style.backgroundColor = "white";
        document.getElementById("phone").style.opacity = "1";
    });
    function check() {
        var iphone = /^[0-9]{10}$/;
        pass = document.getElementById("phone");
        if (!iphone.test(pass.value)) {
        	Swal.fire("請輸入電話號碼(例:0912345678)")
        	  	  	return false;
        }
        document.userInfo.submit();
       }
 
    
</script>
<style>
    #form {
        width: 400px;
        height: 500px;
        border: 3px solid rgb(19, 32, 151);
        position: relative;
    }

    #form #blue {

        width: 400px;
        height: 133px;
        margin: -3px 0 0 0;
        background: -webkit-linear-gradient(right, rgb(193, 109, 163), rgba(7, 42, 244, 0.397));
    }

    #form #garylarge {

        width: 400px;
        height: 278px;
        margin: px 0 0 0;
        background-color: rgb(184, 222, 222);
    }

    #form #darkgary {

        width: 400px;
        height: 10px;
        margin: -3px 0 0 0;
        background-color: rgb(116, 109, 109);
    }

    #form #gary {

        width: 400px;
        height: 46px;
        margin: px 0 0 0;
        background-color: rgb(221, 215, 215);
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

    #form #email_border {
        position: absolute;

        border-radius: 5px;
        top: 130px;
        left: 130px;
        width: 250px;
        height: 30px;
    }

    #form #e-mail {
        border-radius: 10px;
        width: 180px;
        height: 30px;
    }

    #form #forumLevel_border {
        position: absolute;
        border-radius: 10px;
        top: 230px;
        left: 270px;
        width: 150px;
        height: 30px;


    }

    #form #forumLevel_border #forumLevel {

        border-radius: 10px;

        width: 40px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);

    }



    #form #name_border {
        position: absolute;
        width: 135px;
        top: 35px;
        left: 130px;

    }

    #form #name_border #name {
        border-radius: 5px;
        border: 1px solid rgb(22, 22, 23);
        width: 50px;
        height: 20px;
        font-size: 16px;

    }

    #form #nikeName_border {
        position: absolute;
        top: 100px;
        border-radius: 5px;
        left: 275px;
        width: 155px;
        height: 30px;


    }

    #form #nikeName_border #nikeName {
        border-radius: 5px;
        border: 1px solid rgb(22, 22, 23);
        width: 80px;
        height: 20px;
        font-size: 16px;
           background-color:lightgray;
         opacity:0.4;
    }



    #form #brd_border {
        position: absolute;
        border-radius: 5px;
        top: 68px;
        left: 130px;
        width: 205px;
        height: 30px;
    }

    #form #brd_border #brd {
        border-radius: 5px;
        border: 1px solid rgb(22, 22, 23);
        width: 85px;
        height: 20px;
        font-size: 16px;
    }

    #form #phone_border {
        position: absolute;
        border-radius: 5px;
        top: 100px;
        left: 130px;
        width: 160px;
        height: 30px;
       
    }

    #form #phone_border #phone {
        border-radius: 5px;
        border: 1px solid rgb(22, 22, 23);
        width: 90px;
        height: 20px;
        font-size: 16px;
         background-color:lightgray;
         opacity:0.4;
    }

    #form #gender_border {
        position: absolute;
        border-radius: 5px;
        top: 68px;
        left: 270px;
        width: 155px;
        height: 30px;
    }

    #form #gender_border #gender {
        border-radius: 5px;
        border: 1px solid rgb(22, 22, 23);
        width: 70px;
        height: 20px;
        font-size: 16px;
    }

    #form #registrayion_border {
        position: absolute;
        border-radius: 5px;
        top: 35px;
        left: 250px;
        width: 155px;
        height: 30px;
    }

    #form #registrayion_border #registrayion {
        border-radius: 5px;
        border: 1px solid rgb(22, 22, 23);
        width: 85px;
        height: 20px;
        font-size: 16px;

    }

    #form #shopLevel_border {
        position: absolute;
        border-radius: 10px;
        top: 230px;
        left: 5px;
        width: 150px;
        height: 30px;
    }

    #form #shopLevel_border #shopLevel {
        border-radius: 10px;
        width: 40px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #bonus_border {
        position: absolute;
        border-radius: 10px;
        top: 230px;
        left: 130px;
        width: 170px;
        height: 30px;
    }

    #form #bonus_border #bonus {
        border-radius: 10px;
        width: 50px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #alltogeter_scope_border {
        position: absolute;
        border-radius: 10px;
        top: 170px;
        left: 260px;
        width: 150px;
        height: 30px;
    }

    #form #alltogeter_scope_border #alltogeter_scope {
        border-radius: 10px;
        width: 40px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #alltogeter_scope_people_border {
        position: absolute;
        border-radius: 10px;
        top: 0px;
        left: -255px;
        width: 250px;
        height: 30px;
    }

    #form #alltogeter_scope_people_border #alltogeter_scope_people {
        border-radius: 10px;
        width: 100px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #art_count_border {
        position: absolute;
        border-radius: 10px;
        top: 110px;
        left: -255px;
        width: 430px;
        height: 30px;
    }

    #form #art_count_border #art_count {
        border-radius: 10px;
        width: 280px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #like_total_border {
        position: absolute;
        border-radius: 10px;
        top: 160px;
        left: -255px;
        width: 400px;
        height: 30px;
    }

    #form #like_total_border #like_total {
        border-radius: 10px;
        width: 250px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #purchase_border {
        position: absolute;
        border-radius: 10px;
        top: 210px;
        left: -255px;
        width: 400px;
        height: 30px;
    }

    #form #purchase_border #purchase {
        border-radius: 10px;
        width: 270px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #report_border {
        position: absolute;
        border-radius: 10px;
        top: 260px;
        left: -255px;
        width: 400px;
        height: 30px;
    }

    #form #report_border #report {
        border-radius: 10px;
        width: 50px;
        height: 30px;
        border: 1px solid rgb(22, 22, 23);
    }

    #form #update_border {
        position: absolute;
        border-radius: 10px;
        top: 260px;
        left: -15px;
        width: 80px;
        height: 50px;

    }

    #form #update_border #update {
        border-radius: 10px;
        width: 60px;
        height: 50px;
        border: 1px solid rgb(22, 22, 23);
        padding:0 0 0 0px;
    }

    #form #commit_border {
        position: absolute;
        border-radius: 10px;
        top: 260px;
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
    #form #passwordUpdate_border {
        position: absolute;
        border-radius: 10px;
        top: 260px;
        left: -90px;
        width: 80px;
        height: 50px;

    }
#form #passwordUpdate_border #passwordUpdate{
        border-radius: 10px;
        width: 60px;
        height: 50px;
        border: 1px solid rgb(22, 22, 23);
         padding:0 0 0 0px;

    }
    #form #file {
        position: absolute;
        width: 70px;
        height: 20px;
        left: -230px;
        top: -25px;


    }

    #form>span {

        width: 400px;
        height: 200px;
    }

    #form>span>img {
        position: absolute;
        width: 103px;
         height: 105px;
        top: 35px;
        left: 10px;
    }
</style>

</html>