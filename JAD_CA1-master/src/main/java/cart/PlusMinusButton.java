package cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

        SQLqueryCart queryCart = new SQLqueryCart();

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

        queryCart.updateTotalPrice(bookID, quantity);

        response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp");
    }

	
}




