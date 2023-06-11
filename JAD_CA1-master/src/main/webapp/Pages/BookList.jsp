<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="books.Book" %>
<%@ page import="books.SQLquery" %>
<%
    SQLquery query = new SQLquery();
    List<Book> bookList = query.getAllBooks();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="icon" href="../assets/logo.png" type="image/png">
    <title>Book List</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="../js/twCustom.js"></script>
    <script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="bg-sand">
        <%@ include file="../components/navBar.html" %>
        <div class="h-max mx-64 my-20">
            <div class="grid grid-cols-6 gap-4">
                <% for (Book book : bookList) { %>
                <div class="flex flex-col items-center">
                    <div class="max-w-xs">
                        <img class="h-40" src="data:image/jpeg;base64, <%=book.getImage()%>">
                        <p class="text-lg font-bold overflow-hidden whitespace-nowrap overflow-ellipsis"><%=book.getTitle() %></p>
                        <p class="text-sm font-medium overflow-hidden whitespace-nowrap overflow-ellipsis"><%=book.getAuthor() %></p>
                    </div>
                    <button class="mt-2 py-2 px-4 bg-blue-500 hover:bg-blue-600 rounded-full text-white">Add to Cart</button>
                </div>
                <% } %>s
            </div>
        </div>
    </div>
</body>

</html>
