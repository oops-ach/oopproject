package repositories.interfaces;
import models.Book;
import java.sql.SQLException;
import java.util.List;

public interface IBookRepository {
	List<Book> getAllBooks() throws SQLException;
	void addNewBook(String title, String author, int year, int quantity, String category) throws SQLException;
	void deleteBook(String title) throws SQLException;
	boolean isBookAvailable(String title) throws SQLException;
	void takeBook(String name, String surname, String bookTitle) throws SQLException;
	void returnBook(String bookTitle) throws SQLException;
	List<Book> filterBooksByStatus(String status) throws SQLException;
	List<Book> searchBooks(String title, String author) throws SQLException;
}
