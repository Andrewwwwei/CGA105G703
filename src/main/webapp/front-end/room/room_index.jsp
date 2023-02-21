<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%
	if(request.getAttribute("hotList") == null){
		response.sendRedirect(request.getContextPath() + "/Room?action=toRoomIndex");
	}
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>訂房首頁</title>

    <!-- ===================== Bootstrap 5 CSS ========================== -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">

    <!-- ===================== fontawesome ========================== -->
    <script src="https://kit.fontawesome.com/37ac1c7b87.js" crossorigin="anonymous"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.2/css/all.min.css" rel="stylesheet" />

    <!-- --------------- slick -------------------- -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick-theme.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.8.1/slick.js"></script>

    <!-- --------------- Datepicker -------------------- -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>

    <style>
        #search-bar {
            background-color: #ABD7F9;
            border-radius: 20px;
        }

        .slick-prev:before {
            color: grey;
        }

        .slick-next:before {
            color: grey;
        }
        
        .fade-area {
            opacity: 1;
        }

        .fade-area:hover {
            opacity: 0.5;
        }
        .carousel-item{
		  	height:500px;
		  	overflow: hidden;
		}
		.slide-pic{
		  	margin-top: -100px;
		}
		#search-container{
			position: absolute;
			top:300px;
			left: 0;
  			right: 0;
		}
		
    </style>
</head>

<body>
	<c:import url="/front-end/header.jsp" ></c:import>
    <!-- search-bar -->
    <div class="carousel slide" data-bs-ride="carousel">
    <div class="carousel-inner">
      <div class="carousel-item active">
        <img src="<%=request.getContextPath()%>/front-end/images/view1.jpeg" class="d-block w-100 slide-pic">
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/front-end/images/view2.jpeg" class="d-block w-100 slide-pic">
      </div>
      <div class="carousel-item">
        <img src="<%=request.getContextPath()%>/front-end/images/view3.jpeg" class="d-block w-100 slide-pic">
      </div>
    </div>
    <form method="post" action="<%=request.getContextPath()%>/Room" autocomplete="off">
        <div class="container my-5" id="search-container">
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
    </div>
     <!-- hot -->
    <div class="container px-5 my-5 ">
        <h3 class="mb-3 fw-bold"><i class="fa-solid fa-fire text-danger"></i> 熱門住宿</h3>
        <div class="responsive">
        <c:forEach var="hot" items="${hotList}">
            <div>
                <div class="card h-100 mx-auto fade-area" style="width: 15rem;">
                    <img src="<%=request.getContextPath()%>/Room?action=showFirstVenImages&venId=${hot.venId}" class="card-img-top" style="height:180px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${hot.venName}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">${hot.area}<br>$<fmt:formatNumber type="number" pattern="#,###" value="${hot.price}" />起</h6>
                        <c:if test="${empty hot.score or hot.score == 0}">
                        	<a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${hot.venId}" class="btn btn-sm btn-success stretched-link">無</a>
                        </c:if>
                        <c:if test="${not empty hot.score and hot.score != 0}">
                        	<a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${hot.venId}" class="btn btn-sm btn-success stretched-link">${hot.score}分</a>
                        </c:if>
                    </div>
                </div>
            </div>
       </c:forEach>
       </div>
    </div>
    
    <!-- Taipei -->
    <div class="container px-5 mb-5">
        <h3 class="mb-3 fw-bold"><i class="fa-solid fa-fire text-danger"></i> 台北</h3>
        <div class="responsive">
        <c:forEach var="TP" items="${TPList}">
            <div>
                <div class="card h-100 mx-auto fade-area" style="width: 15rem;">
                    <img src="<%=request.getContextPath()%>/Room?action=showFirstVenImages&venId=${TP.venId}" class="card-img-top" style="height:180px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${TP.venName}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">台北<br>$<fmt:formatNumber type="number" pattern="#,###" value="${TP.price}" />起</h6>
                        <c:if test="${empty TP.score or TP.score == 0}">
                        	<a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${TP.venId}" class="btn btn-sm btn-success stretched-link">無</a>
                        </c:if>
                        <c:if test="${not empty TP.score and TP.score != 0}">
                        	<a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${TP.venId}" class="btn btn-sm btn-success stretched-link">${TP.score}分</a>
                        </c:if>
                    </div>
                </div>
            </div>
       </c:forEach>
       </div>
    </div>
    
    <!-- Taichong -->
    <div class="container px-5 mb-5">
        <h3 class="mb-3 fw-bold"><i class="fa-solid fa-fire text-danger"></i> 台中</h3>
        <div class="responsive">
        <c:forEach var="TC" items="${TCList}">
            <div>
                <div class="card h-100 mx-auto fade-area" style="width: 15rem;">
                    <img src="<%=request.getContextPath()%>/Room?action=showFirstVenImages&venId=${TC.venId}" class="card-img-top" style="height:180px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${TC.venName}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">台中<br>$<fmt:formatNumber type="number" pattern="#,###" value="${TC.price}" />起</h6>
                        <c:if test="${empty TC.score or TC.score == 0}">
	                        <a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${TC.venId}" class="btn btn-sm btn-success stretched-link">無</a>
                        </c:if>
                        <c:if test="${not empty TC.score and TC.score != 0}">
	                        <a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${TC.venId}" class="btn btn-sm btn-success stretched-link">${TC.score}分</a>
	                    </c:if>
                    </div>
                </div>
            </div>
       </c:forEach>
       </div>
    </div>
    
    <!-- Tainan -->
    <div class="container px-5 mb-5">
        <h3 class="mb-3 fw-bold"><i class="fa-solid fa-fire text-danger"></i> 台南</h3>
        <div class="responsive">
        <c:forEach var="TN" items="${TNList}">
            <div>
                <div class="card h-100 mx-auto fade-area" style="width: 15rem;">
                    <img src="<%=request.getContextPath()%>/Room?action=showFirstVenImages&venId=${TN.venId}" class="card-img-top" style="height:180px; object-fit: cover;">
                    <div class="card-body">
                        <h5 class="card-title">${TN.venName}</h5>
                        <h6 class="card-subtitle mb-2 text-muted">台南<br>$<fmt:formatNumber type="number" pattern="#,###" value="${TN.price}" />起</h6>
                        <c:if test="${empty TN.score or TN.score == 0}">
                        	<a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${TN.venId}" class="btn btn-sm btn-success stretched-link">無</a>
                        </c:if>
                        <c:if test="${not empty TN.score and TN.score != 0}">
                        	<a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${TN.venId}" class="btn btn-sm btn-success stretched-link">${TN.score}分</a>
                       	</c:if>
                    </div>
                </div>
            </div>
       </c:forEach>
       </div>
    </div>
    
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

	<!-- --------------- slick -------------------- -->
    <script>
        $(".responsive").slick({
            speed: 300,
            slidesToShow: 4,
            slidesToScroll: 1,
            responsive: [
                {
                    breakpoint: 1024,
                    settings: {
                        slidesToShow: 3,
                        slidesToScroll: 1
                    }
                },
                {
                    breakpoint: 800,
                    settings: {
                        slidesToShow: 2,
                        slidesToScroll: 1
                    }
                },
                {
                    breakpoint: 530,
                    settings: {
                        slidesToShow: 1,
                        slidesToScroll: 1
                    }
                }
            ]
        });
    </script>

</body>
</html>