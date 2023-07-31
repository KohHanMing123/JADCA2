package models;

import java.sql.*;
import java.util.*;

import dbaccess.DBConnection;

public class CartDAO {
	
	public static void addToCart(int custID, int bookID, double unitPrice, double totalPrice, int quantity) {
        Connection conn = null;
		try {
			conn = DBConnection.getConnection();

            String sqlStr = "INSERT INTO Cart (custID, bookID, unitPrice, totalPrice, quantity) VALUES (?, ?, ?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);

            pstmt.setInt(1, custID);
            pstmt.setInt(2, bookID);
            pstmt.setDouble(3, unitPrice);
            pstmt.setDouble(4, totalPrice);
            pstmt.setInt(5, quantity);

            pstmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	  public void updateCartItemQuantity(int bookID, int quantity) {
		  Connection conn = null;
			try {
				conn = DBConnection.getConnection();
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
		    Connection conn = null;
			try {
				conn = DBConnection.getConnection();
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
		    Connection conn = null;
			try {
				conn = DBConnection.getConnection();
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

	  
	  public void updateTotalPrice(int bookID, int quantity, double unitPrice) {
		  Connection conn = null;
			try {
				conn = DBConnection.getConnection();
	            String sqlStr = "UPDATE Cart SET totalPrice = ? WHERE bookID = ?";
	            PreparedStatement ps = conn.prepareStatement(sqlStr);
	            ps.setDouble(1, unitPrice * quantity);
	            ps.setInt(2, bookID);
	            ps.executeUpdate();
	            ps.executeUpdate();
	           System.out.println("updateTotalPrice unitPrice is " + unitPrice);
	            ps.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	  
	  public void updateCartPlusButton(int bookID, int quantity) {
		  Connection conn = null;
			try {
				conn = DBConnection.getConnection();
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
			Connection conn = null;
			try {
				conn = DBConnection.getConnection();
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
		
		
		public static void deleteCartItem(String bookID) {
			Connection conn = null;
			try {
				conn = DBConnection.getConnection();

	            String sqlStr = "DELETE FROM Cart WHERE bookID = ?";
	            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	            pstmt.setString(1, bookID);
	            pstmt.executeUpdate();

	            pstmt.close();
	            conn.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	  
}