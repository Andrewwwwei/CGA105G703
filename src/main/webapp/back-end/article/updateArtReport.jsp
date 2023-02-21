<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.article.model.*"%>
<%@ page import="com.article_report.model.*"%>


<%
ArticleReportVO articleReportVO = (ArticleReportVO) request.getAttribute("articleReportVO");
ArticleVO articleVO = (ArticleVO) request.getAttribute("articleVO");
%>

<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- CSS -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/article/css/updateArtReport.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- JavaScript Bundle with Popper -->
<script src="https://code.jquery.com/jquery-3.1.0.js"></script>
<script
	src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
<title>文章檢舉修改</title>
</head>

<body>
	<c:import url="/back-end/sidebar.jsp"></c:import>
	<div class="post-box ">

		<div class="post-header">
			<h3>文章檢舉修改</h3>
		</div>

		<form method="post"
			action="<%=request.getContextPath()%>/articleReport">

			<div class="show" style="text-align: left;">
				</br>
				<div class="artRpId">文章檢舉編號:&nbsp;&nbsp;${articleReportVO.artRpId}</div>
				<div class="artId">文章編號:&nbsp;&nbsp;${articleReportVO.artId}</div>
				<div class="artTitle">文章標題:&nbsp;&nbsp;${articleReportVO.articleVO.artTitle}</div>
				<div class="userId">檢舉會員編號:&nbsp;&nbsp;${articleReportVO.userId}</div>
				<div>
					文章檢舉狀態:&nbsp;&nbsp; <select name="rpStatus" style="color: #272620;">
						<option value="0" style="color: #5834f8;"
							<c:if test="${'0' eq rpStatus}">selected</c:if>>未處理</option>
						<option value="1" style="color: #a9231c;"
							<c:if test="${'1' eq rpStatus}">selected</c:if>>通過</option>
						<option value="2" style="color: #6a5655;"
							<c:if test="${'2' eq rpStatus}">selected</c:if>>不通過</option>
					</select>
				</div>
				<div class="rpReason">
					檢舉事由:&nbsp;
					<c:choose>
						<c:when test="${articleReportVO.rpReason == 0}">惡意洗版、重複張貼</c:when>
						<c:when test="${articleReportVO.rpReason == 1}">包含未成年、裸露、色情內容</c:when>
						<c:when test="${articleReportVO.rpReason == 2}">仇恨言論</c:when>
						<c:when test="${articleReportVO.rpReason == 3}">廣告商業宣傳</c:when>
						<c:otherwise>其他</c:otherwise>
					</c:choose>
				</div>
				<div class="rpContent">檢舉原因:&nbsp;&nbsp;${articleReportVO.rpContent}</div>
				<div class="rpTime">
					檢舉時間:&nbsp;&nbsp;
					<fmt:parseDate value="${articleReportVO.rpTime}"
						pattern="yyyy-MM-dd'T'HH:mm" var="parsedDateTime" type="both" />
					<fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${ parsedDateTime }" />
				</div>

				</br> <input type="hidden" name="action" value="allReportUpdate2">
				<input type="hidden" name="artRpId"
					value="<%=articleReportVO.getArtRpId()%>"> <input
					type="submit" value="送出修改">
			</div>
			</br>
			<textarea id='editor' class="artContent" name="artContent">${articleReportVO.articleVO.artContent}</textarea>
		</form>
	</div>

	<!-- jquery -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<!-- ckeditor Javascript file -->
	<script
		src="<%=request.getContextPath()%>/front-end/article/ckeditor/build/ckeditor.js"></script>

	<script>
		let rpReason = document.getElementsByClassName("rpReason");
		for (let i = 0; i < rpReason.length; i++) {
			if (rpReason[i].innerHTML === "0") {
				rpReason[i].innerHTML = "惡意洗版、重複張貼";
			} else if (rpReason[i].innerHTML === "1") {
				rpReason[i].innerHTML = "包含未成年、裸露、色情內容";
			} else if (rpReason[i].innerHTML === "2") {
				rpReason[i].innerHTML = "仇恨言論";
			}else if (rpReason[i].innerHTML === "3") {
				rpReason[i].innerHTML = "廣告商業宣傳";
			}
		}
	</script>

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