package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.*;
/**
 * Servlet implementation class AdminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        
		String username = request.getParameter("username");
		String password = request.getParameter("password");	
		AdminDAO query = new AdminDAO();
		int adminID = 0;
		try {
			adminID = query.adminLogin(password, username);
			session.setAttribute("adminID", adminID);
			session.setAttribute("username", username);
			session.setMaxInactiveInterval(10 * 60); 
			response.sendRedirect("admin/books.jsp");
		}catch(Exception e) {
			System.out.print(e.getLocalizedMessage());
			if(e.getLocalizedMessage().contains("Invalid")) {
				response.sendRedirect("admin/login.jsp?msg=invalid");
				return;
			}
			response.sendRedirect("admin/login.jsp?msg=error");
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
