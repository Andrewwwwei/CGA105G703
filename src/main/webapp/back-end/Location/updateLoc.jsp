<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.location.model.LocationVO"%>
	
<%LocationVO locVO = (LocationVO)request.getAttribute("locVO"); %>
    <div class="modal fade" id="update">
        <div class="modal-dialog">

          <form action="loc.do" class="modal-content" method="post">
            <div class="modal-header">
              <h5 class="modal-title fw-bold" id="updateLoc">編輯景點</h5>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
            <div class="modal-body">
              <div class="container">
                <div class="row m-2">
                    <label for="uloc_name" class="col-4">景點名稱 : </label>
                    <input type="text" name="loc_name" class="col-6" id="uloc_name" maxlength="15" placeholder="必填" value="${locVO.locName}" required>
                </div>

                <div class="row m-2">
                    <label for="ulongitude" class="col-4">經度 : </label>
                    <input type="text" name="longitude" class="col-6" id="ulongitude" readonly value="${locVO.longitude}">
                </div>

                <div class="row m-2">
                    <label for="ulatitude" class="col-4">緯度 : </label>
                    <input type="text" name="latitude" class="col-6" id="ulatitude" readonly value="${locVO.latitude}">
                </div>

                <div class="row m-2">
                    <label for="uaddress" class="col-4">地址 : </label>
                    <input type="text" name="address" class="col-6" id="uaddress" maxlength="30" placeholder="必填" value="${locVO.locAddress}" required>
                </div>

                <div class="row m-2">
                    <label for="uphone" class="col-4">連絡電話 : </label>
                    <input type="text" name="phone" class="col-6" id="uphone" maxlength="15" value="${locVO.locPhone}">
                </div>

                <div class="row m-2">
                  <div class="col text-center p-0">
                    <div>景點狀態 : </div>
                  </div>
                  <div class="col-3 text-start">							
                   <input type="radio" name="state" id="puton" value="0" ${locVO.locStatus == 0? "checked" : ""} > 
                   <label for="puton">上架</label>		
                  </div>
                  <div class="col text-start">
                    <input type="radio" name="state" id="putoff" value="1"${locVO.locStatus == 1? "checked" : ""} > 
                    <label for="putoff">下架</label>	
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="locId" value="${locVO.locId}">
              <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
              <button type="submit" class="btn btn-primary" id="updateSend">送出</button>
            </div>
          </form>

        </div>
      </div>
 		<script>
      $(document).ready(function(){
    	  $('#update').modal('show');
    	});
      </script>
      