package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List; // Add this import
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.*;
/**
 * Servlet implementation class deleteFromCart
 */
@WebServlet("/deleteFromCart")
public class deleteFromCart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public deleteFromCart() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookID = request.getParameter("bookID");

        CartDAO.deleteCartItem(bookID);

        // Remove the cart item from the session as well
        List<CartItem> cart = (List<CartItem>) request.getSession().getAttribute("cart");
        if (cart != null) {
            int deleteBookID = Integer.parseInt(bookID);
            CartItem itemToRemove = null;
            for (CartItem item : cart) {
                if (item.getBook().getID() == deleteBookID) {
                    itemToRemove = item;
                    break;
                }
            }
            if (itemToRemove != null) {
                cart.remove(itemToRemove);
            }
        }

        response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp");
        System.out.println("Book deleted from cart!");
    }


}
