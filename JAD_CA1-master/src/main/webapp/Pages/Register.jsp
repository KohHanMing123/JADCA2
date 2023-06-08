<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
<title>Register</title>
</head>

<body class="bg-sand">
    <%@ include file="../components/navBar.html" %>
    <div class="flex justify-center items-center min-h-screen">
        <div class="bg-grey shadow-md rounded-md border border-gray-300 max-w-xl px-12 py-6">
            <div class="flex justify-between items-center mb-4">
                <h2 class="text-2xl font-bold">Register</h2>
                <button type="button" class="text-black text-xl">
                    <i class="fa-solid fa-xmark"></i>
                </button>
            </div>
            <form action="<%= request.getContextPath() %>/RegisterCustomerServlet" method="post">
                <div class="mb-4">
                    <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Username:</label>
                    <input type="text" id="username" name="username" placeholder="Enter your username"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
                </div>
                <div class="mb-4">
                    <label for="email" class="block text-gray-700 text-sm font-bold mb-2">Email:</label>
                    <input type="email" id="email" name="email" placeholder="Enter your email address"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
                </div>
                <div class="mb-4">
                    <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password:</label>
                    <input type="password" id="password" name="password" placeholder="Enter your password"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
                </div>
                <div class="mb-4">
                    <label for="confirmPassword" class="block text-gray-700 text-sm font-bold mb-2">Confirm Password:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm your password"
                        class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline" required>
                        
                   <% Boolean passwordMatch = (Boolean) session.getAttribute("passwordMatch");
					   if (passwordMatch != null && !passwordMatch) { %>
					   <p class="text-red-500">Passwords must match.</p>
				   <% } %>
                    
                </div>
                <div class="flex flex-col items-center">
                    <input type="submit" name="btnSubmit" value="Register" class="bg-dark-blue hover:bg-blue-700 text-white font-bold py-2 px-4 rounded-full focus:outline-none focus:shadow-outline w-48">
                    <a href="Login.jsp" class="text-gray-400 mt-2">Back to Login</a>
                </div>
            </form>
        </div>
    </div>
</body>

</html>