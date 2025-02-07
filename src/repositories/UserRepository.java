package repositories;

import data.interfaces.IDB;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM \"users\" ORDER BY id ASC";

        try (Connection conn = db.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("booktitle")
                ));
            }
        }
        return users;
    }


    public boolean userExists(String name, String surname) throws SQLException {
        String query = "SELECT COUNT(*) FROM \"users\" WHERE name = ? AND surname = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            return rs.getInt(1) > 0;
        }
    }


    public String getBookTitleByUser(String name, String surname) throws SQLException {
        String query = "SELECT booktitle FROM \"users\" WHERE name = ? AND surname = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("booktitle");
            }
        }
        return null;
    }

    
    public void addUser(String name, String surname, String bookTitle) throws SQLException {
        String query = "INSERT INTO \"users\" (name, surname, booktitle) VALUES (?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, bookTitle);
            pstmt.executeUpdate();
        }
    }

    
    public void updateBookTitle(String name, String surname, String bookTitle) throws SQLException {
        String query = "UPDATE \"users\" SET booktitle = ? WHERE name = ? AND surname = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, bookTitle);
            pstmt.setString(2, name);
            pstmt.setString(3, surname);
            pstmt.executeUpdate();
        }
    }

    public void deleteUser(String name, String surname) throws SQLException {
        String query = "DELETE FROM users WHERE name = ? AND surname = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.executeUpdate();
        }
    }

    
    public void deleteBookTitle(String name, String surname) throws SQLException {
        String query = "UPDATE \"users\" SET booktitle = NULL WHERE name = ? AND surname = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.executeUpdate();
        }
    }
}


