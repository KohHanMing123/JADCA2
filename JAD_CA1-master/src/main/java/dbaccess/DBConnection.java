package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static String username = System.getenv("PLANETSCALE_USERNAME");
	private static String password = System.getenv("PLANETSCALE_KEY");
	
	public static Connection getConnection() {

		String dbUrl = "jdbc:mysql://aws.connect.psdb.cloud:3306/jad-booksgalore";
		String dbUser = username;
		String dbPassword = password;
		String dbClass = "com.mysql.jdbc.Driver";
		
		Connection connection = null;
		
		try {
			Class.forName(dbClass);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
}

