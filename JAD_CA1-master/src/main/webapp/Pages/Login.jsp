<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
<title>Login</title>
</head>
<body class="bg-sand">
    <%@ include file="../components/navBar.html" %>
    <div class="flex justify-center items-center min-h-screen">
        <form action="<%= request.getContextPath() %>/VerifyLoginServlet" method="post" class="bg-grey shadow-md rounded-md border border-gray-300 max-w-xl px-12 py-6">
            <div class="flex justify-between items-center mb-4">
                <h2 class="text-2xl font-bold">Login</h2>
                <button type="button" class="text-black text-xl">
                    <i class="fa-solid fa-xmark"></i>
                </button>
            </div>
            <% 
            String message = request.getParameter("errCode");
            if (message != null && message.equals("invalidLogin")) { 
            %>
            <p class="text-red-500 mb-4">Sorry, error in login... <br> Please try again!</p>
            <% } %>
            <div class="mb-4">
                <label for="loginid" class="block text-gray-700 text-sm font-bold mb-2">Username:</label>
                <input type="text" name="loginid" id="loginid" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
            </div>
            <div class="mb-6">
                <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password:</label>
                <input type="password" name="password" id="password" class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
            </div>
            <div class="flex flex-col items-center">
                <input type="submit" name="btnSubmit" value="Login" class="bg-dark-blue hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full focus:outline-none focus:shadow-outline w-48">
                <a href="#" class="text-gray-400 mt-2">Register Now</a>
            </div>
        </form>
    </div>
</body>



</html>