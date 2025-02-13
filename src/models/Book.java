package models;

public class Book {
	public static final String STATUS_AVAILABLE = "available";
	public static final String STATUS_TAKEN = "taken";

	private int id;
	private String title;
	private String author;
	private int year;
	private int quantity;
	private String category;
	private String status;

	public Book(int id, String title, String author, int year, int quantity, String category) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.year = year;
		this.quantity = quantity;
		this.category = category;
		this.status = (quantity > 0) ? STATUS_AVAILABLE : STATUS_TAKEN;
	}

	public int getId() { return id; }
	public String getTitle() { return title; }
	public String getAuthor() { return author; }
	public int getYear() { return year; }
	public int getQuantity() { return quantity; }
	public String getCategory() { return category; }
	public String getStatus() { return status; }

	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.status = (quantity > 0) ? STATUS_AVAILABLE : STATUS_TAKEN;
	}

	@Override
	public String toString() {
		return "Book ID: " + id +
				", Title: " + title +
				", Author: " + author +
				", Year: " + year +
				", Quantity: " + quantity +
				", Category: " + category +
				", Status: " + status;
	}
}
