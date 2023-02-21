<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.orderDetail.model.*"%>

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

    <style>
        /* -------------- three box ---------------- */
        #three-box button,
        #three-box a {
            border-radius: 10px;
        }
        
        #three-box a:hover,
        #check-in-table button:hover,
        #check-out-table button:hover {
            filter: drop-shadow(2px 2px 2px black);
        }
        
        tbody:hover td {
            font-weight: bolder;
            background-color: rgb(240, 240, 240);
        }
    </style>
    <title>廠商首頁</title>
</head>

<body>
	<c:import url="/vendor/company_header.jsp" ></c:import>
    <!-- three box -->
    <div class="container">
        <div class="row d-flex justify-content-evenly my-5 text-center" id="three-box">
            <a href="#check-in-table" class="btn btn-light col-2 border border-secondary p-3 fw-bold">
                今日待入住房間<br>
                <span class="fs-4" style="letter-spacing: 14px;"><i class="fa-solid fa-bed"></i>${checkinAmount}</span>
            </a>
            <!-- <div class="col-2 border border-secondary rounded-3 p-3"> -->
            <a href="#check-out-table" class="btn btn-light col-2 border border-secondary p-3 fw-bold">
                今日待退房房間<br>
                <span class="fs-4" style="letter-spacing: 14px;"><i class="fa-solid fa-bed"></i>${checkoutAmount}</span>
            </a>
            <!-- </div> -->
            <a href="<%=request.getContextPath()%>/RoomOrder?action=getLivingVendorOrder" class="btn btn-light col-2 border border-secondary p-3 fw-bold">
                入住中房間<br>
                <span class="fs-4" style="letter-spacing: 14px;"><i class="fa-solid fa-bed"></i>${livingAmount}</span>
            </a>
        </div>
    </div>

    <!-- check-in table -->
    <div class="container pt-5" id="check-in-table">
        <table class="table col-10 table-sm text-center align-middle">
            <thead>
                <tr>
                    <th colspan="10" class="text-center fs-5 table-primary">今日待入住</th>
                </tr>
                <tr>
                    <th scope="col">訂單編號</th>
                    <th scope="col">房型名稱</th>
                    <th scope="col">房數</th>
                    <th scope="col">住客姓名</th>
                    <th scope="col">住客電話</th>
                    <th scope="col">入住日</th>
                    <th scope="col">退房日</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <c:forEach var="roomOrderVO" items="${checkinList}">
            <tbody>
            	<c:forEach var="orderDetailVO" items="${orderDetail.getByOrderID(roomOrderVO.orderId)}" varStatus="vs">
            	<c:set var="rowspan" value="${fn:length(orderDetail.getByOrderID(roomOrderVO.orderId))}"/>
                <tr>
            	<c:if test="${vs.first}">
                    <td rowspan="${rowspan}">${roomOrderVO.orderId}</td>
                </c:if>
                    <td class="order${roomOrderVO.orderId}">${room.getOneRoom(orderDetailVO.roomId).roomName}</td>
                    <td class="order${roomOrderVO.orderId}">${orderDetailVO.roomAmount}</td>
            	<c:if test="${vs.first}">
                    <td rowspan="${rowspan}">${roomOrderVO.customerName}</td>
                    <td rowspan="${rowspan}">${roomOrderVO.customerPhone}</td>
                    <td rowspan="${rowspan}">${roomOrderVO.checkinDate}</td>
                    <td rowspan="${rowspan}">${roomOrderVO.checkoutDate}</td>
                    <td rowspan="${rowspan}">
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
                    <input type="hidden" name="action" value="getOneOrder">
                    <input type="hidden" name="keyword" value="${roomOrderVO.orderId}">
                    <button type="submit" class="btn btn-outline-secondary fw-bold">查看</button>
                    </form>
                    </td>
                    <td rowspan="${rowspan}"><button type="button" class="btn btn-outline-primary fw-bold" data-bs-toggle="modal"
                            data-bs-target="#checkin">入住</button></td>
                </c:if>
                </tr>
            	</c:forEach>
            </tbody>
            </c:forEach>
        </table>
    </div>

    <!-- check-in-confirm-window -->
    <div class="modal fade" id="checkin" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title fw-bold">是否確認入住?</h5>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
					<input type="hidden" name="action" value="checkin">
					<input type="hidden" name="orderId" value="">
                    <button type="submit" class="btn btn-primary checkin-confirm" data-bs-dismiss="modal">確認</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- check-out table -->
    <div class="container py-5" id="check-out-table">
        <table class="table col-10 table-sm text-center align-middle">
            <thead>
                <tr>
                    <th colspan="10" class="text-center fs-5 table-primary">今日待退房</th>
                </tr>
                <tr>
                    <th scope="col">訂單編號</th>
                    <th scope="col">房型名稱</th>
                    <th scope="col">房數</th>
                    <th scope="col">住客姓名</th>
                    <th scope="col">住客電話</th>
                    <th scope="col">入住日</th>
                    <th scope="col">退房日</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <c:forEach var="roomOrderVO" items="${checkoutList}">
            <tbody>
            	<c:forEach var="orderDetailVO" items="${orderDetail.getByOrderID(roomOrderVO.orderId)}" varStatus="vs">
            	<c:set var="rowspan" value="${fn:length(orderDetail.getByOrderID(roomOrderVO.orderId))}"/>
                <tr>
                <c:if test="${vs.first}">
                    <td rowspan="${rowspan}">${roomOrderVO.orderId}</td>
                </c:if>
                    <td class="order${roomOrderVO.orderId}">${room.getOneRoom(orderDetailVO.roomId).roomName}</td>
                    <td class="order${roomOrderVO.orderId}">${orderDetailVO.roomAmount}</td>
            	<c:if test="${vs.first}">
                    <td rowspan="${rowspan}">${roomOrderVO.customerName}</td>
                    <td rowspan="${rowspan}">${roomOrderVO.customerPhone}</td>
                    <td rowspan="${rowspan}">${roomOrderVO.checkinDate}</td>
                    <td rowspan="${rowspan}">${roomOrderVO.checkoutDate}</td>
                    <td rowspan="${rowspan}">
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
                    <input type="hidden" name="action" value="getOneOrder">
                    <input type="hidden" name="keyword" value="${roomOrderVO.orderId}">
                    <button type="submit" class="btn btn-outline-secondary fw-bold">查看</button>
                    </form>
                    </td>
                    <td rowspan="${rowspan}"><button type="button" class="btn btn-outline-primary fw-bold" data-bs-toggle="modal"
                            data-bs-target="#checkout">退房</button></td>
                </c:if>
                </tr>
            	</c:forEach>
            </tbody>
            </c:forEach>
        </table>
    </div>

    <!-- check-out-confirm-window -->
    <div class="modal fade" id="checkout" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title fw-bold">是否確認退房?</h5>
                </div>
                <div class="modal-body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
					<input type="hidden" name="action" value="checkout">
					<input type="hidden" name="orderId" value="">
                    <button type="submit" class="btn btn-primary checkin-confirm" data-bs-dismiss="modal">確認</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- check-in-finish-toast -->
    <div class="toast position-fixed top-50 start-50 translate-middle border border-dark bg-light check-in-finish"
        style="z-index: 5">
        <div class="toast-body text-center fw-bold">
            完成入住
        </div>
    </div>

    <!-- check-out-finish-toast -->
    <div class="toast position-fixed top-50 start-50 translate-middle border border-dark bg-light check-out-finish"
        style="z-index: 5">
        <div class="toast-body text-center fw-bold">
            完成退房
        </div>
    </div>
    
    <c:import url="/vendor/company_footer.jsp" ></c:import>
    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>

    <script>
        $(document).ready(function () {
            $("[data-bs-target='#checkin']").on('click', function () {
                let trs = $(this).parent().prevAll();
                let target = trs[6].className;
                let list = $('.' + target);
                $(".modal-body")[0].innerHTML = '訂單編號：' + trs[7].innerText + '<br>住客姓名：' + trs[4].innerText;
                for (let i = 0; i < list.length; i++) {
                    if (i % 2 == 0) {
                        $(".modal-body")[0].innerHTML += "<br>房型名稱：" + list[i].innerHTML;
                    } else {
                        $(".modal-body")[0].innerHTML += "&emsp;&emsp;房數：" + list[i].innerHTML;
                    }
                }
                $(".modal-footer").find('input')[1].value = trs[7].innerText;
            });
            $("[data-bs-target='#checkout']").on('click', function () {
                let trs = $(this).parent().prevAll();
                let target = trs[6].className;
                let list = $('.' + target);
                $(".modal-body")[1].innerHTML = '訂單編號：' + trs[7].innerText + '<br>住客姓名：' + trs[4].innerText;
                for (let i = 0; i < list.length; i++) {
                    if (i % 2 == 0) {
                        $(".modal-body")[1].innerHTML += "<br>房型名稱：" + list[i].innerHTML;
                    } else {
                        $(".modal-body")[1].innerHTML += "&emsp;&emsp;房數：" + list[i].innerHTML;
                    }
                }
                $(".modal-footer").find('input')[3].value = trs[7].innerText;
            });

            $('.checkin-confirm').click(function () {
                $('.check-in-finish').show();
                setTimeout(() => {
                    $('.check-in-finish').hide();
                }, 1500);
            });

            $('.checkout-confirm').click(function () {
                $('.check-out-finish').show();
                setTimeout(() => {
                    $('.check-out-finish').hide();
                }, 1500);
            });
        });
    </script>
</body>

</html>