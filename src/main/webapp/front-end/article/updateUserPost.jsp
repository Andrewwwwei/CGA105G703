<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.article.model.*"%>

<%
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/article/css/updateUserPost.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
	<!-- markdown語法 -->
<script src="https://cdn.jsdelivr.net/npm/showdown@1.9.1/dist/showdown.min.js"></script>

<title>會員文章修改</title>
</head>

<body>
	<c:import url="/front-end/header.jsp"></c:import>
	<div class="post-box">
		<div class="post-header" style="height: 220px">
			<h3>文章資料修改</h3>
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

			<input type="hidden" name="userId" size="45"
				value="<%=articleVO.getUserId()%>" /> <br>
			<div class="title">
				<input type="text" name="artTitle"
					value="<%=articleVO.getArtTitle()%> " />
			</div>
			<jsp:useBean id="articleSvc" scope="page"
				class="com.article.model.ArticleService" />

			<div class="ckeditor">
				<div id="container">
					<textarea id='editor' name="artContent"><%=articleVO.getArtContent()%></textarea>
				</div>
			</div>
			<div class="d-flex justify-content-start">
				<div>文章主題:&nbsp;&nbsp;</div>
				<select name="artTypeId" >
					<option value="1" <c:if test="${'1' eq artTypeId}">selected</c:if>>遊記</option>
					<option value="2" <c:if test="${'2' eq artTypeId}">selected</c:if>>美食</option>
					<option value="3" <c:if test="${'3' eq artTypeId}">selected</c:if>>住宿</option>
					<option value="4" <c:if test="${'4' eq artTypeId}">selected</c:if>>景點</option>
				</select>
			</div>

			<input type="hidden" name="action" value="UserPost2_Update">
			<input type="hidden" name="artId" value="<%=articleVO.getArtId()%>">
			<input type="submit" value="送出修改">

		</form>
	</div>

	<script
		src="<%=request.getContextPath()%>/front-end/article/ckeditor/build/ckeditor.js"></script>
	<script>
		/*ckeditor */
        ClassicEditor
        .create(document.querySelector('#editor'))
        .catch(error => {
          console.error(error);
        });
        </script>
<c:import url="/front-end/footer.jsp" ></c:import>
</body>
</html>