package cart;

import java.sql.*;
import java.util.*;

public class SQLqueryCart {
	private String username = System.getenv("PLANETSCALE_USERNAME");
	private String password = System.getenv("PLANETSCALE_KEY");
	
	  public void updateCartItemQuantity(int bookID, int quantity) {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
	            Connection conn = DriverManager.getConnection(connURL);
	            String sqlStr = "UPDATE Cart SET quantity = ? WHERE bookID = ?";
	            PreparedStatement ps=conn.prepareStatement(sqlStr);
	            ps.setInt(1, quantity);
	            ps.setInt(2, bookID);
	            ps.executeUpdate();
	            
	            ps.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}