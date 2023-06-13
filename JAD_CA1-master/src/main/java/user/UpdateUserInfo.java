package user;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UpdateUserInfo
 */
@WebServlet("/UpdateUserInfo")
public class UpdateUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();

        // Checks if they are logged in
        if (session.getAttribute("custID") == null) {
            response.sendRedirect("http://localhost:8080/JAD_CA1-master/CheckProfileExistence");
            return;
        }

        String dbUser = System.getenv("PLANETSCALE_USERNAME");
        String dbKey = System.getenv("PLANETSCALE_KEY");

        int custID = Integer.parseInt((String) session.getAttribute("custID"));

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser + "&password=" + dbKey + "&serverTimezone=UTC";
            Connection conn = DriverManager.getConnection(connURL);

            StringBuilder sqlBuilder = new StringBuilder("UPDATE Customers SET");

            // Check if the username field is provided
            String username = request.getParameter("username");
            if (username != null && !username.isEmpty()) {
                sqlBuilder.append(" username = ?,");
            }

            // Check if the email field is provided
            String email = request.getParameter("email");
            if (email != null && !email.isEmpty()) {
                sqlBuilder.append(" email = ?,");
            }

            // Check if the password field is provided
            String password = request.getParameter("password");
            if (password != null && !password.isEmpty()) {
                sqlBuilder.append(" password = ?,");
            }

            // Remove the trailing comma from the SQL query
            sqlBuilder.deleteCharAt(sqlBuilder.length() - 1);

            sqlBuilder.append(" WHERE custID = ?");
            PreparedStatement pstmt = conn.prepareStatement(sqlBuilder.toString());

            int parameterIndex = 1;

            if (username != null && !username.isEmpty()) {
                pstmt.setString(parameterIndex, username);
                parameterIndex++;
            }

            if (email != null && !email.isEmpty()) {
                pstmt.setString(parameterIndex, email);
                parameterIndex++;
            }

            if (password != null && !password.isEmpty()) {
                pstmt.setString(parameterIndex, password);
                parameterIndex++;
            }
            
            pstmt.setInt(parameterIndex, custID);

            pstmt.executeUpdate();
            conn.close();
        } catch (Exception e) {
            out.println("Error: " + e);
        }

        response.sendRedirect(request.getContextPath() + "/Pages/User.jsp");
        System.out.println("Customer info updated!");
    }



}
