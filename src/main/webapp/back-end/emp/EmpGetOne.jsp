<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>


<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

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
      z-index: 6;
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
      z-index: 3;

    }

    div.function>label {
      margin-left: 18px;
      margin-right: 8px;
    }

    div.function>button {
      margin-left: 30px;
      background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 13px;
    }

    div.function select {
      width: 130px;
      height: 21.5px;


    }

    /* ==========================================主要工作區============================================ */
       div.workplace {
      margin-left: calc(var(--aside-width) + 30px);
      /* margin-right: 20px; */
      margin-top: 50px;
      width: calc(100% - var(--aside-width) - 60px);
      /*減號兩邊一定要空格*/
      display: inline-block;
      justify-content: center;
      position: absolute;
      top: var(--head-height);
      z-index: 1;
      text-align: center;
      justify-content: space-between;
     
      

    }

    main.empTable table {

   
      color: black;
      border: 1px solid black;
      font-size: 16px;
      width: 100%;
      border-collapse: collapse;

    }

    main.empTable table th {
      border: 1px solid black;
      background-color: #8b9bb1;
    }

    main.empTable table td {
      border: 1px solid black;


    }
       div.workplace button{
     background-color: transparent;
      border: 2px solid #041427;
      width: 50px;
      border-radius: 6px;
      font-size: 13px;
    }
    
    add{
    color:pink}
  </style>

</head>

<body>

  <!-- ---------------------aside body區 --------------------->
  <aside class="aside">

		<img src="images/logo.png" width="80%">

		<ul class="aside_list">
			<li style="background-color: #041427"><a
				href="<%=request.getContextPath()%>/back-end/emp/Emplistallpage.jsp">員工管理</a></li>
			<li><a href="#">會員管理</a></li>
			<li><a
				href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp">廠商管理</a></li>
			<li ><a
				href="<%=request.getContextPath()%>/back-end/permisFunc/PermisFuncListAll.jsp">權限管理</a></li>
			<li><a href="#">論壇管理</a></li>
			<li><a href="#">揪團管理</a></li>
			<li><a href="#">訂房管理</a></li>
			<li><a href="#">景點管理</a></li>

		</ul>
		<h1 style="margin: 0;">使用者 &#58 empName</h1>
		<button>
			<a href="#">登出</a>
		</button>
		<button>
			<a href="#">修改密碼</a>
		</button>

	</aside>
  <div class="right">

    <!-- ---------------------function body區 --------------------->
    <div class="function">
         <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet" >
        <label for="search1">員工編號: </label>
        
        <input type="text" name="empNo" id="search1" style="width:130px;">
        <input type="hidden" name="action" value="getOne_For_Display">

        <button type="submit">查詢</button>
       </FORM>
       

<!--           <FORM METHOD="post" ACTION="EmpServlet" > -->
<!--          <label for="search2">員工姓名: </label> -->
        
<!--         <input type="text" name="empName" id="search2" style="width:130px;"> -->
<!--         <input type="hidden" name="action" value="getOne_For_DisplayName"> -->

<!--         <button type="submit">查詢</button> -->
<!--        </FORM> -->

   


<!--         -->
<!--          -->

<!--         <label for="search3">身分證字號: </label> -->
<!--         <input type="text" name="empName" id="search3" style="width:130px;"> -->

<!--         <label for="search4">員工部門: </label> -->
<!--         <select name="empApt"> -->
<!--           <option value="all"></option> -->
<!--           <option value="customer">客服部</option> -->
<!--           <option value="hotel">訂房管理部</option> -->
<!--           <option value="blog">論壇管理部</option> -->
<!--           <option value="admin">網站管理部</option> -->
<!--           <option value="tourgo">揪團管理部</option> -->
<!--           <option value="tourplane">行程管理部</option> -->
<!--         </select> -->


    </div>

    <!-- ---------------------main body區 --------------------->
    
    
   
    <div class="workplace">
   
      <main class="empTable" >
        <table>
          <tr>
            <th>編輯</th>
            <th>員編</th>
            <th>姓名</th>
            <th>部門</th>
            <th>職稱</th>
            <th>身分證</th>
            <th>電子信箱</th>
            <th>電話</th>
            <th>入職日</th>
            <th>狀態</th>
          </tr>
         
          <tr>
            <td>
              <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet">
              <button type="submit">修改</button> 
               <input type="hidden" name="empNo"  value="${empVO.empNo}">
			   <input type="hidden" name="action"	value="getOne_For_Update"></FORM>            
                <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/EmpServlet">
			     <button type="submit">刪除</button>
			     <input type="hidden" name="empNo"  value="${empVO.empNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			
			     
			    
			  </td>
             
           <td><%=empVO.getEmpNo()%></td>
           <td><%=empVO.getEmpName()%></td>
           <td><%=empVO.getEmpDep()%></td>
           <td><%=empVO.getJobTitle()%></td>
           <td><%=empVO.getEmpIdnum()%></td>
           <td><%=empVO.getEmpEmail()%></td>
           <td><%=empVO.getEmpTel()%></td>
           <td><%=empVO.getEmpHiredate()%></td>
           <td><%=empVO.getEmpStatus()%></td>
           


              
          </tr>

        </table>

      </main>
      
     
   
    </div>

  </div>

  <script>

    $(".aside_list li").click(function () {
      $(this).css("background", "#041427").siblings().css("background", "");
    })
  </script>
  

</body>


</html>