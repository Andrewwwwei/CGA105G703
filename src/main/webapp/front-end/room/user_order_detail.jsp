<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="java.time.*"%> 
<%@ page import="java.time.format.*"%>
<%@ page import="java.time.temporal.ChronoUnit"%>
<%@ page import="com.room.model.*"%>
<%@ page import="com.vendor.model.*"%>

<jsp:useBean id="room" scope="page" class="com.room.model.RoomService" />
<jsp:useBean id="vendor" scope="page" class="com.vendor.model.VendorService" />
<jsp:useBean id="users" scope="page" class="com.Users.model.UsersService" />


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
    
    <!-- jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>

    <style>
    </style>
    <title>訂單明細</title>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    <div class="container mt-5">
        <div class="row align-items-center">
            <div class="col-3 d-flex align-items-center">
                <div class="fs-4 fw-bold me-5">訂單明細</div>
                <div>訂單編號：${param.orderId}</div>
            </div>
            <div class="col text-end">訂單成立時間：${(roomOrderVO.orderTime).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))}</div>
        </div>
        <div class="row mt-4">
            <div class="fw-bold">住客資訊</div>
        </div>
        <div class="row mt-1 align-items-center">
            <div class="col-2">姓名：${roomOrderVO.customerName}</div>
            <div class="col-3">手機號碼：${roomOrderVO.customerPhone}</div>
            <div class="col">Email：${roomOrderVO.customerEmail}</div>
            <c:if test="${roomOrderVO.orderStatus == 0}">
            	<div class="col-2 text-end"><button type="button" class="btn btn-danger" data-bs-toggle="modal"
                    data-bs-target="#cancel-confirm">取消訂單</button></div>
            </c:if>
            <c:if test="${roomOrderVO.orderStatus == 2 && empty roomOrderVO.reviewsTime}">
            	<div class="col-2 text-end">
            		<button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#comments">留下評價</button>
            	</div>
            </c:if>
            <c:if test="${roomOrderVO.orderStatus == 2 && not empty roomOrderVO.reviewsTime}">
            	<div class="col-2 text-end">
            		<button class="btn btn-success" data-bs-toggle="offcanvas" data-bs-target="#review">查看我的評價</button>
            	</div>
            </c:if>
            <c:if test="${roomOrderVO.orderStatus == 3}">
            	<div class="col-2 text-end ps-0">
                <span class="bg-danger text-white p-2 rounded-3 fw-bold">訂單已取消</span>
            	</div>
            </c:if>
        </div>
        <!-- order tables -->
        <div class="row my-4">
            <table class="col-10 table table-striped text-center align-middle">
                <thead>
                    <tr class="table-primary lh-lg">
                        <th>${vendor.getOneVendor(roomOrderVO.venId).venName}</th>
                        <th colspan="4" style="letter-spacing: 2px;">${roomOrderVO.checkinDate} 入住 <i
                                class="fa-solid fa-arrow-right-long"></i> ${roomOrderVO.checkoutDate} 退房</th>
                    </tr>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">房型</th>
                        <th scope="col">適合人數</th>
                        <th scope="col">間數</th>
                        <th scope="col">小計</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="orderDetailVO" items="${orderDetailList}">
                    <tr>
                        <td class="w-25"><img class="w-100" src="<%=request.getContextPath()%>/RoomPhoto?roomId=${orderDetailVO.roomId}&action=showFirstImages"></td>
                        <td>${room.getOneRoom(orderDetailVO.roomId).roomName}</td>
                        <td>${room.getOneRoom(orderDetailVO.roomId).roomPeople}人</td>
                        <td>${orderDetailVO.roomAmount}間</td>
                        <td>$<fmt:formatNumber type="number" pattern="#,###" value="${room.getOneRoom(orderDetailVO.roomId).roomPrice * orderDetailVO.roomAmount}" /></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- price -->
        <div class="row my-4 text-end">
            <p>總金額：$<fmt:formatNumber type="number" pattern="#,###" value="${roomOrderVO.orderCharge}" /></p>
            <p>折扣金額：$<fmt:formatNumber type="number" pattern="#,###" value="${(roomOrderVO.orderCharge) - (roomOrderVO.orderChargeDiscount)}" /></p>
            <p class="fw-bold">付款金額：$<fmt:formatNumber type="number" pattern="#,###" value="${roomOrderVO.orderChargeDiscount}" /></p>
        </div>
    </div>
	
	<!-- Modal -->
	<c:if test="${roomOrderVO.orderStatus == 0}">
        <div class="modal fade" id="cancel-confirm" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
	        <div class="modal-dialog modal-dialog-centered">
	            <div class="modal-content">
	                <div class="modal-header">
	                    <h5 class="modal-title fw-bold">請確認是否取消訂房?</h5>
	                </div>
	                <div class="modal-body">
	                    <span class="fw-bold">訂房取消政策：</span><br>
	                    ✡ 入住前10~13日取消訂房，將收取30%訂金費用。<br>
	                    ✡ 入住前7~9日取消訂房，將收取50%訂金費用。<br>
	                    ✡ 入住前4~6日取消訂房，將收取60%訂金費用。<br>
	                    ✡ 入住前2~3日取消訂房，將收取70%訂金費用。<br>
	                    ✡ 入住當日取消訂房，將收取100%訂金費用。<br>
	                    <br>
	                    若遇天然災害時，須以交通部中央氣象局發佈之陸上颱風警報並宣布住宿地區為停止上班上課，您可以選擇將訂單辦理延期3個月內入住或訂金退款100%，但若未發佈停止上班上課公告前要求辦理，則視為一般取消訂房。<br>
	                    <br>
	                    若因天然災害導致道路交通受損等因素亦可選擇延期或訂金退款。<br>
	                    其他天候如因下雨，但道路並未中斷等原因不在此限，請依氣象預報為準，並於入住日前二天通知本飯店。<br>
	                </div>
	                <div class="modal-footer">
	                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">返回</button>
	                    <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
							 <input type="hidden" name="action" value="cancelOrder">
							 <input type="hidden" name="orderId" value="${roomOrderVO.orderId}">
							 <button type="submit" class="btn btn-outline-primary">確認取消</button>
						</form>
	                </div>
	            </div>
	        </div>
    	</div>
    </c:if>
    
	<c:if test="${roomOrderVO.orderStatus == 2 && empty roomOrderVO.reviewsTime}">
	    <div class="modal fade" id="comments" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
	        <div class="modal-dialog modal-dialog-centered">
	            <div class="modal-content">
	                <form method="post" action="<%=request.getContextPath()%>/RoomOrder">
		                <div class="modal-header">
		                    <div>
		                        <h5 class="modal-title">請評分 0 ~ 10 分</h5>
		                        <div class="mt-2 p-2 border rounded-3">
		                            <span>5分</span>
		                            <input type="range" name="score" id="score" class="mx-3 align-middle" min="0" max="10">
		                            <span>普通</span>
		                        </div>
		                    </div>
		                    <button type="button" class="btn-close align-self-start" data-bs-dismiss="modal"></button>
		                </div>
		                <div class="modal-body">
		                    <h5 class="modal-title">請評語</h5>
		                    <textarea class="form-control" rows="8" maxlength="500" placeholder="分享你的住宿經驗..."
		                        name="reviews"></textarea>
		                    <div class="text-end">0/500</div>
		                </div>
		                <div class="modal-footer justify-content-center">
							 <input type="hidden" name="action" value="reviewOrder">
							 <input type="hidden" name="orderId" value="${roomOrderVO.orderId}">
							 <button type="submit" class="btn btn-outline-primary">送出評價</button>
		                </div>
	                </form>
	            </div>
	        </div>
	    </div>
    </c:if>
    
    <c:if test="${roomOrderVO.orderStatus == 2 && not empty roomOrderVO.reviewsTime}">
    <!-- review window -->
	    <div class="offcanvas offcanvas-end w-50" data-bs-backdrop="false" id="review">
	        <div class="offcanvas-header">
	            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
	        </div>
	        <div class="offcanvas-body py-0">
	            <div class="container">
	                <div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>
	                <div class="row mt-4">
	                    <div class="col-3">
	                        <div class="fs-5 mb-3"><i class="fa-solid fa-circle-user"></i> ${users.getOneUser(roomOrderVO.userId).userNickname}</div>
	                        <c:forEach var="orderDetailVO" items="${orderDetailList}">
	                        	<div class="text-secondary mb-3"><i class="fa-solid fa-bed"></i> ${room.getOneRoom(orderDetailVO.roomId).roomName}</div>
	                        </c:forEach>
	                        <div class="text-secondary" style="font-size:12px;">住宿於${(roomOrderVO.checkinDate).format(DateTimeFormatter.ofPattern("yyyy年MM月"))}</div>
	                    </div>
	                    <div class="col-8 px-0">
	                        ${roomOrderVO.reviews}
	                    </div>
	                    <div class="col-1 px-0">
	                        <div class="bg-success rounded-3 text-light text-center py-1 ms-auto" style="width:40px;">${roomOrderVO.score}</div>
	                        <c:choose>
							    <c:when test="${roomOrderVO.score} < 3">
							        <div class="mt-2 text-end">非常差</div>
							    </c:when>
							    <c:when test="${roomOrderVO.score} < 5">
							        <div class="mt-2 text-end">差</div>
							    </c:when>
							    <c:when test="${roomOrderVO.score} < 7">
							        <div class="mt-2 text-end">普通</div>
							    </c:when>
							    <c:when test="${roomOrderVO.score} < 8">
							        <div class="mt-2 text-end">好</div>
							    </c:when>
							    <c:when test="${roomOrderVO.score} < 9">
							        <div class="mt-2 text-end">很讚</div>
							    </c:when>
							    <c:otherwise>
							        <div class="mt-2 text-end">超讚</div>
							    </c:otherwise>
							</c:choose>
	                    </div>
	                    <div class="row mt-1 p-0 ms-auto">
	                        <div class="text-end">${(roomOrderVO.reviewsTime).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))} 留下評論</div>
	                    </div>
	                </div>
	                <div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>
	            </div>
	        </div>
	    </div>
    </c:if>
    <c:import url="/front-end/footer.jsp" ></c:import>

    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
        
    <c:if test="${roomOrderVO.orderStatus == 2 && empty roomOrderVO.reviewsTime}">
    	<script>
        $('#score').change(function () {
            let score = parseInt($(this).val(), 10);
            let word = $(this).next();
            let scoreText = $(this).prev();
            if (score < 3) {
                word.text('非常差');
            } else if (score < 5) {
                word.text('差');
            } else if (score < 7) {
                word.text('普通');
            } else if (score < 8) {
                word.text('好');
            } else if (score < 9) {
                word.text('很讚');
            } else {
                word.text('超讚');
            }
            scoreText.text(score + '分');
        });
        $('textarea').keyup(function () {
            $(this).next().text($(this).val().length + '/500');
        });
    </script>
    </c:if>

</body>

</html>