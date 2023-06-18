package models;

public class CartItem {
    private Book book;
    private int quantity;
    private double totalPrice;

    public CartItem(Book book, int quantity) {
        this.book = book;
        this.quantity = quantity;
        calculateTotalPrice();
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
        calculateTotalPrice();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    private void calculateTotalPrice() {
        if (book != null) {
            this.totalPrice = book.getPrice() * quantity;
        }
    }
}
