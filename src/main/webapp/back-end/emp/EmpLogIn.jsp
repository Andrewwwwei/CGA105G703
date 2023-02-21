<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>

<html>
<head>
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>

  <style type="text/css">
  
 
    @import url(https://fonts.googleapis.com/earlyaccess/notosanstc.css);

    body {
      font-family: 'Noto Sans TC', sans-serif;
      margin: 0;

    }

    * {
      box-sizing: border-box;

    }

    :root {
  
      --head-height: 120px;
      
      --aside-width: 220px;
    }

    ::-webkit-scrollbar {
      width: 5px;
    }

    ::-webkit-scrollbar-track {
      background-color: #002e65;
    }

    ::-webkit-scrollbar-thumb {
      background: #8b9bb1;
    }

    ::-webkit-scrollbar-thumb:hover {
      background: #fff;
    }

    /* ========================================== 左方aside============================================ */

    aside.aside {

      background-color: #BFCCDC;
      width: var(--aside-width);
      height: 100%;
      display: flex;
      /*設定元素方向、位置*/
      flex-direction: column;
      /*直向*/
      align-items: center;
      /*垂直致中*/
  
      position: fixed;
      top: 0;
      left: 0;
      z-index: 5;
      /* 層級 */
      overflow-x: hidden;
      /* x滾軸隱藏 */
      overflow-y: auto;
      padding-bottom: 10px;
      padding-top: 10px;

    }

    aside.aside ul.aside_list {
      width: var(--aside-width);
      list-style: none;
      margin: 0;
      padding: 0;
      height: 100%;
    }

    aside.aside ul.aside_list>li:hover {
      background-color: #8b9bb1;

    }


    aside.aside ul.aside_list>li a {
      display: flex;
      justify-content: center;
      /*水平致中 */
      align-items: center;
      /*垂直致中 */
      text-decoration: none;
      /*文字底線去除 */
      padding: 5px;
      font-size: larger;
      color: white;
      height: 50px;


    }


    aside.aside button {
      width: 85px;
      font-size: 14px;
      background-color: transparent;
      border: 2px solid white;
      border-radius: 6px;
      color: white;
      text-align: center;
      text-decoration: none;
      margin-top: 20px;
      margin-bottom: 10px;
    }

    aside.aside button:hover {
      background-color: #8b9bb1;
      color: white;

    }


    aside.aside button a {
      color: white;
      text-align: center;
      text-decoration: none;
    }

    aside.aside>img {
      margin-bottom: 10px;
    }

    aside.aside h1 {
      font-size: 15px;
      color: white;
    }

    /* ========================================== 上方function============================================ */
    div.function {
     background-color: #dee4ec; 
      width: calc(100% - var(--aside-width));
      /*減號兩邊一定要空格 ; */
      height: var(--head-height);
      position: fixed;
      top: 0;
      left: var(--aside-width);
      display: flex;
      align-items: center;
      justify-content:center;
      z-index: 1;

    }



    /* ==========================================主要工作區============================================ */
    div.workplace {

       position: fixed;
      top: var(--head-height)+50px;
          margin-top: 250px;
    left:var(--aside-width);
     width: calc(100% - var(--aside-width));
      /*減號兩邊一定要空格*/
    z-index: 10;
    

    
 }
    
     div.log {
      display: flex;
      /*設定元素方向、位置*/
      flex-direction: column;
      /*直向*/
      align-items: center;
      /*垂直致中*/}
      
      div.log button{
        background-color: transparent;
       margin-top:15px;
       font-size:15px;}
      

  div.log label{
  font-size:15px;
   margin-top:12px;
   font-size:15px;
  }
  
  
  div.right{
  height:100vh;
  position:fixed;
    left:var(--aside-width);
     width: calc(100% - var(--aside-width));
  }
  
      div.workplace>button {
      margin-left: 30px;
      background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 13px;
    }
    
        div.workplace button:hover{
    background-color: #041427;
    color:white;
    }
  </style>

</head>
<body >


 
 
  <!-- ---------------------aside body區 --------------------->
  <aside class="aside">

    <img src="<%=request.getContextPath()%>\back-end\emp\images/logo.png" width="80%">
    
    

    <ul class="aside_list">
      <li><a href="Emplistallpage.jsp">員工管理</a></li>
      <li><a href="#">會員管理</a></li>
      <li><a href="#">客服管理</a></li>
      <li><a href="#">優惠管理</a></li>
      <li><a href="#">廠商管理</a></li>
      <li><a href="#">權限管理</a></li>
      <li><a href="#">論壇管理</a></li>
      <li><a href="#">揪團管理</a></li>
      <li><a href="#">訂房管理</a></li>
      <li><a href="#">景點管理</a></li>

    </ul>
    <h1 style="margin: 0;">使用者 &#58 empName</h1>
    <button><a href="#">登出</a></button>
    <button><a href="#">修改密碼</a></button>

  </aside>



<!--     ---------------------function body區 ------------------- -->
    
    <div class="right">
    
      
    <div class="function">
    


      


    </div>

    <!-- ---------------------main body區 --------------------->
  
   
     <div class="workplace">

  
</div>  
     
     </div>
      
     
       
   




  <script>

    $(".aside_list li").click(function () {
      $(this).css("background", "#041427").siblings().css("background", "");
    })
  </script>
  

</body>


</html>