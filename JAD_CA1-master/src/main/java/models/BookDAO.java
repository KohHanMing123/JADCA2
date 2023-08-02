package models;
import java.sql.*;
import java.util.*;

import dbaccess.DBConnection;

public class BookDAO {
	public ArrayList<Book> getNewArrivalBooks(int limit, int offset) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			
			String sqlStr = "SELECT * FROM Books WHERE stock <> 0 ORDER BY dateAdded DESC LIMIT ? OFFSET ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, limit);
		    ps.setInt(2, offset);
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
		    	
		    	if(imagePath == null) {
		    		imagePath = "/assets/defaultBook.jpeg";
		    	}
		    	books.add(new Book(stock, id, title, author, publicdate, genre, isbn, dateAdded, price, description, imagePath));
		    }
		    return books;
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
		
	}
	public ArrayList<Book> searchBook(String searchInput, String genreInput, double minPrice, double maxPrice, String orderBy, int limit, int offset) throws Exception{
//		if(!(orderBy == "price" || orderBy == "alphabet")) {
//			throw new Exception("invalid order By");
//		}
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM Books WHERE stock <> 0 AND title LIKE ? AND IF(? = '', TRUE, genre = ?) AND IF(? = 0, TRUE, price >= ?) AND IF(? = 0, TRUE, price <= ?) ";
			if(!orderBy.equals("")) {
				if(orderBy.equals("priceASC"))
					sqlStr += "ORDER BY price ASC ";
				if(orderBy.equals("priceDSC"))
					sqlStr += "ORDER BY price DESC ";
			}
			sqlStr += "LIMIT ? OFFSET ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    String formattedSearch  ="%" + searchInput + "%";
		    

		    
		    ps.setString(1, formattedSearch);
		    ps.setString(2, genreInput);
		    ps.setString(3, genreInput);
		    ps.setDouble(4, minPrice);
		    ps.setDouble(5, minPrice);
		    ps.setDouble(6, maxPrice);
		    ps.setDouble(7, maxPrice);
		    ps.setInt(8, limit);
		    ps.setInt(9, offset);
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
		    	
		    	if(imagePath == null) {
		    		imagePath = "/assets/defaultBook.jpeg";
		    	}
		    	books.add(new Book(stock, id, title, author, publicdate, genre, isbn, dateAdded, price, description, imagePath));
		    }
		    return books;
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
	}
	
	public int searchCount(String searchInput, String genreInput, double minPrice, double maxPrice, String orderBy) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT  COUNT(*) as total_count FROM Books WHERE stock <> 0 AND title LIKE ? AND IF(? = '', TRUE, genre = ?) AND IF(? = 0, TRUE, price >= ?) AND IF(? = 0, TRUE, price <= ?) ";

		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    String formattedSearch  ="%" + searchInput + "%";
		    
		    ps.setString(1, formattedSearch);
		    ps.setString(2, genreInput);
		    ps.setString(3, genreInput);
		    ps.setDouble(4, minPrice);
		    ps.setDouble(5, minPrice);
		    ps.setDouble(6, maxPrice);
		    ps.setDouble(7, maxPrice);
		    ResultSet rs = ps.executeQuery();
		    int count = 0;
		    if(rs.next()) {
		    	count = rs.getInt("total_count");
		    }else {
		    	throw new Exception("Error");
		    }
		    return count;
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
	}
	
	public Book getBook(int inputID) throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT * FROM Books WHERE id = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setInt(1, inputID);
		    
		    ResultSet rs = ps.executeQuery();
		    Book books;
		    if(rs.next()) {
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
		    	
		    	if(imagePath == null) {
		    		imagePath = "/assets/defaultBook.jpeg";
		    	}
		    	books = new Book(stock, id, title, author, publicdate, genre, isbn, dateAdded, price, description, imagePath);
		    	return books;
		    }else {
		    	throw new Exception("Does Not Exist");
		    	
		    }
		}catch (Exception e) {
	        throw e;
	    }
	}
	
	public List<Book> getBooksByGenre(String genre) throws Exception {
	    List<Book> books = new ArrayList<Book>();
	    Connection conn = null;
		try {
			conn = DBConnection.getConnection();

	        String sqlStr = "SELECT * FROM Books WHERE genre = ?";
	        PreparedStatement ps = conn.prepareStatement(sqlStr);
	        ps.setString(1, genre);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String title = rs.getString("title");
	            String publicationDate = rs.getString("publication_date");
	            String author = rs.getString("author");
	            double price = rs.getDouble("price");
	            String isbn = rs.getString("isbn13");
	            String dateAdded = rs.getString("dateAdded");
	            String description = rs.getString("description");
	            int stock = rs.getInt("stock");
	            String imagePath = rs.getString("imagePath");
		    	
		    	if(imagePath == null) {
		    		imagePath = "/assets/defaultBook.jpeg";
		    	}
	            Book book = new Book(stock, id, title, author, publicationDate, genre, isbn, dateAdded, price, description, imagePath);
	            books.add(book);
	        }

	        rs.close();
	        ps.close();
	        conn.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;
	    }
	    return books;
	}

	public String insertImage(byte[] imageBlob) {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "UPDATE Books SET imageURL = ? WHERE id = ?";
			PreparedStatement ps=conn.prepareStatement(sqlStr);
			ps.setBytes(1, imageBlob);
			ps.setInt(2, 4);
			int rowsAffected= ps.executeUpdate();
			if(rowsAffected == 1) {
				return "Inserted";
			}
			return "Not Inserted";
		}catch(Exception e) {
			System.err.println("Error :" + e);
			return "Error";
		}
	}
	
	public ArrayList<String> getAllGenre() throws Exception {
		Connection conn = null;
		try {
			conn = DBConnection.getConnection();
			String sqlStr = "SELECT DISTINCT(genre) AS genre FROM Books";
			PreparedStatement ps=conn.prepareStatement(sqlStr);
			ResultSet rs= ps.executeQuery();
			ArrayList<String> genres = new ArrayList<String>();
			while(rs.next()) {
				genres.add(rs.getString("genre"));
			}
			return genres;
		}catch(Exception e) {
			System.err.println("Error :" + e);
			throw new Exception("Error");
		}
	}
}
