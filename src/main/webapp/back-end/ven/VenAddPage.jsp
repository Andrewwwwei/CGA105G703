<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>


<%
VendorVO vendorVO = (VendorVO) request.getAttribute("vendorVO");
%>

<html>
<head>
	<script src="https://kit.fontawesome.com/33115e34cf.js"
		crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<%--  <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/ven/Vendor.css"> --%>
<title>Document</title>

<style type="text/css">

/* ========================================== 上方function============================================ */
    div.function {
      background-color: #dee4ec;
      width: calc(100% - var(--aside-width));
      /*減號兩邊一定要空格 ; */
      height: var(--head-height);
      position: fixed;
      top: 0;
      left: var(--aside-width);
      display: flex;
      align-items: center;
      justify-content:center;
      z-index: 3;

    }

div.function p {

      font-size:25px;
       font-family: 'Noto Sans TC', sans-serif;
       color:#002e65;
    }
    
     div.function a{

position: absolute;
left:20px;
top:10px;

    }

/* ==========================================主要工作區============================================ */
    div.workplace {
      margin-left: calc(var(--aside-width) + 30px);
      /* margin-right: 20px; */
      margin-top: 50px;
      width: calc(100% - var(--aside-width) - 60px);
      /*減號兩邊一定要空格*/
      display: inline-block;
      justify-content: center;
      position: absolute;
      top: var(--head-height);
      z-index: 1;
      
      justify-content: space-between;
    }
     
    div.workplace label {
     
      margin-right: 8px;
      font-size: 18px;
    }
    
     div.workplace select {
      width: 130px;
      height: 21.5px;


    }



       div.workplace button{
     background-color: transparent;
      border: 2px solid #041427;
      width: 60px;
      height:30px;
      border-radius: 6px;
      font-size: 20px;
      margin:2px;
    }
    div.workplace button:hover{
    background-color:#041427;
    color:white;
    }
    
       div.workplace input{
     background-color: transparent;
      border: 2px solid #041427;
     height:35px;
      border-radius: 6px;
      font-size: 20px;
      margin:10px;
    padding-left:10px;
     
    }
    
           div.workplace select{
     background-color: transparent;
      border: 2px solid #041427;
     height:35px;
      border-radius: 6px;
      font-size: 20px;
      margin:10px;
      text-align: center;
      
    }
   
           div.workplace textarea{
     background-color: transparent;
      border: 2px solid #041427;
 
      border-radius: 6px;
      font-size: 20px;
      margin-top:10px;
      margin-bottom:10px;
       padding-left:10px;
    }
    
    div.workplace div.art{
    text-align: center;
    margin-top:10px;
    }
    
        div.workplace div.art label{
   float:left;

    }
</style>

</head>
<body>

<c:import url="/back-end/sidebar.jsp"></c:import>


		<!-- ---------------------function body區 --------------------->
		<div class="function">

<a  href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp"> <i class="fa-solid fa-arrow-left fa-2x"  ></i></a>

			<p>新增廠商資料</p>



		</div>
		<div class="right">
		<!-- ---------------------main body區 --------------------->


		<div class="workplace">
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VenServlet" name="venform">
				
				<main class="head">



					<label for="venpw">廠商密碼: </label> <input type="text" name="venPw"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenPw()%>" id="venpw"
						style="width: 150px;" /> 
						
						<label for="name">廠商名稱: </label> <input
						type="text" name="venName" id="name"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenName()%>"
						style="width: 150px;" /> 
						
						<label for="ventax">統一編號: </label> <input
						type="text" name="venTaxNum" id="ventax"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenTaxnum()%>"
						style="width: 150px;" /> 
						
						<label for="venemail">電子信箱: </label> <input
						type="text" name="venEmail" id="venemail"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenEmail()%>"
						style="width: 300px;" /> <br>
						
						 <label for="ventel">旅宿電話:
					</label> <input type="text" name="venTel" id="ventel"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenTel()%>"
						style="width: 150px;" /> 
						
						<label for="venwin">聯繫窗口: </label> <input
						type="text" name="venWin" id="venwin"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenWin()%>"
						style="width: 150px;" /> 
						
						<label for="venwintel">窗口電話: </label> <input
						type="text" name="venWinTel" id="venwintel"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenWintel()%>"
						style="width: 150px;" />
						
						 <label for="venlocation">通訊地址: </label> <input
						type="text" name="venLocation" id="venlocation"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenLocation()%>"
						style="width: 300px;" /> <br>
						
						 <label for="venbank">銀行帳號:</label> <input type="text" name="venBank" id="venbank"
						value="<%=(vendorVO == null) ? "" : vendorVO.getVenBank()%>"
						style="width: 150px;" /> 
						
						<label for="venstatus">帳號狀態: </label>
						 <select name="venStatus" style="width: 150px;">

<c:if test="${vendorVO.venStatus == 0}" var="true"><option value="0">未啟用</option></c:if>
<c:if test="${vendorVO.venStatus ==1}" var="true"><option value="1">啟用</option></c:if>
<c:if test="${vendorVO.venStatus == 2}" var="true"><option value="2">停用</option></c:if>


<c:if test="${vendorVO.venStatus != 0}" var="true"><option value="0">未啟用</option></c:if>
<c:if test="${vendorVO.venStatus != 1}" var="true"><option value="1">啟動</option></c:if>
<c:if test="${vendorVO.venStatus != 2}" var="true"><option value="2">停用</option></c:if>

					    </select> 
					
					<label for="scorepeo">評分人數: </label> <input type="text"
						name="venScorePeople" id="scorepeo"
						value="0" style="width: 150px;background-color:#b8bbbe !important;"readonly/> 
						
						<label>加入日期:</label> 
						<input type="text" id="f_date1" name="venJoinDate" style="width: 300px;">
						<br> <p>
						<div class="art">
						<label for="venintro">旅宿介紹: </label><br>
					<textarea name="venIntro" rows="10" cols="80" required >
<%=(vendorVO == null) ? "" : vendorVO.getVenIntro()%>

</textarea>
					<br> <label for="canpolicy">取消政策: </label><br>

					<textarea name="venCanPolicy" rows="10" cols="80" required >
<%=(vendorVO == null) ? "" : vendorVO.getVenCanpolicy()%>
</textarea>
					<br> <label for="vennotice">入住須知: </label><br>
					<textarea name="venNotice" rows="10" cols="80" required >
<%=(vendorVO == null) ? "": vendorVO.getVenNotice()%>
</textarea><br>



				<button type="submit">新增</button>
				<input type="hidden" name="action" value="insert"> 
                </div>
                
				</main><p>
				<%-- 錯誤表列 --%>
 <c:if test="${not empty errorMsgs}">
   <span>
		<c:forEach var="message" items="${errorMsgs}">
			<span style="color:red">${message} /</span><br>
		</c:forEach>
		   </span>
	</c:if> 
			</form>
		</div>
	</div>
	<script>

    $(".aside_list li").click(function () {
      $(this).css("background", "#041427").siblings().css("background", "");
    })
    
   $(function () {


  $(".adju").click(function () {
    $("input").attr("disabled", false);
    $("textarea").attr("disabled", false);
    $(this).hide();
    $(".can").toggle();
    $(".confirm").toggle();
    
  
  });

  $(".can").click(function () {

    $(".btnSubmit").attr("disabled", true);
    $(".adju").toggle();
    $(".can").toggle();
    $(".confirm").toggle();
    $("input").prop("disabled", true);
    $("textarea").prop("disabled", true);
  });
});
    
		        $("input").focus(function () {
      $(this).css("background","#fff9cb");
    })
    
           $("input").focusout(function () {
      $(this).css("background", "");
    })
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date joindate = null;
  try {
	  joindate = vendorVO.getVenJoinDate();
   } catch (Exception e) {
	   joindate = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=joindate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
</script>
</html>