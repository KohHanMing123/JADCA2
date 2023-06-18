package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.SQLqueryUser;

/**
 * Servlet implementation class RegisterCustomerServlet
 */
@WebServlet("/RegisterCustomerServlet")
public class RegisterCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterCustomerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        System.out.println("inside new register serv");
        if (!password.equals(confirmPassword)) {
            session.setAttribute("passwordMatch", false);
            response.sendRedirect(request.getContextPath() + "/Pages/Register.jsp");
            System.out.println("passwords are not the same");
            return;
        }

        SQLqueryUser.registerUser(username, email, password);

        session.setAttribute("passwordMatch", true);
        response.sendRedirect(request.getContextPath() + "/Pages/Login.jsp");
        System.out.println("customer registered!");
    }
}


