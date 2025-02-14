package controllers;

import services.UserService;
import java.sql.SQLException;

public class UserController {
	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	public void showAllUsers() {
		try {
			userService.getAllUsers().forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println("Error fetching users: " + e.getMessage());
		}
	}

	public void addUser(String name, String surname, String bookTitle) {
		try {
			userService.addUser(name, surname, bookTitle);
			System.out.println("User added successfully.");
		} catch (SQLException e) {
			System.out.println("Error adding user: " + e.getMessage());
		}
	}

	public void deleteUser(String name, String surname) {
		try {
			userService.deleteUser(name, surname);
			System.out.println("User deleted successfully.");
		} catch (SQLException e) {
			System.out.println("Error deleting user: " + e.getMessage());
		}
	}
}

