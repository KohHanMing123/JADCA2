package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import cart.*;
import books.*;

/**
 * Servlet implementation class VerifyLoginServlet
 */
@WebServlet("/VerifyLoginServlet")
public class VerifyLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifyLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        String dbUser = System.getenv("PLANETSCALE_USERNAME");
        String dbKey = System.getenv("PLANETSCALE_KEY");

        String userRole = "adminUser";
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(user + password);
        boolean found = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser + "&password=" + dbKey + "&serverTimezone=UTC";

            Connection conn = DriverManager.getConnection(connURL);

            String sqlStr = "SELECT * FROM Customers WHERE username=? AND password=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, user);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Query executed!");

            while (rs.next()) {
                found = true;
                String custID = rs.getString("custID");
                System.out.println("custID is " + custID);
                session.setAttribute("custID", custID);
                
                //retrieve the cart items from db when logged in
                List<CartItem> cartItems = new ArrayList<>();
                try {
                    String cartQuery = "SELECT * FROM Cart WHERE custID=?";
                    PreparedStatement cartStmt = conn.prepareStatement(cartQuery);
                    cartStmt.setString(1, custID);
                    ResultSet cartRs = cartStmt.executeQuery();

                    while (cartRs.next()) {
                        int bookID = cartRs.getInt("bookID");
                        SQLqueryBook query = new SQLqueryBook();
                        Book book = query.getBook(bookID); 
                        int quantity = cartRs.getInt("quantity");
                        cartItems.add(new CartItem(book, quantity));
                    }

                    session.setAttribute("cart", cartItems);
                } catch (Exception e) {
                    out.println("Error: " + e);
                }
            }

            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e);
        }

        System.out.println(found + "found value");
        if (found) {
            session.setAttribute("username", user);
            session.setAttribute("sessUserRole", userRole);
            session.setAttribute("loginStatus", "success");
            session.setMaxInactiveInterval(3 * 60); // 3 minutes
            response.sendRedirect(request.getContextPath() + "/Pages/BookGenre.jsp");
        } else {
            response.sendRedirect(request.getContextPath() + "/Pages/LoginFailed.jsp?errCode=invalidLogin");
        }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
