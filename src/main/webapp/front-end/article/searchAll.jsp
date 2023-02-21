<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>


<%
ArticleService artSvc = new ArticleService();
List<ArticleVO> list = artSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- css -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/css/forumhome.css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">
<title>7 Tour | 論壇首頁</title>
</head>

<body style="  background: #efefef;">
	<c:import url="/front-end/header.jsp"></c:import>
	<!--header -->

	<div class="body-content">
		<div class="container">
			<div class="d-flex align-items-end justify-content-between mt-4 ">

				<div class="forum-header text-start mb-1 mt-2 pb-1"
					style="width: 200px;">
					<h4>搜尋結果</h4>
				</div>

				<div class="d-flex">
					<div class="d-flex text-end mt-3 "
						style="margin-right: 20px; margin-top: 24px;">
						<div class="input-group mb-3 text-end">
							<div class="btn-group">
								<button class="btn btn-outline-secondary dropdown-toggle "
									type="button" data-bs-toggle="dropdown" aria-expanded="false">快速查詢</button>
								<ul class="dropdown-menu">
									<li><a class="dropdown-item"
										href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=1">遊記</a></li>
									<li><a class="dropdown-item"
										href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=2">美食</a></li>
									<li><a class="dropdown-item"
										href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=3">住宿</a></li>
									<li><a class="dropdown-item"
										href="<%=request.getContextPath()%>/article?action=SearchByArtType&artTypeId=4">景點</a></li>
									<li><hr class="dropdown-divider"></li>
									<li><a class="dropdown-item"
										href="
										<%=request.getContextPath()%>/article?action=powerSearchAllPost">搜尋全部</a></li>
								</ul>
							</div>
						</div>
					</div>
					<div class="text-end mb-1 mt-3" style="margin-right: 10px;">
						<a class="btn btn-outline-secondary"
							href="<%=request.getContextPath() %>/article?action=userPosted&userId=${sessionScope.usersVO.getUserId()}"
							role="postart">我的文章</a>
					</div>
					<div class="text-end mb-1 mt-3">
						<a class="btn btn-outline-secondary"
							href="<%=request.getContextPath() %>/article?action=addart&userId=${sessionScope.usersVO.getUserId()}"
							role="postart">發佈文章</a>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 文章專區 -->
	<div class="container mb-2">
		<form action="<%=request.getContextPath()%>/article" method="post"
			id="artId">
			<input type="hidden" name="action" value="SelectOnePost_Display">
			<input type="hidden" name="artId" value="${articleVO.artId}">
			<div class="row row-cols-sm-1 row-cols-lg-5 g-2 h-250px ">
				<c:forEach var="articleVO" items="${list}" varStatus="status">
					<div class="col">
						<div class="card border-0 h-100" onclick="document.getElementById('artId').submit()">
							<img src="<%=request.getContextPath()%>/article?action=DBGifReader&artId=${articleVO.artId}"
								class="card-img-top" style="filter: brightness(80%);" >
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
					</div>
				</c:forEach>
			</div>
		</form>
	</div>

	<!-- JQuery -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
// 	輪播圖的文章種類
    for (let i = 0; i < ${list.size()}; i++) {
        let artTypeId = document.getElementById("artTypeId" + i);
        if (artTypeId.innerHTML === "1") {
            artTypeId.innerHTML = "遊記";
            artTypeId.style.color = "#68c6ea";
            artTypeId.style.fontWeight = 'bold';
        } else if (artTypeId.innerHTML === "2") {
            artTypeId.innerHTML = "美食";
            artTypeId.style.color = "#eac368";
            artTypeId.style.fontWeight = 'bold';
        } else if (artTypeId.innerHTML === "3") {
            artTypeId.innerHTML = "住宿";
            artTypeId.style.color = "#45e9b8";
            artTypeId.style.fontWeight = 'bold';
        } else if (artTypeId.innerHTML === "4") {
            artTypeId.innerHTML = "景點";
            artTypeId.style.color = "#9898f8";
            artTypeId.style.fontWeight = 'bold';
        }
    }
    
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
            artTypeIds[i].style.color =  "#9898f8";
        }
    }
    
</script>

	<!-- footer -->
<c:import url="/front-end/footer.jsp"></c:import>
</body>
</html>