<%@page import="com.location.model.LocationVO"%>
<%@page import="com.location.model.LocationService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List"%>
<%
String search =(String)request.getAttribute("search");
String searchWord;
List<LocationVO> list;
if("search".equals(search)){
	list = (List<LocationVO>)request.getAttribute("searchList");
	searchWord = (String)request.getAttribute("searchWord");
}else{
LocationService locSvc = new LocationService();
list = locSvc.getAll();
}
pageContext.setAttribute("list", list);	
%>
<%@ include file="page1.file" %>
<c:if test="${!list.isEmpty()}">
<table class="table" id="loctable">
	<thead>
		<tr>
			<th scope="col">編號</th>
			<th scope="col">景點名稱</th>
			<th scope="col">地址</th>
			<th scope="col">聯絡電話</th>
			<th scope="col">景點狀態</th>
			<th scope="col">圖片</th>
			<th scope="col">編輯</th>
		</tr>
	</thead>
	<tbody>
		
		<c:forEach var="locVO" items="${list}" varStatus="s" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr class="align-middle">
				<td>${s.index+1}</td>
				<td>${locVO.locName}</td>
				<td>${locVO.locAddress}</td>
				<td>${locVO.locPhone == null? "查無資料" : locVO.locPhone}</td>
				<td>${locVO.locStatus == 0? "上架" : "未上架"}</td>
				<td>

					<a href="locPic.do?LOC_ID=${locVO.locId}&action=get_For_Update" class="btn btn-primary py-1 px -2">預覽圖片</a>
				</td>
				<td>
					<a href="loc.do?LOC_ID=${locVO.locId}&action=getOne_For_Update" class="btn btn-primary py-1 px-2">編輯</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
</c:if>

<c:if test="${list.isEmpty()}">
<h3>搜尋項目 : <font color="red">${searchWord}</font></h3>
<h3>查無資料</h3>
</c:if>

	  <!-- 編輯 modal content start -->
	  <c:if test="${ openModal != null }">
      <%@ include file="updateLoc.jsp" %>
	  </c:if> 	  
      <!-- 編輯 modal content end -->
      
      <!-- 編輯圖片 content start -->
      <c:if test="${ openPicModal != null }">
	  <%@ include file="updatePic.jsp" %>
	  </c:if>
	  <!-- 編輯圖片 content end -->

<% if("search".equals(search)&& !list.isEmpty()){ %>
	<%@ include file="page2_Query.file" %>
<%}else if(list.isEmpty()){%>

<%}else{%>
	<%@ include file="page2.file" %>
<%}%>