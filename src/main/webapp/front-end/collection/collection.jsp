<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.ColVen.model.*"%>
<%@ page import="java.util.*"%>
<%
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); 
Integer user=usersVO.getUserId();
ColVenService ColVenSvc = new ColVenService();
    List<ColVenVO> list = ColVenSvc.getAll(user);
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
        <link rel="website icon" href="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/collection/css/sidebar.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
          integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/616f19a0b0.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <script src="<%=request.getContextPath()%>/front-end/member/js/jquery.min.js"></script>
        <title>7 Tour | 旅遊規劃</title>
        
        <style>
        	* {
			    margin: 0;
			    padding: 0;
			}
			
			body {
			     overflow: scroll;
    			 font-family: "微軟正黑體";
			}
			
			ul {
			    list-style: none;
			}
			#wrapper {
				background-color:#DEDEDE;
			}
			#banner{
				position: absolute;
				left:100px;
				top:30px;
			}
			#hotelPic{
				position: absolute;
				left:300px;
				width:230px;
				height:135px;
			}
		    #position{  
			    position: absolute;
			    top:100px;
			    right:400px;
		    }   
		    #position>p{  
			    width:150px;
			    position: absolute;
			    top:30px;
			    right:450px;
		    }
			#position #vendor{
				float: middle;
				width:600px;
				height: 480px;
				background-color: white;
				border-radius: 10px;
				margin: 65px 0 0 0px;
			}
			#position #vendor #font{
				position: relative;
				margin: 10px 0 0 10px;
				padding: 10px 0 0 0;
			}
			#position #vendor #heartPic{
				position: absolute;
				bottom:-5px;
				width: 30px;
				margin: 0px 0 0 540px;
			}
        </style>
      </head>
      
      <body>
      <c:import url="/front-end/header.jsp" ></c:import>
          <div id="wrapper" style="left:0;">

             <div class="sidebar">
                <div class="headSculpture">
                   <img src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='<%=usersVO.getUserAccount()%>'"  alt="">
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
                         <p><a href="<%=request.getContextPath()%>/front-end/collection/myTrip.jsp" class="text-decoration-none link-dark">我的行程</a></p>
                      </li>
                      <li><i class="bi bi-file-text"></i>
                         <p>店家收藏</p>
                      </li>
                   </ul>
                </div>
             </div>
             <!-- 侧边栏按钮 -->
             <button></button>
             <!-- 内容区域 -->
            <div id="banner">
               <h2>我的收藏</h2>
             </div>
          </div>
<!--================================================================================================================== 表格部分-->
	<table>
		<div id="position">
			<p><%@ include file="page1.file"%> </p>
			
		<c:forEach var="ColVenVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
				      
		<div id="vendor"> 
		<img src="<%=request.getContextPath()%>/front-end/collection/getVenPic.jsp?venId='${ColVenVO.venId}'"  alt="" id="hotelPic">
		  <div id="font"> <h4>${ColVenVO.venName}</h4>
		  <div>TEL:${ColVenVO.venTel}</div>
		  <div>${ColVenVO.venLocation}</div>
		  <div>聯絡人:${ColVenVO.venWin}</div>
		  <hr>
		  <div>
		    ${ColVenVO.venIntro}
		  </div>
		  <hr>
		  <div style="color:red">評分：${ColVenVO.venScore}顆星(${ColVenVO.venScorePeople})</div>
		  <div><FORM id="Delete" name="Delete" METHOD="post" ACTION="<%=request.getContextPath()%>/collectionServlet" style="margin-bottom: 0px;" >
		    <img src="<%=request.getContextPath()%>/front-end/collection/images/heart.png"  alt="" id="heartPic">
		    <input type="hidden" name="userId"  value="${ColVenVO.userId}">
		    <input type="hidden" name="venId"  value="${ColVenVO.venId}">
		    <input type="hidden" name="action" value="deleteVen"></FORM></div>
		</div>
		<br>
		<br>
		</div>
		</c:forEach>
	</div>
	</table>
     <script src="<%=request.getContextPath()%>/front-end/member/js/sidebar.js"></script>
     <script>
//         <!-- 控制delete 表單發送-->
        let heart = document.getElementById("heartPic").addEventListener("click", function () {
        	document.getElementById("Delete").submit();
        	});
//         <!-- 控制登出發送  -->
   		loginout.addEventListener("click", function () {
        	document.login_out.submit();
        });
//    		<!-- 控制userInfo 發送   -->
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