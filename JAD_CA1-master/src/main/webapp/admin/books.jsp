<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.SQLqueryAdmin, models.Book, java.util.*" %>
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
	ArrayList<Book> books = new ArrayList<Book>();
	String search = request.getParameter("q");
	if(search == null){
		search = "";
	}
	if(search.equals("")){
		books = adminQuery.getAllBooks();
	}else{
		books = adminQuery.searchBook(search);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Books</title>
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
           <form class="bottom-0 left-0 fixed flex justify-center w-full" action="<%=request.getContextPath()%>/AdminLogout" method="post">
           		<button type="submit" class="hover:cursor-pointer mx-3 rounded-lg py-2 px-2 mb-7 flex items-center text-black hover:text-white hover:bg-gray-100 dark:hover:bg-gray-700 w-full">
	                <i class="fa-solid fa-right-from-bracket fa-xl"></i>
	                <p class=" ml-3 font-bold">Log Out</p>
            	</button>
           </form>
        </div>
    </div>
     <div class="bg-sand min-h-screen pl-72 pr-7">
        <div class="pt-7 flex">
            <div class="basis-1/3">
                <p class="text-2xl font-semibold">Books</p>
            </div>
            <div class="basis-1/3 flex justify-center">
            	<form class="flex" action="<%=request.getContextPath() %>/AdminSearchBook" method="post">
            		<input type="text" class="text-xl py-1 rounded-l-lg w-96 pl-2" name="searchInput" value="<%=search%>">
	                <button type="submit" class="flex items-center px-3 bg-white border-l rounded-r-lg">
	                    <i class="fa-solid fa-magnifying-glass fa-xl"></i>
	                </button>
            	</form>
            </div>
            <div class="basis-1/3 flex justify-end">
            	<a href="<%=request.getContextPath() %>/admin/newbook.jsp" class="bg-dark-blue px-4 rounded-lg text-white py-1 font-semibold">New Book</a>
            </div>
        </div>
        <div class="flex mt-8 border-b-2 pb-2 border-gray-400">
            <div class="basis-5/12">
                <p class="font-semibold">Title</p>
            </div>
            <div class="basis-4/12">
                <p class="font-semibold">Author</p>
            </div>
            <div class="basis-1/12 flex">
                <p class="font-semibold">Genre</p>
            </div>
            <div class="basis-1/12 flex justify-end">
                <p class="font-semibold">Price</p>
            </div>
            <div class="basis-1/12 flex justify-end">
                <p class="font-semibold">Stock</p>
            </div>
        </div>
        <ul class="">
     	<% for(int i = 0; i < books.size(); i++){ %>
         <li>
             <a href="book.jsp?id=<%=books.get(i).getID()%>" class="flex border-b-2 py-2 duration-100 hover:bg-light-blue border-gray-300">
                <div class="basis-5/12">
                	<p class=""><%=books.get(i).getTitle() %></p>
	            </div>
	            <div class="basis-4/12">
	                <p class=""><%=books.get(i).getAuthor() %></p>
	            </div>
	            <div class="basis-1/12 flex">
	                <p class=""><%=books.get(i).getGenre() %></p>
	            </div>
	            <div class="basis-1/12 flex justify-end">
	                <p class="">$<%=books.get(i).getPrice() %></p>
	            </div>
	            <div class="basis-1/12 flex justify-end">
	                <p class=""><%=books.get(i).getStock() %></p>
	            </div>
             </a>
         </li>
         <%} %>
     </ul> 
     </div>
</body>
</html>