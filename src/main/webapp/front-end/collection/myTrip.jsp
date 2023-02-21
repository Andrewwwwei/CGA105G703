<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.ColIti.model.*"%>
<%@ page import="java.util.*"%>
<%
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); 
Integer user=usersVO.getUserId();
String userAcount=usersVO.getUserAccount();
ColItiService ColItiSvc = new ColItiService();
    List<ColItiVO> list = ColItiSvc.getAll(user);
    pageContext.setAttribute("list",list);
    byte UserShopLevel=usersVO.getUserShopLevel();
    String str="";
    if(UserShopLevel==1){
    	str="VIP";
    }
    if(UserShopLevel==0){
    	str="一般";
    }
%>
<!DOCTYPE html>

<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/collection/css/header.css">
        <link rel="website icon" href="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
          integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
          <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/collection/css/sidebar.css">
          <script src="https://kit.fontawesome.com/616f19a0b0.js" crossorigin="anonymous"></script>
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
          <script src="<%=request.getContextPath()%>/front-end/member/js/jquery.min.js"></script>
        
        <title>7 Tour | 旅遊規劃</title>
        
           <style>
			   body {
			      overflow: scroll;
			    font-family: "微軟正黑體";
			    
			}
			#wrapper {
				background-color:#DEDEDE;
			}
			#banner{
				position: absolute;
				left:100px;
				top:30px;
			}
			#tripPic{
			width:230px;
			height:170px;
			
			}
			  #position{  
			 position: absolute;
			 left:470px;
			 margin:30px 0 0 10px;
			
			  }   
			    #position>p{  
			 margin:0 0 0 20px;
			   
			  }
			#position #trip_share{
			  display:Inline-Block;
			margin:10px 0 20px 10px;
			 padding:0 0 0 10px;
			}
			
			#position #trip_share #font{
			
			  width:230px;
			   height:210px;
			border-radius:0 0 10px 10px;
			background-color:white;
			text-indent:5px;
			 
			}
			#position #trip_share #heartPic{
			width:30px;
			margin:10px 0 0 5px;
			 }
  		</style>
  </head>
      
      <body>	
      <c:import url="/front-end/header.jsp" ></c:import>
          <div id="wrapper" style="left:0;">

             <div class="sidebar">
                <div class="headSculpture">
                   <img src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='<%=usersVO.getUserAccount()%>'" id="userPic">
                   <p><%=usersVO.getUserName()%></p>
                </div>
                <p id="vip">論壇等級：達人</p>
                <div class="option">
                   <ul>
                      <li><i class="bi bi-award"></i>
                         <p>購買等級：</p><p id="point"><%=str%></p>
                      </li>
                      <li><i class="bi bi-gem"></i>
                         <p>紅利積點</p><p id="point"><%=usersVO.getBonusPoints()%></p>
                      </li>
                      <li><i class="bi bi-people-fill"></i>
                         <p>揪團評分</p><p id="point"><%=usersVO.getAlltogetherScore()%></p>
                      </li>
                      <li><i class="bi bi-bag"></i>
                        <p>購買累計金額</p><p id="point"><%=usersVO.getPurchaseTotal()%></p>
                     </li>
                     <li><i class="bi bi-book"></i>
                      <p><a href="<%=request.getContextPath()%>/front-end/collection/myArt.jsp" class="text-decoration-none link-dark">我的文章</a></p>
                   </li>
                      <li><i class="bi bi-calendar2"></i>
                         <p>我的行程</p>
                      </li>
                      <li><i class="bi bi-file-text"></i>
                         <p><a href="<%=request.getContextPath()%>/front-end/collection/collection.jsp" class="text-decoration-none link-dark">店家收藏</a></p>
                      </li>
                   </ul>
                </div>
             </div>
             <!-- 侧边栏按钮 -->
             <button></button>
             <!-- 内容区域 -->
            <div id="banner">
               <h2>我的行程</h2>
             </div>
          </div>
          
<!--================================================================================================================== 表格部分-->
	<table>
		<div id="position">
			<p><%@ include file="page1.file"%> </p>
			
		<c:forEach var="ColItiVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				      
		<div id="trip_share"> <img src="<%=request.getContextPath()%>/front-end/collection/getTripPic.jsp?tripShareId='${ColItiVO.tripShareId}'"  alt="" id="tripPic">
		  <div id="font"> <h6 style="color:#00B2B2">分享發文會員：${ColItiVO.userAccount}</h6>
		  <div>行程名稱：${ColItiVO.tripName}</div>
		  <div>開始時間：${ColItiVO.startDate}</div>
		    <div id="tripContent">結束時間：${ColItiVO.endDate}</div>
		    <div style="color:#FF2626">文章瀏覽數：${ColItiVO.tripShareShowCount}</div><div style="color:#FF2626">文章讚數：${ColItiVO.tripShareLlkesCount}</div>
		  <div><FORM id="Delete" name="Delete" METHOD="post" ACTION="<%=request.getContextPath()%>/collectionServlet" style="margin-bottom: 0px;">
		    <img src="<%=request.getContextPath()%>/front-end/collection/images/heart.png"  alt="" id="heartPic">
		    <input type="hidden" name="userId"  value="${ColItiVO.userId}">
		    <input type="hidden" name="tripShareId"  value="${ColItiVO.tripShareId}">
		    <input type="hidden" name="action" value="deleteTrip"></FORM></div>
		</div>
		</div>
		</c:forEach>
		</div>
	</table>
		<script src="<%=request.getContextPath()%>/front-end/member/js/sidebar.js"></script>
        <script>
	        <!-- 控制delete 表單發送                                                                                   -->
	        let heart = document.getElementById("heartPic").addEventListener("click", function () {
	        	document.getElementById("Delete").submit();
	        	});
	        <!-- 控制登出發送                                                                                   -->
	   		loginout.addEventListener("click", function () {
	        	document.getElementById("login_out").submit();
	        });
	   		<!-- 控制userInfo 發送                                                                                   -->
	        document.getElementById("info").addEventListener("click", function () {
	        	window.open("<%=request.getContextPath()%>/front-end/member/userInfo.jsp ", "userInfo", config = "height=540,width=445, top = 100, left = 700"); 
	        });
	        <%  
	        UsersVO usersVO1 = (UsersVO)session.getAttribute("usersVO");
	        if(usersVO1!=null){
	        	out.println("document.getElementById('userPic').src="+"'"+request.getContextPath()+"/front-end/member/getUserPic.jsp?UserAccount="+"\""+usersVO1.getUserAccount()+"\""+"'"+";");
	        	};
	        %>
        </script> 
    </body>
</html>