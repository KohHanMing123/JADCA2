package servlets;


import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import payment.PaymentServices;
import com.paypal.api.payments.*;
import com.paypal.base.rest.PayPalRESTException;
import models.CartItem;
import models.OrderDAO;

import java.util.List;
/**
 * Servlet implementation class ExecutePayment
 */
@WebServlet("/ExecutePayment")
public class ExecutePayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExecutePayment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String paymentId = request.getParameter("paymentId");
        String payerId = request.getParameter("PayerID");
        String custID = (String) session.getAttribute("custID");
        List<CartItem> cartItems = (List<CartItem>) session.getAttribute("cart");
        String totalAmount = request.getParameter("total");
        
        try {
        	System.out.println("this is my total Amount in executepayment servlet " + totalAmount);
        	System.out.println("this is my cart items in executepayment servlet " + cartItems);
        	System.out.println("this is my cust ID in executepayment servlet " + custID);
            PaymentServices paymentServices = new PaymentServices();
            Payment payment = paymentServices.executePayment(paymentId, payerId);
             
            PayerInfo payerInfo = payment.getPayer().getPayerInfo();
            Transaction transaction = payment.getTransactions().get(0);
             
            request.setAttribute("payer", payerInfo);
            request.setAttribute("transaction", transaction);          
            OrderDAO.saveOrder(custID, cartItems, totalAmount);
            
            // Clear the cart after the order is successfully saved
            session.removeAttribute("cart");
            // Redirect the user to a confirmation page or display a success message
            // You can pass the orderId to the confirmation page if needed
//            response.sendRedirect(request.getContextPath() + "/orderConfirmation.jsp?orderId=" + orderId);
            request.getRequestDispatcher("/Pages/PaymentReceipt.jsp").forward(request, response);
             
        } catch (PayPalRESTException ex) {
            request.setAttribute("errorMessage", ex.getMessage());
            ex.printStackTrace();
            request.getRequestDispatcher("/Pages/PaymentError.jsp").forward(request, response);
        } catch (SQLException e) {
			e.printStackTrace();
		}
    }
}


