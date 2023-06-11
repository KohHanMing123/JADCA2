<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
                 <a href="#" class="flex items-center p-2 rounded-lg text-xl font-semibold text-black hover:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
                    <i class="fa-solid fa-user fa-lg"></i>
                    <span class="ml-3">Users</span>
                 </a>
              </li>
              <li>
                 <a href="#" class="flex items-center p-2 rounded-lg text-xl font-semibold text-black hover:text-white hover:bg-gray-100 dark:hover:bg-gray-700">
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
        <div class="pt-7 flex">
            <div class="basis-1/2">
                <p class="text-2xl font-semibold">Users</p>
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
            <li>
                <a href="#" class="flex border-b-2 py-2 duration-100 hover:bg-light-blue border-gray-300">
                    <div class="basis-1/3">
                        <p class="pl-2">Jerald Lee</p>
                    </div>
                    <div class="basis-1/3">
                        <p class="">jleeshancheng@gmail.com</p>
                    </div>
                </a>
            </li>
            <li>
                <a href="#" class="flex border-b-2 py-2 duration-100 hover:bg-light-blue border-gray-300">
                    <div class="basis-1/3">
                        <p class="pl-2">Jerald Lee</p>
                    </div>
                    <div class="basis-1/3">
                        <p class="">jleeshancheng@gmail.com</p>
                    </div>
                </a>
            </li>
        </ul>
     </div>
</body>
</html>