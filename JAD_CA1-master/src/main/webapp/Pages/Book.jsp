<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="books.*" %>
<% 
String idStr = request.getParameter("book");
int id = 0;
try{
	id = Integer.parseInt(idStr);
}catch(NumberFormatException e){
	
}
SQLquery query = new SQLquery();
Book book = query.getBook(id);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="icon" href="../assets/logo.png" type="image/png">
<title><%=book.getTitle() %></title>
<script src="https://cdn.tailwindcss.com"></script>
<script src="../js/twCustom.js"></script>
<script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body>
	<div class="bg-sand">
		<%@ include file = "../components/navBar.html" %>
		<div class="h-max mx-64 my-20 flex">
        <div class="min-w-fit">
            <img class="h-80" src="data:image/jpeg;base64,<%=book.getImage()%>" >
        </div>
        <div class="flex flex-col ml-20">
            <p class="font-semibold font-serif text-4xl"><%=book.getTitle() %></p>
            <p class="text-lg font-serif"><%=book.getAuthor() %></p>
            <p class="text-md mt-3">ISBN13: <%=book.getISBN() %></p>
            <div class="w-full mt-6 ">
                <p><%=book.getDescription() %></p>
            </div>
            <div class="flex mt-6">
            	<p class="text-md text-gray-500">Genre: </p>
            	<a class="ml-4 bg-light-blue px-3 rounded-md hover:scale-105 duration-300"><%=book.getGenre() %></a>
            </div>
            	
        </div>
    </div>
		<%@ include file = "../components/footer.html" %>
	</div>	
</body>
</html>