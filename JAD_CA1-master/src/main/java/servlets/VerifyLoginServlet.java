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
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		String dbUser = System.getenv("PLANETSCALE_USERNAME");
		String dbKey = System.getenv("PLANETSCALE_KEY");
	
		String userRole = "adminUser";
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(user + password);
		boolean found = false;
		// start database connection 
				
		 		try {
		 			System.out.println("inside inside try catch!");
			 		// Step1: Load JDBC Driver
		 			Class.forName("com.mysql.cj.jdbc.Driver");
			 		System.out.println("past driver");
			 		// Step 2: Define Connection URL
			 		String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser + "&password=" + dbKey + "&serverTimezone=UTC";
			 		System.out.println("pass connURL");
			 		// Step 3: Establish connection to URL
			 		Connection conn = DriverManager.getConnection(connURL);
			 		System.out.println("Connected to Database!");
					 // Step 4: Create Statement object
			 		// Statement stmt = conn.createStatement();
					 
			 		// Step 5: Execute SQL Command
			 		String sqlStr = "SELECT * FROM Customers WHERE username=? AND password=?";
			 		PreparedStatement pstmt = conn.prepareStatement(sqlStr);
			 		pstmt.setString(1, user);
			 		pstmt.setString(2, password);
			 		// ResultSet rs = stmt.executeQuery(sqlStr);
			 		ResultSet rs = pstmt.executeQuery();
			 		System.out.println("Query executed!");
			 		// Step 6: Process Result
			 		while (rs.next()) {
			 			found = true;
			 		}
			 		// Step 7: Close connection
			 		conn.close();
		 		} catch (Exception e) {
		 			out.println("Error: " + e);
		 		}
				// end database connection
				System.out.println(found + "found value");
				if (found) {
					System.out.println("user is" + user);
					session.setAttribute("sessUserID", user);
					session.setAttribute("sessUserRole", userRole);
					session.setAttribute("loginStatus", "success");
					session.setMaxInactiveInterval(3 * 60);		// 3 minutes
					//out.print(request.getContextPath() + "/src");
					response.sendRedirect(request.getContextPath() + "/Pages/LoginTest.jsp");
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
