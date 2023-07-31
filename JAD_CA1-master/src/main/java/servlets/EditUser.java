package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import models.*;
/**
 * Servlet implementation class EditUser
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
@WebServlet("/EditUser")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part imageFile = null;
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userIDStr = request.getParameter("userID");
		int userID = 0;
		try {
			userID = Integer.parseInt(userIDStr);
		}catch(Exception e) {
			response.sendRedirect("http://localhost:8080/JAD_CA2-master/admin/user.jsp?id=" + userIDStr + "&msg=error");
		}
		try {
			imageFile = request.getPart("imageFile");
		}catch(Exception e) {
		}
		AdminDAO query = new AdminDAO();
		
		if(request.getParameter("deleteUser") != null) {
			try {
				query.deleteCustomer(userID);
				response.sendRedirect("http://localhost:8080/JAD_CA2-master/admin/users.jsp");
			}catch(Exception e) {
				response.sendRedirect("http://localhost:8080/JAD_CA2-master/admin/user.jsp?id=" + userIDStr + "&msg=error");
				return;
			}
		}else {
			
			
			try {
				query.updateCustomer(userID, username, email, password, imageFile);
				response.sendRedirect("http://localhost:8080/JAD_CA2-master/admin/user.jsp?id=" + userIDStr + "&msg=success");
			}catch(Exception e) {
				System.err.println(e);
				if(e.getLocalizedMessage().contains("username_UNIQUE")) {
					response.sendRedirect("http://localhost:8080/JAD_CA2-master/admin/user.jsp?id=" + userIDStr + "&msg=userexist");
					return;
				}else if(e.getLocalizedMessage().contains("email_UNIQUE")) {
					response.sendRedirect("http://localhost:8080/JAD_CA2-master/admin/user.jsp?id=" + userIDStr + "&msg=emailexist");
					return;
				}
				response.sendRedirect("http://localhost:8080/JAD_CA2-master/admin/user.jsp?id=" + userIDStr + "&msg=error");
			}
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
