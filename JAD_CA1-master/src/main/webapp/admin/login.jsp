<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
<title>Admin Login</title>
</head>
<body class="bg-sand h-screen flex flex-col">
<%
String msg = request.getParameter("msg");
if(msg==null){
	msg = "";
}

%>
    <%@ include file="../components/navBar.html" %>
    <div class="flex flex-col justify-center items-center grow">
        <form action="<%= request.getContextPath() %>/AdminLogin" method="post" class="bg-grey shadow-md rounded-md border border-gray-300 h-fit max-w-xl px-12 py-6">
            <div class="flex justify-between items-center mb-4">
                <h2 class="text-2xl font-bold">Admin Login</h2>
                <button type="button" class="text-black text-xl">
                    <i class="fa-solid fa-xmark"></i>
                </button>
            </div>
            <div class="mb-4">
                <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Username:</label>
                <input type="text" name="username" id="username" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
            </div>
            <div class="mb-6">
                <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password:</label>
                <input type="password" name="password" id="password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
            </div>
            <div class="flex flex-col items-center mt-5">
            <button type="submit" class="bg-dark-blue py-1 px-14 rounded-lg text-white text-lg font-semibold">Log In</button>
            <%if(msg.equals("invalid")){ %>
            	<p class="text-maroon">Invalid Username or Password</p>
            <%} %>
        	<p class="text-gray-400 mt-5">Are you an Customer? <a href="http://localhost:8080/JAD_CA1-master/Pages/Login.jsp">Log in here!</a></p>
        	</div>
        </form>
    </div>
    <%@ include file="../components/footer.html" %>
</body>

</html>