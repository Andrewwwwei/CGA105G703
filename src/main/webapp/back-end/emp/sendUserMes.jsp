<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.emp.model.*"%>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.Mes.model.*"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date , java.text.SimpleDateFormat"%>
<%
EmpVO empVO = (EmpVO) session.getAttribute("empVO");
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); 
SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss.0");
Date currentTime_1 = new Date();
String pDate = formatter.format(currentTime_1); 
%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/back.css">
  <title>7 Tour | 發送個人訊息 </title>
   <style>
	  #sendMes #userBorder{
	 border:1px solid white;
	 width:600px;
	 height:500px;
	 font-family:"微軟正黑體";
	 position:absolute;
	 font-size:20px;
	 font-weight:bold;
	top:80px;
	left:550px;
	border-radius:20px;
	}
	  #sendMes #userPic{
	 width:150px;
	 height:150px;
	  border-radius:20px;
	}  
	#sendMes #title{
	  position:absolute;
	  top:-20px;
	left:155px;
	font-size:26px;
	}
	  #sendMes #email_border{
	  position:absolute;
	  top:40px;
	  left:155px;
	  }
	  #sendMes #name_border{
	  position:absolute;
	  top:65px;
	  left:155px;
	  }
	  #sendMes #nikeName_border{
	  position:absolute;
	  top:65px;
	  left:355px;
	  }
	  #sendMes #brd_border{
	  position:absolute;
	  top:90px;
	  left:155px;
	  }
	  #sendMes #phone_border{
	  position:absolute;
	  top:90px;
	  left:355px;
	  }
	  #sendMes #gender_border{
	  position:absolute;
	  top:115px;
	  left:155px;
	  }
	  #sendMes #registrayion_border{
	  position:absolute;
	  top:115px;
	  left:305px;
	  }
	  #sendMes #line{
	    position:absolute;
	    top:235px;
	    left:550px;
	    width:600px;
	    height:5px;
	    background:lightgray;
	  }
	    #sendMes #sendUserMes{
	    position:absolute;
	    top:235px;
	    left:550px;
	    width:600px;
	    height:500px;
	   }
	   #sendMes #userId_border{
	    position:absolute;
	    font-size:36px;
	    top:30px;
	    left:10px;
	   }
	   #sendMes #sendTitle_border{
	    position:absolute;
	   font-size:36px;
	   height:30px;
	    top:90px;
	    left:10px;
	   }
	   #sendMes #sendContent_border{
	    position:absolute;
	    font-size:36px;;
	    top:150px;
	    left:10px;
	   }
	   #sendMes #sendPic_border{
	    position:absolute;
	    font-size:36px;
	    top:210px;
	    left:10px;
	   }
	   #sendMes #sendTime_border{
	    position:absolute;
	    font-size:36px;
	    top:270px;
	    left:10px;
	    
	   }
	#sendMes #send{
	    position:absolute;
	    font-size:30px;
	    top:280px;
	    left:480px;
	}
 </style>
</head>

<body>
<div id="sendMes" style="background-color:black;width:100%;height:720px">
	<div id="userBorder">
	<img id="userPic" src="<%=request.getContextPath()%>/front-end/member/getUserPic.jsp?UserAccount='<%=usersVO.getUserAccount()%>'" alt="">
	<p id="title"style="color:white">會員基本資料</p>
	    <div id="email_border"style="color:white">信箱 : <%=usersVO.getUserAccount()%></div>
        <div id="name_border"style="color:white">姓名 : <%=usersVO.getUserName()%></div>
        <div id="nikeName_border"style="color:white">匿名: <%=usersVO.getUserNickname()%></div>
        <div id="brd_border"style="color:white">生日 : <%=usersVO.getUserBrthday()%></div>
        <div id="phone_border"style="color:white">電話 : <%=usersVO.getUserPhone()%></div>
        <div id="gender_border"style="color:white">性別 : <%=usersVO.getUserGender()%></div>
        <div id="registrayion_border"style="color:white">註冊日 : <fmt:formatDate value="<%=usersVO.getUserRegistrationDate()%>" pattern="yyyy-MM-dd HH:mm:ss "/></div>
	</div>
	<div id="line"></div>
	<div id="sendUserMes">
	<p id="title"style="color:white">發送訊息欄位</p>
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<ul style="display:inline-block">
	    	<c:forEach var="message" items="${errorMsgs}">
				<li style="color:red;font-size:18px;list-style-type: none;margin:-5px 0 0 340px;">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<jsp:useBean id="mesSvc" scope="page" class="com.Mes.model.MesService" />
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" ENCTYPE="multipart/form-data">
        <input type="hidden" name="action" value="mesSend">
	    <div id="userId_border"style="color:white">使用者ID :&ensp;<input type="text" value="<%=usersVO.getUserId()%>" style="height:40px;font-size:26px;background-color:#A1A1A1;" readonly name="userId"></input></div>
        <div id="sendTitle_border"style="color:white;">留言標題 :&ensp;<select style="color:black;height:40px;font-size:26px" name="sendTitle">
		   	 	<option>警告通知</option>
		    	<option>站務通知</option>
		    	<option>購物通知</option>
			</select>
		</div>
        <div id="sendContent_border"style="color:white">留言內容 :&ensp;<input type="text" name="sendContent" maxlength="20" style="height:40px;font-size:26px;"></input></div>
        <div id="sendPic_border"style="color:white">發送照片 :&ensp;<input type="file" name="sendPic"style="height:40px;font-size:26px;width:350px"></input></div>
		<div id="sendTime_border"style="color:white">發送時間 :&ensp;<input type="text" name="sendTime" value="<%=pDate%>" style="height:40px;font-size:26px;width:247px;background-color:#A1A1A1;"readonly></input></div>
        <div id="readMesseng_border"style="color:white"><input id="readMesseng" type="hidden" value="1" name="readMesseng"></input></div>
        <input id="send" type="submit" value="送出">
     </FORM>   
	</div>
</div>


<c:import url="/back-end/sidebar.jsp"></c:import>
 
</body>  
 
 </html>