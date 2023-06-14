package cart;

import books.Book;

public class CartItem {
    private Book book;
    private int quantity;
    private double totalPrice;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        this.totalPrice = book.getPrice() * quantity;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = book.getPrice() * quantity;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }
    
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}

