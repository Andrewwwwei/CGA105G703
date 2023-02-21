<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>
    <title>InserteOK</title>
</head>
</head>
<body>

</body>
<script>
Swal.fire("會員註冊成功!三秒後回到登入頁面");
setTimeout('myrefresh()',3000);
function myrefresh()
{
	window.location.href = "<%=request.getContextPath()%>/front-end/member/login.jsp";
	<%request.getSession().invalidate();%>
};
</script>
</html>