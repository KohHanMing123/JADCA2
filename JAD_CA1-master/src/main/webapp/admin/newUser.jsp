<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="models.SQLqueryAdmin" %>
<%
SQLqueryAdmin adminQuery = new SQLqueryAdmin();
try{
	String adminUsername = (String) session.getAttribute("username");
	int adminID = (int) session.getAttribute("adminID");
	if(!adminQuery.verifyAdmin(adminID, adminUsername)){
		response.sendRedirect("login.jsp");
		return;
	}
}catch(Exception e){
	response.sendRedirect("login.jsp");
	return;
}

String msg = request.getParameter("msg");
if(msg == null){
	msg = "";
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New User</title>
<link rel="icon" href="../assets/logo.png" type="image/png">
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
           <form class="bottom-0 left-0 fixed flex justify-center w-full" action="<%=request.getContextPath()%>/AdminLogout" method="post">
           		<button type="submit" class="hover:cursor-pointer mx-3 rounded-lg py-2 px-2 mb-7 flex items-center text-black hover:text-white hover:bg-gray-100 dark:hover:bg-gray-700 w-full">
	                <i class="fa-solid fa-right-from-bracket fa-xl"></i>
	                <p class=" ml-3 font-bold">Log Out</p>
            	</button>
           </form>
        </div>
    </div>
    <div class="bg-sand h-screen pl-72 pr-7">
    	<div class="pt-7 flex">
            <div class="basis-1/2">
                <p class="text-2xl font-semibold">New User</p>
            </div>
        </div>
        <div class="flex justify-center mt-20">
	        <form class="flex" action="<%=request.getContextPath()%>/CreateUser" method="post"  enctype="multipart/form-data">
	    		<div class='flex flex-col items-center'>
	    			<div class="bg-gray-300 w-40 h-40 rounded-full border-2 border-black flex items-center justify-center">	
	    				<label for="image"><img id="previewImage" class="w-40 h-40 rounded-full" /></label>
	    			</div>
	    			<label for="image" class="mt-5 text-md text-gray-500">Add Profile Image</label>
	    			<input type="file" class="hidden" name="imageFile" accept=".png, .jpg, .jpeg" id="image">
	    		</div>
	    		<div class='flex flex-col text-lg ml-20'>
		    		<div class="flex">
		    			<p class="mr-3 w-32 font-semibold">Username:</p>
		    			<input type="text" required name="username" class="pl-3 rounded-md">
		    		</div>
	    			<div class="flex mt-3">
		    			<p class="mr-3 w-32 font-semibold">Email:</p>
		    			<input type="text" required name="email" class="pl-3 rounded-md w-72">
		    		</div>
		    		<div class="flex mt-3">
		    			<p class="mr-3 w-32 font-semibold">Password:</p>
		    			<input id="password" required name="password" type="text" class="pl-3 rounded-md">
		    		</div>
		    		<div class="flex mt-10">
			    		<div class="basis-1/2 flex items-center text-sm">
			    			<%if(msg.equals("userexist")){ %>
			    				<p class="text-maroon">Username is already taken!</p>
			    			<%}else if(msg.equals("emailexist")){ %>
			    				<p class="text-maroon">Email is already taken!</p>
			    			<%}else if(msg.equals("success")){ %>
			    			<p class="text-green-600">Changes Successfully Saved!</p>
			    			<%}%>
			    		</div>
			    		<div class="flex justify-end basis-1/2">
			    			<button name="editUser" type="submit" class="text-lg font-semibold px-5 text-white py-1 bg-dark-blue rounded-lg">Create User</button>
			    		</div>
		    		</div>
	    		</div>
	    	</form>
    	</div>
    </div>
    <script>
	    const imageInput = document.getElementById('image');
	    imageInput.addEventListener('change', previewImage);
	
	    function previewImage() {
			const file = imageInput.files[0];
		 	const reader = new FileReader();
	
	    	reader.onload = function(event) {
	    	   const imageURL = event.target.result;
	    	   const previewImage = document.getElementById('previewImage');
	    	   previewImage.src = imageURL;
	    	};
	
	    	reader.readAsDataURL(file);
		}
    </script>
</body>
</html>