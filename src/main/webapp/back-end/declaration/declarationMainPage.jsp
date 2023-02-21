<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- MDB -->
<link
 href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
 rel="stylesheet" />
<!-- Google Fonts -->
<link
 href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
 rel="stylesheet" />
<!-- MDB -->
<link
 href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.css"
 rel="stylesheet" />
<!-- MDB -->
<script type="text/javascript"
 src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.1.0/mdb.min.js"></script>
<!-- MDB -->
<!-- JQuery -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js"
 integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
 crossorigin="anonymous"></script>
<!-- bootstrap Icon -->
<link rel="stylesheet"
 href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.3/font/bootstrap-icons.css">


<!-- dataTable -->
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<script
 src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<script
 src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>

<!-- sweetAleart -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>


<!--boostrap5  -->
<!--   <link rel="stylesheet" -->
<!--      href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"> -->

<!--   <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" -->
<!--      rel="stylesheet" -->
<!--      integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" -->
<!--      crossorigin="anonymous"> -->





<link rel="website icon" href="../sources/images/logo.png">
<link rel="stylesheet" href="../sources/css/back.css">
<link rel="stylesheet" href="css/decMainPage.css">

<link rel="icon" href="../sources/images/logo.ico" type="image/x-icon" />
<title>7 Tour : 公告管理</title>

</head>
<body style="direction: ltr">
 <%@ include file="/back-end/sidebar.jsp"%>
 <div class="mainPage">
  <nav
   style="-bs-breadcrumb-divider: url(&amp; amp; #34; data: image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' width='8' height='8'%3E%3Cpath d='M2.5 0L1 1.5 3.5 4 1 6.5 2.5 8l4-4-4-4z' fill='currentColor'/%3E%3C/svg%3E&amp;amp;#34;);"
   aria-label="breadcrumb">
   <ol class="breadcrumb">
    <li class="breadcrumb-item"><a href="#">Home</a></li>
    <li class="breadcrumb-item active" aria-current="page">公告管理</li>
   </ol>
  </nav>

  <div
   class="maintop p-3 mb-2 bg-light text-dark shadow p-3 mb-5 bg-body rounded">
   <h3>公告管理</h3>
   <div class="info-button">
    <button type="button" id="insert-btn" class="btn btn-primary"
     data-mdb-toggle="modal" data-mdb-target="#insertForm"
     data-mdb-whatever="@mdo">新增公告</button>
   </div>
  </div>

  <div class="horizon">
   <hr>
  </div>

  <div class="mainmiddle shadow p-3 mb-5 bg-body rounded ">
   <table class="table table-hover align-middle mb-0" id="listTable">
    <thead class="table-light">
     <tr>
      <th scope="col" class="text-nowrap">公告編號 <i id="idicon"
       class="bi bi-caret-down-fill"></i></th>
      <th scope="col" class="text-nowrap">標題 <i id="titleicon"
       class="bi bi-caret-down-fill"></i></th>
      <th scope="col" class="text-nowrap">內容</th>
      <th scope="col" class="text-nowrap">上次更新 <i id="dateicon"
       class="bi bi-caret-down-fill"></i></th>
      <th scope="col" class="text-nowrap">更新內容</th>
      <th scope="col" class="text-nowrap">刪除</th>
     </tr>
    </thead>

    <tbody>
     <c:forEach items="${list}" var="decVO">
      <tr class="list-value table-light">
       <td class="clickable"
        data-href="<%=request.getContextPath()%>/back-end/declaration/?action=showOne&decID=${decVO.declarationID}">${decVO.declarationID}</td>
       <td class="clickable overflow-hidden text-nowrap"
        data-href="<%=request.getContextPath()%>/back-end/declaration/?action=showOne&decID=${decVO.declarationID}">${decVO.title}</td>
       <td class="clickable overflow-hidden text-nowrap"
        data-href="<%=request.getContextPath()%>/back-end/declaration/?action=showOne&decID=${decVO.declarationID}">${decVO.content}</td>
       <td class="clickable text-nowrap"
        data-href="<%=request.getContextPath()%>/back-end/declaration/?action=showOne&decID=${decVO.declarationID}">${decVO.date}</td>
       <td>
        <button
         onclick="updateView('${decVO.declarationID}','${decVO.title}','${decVO.content}')"
         type="button" id="update-btn"
         class="btn btn-primary list-update-btn text-nowrap" data-mdb-toggle="modal"
         data-mdb-target="#updateForm" data-mdb-whatever="@mdo">
         更新內容</button>
       </td>
       <td>
        <FORM METHOD="post"
         ACTION="<%=request.getContextPath()%>/back-end/declaration/"
         style="margin-bottom: 0px;">
         <input type="submit" value="刪除" class="btn btn-secondary ">
         <input type="hidden" name="decID"
          value="${decVO.declarationID}"> <input type="hidden"
          name="action" value="delete">
        </FORM>
       </td>
      </tr>
     </c:forEach>
    </tbody>
    <tfoot class="table-light">
     <tr>
      <th scope="col" class="text-nowrap">公告編號</th>
      <th scope="col" class="text-nowrap">標題</th>
      <th scope="col" class="text-nowrap">內容</th>
      <th scope="col" class="text-nowrap">上次更新</th>
      <th scope="col" class="text-nowrap">更新內容</th>
      <th scope="col" class="text-nowrap">刪除</th>
     </tr>
    </tfoot>
   </table>
  </div>
  <div class="horizon">
   <hr>
  </div>

  <!-- insert ------------------------------------------------------------- -->

  <div class="modal fade .modal-fullscreen-md-down" id="insertForm"
   tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
   <div class="modal-dialog">
    <div class="modal-content">
     <div class="modal-header">
      <h5 class="modal-title">新增公告 :</h5>
      <button type="button" class="btn-close" data-mdb-dismiss="modal"
       aria-label="Close"></button>
     </div>

     <form method="post"
      action="<%=request.getContextPath()%>/back-end/declaration/"
      enctype="multipart/form-data" id="test">
      <div class="modal-body">

       <div class="mb-3">
        <label for="insert-title" class="col-form-label">Title:</label>
        <p style="color: red;">${insertErrorMsgs.title}</p>
        <input type="text" class="form-control" id="insert-title"
         name="decTitle" value="${decVO.title}">
       </div>

       <div class="mb-3">
        <label for="insert-content" class="col-form-label">Content:</label>
        <p style="color: red;">${insertErrorMsgs.content}</p>
        <input type="text" class="form-control" id="insert-content"
         name="decContent" value="${decVO.content}">
       </div>

       <div class="mb-3">
        <label for="insert-pic" class="col-form-label">Picture:</label>
        <input type="file" class="form-control" id="insert-pic"
         name="decPic">
       </div>

       <input type="hidden" name="action" value="insert">
      </div>

      <div class="modal-footer">
       <button type="button" class="btn btn-secondary"
        data-mdb-dismiss="modal">Close</button>
       <input type="submit" class="btn btn-primary" value="submit">
      </div>
     </form>
    </div>
   </div>
  </div>

  <!-- update ------------------------------------------------------------- -->

  <div class="modal fade .modal-fullscreen-md-down" id="updateForm"
   tabindex="-1" aria-hidden="true">
   <div class="modal-dialog">
    <div class="modal-content">
     <div class="modal-header">
      <h5 class="modal-title"> 更新公告內容 :</h5>
      <button type="button" class="btn-close" data-mdb-dismiss="modal"
       aria-label="Close"></button>
     </div>

     <form method="post"
      action="<%=request.getContextPath()%>/back-end/declaration/"
      enctype="multipart/form-data">
      <div class="modal-body">

       <div class="mb-3">
        <p id="update-ID" style="size: 15px;"></p>
       </div>

       <div class="mb-3">
        <label for="update-title" class="col-form-label">Title :</label>
        <p style="color: red;">${updateErrorMsgs.title}</p>
        <input type="text" class="form-control" id="update-title"
         name="decTitle" value="${decVO.title}" maxlength="20">
       </div>


       <div class="mb-3">
        <label for="update-content" class="col-form-label">Content
         :</label>
        <p style="color: red;">${updateErrorMsgs.content}</p>
        <textarea class="form-control" id="update-content"
         name="decContent" maxlength="50"> ${decVO.content}</textarea>
       </div>

       <div class="mb-3">
        <label for="update-pic" class="col-form-label">Picture :</label>
        <input type="file" class="form-control" id="update-pic"
         name="decPic" accept=".jpg,.png,.jpeg">
       </div>

       <input type="hidden" name="action" value="update"> <input
        type="hidden" name="decID" id="setUpdateID">
      </div>

      <div class="modal-footer">
       <button type="button" class="btn btn-secondary"
        data-mdb-dismiss="modal">Close</button>
       <input type="submit" class="btn btn-primary" value="submit">
      </div>
     </form>
    </div>
   </div>
  </div>
 </div>

 <script type="text/javascript" src="js/dataTable.js"></script>
 <script type="text/javascript" src="js/decNotions.js"></script>
 <script type="text/javascript" src="js/decMainPage.js"></script>

 <script>
 <!-- Get the modal status value -->
      let modalStatus = ${not empty modalStatus ?modalStatus :"0"} ; 
       
 <!-- Open the target modal -->
       if(modalStatus === 5){
        AnnounceSuccess('新增',${decVO.declarationID});
       }
       if(modalStatus === 6){
        AnnounceSuccess('更新內容',${decID});
       }
       if(modalStatus === 2){
        AnnounceSuccess('刪除',${deleteInfo});
       }
    
    if (modalStatus === 1) {
     $('#insert-btn').click();

     	AnnounceFail('新增');
       }
       if (modalStatus === 3) {
        $('#update-ID').text('Declaration ID : ' + ${not empty decVO.declarationID ?decVO.declarationID :"Error"} ); 
        $('#setUpdateID').attr("value", ${decVO.declarationID});
//         let updateForm = new bootstrap.Modal(document.getElementById('updateForm'), {
//         keyboard: false
//      })
//      updateForm.show(); 
     $('#update-btn').click();
     	AnnounceFail('更新內容');
       }
     </script>

</body>
</html>