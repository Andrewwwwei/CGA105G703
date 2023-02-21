<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@page import="com.Users.model.*"%>
<%@ page import="com.Mes.model.*"%>
<%@ page import="com.article_report.model.*"%>


<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/css/forumhome.css">
<!-- bootstrap-->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

<title>7 Tour | 論壇首頁</title>
</head>

<body>
	<c:import url="/front-end/header.jsp"></c:import>

	<div class="body-content">
		<div class="container" style="width: 80%">
			<div class="d-flex align-items-end justify-content-between mt-4 ">

				<div class="forum-header text-start mb-1 mt-2 pb-1"
					style="width: 200px;">
					<h4>熱門文章</h4>
				</div>

				<div class="d-flex">
					<div class="d-flex text-end mt-4" style="margin-right: 12px;">

						<div class="input-group mb-2 text-end">
							<button type="button" class="btn btn-outline-secondary"
								style="font-size: 17px;" id="search-btn">搜尋</button>
							<button type="button"
								class="btn btn-outline-secondary dropdown-toggle dropdown-toggle-split"
								data-bs-toggle="dropdown" aria-expanded="false">
								<span class="visually-hidden">Toggle Dropdown</span>
							</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=1">遊記</a></li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=2">美食</a></li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=3">住宿</a></li>
								<li><a class="dropdown-item"
									href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=4">景點</a></li>
								<li><a class="dropdown-item"
									href="
										<%=request.getContextPath()%>/article?action=powerSearchAllPost">全部</a></li>
							</ul>
							<input type="text" class="form-control"
								aria-label="Text input with dropdown button" id="keyword"
								placeholder="請輸入關鍵字" style="color: rgb(157, 147, 147);">
							<a
								href="<%=request.getContextPath()%>/article?action=SearchByKeyword&keyword=${keyword}"
								class="d-none" id="search-link"></a>
						</div>
					</div>
					<div class="text-end mb-1 mt-4" style="margin-right: 10px;">
						<a class="btn btn-outline-secondary"
							href="<%=request.getContextPath() %>/article?action=userPosted&userId=${sessionScope.usersVO.getUserId()}"
							role="postart">我的文章</a>
					</div>
					<div class="text-end mb-1 mt-4">
						<a class="btn btn-outline-secondary"
							href="<%=request.getContextPath() %>/article?action=addart&userId=${sessionScope.usersVO.getUserId()}"
							role="postart">發佈文章</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 熱門文章(幻燈片) -->

	<div class="container mb-3" style="width: 80%">
		<!-- 幻燈片定義  interval為停留秒數-->
		<div id="carouselExampleFade" class="carousel slide carousel-fade"
			data-bs-ride="carousel" data-interval="1000">
			<!-- 幻燈片位置 會動主要來自active 指示器-->
			<div class="carousel-indicators ">
				<c:forEach items="${hotlist}" var="articleVO" varStatus="status">
					<c:set var="active" value="" />
					<c:if test="${status.first}">
						<c:set var="active" value=" active" />
					</c:if>
					<button type="button" data-bs-target="#carouselExampleFade"
						data-bs-slide-to="${status.index}" class="${active}"
						aria-current="true" aria-label="Slide ${status.index+1}"></button>
				</c:forEach>
			</div>

			<!-- 幻燈片內容 -->
			<div class="carousel-inner w-100 ">
				<c:forEach var="articleVO" items="${hotlist}" varStatus="status">
					<c:set var="active" value="${status.first ? ' active' : ''}" />
					<div class="carousel-item${active}">
						<img
							src="<%=request.getContextPath()%>/article?action=DBGifReader&artId=${articleVO.artId}"
							class="d-block carousel-img mx-auto d-block w-100"
							style="filter: brightness(70%); height: 500px;">
						<div class="carousel-caption d-none d-md-block">
							<a
								href="<%=request.getContextPath() %>/article?action=SelectOnePost_Display&artId=${articleVO.artId}&userId=${sessionScope.usersVO.getUserId()}"
								style="text-decoration: none; color: #e6e6e6;">
								<h5 id="artTypeId${status.index}" class="card-title">${articleVO.artTypeId}</h5>
								<h4 style="font-weight: bold;">${articleVO.artTitle}</h4>
								<div class="sns-inner">
									<span style="font-weight: bold; color: lightgray;"> <fmt:parseDate
											value="${articleVO.artTime}" pattern="yyyy-MM-dd'T'HH:mm"
											var="parsedDateTime" type="both" /> <fmt:formatDate
											pattern="yyyy .MM .dd" value="${parsedDateTime}" />
									</span> &nbsp;&nbsp;&nbsp; <i class="bi bi-eye-fill"
										style="color: #c2ca95; font-size: 19px;"></i> &nbsp;&nbsp; <span
										id="count"> <fmt:formatNumber type="number"
											minFractionDigits="1" maxFractionDigits="1"
											value="${articleVO.artShowCount/1000}" />K
									</span> &nbsp;&nbsp;&nbsp;
								</div>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- 幻燈片內容結束 -->
		</div>
	</div>
	<!-- 文章專區 -->
	<div class="container mb-2 " style="width: 84%">
		<h4 style="margin-top: 10px; margin-left: 5px;">文章專區</h4>
		<div class="row row-cols-sm-1 row-cols-lg-5 g-2 h-250px ">
			<c:forEach var="articleVO" items="${articleVOlist}" varStatus="status">
				<c:if test="${articleVO.getArticleReportVO().rpStatus != 1}">
					<a
						href="<%=request.getContextPath() %>/article?action=SelectOnePost_Display&artId=${articleVO.artId}&userId=${sessionScope.usersVO.getUserId()}"
						style="text-decoration: none; color: #737373;">
						<div class="card border-0 h-100">
							<img
								src="<%=request.getContextPath()%>/article?action=DBGifReader&artId=${articleVO.artId}"
								class="card-img-top" style="filter: brightness(80%);">
							<div class="card-body">

								<h5 id="artTypeId${status.index}" class="card-title"
									style="font-size: 17px; font-weight: bold;">${articleVO.artTypeId}</h5>

								<h4 class="card-text"
									style="font-size: 18px; font-weight: bold; overflow: hidden; white-space: nowrap; text-overflow: ellipsis; font-family: Fangzheng Zhongsong;">${articleVO.artTitle}</h4>

								<div class="card-sns-inner">
									<span style="font-weight: 900; color: gray;"><fmt:parseDate
											value="${articleVO.artTime }" pattern="yyyy-MM-dd'T'HH:mm"
											var="parsedDateTime" type="both" /> <fmt:formatDate
											pattern="yyyy-MM-dd" value="${ parsedDateTime }" /></span> <span
										style="font-weight: 900; color: gray;">ㆍ 觀看人數 :</span> <span
										id="count"><fmt:formatNumber type="number"
											minFractionDigits="1" maxFractionDigits="1"
											value="${articleVO.artShowCount/1000}" />K</span>
								</div>
							</div>
						</div>
					</a>
				</c:if>
			</c:forEach>
		</div>
	</div>
	<!--     文章專區結束 -->
	<!-- JQuery -->

	<!-- bootstrap js -->
	<script src="https://kit.fontawesome.com/616f19a0b0.js"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script>
		// 	卡片的文章種類
		var artTypeIds = document.getElementsByClassName("card-title");
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
document.getElementById("search-btn").addEventListener("click", function() {
	  var keyword = document.getElementById("keyword").value;
	  var searchLink = document.getElementById("search-link");
	  searchLink.href = "<%=request.getContextPath()%>/article?action=SearchByKeyword&keyword=" + encodeURIComponent(keyword);
	  searchLink.click();
	});
</script>
	<c:import url="/front-end/footer.jsp"></c:import>
</body>
</html>