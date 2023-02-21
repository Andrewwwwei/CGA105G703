<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.locationPic.model.LocationPicVO"%>

<%
Integer locId = (Integer)request.getAttribute("locId");
%>

<div class="modal fade" id="updatePic">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title fw-bold" id="pic">編輯圖片</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">

				<div id="viewPic" class="carousel slide carousel-dark"
					data-bs-interval="false">
					<div class="carousel-indicators">
					 <c:forEach var="locPicVO" items="${locPicVO}" varStatus="number">
					  <c:if test="${number.index == 0}">
						<button type="button" data-bs-target="#viewPic"
							data-bs-slide-to="0" class="active" aria-current="true"></button>
					  </c:if>
					  <c:if test="${number.index != 0}">
						<button type="button" data-bs-target="#viewPic"
							data-bs-slide-to="${number.index}"></button>
					  </c:if>
					</c:forEach>

					</div>
					<div class="carousel-inner">
					  <c:forEach var="locPicVO" items="${locPicVO}" varStatus="number">
					  <c:if test="${number.index == 0}">
						<div class="carousel-item active" style="height: 250px;">														  
							<img src="data:image/png;base64,${Base64.getEncoder().encodeToString(locPicVO.locPic)}" class="d-block w-100 h-100">
						<form class="mt-2" method="post" action="locPic.do">
							<input type="hidden" name="action" value="delete">
						    <input type="hidden" name="LOC_PIC_ID" value="${locPicVO.locPicId}">
						    <input type="hidden" name="LOC_ID" value="${locPicVO.locId}">
							<button type="submit" class="btn btn-danger py-1 px-2 del-img">
							<i class="bi bi-trash3-fill"></i>
							</button>
						</form>
						</div>
					  </c:if>
					  <c:if test="${number.index != 0}">
						 <div class="carousel-item" style="height: 250px;">
							<img src="data:image/png;base64,${Base64.getEncoder().encodeToString(locPicVO.locPic)}" class="d-block w-100 h-100">
						 <form class="mt-2" method="post" action="locPic.do">
							<input type="hidden" name="action" value="delete">
						    <input type="hidden" name="LOC_PIC_ID" value="${locPicVO.locPicId}">
						    <input type="hidden" name="LOC_ID" value="${locPicVO.locId}">
							<button type="submit" class="btn btn-danger py-1 px-2 del-img">
							<i class="bi bi-trash3-fill"></i>
							</button>
						</form>
						</div>
					  </c:if>
					  </c:forEach>
					  <c:if test="${locPicVO.isEmpty()}">
					  	<h3>查無資料</h3>
					  </c:if>
					</div>
					<c:if test="${!locPicVO.isEmpty()}">
					<button class="carousel-control-prev" type="button"
						data-bs-target="#viewPic" data-bs-slide="prev">
						<span class="carousel-control-prev-icon"></span> <span
							class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button"
						data-bs-target="#viewPic" data-bs-slide="next">
						<span class="carousel-control-next-icon"></span> <span
							class="visually-hidden">Next</span>
					</button>
					</c:if>
				</div>

			</div>
	
		<p class="text-start px-3 m-0">圖片共有 : <font color="red">${locPicVO.size()}</font>張</p>
		<form method="post" action="locPic.do" enctype="multipart/form-data">
			<div class="input-group p-3">
				<label class="input-group-text" for="addpic">新增圖片</label> 
				<input type="file" class="form-control" id="addpic" name="picture" multiple accept=".jpg,.png,.jpeg">
			</div>
			<input type="hidden" name="action" value="addPic">
			<input type="hidden" name="LOC_ID" value="<%=locId%>">
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
				<button type="submit" class="btn btn-primary">送出</button>
			</div>
		</form>
			
		</div>
	</div>
</div>
<script>
	$(document).ready(function() {
		$('#updatePic').modal('show');
	});
</script>