package models;

public class User {
    private String username;
    private String email;
    private String password;
    private String imagePath;
    private int userID;

    public User(String username, String email, String password, String imagePath) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.imagePath = imagePath;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
	public String getImage() {
		return imagePath;
	}
	
	public void setUserID(int inputUserID) {
		userID = inputUserID;
	}
    
	public int getUserID() {
		return userID;
	}

}
