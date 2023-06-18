<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.SQLqueryAdmin, models.Book, models.SQLqueryBook" %>
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
	
	String idStr = request.getParameter("id");
	if(idStr == null){
		response.sendRedirect("books.jsp");
		return;
	}
	int id = 0;
	try{
		id = Integer.parseInt(idStr);
	}catch(NumberFormatException e){
		response.sendRedirect("books.jsp");
		return;
	}
	SQLqueryBook query = new SQLqueryBook();
	Book book = null;
	try{
		book = query.getBook(id);
	}catch(Exception e){
		response.sendRedirect("books.jsp");
		return;
	}
	
	String msg = request.getParameter("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><%=book.getTitle() %></title>
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
         
           <form class="bottom-0 left-0 fixed flex justify-center w-full" action="<%=request.getContextPath()%>/AdminLogout" method="post">
           		<button type="submit" class="hover:cursor-pointer mx-3 rounded-lg py-2 px-2 mb-7 flex items-center text-black hover:text-white hover:bg-gray-100 dark:hover:bg-gray-700 w-full">
	                <i class="fa-solid fa-right-from-bracket fa-xl"></i>
	                <p class=" ml-3 font-bold">Log Out</p>
            	</button>
           </form>
           
        </div>
    </div>
   	<div class="bg-sand h-screen pl-72 pr-7 pt-10">
   	 	<form action="<%=request.getContextPath()%>/EditBook" method="post" enctype="multipart/form-data" id="editBookForm">
   	 		<input name="bookID" type="hidden" value="<%=book.getID() %>">
	    	<div class="flex justify-center">
		    	<div class="flex flex-col">
		    		<img id="previewImage" src="data:image/jpeg;base64,<%=book.getImage() %>" class="h-96 w-64" alt="Preview Image">
		    		<label class="hover:cursor-pointer rounded-lg bg-dark-blue w-fit mt-3 py-1 px-4 font-semibold text-white" for="image">Change Image</label>
		    		<input type="file" class="hidden" name="imageFile" value="Change File" accept=".png, .jpg, .jpeg" id="image">
		    	</div>
	    		<div class="flex flex-col ml-32">
	    			<div class="flex mt-3">
	        			<div class="w-44">
	        				<p class="text-lg mr-5 font-semibold">Title:</p>
	        			</div>
	        			<input required type="text" value="<%=book.getTitle() %>" class="rounded-lg pl-2 w-72" name="title" id="title">
	        		</div>
	        		<div class="flex mt-3">
	        			<div class="w-44">
	        				<p class="text-lg mr-5 font-semibold">Author:</p>
	        			</div>
	        			<input required type="text" value="<%=book.getAuthor() %>" class="rounded-lg pl-2 w-52" name="author">
	        		</div>
	        		<div class="flex mt-7">
	        			<div class="w-44">
	        				<p class="text-lg mr-5 font-semibold">Publication Date:</p>
	        			</div>
	        			<input required type="date" value="<%=book.getDate() %>" class="rounded-lg pl-2 w-52" name="date">
	        		</div>
	        		<div class="flex mt-3">
	        			<div class="w-44">
	        				<p class="text-lg mr-5 font-semibold">Genre:</p>
	        			</div>
	        			<input required type="text" value="<%=book.getGenre()%>" class="rounded-lg pl-2 w-52" name="genre">
	        		</div>
	        		<div class="flex mt-3">
	        			<div class="w-44">
	        				<p class="text-lg mr-5 font-semibold">ISBN-13:</p>
	        			</div>
	        			<input required type="text" value="<%=book.getISBN() %>" class="rounded-lg pl-2 w-52" name="isbn">
	        		</div>
	        		<div class="flex mt-7">
	        			<p class="text-lg mr-5 font-semibold">Price:</p>
	        			<input required type="number" value="<%=book.getPrice() %>" step="0.01" class="rounded-lg pl-2 w-20" name="price">
	        			<p class="text-lg mr-5 font-semibold ml-5">Stock:</p>
	        			<input required type="number" step="1" class="rounded-lg pl-2 w-20" value="<%=book.getStock() %>" name="stock">
	        		</div>
		        	<div class="flex mt-7 flex-col">
		        		<p class="text-lg mr-5 font-semibold">Description:</p>
		        		<textarea required class="rounded-lg pl-2" name="desc" cols="70" rows="8"><%=book.getDescription() %></textarea>
		        		<p class="mt-4 text-gray-500" >All fields except image is required.</p>
			        </div>
			        <div class="w-full flex justify-end">
				        <div class="grow">
				        	<p class="text-green-600 font-semibold mr-10">
			        			<%
				        			if(msg != null){
				        				if(msg.equals("Success")){
				        					out.print("Changes Successfully Saved!");
				        				}
				        			}
			        			%>
			        		</p>
				        </div>
			        	<div class="flex flex-col">
			        		<button name="submitButton" class="bg-dark-blue px-5 py-1 disabled:opacity-30 text-lg font-semibold rounded-md text-white" disabled id="submit" type="submit">Save Changes</button>
			        		<button type="submit"  class="bg-maroon px-5 py-1 disabled:opacity-30 mt-5 text-lg font-semibold rounded-md text-white" name="deleteButton">Delete</button>
			        	</div>
			        </div>
	    		</div>
	    	</div>
    	</form>
    </div>
    
    <script>
    	const form = document.getElementById("editBookForm");
    	form.addEventListener('change', enableSubmit);
    	
    	function enableSubmit(){
    		document.getElementById("submit").disabled = false;
    	}
    	
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