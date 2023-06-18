package models;

import java.sql.*;
import java.util.*;

public class SQLqueryUser {
	private String username = System.getenv("PLANETSCALE_USERNAME");
	private String password = System.getenv("PLANETSCALE_KEY");
	
	public User getUserInfo(String inputID) throws Exception {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
	        Connection conn = DriverManager.getConnection(connURL);

	        String sqlStr = "SELECT * FROM Customers WHERE custID = ?";
	        PreparedStatement ps = conn.prepareStatement(sqlStr);
	        ps.setString(1, inputID);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            String imageBlob = rs.getString("custImageURL");
	            return new User(username, email, password, imageBlob);
	        }
	        
	        throw new Exception("User not found with inputID: " + inputID);

	    } catch (Exception e) {
	        System.err.println("Error: " + e);
	        throw new Exception("Error");
	    }
	}

	public String insertImage(int custID, byte[] imageBlob) {
	    try {
	        Class.forName("com.mysql.jdbc.Driver");
	        String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
	        Connection conn = null;
	        PreparedStatement ps = null;
	        try {
	            conn = DriverManager.getConnection(connURL);
	            String sqlStr = "UPDATE Customers SET custImageURL = ? WHERE custID = ?";
	            ps = conn.prepareStatement(sqlStr);
	            ps.setBytes(1, imageBlob);
	            ps.setInt(2, custID);
	            int rowsAffected = ps.executeUpdate();
	            if (rowsAffected == 1) {
	                return "Inserted";
	            }
	            return "Not Inserted";
	        } finally {
	            if (ps != null) {
	                ps.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        }
	    } catch (Exception e) {
	        System.err.println("Error: " + e);
	        return "Error";
	    }
	}


}
