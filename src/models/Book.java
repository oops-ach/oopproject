package models;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private int quantity;
    private String category;

    public Book(int id, String title, String author, int year, int quantity, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.quantity = quantity;
        this.category = category;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public String toString() {
        return "Book ID: " + id +
                ", Title: " + title +
                ", Author: " + author +
                ", Year: " + year +
                ", Quantity: " + quantity +
                ", Category: " + category ;
    }
}
