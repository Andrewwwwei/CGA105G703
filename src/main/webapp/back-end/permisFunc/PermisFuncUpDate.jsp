<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.permissfunc.model.*"%>
<%@ page import="com.permission.model.*"%>

<%
PermisFuncVO permisFuncVO = (PermisFuncVO) request.getAttribute("permisFuncVO");
PermissionService permissionSvc = new PermissionService();
%>



<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.0.0/dist/sweetalert2.min.css">

<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">

<script src="https://kit.fontawesome.com/33115e34cf.js"
		crossorigin="anonymous"></script>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Accordion - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<%--   <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/permisFunc/PermisFunc.css"> --%>
  <title>Document</title>
  <style type="text/css">
  a#permisscontrol {
	background-color: #041427;
	color: white;
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

    div.function label {
      margin-left: 18px;
      margin-right: 8px;
    }

   div.function button {
      margin-left: 30px;
      background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;
    }

    div.function select {
    background-color: transparent;
     border: 2px solid #041427;
      width: 130px;
      height:25px;
      border-radius: 6px;
      font-size: 16px;
      

    }
    
         div.function p {
 
      font-size:25px;
       font-family: 'Noto Sans TC', sans-serif;
       color:#002e65;

    }
    
               div.function input {
      background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;
       padding-left:8px;

    }
    
          div.function button:hover{
     background-color:#041427;
    color:white;
        }
        
             div.function a{
position: absolute;
left:20px;
top:10px;
    }
        

/* ==========================================主要工作區============================================ */
  div.workplace {
      margin-left: calc(var(--aside-width));
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
      margin-left: 10px;
      margin-right: 8px;
    }

       div.workplace button{
     background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;
      
    }

 div.workplace button:hover{
    background-color:#041427;
    color:white;
    }
    
         div.workplace input {
      background-color: transparent;
      border: 2px solid #041427;
      width: 130px;
      border-radius: 6px;
      font-size: 16px;
        padding-left:8px;
       text-align: center;

    }


main.head table {
	color: black; 
	border-bottom: 1px solid black;
	font-size: 16px;
	width: 30%;
	border-collapse: collapse;
	position: absolute;
	left: 35%;
/* 	table-layout: fixed; */
	
}

main.head table th {
border-bottom: 1px solid black;
	background-color: #8b9bb1;
	height:40px;
}

main.head table td {
	border-top: 1px solid #e3e9ef;
	text-align: center;
	flex-direction: row;
	table-layout: fixed;
	height:20px;
	

}

main.head table tr:nth-child(odd) {
background-color:#eff3f9;

}


main.head table tr:hover{
    background-color:#dde3eb !important;

main.head table td form {
	display: inline;
	margin: 3px;
}

main.head table td form {
	display: inline;
	margin: 3px;
}

 .addarea{ 
 float:left; 
position:absolute; 
  z-index: 6;
left:0;
top:3px;


 } 

.addemp{
margin-top:50px;

}

</style>
</head>

<body>
<c:import url="/back-end/sidebar.jsp"></c:import>
	
	
	<div class="right">
		<!-- ---------------------function body區 --------------------->
		<div class="function">
<a  href="<%=request.getContextPath()%>/back-end/permisFunc/permisFuncListAll.jsp"> <i class="fa-solid fa-arrow-left fa-2x"  ></i></a>		
		 <a  href="<%=request.getContextPath()%>/back-end/permisFunc/PermisFuncListAll.jsp"> 
     <i class="fa-solid fa-arrow-left fa-2x"  ></i></a>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FuncServlet" name="form1">
				<label for="search1">員工編號: </label> 
				<input type="text" name="emp_no" style="width: 130px;"> 
				<label for="search2">權限編號: </label> 
				<input type="text" name="func_id" style="width: 130px;">

				<button type="submit">查詢</button>

				<input type="hidden" name="action" value="listPermission_ByCompositeQuery">

                <c:if test="${not empty QueryerrorMsg}">

	    <c:forEach var="message" items="${QueryerrorMsg}">
			<p style="color:red" >${message}</p>
		</c:forEach>

</c:if>


			</FORM>
			
			    
		</div>

       <!-- ---------------------main body區 --------------------->
<div class="workplace">

    <main class="head">

         <FORM METHOD="post"  ACTION="FuncServlet" name="funcform" class="per" >
      
        
        
        
        <label for="funcid" style="margin-right:10px">權限編號: </label> 
				<input type="TEXT"  name="funcId" style="margin-right:10px;background-color:#efefef;"
			 value="<%=permisFuncVO.getFuncId()%>" readonly/>
			  <label for="funcname"  style="margin-right:10px" ;>權限名稱: </label> 
				<input type="TEXT" name="funcName" style="margin-right:10px;background-color:#efefef;" id="namechange"  class="color"
			 value="<%=permisFuncVO.getFuncName()%>" disabled /><p>
       
         
				
				
			
			
			
			  <%-- 錯誤表列 --%>
   <c:if test="${not empty funcUpdateErr}">

	<ul>
		<c:forEach var="message" items="${funcUpdateErr}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>    
<p> 
 <button type="button" class="adju" style="width:100px;">修改權限</button>
  <input type="hidden" name="action" value="funcUpdate">
<button type="submit" class="confirm" style="display:none;width:100px;">確認修改</button>
<button type="button"onclick="javascript:location.reload();" 
class="canchange"  style="margin-right:5px ; display:none">取消</button>
</FORM>


   <div class="addemp" style="margin-top:50px;">     	
  <p style="font-size:18px;margin-top:30px">-----    員工列表    -----</p>
 
  
  		<button type="button" class="add" style="width:120px;">新增員工權限</button>
					<FORM METHOD="post" ACTION="FuncServlet">
					<div class="addpermis" style="display:none">
					<label for="empno">新增員工編號: </label> 
					<input type="text" name="empNo"
						style="width: 150px;" oninvalid="setCustomValidity('請輸入員工編號');" oninput="setCustomValidity('');" /> 
					<button type="submit" style="width:80px;">確認新增</button>
					
				<input type="hidden" name="action" value="addEmpPermis">
				<input type="hidden" name="empfuncId"  value="<%=permisFuncVO.getFuncId()%>">
				<input type="hidden" name="empfuncName"  value="<%=permisFuncVO.getFuncName()%>">
					<button type="button"onclick="javascript:location.reload();" >取消</button>
				
				</div>
				
					
			</FORM>
			
		</div>	
			  <%-- 錯誤表列 --%>
   <c:if test="${not empty empAddErrL}">
	
	<ul>
		<c:forEach var="message2" items="${empAddErrL}">
			<span style="color: red">${message2} </span>
		</c:forEach>
	</ul>
</c:if>    


<table > 
          <thead>
          <tr>
            <th style="width:90px">編輯</th>
            <th>權限編號</th>
           
            <th>員工編號</th>
             </tr>
         </thead>
          <tbody>
<c:forEach var="permissionVO" items="${permissionVO}">
        <tr>
            <td>
            
                <FORM METHOD="post" ACTION="FuncServlet" style="margin:5px;">
			     <button type="submit" id="listb" >刪除</button>
			     <input type="hidden" name="funcId"  value="${permissionVO.funcId}">
			     <input type="hidden" name="empNo"  value="${permissionVO.empNo}">
			     <input type="hidden" name="action" value="deletPermission"></FORM>
			  </td>
  
            
            <td>${permissionVO.funcId}</td>
           
            <td>${permissionVO.empNo}</td>
             </tr>
             </c:forEach>

           
       </tbody> 

        </table>
        </main>
  </div>
      <img class="permis" src="<%=request.getContextPath()%>/back-end/permisFunc/images/PermissionPic.png"
        	style="width:800px; position:absolute; right:20px;bottom:0px;">
    </div>

     <script>
    		$(".add").click(
					function() {
						$(".add").css('display','none');//表示设置为可用
						 $(".addpermis").css('display','block');//表示设置为可用
	});
    		
    		
    		  $(function () {


    			  $(".adju").click(function () {
    			    $("input").attr("disabled", false);
    			    $(this).hide();
    			    $(".canchange").toggle();
    			    $(".confirm").toggle();
    			    $(".color").css("background", "");
    			    
    			  
    			  });
    			  

    			  $(".canchange").click(function () {

    			    $(".adju").toggle();
    			    $(".canchange").toggle();
    			    $(".confirm").toggle();
    			    $("input").prop("disabled", true);
    			    $(".color").css("background", "#efefef");
    			  });
    			});
    		  
    		  
    		  
    		  $(".color").focus(function () {
    		      $(this).css("background", "#f7f6ea");
    		    })
    		    
    		       $(".color").focusout(function () {
    		      $(this).css("background", "");
    		    })

    		
  
  </script>
</body>

</html>