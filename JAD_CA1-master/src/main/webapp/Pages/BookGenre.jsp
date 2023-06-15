<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
<title>Book Genre</title>
</head>
<body class="bg-sand">
    <%@ include file="../components/navBar.html" %>

    <h1 class="text-4xl text-center font-bold my-8">Genre</h1>

    <div class="flex flex-wrap justify-center">
        <!-- Button 1 -->
        <a href="BookList.jsp?genre=Fantasy" class="w-1/5 h-72 bg-light-blue m-6 rounded-xl flex flex-col items-center justify-center">
            <i class="fas fa-wand-sparkles text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Fantasy</span>
        </a>
        <!-- Button 2 -->
        <a href="BookList.jsp?genre=Thriller" class="w-1/5 h-72 bg-dark-blue m-6 rounded-xl flex flex-col items-center justify-center">
            <i class="fas fa-user-secret text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Thriller</span>
        </a>
        <!-- Button 3 -->
        <a href="BookList.jsp?genre=Horror" class="w-1/5 h-72 bg-light-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-sharp fa-solid fa-ghost text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Horror</span>
        </a>
        <!-- Button 4 -->
        <a href="BookList.jsp?genre=Romance" class="w-1/5 h-72 bg-dark-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-solid fa-heart text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Romance</span>
        </a>
        <!-- Button 5 -->
        <a href="BookList.jsp?genre=Science Fiction" class="w-1/5 h-72 bg-light-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-solid fa-atom text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Sci-Fi</span>
        </a>
        <!-- Button 6 -->
        <a href="BookList.jsp?genre=History" class="w-1/5 h-72 bg-dark-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-solid fa-landmark text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">History</span>
        </a>
        <!-- Button 7 -->
        <a href="BookList.jsp?genre=Cooking" class="w-1/5 h-72 bg-light-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-solid fa-bowl-food text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Cooking</span>
        </a>
        <!-- Button 8 -->
        <a href="BookList.jsp?genre=Comedy" class="w-1/5 h-72 bg-dark-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-solid fa-masks-theater text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Comedy</span>
        </a>
        
        <!-- Button 9 -->
        <a href="BookList.jsp?genre=Historical Fiction" class="w-1/5 h-72 bg-dark-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-solid fa-hippo text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Historical Fiction</span>
        </a>
        
        <!-- Button 10 -->
        <a href="BookList.jsp?genre=Fiction" class="w-1/5 h-72 bg-dark-blue m-6 rounded-xl flex flex-col items-center justify-center">
        <i class="fa-solid fa-earth-americas text-8xl mb-9"></i>
            <span class="text-black text-3xl font-bold">Fiction</span>
        </a>
    </div>
</body>

</html>