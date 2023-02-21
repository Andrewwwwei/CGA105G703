<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>
<%@ page import="com.vendorPic.model.*"%>


<%
VendorVO vendorVO = (VendorVO) request.getAttribute("vendorVO");
%>

<html>
<head>
<script src="https://kit.fontawesome.com/33115e34cf.js" crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <title>Document</title>

  <style>
   
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
      text-align: center;
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
      height:25px;
      border-radius: 6px;
      font-size: 16px;
      margin:2px;
      text-align: center;
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
      font-size: 16px;
      margin:10px;
    padding-left:3px;
   
     
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
      margin-top:5px;
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
    
           div.workplace div.pic{
   display:inline-block;
  height:140px;
   border: 2px solid #041427;
border-color:#dee4ec;

    }
    
    div.workplace img{
width:300px;height:200px;
margin-bottom:10px;
}
    


  </style>

</head>





<body>
<c:import url="/back-end/sidebar.jsp"></c:import>

  <!-- ---------------------aside body區 --------------------->
 
  <div class="right">

    <!-- ---------------------function body區 --------------------->
    <div class="function">

    
<a  href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp"> <i class="fa-solid fa-arrow-left fa-2x"  ></i></a>
<p>查詢/修改廠商資料</p>
      


    </div>
    <!-- ---------------------main body區 --------------------->
  

    <div class="workplace">
         <FORM METHOD="post"  ACTION="<%=request.getContextPath()%>/VenServlet">
 
   
    
      <main  >
  

       <label for="venid">廠商編號: </label> 
				<input type="text" name="venId"  id="disableset" value="<%=vendorVO.getVenId()%>"  style="width:180px;background-color:#b8bbbe  !important" readonly>
			    <label for="venid">廠商密碼: </label> 
			    <input type="password" name="venPw" value="<%=vendorVO.getVenPw()%>" style="width:180px;" disabled>
				<label for="name">廠商名稱: </label> 
				<input type="text" name="venName"  id="disableset" value="<%=vendorVO.getVenName()%>"  style="width:180px;" disabled>
				
				<label for="ventax">統一編號: </label> 
				<input type="text" name="venTaxNum"  id="disableset" value="<%=vendorVO.getVenTaxnum()%>" style="width:180px;" disabled>
				<br>
				
				<label for="venjoindate">加入日期: </label> 
				<input type="text" name="venJoinDate" class="f_date1"  value="<%=vendorVO.getVenJoinDate()%>" style="width:180px;" disabled>
				
				
			
				<label for="ventel">旅宿電話: </label> 
				<input type="text" name="venTel" value="<%=vendorVO.getVenTel()%>" style="width:180px;" disabled>
				

				
				<label for="venwin">聯繫窗口: </label> 
				<input type="text" name="venWin" value="<%=vendorVO.getVenWin()%>" style="width:180px;" disabled>
				
				<label for="venwintel">窗口電話: </label> 
				<input type="text" name="venWinTel"  value="<%=vendorVO.getVenWintel()%>" style="width:180px;" disabled>
				<br>
				
				<label for="venbank">銀行帳號: </label> 
				<input type="text" name="venBank" value="<%=vendorVO.getVenBank()%>"  style="width:180px;" disabled>
				
				<label for="venstatus">帳號狀態: </label> 
				<select name="venStatus" style="width:180px;font-size:16px; disable">
			    
<c:if test="${vendorVO.venStatus == 0}" var="true"><option value="0">未啟用</option></c:if>
<c:if test="${vendorVO.venStatus ==1}" var="true"><option value="1">啟用</option></c:if>
<c:if test="${vendorVO.venStatus == 2}" var="true"><option value="2">停用</option></c:if>


<c:if test="${vendorVO.venStatus != 0}" var="true"><option value="0">未啟動</option></c:if>
<c:if test="${vendorVO.venStatus != 1}" var="true"><option value="1">啟動</option></c:if>
<c:if test="${vendorVO.venStatus != 2}" var="true"><option value="2">停用</option></c:if>
				</select>
				
				
				
			    <label for="venscore">評分分數: </label> 
				<input type="text" name="venScore"  value="<%=vendorVO.getVenScore()%>" style="width:180px;" readonly>
							 
			    <label for="scorepeo">評分人數: </label> 
				<input type="text" name="venScorePeople"  value="<%=vendorVO.getVenScorePeople()%>" style="width:180px;background-color:#b8bbbe" readonly>
				   <br>
     <label for="venemail">電子信箱: </label> 
				<input type="text" name="venEmail"  id="disableset"value="<%=vendorVO.getVenEmail()%>" style="width:300px;" disabled>
        
								<label for="venlocation">地址: </label> 
				<input type="text" name="venLocation"  value="<%=vendorVO.getVenLocation()%>" style="width:300px;" disabled>
				<br>
			    
			<div class="art">    
			
				
				<label for="venintro">旅宿介紹: </label> <p>
				<textarea name="venIntro"
          rows="10"
          cols="80"
          required
          disabled>
<%=vendorVO.getVenIntro()%>
</textarea>
<br>
<label for="canpolicy">取消政策: </label> <p>
            
<textarea name="venCanPolicy"
          rows="10"
          cols="80"
          required
          disabled>
<%=vendorVO.getVenCanpolicy()%>
</textarea>
<br>
<label for="vennotice">入住須知: </label> <p>
<textarea name="venNotice"
          rows="10"
          cols="80"
          required
          disabled>
<%=vendorVO.getVenNotice()%>
</textarea>
	</div>  
      </main>
          <%-- 錯誤表列 --%>
   <c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>    
             <input type="hidden" name="action" value="update">
             
          
           <button type="button" class="adju">修改</button>

<button type="button"onclick="javascript:location.reload();" class="can" style="display:none"  >取消</button>



  
  
  <input type="hidden" name="action" value="update">
<button type="submit" class="confirm" style="display:none;width:100px;">確認修改</button>


    </form>
<div>
	<c:forEach var="venPic" items="${vendorPicVOList}">
	
	
	<div class="pic">
		<FORM METHOD="post"  ACTION="<%=request.getContextPath()%>/VenServlet">
		<button type="submit" style="margin-top:5px;font-size: 15px;width: 50px;height:25px;">刪除</button>
		<input type="hidden" name="action" value="deleteVenPic">
		<input type="hidden" name="venPicid" value="${venPic.venPicid}">
		</form>
		
		<img src="<%=request.getContextPath()%>/VenServlet?action=getOneVenPic&venPicid=${venPic.venPicid}" style="width:180px;height:100px;margin-top:-10px;">
		</div>
	
	</c:forEach>
</div>
    
      <div>


  <FORM METHOD="post"  ACTION="<%=request.getContextPath()%>/VenServlet"  enctype="multipart/form-data">
        <input type="file" name="upfile1" id="p1" multiple="multiple" style="border:none;" >
      <button type="submit" style="width:100px;">圖片上傳</button>
       <input type="hidden" name="action" value="venPicUp">
       <input type="hidden" name="venId" value="${vendorVO.venId}">
  </FORM>
  
  
  <div id="previewContainer">
				</div>
  </div>
  
  		


									
									

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

    
    var pic=1;
    $(".aside_list li").click(function () {
        $(this).css("background", "#041427").siblings().css("background", "");
      }) 
      
   
      

    

  </script>
  
  
  <script>
  const input = document.getElementById('p1');
  const previewContainer = document.getElementById('previewContainer');

  input.addEventListener('change', () => {
    previewContainer.innerHTML = '';
    for (let i = 0; i < input.files.length; i++) {
      const reader = new FileReader();
      reader.readAsDataURL(input.files[i]);
      reader.onload = () => {
        const preview = document.createElement('div');
        const img = document.createElement('img');
        const trash = document.createElement("i");
        trash.setAttribute('id','trash' + i);
        trash.className="fa-regular fa-trash-can trashcan";
        
        img.src = reader.result;

        trash.addEventListener('click', (e) => {
          const ID = e.target.id;
          const index = ID.substring(ID.length - 1);
          const dt = new DataTransfer();
          
          for (let i = 0; i < input.files.length; i++) {
            const file = input.files[i];
            if (parseInt(index) !== i)
          dt.items.add(file); // here you exclude the file. thus removing it.
      }
      
      input.files = dt.files // Assign the updates list
      preview.remove();
      const trashcan = document.querySelectorAll(".trashcan");
      for(let i = 0; i < trashcan.length; i++){
    		  trashcan[i].id = 'trash' + i;
      }
    });
        
        preview.appendChild(img);
        preview.appendChild(trash);
        previewContainer.appendChild(preview);
      };
    }
  });

  </script>
  
  
  

  
  
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
        $('.f_date1').datetimepicker({
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