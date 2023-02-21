<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ page import="java.util.*"%> 
<%@ page import="java.time.*"%> 
<%@ page import="com.vendor.model.*"%>

<jsp:useBean id="vendor" scope="page" class="com.vendor.model.VendorService" />

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
    
    <!-- ===================== datatable ========================== -->
    <link rel="https://cdn.datatables.net/1.13.1/css/dataTables.bootstrap5.min.css">
    <script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>

    <style>
    	.dataTables_filter input {
            width: 250px;
            height: 50px;
            font-size: medium;
        }
    </style>
    <title>訂單管理</title>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    <div class="container mt-5">
        <!-- nav-tabs -->
        <div class="row justify-content-between">
            <div class="col-7">
                <div class="nav nav-tabs" id="" role="tablist">
                    <button class="nav-link active" id="" data-bs-toggle="tab" data-bs-target="#future-order"
                        type="button" role="tab">未來式</button>
                    <button class="nav-link" id="" data-bs-toggle="tab" data-bs-target="#history-order" type="button"
                        role="tab">過去式</button>
                    <button class="nav-link" id="" data-bs-toggle="tab" data-bs-target="#cancel-order" type="button"
                        role="tab">已取消</button>
                </div>
            </div>
            <!-- search -->
            <div class="btn-group col-5" role="group">
                <button class="btn btn-outline-dark col-3" disabled>排序依據(新至舊)</button>
                <input type="radio" class="btn-check" name="btnradio" id="bookdate" autocomplete="off">
                <label class="btn btn-outline-dark fw-bold col" for="bookdate">預訂日期</label>
                <input type="radio" class="btn-check" name="btnradio" id="checkdate" autocomplete="off" checked>
                <label class="btn btn-outline-dark fw-bold col" for="checkdate">入住日期</label>
            </div>
        </div>
		<!-- order tables -->
		<div class="tab-content my-3" id="order-tables">
		    <!-- future-order tables -->
		    <div class="tab-pane fade show active" id="future-order" role="tabpanel">
		        <table class="col-10 table table-striped text-center align-middle" id="ftable">
		            <thead class="table-primary">
		                <tr>
		                    <th scope="col">訂單編號</th>
		                    <th scope="col">住宿名稱</th>
		                    <th scope="col" class="checkday">入住日</th>
		                    <th scope="col">退房日</th>
		                    <th scope="col">付款金額</th>
		                    <th scope="col" class="orderday">訂單成立日期</th>
		                    <th scope="col"></th>
		                </tr>
		            </thead>
		            <tbody>
		            	<c:forEach var="roomOrderVO" items="${orderListByCheckin}">
		                	<c:if test="${roomOrderVO.orderStatus  == 0}">
								<tr>
							            <td>${roomOrderVO.orderId}</td>
							            <td>${vendor.getOneVendor(roomOrderVO.venId).venName}</td>
							            <td>${roomOrderVO.checkinDate}</td>
							            <td>${roomOrderVO.checkoutDate}</td>
							            <td>${roomOrderVO.orderChargeDiscount}</td>
							            <td>${(roomOrderVO.orderTime).toLocalDate()}</td>
							            <td>
		                				<form method="post" action="<%=request.getContextPath()%>/OrderDetail">
							            <input type="hidden" name="action" value="getOrderDetailByOrder">
							            <input type="hidden" name="orderId" value="${roomOrderVO.orderId}">
							            <button type="submit" class="btn btn-outline-primary">查看訂單明細</button>
						        		</form>
							            </td>
							    </tr>
						    </c:if>
						</c:forEach>
		            </tbody>
		        </table>
		    </div>
		    <!-- history-order tables -->
		    <div class="tab-pane fade" id="history-order" role="tabpanel">
		        <table class="col-10 table table-striped text-center align-middle" id="htable">
		            <thead class="table-primary">
		                <tr>
		                    <th scope="col">訂單編號</th>
		                    <th scope="col">住宿名稱</th>
		                    <th scope="col" class="checkday">入住日</th>
		                    <th scope="col">退房日</th>
		                    <th scope="col">付款金額</th>
		                    <th scope="col" class="orderday">訂單成立日期</th>
		                    <th scope="col"></th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="roomOrderVO" items="${orderListByCheckin}">
		                	<c:if test="${roomOrderVO.orderStatus  == 2}">
								<tr>
						            <td>${roomOrderVO.orderId}</td>
						            <td>${vendor.getOneVendor(roomOrderVO.venId).venName}</td>
						            <td>${roomOrderVO.checkinDate}</td>
						            <td>${roomOrderVO.checkoutDate}</td>
						            <td>${roomOrderVO.orderChargeDiscount}</td>
						            <td>${(roomOrderVO.orderTime).toLocalDate()}</td>
						            <td>
						            <form method="post" action="<%=request.getContextPath()%>/OrderDetail">
							            <input type="hidden" name="action" value="getOrderDetailByOrder">
							            <input type="hidden" name="orderId" value="${roomOrderVO.orderId}">
							            <button type="submit" class="btn btn-outline-primary">查看訂單明細</button>
						        	</form>
						            </td>
						        </tr>
						    </c:if>
						</c:forEach>
		            </tbody>
		        </table>
		    </div>
		    <!-- cancel-order tables -->
		    <div class="tab-pane fade" id="cancel-order" role="tabpanel">
		        <table class="col-10 table table-striped text-center align-middle" id="ctable">
		            <thead class="table-primary">
		                <tr>
		                    <th scope="col">訂單編號</th>
		                    <th scope="col">住宿名稱</th>
		                    <th scope="col" class="checkday">入住日</th>
		                    <th scope="col">退房日</th>
		                    <th scope="col">付款金額</th>
		                    <th scope="col" class="orderday">訂單成立日期</th>
		                    <th scope="col"></th>
		                </tr>
		            </thead>
		            <tbody>
		                <c:forEach var="roomOrderVO" items="${orderListByCheckin}">
		                	<c:if test="${roomOrderVO.orderStatus  == 3}">
								<tr>
						            <td>${roomOrderVO.orderId}</td>
						            <td>${vendor.getOneVendor(roomOrderVO.venId).venName}</td>
						            <td>${roomOrderVO.checkinDate}</td>
						            <td>${roomOrderVO.checkoutDate}</td>
						            <td>${roomOrderVO.orderChargeDiscount}</td>
						            <td>${(roomOrderVO.orderTime).toLocalDate()}</td>
						            <td>
									<form method="post" action="<%=request.getContextPath()%>/OrderDetail">
							            <input type="hidden" name="action" value="getOrderDetailByOrder">
							            <input type="hidden" name="orderId" value="${roomOrderVO.orderId}">
							            <button type="submit" class="btn btn-outline-primary">查看訂單明細</button>
						        	</form>
						            </td>
						        </tr>
						    </c:if>
						</c:forEach>
		            </tbody>
		        </table>
		    </div>
		</div>
    </div>
    
    <c:import url="/front-end/footer.jsp" ></c:import>


    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
	<script>
        $(document).ready(function () {
            $('#ftable, #htable, #ctable').DataTable({
                "pageLength": 5,
                "dom": `<'row'<'col-sm-12 mb-3 text-start'f>>
                        <'row'<'col-sm-12'tr>>
                        <'row'<'col-sm-12 d-flex justify-content-center'p>>`,
                language: {  // 語言重設
                    search: "_INPUT_",
                    searchPlaceholder: "搜尋訂單編號或住宿名稱",
                    "sZeroRecords": "没有匹配结果",
                    "oPaginate": {
                        "sPrevious": "上一頁",
                        "sNext": "下一頁",
                    },
                },
                order: [[2, 'desc']], //預設排序
                aoColumns: [  //點擊時排序設定
                    null,
                    null,
                    { orderSequence: ['desc'] },
                    null,
                    null,
                    { orderSequence: ['desc'] },
                    null,
                ],
                "columnDefs": [
                    {
                        //設定欄位不參與搜索
                        "targets": [2, 3, 4, 5, 6],
                        "searchable": false
                    }
                ],
                drawCallback: function (settings) { // 只有一頁時隱藏分頁
                    var pagination = $(this).closest('.dataTables_wrapper').find('.dataTables_paginate');
                    pagination.toggle(this.api().page.info().pages > 1);
                }
            });

            $('#bookdate').click(function () {
                $('.orderday').click();
            });
            $('#checkdate').click(function () {
                $('.checkday').click();
            });

        });


    </script>
</body>

</html>