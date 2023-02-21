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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

    <style>
    </style>
    <title>空房查詢</title>
</head>

<body>
	<c:import url="/vendor/company_header.jsp" ></c:import>
    <!-- Datepicker -->
    <div class="container my-5">
        <form method="post" action="<%=request.getContextPath()%>/Stock" autocomplete="off">
        <div class="row justify-content-center">
            <div class="col-2 text-center  fs-4">空房查詢</div>
            <div class="col-2">
                <div class="input-group">
                    <label class="input-group-text" for="start-day"><i class="fa-regular fa-calendar-days"></i></label>
                    <input class="form-control" type="text" id="start-day" name="start-day" placeholder="入住日期">
                </div>
                <div class="text-danger fw-bold text-center">${errorMsgs.ckinErrorMsg}</div>
            </div>
            <div class="col-1 text-center lh-lg"><i class="fa-solid fa-arrow-right-long "></i></div>
            <div class="col-2">
                <div class="input-group">
                    <label class="input-group-text" for="end-day"><i class="fa-regular fa-calendar-days"></i></label>
                    <input class="form-control" type="text" id="end-day" name="end-day" placeholder="退房日期">
                </div>
                <div class="text-danger fw-bold text-center">${errorMsgs.ckoutErrorMsg}</div>
            </div>
            <div class="col-1">
                    <input type="hidden" name="action" value="getVendorAllStockByDate">
                	<button type="submit" class="btn btn-sm btn-outline-dark mt-1 ms-4">查詢</button>
            </div>
        </div>
        </form>
    </div>

	<c:if test="${not empty resultList}">
    <!-- Search console -->
    <div class="container">
        <div class="row justify-content-center text-center">
            <div class="col-6">
                <table class="table table-borderless table-striped">
                    <tbody>
                    <c:forEach var="aRoom" items="${resultList}">
                        <tr>
                            <td class="text-end"><i class="fa-solid fa-bed"></td>
                            <td class="fw-bold">${aRoom.get(0)}</td>
                            <td class="fw-bold">剩餘${aRoom.get(1)}間</td>
                            <td>/</td>
                            <td>庫存${aRoom.get(2)}間</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </c:if>

	<c:import url="/vendor/company_footer.jsp" ></c:import>
    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>

    <!-- ====================== Datepicker =========================== -->
    <script>
        $(document).ready(function () {
            var dateFormat = "yy-mm-dd",
                from = $("#start-day")
                    .datepicker({
                        changeMonth: true,
                        changeYear: true
                    })
                    .datepicker("option", "dateFormat", "yy-mm-dd")
                    .datepicker("option", "minDate", 0)
                    .datepicker("option", "maxDate", "+6M")
                    .on("change", function () {
                        to.datepicker("option", "minDate", getDateAdd(this));
                    }),
                to = $("#end-day").datepicker({
                    changeMonth: true,
                    changeYear: true
                })
                    .datepicker("option", "dateFormat", "yy-mm-dd")
                    .datepicker("option", "minDate", 0)
                    .datepicker("option", "maxDate", "+6M +1D")
                    .on("change", function () {
                        from.datepicker("option", "maxDate", getDate(this));
                    });

            function getDate(element) {
                var date;
                try {
                    date = $.datepicker.parseDate(dateFormat, element.value);
                } catch (error) {
                    date = null;
                }
                return date;
            }

            function getDateAdd(element) {
                var date;
                try {
                    date = $.datepicker.parseDate(dateFormat, element.value);
                    date.setDate(date.getDate() + 1);
                } catch (error) {
                    date = null;
                }
                return date;
            }
            
            <!-- ====================== 表單驗證錯誤，填入剛剛輸入資料 =========================== -->
	        let startDay = "<c:out value="${startDay}"/>";
	        if(startDay){
		        $('#start-day').val(startDay);
	        }
	        let endDay = "<c:out value="${endDay}"/>";
	        if(endDay){
		        $('#end-day').val(endDay);
	        }
        });
    </script>
</body>

</html>