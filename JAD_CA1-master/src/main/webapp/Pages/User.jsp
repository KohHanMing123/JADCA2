<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="models.UserDAO,models.User" %>

<%
UserDAO query = new UserDAO();
	String custID = (String) session.getAttribute("custID");
	System.out.println("custID in user.jsp " + custID);
	User user = null;
	
	try {
	    user = query.getUserInfo(custID);
	} catch (Exception e) {
	    System.out.println("No custID found");
	    e.printStackTrace();
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Profile</title>
<link rel="icon" href="../assets/logo.png" type="image/png">
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body class="bg-sand">
  <%@ include file="../components/navBar.html" %>
  <div class="container mx-auto py-8 flex flex-col items-center justify-center">
    <h1 class="text-3xl mb-6 text-center font-bold">Update Profile</h1>
    <div class="bg-grey p-8 rounded shadow">
       <form action="<%= request.getContextPath() %>/UpdateUserInfo" method="post" enctype="multipart/form-data">
        <% if (user != null) { %>
        
		<div class="mb-8 flex items-center justify-center">
		  <div class="relative inline-block">
		    <label for="imageInput" class="cursor-pointer">
		      <div class="bg-gray-300 w-40 h-40 rounded-full border-2 border-black flex items-center justify-center">		        

		        <img id="profileImage" class="w-40 h-40 rounded-full" src="<%= request.getContextPath() %>/getImage?id=<%= session.getAttribute("custID") %>" alt="Profile Image">

		      </div>
		      <input type="file" id="imageInput" name="imageInput" class="hidden">
		    </label>
		  </div>
		</div>
		<%-- could add class="w-40 h-40 rounded-full" for img --%>
		<%-- <i class="fa-solid fa-plus text-2xl"></i> --%>
        
        <div class="mb-8 flex items-center">
          <label for="username" class="block font-bold text-xl">
            Username:
          </label>
          <div class="flex items-center ml-2">
            <span id="usernameValue" class="text-xl mr-2"><%= user.getUsername() %></span>
            <input
              type="text"
              id="usernameInput"
              name="username"
              class="w-3/2 ml-2 pl-2 pr-3 h-15 text-lg placeholder-gray-500 text-black rounded-lg border-none ring-2 ring-gray-300 focus:ring-gray-500 focus:ring-2 hidden"
              placeholder=""
              autocomplete="off"
            >
            <i class="fa-solid fa-pen-to-square cursor-pointer ml-3 mt-1 text-gray-500 hover:text-gray-700" onclick="toggleUsernameInput()"></i>
          </div>
        </div>

	        <div class="mb-8 flex items-center">
			  <label for="password" class="block font-bold text-xl">
			    Password:
			  </label>
			  <div class="flex items-center ml-2">
			    <span id="passwordValue" class="text-xl mr-2"><%= user.getPassword() %></span>
			    <div class="relative">
			      <input
			        type="password"
			        id="passwordInput"
			        name="password"
			        class="w-3/2 ml-2 pl-2 pr-10 h-15 text-lg placeholder-gray-500 text-black rounded-lg border-none ring-2 ring-gray-300 focus:ring-gray-500 focus:ring-2 hidden"
			        placeholder=""
			        autocomplete="off"
			      >
			      <label for="passwordInput" class="absolute inset-y-0 right-3 flex items-center cursor-pointer">
			        <i class="far fa-eye text-gray-500 hover:text-gray-700 opacity-0"></i>
			      </label>
			    </div>
			    <i class="fa-solid fa-pen-to-square text-gray-500 hover:text-gray-700 ml-3 cursor-pointer" onclick="togglePasswordInput()"></i>
			  </div>
			</div>

        
        <div class="mb-8 flex items-center">
          <label for="email" class="block font-bold text-xl">
            Email:
          </label>
          <div class="flex items-center ml-2">
            <span id="emailValue" class="text-xl mr-2"><%= user.getEmail() %></span>
            <input
              type="email"
              id="emailInput"
              name="email"
              class="w-80 ml-2 pl-2 pr-3 h-15 text-lg placeholder-gray-500 text-black rounded-lg border-none ring-2 ring-gray-300 focus:ring-gray-500 focus:ring-2 hidden"
              placeholder=""
              autocomplete="off"
            >
            <i class="fa-solid fa-pen-to-square cursor-pointer ml-3 mt-1 text-gray-500 hover:text-gray-700" onclick="toggleEmailInput()"></i>
          </div>
        </div>
        
        <div class="flex items-center justify-center">
          <button type="submit" class="bg-dark-blue text-white py-2 px-4 rounded-xl hover:bg-sky-900">Update</button>
          <a href="<%= request.getContextPath() %>/UserLogout" class="bg-maroon text-white py-2 px-4 rounded-xl hover:bg-red-700 ml-2">Logout</a>
        </div>
        </form>
        
        <div class="flex items-center justify-center mt-4">
		  <form action="<%= request.getContextPath() %>/DeleteUser" method="post">
		    <input type="hidden" name="username" value="<%= user.getUsername() %>">
		    <button type="submit" class="bg-red-500 text-white py-2 px-4 rounded-xl hover:bg-red-700">Delete User</button>
		  </form>
		</div>
        
        
        <% } else { %>
        <div class="flex items-center justify-center h-80">
          <h2 class="text-4xl font-bold">No user found</h2>
        </div>
        <% } %>
      
    </div>
  </div>
  
  <script>
  
  function toggleUsernameInput() {
    const usernameValue = document.getElementById('usernameValue');
    const usernameInput = document.getElementById('usernameInput');
    usernameValue.classList.toggle('hidden');
    usernameInput.classList.toggle('hidden');
    if (!usernameInput.classList.contains('hidden')) {
      usernameInput.focus();
    }
  }
  
  function togglePasswordInput() {
	  const passwordValue = document.getElementById('passwordValue');
	  const passwordInput = document.getElementById('passwordInput');
	  const eyeIcon = document.querySelector('#passwordInput + label i');

	  passwordValue.classList.toggle('hidden');
	  passwordInput.classList.toggle('hidden');
	  eyeIcon.classList.toggle('opacity-0');

	  if (!passwordInput.classList.contains('hidden')) {
	    passwordInput.focus();
	  }
	}
  
  function toggleEmailInput() {
	    const emailValue = document.getElementById('emailValue');
	    const emailInput = document.getElementById('emailInput');
	    emailValue.classList.toggle('hidden');
	    emailInput.classList.toggle('hidden');
	    if (!emailInput.classList.contains('hidden')) {
	    	emailInput.focus();
	    }
	  }
  
  const passwordInput = document.getElementById('passwordInput');
  const eyeIcon = document.querySelector('.fa-eye');

  eyeIcon.addEventListener('click', function () {
    const isPasswordVisible = passwordInput.type === 'text';
    passwordInput.type = isPasswordVisible ? 'password' : 'text';
    eyeIcon.classList.toggle('fa-eye-slash', !isPasswordVisible);
  });
</script>
</body>
</html>
