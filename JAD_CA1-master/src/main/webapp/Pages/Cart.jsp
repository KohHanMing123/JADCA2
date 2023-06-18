<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="models.SQLqueryBook, models.Book, models.CartItem, models.SQLqueryCart" %>
<%
	
	//Checks if they are logged in
	if (session.getAttribute("custID") == null) {
	    response.sendRedirect("http://localhost:8080/JAD_CA1-master/CheckProfileExistence");
	    return;
	}	
	
    String bookIDString = request.getParameter("book");
    int bookID = 0; 
    if (bookIDString != null) {
        bookID = Integer.parseInt(bookIDString);
    }
    	
    System.out.println("book ID in Cart isss " + bookID);
    
    SQLqueryBook query = new SQLqueryBook();
    Book book = null;
    try{
    	book = query.getBook(bookID);
    }catch(Exception e){
    	System.out.println(book);
    	
    }
    System.out.println("Book object: " + book);
    
   
    String custID = (String) session.getAttribute("custID");
    System.out.println("custOMER in CART.JSP id is " + custID);
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
                System.out.println("book price is " + book.getPrice());
                queryCart.updateTotalPrice(book.getID(), newQuantity, book.getPrice());
                
                break;
            }
        }
        
        if (!bookExists) {
            cart.add(new CartItem(book, 1));
            SQLqueryCart queryCart = new SQLqueryCart();
            queryCart.updateTotalPrice(book.getID(), 1, book.getPrice());
        }
        
        response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp");
        return;
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
        <table class="w-full bg-amber-50">
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
            	<% double totalPrice = 0;    	
                 for (CartItem cartItem : cart) { %>
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
                    <%
                    	
					    int cartBookID = cartItem.getBook().getID();
                    	int quantity = new SQLqueryCart().getCartItemQuantity(cartBookID);
                    	double total = new SQLqueryCart().getCartItemTotalPrice(cartBookID);
					    cartItem.setQuantity(quantity);

					    totalPrice += total;
					    System.out.println("what is this Cart tPrice" + totalPrice);
					%>	
                    <td class="py-4 px-6 border-b border-gray-200">
                        <div class="flex items-center justify-center">
                            <form action="<%= request.getContextPath() %>/PlusMinusButton" method="post">
							    <input type="hidden" name="action" value="minus">
							    <input type="hidden" name="bookID" value="<%= cartItem.getBook().getID()%>">
							    <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() %>">
							    <button class="rounded-l-lg bg-gray-200 text-gray-700 hover:bg-gray-300 px-2 py-1 minus-btn">
							        <i class="fas fa-minus"></i>
							    </button>
							</form>
							<%System.out.println("quantity is " + cartItem.getQuantity()); %>
							<span class="w-10 py-1 bg-gray-200 text-center text-gray-700 font-semibold quantity">							
								<%= quantity %> 							
							</span>
							
							<form action="<%= request.getContextPath() %>/PlusMinusButton" method="post">
							    <input type="hidden" name="action" value="plus">
							    <input type="hidden" name="bookID" value="<%= cartItem.getBook().getID() %>">
							    <input type="hidden" name="quantity" value="<%= cartItem.getQuantity() %>">
							    <button class="rounded-r-lg bg-gray-200 text-gray-700 hover:bg-gray-300 px-2 py-1 plus-btn">
							        <i class="fas fa-plus"></i>
							    </button>
							</form>
                        </div>
                    </td>
                    <td class="py-4 px-6 border-b border-gray-200 text-center">
                        <span class="total-price"><%= total%></span>
                    </td>
                    <td class="py-4 px-6 border-b border-gray-200 text-center">
					    <form action="<%= request.getContextPath() %>/deleteFromCart" method="post">
					        <input type="hidden" name="bookID" value="<%= cartItem.getBook().getID() %>">
					        <button class="rounded-lg bg-gray-200 text-gray-700 hover:bg-gray-300 px-2 py-1 action-btn" type="submit">
					            <i class="fas fa-trash"></i>
					        </button>
					    </form>
					</td>
                </tr>
                <% } %>
            </tbody>
            
		 <tfoot>
		    <tr>
		        <td class="py-4 px-6 border-t border-gray-200 font-bold" colspan="3">Total Price</td>
		        <td class="py-4 px-6 border-t border-gray-200 text-center">
		            <span class="total-price"><%= String.format("%.2f", totalPrice) %></span>
		        </td>
		        <td class="py-4 px-6 border-t border-gray-200 text-center">
		            <a href="#" class="rounded-lg bg-dark-blue text-white hover:bg-blue-700 px-4 py-2 checkout-btn">Checkout</a>
		        </td>
		    </tr>
		    <tr>
		        <td colspan="5" class="py-2 px-6 text-right">
		            <span class="text-gray-500">Total: </span>
		            <span class="text-gray-700 font-bold"><%= String.format("%.2f", totalPrice) %></span>
		        </td>
		    </tr>
		</tfoot>
        </table>
        <% } %>
    </div>

</body>
</html>
