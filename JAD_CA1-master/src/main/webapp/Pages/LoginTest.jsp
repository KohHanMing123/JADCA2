<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success</title>
</head>
<body>
	<%
	//new added code//
	String dm_userID = (String)session.getAttribute("sessUserID");
	String dm_userRole = (String)session.getAttribute("sessUserRole");
	String dm_loginStatus = (String)session.getAttribute("loginStatus");
	if(dm_userID==null|| !dm_loginStatus.equals("success")){
		response.sendRedirect("Login.jsp?errCode=invalidLogin");
	}
	
	out.print("<h2>Display Details</h2>");
	/* out.print("====>" + dm_class + "<br>"); */
	out.print("Welcome!.. " + dm_userID + "<br>");
	out.print("Your user role is: " + dm_userRole);
	%>
	
	<h1>Successful Login!!</h1>
	
	
</body>
</html>