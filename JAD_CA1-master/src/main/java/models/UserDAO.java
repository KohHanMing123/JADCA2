package models;

import java.io.File;
import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dbaccess.DBConnection;

public class UserDAO {
	public static void registerUser(String username, String email, String password) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();

            String sqlStr = "INSERT INTO Customers (username, email, password) VALUES (?, ?, ?);";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, username);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            pstmt.executeUpdate();

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	public static String verifyUser(String username, String password, HttpSession session) {
        String custID = null;
        boolean found = false;
        
        Connection conn = null;
		try {
			conn = DBConnection.getConnection();

            String sqlStr = "SELECT * FROM Customers WHERE username=? AND password=?";
            PreparedStatement pstmt = conn.prepareStatement(sqlStr);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Query executed!");

            if (rs.next()) {
                found = true;
                custID = rs.getString("custID");
                System.out.println("custID is " + custID);
                
                // Retrieve the cart items from the database
                List<CartItem> cartItems = new ArrayList<>();
                try {
                    String cartQuery = "SELECT * FROM Cart WHERE custID=?";
                    PreparedStatement cartStmt = conn.prepareStatement(cartQuery);
                    cartStmt.setString(1, custID);
                    ResultSet cartRs = cartStmt.executeQuery();

                    while (cartRs.next()) {
                        int bookID = cartRs.getInt("bookID");
                        BookDAO query = new BookDAO();
                        Book book = query.getBook(bookID);
                        int quantity = cartRs.getInt("quantity");
                        cartItems.add(new CartItem(book, quantity));
                    }

                    session.setAttribute("cart", cartItems);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (found) {
            return custID;
        } else {
            return null;
        }
    }

	
	public User getUserInfo(String inputID) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();

	        String sqlStr = "SELECT * FROM Customers WHERE custID = ?";
	        PreparedStatement ps = conn.prepareStatement(sqlStr);
	        ps.setString(1, inputID);

	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            String imageBlob = rs.getString("imagePath");
	            return new User(username, email, password, imageBlob);
	        }
	        
	        throw new Exception("User not found with inputID: " + inputID);

	    } catch (Exception e) {
	        System.err.println("Error: " + e);
	        throw new Exception("Error");
	    }
	}

	public String insertImage(int custID, Part imageFile) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
	        PreparedStatement ps = null;
	        String imagePath = Image.saveImage(imageFile);
	        try {
	            String sqlStr = "UPDATE Customers SET imagePath = ? WHERE custID = ?";
	            ps = conn.prepareStatement(sqlStr);
	            ps.setString(1, imagePath);
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

	public void updateUserInfo(int custID, String username, String email, String password) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();

            StringBuilder sqlBuilder = new StringBuilder("UPDATE Customers SET");

            // Check if the username field is provided
            if (username != null && !username.isEmpty()) {
                sqlBuilder.append(" username = ?,");
            }

            // Check if the email field is provided
            if (email != null && !email.isEmpty()) {
                sqlBuilder.append(" email = ?,");
            }

            // Check if the password field is provided
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
            e.printStackTrace();
        }
    }
	
	public static void deleteUser(String username) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();

	        String sqlStr = "DELETE FROM Customers WHERE username=?";
	        PreparedStatement pstmt = conn.prepareStatement(sqlStr);
	        pstmt.setString(1, username);
	        pstmt.executeUpdate();
	        System.out.println("User deleted!");

	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
}
