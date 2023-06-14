
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
                 <a href="books.jsp" class="flex items-center p-2 rounded-lg text-xl font-semibold text-white bg-gray-700">
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
	        <form action="<%=request.getContextPath()%>/CreateBook" method="post" enctype="multipart/form-data">
	        	<div class="flex flex-row mt-12">
		        	<div class="basis-1/2 flex flex-col h-full justify-start">
			        	<div class="flex mb-6">
			        		<p class="font-semibold text-green-600">
			        			<%
			        				String msg = request.getParameter("msg");
			        				if(msg != null){
			        					if(msg.equals("success")){
			        			%>
			        				<%=request.getParameter("t")%> has been successfully created!
			        			<% 
			        					}
			        			%>
			        		</p>
			        		<p class="font-semibold text-maroon">
			        			<%
			        				 	if(msg.equals("badreq")){
			        				
			        			%>
			        					Please fill in all fields! 
			        			<% 
			        					}else if(msg.equals("error")){
			        			%>
			        						An unexpected error occurred. Please try again later
			        			<% 
			        					}
			        				}
			        			%>
			        		</p>
			        	</div>
		        		<div class="flex">
			        		<div class="w-44">
			        			<p class="text-lg mr-5 font-semibold">Image:</p>
			        		</div>
		        			<input type="file" name="imageFile" accept=".png, .jpg, .jpeg" id="image">
		        		</div>
		        		<div class="flex mt-3">
		        			<div class="w-44">
		        				<p class="text-lg mr-5 font-semibold">Title:</p>
		        			</div>
		        			<input required type="text" class="rounded-lg pl-2 w-72" name="title" id="title">
		        		</div>
		        		<div class="flex mt-3">
		        			<div class="w-44">
		        				<p class="text-lg mr-5 font-semibold">Author:</p>
		        			</div>
		        			<input required type="text" class="rounded-lg pl-2 w-52" name="author">
		        		</div>
		        		<div class="flex mt-7">
		        			<div class="w-44">
		        				<p class="text-lg mr-5 font-semibold">Publication Date:</p>
		        			</div>
		        			<input required type="date" class="rounded-lg pl-2 w-52" name="date">
		        		</div>
		        		<div class="flex mt-3">
		        			<div class="w-44">
		        				<p class="text-lg mr-5 font-semibold">Genre:</p>
		        			</div>
		        			<input required type="text" class="rounded-lg pl-2 w-52" name="genre">
		        		</div>
		        		<div class="flex mt-3">
		        			<div class="w-44">
		        				<p class="text-lg mr-5 font-semibold">ISBN-13:</p>
		        			</div>
		        			<input required type="text" class="rounded-lg pl-2 w-52" name="isbn">
		        		</div>
		        		<div class="flex mt-7">
		        			<p class="text-lg mr-5 font-semibold">Price:</p>
		        			<input required type="number" step="0.01" class="rounded-lg pl-2 w-20" name="price">
		        			<p class="text-lg mr-5 font-semibold ml-5">Stock:</p>
		        			<input required type="number" step="1" class="rounded-lg pl-2 w-20" value="0" name="stock">
		        		</div>
			        	<div class="flex mt-7 flex-col">
			        		<p class="text-lg mr-5 font-semibold">Description:</p>
			        		<textarea required class="rounded-lg" name="desc" cols="70" rows="5"></textarea>
			        		<p class="mt-4 text-gray-500" >All fields except image is required.</p>
				        </div>
				        <div class="w-full flex justify-end">
				        	<button class="bg-dark-blue px-5 py-1 font-semibold rounded-md text-white" type="submit">Create Book</button>
				        </div>
		        	</div>
	        		<div class="hidden xl:flex basis-1/2 flex-col items-end">
	        			<div>
		        			<div class="flex items-center">
		        				<p class="font-semibold">Sample Book View:</p>
		        				<div class="grow flex justify-end items-center">
		        					<i class="hover:cursor-pointer hover:scale-105 fa-solid fa-rotate-right"></i>
		        				</div>
		        			</div>
		        			<div class="h-96 w-64 bg-grey flex justify-center items-center">
		        				<img id="previewImage" src="data:image/jpeg;base64,<%=query.getDefaultBookImage() %>" class="h-96 w-64" alt="Preview Image">
		        			</div>
		        			<div class="flex flex-col w-64 mt-2">
		        				<p class="text-lg font-semibold" id="titlePreview"></p>	
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
     	const titleInput = document.getElementById("title");
	     imageInput.addEventListener('change', previewImage);
	     titleInput.addEventListener('change', previewTitle);
	     function previewTitle(){
	    	 document.getElementById("titlePreview").innerText = titleInput.value
	     }
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