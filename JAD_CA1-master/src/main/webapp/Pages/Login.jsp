<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<%
	String message = request.getParameter("errCode");
	// out.print(message);
	
	if (message != null && message.equals("invalidLogin")){
		// if (message.equals("invalidLogin")){ // cannot work: null pointer exception
		out.print("Sorry, error in login... <br><h2>Please try again!</h2>");
	}
	%>
	
	<!--<form action="verifyUser.jsp">-->
	<form action="<%= request.getContextPath() %>/VerifyUserServlet" method="post">
		Login ID: <input type="text" name="loginid"><br> 
		Password: <input type="password" name="password"> <br>
		<input type="submit" name="btnSubmit" value="Login">
		<input type="reset" name="btnReset" value="Reset">
	</form>
</body>
</html>