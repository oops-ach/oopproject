package repositories;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import data.interfaces.IDB;
import models.Book;

public class BookRepository {
    private IDB db;

    public BookRepository(IDB db) {
        this.db = db;
    }

    public List<Book> searchBooks(String title, String author, String category) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title LIKE ? AND author LIKE ? AND category LIKE ? ORDER BY title ASC";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + title + "%");
            pstmt.setString(2, "%" + author + "%");
            pstmt.setString(3, "%" + category + "%"); 
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
            query = "SELECT * FROM books WHERE quantity > 0 ORDER BY title ASC";
        } else if (status.equals("taken")) {
            query = "SELECT * FROM books WHERE quantity = 0 ORDER BY title ASC";
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
        String query = "SELECT * FROM books ORDER BY id ASC";
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

    
    public void addNewBook(String title, String author, int year, int quantity) throws SQLException {
        String query = "INSERT INTO books (title, author, year, quantity) VALUES (?, ?, ?, ?)";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, year);
            pstmt.setInt(4, quantity);
            pstmt.executeUpdate();
        }
    }


    public void deleteBook(String bookTitle) throws SQLException {
        String query = "DELETE FROM books WHERE title = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, bookTitle);
            pstmt.executeUpdate();
        }
    }

    public void takeBook(String bookTitle) throws SQLException {
        String query = "UPDATE books SET quantity = quantity - 1 WHERE title = ? AND quantity > 0";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, bookTitle);
            pstmt.executeUpdate();
        }
    }

   
    public void returnBook(String bookTitle) throws SQLException {
        String query = "UPDATE books SET quantity = quantity + 1 WHERE title = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, bookTitle);
            pstmt.executeUpdate();
        }
    }

    
    public boolean isBookAvailable(String bookTitle) throws SQLException {
        String query = "SELECT quantity FROM books WHERE title = ?";
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
