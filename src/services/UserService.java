package services;

import models.User;
import repositories.interfaces.IUserRepository;
import java.sql.SQLException;
import java.util.List;

public class UserService {
	private final IUserRepository userRepository;

	public UserService(IUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() throws SQLException {
		return userRepository.getAllUsers();
	}

	public void addUser(String name, String surname, String bookTitle) throws SQLException {
		userRepository.addUser(name, surname, bookTitle);
	}

	public void deleteUser(String name, String surname) throws SQLException {
		userRepository.deleteUser(name, surname);
	}
}
