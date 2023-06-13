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
	  
	  public int getCartItemQuantity(int bookID) {
		    int quantity = 0;
		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		        String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
		        Connection conn = DriverManager.getConnection(connURL);
		        String sqlStr = "SELECT Quantity FROM Cart WHERE bookID = ?";
		        PreparedStatement ps = conn.prepareStatement(sqlStr);
		        ps.setInt(1, bookID);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            quantity = rs.getInt("Quantity");
		        }

		        rs.close();
		        ps.close();
		        conn.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return quantity;
		}
	  
	  public double getCartItemTotalPrice(int bookID) {
		    double total = 0;
		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		        String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
		        Connection conn = DriverManager.getConnection(connURL);
		        String sqlStr = "SELECT totalPrice FROM Cart WHERE bookID = ?";
		        PreparedStatement ps = conn.prepareStatement(sqlStr);
		        ps.setInt(1, bookID);
		        ResultSet rs = ps.executeQuery();

		        if (rs.next()) {
		            total = rs.getDouble("totalPrice");
		        }

		        rs.close();
		        ps.close();
		        conn.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		    return total;
		}

	  
	  public void updateTotalPrice(int bookID, int quantity) {
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
	            Connection conn = DriverManager.getConnection(connURL);
	            String sqlStr = "UPDATE Cart SET totalPrice = unitPrice * ? WHERE bookID = ?";
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
	  
	  public void updateCartPlusButton(int bookID, int quantity) {
		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		        String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
		        Connection conn = DriverManager.getConnection(connURL);
		        String sqlStr = "UPDATE Cart SET quantity = quantity + 1 WHERE bookID = ?";
		        PreparedStatement ps = conn.prepareStatement(sqlStr);
		        ps.setInt(1, bookID);
		        ps.executeUpdate();

		        ps.close();	
		        conn.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}

		public void updateCartMinusButton(int bookID, int quantity) {
		    try {
		        Class.forName("com.mysql.jdbc.Driver");
		        String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
		        Connection conn = DriverManager.getConnection(connURL);
		        String sqlStr = "UPDATE Cart SET quantity = quantity - 1 WHERE bookID = ?";
		        PreparedStatement ps = conn.prepareStatement(sqlStr);
		        ps.setInt(1, bookID);
		        ps.executeUpdate();
		         
		        ps.close();
		        conn.close();
		    } catch (Exception e) {
		        e.printStackTrace();
		    }
		}
	  
}