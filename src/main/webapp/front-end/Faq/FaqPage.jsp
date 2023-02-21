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
    
h3{
border:2px solid black;
margin:0;
height:15px;
padding:20px;
background-color:#0f2b4c;
color:white;
border-top-left-radius:30px;
border-top-right-radius:30px;
z-index:6;

}

.FAQp{
border:2px solid #0f2b4c;

height:90px;
font-size:20px;
border-bottom-left-radius:30px;
border-bottom-right-radius:30px;

padding-top:10px;
padding-left:10px;
}
#accordion{
display:block;
width:70%;
position:absolute;
left:15%;
top:150px;
margin-bottom:30px;
}

.showplace{
height:100%;
position:relative;
overflow:hidden;
}


.each{
margin-bottom:30px;
}

.FAQp{
margin:0;
}


img.question{
width:55%;

position:absolute;
right:-200px;
top:-20px;
/* margin-top:-650px; */
/* margin-left:800px; */


}
.qa{
width:30%;

position:absolute;
top:10px;
left:-100px;


}

</style>



</head>
<body>
<c:import url="/front-end/header.jsp" ></c:import>
<div class="showplace">
<img class="qa"src="<%=request.getContextPath()%>/front-end/Faq/images/FAQ.png">
<img class="question" src="<%=request.getContextPath()%>/front-end/Faq/images/faqimage.PNG">
<div id="accordion">
 <c:forEach var="faqVO" items="${list}">
 <div class="each">
  <h3 class="tital" style="line-height:5px; font-size:20px;">${faqVO.faqTitle}</h3>
  <div  style="display:none">
    <p class="FAQp">
   ${faqVO.faqContent}
    </p>
  </div>
  
  
   </div> 

   </c:forEach> 
</div>
</div>

<c:import url="/front-end/footer.jsp" ></c:import>

<script>


$(".tital").click(
		function() {
			$(this).next().slideToggle();
		
});




</script>


</body>
</html>