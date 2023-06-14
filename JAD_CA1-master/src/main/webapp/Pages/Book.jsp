<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="books.*" %>
<% 
String idStr = request.getParameter("book");
System.out.println("Book.jsp book idStr " + idStr);
int id = 0;
if (idStr != null) {
    id = Integer.parseInt(idStr);
}

System.out.println("Book.jsp book id " + id);

SQLqueryBook query = new SQLqueryBook();
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
<body class="min-h-screen flex flex-col">
	<div class="bg-sand grow">
		<%@ include file = "../components/navBar.html" %>
		<div class="mx-64 my-20 flex">
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
            <div class="flex mt-6">
            	<div class="basis-1/2 flex">
            		<p class="text-xl font-semibold">$<%=book.getPrice() %></p>
            	</div>
            	<div class="basis-1/2 flex justify-end">
            		<form action="<%= request.getContextPath() %>/addToCart" method="post" class="flex justify-end" >
            			<input type="hidden" name="book" value="<%=book.getID()%>">
						<input type="hidden" name="unitPrice" value="<%=book.getPrice()%>">	
            			<input class="text-lg w-16 h-9 text-center" value="1" type="number" min="1" step="1" name="bookQuantity">
            			<button class="text-lg font-semibold bg-dark-blue text-white py-1 px-2" >Add To Cart</button>
            		</form>	
            	</div>
            </div>
        </div>
    </div>
    </div>	
		<%@ include file = "../components/footer.html" %>
</body>
</html>