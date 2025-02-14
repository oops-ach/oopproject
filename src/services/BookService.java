package services;

import models.Book;
import repositories.BookRepository;
import repositories.interfaces.IBookRepository;
import java.sql.SQLException;
import java.util.List;

public class BookService {
	private final IBookRepository bookRepository;

	public BookService(IBookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	public List<Book> getAllBooks() throws SQLException {
		return bookRepository.getAllBooks();
	}

	public void addNewBook(String title, String author, int year, int quantity, String category) throws SQLException {
		if (title.isEmpty() || author.isEmpty() || category.isEmpty() || year <= 0 || quantity < 0) {
			throw new SQLException("Invalid book details! Check input values.");
		}
		bookRepository.addNewBook(title, author, year, quantity, category);
	}

	public boolean isBookAvailable(String bookTitle) throws SQLException {
		return bookRepository.isBookAvailable(bookTitle);
	}

	public void takeBook(String name, String surname, String bookTitle) throws SQLException {
		if (!isBookAvailable(bookTitle)) {
			throw new SQLException("Book is not available or out of stock.");
		}
		bookRepository.takeBook(name, surname, bookTitle);
	}

	public void returnBook(String bookTitle) throws SQLException {
		bookRepository.returnBook(bookTitle);
	}

	public void deleteBook(String title) throws SQLException {
		bookRepository.deleteBook(title);
	}

	public void filterBooksByStatus(String status) throws SQLException {
		if (!status.equals(Book.STATUS_AVAILABLE) && !status.equals(Book.STATUS_TAKEN)) {
			throw new SQLException("Invalid status! Use 'available' or 'taken'.");
		}
		List<Book> books = bookRepository.filterBooksByStatus(status);
		books.forEach(System.out::println);
	}

	public void searchBooks(String title, String author) throws SQLException {
		List<Book> books = bookRepository.searchBooks(title, author);
		books.forEach(System.out::println);
	}
}
