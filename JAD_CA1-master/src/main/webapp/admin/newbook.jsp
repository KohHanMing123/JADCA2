
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="books.*,java.util.*" %>
<%
	SQLqueryBook query = new SQLqueryBook();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Book</title>
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
                 <a href="users.jsp" class="flex items-center p-2 rounded-lg text-xl text-black hover:text-white font-semibold hover:bg-gray-700">
                    <i class="fa-solid fa-user fa-lg"></i>
                    <span class="ml-3">Users</span>
                 </a>
              </li>
              <li>
                 <a class="flex items-center p-2 rounded-lg text-xl font-semibold text-white bg-gray-700">
                    <i class="fa-solid fa-book fa-lg"></i>
                    <span class="flex-1 ml-3 whitespace-nowrap">Books</span>
                 </a>
              </li>
           </ul>
           <div class="bottom-0 left-0 fixed flex justify-center w-full">
            <a class="hover:cursor-pointer mx-3 rounded-lg py-2 px-2 mb-7 flex items-center text-black hover:text-white hover:bg-gray-100 dark:hover:bg-gray-700 w-full">
                <i class="fa-solid fa-right-from-bracket fa-xl"></i>
                <p class=" ml-3 font-bold">Log Out</p>
            </a>
           </div>
        </div>
    </div>
     <div class="bg-sand h-screen pl-72 pr-7">
        <div class="flex flex-col pt-7">
	        <div class="basis-1/2">
	        	<p class="text-2xl font-semibold">New Book</p>
	        </div>
	        <form>
	        	<div class="flex flex-row mt-16">
		        	<div class="basis-1/2 flex flex-col h-full justify-start">
		        		<div class="flex">
		        			<p class="text-lg mr-5 font-semibold">Image:</p>
		        			<input type="file" name="imageFile" accept=".png, .jpg, .jpeg" id="image">
		        		</div>
		        		<div class="flex mt-3">
		        			<p class="text-lg mr-5 font-semibold">Title:</p>
		        			<input type="text" class="rounded-lg pl-2 w-72" name="title">
		        		</div>
		        		<div class="flex mt-3">
		        			<p class="text-lg mr-5 font-semibold">Author:</p>
		        			<input type="text" class="rounded-lg pl-2 w-52" name="author">
		        		</div>
		        		<div class="flex mt-7">
		        			<p class="text-lg mr-5 font-semibold">Publication Date:</p>
		        			<input type="date" class="rounded-lg pl-2 w-52" name="date">
		        		</div>
		        		<div class="flex mt-3">
		        			<p class="text-lg mr-5 font-semibold">Genre:</p>
		        			<input type="text" class="rounded-lg pl-2 w-52" name="genre">
		        		</div>
		        		<div class="flex mt-3">
		        			<p class="text-lg mr-5 font-semibold">ISBN-13:</p>
		        			<input type="text" class="rounded-lg pl-2 w-52" name="isbn">
		        		</div>
		        		<div class="flex mt-7">
		        			<p class="text-lg mr-5 font-semibold">Price:</p>
		        			<input type="number" step="0.01" class="rounded-lg pl-2 w-20" name="price">
		        			<p class="text-lg mr-5 font-semibold ml-5">Stock:</p>
		        			<input type="number" step="1" class="rounded-lg pl-2 w-20" name="stock">
		        		</div>
			        	<div class="flex mt-7 flex-col">
			        		<p class="text-lg mr-5 font-semibold">Description:</p>
			        		<textarea class="rounded-lg" name="desc" cols="70" rows="5"></textarea>
				        </div>
		        	</div>
	        		<div class="basis-1/2 flex flex-col items-end">
	        			<div>
	        				<p>Sample Book View:</p>
		        			<div class="h-96 w-64 bg-grey flex justify-center items-center">
		        			<img id="previewImage" src="data:image/jpeg;base64,<%=query.getDefaultBookImage() %>" class="h-96 w-64" alt="Preview Image">
		        			</div>
	        			</div>
	        		</div>
	        	</div>   
	        </form>
        </div>
     </div>
     
     <script>
     	//To update image preview on upload
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