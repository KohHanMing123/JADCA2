<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="books.*" %>
<% 
String isbn = request.getParameter("book");
Book book = SQLquery.getBook(isbn);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
            <img class="h-80" src="https://kbimages1-a.akamaihd.net/00437f92-768a-4b3d-9f43-a1c2ac75816a/1200/1200/False/geronimo-stilton-and-the-kingdom-of-fantasy-2-the-quest-for-paradise.jpg" >
        </div>
        <div class="flex flex-col ml-20">
            <p class="font-semibold font-serif text-4xl"><%=book.getTitle() %></p>
            <p class="text-lg font-serif"><%=book.getAuthor() %></p>
            <p class="text-md mt-3">ISBN13: <%=book.getISBN() %></p>
            <div class="w-full mt-6 ">
                <p><%=book.getDescription() %></p>
            </div>
        </div>
    </div>
		<%@ include file = "../components/footer.html" %>
	</div>	
</body>
</html>