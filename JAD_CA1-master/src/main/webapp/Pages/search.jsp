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
<body class="min-h-screen">
<%@ page import ="books.*, java.util.*" %>
	<%
	String search = request.getParameter("search");
	if(search == null){
		search = "";
	}
	System.out.println(request.getContextPath());
	SQLqueryBook query = new SQLqueryBook();
	ArrayList<Book> books = query.searchBook(search, "", 0, 0, "", 9, 0);
	
	%>
	<div class="bg-sand min-h-screen flex flex-col">
		<%@ include file = "../components/navBar.html" %>
		 <div class="flex justify-center my-10">
	        <form action="<%=request.getContextPath()%>/SearchBook" method="post">
	            <div class="flex">
	                <input type="text" class="text-xl py-1 rounded-l-lg w-96 pl-2" name="searchInput" value="<%=search%>">
	                <button type="submit" class="flex items-center px-3 bg-white border-l rounded-r-lg">
	                    <i class="fa-solid fa-magnifying-glass fa-xl"></i>
	                </button>
	            </div> 
	        </form>
	    </div>
	
	    <div class="flex justify-center flex-wrap gap-10 grow">
	    	<%
				for(int i = 0; i < books.size(); i++){
			%>
	        <div class="flex justify-center mb-5 hover:cursor-pointer hover:scale-105 duration-300">
	            <img class="h-56" src="data:image/jpeg;base64,<%=books.get(i).getImage()%>" >
	            <div class="bg-light-blue px-4 pt-2 h-56 w-80">
	                <p class="font-semibold text-2xl"><%=books.get(i).getTitle()%></p>
			        <p class="text-sm">By: <%=books.get(i).getAuthor()%></p>
			        <p class="mt-4 text-xs max-h-24 overflow-hidden"><%=books.get(i).getDescription()%></p>
			        <div class="flex justify-end gap-2 mt-3">
                        <input type="number" step="1" class="w-14 text-center" value="1">
                        <button class="hover:scale-105 duration-200 bg-dark-blue text-grey px-2">Add To Cart</button>
                    </div>
	            </div>
	        </div>
			<% 
				}
			%>
	    </div>
	    <div class="flex justify-center">
	    
	    </div>
		<%@ include file = "../components/footer.html" %>
	</div>	
</body>
</html>