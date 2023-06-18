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
import models.*;

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
        HttpSession session = request.getSession();

        String userRole = "adminUser";
        String user = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(user + password);
        String custID = SQLqueryUser.verifyUser(user, password, session);

        if (custID != null) {
            // The user was found
            session.setAttribute("username", user);
            session.setAttribute("sessUserRole", userRole);
            session.setAttribute("loginStatus", "success");
            session.setAttribute("custID", custID);
            session.setMaxInactiveInterval(3 * 60); // 3 minutes
            response.sendRedirect(request.getContextPath() + "/Pages/home.jsp");
        } else {
            // The user was not found
            response.sendRedirect(request.getContextPath() + "/Pages/Login.jsp?errCode=invalidLogin");
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
