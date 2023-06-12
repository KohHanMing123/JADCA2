<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="books.*" %>
<%@ page import = "cart.*" %>
<%
    String bookIDString = request.getParameter("book");
    int bookID = 0; 
    if (bookIDString != null) {
        bookID = Integer.parseInt(bookIDString);
    }
    
    SQLqueryBook query = new SQLqueryBook();
    Book book = query.getBook(bookID);
    System.out.println("Book object: " + book);
    // Get the current cart from the session
    List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
    System.out.println("Cart items: " + cart);
    if (cart == null) {
        cart = new ArrayList<CartItem>();
        session.setAttribute("cart", cart);
    }

    if (book != null) {
        // Check if the book already exists in the cart
        boolean bookExists = false;
        for (CartItem item : cart) {
            if (item.getBook().getID() == book.getID()) {
                int newQuantity = item.getQuantity() + 1;
                item.setQuantity(newQuantity);
                bookExists = true;
                
                SQLqueryCart queryCart = new SQLqueryCart();
                queryCart.updateCartItemQuantity(book.getID(), newQuantity);
                
                break;
            }
        }
        
        if (!bookExists) {
            // Add the book as a new cart item
            cart.add(new CartItem(book, 1));
        }
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
    <div class="container mx-auto my-20">
        <% if (cart.isEmpty()) { %>
        <p class="text-4xl text-center font-bold">Your cart is empty.</p>
        <% } else { %>
        <table class="w-full bg-white">
            <thead>
                <tr>
                    <th class="py-3 px-6 text-left">Book</th>
                    <th class="py-3 px-6 text-left">Unit Price</th>
                    <th class="py-3 px-6 text-center">Quantity</th>
                    <th class="py-3 px-6 text-center">Total Price</th>
                    <th class="py-3 px-6 text-center">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (CartItem cartItem : cart) { %>
                <tr>
                    <td class="py-4 px-6 border-b border-gray-200">
                        <div class="flex items-center">
                            <div class="h-24 w-16 mr-4">
                                <img class="h-full object-contain hover:cursor-pointer hover:scale-105 duration-300" 
                                     src="data:image/jpeg;base64, <%=cartItem.getBook().getImage()%>"
                                     onclick="redirectToBook('<%=cartItem.getBook().getID()%>')">
                            </div>
                            <div>
                                <p class="text-base font-bold"><%= cartItem.getBook().getTitle() %></p>
                                <p class="text-xs font-semibold"><%= cartItem.getBook().getAuthor() %></p>
                            </div>
                        </div>
                    </td>
                    <td class="py-4 px-6 border-b border-gray-200"><%= cartItem.getBook().getPrice() %></td>
                    <td class="py-4 px-6 border-b border-gray-200">
                        <div class="flex items-center justify-center">
                            <button class="rounded-l-lg 	bg-gray-200 text-gray-700 hover:bg-gray-300 px-2 py-1 minus-btn" onclick="minusButton(this)">
                                <i class="fas fa-minus"></i>
                            </button>
                            <span class="w-10 py-1 bg-gray-200 text-center text-gray-700 font-semibold quantity"><%= cartItem.getQuantity() %></span>
                            <button class="rounded-r-lg bg-gray-200 text-gray-700 hover:bg-gray-300 px-2 py-1 plus-btn" onclick="plusButton(this)">
                                <i class="fas fa-plus"></i>
                            </button>
                        </div>
                    </td>
                    <td class="py-4 px-6 border-b border-gray-200 text-center">
                        <span class="total-price"><%= (cartItem.getBook().getPrice() * cartItem.getQuantity()) %></span>
                    </td>
                    <td class="py-4 px-6 border-b border-gray-200 text-center">
                        <button class="rounded-lg bg-gray-200 text-gray-700 hover:bg-gray-300 px-2 py-1 action-btn" onclick="deleteButton(this)">
                            <i class="fas fa-trash"></i>
                        </button>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
        <% } %>
    </div>

    <script>
        function plusButton(button) {
            const quantityElement = button.parentNode.querySelector('.quantity');
            let quantity = parseInt(quantityElement.textContent);
            quantity++;
            quantityElement.textContent = quantity.toString();
            
            updateTotalPrice(button);
        }

        function minusButton(button) {
            const quantityElement = button.parentNode.querySelector('.quantity');
            let quantity = parseInt(quantityElement.textContent);
            if (quantity > 1) {
                quantity--;
                quantityElement.textContent = quantity.toString();
                
                updateTotalPrice(button);
            }
        }
        
        function updateTotalPrice(button) {
            const quantityElement = button.parentNode.querySelector('.quantity');
            const unitPriceElement = button.parentNode.parentNode.previousElementSibling;
            const totalPriceElement = button.parentNode.parentNode.nextElementSibling.querySelector('.total-price');

            let quantity = parseInt(quantityElement.textContent);
            let unitPrice = parseFloat(unitPriceElement.textContent);

            let totalPrice = quantity * unitPrice;
            totalPriceElement.textContent = totalPrice.toFixed(2);
        }
        
        function deleteButton(button) {
            const row = button.parentNode.parentNode;
            row.parentNode.removeChild(row);
            
            updateTotalPrice(button);
        }
        
        function redirectToBook(bookID) {
            window.location.href = 'Book.jsp?book=' + bookID;
        }
    </script>
</body>
</html>
