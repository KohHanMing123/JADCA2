package books;

public class Book {
	private String title, author, public_date, genre, isbn, dateAdded, description, imageBlob;
	private double price;
	private int id, stock;
	
	public Book(int inputStock, int inputID, String inputTitle, String inputAuthor, String inputPublic_date, String inputGenre, String inputIsbn, String inputDateAdded, double inputPrice, String inputDescription, String inputBlob) {
		title = inputTitle;
		author = inputAuthor;
		public_date = inputPublic_date;
		genre = inputGenre;
		isbn = inputIsbn;
		dateAdded = inputDateAdded;
		price = inputPrice;
		description = inputDescription;
		imageBlob = inputBlob;
		id = inputID;
		stock = inputStock;
	}
	public int getID() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getISBN() {
		return isbn;
	}
	
	public String getImage() {
		return imageBlob;
	}
	public String getGenre() {
		return genre;
	}
	public double getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	public String getDate() {
		return public_date;
	}
	
	public void setTitle(String inputTitle) {
		this.title = title;
	}

	public void setAuthor(String inputAuthor) {
		this.author = author;
	}

	public void setPrice(double inputPrice) {
		this.price = price;
	}
	
	public void setBookID(int inputID) {
		this.id = id;
	}
	
	public void setImage(String inputBlob) {
		this.imageBlob = imageBlob;
	}

}
