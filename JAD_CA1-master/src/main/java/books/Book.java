package books;

public class Book {
	private String title, author, public_date, genre, isbn, dateAdded, description;
	private double price;
	
	public Book(String inputTitle, String inputAuthor, String inputPublic_date, String inputGenre, String inputIsbn, String inputDateAdded, double inputPrice, String inputDescription) {
		title = inputTitle;
		author = inputAuthor;
		public_date = inputPublic_date;
		genre = inputGenre;
		isbn = inputIsbn;
		dateAdded = inputDateAdded;
		price = inputPrice;
		description = inputDescription;
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
}
