<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.permissfunc.model.*"%>
<%
PermisFuncService permissSvc = new PermisFuncService();
List<PermisFuncVO> list = permissSvc.getAllPermisFunc();
pageContext.setAttribute("list", list);
%>
<%
PermisFuncVO permisFuncVO = (PermisFuncVO) request.getAttribute("permisFuncVO");
%>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@10.0.0/dist/sweetalert2.min.css">
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/sweetalert2@10/dist/sweetalert2.min.css">
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

 .addarea{ 
 float:left; 
position:absolute; 
  z-index: 6;
left:20px;
top:3px;


 } 
 
                div.workplace input {
      background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 16px;
        padding-left:8px;

    }

</style>
</head>

<body>
<c:import url="/back-end/sidebar.jsp"></c:import>
	
		<!-- ---------------------function body區 --------------------->
		<div class="function">
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
		<div class="right">
		<!-- ---------------------main body區 --------------------->

		<div class="workplace">

			<c:if test="${not empty funcDeleteErr}">

				<c:forEach var="message" items="${funcDeleteErr}">
					<p style="color: red">${message}</p>
				</c:forEach>

			</c:if>

		        <div class= addarea>
				<button type="button" class="add" style="width:120px;">新增權限</button>
					<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FuncServlet" name="venform">
					<div class="addfunc" style="display:none">
					<label for="funcName">新增權限名稱: </label> 
					<input type="text" name="funcName" 
						value="<%=(permisFuncVO == null) ? "" : permisFuncVO.getFuncName()%>" id="funcName"
						style="width: 150px;" /> <p>
					<button type="submit" style="width:80px;">確認新增</button>
					
				<input type="hidden" name="action" value="insert">
					<button type="button"onclick="javascript:location.reload();" >取消</button>
				
				</div>
			</FORM>
			
						  <%-- 錯誤表列 --%>
   <c:if test="${not empty funcAddErrL}">
	
	
		<c:forEach var="message" items="${funcAddErrL}">
			<span style="color: red">${message} /</span>
		</c:forEach>
	
</c:if> 
			</div>		
			<main class="head">
				<table id="func_tableid">
			<thead >
					<tr>
						<th>調整</th>
						<th>權限編號</th>
						<th>名稱</th>
					</tr>
			</thead>		
					<tbody>
						<c:forEach var="permisFuncVO" items="${list}">

							<tr>
								<td style="height:40px; ">
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FuncServlet"  id="deleteForm${permisfuncVO.funcId}">
										<button type="submit" style="height:25px;" id="delsub" onclick="deleteData('deleteForm${permisfuncVO.funcId}')">刪除</button>
										<input type="hidden" name="funcId"
											value="${permisFuncVO.funcId}"> <input type="hidden"
											name="action" value="delete">
									</FORM>

									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/FuncServlet">
										<button type="submit"  style="height:25px;">查看</button>
										<input type="hidden" name="funcId"
											value="${permisFuncVO.funcId}"> <input type="hidden"
											name="action" value="getOneFunc_For_Update">
									</FORM>



								</td>

								<td >${permisFuncVO.funcId}</td>
								<td>${permisFuncVO.funcName}</td>


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
			$(".aside_list li").click(
					function() {
						$(this).css("background", "#041427").siblings().css(
								"background", "");
					});
			
			$(".add").click(
					function() {
						$(".add").css('display','none');
						 $(".addfunc").css('display','block');
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