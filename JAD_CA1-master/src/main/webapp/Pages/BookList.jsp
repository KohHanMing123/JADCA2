<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="books.Book" %>
<%@ page import="books.SQLqueryBook" %>
<%
	String genre = request.getParameter("genre");

    SQLqueryBook query = new SQLqueryBook();
    List<Book> bookList = query.getBooksByGenre(genre);

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
<body class="bg-sand">
    <%@ include file="../components/navBar.html" %>
    <div class="h-max mx-64 my-20">
        <div class="grid grid-cols-6 gap-12">
            <% if (bookList.isEmpty()) { %>
            <p class="text-4xl text-center text-bold">There are no books under this genre.</p>
            <% } else { %>
            <% for (Book book : bookList) { %>
            <div class="flex flex-col items-start space-y-4 mb-8 space-x-12">
                <div class="max-w-xs flex flex-col items-start">
                    <div class="h-56 w-40">
                        <img class="h-full object-contain flex hover:cursor-pointer hover:scale-105 duration-300" 
                             src="data:image/jpeg;base64, <%=book.getImage()%>"
                             onclick="redirectToBook('<%=book.getID()%>')">
                    </div>
                    <div class="h-16">
                        <p class="text-sm font-bold mt-2 line-clamp-2"><%=book.getTitle() %></p>
                        <p class="text-xs mt-1 line-clamp-1 font-semibold">
                            By <%=book.getAuthor() %>
                        </p>
                        <button class="mt-2 py-1 px-3 bg-dark-blue hover:bg-blue-600 rounded-full text-sm text-gray font-bold">Add to Cart</button>
                        <i class="fa-regular fa-star" onclick="toggleStar(this)"></i>
                    </div>
                </div>
            </div>
            <% } %>
            <% } %>
        </div>
    </div>
    
    <script>
        function redirectToBook(bookID) {
            window.location.href = 'Book.jsp?book=' + bookID;
        }
        
        function toggleStar(icon) {
            icon.classList.toggle('fa-regular');
            icon.classList.toggle('fa-solid');
        }
    </script>
</body>


</html>
