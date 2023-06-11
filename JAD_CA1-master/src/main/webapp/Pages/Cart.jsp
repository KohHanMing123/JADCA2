<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="books.*" %>
<%
    String bookIDString = request.getParameter("book");
    int bookID = 0; 
    if (bookIDString != null) {
        bookID = Integer.parseInt(bookIDString);
    }
    
    SQLqueryBook query = new SQLqueryBook();
    Book book = query.getBook(bookID);

    // Get the current cart from the session
    List<Book> cart = (List<Book>) session.getAttribute("cart");
    if (cart == null) {
        cart = new ArrayList<Book>();
        session.setAttribute("cart", cart);
    }

    // Add the book to the cart
    if (book != null) {
        cart.add(book);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Cart</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="../js/twCustom.js"></script>
    <script src="https://kit.fontawesome.com/61e63c790b.js" crossorigin="anonymous"></script>
</head>
<body class="bg-sand">
    <%@ include file="../components/navBar.html" %>
    <div class="h-max mx-64 my-20">
        <% if (cart.isEmpty()) { %>
        <p class="text-4xl text-center text-bold">Your cart is empty.</p>
        <% } else { %>
        <table class="min-w-full bg-white">
            <thead>
                <tr>
                    <th class="py-3 px-6 text-left">Title</th>
                    <th class="py-3 px-6 text-left">Author</th>
                    <th class="py-3 px-6 text-right">Price</th>
                </tr>
            </thead>
            <tbody>
                <% for (Book cartBook : cart) { %>
                <tr>
                    <td class="py-4 px-6 border-b border-gray-200"><%= cartBook.getTitle() %></td>
                    <td class="py-4 px-6 border-b border-gray-200"><%= cartBook.getAuthor() %></td>
                    <td class="py-4 px-6 border-b border-gray-200 text-right">$<%= cartBook.getPrice() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } %>
    </div>
</body>
</html>
