package admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class CreateUser
 */
@MultipartConfig(
	    fileSizeThreshold = 1024 * 1024, // 1MB
	    maxFileSize = 1024 * 1024 * 10, // 10MB
	    maxRequestSize = 1024 * 1024 * 50 // 50MB
	)
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		Part imageFile = request.getPart("imageFile");
		
		SQLqueryAdmin query = new SQLqueryAdmin();
		try {
			query.createUser(username, password, email, imageFile);
			response.sendRedirect("admin/newUser.jsp?msg=success");
		}catch(Exception e) {
			if(e.getLocalizedMessage().contains("username_UNIQUE")) {
				response.sendRedirect("http://localhost:8080/JAD_CA1-master/admin/newUser.jsp?msg=userexist");
				return;
			}else if(e.getLocalizedMessage().contains("email_UNIQUE")) {
				response.sendRedirect("http://localhost:8080/JAD_CA1-master/admin/newUser.jsp?msg=emailexist");
				return;
			}
			response.sendRedirect("http://localhost:8080/JAD_CA1-master/admin/newUser.jsp?msg=error");
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
