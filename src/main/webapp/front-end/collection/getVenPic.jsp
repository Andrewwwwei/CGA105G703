<%@ page language="java" contentType="text/html; charset=UTF-8"
     pageEncoding="UTF-8"%> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ page import="java.sql.*" %> 
 <%@ page import="java.util.*"%> 
 <%@ page import="java.text.*"%> 
<%@ page import="java.io.*"%> 
<meta http-equiv="Content-Type" content="text/html; charset=big5">
 <html>
<body>
<%
Class.forName("com.mysql.cj.jdbc.Driver"); 
String url = "jdbc:mysql://localhost:3306/cga105_g7";
String userid = "root";
String passwd = "02021";
 Connection con = DriverManager.getConnection(url, userid, passwd);

Statement stmt=con.createStatement();
ResultSet rs=null;

String venId= request.getParameter("venId");


String sql = "select VEN_PIC from vendor_pic WHERE VEN_ID =" + venId + "" ;

rs=stmt.executeQuery(sql);
try{
if(rs.next()) {
ServletOutputStream sout = response.getOutputStream();

BufferedInputStream in =  new BufferedInputStream(rs.getBinaryStream(1));
byte b[] = new byte[4 * 1024];
int len;
while((len = in.read(b)) != -1)
{
sout.write(b, 0, len);

}
in.close();
sout.flush();

sout.close();
out.clear();
rs.close();
out = pageContext.pushBody();
}
}catch (IllegalStateException e) {
	out.flush();
	out.close();
	out.clear();
	out = pageContext.pushBody();

	rs.close();
}catch (Exception e) {
	out.flush();
	out.close();
	out.clear();
	out = pageContext.pushBody();

	rs.close();
}
 %> 
</body>
</html>