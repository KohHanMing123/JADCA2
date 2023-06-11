package admin;
import java.sql.*;
import java.util.*;

public class SQLqueryAdmin {
	private String username = System.getenv("PLANETSCALE_USERNAME");
	private String password = System.getenv("PLANETSCALE_KEY");
	
	public ArrayList<AdminUser> getAllUsers(int offset, int limit) throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT c.custID, c.username, c.email FROM Customers AS c";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<AdminUser> users = new ArrayList<AdminUser>();
		    while(rs.next()) {
		    	int id = rs.getInt("custID");
		    	String username = rs.getString("username");
		    	String email = rs.getString("email");
		    	users.add(new AdminUser(username, email, id));
		    }
		    return users;
		}catch(Exception e) {
			throw e;
		}
	}
}

//Customers AS c LEFT JOIN CustomerAddress AS ca ON c.CustID = ca.customerID LEFT JOIN Address AS a ON ca.addressID = a.addressID
