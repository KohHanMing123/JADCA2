<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" href="../assets/logo.png" type="image/png">
<meta charset="UTF-8">
<title>Search Book</title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body>
<%@ page import ="books.*, java.util.*" %>
	<%
	SQLqueryBook query = new SQLqueryBook();
	ArrayList<Book> books = query.getNewArrivalBooks(8, 0);
	%>
	<div class="bg-sand">
		<%@ include file = "../components/navBar.html" %>
		 <div class="flex justify-center my-10">
	        <form action="">
	            <div class="flex">
	                <input type="text" class="text-xl py-1 rounded-l-lg w-96 pl-2" name="searchInput">
	                <button type="submit" class="flex items-center px-3 bg-white border-l rounded-r-lg">
	                    <i class="fa-solid fa-magnifying-glass fa-xl"></i>
	                </button>
	            </div>
	        </form>
	    </div>
	
	    <div class="flex justify-center w-screen flex-wrap gap-10">
	    	<%
				for(int i = 0; i < books.size(); i++){
			%>
	        <div class="flex w-1/4 justify-center mb-5">
	            <img class="h-56" src="data:image/jpeg;base64,<%=books.get(i).getImage()%>" >
	            <div class="bg-light-blue pl-4 pt-2">
	                <p class="font-semibold text-2xl"><%=books.get(i).getTitle()%></p>
			        <p class="text-sm">By: <%=books.get(i).getAuthor()%></p>
			        <p class="mt-4 text-xs max-h-24 overflow-hidden"><%=books.get(i).getDescription()%></p>
	            </div>
	        </div>
			<% 
				}
			%>
	    </div>
		<%@ include file = "../components/footer.html" %>
	</div>	
</body>
</html>