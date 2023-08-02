<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
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
                 <a href="dashboard.jsp" class="flex items-center p-2 rounded-lg text-xl font-semibold text-white bg-gray-700">
                    <i class="fa-solid fa-chart-line fa-lg"></i>
                    <span class="ml-3">Dashboard</span>
                 </a>
              </li>
              <li>
                 <a href="users.jsp" class="flex items-center p-2 rounded-lg text-xl text-black hover:text-white font-semibold hover:bg-gray-700">
                    <i class="fa-solid fa-user fa-lg"></i>
                    <span class="ml-3">Users</span>
                 </a>
              </li>
              <li>
                 <a class="flex items-center p-2 rounded-lg text-xl text-black hover:text-white font-semibold hover:bg-gray-700">
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
    	<div class="flex flex-col">
    		<div class="h-1/3 flex">
    			<div class="basis-1/3 h-full bg-light-blue">
    			</div>
    			<div class="basis-1/3"></div>
    			<div class="basis-1/3"></div>
    		</div>
    		<div class="basis-1/3 flex">
    			<div class="basis-1/3"></div>
    			<div class="basis-1/3"></div>
    			<div class="basis-1/3"></div>
    		</div>
    		<div class="basis-1/3 flex">
    			<div class="basis-1/3"></div>
    			<div class="basis-1/3"></div>
    			<div class="basis-1/3"></div>
    		</div>
    	</div>
    </div>
</body>
</html>