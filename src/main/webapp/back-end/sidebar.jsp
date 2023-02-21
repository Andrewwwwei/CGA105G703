<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ===================== Bootstrap 5 CSS ========================== -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/emp/css/back.css">
<body>
  <!-- ---------------------aside body區 start--------------------->
  <aside class="aside">

	<a href="<%=request.getContextPath()%>/back-end/backpage.jsp" style="display: flex; justify-content: center;">
		<img src="<%=request.getContextPath()%>/back-end/images/logo.png" width="80%">
	</a>  
	
 	<ul class="aside_list">
    <c:forEach var="permissionVO" items="${sessionScope.permissionVOList}">
		<c:if test="${permissionVO.funcId == 11}">
			<li><a id="empcontrol"
				href="<%=request.getContextPath()%>/back-end/emp/Emplistallpage.jsp">
				員工管理</a></li>
		</c:if>
	
		<c:if test="${permissionVO.funcId == 12}">
			<li id="usercontrol"><a href="<%=request.getContextPath()%>/EmpServlet?action=usercontrol">
			會員管理</a></li>
		</c:if>
	
		<c:if test="${permissionVO.funcId == 14}">
			<li><a href="<%=request.getContextPath()%>/back-end/permisFunc/PermisFuncListAll.jsp">
			權限管理</a></li>	
		</c:if>
		
		<c:if test="${permissionVO.funcId == 13}">
			<li><a href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp">
			廠商及房型管理</a></li>
		</c:if>
		
        <c:if test="${permissionVO.funcId == 17}">
             <li><a href="#">
             訂單管理</a></li>
        </c:if>
        
		<c:if test="${permissionVO.funcId == 15}">
		    <li><a href="<%=request.getContextPath()%>/back-end/article/Artback.jsp">
		    論壇管理</a></li>
		</c:if>
		
		<c:if test="${permissionVO.funcId == 16}">
             <li><a href="<%=request.getContextPath()%>\back-end\act\select_ActHome.jsp">
             揪團管理</a></li>
        </c:if>
        
        <c:if test="${permissionVO.funcId == 18}">
             <li><a href="<%=request.getContextPath()%>\back-end\Location\locManage.jsp">
             景點管理</a></li>
        </c:if>
        
        <c:if test="${permissionVO.funcId == 43}">
             <li><a href="<%=request.getContextPath()%>/back-end/customerService/customer_service_back.jsp">
             客服管理</a></li>
        </c:if>
          <c:if test="${permissionVO.funcId == 19}">
             <li><a href="<%=request.getContextPath()%>/back-end/faq/FAQListAll.jsp">
             常見問答</a></li>
        </c:if>
        
        <c:if test="${permissionVO.funcId == 44}">
             <li><a href="<%=request.getContextPath()%>/back-end/declaration/?action=aside">
             公告管理</a></li>
        </c:if>
	</c:forEach>
	 </ul>
    <div style="color:red; font-size:14px; margin-bottom: 4px;">使用者:${sessionScope.empVO.empName}</div>
    <div style="color:red; font-size:14px; margin-bottom: 2px;">登入身分:${sessionScope.empVO.jobTitle}</div>
    <button id="loginOut"style="margin: 10px 0 10px 0"><a href="<%=request.getContextPath()%>/EmpServlet?action=loginOut">登出</a></button>
    <button style="margin: 0"><a href="#">修改密碼</a></button>
  </aside>
  
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
 </html>