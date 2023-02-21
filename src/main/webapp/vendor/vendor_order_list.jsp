<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.Users.model.*"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.orderDetail.model.*"%>

<jsp:useBean id="users" scope="page" class="com.Users.model.UsersService" />
<jsp:useBean id="room" scope="page" class="com.room.model.RoomService" />
<jsp:useBean id="orderDetail" scope="page" class="com.orderDetail.model.OrderDetailService" />

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- ===================== Bootstrap 5 CSS ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- ===================== fontawesome ========================== -->
    <script src="https://kit.fontawesome.com/37ac1c7b87.js" crossorigin="anonymous"></script>

    <!-- ===================== jquery ========================== -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    
    <!-- ===================== lottie ========================== -->
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>

    <style>
    .order:hover{
            cursor: pointer;
    		background-color: #DCE2EB;
        }
    .trbg {
            background-color: #F3F5F8;
        }
    </style>
    <title>訂單管理</title>
</head>

<body>
    <c:import url="/vendor/company_header.jsp" ></c:import>

    <!-- header -->
    <div class="container">
        <div class="text-dark fs-5 fw-bold my-3">訂單管理</div>

        <!-- nav-tabs -->
        <div class="row justify-content-between">
            <div class="col-8">
                <div class="nav nav-tabs">
                	<form method="post" action="<%=request.getContextPath()%>/RoomOrder">
					<input type="hidden" name="action" value="getAllVendorOrder">
					<c:if test="${orderTab == 4}">
                    <button class="nav-link active" type="submit" data-bs-toggle="tab">所有訂單</button>
                    </c:if>
					<c:if test="${orderTab !=  4}">
                    <button class="nav-link" type="submit" data-bs-toggle="tab">所有訂單</button>
                    </c:if>
                    </form>
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
					<input type="hidden" name="action" value="getfutureVendorOrder">
					<c:if test="${orderTab == 0}">
                    <button class="nav-link active" type="submit" data-bs-toggle="tab">待入住</button>
                    </c:if>
					<c:if test="${orderTab !=  0}">
                    <button class="nav-link" type="submit" data-bs-toggle="tab">待入住</button>
                    </c:if>
                    </form>
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
					<input type="hidden" name="action" value="getLivingVendorOrder">
					<c:if test="${orderTab == 1}">
                    <button class="nav-link active" type="submit" data-bs-toggle="tab">入住中</button>
                    </c:if>
					<c:if test="${orderTab !=  1}">
                    <button class="nav-link" type="submit" data-bs-toggle="tab">入住中</button>
                    </c:if>
                    </form>
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
					<input type="hidden" name="action" value="getFinishVendorOrder">
					<c:if test="${orderTab == 2}">
                    <button class="nav-link active" type="submit" data-bs-toggle="tab">已完成</button>
                     </c:if>
					<c:if test="${orderTab !=  2}">
                    <button class="nav-link" type="submit" data-bs-toggle="tab">已完成</button>
                     </c:if>
                    </form>
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
					<input type="hidden" name="action" value="getCancelVendorOrder">
					<c:if test="${orderTab == 3}">
                    <button class="nav-link active" type="submit" data-bs-toggle="tab">已取消</button>
                    </c:if>
					<c:if test="${orderTab != 3}">
                    <button class="nav-link" type="submit" data-bs-toggle="tab">已取消</button>
                    </c:if>
                    </form>
                </div>
            </div>
            <!-- search -->
            <div class="col-4">
                <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
                <div class="input-group">
                    <input type="text" class="col-2 form-control" name="keyword" placeholder="請輸入訂單編號或姓名" maxlength="7">
                    <input type="hidden" name="action" value="getOneOrder">
                    <button class="btn btn-outline-secondary" type="submit">查詢</button>
                </div>
                </form>
            </div>
        </div>
		<c:if test="${empty roomOrderList}">
		<h1 class="text-center mt-5">查無訂單</h1>
		</c:if>
		<c:if test="${not empty roomOrderList}">
        <!-- order tables -->
        	<table class="table text-center align-middle" id="table" style="display: none;">
                <thead class="table-primary">
                    <tr>
                        <th>訂單編號</th>
                        <th>住客姓名</th>
                        <th>入住日期</th>
                        <th>退房日期</th>
                        <th>訂單狀態</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="roomOrderVO" items="${roomOrderList}">
                    <tr class="order">
                        <td><b>${roomOrderVO.orderId}</b></td>
                        <td><b>${roomOrderVO.customerName}</b></td>
                        <td>${roomOrderVO.checkinDate}</td>
                        <td>${roomOrderVO.checkoutDate}</td>
                        <c:if test="${roomOrderVO.orderStatus == 0}">
                        	<td><i class="fa-regular fa-circle"></i> 待入住</td>
                        </c:if>
                        <c:if test="${roomOrderVO.orderStatus == 1}">
                        	<td><i class="fa-solid fa-bed"></i> 入住中</td>
                        </c:if>
                        <c:if test="${roomOrderVO.orderStatus == 2}">
                        	<td><i class="fa-solid fa-circle-check text-success"></i> 已完成</td>
                        </c:if>
                        <c:if test="${roomOrderVO.orderStatus == 3}">
                        	<td><i class="fa-solid fa-circle-xmark text-danger"></i> 已取消</td>
                        </c:if>
                    </tr>
                    <tr class="detail" style="display: none;">
                        <td colspan="5">
                            <div class="row d-flex justify-content-center my-2 w-75 mx-auto text-start">
                                <div class="col-1"></div>
                                <div class="col-5">
                                    <h5>
                                        <i class="fa-solid fa-user"></i> 住客資料
                                    </h5>
                                    <div class="my-2 ps-4"><b>姓名：</b>${roomOrderVO.customerName}</div>
                                    <div class="my-2 ps-4"><b>電話：</b>${roomOrderVO.customerPhone}</div>
                                    <div class="my-2 ps-4"><b>Email：</b>${roomOrderVO.customerEmail}</div>
                                </div>
                                <div class="col-5">
                                    <h5>
                                        <i class="fa-solid fa-circle-user"></i> 會員資料
                                    </h5>
                                    <div class="my-2 ps-4"><b>訂單成立日期：</b>${(roomOrderVO.orderTime).toLocalDate()}</div>
                                    <div class="my-2 ps-4"><b>會員編號：</b>${roomOrderVO.userId}</div>
                                    <div class="my-2 ps-4"><b>會員姓名：</b>${users.getOneUser(roomOrderVO.userId).userName}</div>
                                </div>
                            </div>
                            <table class="table w-50 mx-auto text-center align-middle">
                                <thead>
                                    <tr style="background-color: #F1EBFF;">
                                        <th class="col-5">房型</th>
                                        <th class="col-2">間數</th>
                                        <th class="col-5"></th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <c:forEach var="orderDetailVO" items="${orderDetail.getByOrderID(roomOrderVO.orderId)}">
                                    <tr>
                                        <td>${room.getOneRoom(orderDetailVO.roomId).roomName}</td>
                                        <td>${orderDetailVO.roomAmount}間</td>
                                        <td></td>
                                    </tr>
                                 </c:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td class="text-end fw-bold">付款金額&emsp;$<fmt:formatNumber type="number" pattern="#,###" value="${roomOrderVO.orderChargeDiscount}" /></td>
                                    </tr>
                                </tfoot>
                            </table>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
		<c:if test="${roomOrderList.size() > 5}">
		<!-- paganition -->
        <nav>
             <ul class="pagination justify-content-center">
                <li class="page-item disabled" id="prev"><button class="page-link">上一頁</button></li>
                <li class="page-item" id="next"><button class="page-link">下一頁</button></li>
            </ul>
        </nav>
        </c:if>
        </c:if>
        </div>
        
	<c:import url="/vendor/company_footer.jsp" ></c:import>

    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>

    <script>
    <c:if test="${not empty roomOrderList}">
        $(document).ready(function () {
        	<c:if test="${orderTab == 5 && roomOrderList.size() == 1}">
        	$('.detail').show();
        	</c:if>
            $('.order').click(function () {
                $(this).next('.detail').toggle();
            });
            for (let i = 0; i < $('.order').length; i++) {
                if (i % 2 === 1) {
                    $('.order')[i].classList.add('trbg');
                }
            }
            $('#table').show();
        });
    </c:if>
        <c:if test="${roomOrderList.size() > 5}">
        // <!-- ====================== 控制分頁 =========================== -->
        $(function () {
            let $table = $("#table");
            let currentPage = 0; //當前頁默認值為0  
            let pageSize = 5; //每一頁顯示的數目  
            $table.bind('paging', function () {
                $table.find('.order').hide().slice(currentPage * pageSize, (currentPage + 1) * pageSize).show();
                $('.detail').hide();
            });
            $table.trigger("paging");
            let sumRows = $table.find('.order').length;
            let sumPages = Math.ceil(sumRows / pageSize); //總頁數  
            
            let next = $('#next');   
            for (let pageIndex = 0; pageIndex < sumPages; pageIndex++) {
                $('<li class="page-item" onclick="changActive(this)"><button class="page-link">' + (pageIndex + 1) + '</button></li>').bind("click", { "newPage": pageIndex }, function (event) {
                    currentPage = event.data["newPage"];
                    $table.trigger("paging");
                    //觸發分頁函數  
                }).insertBefore(next);
            }
            $('#prev').bind("click", function (event) {
                    if(currentPage !== 0){
                        currentPage --;
                        changActive($('.pagination').find('li')[currentPage+1]);
                    }
                    $table.trigger("paging");
            })
            next.bind("click", function (event) {
                    if(currentPage !== sumPages-1){
                        currentPage ++;
                        changActive($('.pagination').find('li')[currentPage+1]);
                    }
                    $table.trigger("paging");
            })
            $('.pagination').find('li')[1].classList.add('active');
        });

        //分頁點擊變色，再點其他回復原色  
        function changActive(obj) {
            let arr = $('.pagination').find('li');
            for (let i = 0; i < arr.length; i++) {
                if (obj == arr[i]) {       //當前頁樣式  
                    obj.classList.add('active');
                    if(i == 1){
                        arr[0].classList.add('disabled');
                    }else{
                        arr[0].classList.remove('disabled');
                    }
                    if(i == arr.length-2){
                        arr[arr.length-1].classList.add('disabled');
                    }else{
                        arr[arr.length-1].classList.remove('disabled');
                    }
                }
                else {
                    arr[i].classList.remove('active');
                }
            }
        }
        </c:if>
    </script>

</body>

</html>