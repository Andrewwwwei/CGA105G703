<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    </style>
    <title>購物車</title>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    <div style="height: 50px;"></div>
    <div class="container mt-5">
        <div class="row text-center my-3"><i class="fa-brands fa-shopify" style="font-size: 10em;"></i></div>
        <h2 class="row justify-content-center fw-bold my-3">你的購物車還是空的</h2>
        <div class="row text-center w-25 mx-auto my-3"><a type="button" class="btn btn-info px-5 fw-bold text-white" href="<%=request.getContextPath()%>/front-end/room/room_index.jsp">快去訂房吧！</a></div>
    </div>
			
	<c:import url="/front-end/footer.jsp" ></c:import>
    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
    <!-- sweet alert -->
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script>
		<c:if test="${not empty lossStock}">
		    swal({
		        title: "庫存不足，請確認結帳房間數量",
		        icon: "error",
		     });
		</c:if>
	</script>
</body>

</html>