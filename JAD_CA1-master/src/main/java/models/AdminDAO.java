package models;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.*;
import java.util.*;

import javax.servlet.http.Part;


import dbaccess.*;

public class AdminDAO {
	
	public int adminLogin(String adminPassword, String adminUsername) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "SELECT * FROM Admin WHERE password = ? AND username = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, adminPassword);
		    ps.setString(2, adminUsername);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
		    	return rs.getInt("adminID");
		    }
		    throw new Exception("Invalid");
		}catch (Exception e) {
	        throw e;
	    }
	}
	
	public boolean verifyAdmin(int adminID, String adminUsername) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "SELECT * FROM Admin WHERE adminID = ? AND username = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, adminID);
		    ps.setString(2, adminUsername);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
		    	return true;
		    }
		    return false;
		}catch (Exception e) {
	        throw e;
	    }
	}
	
	public ArrayList<Book> getAllBooks() throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "SELECT * FROM Books ORDER BY dateAdded DESC";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<Book> books = new ArrayList<Book>();
		    while(rs.next()) {
		    	int id = rs.getInt("id");
		    	String title = rs.getString("title");
		    	String publicdate = rs.getString("publication_date");
		    	String author = rs.getString("author");
		    	String genre = rs.getString("genre");
		    	double price = rs.getDouble("price");
		    	String isbn = rs.getString("isbn13");
		    	String dateAdded = rs.getString("dateAdded");
		    	String description = rs.getString("description");
		    	int stock = rs.getInt("stock");
		    	String imagePath = rs.getString("imagePath");

		    	books.add(new Book(stock, id, title, author, publicdate, genre, isbn, dateAdded, price, description, imagePath));
		    }
		    return books;
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
		
	}
	
	public ArrayList<Book> searchBook(String searchInput) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String formattedSearch = "%" + searchInput + "%";
			
			String sqlStr = "SELECT * FROM Books WHERE title LIKE ? ORDER BY dateAdded DESC ";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, formattedSearch);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<Book> books = new ArrayList<Book>();
		    while(rs.next()) {
		    	int id = rs.getInt("id");
		    	String title = rs.getString("title");
		    	String publicdate = rs.getString("publication_date");
		    	String author = rs.getString("author");
		    	String genre = rs.getString("genre");
		    	double price = rs.getDouble("price");
		    	String isbn = rs.getString("isbn13");
		    	String dateAdded = rs.getString("dateAdded");
		    	String description = rs.getString("description");
		    	int stock = rs.getInt("stock");
		    	String imagePath = rs.getString("imagePath");
		    	
		    	books.add(new Book(stock, id, title, author, publicdate, genre, isbn, dateAdded, price, description, imagePath));
		    }
		    return books;
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
		
	}
	
	public ArrayList<User> getAllUsers() throws Exception{
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "SELECT * FROM Customers AS c";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<User> users = new ArrayList<User>();
		    while(rs.next()) {
		    	int custID = rs.getInt("custID");
		    	String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            String imageBlob = rs.getString("custImageURL");
	            
	            User user = new User(username, email, password, imageBlob);
	            user.setUserID(custID);
	            
		    	users.add(user);
		    }
		    return users;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public ArrayList<User> searchUser(String searchInput) throws Exception{
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String formattedSearch = "%" + searchInput + "%";
			String sqlStr = "SELECT * FROM Customers WHERE username LIKE ? OR email LIKE ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, formattedSearch);
		    ps.setString(2, formattedSearch);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<User> users = new ArrayList<User>();
		    while(rs.next()) {
		    	int custID = rs.getInt("custID");
		    	String username = rs.getString("username");
	            String email = rs.getString("email");
	            String password = rs.getString("password");
	            String imageBlob = rs.getString("custImageURL");
	            
	            User user = new User(username, email, password, imageBlob);
	            user.setUserID(custID);
	            
		    	users.add(user);
		    }
		    return users;
		}catch(Exception e) {
			throw e;
		}
	}
	
	public String createBook(Part imageFile, String title, String author, String date, String genre, String isbn, double price, int stock, String desc) {
		String imagePath = null;
		if(imageFile != null) {
			try {
				imagePath = Image.saveImage(imageFile);
			} catch (Exception e) {
				imagePath = null;
			}
		}
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "INSERT INTO Books (title, author, publication_date, genre, price, stock, isbn13, description, imagePath) VALUES (?,?,?,?,?,?,?,?,?)";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, title);
		    ps.setString(2, author);
		    ps.setString(3, date);
		    ps.setString(4, genre);
		    ps.setDouble(5, price);
		    ps.setInt(6, stock);
		    ps.setString(7, isbn);
		    ps.setString(8, desc);
		    ps.setString(9, imagePath);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows == 1) {
		    	return "Success";
		    }
		}catch(Exception e) {
			System.err.print(e);
		}
		
		
		return "Failed";
	}
	
	public String editBook(int id, Part imageFile, String title, String author, String date, String genre, String isbn, double price, int stock, String desc) {
		String imagePath = null;
		if(imageFile != null) {
			try {
				imagePath = Image.saveImage(imageFile);
			} catch (Exception e) {
				imagePath = null;
			}
		}
		
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "UPDATE Books SET title = ?, author = ?, publication_date = ?, genre = ?, price = ?, stock = ?, isbn13 = ?, description = ?, imagePath = ? WHERE id = ?";
			if(imagePath == null) {
				sqlStr = "UPDATE Books SET title = ?, author = ?, publication_date = ?, genre = ?, price = ?, stock = ?, isbn13 = ?, description = ? WHERE id = ?";
				PreparedStatement ps=conn.prepareStatement(sqlStr);
			    ps.setString(1, title);
			    ps.setString(2, author);
			    ps.setString(3, date);
			    ps.setString(4, genre);
			    ps.setDouble(5, price);
			    ps.setInt(6, stock);
			    ps.setString(7, isbn);
			    ps.setString(8, desc);
			    ps.setInt(9, id);
			    int affectedRows = ps.executeUpdate();
			    if(affectedRows == 1) {
			    	return "Success";
			    }
			}else {
				PreparedStatement ps=conn.prepareStatement(sqlStr);
			    ps.setString(1, title);
			    ps.setString(2, author);
			    ps.setString(3, date);
			    ps.setString(4, genre);
			    ps.setDouble(5, price);
			    ps.setInt(6, stock);
			    ps.setString(7, isbn);
			    ps.setString(8, desc);
			    ps.setString(9, imagePath);
			    ps.setInt(10, id);
			    int affectedRows = ps.executeUpdate();
			    if(affectedRows == 1) {
			    	return "Success";
			    }
			}
		}catch(Exception e) {
			System.err.print(e);
			if(e.getLocalizedMessage().contains("Data too long for column 'imageBLOB'")) {
				return "imageSizeErr";
			}
		}
		return "Failed";
	}
	
	public String deleteBook(int bookID) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "DELETE FROM Books WHERE id = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, bookID);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows == 1) {
		    	return "Success";
		    }else {
		    	return "Error";
		    }
		}catch(Exception e) {
			return "Error";
		}
	}
	
	public User getCustomer(int userID) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "SELECT * FROM Customers WHERE custID = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, userID);
		    ResultSet rs = ps.executeQuery();
		    if(rs.next()) {
		    	String username = rs.getString("username");
		    	String email = rs.getString("email");
		    	String password = rs.getString("password");
		    	String imageBlob = rs.getString("custImageURL");
		    	int custID = rs.getInt("custID");
		    	User user = new User(username, email, password, imageBlob);
		    	
		    	user.setUserID(custID);
		    	
		    	return user;
		    }else {
		    	throw new Exception("No User");
		    }
		}catch(Exception e) {
			throw e;
		}
	}
	
	public void deleteCustomer(int custID) throws Exception{
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "DELETE FROM Customers WHERE custID = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, custID);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows != 1) {
		    	throw new Exception("Error");
		    }
		}catch(Exception e) {
			throw e;
		}
	}
	
	public static void deleteDirectory(File directory) {
	    if (directory.exists()) {
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                if (file.isDirectory()) {
	                    deleteDirectory(file);
	                } else {
	                    file.delete();
	                }
	            }
	        }
	        directory.delete();
	    }
	}
	
	public void updateCustomer(int custID, String custUsername, String email, String custPassword, Part imageFile) throws Exception {
		String imagePath = null;
		if(imageFile != null) {
			try {
				imagePath = Image.saveImage(imageFile);
			} catch (Exception e) {
			}
		}
		
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "UPDATE Customers SET username = ?, email = ?";
		    
		    if(custPassword != null) {
		    	sqlStr += ",password = ?";
		    }
		    
		    if(imagePath != null) {
		    	sqlStr += ",custImageURL = ?";
		    }
		    
		    sqlStr += " WHERE custID = ?";
		    
		    PreparedStatement ps = conn.prepareStatement(sqlStr);
		    ps.setString(1, custUsername);
		    ps.setString(2, email);
		    
		    if(custPassword == null && imagePath == null) {
		    	ps.setInt(3, custID);
		    }else if(custPassword != null && imagePath != null) {
		    	ps.setString(3, custPassword);
		    	ps.setString(4, imagePath);
		    	ps.setInt(5, custID);
		    }else if(custPassword == null) {
		    	ps.setString(3, imagePath);
		    	ps.setInt(4, custID);
		    }else if(imagePath == null) {
		    	ps.setString(3, custPassword);
		    	ps.setInt(4, custID);
		    }
		    
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows != 1) {
		    	throw new Exception("Error");
		    }
		}catch(Exception e) {
			System.err.print(e);
			throw e;
		}
	}
	
	public void createUser(String custUsername, String custPassword, String email, Part imageFile) throws Exception {
		String imagePath = null;
		if(imageFile != null) {
			try {
				imagePath = Image.saveImage(imageFile);
			} catch (Exception e) {
			}
		}
		
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "INSERT INTO Customers (username, email, password, custImageURL) VALUES (?,?,?,?)";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, custUsername);
		    ps.setString(2, email);
		    ps.setString(3, custPassword);
		    ps.setString(4, imagePath);
		    int affectedRows = ps.executeUpdate();
		    if(affectedRows != 1) {
		    	throw new Exception("Error");
		    }
		}catch(Exception e) {
			throw e;
		}
	}
	
}
