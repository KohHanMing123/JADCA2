package servlets;

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

import models.SQLqueryCart;


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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        System.out.println("inside new addToCart serv");

        // Checks if they are logged in
        if (session.getAttribute("custID") == null) {
            response.sendRedirect("http://localhost:8080/JAD_CA1-master/CheckProfileExistence");
            return;
        }

        int custID = Integer.parseInt((String) session.getAttribute("custID"));  
        System.out.println("custID in addToCart is " + custID);
        String bookIDString = request.getParameter("book");
        int bookID = Integer.parseInt(bookIDString);
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

        System.out.println("add to cart servlet quantity is " + quantity);
        System.out.println("add to cart servlet totalPrice is " + totalPrice);
        System.out.println("addToCart servlet book id " + bookID);
        System.out.println("addToCart servlet cust id " + custID);
        
        SQLqueryCart.addToCart(custID, bookID, unitPrice, totalPrice, quantity);

        response.sendRedirect(request.getContextPath() + "/Pages/Cart.jsp?book=" + bookID);
        System.out.println("Item added to cart");
    }

}




