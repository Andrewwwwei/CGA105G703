<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>


<%
    EmpService empSvc = new EmpService();
    List<EmpVO> list = empSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<script src="https://kit.fontawesome.com/33115e34cf.js"crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.0.0/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/simplePagination.js/1.6/jquery.simplePagination.min.js"></script>
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

    div.function>label {
      margin-left: 18px;
      margin-right: 8px;
    }

    div.function button {
     background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;
      margin:2px;
    }
    
           div.function input {
      background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;

    }


    
        div.function form {
       margin:0px;
        }
        
         div.function p {
       margin:0px;
        text-align: center;
       
        }
        
        div.function button:hover{
     background-color:#041427;
    color:white;
        }
        
            div.function select {
    background-color: transparent;
     border: 2px solid #041427;
      width: 130px;
      height:25px;
      border-radius: 6px;
      font-size: 16px;
      


    }

    /* ==========================================主要工作區============================================ */
       div.workplace {
      margin-left: calc(var(--aside-width) + 30px);
      /* margin-right: 20px; */
      margin-top: 25px;
 
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
    
    main.empTable{
    positin:relative;
    }

    main.empTable table {

   
      color: black;
      font-size: 16px;
      width: 100%;
      border-collapse: collapse;
      table-layout:automatic;
      z-index:3;

    }

    main.empTable table th {
         background-color: #8b9bb1;
      
    }

    main.empTable table td {
    
      text-align: center;


    }
    
        main.empTable table td {
    
      text-align: center;


    }
    
    tr.hover.even:hover{
    background-color:#dde3eb !important;
    }
    
    tr.hover.odd:hover{
    background-color:#dde3eb !important;
     }   


   
    
     table.dataTable tbody th, table.dataTable tbody td { 
    padding: 5px 5px; 
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
    
      main.empTable table td form{
      display:inline;
  
}
  
 .dataTables_length{
 margin-bottom:10px;

}

 div.workplace select{
width:80px;
}

 div.workplace option{
text-align: center;

}

.dataTables_wrapper .dataTables_paginate .paginate_button {

    color: #333 !important;
    border: 1px solid transparent;
    border-radius: 30px;
    background-color: #dee4ec;
    padding: 4px 13px;
    margin-left: 5px;
}


.dataTables_wrapper .dataTables_paginate .paginate_button.current, .dataTables_wrapper .dataTables_paginate .paginate_button.current:hover {
    color: white !important;
  
    background: #8b9bb1;

}

.dataTables_wrapper .dataTables_paginate {

    padding-top: 15;
     
}

.dataTables_wrapper .dataTables_info {

    padding-top: 20px;
}

.dataTables_wrapper .dataTables_filter {
 margin-bottom:10px;

}

 button.add{ 
 float:left; 
position:absolute; 
  z-index: 6;
left:0;
top:13px;


 } 
 
 table.dataTable.stripe tbody tr.odd, table.dataTable.display tbody tr.odd {
    background-color: #eff3f9;
}



table.dataTable.display tbody tr.odd>.sorting_1, table.dataTable.order-column.stripe tbody tr.odd>.sorting_1 {
    background-color:#eff3f9;
}


img.emp{
position:absolute;
bottom:0px;
z-index:0;
right:400px;
}

.swal2-title {
    font-size: 18px;
    color: #041427;
  }
  
  .swal2-content {
    font-size: 14px;
    color: blue;
  }
  
  .swal2-confirm {
    background-color: yellow;
  }



  </style>

</head>

<body>
<c:import url="/back-end/sidebar.jsp"></c:import>

 
  <div class="right">

    <!-- ---------------------function body區 --------------------->
    <div class="function">
         <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" name="form1" >
        <label for="search1">員工編號: </label>
        <input type="text" name="emp_no" style="width:130px;">
         
         <label for="search2">員工姓名: </label>
        <input type="text" name="emp_name"  style="width:130px;">
        
          <label for="search3">身分證字號: </label>
        <input type="text" name="emp_idnum" style="width:130px;">
        
         <label for="search4">員工部門: </label>
<!--         <select name="empApt"> -->
        <select name="emp_dep">
          <option value=""></option>      
          <option value="客服部">客服部</option>
          <option value="訂房管理部">訂房管理部</option>
          <option value="論壇管理部">論壇管理部</option>
          <option value="網站管理部">網站管理部</option>
          <option value="揪團管理部">揪團管理部</option>
          <option value="行程管理部">行程管理部</option>
        </select>
          
          <label for="search5">狀態: </label>
<!--         <input type="text" name="emp_Status" style="width:130px;"> -->
        <select name="emp_Status">
          <option value=""></option>      
          <option value="0" >未啟用</option>
          <option value="1"  >啟用</option>
          <option value="2" >停用</option>
        </select>
        
       <button type="submit">查詢</button>
        
         <input type="hidden" name="action" value="listEmps_ByCompositeQuery">

     

        <c:if test="${not empty errorMsgs}">

	    <c:forEach var="message" items="${errorMsgs}">
			<p style="color:red" >${message}</p>
		</c:forEach>

</c:if>
 </FORM>
    </div>

    <!-- ---------------------main body區 --------------------->
    
    
   
    <div class="workplace">
    

    
     <c:if test="${not empty deletErr}">
     
     



	    <c:forEach var="message" items="${deletErr}">
			<p style="color:red" >${message}</p>
		</c:forEach>

</c:if>
    <FORM ACTION="<%=request.getContextPath()%>/back-end/emp/Empaddpage.jsp" >
   <button type="submit" name="add" class="add" style="width:80px;">新增員工</button></FORM> 
      <main class="empTable" >
        <table id="table_id" class="display"> 
        <thead>
          <tr >
            <th>編輯</th>
            <th>員編</th>
            <th>姓名</th>
            <th>部門</th>
            <th>職稱</th>
            <th>身分證</th>
            <th>電子信箱</th>
            <th>電話</th>
            <th>入職日</th>
            <th>狀態</th>
          </tr>
</thead>
<tbody>
	       <c:forEach var="empVO" items="${list}">
	    
         
          <tr class="hover">
            <td>
              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet">
              <button type="submit">修改</button> 
               <input type="hidden" name="empNo"  value="${empVO.empNo}">
			   <input type="hidden" name="action"	value="getOne_For_Update"></FORM>            
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet"  id="deleteForm${empVO.empNo}">
			     <button type="submit" id="delsub" onclick="deleteData('deleteForm${empVO.empNo}')">刪除</button>
			     <input type="hidden" name="empNo"  value="${empVO.empNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			
			     
			    
			  </td>
             
         
            <td>${empVO.empNo}</td>
            <td>${empVO.empName}</td>
            <td>${empVO.empDep}</td>
            <td>${empVO.jobTitle}</td>
            <td>${empVO.empIdnum}</td>
            <td>${empVO.empEmail}</td>
            <td>${empVO.empTel}</td>
            <td>${empVO.empHiredate}</td>
            <c:if test="${empVO.empStatus == 0}" var="true"><td>未啟用</td></c:if>
     
             <c:if test="${empVO.empStatus ==1}" var="true"><td>啟用</td></c:if>
            
             <c:if test="${empVO.empStatus == 2}" var="true"> <td>停用</td></c:if>
           

              
          </tr>
        </c:forEach>
        </tbody>

        </table>
       

      </main>
      
   

   
    </div>
   	<img class="emp" src="<%=request.getContextPath()%>/back-end/emp/images/Emp.png"
        	style="width:40%;">
  </div>

  <script>


    
		$(document).ready(function() {
			$('#table_id').DataTable({
				"pageLength" :8,
// 				"lengthMenu" : [5,10],
				lengthChange: false,
				 "language": {
					  "processing": "處理中...",
					  "loadingRecords": "載入中...",
					  "lengthMenu": "顯示_MENU_項結果",
					  "zeroRecords": "沒有符合的結果",
					  "info": "顯示第_START_至_END_項結果，共_TOTAL_項",
					  "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
					  "infoFiltered": "(從_MAX_項結果中過濾)",
					  "infoPostFix": "",
					  "search": "搜尋:",
					  "paginate": {
					   "first": "第一頁",
					   "previous": "上一頁",
					   "next": "下一頁",
					   "last": "最後一頁"
					  },
					  "aria": {
					   "sortAscending": ": 升冪排列",
					   "sortDescending": ": 降冪排列"
					  },
					 },
			});
		});
    

  </script>
  



<script>
document.querySelectorAll('#delsub').forEach(function(elem) {
    elem.addEventListener('click', function(event) {
      event.preventDefault();
      var form = event.target.parentNode;
      Swal.fire({
        title: '確定要刪除嗎？',

        
        showCancelButton: true,
        confirmButtonColor: '#002e65',
        confirmButtonTextColor:"#1f1e12",
        cancelButtonColor: '#53051a',
        confirmButtonText: '確認',
        cancelButtonText: '取消'
      }).then((result) => {
        if (result.value) {
          form.submit();
        }
      })
    });
  });
  
  
  
  
  
  
</script>






   
    
</body>


</html>