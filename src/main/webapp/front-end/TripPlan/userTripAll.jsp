<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<%@page import="com.trip.model.TripService"%>
<%@page import="com.trip.model.TripVO"%>
<%@ page import="com.Users.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- 	UsersVO usersVO = (UsersVO) session.getAttribute("usersVO"); -->
<%
	UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
	//如果沒有userId就等於沒有登入，轉跳到login
	if(usersVO == null)
		request.getRequestDispatcher("/front-end/member/login.jsp").forward(request, response);
	Integer userId = usersVO.getUserId();

	//搜尋user所有的 trip
	TripService tripSvc = new TripService();
	List<TripVO> triplist = tripSvc.getAll(userId);
	pageContext.setAttribute("triplist", triplist);
%>

<%@ include file="/front-end/TripPlan/headAndFoot/header.jsp" %>
 
  <!-- content start -->
  <div class="container me-auto my-5">
    <div class="row">
      <div class="col px-5">
        <h1 class="fw-bold">行程</h1>
      </div>
    </div>

    <div class="row">
      <div class="col px-5 text-end">
        <button class="btn btn-primary"  data-bs-toggle="modal" data-bs-target=".newTrip">+建立行程</button>
      </div>
    </div>

    <div class="row">
      <div class="col px-5">

        <ul class="nav nav-pills mb-3" id="pills-tab">
          <li class="nav-item" role="presentation">
            <button class="btn trip-btn active" data-bs-toggle="pill" data-bs-target="#user-trip" type="button" aria-selected="true">我的行程</button>
          </li>
          <li class="nav-item" role="presentation">
            <button class="btn trip-btn" data-bs-toggle="pill" data-bs-target="#trip-group" type="button" aria-selected="false">旅遊群組</button>
          </li>
        </ul>
        
        <div class="tab-content pt-2 px-3" id="pills-tabContent">
          <div class="tab-pane fade show active text-center" id="user-trip">
            
          <c:forEach var="tripVO" items="${triplist}">
           <c:if test="${!triplist.isEmpty()}">
            <div class="card tripCard d-inline-block m-2" style="width: 18rem;">
            <a href="<%=request.getContextPath()%>/front-end/TripPlan/tripPlan.jsp?TRIP_ID=${tripVO.tripId}">
              <c:if test="${tripVO.coverPic != null }">
                <img src="data:image/png;base64,${Base64.getEncoder().encodeToString(tripVO.coverPic)}" class="card-img-top" style="height: 150px">
              </c:if>
              <c:if test="${tripVO.coverPic == null }">
               <h3 class="d-block">尚無圖片</h3>
              </c:if>  
            </a>
              <div class="card-body">
                <h5 class="card-title fw-bold">${tripVO.tripName}</h5>
                <p class="card-text mb-1">${tripVO.startDate} - ${tripVO.endDate}</p>
                
                <form action="trip.do" method="post" class="d-inline">
                  <input type="hidden" name="action" value="delete">
				  <input type="hidden" name="TRIP_ID" value="${tripVO.tripId}">
                  <button type="button" class="btn btn-danger" onclick="delTrip(this)"><i class="bi bi-trash-fill"></i></button>
                </form>
                
                <a href="trip.do?TRIP_ID=${tripVO.tripId}&action=getPic_For_Update" class="btn btn-primary"><i class="bi bi-image-fill"></i></a>
              </div>
            </div>
           </c:if>
          </c:forEach>
           <c:if test="${triplist.isEmpty()}">
           	  <div><i class="bi bi-map-fill fa-5x"></i></div>
              <h5>您尚未有任何旅遊規劃ㄛ~~!</h5>
              <p>立即開始規劃吧~~</p>
           </c:if>
            
          </div>

          <div class="tab-pane fade text-center" id="trip-group">

          <div>
            <i class="bi bi-people-fill fa-5x"></i>
            <h5>您尚未有任何旅遊群組!</h5>
          </div>


          </div>
        </div>

      </div>
    </div>
  </div>
  <!-- content end -->

  <!-- add trip start -->
  <div class="modal fade newTrip" tabindex="-1" aria-labelledby="newTripLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="background-color:rgba(171, 192, 231, .7);">
        <div class="modal-header">
          <h5 class="modal-title newTripLabel">建立行程</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form action="trip.do" method="post" enctype="multipart/form-data">
          <div class="modal-body">
            <div class="form-outline mb-4">
              <label class="form-label" for="groupTopic">名稱 : </label>
              <input type="text" id="groupTopic" class="form-control" name="trip_name" required />
            </div>

            <div class="d-flex justify-content-around">
              <div class="form-outline mb-4">
                <label class="form-label" for="tripStart">出發日期 : </label>
                <input type="text" id="tripStart" class="form-control" name="start_date" required onkeypress="$(this).val('')" />
              </div>

              <div class="form-outline mb-4">
                <label class="form-label" for="tripEnd">結束日期 : </label>
                <input type="text" id="tripEnd" class="form-control" name="end_date" required onkeypress="$(this).val('')" />
              </div>
            </div>

            <div class="form-outline mb-4">
              <label class="form-label" for="actContent">提醒事項</label>
              <textarea class="form-control" style="height: 200px; resize: none;" id="actContent" name="note"></textarea>
            </div>
            <img class="shadow w-100" style="height: 300px;" hidden>
            <div class="input-group p-3">
              <label class="input-group-text" for="cover-pic">封面背景</label>
              <input type="file" class="form-control" id="cover-pic" name="cover-pic" accept=".jpg,.png,.jpeg">
            </div>
          </div>
          <input type="hidden" name="action" value="insert">
		  <input type="hidden" name="userId" value="<%=userId%>">
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-primary" onclick="addTripForm(event)">7Tour 囉!!</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- add trip end -->

  <!-- UpdatePIC modal start-->
<c:if test="${ openPicModal != null }">
  <div class="modal fade" id="veiwPic">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title changeIMGLabel">編輯行程封面</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <form action="trip.do" method="post" enctype="multipart/form-data">
          <div class="modal-body">
          <c:if test="${tripVO.coverPic != null }">
            <img src="data:image/png;base64,${Base64.getEncoder().encodeToString(tripVO.coverPic)}" class="shadow w-100" style="height: 300px;">
          </c:if>
          <c:if test="${tripVO.coverPic == null }">
          	<h3 class="text-center">此旅遊規劃，尚無圖片</h3>
          </c:if>
            <div class="input-group p-3">
              <label class="input-group-text" for="updatePic">更換圖片</label>
              <input type="file" class="form-control" id="updatePic" name="COVER_PIC" accept=".jpg,.png,.jpeg">
            </div>
          </div>
          	<input type="hidden" name="action" value="updateTripPic">
			<input type="hidden" name="TRIP_ID" value="${tripVO.tripId}">
			<input type="hidden" name="TRIP_NAME" value="${tripVO.tripName}">
			<input type="hidden" name="START_DATE" value="${tripVO.startDate}">
			<input type="hidden" name="END_DATE" value="${tripVO.endDate}">
			<input type="hidden" name="NOTE" value="${tripVO.note}">
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
            <button type="submit" class="btn btn-primary" onclick="updateTripPicForm(event)">送出</button>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script>
      $(document).ready(function(){
    	  $('#veiwPic').modal('show');
    	});
  </script>
</c:if>
  <!-- UpdatePIC modal  end-->


<%@ include file="/front-end/TripPlan/headAndFoot/footer.jsp" %>
 