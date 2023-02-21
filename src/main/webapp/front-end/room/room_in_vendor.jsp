<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.time.format.*"%>
<%@ page import="com.Users.model.*"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>瀏覽房型</title>

    <!-- ===================== Bootstrap 5 CSS ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- ===================== fontawesome ========================== -->
    <script src="https://kit.fontawesome.com/37ac1c7b87.js" crossorigin="anonymous"></script>

    <!-- --------------- slick -------------------- -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.js"></script>

    <!-- --------------- Datepicker -------------------- -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>


    <style>
        .slick-prev:before {
            color: grey;
        }

        .slick-next:before {
            color: grey;
        }

        #search-bar {
            background-color: #ABD7F9;
            border-radius: 20px;
        }

        .room-pic :hover,
        .storepic :hover {
            cursor: pointer;
        }
    </style>

</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    <!-- store title -->
    <div class="container mt-5 px-5">
        <div class="row">
            <div class="col-9">
                <h2 class="fw-bold">${vendorVO.venName}</h2>
                <div class="d-flex mt-4">
                    <h5 class="mb-0 me-2"><i class="fa-solid fa-location-dot text-danger"></i> ${vendorVO.venLocation}</h5>
                    <a href="https://www.google.com/maps/place/${vendorVO.venLocation}" class="text-decoration-none fs-5" target="_blank"><img src="<%=request.getContextPath()%>/front-end/images/google-maps.png" class="mb-2">查看地圖</a>
                </div>
            </div>
            <div class="border border-secondary rounded-3 d-flex ms-auto py-2 px-3" style="width:190px;">
                <div class="d-flex flex-column justify-content-between text-center">
                <c:if test="${empty vendorVO.venScore or vendorVO.venScore == 0}">
                	<div class="bg-success rounded-3 text-light text-center p-2 mt-1">尚無<br>評價</div>
                </c:if>
                <c:if test="${not empty vendorVO.venScore and vendorVO.venScore != 0}">
                    <h5 class="bg-success rounded-3 text-light text-center p-2 mt-1">${vendorVO.venScore}</h5>
                <c:choose>
					<c:when test="${vendorVO.venScore < 3}">
						<h5>非常差</h5>
					</c:when>
					<c:when test="${vendorVO.venScore < 5}">
						<h5>差</h5>
					</c:when>
					<c:when test="${vendorVO.venScore < 7}">
						<h5>普通</h5>
					</c:when>
					<c:when test="${vendorVO.venScore < 8}">
						<h5>好</h5>
					</c:when>
					<c:when test="${vendorVO.venScore < 9}">
						<h5>很讚</h5>
					</c:when>
					<c:otherwise>
						<h5>超讚</h5>
					</c:otherwise>
				</c:choose>
				</c:if>
                </div>
                <div class="ms-auto d-flex flex-column justify-content-between">
                    <div class="mt-2 text-center pe-2">${vendorVO.venScorePeople}則評價</div>
                    <button class="btn btn-sm btn-outline-primary my-2" data-bs-toggle="offcanvas"
                        data-bs-target="#review">查看所有評價</button>
                </div>
            </div>
        </div>
    </div>
	<div class="bg-secondary bg-opacity-25 my-4 mx-auto" style="height:2px; width:90%;"></div>
    <!-- store description -->
    <div class="container px-5 my-3">
        <div class="row">
            <div class="storepic col-6">
            <c:forEach var="vendorpicVO" items="${vendorpicVOList}">
                <img src="<%=request.getContextPath()%>/VendorPic?action=getPicById&venPicid=${vendorpicVO.venPicid}" class="w-100 rounded-2" data-bs-toggle="modal" data-bs-target="#storepic">
            </c:forEach>
            </div>
            <div class="col-6 ps-5 pe-2 lh-lg">&emsp;&emsp;${vendorVO.venIntro}</div>
        </div>
        <div class="row mt-3">
        	<div class="col-10 fw-bold">${vendorVO.venNotice}</div>
	        <div class="col-2 text-end">
	        <c:if test="${empty colVenVO}">
	            <button type="button" class="btn btn-outline-primary like-btn"><i class="fa-regular fa-heart"></i>
	                收藏住宿</button>
	            <button type="button" class="btn btn-outline-danger liked-btn" style="display: none;"><i class="fa-solid fa-heart"></i>
	                已收藏</button>
	        </c:if>
	        <c:if test="${not empty colVenVO}">
	            <button type="button" class="btn btn-outline-primary like-btn" style="display: none;"><i class="fa-regular fa-heart"></i>
	                收藏住宿</button>
	            <button type="button" class="btn btn-outline-danger liked-btn"><i class="fa-solid fa-heart"></i>
	                已收藏</button>
	        </c:if>
	        </div>
        </div>
    </div>
    
    <!-- search-bar -->
    <form method="post" action="<%=request.getContextPath()%>/Room" autocomplete="off">
        <div class="container my-3 px-5">
            <div class="row mb-2 ms-5 fs-4 fw-bold">搜尋條件</div>
            <div class="row py-4 w-75 justify-content-center mx-auto" id="search-bar">
                <div class="col-3 align-self-center">
                    <div class="input-group">
                        <label class="input-group-text" for="start-day"><i
                                class="fa-regular fa-calendar-days"></i></label>
                        <input class="form-control" type="text" id="start-day" name="startDay" placeholder="入住日期">
                    </div>
                        <div class="text-danger fw-bold text-center">${errorMsgs.ckinErrorMsg}</div>
                </div>
                <div class="col-3 align-self-center">
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
		                <input type="hidden" name="venId" value="${vendorVO.venId}">
		                <input type="hidden" name="action" value="findVenRooms">
	                    <button type="submit" class="btn btn-sm btn-primary">重新查詢</button>
                </div>
            </div>
        </div>
    </form>
    <c:if test="${not empty noData}">
    	<h4 class="text-center mt-5">${noData}</h4>
    </c:if>

	<c:if test="${empty noData}">
    <!-- room -->
    <c:forEach var="room" items="${roomList}" varStatus="rmvs">
    <div class="container px-5 my-3">
        <div class="row border border-secondary p-2">
            <div class="room-pic col-3 p-0 mx-4">
            <c:forEach var="roomPicId" items="${room.roomPhIdList}">
                <img src="<%=request.getContextPath()%>//RoomPhoto?action=showImagesByPicId&roomPhotoId=${roomPicId}" class="w-100" data-bs-toggle="modal" data-bs-target="#room${rmvs.count}">
            </c:forEach>
            </div>
            <div class="col-5 ms-3 d-flex flex-column ">
                <div hidden class="roomId">${room.roomId}</div>
                <h4 class="fw-bold my-1 roomName">${room.roomName}</h4>
                <div hidden class="roomPeople">${room.roomPeople}</div>
                <h5 class="my-3">適合人數 ${room.roomPeople}人&emsp;<c:forEach begin="1" end="${room.roomPeople}"><i class="fa-solid fa-person"></i></c:forEach></h5>
                <h6 class="mb-3 lh-base">${room.roomIntro}</h6>
            </div>
            <div class="col-1 ms-auto d-flex flex-column justify-content-end">
            	<c:if test="${room.roomRest < 4}">
                <div class="mb-2 text-danger text-end" style="width: 150%;">僅剩${room.roomRest}間</div>
                </c:if>
                <select class="form-select mb-2 amountSelect" style="width: 150%;" name="amountSelect">
                    <option value="" disabled>房數</option>
                    <c:forEach begin="1" end="${room.roomRest}" varStatus="vs">
                    	<c:if test="${vs.count == param.roomAmount}">
                    		<option value="${vs.count}" selected>${vs.count}間</option>
                    	</c:if>
                    	<c:if test="${vs.count != param.roomAmount}">
	                    <option value="${vs.count}">${vs.count}間</option>
	                    </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="col-2 ms-auto d-flex flex-column justify-content-end">
            	<div hidden class="onePrice">${room.roomPrice}</div>
                <h4 class="ms-auto mb-3 price">$<fmt:formatNumber type="number" pattern="#,###" value="${room.roomPrice * param.roomAmount}" />/<span style="font-size:12px; font-weight:bold;">${param.roomAmount}間</span></h4>
            </div>
            <div class="col-9 mt-2 d-flex pe-0 align-items-center" style="padding-left:20px;">
            	<c:if test="${room.breakfast == 1}">
                	<div class="me-3"><i class="fa-solid fa-utensils"></i> 早餐</div>
                </c:if>
                <c:if test="${room.air_condotioner == 1}">
	                <div class="me-3"><i class="fa-regular fa-snowflake"></i> 空調</div>
                </c:if>
                <c:if test="${room.wifi == 1}">
	                <div class="me-3"><i class="fa-solid fa-wifi"></i> WIFI</div>
                </c:if>
                <c:if test="${room.television == 1}">
	                <div class="me-3"><i class="fa-solid fa-tv"></i> 電視</div>
                </c:if>
                <c:if test="${room.bathroom == 1}">
	                <div class="me-3"><i class="fa-solid fa-shower"></i> 私人衛浴</div>
                </c:if>
                <c:if test="${room.toiletries == 1}">
	                <div class="me-3"><i class="fa-solid fa-check"></i> 免費盥洗用品</div>
                </c:if>
                <c:if test="${room.safebox == 1}">
	                <div class="me-3"><i class="fa-solid fa-check"></i> 保險箱</div>
                </c:if>
                <c:if test="${room.fridge == 1}">
	                <div class="me-3"><i class="fa-solid fa-check"></i> 冰箱</div>
                </c:if>
                <c:if test="${room.water_boiler == 1}">
	                <div class="me-3"><i class="fa-solid fa-check"></i> 電熱水壺</div>
                </c:if>
            </div>
            <button class="btn btn-outline-info mt-2 add-cart-btn" style="width: 130px;"><i
                    class="fa-solid fa-cart-shopping"></i>
                加入購物車</button>
            <div class="col p-0 d-flex justify-content-end">
            	<input type="hidden" name="venId" value="${vendorVO.venId}">
            	<input type="hidden" name="roomId" value="${room.roomId}">
            	<input type="hidden" name="checkinDay" value="${startDay}">
            	<input type="hidden" name="checkoutDay" value="${endDay}">
		        <input type="hidden" name="action" value="directPayment">
                <button type="button" class="btn btn-success mx-2 mt-2 payment">立即結帳</button>
            </div>
        </div>
    </div>
	</c:forEach>
	</c:if>
	
    <!-- review window -->
    <div class="offcanvas offcanvas-end w-50" data-bs-backdrop="false" id="review">
        <div class="offcanvas-header">
            <button type="button" class="btn-close" data-bs-dismiss="offcanvas"></button>
        </div>
        <div class="offcanvas-body py-0">
            <div class="container">
                <div class="row">
                    <div class="col-9 d-flex ">
                    	<c:if test="${empty vendorVO.venScore or vendorVO.venScore == 0}">
                        	<div class="bg-success rounded-3 text-light text-center fs-5 py-1 px-2">尚無評價</div>
		                </c:if>
                		<c:if test="${not empty vendorVO.venScore and vendorVO.venScore != 0}">
                        <div class="bg-success rounded-3 text-light text-center fs-5 py-1" style="width:50px;">${vendorVO.venScore}</div>
                        <c:choose>
							<c:when test="${vendorVO.venScore < 3}">
								<h5 class="my-auto mx-4 fw-bold">非常差</h5>
							</c:when>
							<c:when test="${vendorVO.venScore < 5}">
								<h5 class="my-auto mx-4 fw-bold">差</h5>
							</c:when>
							<c:when test="${vendorVO.venScore < 7}">
								<h5 class="my-auto mx-4 fw-bold">普通</h5>
							</c:when>
							<c:when test="${vendorVO.venScore < 8}">
								<h5 class="my-auto mx-4 fw-bold">好</h5>
							</c:when>
							<c:when test="${vendorVO.venScore < 9}">
								<h5 class="my-auto mx-4 fw-bold">很讚</h5>
							</c:when>
							<c:otherwise>
								<h5 class="my-auto mx-4 fw-bold">超讚</h5>
							</c:otherwise>
						</c:choose>
                        <div class="my-auto">${vendorVO.venScorePeople}則評價</div>
                        </c:if>
                    </div>
                </div>
                <div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>
                <div class="row align-items-center">
                    <div class="col-4 fs-5 fw-bold">住客評語</div>
                    <label for="orderby" class="col-3 pe-0 text-end ms-auto">排序依：</label>
                    <div class="col-4 ps-0">
                        <select class="form-select form-select-sm" id="orderby">
                            <option value="0">最新</option>
                            <option value="1">最舊</option>
                            <option value="2">評分最高</option>
                            <option value="3">評分最低</option>
                        </select>
                    </div>
                </div>
                <!-- review -->
                <div id="reviewList">
                <c:forEach var="review" items="${reviewList}">
                <div class="row mt-4 review">
                    <div class="col-3">
                        <div class="fs-5 mb-3"><i class="fa-solid fa-circle-user"></i> ${review.userNick}</div>
                        <c:forEach var="rmName" items="${review.roomNameList}">
                        <div class="text-secondary mb-3"><i class="fa-solid fa-bed"></i> ${rmName}</div>
                        </c:forEach>
                        <div class="text-secondary" style="font-size:12px;">住宿於${(review.stayDay).format(DateTimeFormatter.ofPattern("yyyy年MM月"))}</div>
                    </div>
                    <div class="col-8 px-0">${review.reviews}</div>
                    <div class="col-1 px-0">
                        <div class="bg-success rounded-3 text-light text-center py-1 ms-auto score" style="width:40px;">${review.score}</div>
                        <c:choose>
							    <c:when test="${review.score} < 3">
							        <div class="mt-2 text-end">非常差</div>
							    </c:when>
							    <c:when test="${review.score} < 5">
							        <div class="mt-2 text-end">差</div>
							    </c:when>
							    <c:when test="${review.score} < 7">
							        <div class="mt-2 text-end">普通</div>
							    </c:when>
							    <c:when test="${review.score} < 8">
							        <div class="mt-2 text-end">好</div>
							    </c:when>
							    <c:when test="${review.score} < 9">
							        <div class="mt-2 text-end">很讚</div>
							    </c:when>
							    <c:otherwise>
							        <div class="mt-2 text-end">超讚</div>
							    </c:otherwise>
							</c:choose>
                    </div>
                    <div class="row mt-1 p-0 ms-auto">
                    	<div class="reviewsTime" hidden>${review.reviewsTime}</div>
                        <div class="text-end">${(review.reviewsTime).format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))} 留下評論</div>
                    </div>
                </div>
                <div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>
                </c:forEach>
                </div>
            </div>
        </div>
    </div>

    <!-- toast -->
    <div class="toast position-fixed top-50 start-50 translate-middle bg-dark bg-opacity-75 add-like"
        style="z-index: 5">
        <div class="toast-body text-center text-light fw-bold">
            收藏成功
        </div>
    </div>
    <div class="toast position-fixed top-50 start-50 translate-middle bg-dark bg-opacity-75 delete-like"
        style="z-index: 5">
        <div class="toast-body text-center text-light fw-bold">
            已取消收藏
        </div>
    </div>
    <div class="toast position-fixed top-50 start-50 translate-middle bg-dark bg-opacity-75 add-cart"
        style="z-index: 5">
        <div class="toast-body text-center text-light fw-bold">
            房型已加入購物車
        </div>
    </div>

    <!-- Modal -->
    <div class="modal fade" id="storepic" tabindex="-1">
        <div class="modal-dialog modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="pic-modal">
                            <c:forEach var="vendorpicVO" items="${vendorpicVOList}">
				                <img src="<%=request.getContextPath()%>/VendorPic?action=getPicById&venPicid=${vendorpicVO.venPicid}" class="w-100">
				            </c:forEach>
                        </div>
                        <div class="pic-modal-sm mt-3">
                            <c:forEach var="vendorpicVO" items="${vendorpicVOList}">
				                <img src="<%=request.getContextPath()%>/VendorPic?action=getPicById&venPicid=${vendorpicVO.venPicid}" class="w-100">
				            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
	<c:forEach var="room" items="${roomList}" varStatus="rmvs">
    <div class="modal fade" id="room${rmvs.count}" tabindex="-1">
        <div class="modal-dialog modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                </div>
                <div class="modal-body">
                    <div class="container-fluid">
                        <div class="pic-modal">
                        <c:forEach var="roomPicId" items="${room.roomPhIdList}">
                            <img src="<%=request.getContextPath()%>//RoomPhoto?action=showImagesByPicId&roomPhotoId=${roomPicId}" class="w-100">
           				</c:forEach>
                        </div>
                        <div class="pic-modal-sm mt-3">
                        <c:forEach var="roomPicId" items="${room.roomPhIdList}">
                            <img src="<%=request.getContextPath()%>//RoomPhoto?action=showImagesByPicId&roomPhotoId=${roomPicId}">
           				</c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </c:forEach>
	<c:import url="/front-end/footer.jsp" ></c:import>
    <!-- ====================== Bootstrap 5 JS =========================== -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8"
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

    <!-- --------------- slick -------------------- -->
    <script>
        $(".storepic").slick({
            dots: true
        });

        $(".room-pic").slick();

        $(".pic-modal").slick({
            slidesToShow: 1,
            slidesToScroll: 1,
            arrows: true,
            fade: true,
            asNavFor: '.pic-modal-sm'
        });

        $('.pic-modal-sm').slick({
            slidesToShow: 4,
            slidesToScroll: 1,
            arrows: false,
            asNavFor: '.pic-modal',
            dots: true,
            centerMode: false,
            focusOnSelect: true
        });

        $('.modal').on('shown.bs.modal', function (e) {
            $('.pic-modal').slick('setPosition');
            $('.pic-modal').addClass('open');
            $('.pic-modal-sm').slick('setPosition');
            $('.pic-modal-sm').addClass('open');
        });

    </script>


    <script>
        // ------------- 收藏控制 -------------------
        $('.like-btn').click(function () {
        	$.ajax({
                url: '<%=request.getContextPath()%>/ColVen',
                type: 'POST',
                data: {
                    action: 'addlike',
                    venId: ${vendorVO.venId},
                    userId: '${sessionScope.usersVO.getUserId()}'
                },
                success:function(res){
                	if(res === 'ok'){
	                    $('.like-btn').hide();
	                    $('.like-btn').next().show();
	                    $('.add-like').show();
	                    setTimeout(() => {$('.add-like').hide();}, 1000);
                	}else if(res === 'login'){
	            		window.location = "<%=request.getContextPath()%>/front-end/member/login.jsp";
	            	}
                },
                error:function(err){console.log(err)},
            });
        });
        $('.liked-btn').click(function () {
        	$.ajax({
                url: '<%=request.getContextPath()%>/ColVen',
                type: 'POST',
                data: {
                    action: 'cancellike',
                    venId: ${vendorVO.venId},
                    userId: '${sessionScope.usersVO.getUserId()}'
                },
                success:function(res){
                	if(res === 'ok'){
	                    $('.liked-btn').hide();
	                    $('.liked-btn').prev().show();
	                    $('.delete-like').show();
	                    setTimeout(() => {$('.delete-like').hide();}, 1000);
                	}
                },
                error:function(err){console.log(err)},
            });
        });
        // ------------- 加入購物車控制 -------------------
        $('.add-cart-btn').on('click',function () {
        	let container = $(this)[0].closest('.container');
        	let rmName = container.querySelector('.roomName').innerText;
        	let rmId = container.querySelector('.roomId').innerText;
        	let rmPeople = container.querySelector('.roomPeople').innerText;
        	let rmPrice = container.querySelector('.onePrice').innerText;
        	let rmAmount = container.querySelector('.amountSelect').value;
        	$.ajax({
                url: '<%=request.getContextPath()%>/ShoppingCart',
                method: 'POST',
                data: {
                    action: 'ADD',
                    venName: '${vendorVO.venName}', 
                    roomName: rmName, 
                    roomId: rmId, 
                    checkinDate: '${startDay}',
                    checkoutDate: '${endDay}',
                    people: rmPeople,
                    price: rmPrice,
                    amount: rmAmount
                },
                dataType: "json",
                success:function(res){
                	if(res.status === 'ok'){
                		$('.add-cart').show();
                        setTimeout(() => {$('.add-cart').hide();}, 1000);
                        $('.cartQuantity')[0].innerText = res.size;
                        $('.cartQuantity').show();
                	}
                },
                error:function(err){console.log(err)},
            });
        });

     	// ------------- 直接結帳 -------------------
        $('.payment').click(function(){
            let container = $(this)[0].closest('.container');
        	let rmName = container.querySelector('.roomName').innerText;
        	let rmId = container.querySelector('.roomId').innerText;
        	let rmPeople = container.querySelector('.roomPeople').innerText;
        	let rmPrice = container.querySelector('.onePrice').innerText;
        	let rmAmount = container.querySelector('.amountSelect').value;
            $.ajax({
                url: '<%=request.getContextPath()%>/ShoppingCart',
                method: 'POST',
                data: {
                    action: 'directPreparePayment',
                    venName: '${vendorVO.venName}',
                    roomName: rmName,
                    roomId: rmId,
                    checkinDate: '${startDay}',
                    checkoutDate: '${endDay}',
                    people: rmPeople,
                    price: rmPrice,
                    amount: rmAmount
                },
                success:function(res){
                	if(res === 'ok'){
	                	window.location = "<%=request.getContextPath()%>/ShoppingCart?action=goPayment";
                	}else if(res === 'no'){
                		alert("庫存不足，請確認結帳房間數量");
                	}
                },
                error:function(err){console.log(err)},
            });
        });
    </script>
    
    <!-- ====================== 排列順序 =========================== -->
    <script>
        $(document).ready(function () {
            $('#orderby').change(function(){
                switch($(this).val()){
                    case '0': { //最新
                        let timeList = $('.reviewsTime');
                        let newList1 = [];
                        for(let i = 0; i < timeList.length; i++){
                            let obj = {
                                oneReview: $('.review')[i].outerHTML,
                                date: parseInt(timeList[i].innerText),
                            };
	                        newList1[newList1.length] = obj;
                        }
                        newList1.sort(function (a, b) {
	                        return new Date(a.date) < new Date(b.date) ? 1 : -1;
	                    });
                        $('#reviewList')[0].innerHTML = "";
                        for(one of newList1){
                            $('#reviewList')[0].innerHTML += one.oneReview + '<div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>';
                        }
                        break;
                    }
                    case '1': { //最舊
                    	let timeList = $('.reviewsTime');
                        let newList1 = [];
                        for(let i = 0; i < timeList.length; i++){
                            let obj = {
                                oneReview: $('.review')[i].outerHTML,
                                score: parseInt(timeList[i].innerText),
                            };
	                        newList1[newList1.length] = obj;
                        }
                        newList1.sort(function (a, b) {
                        	return new Date(a.date) > new Date(b.date) ? 1 : -1;
	                    });
                        $('#reviewList')[0].innerHTML = "";
                        for(one of newList1){
                            $('#reviewList')[0].innerHTML += one.oneReview + '<div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>';
                        }
                        break;
                    }
                    case '2': { //最高分
                        let scoreList = $('.score');
                        let newList1 = [];
                        for(let i = 0; i < scoreList.length; i++){
                            let obj = {
                                oneReview: $('.review')[i].outerHTML,
                                score: parseInt(scoreList[i].innerText),
                            };
	                        newList1[newList1.length] = obj;
                        }
                        newList1.sort(function (a, b) {
	                        return a.score < b.score ? 1 : -1;
	                    });
                        $('#reviewList')[0].innerHTML = "";
                        for(one of newList1){
                            $('#reviewList')[0].innerHTML += one.oneReview + '<div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>';
                        }
                        break;
                    }
                    case '3': { //最低分
                        let scoreList = $('.score');
                        let newList1 = [];
                        for(let i = 0; i < scoreList.length; i++){
                            let obj = {
                                oneReview: $('.review')[i].outerHTML,
                                score: parseInt(scoreList[i].innerText),
                            };
	                        newList1[newList1.length] = obj;
                        }
                        newList1.sort(function (a, b) {
	                        return a.score > b.score ? 1 : -1;
	                    });
                        $('#reviewList')[0].innerHTML = "";
                        for(one of newList1){
                            $('#reviewList')[0].innerHTML += one.oneReview + '<div class="row bg-secondary bg-opacity-25 my-2" style="height:2px;"></div>';
                        }
                        break;
                    }
                }
            });
            $('#orderby').val('0');
   /////////////// 價格取值 //////////////
            $('.amountSelect').change(function(){
                let price = parseInt($(this).closest('.col-1').next().find('.price').prev()[0].innerText);
                let showPrice = (price * parseInt($(this).val())).toLocaleString('en-US')
                $(this).closest('.col-1').next().find('.price')[0].innerHTML = '$' + showPrice + 
                '/<span style="font-size:12px; font-weight:bold;">' + $(this).val() + '間</span>';
                
            })
        });
    </script>
</body>

</html>