package user;

public class User {
    private String username;
    private String email;
    private String password;
    private String imageBlob;
    private int userID;

    public User(String username, String email, String password, String imageBlob) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.imageBlob = imageBlob;
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
		return imageBlob;
	}
	
	public void setUserID(int inputUserID) {
		userID = inputUserID;
	}
    
	public int getUserID() {
		return userID;
	}

}
