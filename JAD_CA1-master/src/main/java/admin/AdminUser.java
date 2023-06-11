package admin;
import java.util.*;

public class AdminUser {
	private String username, email;
	private int UserID;
	private ArrayList<String> address;
	
	public AdminUser(String inputUN, String inputEmail, int inputUserID) {
		username = inputUN;
		email = inputEmail;
		UserID = inputUserID;
	}
	
	public String getUserName() {
		return username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getUserID() {
		return UserID;
	}
	
	public void setAddress(ArrayList<String> inputAddress) {
		address = inputAddress;
	}
}
