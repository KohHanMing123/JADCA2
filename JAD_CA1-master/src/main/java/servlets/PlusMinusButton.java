package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.*;
/**
 * Servlet implementation class PlusMinusButton
 */
@WebServlet("/PlusMinusButton")
public class PlusMinusButton extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlusMinusButton() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        BookDAO queryBook = new BookDAO();
        Book book = null;
		try {
			book = queryBook.getBook(bookID);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        if (book == null) {
            // to handle the case where the book is not found
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        double unitPrice = book.getPrice();
        CartDAO queryCart = new CartDAO();

        if ("plus".equals(action)) {
            queryCart.updateCartPlusButton(bookID, quantity + 1);
            quantity += 1;
        } else if ("minus".equals(action)) {
            if (quantity > 1) {
                queryCart.updateCartMinusButton(bookID, quantity - 1);
                quantity -= 1;
            } else {
                // Call the deleteFromCart servlet to handle the deletion
                request.setAttribute("bookID", String.valueOf(bookID));
                request.getRequestDispatcher("/deleteFromCart").forward(request, response);
                return; // Return to avoid updating the total price
            }
        }

        queryCart.updateTotalPrice(bookID, quantity, unitPrice);

        response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp");
    }
	
}




