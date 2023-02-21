<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>訂房搜尋結果</title>

    <!-- ===================== Bootstrap 5 CSS ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- ===================== fontawesome ========================== -->
    <script src="https://kit.fontawesome.com/37ac1c7b87.js" crossorigin="anonymous"></script>

    <!-- ===================== jquery ========================== -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>

    <!-- --------------- Datepicker -------------------- -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

    <style>
        #search-bar {
            background-color: #ABD7F9;
            border-radius: 20px;
        }
    </style>

</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    <!-- search-bar -->
     <form method="post" action="<%=request.getContextPath()%>/Room" autocomplete="off">
        <div class="container mt-5 mb-4">
            <div class="row py-4" id="search-bar">
                <div class="col-3 ms-3 align-self-center">
                    <input type="text" class="form-control" style="font-family: FontAwesome;"
                        placeholder="&#xf002 地區或住宿名稱" name="keyword" maxlength="10" value="${param.keyword}">
                    <div class="text-danger fw-bold text-center">${errorMsgs.keywordErrorMsg}</div>
                </div>
                <div class="col-2 align-self-center">
                    <div class="input-group">
                        <label class="input-group-text" for="start-day"><i
                                class="fa-regular fa-calendar-days"></i></label>
                        <input class="form-control" type="text" id="start-day" name="startDay" placeholder="入住日期">
                    </div>
                        <div class="text-danger fw-bold text-center">${errorMsgs.ckinErrorMsg}</div>
                </div>
                <div class="col-2 align-self-center">
                    <div class="input-group">
                        <label class="input-group-text" for="end-day"><i
                                class="fa-regular fa-calendar-days"></i></label>
                        <input class="form-control" type="text" id="end-day" name="endDay" placeholder="退房日期">
                    </div>
                        <div class="text-danger fw-bold text-center">${errorMsgs.ckoutErrorMsg}</div>
                </div>
                <div class="col-2 align-self-center">
                    <select class="form-select" name="people" id="people">
                        <option value="" disabled selected>人數</option>
                        <option value="1">1人</option>
                        <option value="2">2人</option>
                        <option value="3">3人</option>
                        <option value="4">4人以上</option>
                    </select>
                    <div class="text-danger fw-bold text-center">${errorMsgs.peopleErrorMsg}</div>
                </div>
                <div class="col-2 align-self-center">
                    <select class="form-select" name="roomAmount" id="roomAmount">
                        <option value="" disabled selected>房數</option>
                        <option value="1">1間</option>
                        <option value="2">2間</option>
                        <option value="3">3間</option>
                        <option value="4">4間</option>
                        <option value="5">5間以上</option>
                    </select>
                    <div class="text-danger fw-bold text-center">${errorMsgs.roomAmtErrorMsg}</div>
                </div>
                <div class="col-auto align-self-center">
                	<input type="hidden" name="action" value="findRooms">
                    <button type="submit" class="btn btn-sm btn-primary">查詢</button>
                </div>
            </div>
        </div>
    </form>

	<c:if test="${empty resultList && empty noData}">
	<div style="height: 50px;"></div>
	</c:if>

	<c:if test="${not empty noData}">
    <div class="text-center fw-bold fs-4 mt-5">${noData}</div>
    </c:if>

	<c:if test="${not empty resultList}">
    <!-- order by -->
    <div class="container mb-3 px-5">
        <div class="row">
            <div class="btn-group" role="group">
                <button class="btn btn-outline-dark col-1" disabled>排序</button>
                <input type="radio" class="btn-check" name="btnradio" id="hot" autocomplete="off" checked>
                <label class="btn btn-outline-dark col-3 fw-bold" for="hot">熱門優先</label>
                <input type="radio" class="btn-check" name="btnradio" id="price" autocomplete="off">
                <label class="btn btn-outline-dark col-3 fw-bold" for="price">價格低優先</label>
                <input type="radio" class="btn-check" name="btnradio" id="score" autocomplete="off">
                <label class="btn btn-outline-dark col-3 fw-bold" for="score">評分高優先</label>
            </div>
        </div>
    </div>

    <!-- result -->
    <div id="result">
    <c:forEach var="oneVen" items="${resultList}">
    <div class="container px-5 mb-3">
        <div class="row border border-secondary p-1">
        	<div class="roomView" hidden>${oneVen.roomView}</div>
            <div class="col-3 p-2 d-flex justify-content-center">
                <img src="<%=request.getContextPath()%>/Room?venId=${oneVen.venId}&action=showFirstVenImages" class="w-100 rounded-3">
            </div>
            <div class="col-7 d-flex p-0">
                <div class="d-flex flex-column justify-content-between py-2 ms-4">
                    <div class="fs-3 fw-bold my-1">${oneVen.venName}</div>
                    <div class="text-decoration-underline my-2"><i class="fa-solid fa-location-dot text-danger"></i> ${oneVen.venAddr}</div>
                    <div class="my-2" style="overflow:hidden; display: -webkit-box; -webkit-box-orient: vertical; -webkit-line-clamp: 2;">
                        ${oneVen.venIntro}</div>
                    <div class="roomPrice" hidden>${oneVen.roomPrice}</div>
                    <div class="my-1"><span class="fs-3 fw-bold">$<fmt:formatNumber type="number" pattern="#,###" value="${oneVen.roomPrice}" />
						</span>起(每晚/間)&emsp;
						<c:if test="${oneVen.roomStock < 4}">
						<span class="text-danger fw-bold">僅剩${oneVen.roomStock}間</span>
						</c:if>
					</div>
                </div>
            </div>
            <div class="col-2 d-flex flex-column justify-content-end py-3">
            	<div class="venScore" hidden>${oneVen.venScore}</div>
            	<c:if test="${empty oneVen.venScore or oneVen.venScore == 0}">
                	<div class="bg-success rounded-3 p-1 text-center align-self-end mb-auto text-light" style="width: 40%;">尚無<br>評分</div>
		        </c:if>
                <c:if test="${not empty oneVen.venScore and oneVen.venScore != 0}">
                <div class="bg-success rounded-3 p-1 text-center align-self-end mb-auto text-light" style="width: 40%;">${oneVen.venScore}<br>
                 <c:choose>
					<c:when test="${oneVen.venScore < 3}">
						非常差
					</c:when>
					<c:when test="${oneVen.venScore < 5}">
						差
					</c:when>
					<c:when test="${oneVen.venScore < 7}">
						普通
					</c:when>
					<c:when test="${oneVen.venScore < 8}">
						好
					</c:when>
					<c:when test="${oneVen.venScore < 9}">
						很讚
					</c:when>
					<c:otherwise>
						超讚
					</c:otherwise>
				</c:choose>
                </div>
				</c:if>
                <form method="post" action="<%=request.getContextPath()%>/Room" class="text-end">
	                <input type="hidden" name="venId" value="${oneVen.venId}">
	                <input type="hidden" name="startDay" value="${startDay}">
	                <input type="hidden" name="endDay" value="${endDay}">
	                <input type="hidden" name="people" value="${param.people}">
	                <input type="hidden" name="roomAmount" value="${param.roomAmount}">
	                <input type="hidden" name="action" value="findVenRooms">
	                <button type="submit" class="btn btn-primary">查看詳情 <i class="fa-solid fa-right-long"></i></button>
                </form>
            </div>
        </div>
    </div>
    </c:forEach>
    </div>
    </c:if>
	<c:import url="/front-end/footer.jsp" ></c:import>
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
                to = $("#end-day")
                	.datepicker({
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
        
        let people = "<c:out value="${param.people}"/>";
        switch (people) {
            case '1':
                $('#people').val(1);
                break;
            case '2':
                $('#people').val(2);
                break;
            case '3':
                $('#people').val(3);
                break;
            case '4':
                $('#people').val(4);
                break;
            default:
                $('#people').val("");
        }
        let roomAmount = "<c:out value="${param.roomAmount}"/>";
        switch (roomAmount) {
            case '1':
                $('#roomAmount').val(1);
                break;
            case '2':
                $('#roomAmount').val(2);
                break;
            case '3':
                $('#roomAmount').val(3);
                break;
            case '4':
                $('#roomAmount').val(4);
                break;
            case '5':
                $('#roomAmount').val(5);
                break;
            default:
                $('#roomAmount').val("");
        }
        
    </script>
    
    <c:if test="${not empty resultList}">
    <!-- ====================== 排列順序 =========================== -->
    <script>
        $(document).ready(function () {
//     <!-- ====================== 熱門排列 =========================== -->
        	$('#hot').click(function(){
            	let venList = $('.roomView');
	            let viewList = [];
	            for(let i = 0; i < venList.length; i++){
	                let obj = {
	                    oneVen: venList.parent().parent()[i].outerHTML,
	                    view: parseInt(venList[i].innerText),
	                };
	                viewList[viewList.length] = obj;
	            }
	            viewList.sort(function (a, b) {
	                return a.view < b.view ? 1 : -1;
	            });
	            $('#result')[0].innerHTML = "";
	            for(one of viewList){
	            	$('#result')[0].innerHTML += one.oneVen;
	            }
        	});
        	$('#hot').click();
//     <!-- ====================== 價錢排列 =========================== -->      
        	$('#price').click(function(){
        		let venList = $('.roomView');
	            let priceList = [];
	            for(let i = 0; i < venList.length; i++){
	                let obj = {
	                    oneVen: venList.parent().parent()[i].outerHTML,
	                    price: parseInt($('.roomPrice')[i].innerText),
	                };
	                priceList[priceList.length] = obj;
	            }
	            priceList.sort(function (a, b) {
	                return a.price > b.price ? 1 : -1;
	            });
	            $('#result')[0].innerHTML = "";
	            for(one of priceList){
	            	$('#result')[0].innerHTML += one.oneVen;
	            }
        	});
//      <!-- ====================== 評分排列 =========================== -->
		    $('#score').click(function(){
		    	 let venList = $('.roomView');
		         let scoreList = [];
		         for(let i = 0; i < venList.length; i++){
		             let obj = {
		                 oneVen: venList.parent().parent()[i].outerHTML,
		                 score: parseFloat($('.venScore')[i].innerText),
		             };
		             scoreList[scoreList.length] = obj;
		         }
		         scoreList.sort(function (a, b) {
		             return a.score < b.score ? 1 : -1;
		         });
		         $('#result')[0].innerHTML = "";
		         for(one of scoreList){
		         	$('#result')[0].innerHTML += one.oneVen;
		         }
		 	});
        });
    </script>
    </c:if>
</body>

</html>