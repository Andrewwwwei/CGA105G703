<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

    <style>
        /* -------------- nav ---------------- */
        nav div.container div.collapse a:hover {
            font-weight: bolder !important;
        }
        a{
        	text-decoration: none;
        	color: white;
        }
    </style>
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg shadow-sm p-0" style="background-color: #e3f2fd">
        <div class="container mx-4 mw-100">
            <a class="navbar-brand fw-bold text-dark" href="<%=request.getContextPath()%>/RoomOrder?action=toVendorIndex" style="width: 15%;"><img src="<%=request.getContextPath()%>/vendor/images/Logo.png" class="w-75"></a>
            <c:if test="${not empty sessionScope.venId}">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive">
                Menu
                <i class="fa-solid fa-bars"></i>
            </button>
            <div class="collapse navbar-collapse ms-auto" id="navbarResponsive">
                <ul class="navbar-nav ms-auto me-4 my-3 my-lg-0 ">
                    <li class="nav-item"><a class="nav-link me-lg-3 text-dark fs-5" href="<%=request.getContextPath()%>/vendor/vendor_room_rest.jsp">空房查詢</a></li>
                    <li class="nav-item"><a class="nav-link me-lg-3 text-dark fs-5" href="<%=request.getContextPath()%>/RoomOrder?action=getAllVendorOrder">訂單管理</a></li>
                </ul>
                <a class="btn btn-primary rounded-pill px-3 mb-2 mb-lg-0" href="<%=request.getContextPath()%>/Vendor?action=vendorLogout">
                    <span class="d-flex align-items-center">
                        <i class="fa-solid fa-arrow-right-from-bracket me-2"></i>
                        <span class="small">登出</span>
                    </span>
                </a>
            </div>
            </c:if>
        </div>
    </nav>

</body>