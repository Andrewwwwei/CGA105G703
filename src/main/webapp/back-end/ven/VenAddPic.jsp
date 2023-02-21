<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.vendor.model.*"%>
<%@ page import="com.vendorPic.model.*"%>
<%
VendorVO vendorVO = (VendorVO) request.getAttribute("vendorVO");
%>

<html>
<head>
<script src="https://kit.fontawesome.com/33115e34cf.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/ven/Vendor.css">
<meta charset="BIG5">
<title>Insert title here</title>
<style type="text/css">
/* ========================================== 上方function============================================ */
div.function {
	background-color: #dee4ec;
	width: calc(100% - var(- -aside-width));
	/*減號兩邊一定要空格 ; */
	height: var(- -head-height);
	position: fixed;
	top: 0;
	left: var(- -aside-width);
	display: flex;
	align-items: center;
	justify-content: center;
	z-index: 3;
}

div.function p {
	font-size: 25px;
	font-family: 'Noto Sans TC', sans-serif;
	color: #002e65;
}

div.function a {
	position: absolute;
	left: 20px;
	top: 10px;
}

/* ==========================================主要工作區============================================ */
div.workplace button {
	background-color: transparent;
	border: 2px solid #041427;
	width: 120px;
	height: 30px;
	border-radius: 6px;
	font-size: 16px;
	margin: 2px;
}

div.workplace button:hover {
	background-color: #041427;
	color: white;
}
div.workplace img{
width:300px;height:200px;
margin:10px;
}



</style>

</head>
<body>
	<c:import url="/back-end/sidebar.jsp"></c:import>

	<div class="right">

		<!-- ---------------------function body區 --------------------->
		<div class="function">

			<a href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp">
				<i class="fa-solid fa-arrow-left fa-2x"></i>
			</a>
			<p>上傳廠商圖片</p>


		</div>

		<!-- ---------------------main body區 --------------------->


		<div class="workplace">

			<main>
			
			

				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/VenServlet"
					enctype="multipart/form-data">
					<input type="file" name="upfile1" id="p1" multiple="multiple">
					<button type="submit">圖片上傳</button>
					<input type="hidden" name="action" value="picUpload"> <input
						type="hidden" name="venId" value="${venId}">

				</FORM>
				
				<div id="previewContainer">
				</div>
				
			</main>
		</div>
	</div>

	


<script>
  const input = document.getElementById('p1');
  const previewContainer = document.getElementById('previewContainer');

  input.addEventListener('change', () => {
    previewContainer.innerHTML = '';
    for (let i = 0; i < input.files.length; i++) {
      const reader = new FileReader();
      reader.readAsDataURL(input.files[i]);
      reader.onload = () => {
        const preview = document.createElement('div');
        const img = document.createElement('img');
        const trash = document.createElement("i");
        trash.setAttribute('id','trash' + i);
        trash.className="fa-regular fa-trash-can trashcan";
        
        img.src = reader.result;

        trash.addEventListener('click', (e) => {
          const ID = e.target.id;
          const index = ID.substring(ID.length - 1);
          const dt = new DataTransfer();
          
          for (let i = 0; i < input.files.length; i++) {
            const file = input.files[i];
            if (parseInt(index) !== i)
          dt.items.add(file); // here you exclude the file. thus removing it.
      }
      
      input.files = dt.files // Assign the updates list
      preview.remove();
      const trashcan = document.querySelectorAll(".trashcan");
      for(let i = 0; i < trashcan.length; i++){
    		  trashcan[i].id = 'trash' + i;
      }
    });
        
        preview.appendChild(img);
        preview.appendChild(trash);
        previewContainer.appendChild(preview);
      };
    }
  });

  </script>




	
</body>
</html>