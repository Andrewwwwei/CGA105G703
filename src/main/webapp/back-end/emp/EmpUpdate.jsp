<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>


<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<script src="
https://cdn.jsdelivr.net/npm/sweetalert2@11.7.1/dist/sweetalert2.all.min.js
"></script>
	<script src="https://kit.fontawesome.com/33115e34cf.js"
		crossorigin="anonymous"></script>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>

  <style type="text/css">
    @import url(https://fonts.googleapis.com/earlyaccess/notosanstc.css);

     a#empcontrol{
    background-color:#041427;
    color:white;
    }
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

    main.empTable table {

   
      color: black;
      border: 1px solid black;
      font-size: 16px;
      width: 80%;
      border-collapse: collapse;
      table-layout:automatic;
      

    }

    main.empTable table th {
      border: 1px solid black;
      background-color: #8b9bb1;
      width:140px;
    }

    main.empTable table td {
      border: 1px solid black;
       width:140px;
      text-align: center;
   


    }
        main.empTable table input {
     
      text-align: center;


    }
    
            main.empTable table select {
     
      text-align: center;


    }
       div.workplace button{
     background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;
      margin:2px;
    }
    div.workplace button:hover{
    background-color:#041427;
    color:white;
    }
    
    img.emp{
position:absolute;
bottom:0px;
z-index:0;
right:400px;
}
    

    
  </style>
</head>

<body>
<c:import url="/back-end/sidebar.jsp"></c:import>
  <div class="right">

    <!-- ---------------------function body區 --------------------->
    <div class="function">

			<a
				href="<%=request.getContextPath()%>/back-end/emp/Emplistallpage.jsp">
				<i class="fa-solid fa-arrow-left fa-2x"></i>
			</a>
			<p>修改員工資料</p>
     </div>
    <!-- ---------------------main body區 --------------------->
  

   
    <div class="workplace">
    
         <FORM METHOD="post"  ACTION="<%=request.getContextPath()%>/EmpServlet" name="empform">
      <main class="empTable" >

        <table>
          <tr>
            <th>編輯</th>
            <th>員編</th>
            <th>密碼</th>
            <th>姓名</th>            
            <th>部門</th>
            <th>職稱</th>
            <th>身分證</th>
            <th>電子信箱</th>
            <th>電話</th>
            <th>入職日</th>
            <th>狀態</th>
          </tr>

          <tr>
            <td>
              <button type="submit" id="submitBtn">確認</button>
            </td>
            
            <td><input type="TEXT"  style="width:110px;   font-size:16px; height:30px; background-color:#efefef;"
			 value="<%=empVO.getEmpNo()%>" disabled/></td>

            <td><input type="password" name="empPw" style="width:110px;   font-size:16px; height:30px;"
			 value="<%=empVO.getEmpPw()%>" /></td>
            <td><input type="TEXT" name="empName" style="width:110px;   font-size:16px; height:30px;"
			 value="<%=empVO.getEmpName()%>" /></td>
							<td>
							<select name="empDep" style="width:110px;   font-size:16px; height:30px;">
								<option value="<%=empVO.getEmpDep()%>" / ><%=empVO.getEmpDep()%></option>
								
								<option value="行政部">行政部</option>
								<option value="商品部">商品部</option>
								<option value="管理部">管理部</option>
								<option value="開發部">開發部</option>
								<option value="客服部">客服部</option>

							</select></td>
							<td><input type="TEXT" name="empJobTitle" style="width:110px;   font-size:16px; height:30px;"
			 value="<%=empVO.getJobTitle()%>" /></td>
            <td><input type="TEXT" name="empIdnum" style="width:110px;   font-size:16px; height:30px;"
			 value="<%=empVO.getEmpIdnum()%>" /></td>
            <td><input type="TEXT" name="empEmail" style="width:160px;   font-size:16px; height:30px;"
			 value="<%=empVO.getEmpEmail()%>" /></td>
            <td><input type="TEXT" name="empTel"  style="width:110px;   font-size:16px; height:30px;"
			 value="<%=empVO.getEmpTel()%>" /></td>

            <td><input type="TEXT" id="f_date1" name="empHireDate" style="width:101px;   font-size:16px; height:30px;"></td>
            <td><select name="empStatus" style="width:110px; text-align: center;   font-size:16px; height:30px;"> 

    
      
<c:if test="${empVO.empStatus == 0}" var="true"><option value="0">未啟用</option></c:if>
<c:if test="${empVO.empStatus ==1}" var="true"><option value="1">啟用</option></c:if>
<c:if test="${empVO.empStatus == 2}" var="true"><option value="2">停用</option></c:if>


<c:if test="${empVO.empStatus != 0}" var="true"><option value="0">未啟動</option></c:if>
<c:if test="${empVO.empStatus != 1}" var="true"><option value="1">啟動</option></c:if>
<c:if test="${empVO.empStatus != 2}" var="true"><option value="2">停用</option></c:if>

        </select></td>

        </tr>


        </table>

      </main>
          <%-- 錯誤表列 --%>
   <c:if test="${not empty errorMsgs}">
	
	
		<c:forEach var="message" items="${errorMsgs}">
			<span style="color: red">${message} /</span>
		</c:forEach>
	
</c:if>    
             <input type="hidden" name="action" value="update">
             <input type="hidden" name="empNo" value="<%=empVO.getEmpNo()%>">  
      
    </form>
    </div>
	<img class="emp" src="<%=request.getContextPath()%>/back-end/emp/images/Emp.png"
        	style="width:40%;">
  </div>

  <script>

    $(".aside_list li").click(function () {
      $(this).css("background", "#041427").siblings().css("background", "");
    })
    
        $("input").focus(function () {
      $(this).css("background", "#fffee9");
    })
    
       $("input").focusout(function () {
      $(this).css("background", "");
    })
    
            $("select").focus(function () {
      $(this).css("background", "#fffee9");
    })
    
       $("select").focusout(function () {
      $(this).css("background", "");
    })
   
    
  </script>
  
  
 
 
</body>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date hiredate = null;
  try {
	    hiredate = empVO.getEmpHiredate();
   } catch (Exception e) {
	    hiredate = new java.sql.Date(System.currentTimeMillis());
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
		   value: '<%=hiredate%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
        
    
</script>
</html>