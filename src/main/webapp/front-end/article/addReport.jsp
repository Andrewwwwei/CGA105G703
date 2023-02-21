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
	href="<%=request.getContextPath()%>/front-end/article/css/addReport.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<!-- sweetalert2 載入 -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

<title>7 Tour | 會員檢舉頁面</title>
</head>

<body>
	<c:import url="/front-end/header.jsp"></c:import>
	<div class="post-box">
		<div class="post-header" style="height: 180px">
			<h3>檢舉文章</h3>
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

		<form method="post"
			action="<%=request.getContextPath()%>/articleReport" id="addForm">

			<br> <input type="hidden" name="userId"
				value="${sessionScope.usersVO.getUserId() }" />

			<div class="">
				&nbsp;&nbsp; <select name="rpReason" class="form-select"
					aria-label="Default select example">
					<option value="0" <c:if test="${'1' eq rpReason}">selected</c:if>>惡意洗版、重複張貼</option>
					<option value="1" <c:if test="${'2' eq rpReason}">selected</c:if>>包含未成年、裸露、色情內容</option>
					<option value="2" <c:if test="${'3' eq rpReason}">selected</c:if>>仇恨言論</option>
					<option value="3" <c:if test="${'4' eq rpReason}">selected</c:if>>廣告商業宣傳
					</option>
				</select>
			</div>

			<br>
			<div class="form-group mb-3" style="height: 100px;">
				<label for="rpContent">${articleReportVO.rpContent}</label>
				<textarea name="rpContent" class="form-control" id="rpContent"
					rows="3"></textarea>
			</div>
			<input type="hidden" name="artId" value="${articleVO.artId}">
			<input type="hidden" name="action" value="InsertReport"> <input
				type="submit" class="fs-6" value="送出" id="reportSubmit">
		</form>
	</div>


<script type="text/javascript">

document.querySelector("#reportSubmit").addEventListener("click", function(event) {
  event.preventDefault(); // 阻止表單先交

  if (document.querySelector("#rpContent").value === "") {
	    Swal.fire({
	      icon: 'warning',
	      title: '檢舉內容麻煩填寫下哦~!',
	      text: '',
	    });
	  } else {
	    Swal.fire({
	      icon: 'success',
	      title: '檢舉成功!',
	      text: '',
	    });
	    setTimeout(function() {
	      document.querySelector("#addForm").submit();
	    }, 3000);
	  }
	});
	</script>




	<c:import url="/front-end/footer.jsp"></c:import>
</body>
</html>