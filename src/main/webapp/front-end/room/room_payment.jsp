<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"
        integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>
    
    <style>
    	#preloader {
            position: fixed;
            width: 100%;
            height: 100%;
            left: 0%;
            top: 0%;
            background-color: rgb(137, 137, 137); 
            opacity: 0.5;
            z-index: 9998;
        }
        #load{
            position:fixed;
            top:50%;
            left:50%; 
            margin-left:-150px;
            margin-top:-150px;
            z-index: 9999;
        }
    </style>
    <title>付款頁面</title>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    
    <form method="post" action="<%=request.getContextPath()%>/EcpayPayment" class="needs-validation" novalidate>
        <div class="container pt-5">
            <div class="row justify-content-between">
                <div class="col-2 fs-4 fw-bold">結帳清單</div>
            </div>
            <!-- room table -->
            <div class="row justify-content-center mt-3">
                <div class="col">
                    <table class="table table-striped table-sm text-center align-middle">
                        <thead>
                            <tr class="text-center table-primary">
                                <th scope="col">住宿名稱</th>
                                <th scope="col">入住日</th>
                                <th scope="col">退房日</th>
                                <th scope="col">單價</th>
                                <th scope="col">房數</th>
                                <th scope="col">小計</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="item" items="${sessionScope.paymentItems}">
                            <tr>
                                <th>${item.venName}<br>${item.roomName}</th>
                                <td>${item.checkinDate}</td>
                                <td>${item.checkoutDate}</td>
                                <td>$<fmt:formatNumber type="number" pattern="#,###" value="${item.price}" /></td>
                                <td>${item.amount}間</td>
                                <td>$<fmt:formatNumber type="number" pattern="#,###" value="${item.price * item.amount}" /></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- coupon -->
            <div class="row mt-3">
                <div class="col-8 fw-bold">使用優惠券</div>
                <div class="col-4 d-flex justify-content-end">
                    <label for="coupon-select" class="me-2">優惠券選單</label>
                    <select class="form-select-sm" id="coupon-select" name="couponNo">
                    <c:if test="${empty couponVOList}">
                    	<option value="" disabled selected>無可使用之優惠券</option>
                    </c:if>
                    <c:if test="${not empty couponVOList}">
                        <option value="" selected>不使用優惠券</option>
                    <c:forEach var="coupon" items="${couponVOList}">
                        <option value="${coupon.couponNo}">${coupon.couponTitle}</option>
                    </c:forEach>
                    </c:if>
                    </select>
                </div>
            </div>
            <div class="row bg-secondary bg-opacity-25 my-4" style="height:2px;"></div>

            <!-- bonus point -->
            <div class="row align-items-center">
                <div class="col-7 d-flex">
                    <div class="fw-bold">紅利點數</div>
                    <div class="ms-5">共有${usersVO.bonusPoints}點</div>
                </div>
                <div class="col-5 d-flex justify-content-end">
                <c:if test="${usersVO.bonusPoints >= total}">
                    <label for="bonus" class="text-center me-2">勾選使用點數折抵${total}元</label>
                </c:if>
                <c:if test="${usersVO.bonusPoints < total}">
                    <label for="bonus" class="text-center me-2">勾選使用點數折抵${usersVO.bonusPoints}元</label>
                </c:if>
                    <div class="d-flex align-items-center"><input style="width:18px; height:18px;" type="checkbox" id="bonus" name="bonus"></div>
                </div>
            </div>
            <div class="row bg-secondary bg-opacity-25 my-4" style="height:2px;"></div>

            <!-- customer info -->
            <div class="row">
                <div class="col-2 fw-bold">
                    <div>填寫住客資訊</div>
                    <div class="text-danger"><i class="fa-solid fa-star-of-life"></i> 為必填資訊</div>
                </div>
                <div class="col-7">
                    <div>
                        <input class="form-check-input" type="checkbox" value="" id="sameas">
                        <label for="#sameas">同會員資料</label>
                    </div>
                    <label class="mt-3"><i class="fa-solid fa-star-of-life text-danger"></i> 住客姓名</label> 
					<input class="form-control" type="text" required maxlength="20" id="name" name="customerName">
					<div class="invalid-feedback">請輸入住客姓名</div>
					<label class="mt-3"><i class="fa-solid fa-star-of-life text-danger"></i> Email</label> 
					<input class="form-control" type="text" required pattern="^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$" maxlength="30" id="email" name="customerEmail">
					<div class="invalid-feedback">請輸入正確格式Email</div>
					<label class="mt-3"><i class="fa-solid fa-star-of-life text-danger"></i> 手機號碼</label> 
					<input class="form-control" type="text" required pattern="^09\d{8}$" maxlength="10" id="phone" name="customerPhone">
					<div class="invalid-feedback">請輸入正確格式10位數手機號碼</div>
				</div>
            </div>
            <div class="row bg-secondary bg-opacity-25 my-4" style="height:2px;"></div>
            <!-- sum -->
            <div class="row text-end bg-body shadow rounded border-top" style="position: sticky; bottom:0;">
                <p class="mt-2">小計：$<fmt:formatNumber type="number" pattern="#,###" value="${total}" /></p>
                <p id="couponDiscount">優惠券折扣金額：$ 0</p>
                <p id="bonusDiscount">紅利點數折抵金額：$ 0</p>
                <p class="fw-bold" id="payPrice">總付款金額：$<fmt:formatNumber type="number" pattern="#,###" value="${total}" /></p>
                <div>
                    <button type="submit" class="btn btn-primary mb-3">確認付款</button>
                </div>
            </div>
        </div>
    </form>
    
    <!-- modal -->
    <div class="modal fade" id="success" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <div class="modal-body text-center fs-5">結帳成功，訂單已成立</div>
            </div>
        </div>
    </div>
    
	<c:import url="/front-end/footer.jsp" ></c:import>
	
    <!-- loading -->
	<div id="preloader"></div>
    <lottie-player id="load" src="https://assets10.lottiefiles.com/packages/lf20_ztbbzkjc.json"  background="transparent"  speed="1"  style="width: 300px; height: 300px;" loop autoplay></lottie-player>
    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
	<!-- ===================== lottie-player ========================== -->
    <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
	<script>
		//loading隱藏
		$('#preloader').hide();
	    $('#load').hide();
	</script>
    <!-- ====================== 表單驗證 =========================== -->
    <script>
        (function () {
            var forms = document.querySelectorAll('.needs-validation')
            Array.prototype.slice.call(forms)
                .forEach(function (form) {
                    form.addEventListener('submit', function (event) {
                        if (!form.checkValidity()) {
                            event.preventDefault()
                            event.stopPropagation()
                        }else{
                    	    $('#load').show();
                        	$('#preloader').show();
                        }
                        form.classList.add('was-validated')
                    }, false)
                })
        })()

        $('[type="submit"]').click(function () {
        	if(document.querySelectorAll(":invalid").length > 0){
	            document.querySelectorAll(":invalid")[1].focus();
        	}
        });
    </script>
	
	<script>
        //////////// 同會員資料
        $('#sameas').change(function(){
            if($('#sameas').prop('checked')){
                $('#name').val('${usersVO.userName}');
                $('#email').val('${usersVO.userAccount}');
                $('#phone').val('${usersVO.userPhone}');
            }else{
                $('#name').val('');
                $('#email').val('');
                $('#phone').val('');
            }
        });
		/////////// 使用紅利點數
        let bonus = parseInt($('[for="bonus"]').text().replace(/[^\d]/g, ''), 10);
        $('#bonus').change(function(){
            let payPrice = parseInt($('#payPrice').text().replace(/[^\d]/g, ''), 10);
            if($('#bonus').prop('checked')){
                $('#bonusDiscount').text('紅利點數折抵金額：$' + bonus);
                $('#payPrice').text('總付款金額：$' + (payPrice - bonus).toLocaleString('en-US'));
            }else{
                $('#bonusDiscount').text('紅利點數折抵金額：$ 0');
                $('#payPrice').text('總付款金額：$' + (payPrice + bonus).toLocaleString('en-US'));
            }
        });
        /////////// 使用優惠券
        $('#coupon-select').change(function(){
                let useBonus = parseInt($('#bonusDiscount').text().replace(/[^\d]/g, ''), 10);
            if($(this).val() == '0'){
                let payPrice = parseInt($('#payPrice').text().replace(/[^\d]/g, ''), 10);
                $('#couponDiscount').text('優惠券折扣金額：$ 0');
                $('#payPrice').text('總付款金額：$' + (payPrice - useBonus).toLocaleString('en-US'));
            }else{
                let cpNo = $(this).val();
                $.ajax({
	                url: '<%=request.getContextPath()%>/ShoppingCart',
	                method: 'POST',
	                data: {
	                    action: 'countCouponDiscount',
	                    couponNo: cpNo,
	                    totalPrice: ${total}
	                },
	                dataType: "json",
	                success:function(res){
	                	if(res.status === 'ok'){
	                        $('#couponDiscount').text('優惠券折扣金額：$' + parseInt(res.couponDiscount, 10).toLocaleString('en-US'));
	                        $('#payPrice').text('總付款金額：$' + (parseInt(res.payPrice, 10) - useBonus).toLocaleString('en-US'));
	                	}
	                },
	                error:function(err){console.log(err)},
	            });
            }
        });
    </script>
</body>

</html>