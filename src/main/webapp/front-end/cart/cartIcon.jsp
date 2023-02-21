<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

    <style>
        .cartQuantity {
            position: absolute;
            width: 20px;
            height: 20px;
            border-radius: 50%;
            display: flex;
            justify-content: center;
            align-items: center;
            right: -9px;
            top: -12px;
        }

        .cart-icon {
            position: fixed;
            right: 380px;
            top: 17px;
            z-index: 1031;
        }
        
        .cart-icon:hover{
        	text-shadow: 1px 1px 1px rgba(0,0,0,0.5);
        	cursor: pointer;
        }

        @media (max-width: 992px) {
            .cart-icon {
                right: 90px;
            }
        }
    </style>

<body>
    <div class="cart-icon text-black" id="cart">
        <i class="fa-solid fa-cart-shopping fs-2"></i>
        <c:if test="${empty sessionScope.shoppingcart}">
        <span class="cartQuantity text-white bg-danger bg-gradient" style="display: none;"></span>
        </c:if>
        <c:if test="${not empty sessionScope.shoppingcart}">
        <span class="cartQuantity text-white bg-danger bg-gradient">${sessionScope.shoppingcart.size()}</span>
        </c:if>
    </div>
     <script>
	     document.getElementById('cart').addEventListener("click", function () {
	         window.location = "<%=request.getContextPath()%>/ShoppingCart?action=showCart";
	     });
    </script>
</body>

</html>