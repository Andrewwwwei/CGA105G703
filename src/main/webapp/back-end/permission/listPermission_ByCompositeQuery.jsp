<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.permission.model.*"%>
<%@ page import="com.permissfunc.model.*"%>
<%
PermisFuncVO permisFuncVO = (PermisFuncVO) request.getAttribute("permisFuncVO");
%>
<!DOCTYPE html>
<html>
<head>
<script src="https://kit.fontawesome.com/33115e34cf.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
      margin-left: calc(var(--aside-width) + 10px);
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
    
    
    main.head table {
	color: black; 
	border-bottom: 1px solid black;
	font-size: 16px;
	width: 40%;
	border-collapse: collapse;
	position: absolute;
	left: 30%;
	table-layout: fixed;
	
	
}

main.head table th {
	border-bottom: 1px solid black;
	background-color: #8b9bb1;
	height:50px;
}

main.head table td {
	border-top: 1px solid #c9cacb;
	text-align: center;
	flex-direction: row;
	table-layout: fixed;

}

main.head table tr:nth-child(odd) {
background-color:#eff3f9;

}


main.head table tr:hover{
    background-color:#dde3eb !important;

}

main.head table td form {
	display: inline;
	margin: 3px;
}
    
    

</style>
</head>
<body>
<c:import url="/back-end/sidebar.jsp"></c:import>
<!-- ---------------------aside body區 --------------------->

	<div class="right" style="margin-bottom:100px;">

    <!-- ---------------------function body區 --------------------->
    <div class="function">
    <a href="<%=request.getContextPath()%>/back-end/permisFunc/PermisFuncListAll.jsp"> <i class="fa-solid fa-arrow-left fa-2x"></i></a>
			<FORM METHOD="post" ACTION="FuncServlet" name="form1">
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
 <div class="workplace" >
 
 
   
      <main class="head" id="table-1">
         <table id="table_id" class="display"  > 
          <thead>
          <tr>
            <th>編輯</th>
            <th>權限編號</th>
           
            <th>員工編號</th>
             </tr>
         </thead>
          <tbody>

    <c:forEach var="permissionVO" items="${listPermission_ByCompositeQuery}">
		<tr align='center' valign='middle' ${(permissionVO.funcId==param.funcId) ? 'bgcolor=#CCCCFF':''}><!--將修改的那一筆加入對比色而已-->       
        
            <td>
            
                <FORM METHOD="post" ACTION="FuncServlet">
			     <button type="submit" id="listb">刪除</button>
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


    
    
        	  $(document).ready( function () {
    $('#table_id').DataTable(); 
} );
 
  </script>
  
         
</body>
</html>