<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	href="<%=request.getContextPath()%>/back-end/article/css/updateArtPost.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<title>文章修改</title>
</head>

<body>
	<c:import url="/back-end/sidebar.jsp"></c:import>
	<div class="post-box">
		<div class="post-header">
			<h3>文章資料修改</h3>
		</div>

		<form method="post" action="<%=request.getContextPath()%>/article"
			name="form1" enctype="multipart/form-data">

			<div class="show" style="text-align: left;">
				</br>
				<div class="artId">文章編號:&nbsp;&nbsp;${articleVO.artId}</div>
				<div class="userId">會員編號:&nbsp;&nbsp;${articleVO.userId}</div>

				<div>
					文章主題:&nbsp;&nbsp; <select name="artTypeId">
						<option value="1" <c:if test="${'1' eq artTypeId}">selected</c:if>>遊記</option>
						<option value="2" <c:if test="${'2' eq artTypeId}">selected</c:if>>美食</option>
						<option value="3" <c:if test="${'3' eq artTypeId}">selected</c:if>>住宿</option>
						<option value="4" <c:if test="${'4' eq artTypeId}">selected</c:if>>景點</option>
					</select>
				</div>

				<div class="artShowCount">文章瀏覽次數:&nbsp;&nbsp;${articleVO.artShowCount}</div>
				<div class="artTime">
					文章發佈時間:&nbsp;&nbsp;
					<fmt:parseDate value="${articleVO.artTime }"
						pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${ parsedDateTime }" />
				</div>

				<div>
					文章狀態:&nbsp;&nbsp; <select name="artStatus">
						<option value="0" <c:if test="${'0' eq artTypeId}">selected</c:if>>顯示</option>
						<option value="1" <c:if test="${'1' eq artTypeId}">selected</c:if>>不顯示</option>
					</select>
				</div>
				<div class="title">文章標題:&nbsp;&nbsp;${articleVO.artTitle}</div>
				</br> <input type="hidden" name="action" value="AllArtUpdate2"> <input
					type="hidden" name="artId" value="<%=articleVO.getArtId()%>">
				<input type="submit" value="送出修改">
			</div>
			</br>
			<textarea id='editor' class="artContent" name="artContent" style="border-radius: 30px;">${articleVO.artContent}</textarea>
		</form>
	</div>


	<!-- ckeditor Javascript file -->
	<script
		src="<%=request.getContextPath()%>/front-end/article/ckeditor/build/ckeditor.js"></script>

	<script>
		/*ckeditor 後台人員不能編輯只能看 */
      ClassicEditor.create(document.querySelector("#editor"), {
        toolbar: [],
      }).then(editor => {
        editor.enableReadOnlyMode("editor");
        console.log(editor);
      }).catch(error => {
        console.error(error);
      });
    </script>

</body>
</html>