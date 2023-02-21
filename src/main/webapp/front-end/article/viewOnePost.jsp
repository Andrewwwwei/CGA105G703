<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.Users.model.*"%>

<%
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); // 傳遞打過的資料
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
boolean isCollected = false;
%>

<html lang="en" class="no-js">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<!-- 字體 -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto+Slab:400,700,300,100">
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Roboto:400,400italic,300italic,300,500,500italic,700,900">

<!-- 自己的css js -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/view_one_assert/css/bootstrap.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/view_one_assert/css/font-awesome.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/view_one_assert/css/animate.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/view_one_assert/css/templatemo-style.css">

<!-- bootstrap 載入 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.1/dist/umd/popper.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">

<!-- Fontawesome 載入 -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.14.0/css/all.css">

<!-- jQuery -->
<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

<!-- sweetalert2 載入 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<!-- markdown語法載入 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/showdown/1.9.0/showdown.min.js"></script>

<title>7 Tour | 單一文章頁面</title>
</head>
<body>
	<c:import url="/front-end/header.jsp"></c:import>
	<!-- #pageloader -->
	<section id="pageloader">
		<div class="loader-item fa fa-spin colored-border"></div>
	</section>

	<div class="content-wrapper">
		<div class="inner-container container " style="width: 70%">

			<!--留言串上方區塊 -->
			<div class="row">
				<!-- 單一文章照片 -->
				<div class="blog-image col-md-12" style="margin-top: 20px;">
					<img
						src="<%=request.getContextPath()%>/article/DBGifReader?artId=${articleVO.artId}"
						style="border-radius: 1px; backdrop-filter: blur(6px); opacity: 0.8;">
				</div>
				<!-- 單一文章內容 -->
				<div class="blog-info col-md-12">
					<div class="box-content" style="margin-bottom: 7px;">

						<h2 class="blog-title bolder"
							style="display: block; border-left: 8px solid; margin: 0.33em 0 0.30em; padding-left: 1em; border-color: #ff613; font-weight: bolder;">${articleVO.artTitle}</h2>

						<div class="article-actions top d-flex">
							<div class="profile-picture" style="margin-top: 20px;">
								<img
									src="<%=request.getContextPath()%>/user/DBGifReader?userId=${articleVO.userId}"
									style="" alt="Profile Picture">
							</div>

							<span
								style="font-weight: bold; color: rgb(89, 84, 84); font-size: 20px; margin-left: 2px;]"
								class="align-self-end">&nbsp;&nbsp;By&nbsp;</span> <span
								style="font-weight: bold; color: rgb(89, 84, 84); font-size: 18px;"
								class="align-self-end">${articleVO.getUsersVO().userName}&nbsp;</span>


							<span
								style="color: color: rgb(89, 84, 84color : color : rgb(89, 84, 84)); font-weight: bold; margin-bottom: 2px"
								class="align-self-end">&nbsp;&nbsp;<fmt:parseDate
									value="${articleVO.artTime }" pattern="yyyy-MM-dd'T'HH:mm"
									var="parsedDateTime" type="both" /> <fmt:formatDate
									pattern="yyyy.MM.dd" value="${ parsedDateTime }" />&nbsp;&nbsp;&nbsp;
							</span> <i class="bi bi-eye-fill align-self-end"
								style="font-size: 17px; margin-bottom: 6px; color: rgb(89, 84, 84);"></i>
							&nbsp; <span id="count" class="align-self-end"
								style="margin-bottom: 2px;]"><fmt:formatNumber
									type="number" minFractionDigits="1" maxFractionDigits="1"
									value="${articleVO.artShowCount/1000}" />K</span>
						</div>

						<div id="content">${articleVO.artContent}</div>


						<div style="display: inline-block;">
							<%
							if (!isCollected) {
							%>
							<form method="post"
								action="<%=request.getContextPath()%>/collectionServlet"
								style="margin-bottom: 0px;" id="formLove">
								<input type="hidden" name="artId" value="${articleVO.artId}">
								<input type="hidden" name="action" value="colartinsert">
								<button class="button1" id="favouritepost" type="submit"
									style="border-radius: 9px;">收藏</button>
								&nbsp;
							</form>
							<%
							}
							%>
						</div>

						<div style="display: inline-block;">
							<c:if test="${usersVO.userId!=articleVO.userId}">
								<a
									href=" <%=request.getContextPath()%>/articleReport?action=addreport&userId=${sessionScope.usersVO.getUserId()}&artId=${articleVO.artId}">
									<button class="button1"
										style="border-radius: 9px; color: #628ec0;" id="reportbtn">檢舉</button>
								</a>
							</c:if>
						</div>

					</div>
				</div>
			</div>
			<!--留言串上方區塊結束 -->
			<!-- 留言串下方區塊開始 -->
			<section id="pd_review">
				<div class="pd-collapse-row">

					<!-- 	留言串開始 -->
					<c:forEach var="articleReplyVO" items="${articleReplyVOList}">
						<c:if test="${ articleReplyVO.replyStatus == 0}">
							<div class="d-flex border-top p-2  mt-1"
								style="background: white; border-radius: 3px;">
								<div>
									<div class="avatar avatar-lg " id="Pinf"
										style="display: inline-block;">
										<img
											src="<%=request.getContextPath()%>/user/DBGifReader?userId=${articleReplyVO.userId}"
											id="imgPinf" />
									</div>
								</div>
								<div class="col">
									<div class="rating-star  ps-2 "
										style="display: inline-block; position: relative; top: 2px; font-weight: bold;">
										<h6 style="font-weight: bold;">${articleReplyVO.getUsersVO().userName}</h6>

										<span style="font-weight: bold; color: lightgray;"
											class="align-text-end"><fmt:parseDate
												value="${articleReplyVO.replyTime}"
												pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime"
												type="both" /> <fmt:formatDate pattern="yyyy-MM-dd"
												value="${ parsedDateTime }" /></span>
									</div>

									<div style="display: inline-block; position: relative;">
										<span class="element"
											style="color: #2a2416; font-weight: border; font-size: 16px; padding-right: 30px;">${articleReplyVO.replyContent}</span>
									</div>

									<div style="display: inline-block; position: relative;">
										<c:if test="${userId.equals(articleReplyVO.userId)}">
											<a
												href=" <%=request.getContextPath()%>/article/reply?action=myMsgDelete&artId=${articleVO.artId}&artReplyId=${articleReplyVO.artReplyId}&userId=${sessionScope.usersVO.getUserId()}"
												id="formA">
												<button
													style="margin-left: 20px; border-radius: 9px; float: right;">刪除</button>
											</a>
										</c:if>
									</div>

								</div>
							</div>
						</c:if>
					</c:forEach>

					<!-- 	留言串結束  開始留言-->
					<div class="collapse show">
						<div class="pd-collapse-box">
							<div class="row align-items-end ">
								<!-- 留言按鍵 -->
								<div class="text-sm-left p-3">
									<a class="me-4" data-bs-toggle="collapse" href="#pd_add_review"
										role="button" aria-expanded="false"
										aria-controls="pd_add_review"
										style="font-weight: bold; font-size: 1.3em; color: #625855;">留言</a>
								</div>
								<!-- 會員留言開啟 -->
								<div class="col-12 collapse" id="pd_add_review">

									<div class="my-1 p-3 bg-gray-100 border">

										<form method="post"
											action="<%=request.getContextPath()%>/article/reply"
											name="msgform" id="replyContentForm">

											<div class="row g-2">
												<div class="col-sm-12">
													<label class="form-label">寫下你的留言</label>
													<textarea rows="5" class="form-control" name="replyContent">${articleReplyVO.replyContent}</textarea>
												</div>

												<div class="col-sm-6 ">
													<input type="hidden" name="artId"
														value="${articleVO.artId}"> <input type="hidden"
														name="userId" value="${sessionScope.usersVO.getUserId()}">
													<input type="hidden" name="action" value="insert_reply">
													<input type="button" id="msgSubmit" value="送出">

												</div>
											</div>
										</form>
									</div>
								</div>
								<!-- 會員留言結束 -->
							</div>
						</div>
					</div>
					<!--留言結束 -->
				</div>
			</section>
			<!-- 留言串下方區塊結束 -->
		</div>
	</div>


	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/article/view_one_assert/js/plugins.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/article/view_one_assert/js/main.js"></script>

	<script src="https://cdn.jsdelivr.net/npm/promise-polyfill"></script>

	<script>
		document.getElementById("content").innerHTML = "${articleVO.artContent}";
	</script>

	<!-- Preloader 載入畫面-->
	<script type="text/javascript">
		$(window).load(function() { // makes sure the whole site is loaded
			$('.loader-item').fadeOut(); // will first fade out the loading animation
			$('#pageloader').delay(150).fadeOut('slow'); // will fade out the white DIV that covers the website.
			$('body').delay(150).css({
				'overflow-y' : 'visible'
			});
		})
	</script>
	<script type="text/javascript">
	let userJs ="<c:out value='${usersVO.userId}'/>";
	
// 	let favouritePost = document.querySelector("#favouritePost");
// 	if (!isCollected) {
// 	  favouritePost.style.display = "block";
// 	  favouritePost.addEventListener("click", function() {
// 	    if (userJs.length === 0) {
// 	      alert("请先登录!");
// 	    } else {
// 	      alert("已加入文章收藏");
// 	      setTimeout(function() {
// 	        document.querySelector("#formLove").submit();
// 	      }, 6000);
// 	    }
// 	  });
// 	} else {
// 	  favouritePost.style.display = "none";
// 	}

	let msgSubmit = document.querySelector("#msgSubmit").addEventListener(
  "click", function() {
    if (userJs.length === 0) {
      Swal.fire({
        title: "请先登录!",
        text: "",
        icon: "warning",
        confirmButtonText: "确定"
      });
    } else if (msgform.replyContent.value === "") {
      Swal.fire({
        title: "留言要有文字喔~",
        text: "",
        icon: "warning",
        confirmButtonText: "确定"
      });
    } else if (msgform.replyContent.value !== "") {
      document.querySelector("#replyContentForm").submit();
    }
  });
	</script>
	<c:import url="/front-end/footer.jsp"></c:import>
</body>
</html>