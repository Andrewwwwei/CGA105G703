<%@page import="com.room.controller.RoomServlet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.Users.model.*"%>
<jsp:useBean id="userSvc" scope="page" class="com.Users.model.UsersService" />

<%
	if(request.getAttribute("roomHotList") == null){
		response.sendRedirect(request.getContextPath() + "/FrontIndex");
	}
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  <link rel="website icon" href="<%=request.getContextPath()%>/front-end/member/images/bgremove_Logo.jpg">
  <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/fontpage.css">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
    integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
  <title>7 Tour | 旅遊規劃</title>
  <style>
  	.profile-picture {
		width: 45px;
		height: 45px;
		border-radius: 40px;
		overflow: hidden;
		object-fit: cover;
	}
	.card-img-top{
		height: 210px;;
		object-fit: cover;
	}
  </style>
</head>
<body>
	<c:import url="/front-end/header.jsp" ></c:import>
  <!-- slide pic start -->
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
    <div class="container pic-searchbar">
      <div class="row justify-content-center">
        <div class="col-8">
          <form class="d-flex">
            <input class="form-control" type="search" placeholder="輸入你想去的地方吧!!~~" aria-label="Search">
            <button class="btn btn-primary" type="submit">
              <i class="bi bi-search"></i>
            </button>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!-- slide pic end -->

  <!-- area start -->
  <article class="container-md">
    <div class="row justify-content-md-evenly text-center area-content">
      <a href="" class="col-2 fixed-w-h fade-area text-decoration-none">
        <img src="<%=request.getContextPath()%>/front-end/images/north.jpeg" alt="北部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">北部</div>
      </a>
      <a href="" class="col-2 fixed-w-h fade-area text-decoration-none">
        <img src="<%=request.getContextPath()%>/front-end/images/middle.jpg" alt="中部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">中部</div>
      </a>
      <a href="" class="col-2 fixed-w-h fade-area text-decoration-none">
        <img src="<%=request.getContextPath()%>/front-end/images/south.jpg" alt="南部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">南部</div>
      </a>
      <a href="" class="col-2 fixed-w-h fade-area text-decoration-none">
        <img src="<%=request.getContextPath()%>/front-end/images/east.jpg" alt="東部" class="w-100 h-100 rounded">
        <div class="w-100 h-100 pic-text">東部</div>
      </a>
    </div>
  </article>
  <!-- area end -->

  <!-- hotel card start -->
  <div class="container">
    <div class="row mt-4 pt-4">
      <div class="col">
        <h2 class="fw-bold">熱門住宿</h2>
      </div>
      <div class="col text-end">
        <a href="<%=request.getContextPath()%>/Room?action=toRoomIndex" class="a-style text-decoration-none">更多</a>
      </div>
    </div>
    
	<div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
	<c:forEach var="Ven" items="${roomHotList}">
	      <div class="col">
	        <a href="<%=request.getContextPath()%>/Room?action=showVenAllRooms&venId=${Ven.venId}" class="card h-100 shadow-sm fade-area text-decoration-none text-black">
				<img src="<%=request.getContextPath()%>/Room?action=showFirstVenImages&venId=${Ven.venId}" class="card-img-top" style="height:180px; object-fit: cover;">          
				<div class="card-body">
	            <h5 class="card-title fw-bold text-truncate">${Ven.venName}</h5>
	            <p class="card-text m-1">${Ven.area}</p>
	            <p class="card-text mx-1 mt-1 mb-2">$<fmt:formatNumber type="number" pattern="#,###" value="${Ven.price}" />元起</p>
	            <span class="card-text bg-success rounded p-1 text-white">${Ven.score}分</span>
	      		</div>
	        </a>
	      </div>
      </c:forEach>
    </div>

  </div>
  <!-- hotel card end -->

  <!-- article start -->
	<div class="container">
		<div class="row mt-4 pt-4">
			<div class="col">
				<h2 class="fw-bold">旅遊回憶</h2>
			</div>
			<div class="col text-end">
				<a
					href="<%=request.getContextPath()%>/article?action=forumHome"
					class="a-style text-decoration-none">更多</a>
			</div>
		</div>
		<div
			class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">

			<c:forEach var="articleVO" items="${articleVOlist}" varStatus="status">
				<c:if test="${status.index >= 0 && status.index < 4}">
					<a
						href="<%=request.getContextPath() %>/article?action=SelectOnePost_Display&artId=${articleVO.artId}&userId=${sessionScope.usersVO.getUserId()}"
						style="text-decoration: none; color: #737373;">
						<div class="card border-0 h-100 " style="opacity: 0.9; box-shadow: 0 0 5px #a1a1a1;">
							<img src="<%=request.getContextPath()%>/article/DBGifReader?artId=${articleVO.artId}" class="card-img-top" style="filter: brightness(80%);">
							<div class="card-body" style="padding: 9px;">
								<div style="display: flex;">
									<img src="<%=request.getContextPath()%>/usersServlet?action=getUserPic&userId=${articleVO.userId}" class="profile-picture">
									<div
										style="display: flex; flex-direction: column; margin-left: 10px;">
										<div style="display: flex; align-items: baseline;">
											<span class="userForumLevel"
												style="font-weight: bold; font-size: 18.4px;">${articleVO.getUsersVO().userForumLevel}</span>&nbsp;
											<span
												style="font-weight: bold; color: #2f3237; font-size: 18px; ">${articleVO.getUsersVO().userName}</span>
										</div>
										<span
											style="font-weight: bold; color: #b8c0c9;font-size: 12px; ">${articleVO.getUsersVO().userAccount}</span>
									</div>
									<h5 id="artTypeId${status.index}" class="card-title artTypeIds"
										style="font-weight: bold; margin-left: auto; ">${articleVO.artTypeId}</h5>
								</div>

								<h4 class="card-text "
									style="font-size: 20px; font-weight: bold; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; font-family: Fangzheng Zhongsong; margin-top:10px;  letter-spacing: 0.5px; color:#2f3237;">${articleVO.artTitle}</h4>

								<div class="card-sns-inner mr-2" style="font-size: 18px; color: gray; font-family: Fangzheng Zhongsong; font-weight: 900;">
									<span style="font-size: 20px;">
										<fmt:parseDate value="${articleVO.artTime }" pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" /> 
										<fmt:formatDate pattern="yyyy-MM-dd" value="${ parsedDateTime }" />
									</span>
									<span>ㆍ 觀看人數 :</span> 
									<span id="count"><fmt:formatNumber type="number" minFractionDigits="1" maxFractionDigits="1" value="${articleVO.artShowCount/1000}" />K</span>
								</div>

							</div>
						</div>
					</a>
				</c:if>
			</c:forEach>

		</div>
	</div>
	<!-- article end -->

  <!-- group start -->
  <div class="container">
    <div class="row mt-4 pt-4">
      <div class="col">
        <h2 class="fw-bold">熱門揪團</h2>
      </div>
      <div class="col text-end">
        <a href="<%=request.getContextPath()%>/front-end/act/actHomePage.jsp" class="a-style text-decoration-none">更多</a>
      </div>
    </div>

    <div class="row row-cols-1 row-cols-xs-2 row-cols-sm-2 row-cols-lg-4 g-3">
    
    <c:forEach var="actVO" items="${actVOlist}" begin="1" end="4">
      <div class="col">
        <div class="card h-100 shadow-sm">
          <a href="<%=request.getContextPath()%>/back-end/act/act.do?action=SelectOneAct&activityId=${actVO.activityId}" class="fade-area">
            <img src="<%=request.getContextPath()%>/back-end/act/act.do?action=showFirstImages&activityId=${actVO.activityId}" class="card-img-top">
          </a>
          <div class="card-body">
            <a href="<%=request.getContextPath()%>/back-end/act/act.do?action=SelectOneAct&activityId=${actVO.activityId}" class="a-style text-decoration-none text-black">
              <h5 class="card-title fw-bold text-truncate">${actVO.activityTitle}</h5>
            </a>
            <div class="card-text m-1">
                <img src="<%=request.getContextPath()%>/usersServlet?action=getUserPic&userId=${actVO.userId}" alt="會員照片" class="profile-picture">
                <span class="fw-bold text-truncate">${userSvc.getOneUser(actVO.userId).userName}</span>
            </div>
            <span class="card-text m-1">參加人數 : ${actVO.currentNumber}</span>
            <span class="card-text m-1 border-start border-dark ps-2">人數上限 : ${actVO.maxPeople}</span>
            <div class="card-text">報名截止 : ${actVO.registrationEnd}</div>
          </div>
        </div>
      </div>
	</c:forEach>
	
   </div>
  </div>
  <!-- group end -->
	<c:import url="/front-end/footer.jsp" ></c:import>
  <script src="https://kit.fontawesome.com/616f19a0b0.js" crossorigin="anonymous"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
  <script>
		var artTypeIds = document.getElementsByClassName("artTypeIds");
		for (let i = 0; i < artTypeIds.length; i++) {
			if (artTypeIds[i].innerHTML === "1") {
				artTypeIds[i].innerHTML = "遊記";
				artTypeIds[i].style.color = "#68c6ea";
			} else if (artTypeIds[i].innerHTML === "2") {
				artTypeIds[i].innerHTML = "美食";
				artTypeIds[i].style.color = "#eac368";
			} else if (artTypeIds[i].innerHTML === "3") {
				artTypeIds[i].innerHTML = "住宿";
				artTypeIds[i].style.color = "#45e9b8";
			} else if (artTypeIds[i].innerHTML === "4") {
				artTypeIds[i].innerHTML = "景點";
				artTypeIds[i].style.color = "#9898f8";
			}
		}
	</script>

	<script>
		var userForumLevel = document.getElementsByClassName("userForumLevel");
		for (let i = 0; i < userForumLevel.length; i++) {
			if (userForumLevel[i].innerHTML === "0") {
				userForumLevel[i].innerHTML = "平民";
				userForumLevel[i].style.color = "#6a88a8";
			} else if (userForumLevel[i].innerHTML === "1") {
				userForumLevel[i].innerHTML = "達人";
				userForumLevel[i].style.color = "#468caf";
			}
		}
	</script>
</body>

</html>