<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.Mes.model.*"%>
<%@ page import="com.Collection.model.*"%>
<%@ page import="java.util.*"%>

<% 
try{
	UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
	if(usersVO!=null){
		Integer user=usersVO.getUserId();
		String userAcount=usersVO.getUserAccount();
		MesService MesSvc = new MesService();
		    List<MesVO> list = MesSvc.getAll(user);
		    pageContext.setAttribute("meslist",list);
		    byte UserShopLevel=usersVO.getUserShopLevel();
		    String str="";
		    if(UserShopLevel==1){
		    	str="VIP";
		    }
		    if(UserShopLevel==0){
		    	str="一般";
		    }
	}	
}catch (NullPointerException e) {
	e.printStackTrace();
}
%>

	<link
		href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
		rel="stylesheet"
		integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
		crossorigin="anonymous">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/header.css">
	<link rel="website icon" href="<%=request.getContextPath()%>/front-end/images/bgremove_Logo.jpg">
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
		integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e"
		crossorigin="anonymous">
	<title>7 Tour | 旅遊規劃</title>
	<style>
		.change {
		   color: #FF3B3B; 
		}
	</style>

<body>
	<c:import url="/front-end/customerService/customer_service_front.jsp"></c:import>
	<c:import url="/front-end/cart/cartIcon.jsp"></c:import>
	<!-- nav start -->
	<nav class="navbar navbar-expand-lg navbar-light bg-cblue fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="<%=request.getContextPath()%>/front-end/index.jsp">
        <img src="<%=request.getContextPath()%>/front-end/images/headerLogo.png" id="logo">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">論壇</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/article?action=forumHome&userId=${sessionScope.usersVO.getUserId()}">論壇首頁</a></li>
			  <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath() %>/article?action=userPosted&userId=${sessionScope.usersVO.getUserId()}">文章列表</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">訂房</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/Room?action=toRoomIndex">訂房首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/RoomOrder?action=toThisUserOrder">我的訂單</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/ShoppingCart?action=showCart">購物車</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">揪團</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/front-end/act/actHomePage.jsp">揪團首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/front-end/act/addAct.jsp">建立揪團</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/back-end/act/act.do?action=getLeaderAct">我的揪團</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="<%=request.getContextPath()%>/back-end/act/act.do?action=getMyJoinAct">參團紀錄</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="<%=request.getContextPath()%>/front-end/Faq/FaqPage.jsp">FAQ</a>
          </li>
        </ul>
        <a href="<%=request.getContextPath()%>/front-end/member/login.jsp" id="login" class="text-decoration-none link-dark">登入/註冊</a>
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item bg-dblue rounded mx-3 ">
              <a class="nav-link fs-5 text-white fw-bold" href="<%=request.getContextPath()%>/front-end/TripPlan/userTripAll.jsp">開始規劃</a>
          </li>

          <li class="nav-item user-msg">
            <div id="bi"><i class="bi bi-bell-fill fa-2x msg-icon"></i></div>
            <ul class="bg-cblue msg-list">
	            <c:forEach var="MesVO" items="${meslist}" begin="<%=0%>" end="<%=10%>">
	            
		                <div id="msg-item" class="msg-item">
	     				<FORM id="color" name="color" METHOD="post" ACTION="<%=request.getContextPath()%>/usersServlet" style="margin-bottom: 0px;">
			                <img src="<%=request.getContextPath()%>/MesServlet?mesId=${MesVO.mesId}" id="mesPic" style="width:30px;float:right">
			            	<input type="hidden" name="userId"  value="${MesVO.userId}">
			    			<input type="hidden" name="action" value="changeColor">
	    				</FORM>
			             
			                <h5>${MesVO.sendTitle}</h5>
			                <p class="text-truncate" style="white-space: normal;width:200px">${MesVO.sendContent}</p>
			               	<p>發送時間：<fmt:formatDate value="${MesVO.sendTime}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
			               	<FORM id="delete_mes" name="delete_mes" METHOD="post" ACTION="<%=request.getContextPath()%>/usersServlet" style="margin-bottom: 0px;">
			              		<div id="bell" class="bell"><i class="bi-bell" style="font-size:24px;position:relative;z-index:1"></i></div>
			                 	<input type="hidden" name="mesId"  value="${MesVO.mesId}">
			    				<input type="hidden" name="action" value="deleteMes">
			    			</FORM>
			                <li  class="bgcolor" >
			                	<div class="text" style="color:transparent;font-size:0px">${MesVO.readMesseng}</div>
			                </li>
			            </div>
	              <hr class="m-1">
	             </c:forEach>
            </ul>
          </li>

          <li class="nav-item mx-3 user-info">
            <img src="<%=request.getContextPath()%>/front-end/images/user.png" alt="會員照片" class="user-pic" id="userPic">
            <ul class="bg-cblue info-list">
				
			  <!-- 基本資料 -->	
              <li id="info">
              		<a href="#" class="d-block info-item text-decoration-none link-dark">會員基本資料</a>
              </li>
			  <!-- 我的行程 -->	
              <li id="trip"><a href="<%=request.getContextPath()%>/front-end/TripPlan/userTripAll.jsp" class="d-block info-item text-decoration-none link-dark">我的行程</a></li>
			  <!-- 我的收藏 -->	
			  <li id="collection" name="collection">
				 <form id="Collection" name="Collection" METHOD="post" ACTION="<%=request.getContextPath()%>/collectionServlet"><input type="hidden" name="action" value="collection">
					 <a href="#" class="d-block info-item text-decoration-none link-dark">我的收藏</a>
			 	</form>
			 </li>
			  <!-- 我的訂單 -->	
              
              <li><a href="<%=request.getContextPath()%>/RoomOrder?action=toThisUserOrder" class="d-block info-item text-decoration-none link-dark">我的訂單</a></li>
			  <!-- 登出 -->	
			  <li id="loginout" style="display:none" name="loginout">
              	<form id="login_out" name="login_out" ACTION="<%=request.getContextPath()%>/usersServlet">
	              <input type="hidden" name="action" value="loginout">
	              <a href="#" class="d-block info-item text-decoration-none link-dark">登出</a>
             	 </form>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <div style="height: 50px;"></div>
	<!-- nav end -->
   <script> 
<%
 UsersVO usersVO2 = (UsersVO)request.getSession().getAttribute("usersVO");
 if(usersVO2!=null){
	out.println("let userPic = document.getElementById('userPic').src="+"'"+request.getContextPath()+"/front-end/member/getUserPic.jsp?UserAccount="+"\""+usersVO2.getUserAccount()+"\""+"'"+";");
	out.println("login.style.visibility = 'hidden' ; ");
	out.println("loginout.style.display='block' ; ");
	out.println("let position = document.getElementById('mesPic').addEventListener('click', function () {"+
	"document.getElementById('color').submit();"
	+"});");
 }
%>
 </script> 
  <script src="https://kit.fontawesome.com/616f19a0b0.js" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
 <script> 
 let collection=document.getElementById("collection").addEventListener("click", function () {
	   document.Collection.submit();
 });     
 let bgcolor = document.getElementsByClassName("msg-item");
	for(let i = 0;i<bgcolor.length;i++){
	  	if(document.getElementsByClassName('bgcolor')[i].innerText==0){
	  	document.getElementsByClassName('msg-item')[i].style.backgroundColor='gray';}
 	}
 let loginout=document.getElementById("loginout");
 loginout.addEventListener("click", function () {document.login_out.submit();});  
 let info = document.getElementById("info").addEventListener("click", function () {
	      window.open("<%=request.getContextPath()%>/front-end/member/userInfo.jsp ", "userInfo", config = "height=540,width=445, top = 100, left = 700"); 
	     });
 if(document.getElementById('msg-item') != null){
	 if(document.getElementById('msg-item').style.backgroundColor!='gray'){
		 document.getElementById("bi").className = "change";
	 }
 }
 let bellArr = document.querySelectorAll('.bell');
 for (let bell of bellArr) {
     bell.addEventListener("click", function() {
             this.parentElement.submit();
         });
 }
</script>
</body>

</html>