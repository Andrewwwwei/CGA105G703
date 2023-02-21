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
    <title>UpdatePasswordOK</title>
</head>
</head>
<body>
</body>
<script>
Swal.fire("密碼修改成功請用新密碼登入!");
setTimeout('myrefresh()',2000);
function myrefresh()
{
	<%request.getSession().invalidate();%>
	
   window.close();
   return;
}
</script>
</html>