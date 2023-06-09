package books;

public class Book {
	private String title, author, public_date, genre, isbn, dateAdded, description, imageBlob;
	private double price;
	private int id;
	
	public Book(int inputID, String inputTitle, String inputAuthor, String inputPublic_date, String inputGenre, String inputIsbn, String inputDateAdded, double inputPrice, String inputDescription, String inputBlob) {
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
}
