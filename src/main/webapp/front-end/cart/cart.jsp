<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.* , com.shoppingCart.RoomItem" %>
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

    <!-- jquery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>


    <style>
    	button:hover {
            filter: drop-shadow(2px 2px 2px black);
        }
        img:hover {
        	cursor:pointer;
        	filter: drop-shadow(2px 2px 2px black);
        }
    </style>
    <title>購物車</title>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    <div style="height: 50px;"></div>
    <div class="container mt-5">
        <div class="row align-items-center">
            <div class="fs-4 fw-bold ">購物車</div>
        </div>
        <!-- tables -->
        <div class="row my-4">
            <table class="col-10 table table-striped text-center align-middle">
                <thead class="table-primary">
                    <tr>
                        <th scope="col" colspan="2" class="text-start ps-3 w-25">
                            <input type="checkbox" value="" id="all" style="width:20px; height:20px;"
                                class="align-text-bottom me-2">
                            <label for="#all">全選</label>
                        </th>
                        <th scope="col">住宿名稱</th>
                        <th scope="col">日期</th>
                        <th scope="col">適合人數</th>
                        <th scope="col">單價</th>
                        <th scope="col">間數</th>
                        <th scope="col">小計</th>
                        <th scope="col">刪除</th>
                    </tr>
                </thead>
                <tbody class="lh-lg">
                <c:forEach var="list" items="${sessionScope.shoppingcart}" varStatus="status">
                    <tr>
                    	<c:if test="${amountList[status.index] == 0}">
                        <td class="text-start ps-3"><input type="checkbox" value="${status.index}" class="checkone" disabled style="width:20px; height:20px;"></td>
                        </c:if>
                        <c:if test="${amountList[status.index] != 0}">
                        <td class="text-start ps-3"><input type="checkbox" value="${status.index}" class="checkone" style="width:20px; height:20px;"></td>
                        </c:if>
                        <td><img class="w-100" src="<%=request.getContextPath()%>/RoomPhoto?action=showFirstImages&roomId=${list.roomId}"></td>
                        <td>${list.venName}<br>${list.roomName}</td>
                        <td>入住日 ${list.checkinDate}<br>退房日 ${list.checkoutDate}</td>
                        <td>${list.people}人</td>
                        <td>$<fmt:formatNumber type="number" pattern="#,###" value="${list.price}" /></td>
                        <c:if test="${amountList[status.index] == 0}">
                        	<td colspan="2">
                        		<span class="text-danger fw-bold">已售罄<br>請重新搜尋</span>
                        	</td>
                        </c:if>
                        <c:if test="${amountList[status.index] < list.amount && amountList[status.index] != 0}">
                        	<td>
                        		<select class="form-select">
                                <c:forEach begin="1" end="${amountList[status.index]}" varStatus="vs">
				                    <option value="${vs.count}">${vs.count}間</option>
			                   	</c:forEach>
                            	</select>
                            	<div class="text-danger fw-bold">房數不足${list.amount}間<br>僅剩${amountList[status.index]}間</div>
                            </td>
                            <td class="subtotal">$<fmt:formatNumber type="number" pattern="#,###" value="${list.price}" /></td>
                        </c:if>
                        <c:if test="${amountList[status.index] >= list.amount}">
                        	<td>
	                            <select class="form-select">
	                                <c:forEach begin="1" end="${amountList[status.index]}" varStatus="vs">
				                    	<c:if test="${vs.count == list.amount}">
				                    		<option value="${vs.count}" selected>${vs.count}間</option>
				                    	</c:if>
				                    	<c:if test="${vs.count != list.amount}">
					                    <option value="${vs.count}">${vs.count}間</option>
					                    </c:if>
				                   </c:forEach>
	                            </select>
	                        </td>
	                        <td class="subtotal">$<fmt:formatNumber type="number" pattern="#,###" value="${list.price * list.amount}" /></td>
                        </c:if>
                        <td><button type="button" class="btn btn-outline-primary fs-5 delete">
                                <i class="fa-solid fa-trash-can"></i></button></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- price -->
        <div class="row my-4 text-end">
            <div class="fw-bold fs-5" id="allTotal">總金額：$ 0</div>
            <div class="my-3">
                <button type="button" class="btn btn-success fw-bold" id="payment">去結帳</button>
            </div>
        </div>
    </div>
	<c:import url="/front-end/footer.jsp" ></c:import>

    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
	<!-- sweet alert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	
    <script>
        // -------------------- 全選 -----------------------
        $('#all').change(function () {
            if ($(this).prop('checked')) {
                $('[type="checkbox"]').prop('checked', true);
                $('[disabled]').prop('checked', false);
                let list = $('.subtotal');
    	        let count = 0;
    	        for(let one of list){
    	            count += parseInt(one.innerText.replace(/[^\d]/g, ''), 10);
    	        }
    	        $('#allTotal')[0].innerText = '總金額：$' + count.toLocaleString('en-US');
            } else {
                $('[type="checkbox"]').prop('checked', false);
                $('#allTotal')[0].innerText = '總金額：$ 0';
            }
        });

        // -------------------- 刪除 -----------------------
        $('.delete').click(function () {
        	let num = $('.delete').toArray().indexOf($(this)[0]);
        	let deltr = $(this).closest('tr')[0];
        	$.ajax({
                url: '<%=request.getContextPath()%>/ShoppingCart',
                method: 'POST',
                data: {
                    action: 'DELETE',
                    indexNum: num
                },
                dataType: "json",
                success:function(res){
                	if(res.status === 'ok'){
	                	let number = parseInt(res.size, 10);
	                	if(number == 0){
	                		window.location = "<%=request.getContextPath()%>/front-end/cart/emptyCart.jsp";
	                	}else{
	                		$('.cartQuantity')[0].innerText = number;
	                        $('.cartQuantity').show();
	                        if(deltr.querySelector('.checkone').checked){
	                        	let orgTotal = parseInt($('#allTotal')[0].innerText.replace(/[^\d]/g, ''), 10);
	                        	let minus = parseInt(deltr.querySelector('.subtotal').innerText.replace(/[^\d]/g, ''), 10)
	                        	$('#allTotal')[0].innerText = '總金額：$' + (orgTotal - minus);
	                        }
	                		deltr.remove();
	                	}
                	}
                },
                error:function(err){console.log(err)},
            });
        	
        });
     	// -------------------- 小計計算 -----------------------
        $('select').change(function(){
        	let orgST = parseInt($(this).parent().siblings('.subtotal')[0].innerText.replace(/[^\d]/g, ''), 10);
            let money = parseInt($(this).parent().prev().text().replace(/[^\d]/g, ''), 10) * $(this).val();
            $(this).parent().next()[0].innerText = '$' + money.toLocaleString('en-US');
            if($(this).parent().parent().find('.checkone').prop('checked')){
            	let orgTotal = parseInt($('#allTotal')[0].innerText.replace(/[^\d]/g, ''), 10);
     			let plus = parseInt($(this).parent().siblings('.subtotal')[0].innerText.replace(/[^\d]/g, ''), 10) - orgST;
     			$('#allTotal')[0].innerText = '總金額：$' + (orgTotal + plus);
            }
            let num = $('select').toArray().indexOf($(this)[0]);
            let amount = $(this).val();
            $.ajax({
                url: '<%=request.getContextPath()%>/ShoppingCart',
                method: 'POST',
                data: {
                    action: 'changeNum',
                    indexNum: num,
                    newAmount: amount
                },
                error:function(err){console.log(err)},
            });
        });
     	
     	// -------------------- 總額 -----------------------
     	$('.checkone').change(function(){
     		if($(this).prop('checked')){
     			let orgTotal = parseInt($('#allTotal')[0].innerText.replace(/[^\d]/g, ''), 10);
     			let plus = parseInt($(this).parent().siblings('.subtotal')[0].innerText.replace(/[^\d]/g, ''), 10);
     			$('#allTotal')[0].innerText = '總金額：$' + (orgTotal + plus);
     		}else{
     			let orgTotal = parseInt($('#allTotal')[0].innerText.replace(/[^\d]/g, ''), 10);
     			let minus = parseInt($(this).parent().siblings('.subtotal')[0].innerText.replace(/[^\d]/g, ''), 10);
     			$('#allTotal')[0].innerText = '總金額：$' + (orgTotal - minus);
     		}
     	});
     	
     	// -------------------- 結帳 -----------------------
        $('#payment').click(function(){
        	if($('input:checkbox').is(':checked')){
	            let idNum = [];
	            for(let i = 0; i < $('.checkone').toArray().length; i++){
	                if($('.checkone').toArray()[i].checked === true){
	                	idNum[idNum.length] = i;
	                }
	            }
	            $.ajax({
	                url: '<%=request.getContextPath()%>/ShoppingCart',
	                method: 'POST',
	                data: {
	                    action: 'preparePayment',
	                    indexNum: JSON.stringify(idNum)
	                },
	                success:function(res){
	                	if(res === 'ok'){
		                	window.location = "<%=request.getContextPath()%>/ShoppingCart?action=goPayment";
	                	}else if(res === 'no'){
	                		alert("庫存不足，請確認結帳房間數量");
	                		window.location = "<%=request.getContextPath()%>/ShoppingCart?action=showCart"
	                	}
	                },
	                error:function(err){console.log(err)},
	            });
        	}else{
        		alert('您未勾選結帳商品');
        	}
        });
     	
     // -------------------- 到該商品頁面 -----------------------
        $('img').click(function(){
            let rmId = $(this).attr('src').slice($(this).attr('src').indexOf('roomId=') + 'roomId='.length);
            $.ajax({
                url: '<%=request.getContextPath()%>/ShoppingCart',
                method: 'POST',
                data: {
                    action: 'toVenPage',
                    roomId: rmId
                },
                success:function(res){
                	window.location = "<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=" + res;
                	},
                error:function(err){console.log(err)},
            });
        });
     <c:if test="${not empty lossStock}">
	     swal({
	         title: "庫存不足，請確認結帳房間數量",
	         icon: "error",
	      });
     </c:if>
    </script>

</body>

</html>