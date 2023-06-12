//package cart;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
///**
// * Servlet implementation class deleteFromCart
// */
//@WebServlet("/deleteFromCart")
//public class deleteFromCart extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public deleteFromCart() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		PrintWriter out = response.getWriter();
//        HttpSession session = request.getSession();
//        
//        String dbUser = System.getenv("PLANETSCALE_USERNAME");
//		String dbKey = System.getenv("PLANETSCALE_KEY");
//		
//		try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + dbUser + "&password=" + dbKey + "&serverTimezone=UTC";
//            Connection conn = DriverManager.getConnection(connURL);
//
//            String sqlStr = "DELETE FROM Cart";
//            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
//            pstmt.setString(1, username);
//            pstmt.setString(2, email);
//            pstmt.setString(3, password);
//            pstmt.executeUpdate();
//
//            conn.close();
//        } catch (Exception e) {
//            out.println("Error: " + e);
//        }
//
//        session.setAttribute("passwordMatch", true);
//        response.sendRedirect(request.getContextPath() + "/Pages/Login.jsp");
//        System.out.println("customer registered!");
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
//
//}
