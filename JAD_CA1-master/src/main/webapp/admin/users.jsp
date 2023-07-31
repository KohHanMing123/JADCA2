<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="models.SQLqueryAdmin,models.User,java.util.*" %>
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
	String search = request.getParameter("q");
	if(search == null){
		search = "";
	}
	ArrayList<User> users = new ArrayList<User>();
	if(search.equals("")){
		users = adminQuery.getAllUsers();
	}else{
		users = adminQuery.searchUser(search);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
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
                 <a class="flex items-center p-2 rounded-lg text-xl font-semibold text-white bg-gray-700">
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
            <div class="basis-1/3">
                <p class="text-2xl font-semibold">Users</p>
            </div>
            <div class="basis-1/3 flex justify-center">
            	<form class="flex" action="<%=request.getContextPath() %>/AdminSearchUser" method="post">
            		<input type="text" class="text-xl py-1 rounded-l-lg w-96 pl-2" name="searchInput" value="<%=search%>">
	                <button type="submit" class="flex items-center px-3 bg-white border-l rounded-r-lg">
	                    <i class="fa-solid fa-magnifying-glass fa-xl"></i>
	                </button>
            	</form>
            </div>
            <div class="flex justify-end basis-1/3">
            	<a href="newUser.jsp" class="py-1 px-3 font-semibold rounded-lg bg-dark-blue text-white">Create User</a>
            </div>
        
        </div>
        <div class="flex mt-8 border-b-2 pb-2 border-gray-400">
            <div class="basis-1/3">
                <p class="font-semibold">User Name</p>
            </div>
            <div class="basis-1/3">
                <p class="font-semibold">Email</p>
            </div>
        </div>
        <ul class="">
        	<% for(int i = 0; i < users.size(); i++){ %>
            <li>
                <a href="user.jsp?id=<%=users.get(i).getUserID()%>" class="flex border-b-2 py-2 duration-100 hover:bg-light-blue border-gray-300">
                    <div class="basis-1/3">
                        <p class="pl-2"><%=users.get(i).getUsername() %></p>
                    </div>
                    <div class="basis-1/3">
                        <p class=""><%=users.get(i).getEmail() %></p>
                    </div>
                </a>
            </li>
            <%} %>
        </ul>
     </div>
</body>
</html>