package repositories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import data.interfaces.IDB;
import models.Book;
import repositories.interfaces.IBookRepository;

public class BookRepository implements IBookRepository {
	private IDB db;

	public BookRepository(IDB db) {
		this.db = db;
	}

	public List<Book> searchBooks(String title, String author) throws SQLException {
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM librardb WHERE title LIKE ? AND author LIKE ? ORDER BY title ASC";

		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, "%" + title + "%");
			pstmt.setString(2, "%" + author + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				books.add(new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getInt("year"),
						rs.getInt("quantity"),
						rs.getString("category")
				));
			}
		}
		return books;
	}



	public List<Book> filterBooksByStatus(String status) throws SQLException {
		List<Book> books = new ArrayList<>();
		String query;

		if (status.equals("available")) {
			query = "SELECT * FROM librardb WHERE quantity > 0 ORDER BY title ASC";
		} else if (status.equals("taken")) {
			query = "SELECT * FROM librardb WHERE quantity = 0 ORDER BY title ASC";
		} else {
			return books;
		}

		try (Connection conn = db.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				books.add(new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getInt("year"),
						rs.getInt("quantity"),
						rs.getString("category")
				));
			}
		}
		return books;
	}


	public List<Book> getAllBooks() throws SQLException {
		List<Book> books = new ArrayList<>();
		String query = "SELECT * FROM librardb ORDER BY id ASC";
		try (Connection conn = db.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				books.add(new Book(
						rs.getInt("id"),
						rs.getString("title"),
						rs.getString("author"),
						rs.getInt("year"),
						rs.getInt("quantity"),
						rs.getString("category")
				));
			}
		}
		return books;
	}


	public void addNewBook(String title, String author, int year, int quantity, String category) throws SQLException {
		String query = "INSERT INTO librardb (title, author, year, quantity, category) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, title);
			pstmt.setString(2, author);
			pstmt.setInt(3, year);
			pstmt.setInt(4, quantity);
			pstmt.setString(5, category);
			pstmt.executeUpdate();
		}
	}


	public void deleteBook(String bookTitle) throws SQLException {
		String query = "DELETE FROM librardb WHERE title = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, bookTitle);
			pstmt.executeUpdate();
		}
	}

	public void takeBook(String name, String surname, String bookTitle) throws SQLException {
		String query1 = "UPDATE librardb SET quantity = quantity - 1 WHERE title = ? AND quantity > 0";
		String query2 = "INSERT INTO \"user\" (name, surname, booktitle) VALUES (?, ?, ?)";

		try (Connection conn = db.getConnection()){
			conn.setAutoCommit(false);

			try (PreparedStatement pstmt = conn.prepareStatement(query1)) {
				pstmt.setString(1, bookTitle);
				int affectedRows = pstmt.executeUpdate();

				if (affectedRows == 0) {
					conn.rollback();
					throw new SQLException("Book not available or out of stock");
				}
			}

			try (PreparedStatement pstmt2 = conn.prepareStatement(query2)){
				pstmt2.setString(1, name);
				pstmt2.setString(2, surname);
				pstmt2.setString(3, bookTitle);
				pstmt2.executeUpdate();
			}
			conn.commit();
		}catch (SQLException e) {
			System.out.println("Error taking book: " + e.getMessage());
		}

	}


	public void returnBook(String bookTitle) throws SQLException {
		String query = "UPDATE librardb SET quantity = quantity + 1 WHERE title = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, bookTitle);
			pstmt.executeUpdate();
		}
	}


	public boolean isBookAvailable(String bookTitle) throws SQLException {
		String query = "SELECT quantity FROM librardb WHERE title = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, bookTitle);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int quantity = rs.getInt("quantity");
				return quantity > 0;
			}
		}
		return false;
	}


}