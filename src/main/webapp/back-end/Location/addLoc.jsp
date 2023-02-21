<%@page import="com.location.model.LocationVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="modal fade" id="add" tabindex="-1" aria-labelledby="addLoc"
	aria-hidden="true">
	<div class="modal-dialog">
		<form action="loc.do" class="modal-content" method="post" enctype="multipart/form-data">
			<div class="modal-header">
				<h5 class="modal-title fw-bold" id="addLoc">新增景點</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="container">
					<div class="row m-2">
							<label for="aloc_name" class="col-4">景點名稱 : </label>
							<input type="text" name="loc_name" maxlength="15" id="aloc_name" class="col-6" placeholder="必填" required>
					</div>
							<input type="hidden" id="addLocalongitude" name="longitude"><!-- 經度 : -->
							<input type="hidden" id="addLocalatitude" name="latitude"><!-- 緯度 : -->
					<div class="row m-2">
							<label for="addAddress" class="col-4">地址 : </label>
							<input type="text" name="address" id="addAddress" maxlength="30" class="col-6" placeholder="必填" required>
					</div>
					<div class="row m-2 ">
							<label for="aphone" class="col-4">連絡電話 : </label>
							<input type="text" name="phone" maxlength="15" class="col-6" id="aphone">
					</div>
					<div class="input-group p-4 pt-1">
						<label class="input-group-text" for="Apicture">景點圖片 : </label> 
						<input type="file" class="form-control" name="picture" id="Apicture" accept=".jpg,.png,.jpeg" multiple>
					</div>
				</div>
			</div>
			<input type="hidden" name="action" value="insert">
			<input type="hidden" name="locStatus" value="0">			
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary"
					data-bs-dismiss="modal">取消</button>
				<button type="button" class="btn btn-primary" id="send" onclick="codeAddress(this)">送出</button>
			</div>
		</form>
	</div>
</div>