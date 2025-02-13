package menu.options;

import menu.MenuOption;
import controllers.BookController;

import java.util.Scanner;

public class AddBookMenuOption implements MenuOption {
	private final BookController bookController;

	public AddBookMenuOption(BookController bookController) {
		this.bookController = bookController;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter book title: ");
		String title = scanner.nextLine();

		System.out.print("Enter author name: ");
		String author = scanner.nextLine();

		System.out.print("Enter publication year: ");
		int year = scanner.nextInt();

		System.out.print("Enter quantity: ");
		int quantity = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Enter category: ");
		String category = scanner.nextLine();

		bookController.addNewBook(title, author, year, quantity, category);
	}

	@Override
	public String getDescription() {
		return "Add a new book";
	}
}
