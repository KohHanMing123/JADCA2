package models;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import dbaccess.DBConnection;


public class OrderDAO {
	
	public static void saveOrder(String custID, List<CartItem> cartItems, String totalAmt) throws SQLException {
		Connection conn = null;
		ResultSet generatedKeys = null;
		try {
			conn = DBConnection.getConnection();

			// Insert order into Orders table
            String sqlStr = "INSERT INTO Orders (custID, totalAmt) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, custID);
            pstmt.setString(2, totalAmt);
            
            pstmt.executeUpdate();
            
            // Get the generated order_id from the insert operation
            generatedKeys = pstmt.getGeneratedKeys();
            int orderID = 0;
            if (generatedKeys.next()) {
                orderID = generatedKeys.getInt(1);
            }
      
         // Insert each book from the cart into the Order_book table
            String orderBookSql = "INSERT INTO Order_books (orderID, bookID, quantity) VALUES (?, ?, ?)";
            pstmt = conn.prepareStatement(orderBookSql);
            for (CartItem cartItem : cartItems) {
                pstmt.setInt(1, orderID);
                pstmt.setInt(2, cartItem.getBook().getID());
                pstmt.setInt(3, cartItem.getQuantity());
                pstmt.executeUpdate();
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }	
        // You need to establish a database connection and insert the order details and book details into appropriate tables.
        // For each book in the cartItems list, you can save its details (book ID, title, price, quantity) along with the order ID and customer ID.
        // Save the totalAmount as the total price for the order.
    }

