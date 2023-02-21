<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.Users.model.*"%>

<%
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO"); // 傳遞打過的資料
UsersVO usersVO = (UsersVO) session.getAttribute("usersVO");
%>


<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/css/addArtPost.css">
<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>

<title>7 Tour | 發文首頁</title>
</head>

<body>
<c:import url="/front-end/header.jsp" ></c:import>
	<div class="post-box">
		<div class="post-header" style="height: 220px">
			<h3>發文頁面</h3>
		</div>

		<%-- 錯誤表列 --%>
		<div class="error">
			<c:if test="${not empty errorMsgs}">
				<font style="color: red;">請修正以下錯誤:</font>
				<c:forEach var="message" items="${errorMsgs}">
					<p style="color: red">${message}</p>
				</c:forEach>
			</c:if>
		</div>

		<form method="post" action="<%=request.getContextPath()%>/article"
			name="form1" enctype="multipart/form-data">

			<br>
			<div class="title">
				<input type="text" name="artTitle"
					value="<%=(articleVO == null) ? "請輸入標題" : articleVO.getArtTitle()%>" />
			</div>


			<input type="hidden" name="userId"
				value="${sessionScope.usersVO.getUserId() }" />

			<div class="radios">
				<input type="radio" id="travel" name="artTypeId" value="1" checked
					style="width: 16px; height: 16px"> <label for="travel"
					style="font-size: 15PX;">遊記</label> <input type="radio" id="food"
					name="artTypeId" value="2" style="width: 16px; height: 16px">
				<label for="food" style="font-size: 15PX;">美食</label> <input
					type="radio" id="hotel" name="artTypeId" value="3"
					style="width: 16px; height: 16px"> <label for="hotel"
					style="font-size: 15PX;">住宿</label> <input type="radio"
					id="location" name="artTypeId" value="4"
					style="width: 16px; height: 16px"> <label for="location"
					style="font-size: 15PX;">景點</label>
			</div>

			<br> <input id="artPic" name="artPic" type="file"
				class="upl form-control" multiple="multiple"
				onchange="hideContent('artPic.errors');">

			<div class="ckeditor">
				<div id="container">
					<textarea id='editor' name="artContent">${articleVO.artContent}</textarea>
				</div>
			</div>

			<input type="hidden" name="action" value="insert"> 
			<input type="submit" class="fs-6" value="送出">

			<div style="text-align: start;">
				<img class="preview" style="max-width: 250px; max-height: 300px;">
				<div class="size"></div>
			</div>
		</form>
	</div>

<!-- 	<!-- ckeditor Javascript file --> -->
 <script src="<%=request.getContextPath()%>/front-end/article/ckeditor/build/ckeditor.js"></script>


<script>
  /*ckeditor */
  ClassicEditor
    .create(document.querySelector('#editor'))
    .catch(error => {
      console.error(error);
    });
</script>

	<!-- 預覽圖片 -->
	<script>
    $(function () {

      function format_float(num, pos) {
        var size = Math.pow(10, pos);
        return Math.round(num * size) / size;
      }

      function preview(input) {

        if (input.files && input.files[0]) {
          var reader = new FileReader();

          reader.onload = function (e) {
            $('.preview').attr('src', e.target.result);
            var KB = format_float(e.total / 1024, 2);
            $('.size').text("檔案大小：" + KB + " KB");
          }

          reader.readAsDataURL(input.files[0]);
        }
      }

      $("body").on("change", ".upl", function () {
        preview(this);
      })
    })
  </script>

	<!--   叫出成功發文的圖示 -->
	<script>
// function showPopover() {
//   $('.popover-test').popover('show');
// }
</script>
<c:import url="/front-end/footer.jsp" ></c:import>
</body>
</html>