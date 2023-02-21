<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ page import="java.util.*"%>
<%@ page import="com.actReport.model.*"%>


					<!-- 揪團檢舉申請 -->

<html>
<head>
<meta charset="UTF-8">
  	<meta http-equiv="X-UA-Compatible" content="IE=edge">
  	<meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
  	<link rel="stylesheet" href="./css/header.css">
	<link rel="website icon" href="./images/bgremove_Logo.jpg">
  	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.2/font/bootstrap-icons.css"
    integrity="sha384-b6lVK+yci+bfDmaY1u0zE8YYJt0TZxLEAFyYSLHId4xoVvsrQu3INevFKo+Xir8e" crossorigin="anonymous">
 	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
 	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js" integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.min.js" integrity="sha384-mQ93GR66B00ZXjt0YO5KlohRA5SY2XofN4zfuZxLkoj1gXtW8ANNCe9d5Y3eG5eD" crossorigin="anonymous"></script>
 	<title>7 Tour | 旅遊規劃</title>
	<style>
		.container{
			/*整個頁面*/
			display : flex;
			flex-direction : column;
			justify-content: space-between; 
			flex-wrap: wrap;
		}
		.location{
			margin-top:50px;
			width: 900px;
			display: table-cell;
  			vertical-align: middle;
  			/*文字水平置中*/
			text-align: center;
		}
		.clearfix:after {
        	content:"";
        	display:table;
        	clear:both;
    	}
    	a {
 			 color: black;
 			 text-decoration: none;
		}
		#actContent{
			display: flex;
			justify-content:space-around;
			flex-wrap : wrap;
			
		}
		.card{
			margin-bottom: 50px;
		}
		.Bottom distance::after{
			width: 100%;
			height: 50px;
		 	content:"";
  			display: block;
  			clear:both;
		}

	</style>
</head>
<body>
<!-- include header -->
  <nav class="navbar navbar-expand-lg navbar-light bg-cblue sticky-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="./header.html">
        <img src="./images/bgremove_Logo.jpg" id="logo">
      </a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">論壇</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="#">論壇首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="#">文章</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">訂房</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="#">訂房首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="#">我的訂單</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="#">購物車</a></li>
            </ul>
          </li>
          <li class="nav-item hover-down">
            <a class="nav-link fs-5 fw-bold" href="#">揪團</a>
            <ul class="bg-cblue hover-list">
              <li><a class="hover-item fs-5 fw-bold" href="#">揪團首頁</a></li>
              <li><a class="hover-item fs-5 fw-bold" href="#">建立揪團</a></li>
            </ul>
          </li>
        </ul>
        <ul class="navbar-nav mb-2 mb-lg-0">
          <li class="nav-item bg-dblue rounded mx-3 ">
              <a class="nav-link fs-5 text-white fw-bold" href="#">開始規劃</a>
          </li>

          <li class="nav-item user-msg">
            <i class="bi bi-bell-fill fa-2x msg-icon"></i>
            <ul class="bg-cblue msg-list">
              <li class="msg-item">
                <h5>title</h5>
                <p class="text-truncate">訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容訊息內容</p>
              </li>
              <hr class="m-1">

              <li class="msg-item">
                <h5>title</h5>
                <p class="text-truncate">訊息內容</p>
              </li>
              <hr class="m-1">
              
              <li class="msg-item">
                <form action="">
                  <h5>XXX邀請你，共同規畫行程</h5>
                  <button class="btn btn-primary rounded p-1">確認</button>
                  <button class="btn btn-danger rounded p-1">拒絕</button>
                </form>
              </li>
              <hr class="m-1">

              <li class="msg-item">
                <h5>title</h5>
                <p class="text-truncate">訊息內容</p>
              </li>
              <hr class="m-1">
            </ul>
          </li>

          <li class="nav-item mx-3 user-info">
            <img src="./images/dog.jpeg" alt="會員照片" class="user-pic">
            <ul class="bg-cblue info-list">
              <li><a href="#" class="d-block info-item">會員基本資料</a></li>
              <li><a href="#" class="d-block info-item">我的行程</a></li>
              <li><a href="#" class="d-block info-item">我的收藏</a></li>
              <li><a href="#" class="d-block info-item">我的訂單</a></li>
              <li><a href="#" class="d-block info-item">登出</a></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <!-- header end -->
  
    <main>
  <div class="container py-4">
    <header class="pb-3 mb-4 border-bottom">
      <a href="/" class="d-flex align-items-center text-dark text-decoration-none">
      </a>
    </header>

    <div class="p-5 mb-4 bg-light rounded-3">
      <div class="container-fluid py-5">
        <h1 class="display-5 fw-bold">檢舉揪團</h1>
        <p class="col-md-8 fs-4"></p>
      </div>
    </div>

    <div class="row align-items-md-stretch">
      <div class="col-md-6">
        <div class="h-100 p-5 text-white bg-dark rounded-3">
        	<div>
            	<h2>檢舉行程內容</h2>
        	</div>
        </div>
      </div>
      
      <div class="col-md-6">
        <div class="h-100 p-5 bg-light border rounded-3">
					<h7>揪團檢舉</h7>
       		 <table>
       		 	<tr>
       		 		<td>檢舉內容</td>
       		 			<td><input type="text" siz="50"></td>
       		 	</tr>
				<tr>
					<td>檢舉事項</td>
					  <td>
					<select size="1" name="reportMatterStr">	
						<option value= "0">騷擾行為</option>
						<option value= "1">疑似詐騙內容</option>
						<option value= "2">標題與活動內容不符</option>
						<option value= "3">其他</option>
					</select>
					  </td>
				</tr>
			</table>
        </div>
      </div>

    </div>
  </div>
</main>
  	<!-- include footer -->
			<c:import url="/front-end/footer.jsp" ></c:import>
</body>
</html>