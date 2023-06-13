package user;

import java.sql.*;
import java.util.*;
import user.*;
public class SQLqueryUser {
	private String username = System.getenv("PLANETSCALE_USERNAME");
	private String password = System.getenv("PLANETSCALE_KEY");
	
	public User getUserInfo(String inputID) throws Exception {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
	        Connection conn = DriverManager.getConnection(connURL);

	        String sqlStr = "SELECT username, email, password FROM Customers WHERE custID = ?";
	        PreparedStatement ps = conn.prepareStatement(sqlStr);
	        ps.setString(1, inputID);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            return new User(username, email, password);
	        }
	        
	        throw new Exception("User not found with inputID: " + inputID);

	    } catch (Exception e) {
	        System.err.println("Error: " + e);
	        throw new Exception("Error");
	    }
	}



}
