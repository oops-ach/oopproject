package repositories.interfaces;
import models.User;
import java.sql.SQLException;
import java.util.List;

public interface IUserRepository {
	List<User> getAllUsers() throws SQLException;
	void addUser(String name, String surname, String bookTitle) throws SQLException;
	void deleteUser(String name, String surname) throws SQLException;
	boolean userExists(String name, String surname) throws SQLException;
	String getBookTitleByUser(String name, String surname) throws SQLException;
	void deleteBookTitle(String name, String surname) throws SQLException;
}
