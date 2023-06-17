package cart;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class addToCart
 */
@WebServlet("/addToCart")
public class addToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // Checks if they are logged in
        if (session.getAttribute("custID") == null) {
            response.sendRedirect("http://localhost:8080/JAD_CA1-master/CheckProfileExistence");
            return;
        }

        String dbUser = System.getenv("PLANETSCALE_USERNAME");
        String dbKey = System.getenv("PLANETSCALE_KEY");

        String bookIDString = request.getParameter("book");
        int bookID = Integer.parseInt(bookIDString);
        int custID = Integer.parseInt((String) session.getAttribute("custID"));  
        System.out.println("custID in addToCart is " + custID);
        int quantity;
        String quantityString = request.getParameter("bookQuantity"); // this is to get from Book.jsp
        
        if (quantityString != null && !quantityString.isEmpty()) {
            quantity = Integer.parseInt(quantityString);
        } else {
            quantity = 1; // Default quantity - for both Booklist.jsp and Search.jsp
        }
        
        String unitPriceString = request.getParameter("unitPrice");
        double unitPrice = Double.parseDouble(unitPriceString);
        double totalPrice = unitPrice * quantity;

        System.out.println("add to cart serrvlet quantity is " + quantity);
        System.out.println("add to cart serrvlet tPrice is " + totalPrice);
        System.out.println("addToCart servlet book id " + bookID);
        System.out.println("addToCart servlet cust id " + custID);
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser + "&password=" + dbKey + "&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(connURL);
            System.out.println("inside try total PRice is " + totalPrice);
            String sqlStr = "INSERT INTO Cart (custID, bookID, unitPrice, totalPrice, quantity) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            
            pstmt.setInt(1, custID);
            pstmt.setInt(2, bookID);
            pstmt.setDouble(3, unitPrice);
            pstmt.setDouble(4, totalPrice);
            pstmt.setInt(5, quantity);
            System.out.println("SQL Query: " + pstmt.toString());
            pstmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e);
        }

        response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp?book=" + bookID);
        System.out.println("Item added to cart");
    }

}




