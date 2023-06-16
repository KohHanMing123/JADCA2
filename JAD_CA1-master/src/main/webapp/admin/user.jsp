<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="user.*" %>
<%
String userID = request.getParameter("id");
if(userID == null){
	response.sendRedirect("users.jsp");
	return;
}
    SQLqueryUser query = new SQLqueryUser();
    User currentUser = null;
    try{
    	currentUser = query.getUserInfo(userID);
    }catch(Exception e){
    	response.sendRedirect("users.jsp");
    	return;
    }
    String password = "";
    for(int i = 0; i < currentUser.getPassword().length(); i++){
    	password += "*";
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User: <%=currentUser.getUsername() %></title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body>
	<div id="side-navbar" class="fixed top-0 left-0 z-40 w-64 h-screen transition-transform translate-x-0">
        <div class="h-full px-3 py-7 overflow-y-auto bg-tan">
           <ul class="space-y-2">
              <li>
                 <a href="users.jsp" class="flex items-center p-2 rounded-lg text-xl font-semibold text-white bg-gray-700">
                    <i class="fa-solid fa-user fa-lg"></i>
                    <span class="ml-3">Users</span>
                 </a>
              </li>
              <li>
                 <a href="books.jsp" class="flex items-center p-2 rounded-lg text-xl font-semibold text-black hover:text-white hover:bg-gray-700">
                    <i class="fa-solid fa-book fa-lg"></i>
                    <span class="flex-1 ml-3 whitespace-nowrap">Books</span>
                 </a>
              </li>
           </ul>
           <div class="bottom-0 left-0 fixed flex justify-center w-full">
            <a class="hover:cursor-pointer mx-3 rounded-lg py-2 px-2 mb-7 flex items-center text-black hover:text-white hover:bg-gray-700 w-full">
                <i class="fa-solid fa-right-from-bracket fa-xl"></i>
                <p class=" ml-3 font-bold">Log Out</p>
            </a>
           </div>
        </div>
    </div>
    <div class="bg-sand h-screen pl-72 pr-7">
    	<div class="pt-7 flex">
    		<p class="text-2xl font-semibold">User</p>
    		<div class="grow flex justify-end">
    			<button name="deleteUser" class="text-lg font-semibold px-5 text-white py-1 bg-maroon rounded-lg">Delete</button>
    		</div>
    	</div>
    	<div class="flex justify-center mt-20">
    		<div class='flex flex-col items-center'>
    			<div class="bg-gray-300 w-40 h-40 rounded-full border-2 border-black flex items-center justify-center">	
    				<label for="image"><img class="w-40 h-40 rounded-full" src="<%= request.getContextPath() %>/getImage?id=<%=userID %>" /></label>
    			</div>
    			<label for="image" class="mt-5 text-md text-gray-500">Change Profile Image</label>
    			<input type="file" class="hidden" name="imageFile" accept=".png, .jpg, .jpeg" id="image">
    		</div>
    		<div class='flex flex-col text-lg ml-20'>
	    		<div class="flex">
	    			<p class="mr-3 w-32 font-semibold">Username:</p>
	    			<input type="text" class="pl-3 rounded-md" value="<%=currentUser.getUsername() %>">
	    		</div>
    			<div class="flex mt-3">
	    			<p class="mr-3 w-32 font-semibold">Email:</p>
	    			<input type="text" class="pl-3 rounded-md w-72" value="<%=currentUser.getEmail() %>">
	    		</div>
	    		<div class="flex mt-3">
	    			<p class="mr-3 w-32 font-semibold">Password:</p>
	    			<input id="password" type="text" class="pl-3 rounded-md" disabled value="<%=password %>">
	    			<button class="ml-4 text-gray-500 text-sm" onclick="changePassword()">Change Password</button>
	    		</div>
	    		<div class="flex justify-end mt-10">
	    			<button name="editUser" class="text-lg font-semibold px-5 text-white py-1 bg-dark-blue rounded-lg">Save Changes</button>
	    		</div>
    		</div>
    	</div>
    </div>	
    <script>
    	function changePassword(){
    		 const password  = document.getElementById("password");
    		 password.disabled = false;
    		 password.value = "";
    	}
    </script>
    
</body>
</html>