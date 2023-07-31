<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="icon" href="../assets/logo.png" type="image/png">
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
<title>BooksGalore Home</title>
</head>
<body class="min-h-screen flex flex-col">
<%@ page import ="models.SQLqueryBook,models.Book,java.util.ArrayList" %>
	<div class="bg-sand grow">
		<%@ include file = "../components/navBar.html" %>
		<%
		SQLqueryBook query = new SQLqueryBook();
				ArrayList<Book> books = query.getNewArrivalBooks(3, 0);
		%>
		<div class="h-96 flex flex-col items-center">
	        <div class="flex h-20 items-end w-4/5">
	            <p class="font-semibold text-3xl">New Arrivals!</p>
	        </div>
	        <div class="flex gap-10 justify-start mt-10 min-w-fit w-4/5">
		        <%
					for(int i = 0; i < books.size(); i++){
				%>
					<div class="flex hover:cursor-pointer hover:scale-105 duration-300" onclick="window.location.href='Book.jsp?book=<%=books.get(i).getID()%>'">
		                <div class="bg-grey">
		                    <img class="h-56 w-40" src="data:image/jpeg;base64,<%=books.get(i).getImage()%>" >
		                </div>
		                <div class="bg-light-blue h-56 w-64 px-4 pt-2">
		                    <p class="font-semibold text-xl"><%=books.get(i).getTitle()%></p>
		                    <p class="text-sm">By: <%=books.get(i).getAuthor()%></p>
		                    <p class="mt-4 text-xs max-h-24 overflow-hidden"><%=books.get(i).getDescription()%></p>
		                    <div class="flex justify-end gap-2 mt-3">
		                        <button class="hover:scale-105 duration-200 bg-dark-blue text-grey px-2">Add To Cart</button>
		                    </div>
		                </div>
	            	</div>
				<% 
					}
				%>
	        </div>
	    </div>
	    <div class="h-64 flex flex-col items-center">
	        <div class="flex h-20 items-end w-4/5">
	            <p class="font-semibold text-2xl">Search By:</p>
	        </div>
	        <div class="flex justify-start w-4/5 mt-8 gap-10">
	            <a href="BookGenre.jsp" class="bg-dark-blue text-xl text-white px-14 rounded-lg py-1">Genre</a>
	        </div>
	    </div>
	</div>
	<%@ include file = "../components/footer.html" %>
</body>
</html>