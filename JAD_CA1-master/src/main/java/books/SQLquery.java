package books;
import java.sql.*;
import java.util.*;

public class SQLquery {
	private static String username = System.getenv("PLANETSCALE_USERNAME");
	private static String password = System.getenv("PLANETSCALE_KEY");
	public static ArrayList<Book> getNewArrivalBooks() throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT * FROM Books ORDER BY dateAdded DESC LIMIT 3";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ResultSet rs = ps.executeQuery();
		    ArrayList<Book> books = new ArrayList<Book>();
		    while(rs.next()) {
		    	String title = rs.getString("title");
		    	String publicdate = rs.getString("publication_date");
		    	String author = rs.getString("author");
		    	String genre = rs.getString("genre");
		    	double price = rs.getDouble("price");
		    	String isbn = rs.getString("isbn13");
		    	String dateAdded = rs.getString("dateAdded");
		    	String description = rs.getString("description");
		    	books.add(new Book(title, author, publicdate, genre, isbn, dateAdded, price, description));
		    }
		    return books;
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
		
	}
	
	public static Book getBook(String inputIsbn) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			String connURL = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore?user=" + username + "&password=" + password + "&serverTimezone=UTC";
			Connection conn = DriverManager.getConnection(connURL);
			
			String sqlStr = "SELECT * FROM Books WHERE isbn13 = ?";
		    PreparedStatement ps=conn.prepareStatement(sqlStr);
		    ps.setString(1, inputIsbn);
		    
		    ResultSet rs = ps.executeQuery();
		    Book books;
		    if(rs.next()) {
		    	String title = rs.getString("title");
		    	String publicdate = rs.getString("publication_date");
		    	String author = rs.getString("author");
		    	String genre = rs.getString("genre");
		    	double price = rs.getDouble("price");
		    	String isbn = rs.getString("isbn13");
		    	String dateAdded = rs.getString("dateAdded");
		    	String description = rs.getString("description");
		    	books = new Book(title, author, publicdate, genre, isbn, dateAdded, price, description);
		    	return books;
		    }else {
		    	throw new Exception("Does Not Exist");
		    }
		}catch (Exception e) {
	        System.err.println("Error :" + e);
	        throw e;
	    }
	}
}
