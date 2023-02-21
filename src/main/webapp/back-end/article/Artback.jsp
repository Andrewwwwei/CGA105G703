<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@page import="com.Users.model.*"%>
<%@page import="com.article.model.*"%>
<%@page import="com.article_report.model.*"%>


<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>7Tour | 後台檢舉頁面</title>
<!-- 自己的bootstrap -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- datatable basic cdn  -->
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


<style>
body {
	font-family: 'Noto Sans TC', sans-serif;
background: #8e9eab;  /* fallback for old browsers */
background: -webkit-linear-gradient(to right, #eef2f3, #8e9eab);  /* Chrome 10-25, Safari 5.1-6 */
background: linear-gradient(to right, #eef2f3, #8e9eab); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */


}

div.workplace {
	margin-left: 250px !important;
	margin-bottom: 200px !important;
	display: inline-block !important;
	justify-content: center !important;
	position: absloute !important;
	text-align: center !important;
}

/* datatable */
td {
	max-width: 250px !important;
	overflow: hidden !important;
	text-overflow: ellipsis !important;
	white-space: nowrap !important;
	text-align: center !important;
}

.showTd {
	max-width: none !important;
	overflow: visible !important;
	white-space: normal !important;
}

.workplace {
	text-align: center !important;
}

h4 {
	color: #33344f;!important;
	font-weight: bold !important;
	font-family: 'Trebuchet MS', 'Lucida Sans Unicode', 'Lucida Grande',
		'Lucida Sans', Arial, sans-serif !important;
	line-height: 700px;
	font-size: 3em !important;
	opacity: 0.9;
}

input[type='submit'] {
	width: 300px;
	height: 300px;
	padding: 10px 20px;
	color: #3d4280;
	text-decoration: none;
	text-transform: uppercase;
	transition: .5s;
	letter-spacing: 4px;
	border: none;
	border-radius: 30px;
	font-family: 'Lucida Grande', 'Lucida Sans', Arial, sans-serif;
	font-weight: bold;
	font-size: 2em !important;
	position: absolute;
	top: 160px;
	bottom: 100;
	line-height: 30px;
	background-color: #e3e2bb;
}

input[type='submit']:hover {
	width: 300px;
	height: 300px;
	box-shadow: 0 0 5px #a1a1a1;
	position: absolute;
}
</style>
</head>

<body>
	<c:import url="/back-end/sidebar.jsp"></c:import>
	<div class="workplace " style="top: 1px;]">
		<h4 style="text-align: center;">""</h4>

		<div
			style="display: flex; justify-content: start; align-items: center;">

			<div style="width: 300px; height: 300px; margin: 0 10px;">
				<form method="post"
					action="<%=request.getContextPath()%>/articleReport">
					<input type="hidden" name="action" value="listAllArtReport"> 
					<input type="submit" class="fs-6" value="後台檢舉管理">
				</form>
			</div>

			<div style="width: 300px; height: 300px; margin: 0 10px;">
				<form method="post" action="<%=request.getContextPath()%>/article">
					<input type="hidden" name="action" value="listAllArt"> 
					<input type="submit" class="fs-6" value="後台文章管理">
				</form>
			</div>

		</div>
	</div>

</body>
</html>