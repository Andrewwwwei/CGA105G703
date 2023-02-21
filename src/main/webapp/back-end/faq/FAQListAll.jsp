<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<%@ page import="java.util.*"%>


<%@ page import="com.faq.model.*"%>
<%  
FaqService faqSvc= new FaqService();
List<FaqVO> list = faqSvc.getAll();
pageContext.setAttribute("list", list);
FaqVO faqVO = (FaqVO)request.getAttribute("faqVO");
%>
 
<html>
<head>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
<style>

a#faqcontrol {
	background-color: #041427;
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
         div.function p {
 
      font-size:25px;
       font-family: 'Noto Sans TC', sans-serif;
       color:#002e65;

    }
    
         div.function a{

position: absolute;
left:20px;
top:10px;

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
/*       text-align: center; */
      justify-content: space-between;
     
      font-size:16px;

    }



 button.add{ 
 float:left; 
position:absolute; 
  z-index: 6;
left:0;
top:13px;
 } 
 
 
  div.workplace button{
     background-color: transparent;
      border: 2px solid #041427;
      width: 100px;
      height:25px;
      border-radius: 6px;
      font-size: 16px;
      margin:2px;
      text-align: center;
    }
    div.workplace button:hover{
    background-color:#041427;
    color:white;
    }
    
       div.workplace input{
     background-color: transparent;
      border: 2px solid #041427;
     height:35px;
      border-radius: 6px;
      font-size: 16px;
      margin:5px;
    padding-left:3px;
 
   
     
    }
    
           div.workplace textarea{
     background-color: transparent;
      border: 2px solid #041427;
     height:100px;
      border-radius: 6px;
      font-size: 16px;
      margin:10px;
    padding-left:3px;
   
     
    }
    
           div.workplace #accordion{

   margin-top:100px;
  margin-left:100px;
  
     
    }
    
    div.workplace .faqnum:nth-child(odd){
    background-color:#eff3f9;}
    
               div.workplace .faqnum{

 
  
  height:300px;
   border-radius: 6px;
     
    }

    
  </style>

</head>
<body>
<c:import url="/back-end/sidebar.jsp"></c:import>


  <!-- ---------------------aside body區 --------------------->
 
  <div class="right">

    <!-- ---------------------function body區 --------------------->
    <div class="function">

    
<a  href="<%=request.getContextPath()%>/back-end/ven/VenListAll.jsp"> <i class="fa-solid fa-arrow-left fa-2x"  ></i></a>
<p>常見問答管理</p>
      
    </div>
		<!-- ---------------------main body區 --------------------->
 <div class="workplace">

<main>
				<button type="button" class="add">新增</button>
				
			
					<FORM id="faqForm">	<p>
					<div class="addfaqdiv" style="display:none"><p>
					<label for="faqtitle">標題: </label> 
					<input type="text" name="faqTitle" style="width:500px;" class="color"
						value="<%=(faqVO == null) ? "" : faqVO.getFaqTitle()%>" id="faqTitle"
						style="width: 150px;" /> <br>
						
						<label for="faqtitle">內文: </label><br> 
					<textarea name="faqContent" id="faqContent" style="width: 1000px;" class="color"
						><%=(faqVO == null) ? "" : faqVO.getFaqContent()%></textarea>   <br>						
					<button type="submit" style="width:80px;" class="addFaq">確認新增</button>
					<button type="button" class="addCan">取消</button>
				
				</div>
				   <c:if test="${not empty aqAddErrL}">
	
	
		<c:forEach var="message" items="${aqAddErrL}">
			<span style="color: red">${message} /</span>
		</c:forEach>
	
</c:if> 
				
					
			</FORM>

		
  <div id="accordion" >  
  <c:forEach var="faqVO" items="${list}">

  <form class="submitform${faqVO.faqId}">

<label for="num" style="margin-left:30px;"class="p1">++編號: </label>
<input type="text"  id="id" name="faqId" value="${faqVO.faqId}" style="width:35px; border:none;"readonly>

 <label for="title" class="p4" style="margin-left:5px;">標題: </label>

<input type="text"  class="color"  id="pop" name="faqTitle" value="${faqVO.faqTitle}" style="width:820px;background-color:#e6eaf0; color:black;" disabled></input>
<div class="faqinfo" style="display:none">
 <label for="content" style="margin-left:145px;">內文: </label> <br>
<textarea name="faqContent" rows="5" cols="80" class="color" required style="width:820px;background-color:#f2f4f6;margin-left:188px;" disabled>${faqVO.faqContent}</textarea><br>
<button type="button" id="adju" style="margin-left:190px;">編輯</button> 
<input type="hidden" name="faqId" id="p3" value="${faqVO.faqId}"> 
          <button type="submit" style="display:none;margin-left:190px;" class="confirm" ">確認修改</button>
         <button type="button" class="can" style="display:none" onclick="javascript:location.reload();">取消修改</button>	
        
		
		<button type="button" style="display:none" class="del">刪除</button>

			<input type="hidden" id="p2" name="faqId" value="${faqVO.faqId}">
</div>	

</form>

 </c:forEach> 

	 </div>

   
</main>
 </div>
 </div>

 <script>
 
       $(".can").click(function () {
                             $(this).hide();
                             $(this).prev().hide();
                             $(this).next().hide();
                             $('button[id="adju"]').show();
                                $(".btnSubmit").attr("disabled", true);
                               
                                
                                $("input").prop("disabled", true);
                                $("textarea").prop("disabled", true);
                              });                 
    $(function () {

                 $('button[id="adju"]'). mousedown(function () {
                 	  $(this).siblings().show();
                       $(this).hide();
                       $(this).prevUntil(".p1").attr("disabled", false).css("background", "");
                       $(this).parent().parent().find("input").attr("disabled", false).css("background", "");
                 
            
             	$(".confirm").click(function(e) {
             		 $(this).hide(); 
             		 $(this).nextUntil("#p2").hide(); 
             		 $('button[id="adju"]').show();
             		 
     			    e.preventDefault();
     			    var form = $(this).closest('form');
     			    console.log(form);
     			    var formData = form.serialize()+'&action=update';
     			   console.log(formData);
     			    $.ajax({
     			      type: "POST",
            		url: `<%=request.getContextPath()%>/FAQServlet`,
     			      data: formData,
     			      success: function(response) {
     			    	 window.location.reload()}            			    
     			    });
     			    $(this).prevUntil("#p1").attr("disabled", true);
     			  })	;                     	            			    	
     			      })
     			    })
     			    
     			    
     			    
         $(function () {
             	$('.del').click(function(e) {
             		 $(this).hide(); 
             		 $(this).prevUntil("#p3").hide(); 
             		 $('button[id="adju"]').show();
             		 
     			    e.preventDefault();
     			    var form = $(this).closest('form');
     			    console.log(form);
     			    var formData = form.serialize()+'&action=delete';
     			   console.log(formData);
     			    $.ajax({
     			      type: "POST",
            			      url: `<%=request.getContextPath()%>/FAQServlet`,
     			      data: formData,
     			      success: function(response) {
     			    	 window.location.reload()}            			    
     			    });
     			    $(this).prevUntil("#p1").attr("disabled", true);
     			  })	;                     	            			    	
     			      })
     			    
     		
	$(".add").click(
			function() {
				$(".add").css('display','none');
				 $(".addfaqdiv").css('display','block');
});
	$(".addCan").click(
			function() {
				$(".add").css('display','block');
				 $(".addfaqdiv").css('display','none');
});
	
	$(".p1").click(
			function() {
				$(this).next().next().next().next().toggle("slow");				
});

  
  $(".addFaq").click(function(e) {
	
	    e.preventDefault();
	    var formData = $("#faqForm").serialize()+'&action=insert';
	    $.ajax({
	      type: "POST",
	      url:`<%=request.getContextPath()%>/FAQServlet`,
	      data: formData,
	      success: function(response) {
	    	  window.location.reload();
	        console.log(response);
	      }
	   
	  });
	});
  
  
  $(".color").focus(function () {
      $(this).css("background", "#f7f6ea");
    })
    
       $(".color").focusout(function () {
      $(this).css("background", "");
    })
    
  
  

</script>


</body>
</html>