package cart;

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
        PrintWriter out = response.getWriter();

        String dbUser = System.getenv("PLANETSCALE_USERNAME");
        String dbKey = System.getenv("PLANETSCALE_KEY");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser + "&password=" + dbKey + "&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(connURL);

            String bookID = request.getParameter("bookID");
            String sqlStr = "DELETE FROM Cart WHERE bookID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, bookID);
            pstmt.executeUpdate();

            pstmt.close();
            conn.close();

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

        } catch (Exception e) {
            out.println("Error: " + e);
        }

        response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp");
        System.out.println("Book deleted from cart!");
    }

}
