<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.vendor.model.*"%>


<html>
<head>
<script src="https://kit.fontawesome.com/33115e34cf.js"
		crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
	src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.min.js"></script>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/simplePagination.js/1.6/jquery.simplePagination.min.js"></script>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

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
    div.function button {
      background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;
      margin-left: 30px;
    }
    
        div.function input {
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
    
         div.function a{

position: absolute;
left:20px;
top:10px;

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
 /* ========================================== main============================================ */
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
    
    main.head{
    positin:relative;
    }

    main.head table {

   
      color: black;
      font-size: 16px;
      width: 100%;
      border-collapse: collapse;
      table-layout:automatic;

    }

    main.head table th {
         background-color: #8b9bb1;
      
    }

    main.head table td {
    
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
    
      main.head table td form{
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


</style>

</head>

<body>
<c:import url="/back-end/sidebar.jsp"></c:import>

	<div class="right">

		<!-- ---------------------function body區 --------------------->
		<div class="function">
		
		<a  href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp"> <i class="fa-solid fa-arrow-left fa-2x"  ></i></a>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VenServlet" name="form1">
				<label for="search1">廠商編號: </label> 
				<input type="text" name="ven_id" style="width: 130px;">
					 <label for="search2">名稱: </label> 
					 <input type="text" name="ven_name" style="width: 130px;"> 
					 <label for="search3">統編: </label> 
					 <input type="text" name="ven_taxnum" style="width: 130px;">
					  <label for="search4">地區: </label> 
					  <input type="text" name="ven_location" style="width: 130px;">
					   <label for="search5">狀態: </label> 
					   <select name="ven_status">
					<option value=""></option>
					<option value="0">未啟用</option>
					<option value="1">啟用</option>
					<option value="2">停用</option>
				   </select>

				<button type="submit">查詢</button>

				<input type="hidden" name="action"
					value="venListAll_ByCompositeQuery">



				<c:if test="${not empty errorMsgs}">

					<c:forEach var="message" items="${errorMsgs}">
						<p style="color: red">${message}</p>
					</c:forEach>

				</c:if>
			</FORM>
		</div>
		<!-- ---------------------main body區 --------------------->
		<div class="workplace">
			<FORM ACTION="<%=request.getContextPath()%>/back-end/ven/VenAddPage.jsp">
				<button type="submit" name="add" class="add">新增</button>
			</FORM>
			<main class="head">
				<table id="table_id" class="display">
					<thead>
						<tr>
							<th>編輯</th>
							<th>廠商編號</th>
							<th>名稱</th>
							<th>統編</th>
							<th>電子信箱</th>
							<th>電話</th>
							<th>地址</th>
							<th>評分</th>
							<th>狀態</th>
							<th>房型</th>
						</tr>
					</thead>
					<%--            <%@ include file="page1.file" %>  --%>


					<tbody>
						<c:forEach var="vendorVO" items="${venListAll_ByCompositeQuery}">

							<tr>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/VenServlet">
										<button type="submit">編輯</button>
										<input type="hidden" name="venId" value="${vendorVO.venId}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								<td>${vendorVO.venId}</td>
								<td>${vendorVO.venName}</td>
								<td>${vendorVO.venTaxnum}</td>
								<td>${vendorVO.venEmail}</td>
								<td>${vendorVO.venTel}</td>
								<td>${vendorVO.venLocation}</td>
								<td>${vendorVO.venScore}</td>
								<c:if test="${vendorVO.venStatus == 0}" var="true">
									<td>未啟用</td>
								</c:if>
								<c:if test="${vendorVO.venStatus ==1}" var="true">
									<td>啟用</td>
								</c:if>
								<c:if test="${vendorVO.venStatus == 2}" var="true">
									<td>停用</td>
								</c:if>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/RoomBackEnd">
										<button type="submit">查看</button>
										<input type="hidden" name="venId" value="${vendorVO.venId}">
										<input type="hidden" name="action" value="goThisVenRoomType">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</main>
		</div>
	</div>
	<script>
	$(document).ready(function() {
			$('#table_id').DataTable({
				"pageLength" :5,
				lengthChange: false,
				 "language": {
					  "processing": "處理中...",
					  "loadingRecords": "載入中...",
					  "lengthMenu": "顯示_MENU_項結果",
					  "zeroRecords": "沒有符合的結果",
					  "info": "顯示第_START_至_END_項結果，共_TOTAL_項",
					  "infoEmpty": "顯示第 0 至 0 項結果，共 0 項",
					  "infoFiltered": "(從_MAX 項結果中過濾)",
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
</body>


</html>