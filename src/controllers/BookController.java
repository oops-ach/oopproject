
package controllers;

import services.BookService;
import java.sql.SQLException;

public class BookController {
	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	public void showAllBooks() {
		try {
			bookService.getAllBooks().forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println("Error fetching books: " + e.getMessage());
		}
	}

	public void addNewBook(String title, String author, int year, int quantity, String category) {
		try {
			bookService.addNewBook(title, author, year, quantity, category);
			System.out.println("Book added successfully.");
		} catch (SQLException e) {
			System.out.println("Error adding book: " + e.getMessage());
		}
	}

	public void takeBook(String name, String surname, String bookTitle) {
		try {
			bookService.takeBook(name, surname, bookTitle);
			System.out.println("Book taken successfully.");
		} catch (SQLException e) {
			System.out.println("Error taking book: " + e.getMessage());
		}
	}

	public void returnBook(String bookTitle) {
		try {
			bookService.returnBook(bookTitle);
			System.out.println("Book returned successfully.");
		} catch (SQLException e) {
			System.out.println("Error returning book: " + e.getMessage());
		}
	}

	public boolean isBookAvailable(String bookTitle) {
		try {
			return bookService.isBookAvailable(bookTitle);
		} catch (SQLException e) {
			System.out.println("Error checking book availability: " + e.getMessage());
			return false;
		}
	}

	public void deleteBook(String title) {
		try {
			bookService.addNewBook(title, "", 0, 0, "");
			System.out.println("Book deleted successfully.");
		} catch (SQLException e) {
			System.out.println("Error deleting book: " + e.getMessage());
		}
	}

	public void searchBooks(String title, String author) {
		try {
			bookService.getAllBooks()
					.stream()
					.filter(book -> book.getTitle().contains(title) && book.getAuthor().contains(author))
					.forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println("Error searching books: " + e.getMessage());
		}
	}

	public void filterBooksByStatus(String status) {
		try {
			bookService.getAllBooks()
					.stream()
					.filter(book -> book.getStatus().equalsIgnoreCase(status))
					.forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println("Error filtering books: " + e.getMessage());
		}
	}
}
