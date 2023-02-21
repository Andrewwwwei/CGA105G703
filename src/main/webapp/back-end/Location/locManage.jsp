<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<%@ page import="java.util.*" %>
<%@ include file="header.jsp"%>
<title>7Tour | 景點管理</title>
<!-- ---------------------main body區 start--------------------->
<div class="workplace mt-2">
	<div class="container">
		<div class="row">
			<div class="col p-0">
				<div class="fw-bold text-start fs-3">
					<a href="locManage.jsp" class="nav-link"> 景點資訊管理</a>
				</div>
			</div>

			<div class="col p-0  text-end">
				<button type="button" class="btn btn-primary py-1 px-2 mx-3"
					data-bs-toggle="modal" data-bs-target="#add">+新增景點</button>
				<form action="loc.do" method="post" class="d-inline">
					<input type="text" placeholder="Search..." id="searchLoc" name="word">
					<button class="btn btn-primary py-1 px-2" type="submit">
						<i class="bi bi-search"></i>
					</button>
					<input type="hidden" name="action" value="search">
				</form>
			</div>
		</div>
		<div class="row">

		<%@ include file="getAllLoc.jsp"%>

		</div>

		<!-- 			新增 content start -->
		<%@ include file="addLoc.jsp"%>
		<!-- 新增 content end -->
	</div>
</div>
<!-- ---------------------main body區 end--------------------->

<%@ include file="footer.jsp"%>
