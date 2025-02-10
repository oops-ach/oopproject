package menu.options;

import menu.MenuOption;
import controllers.BookController;

import java.sql.SQLException;
import java.util.Scanner;

public class AddBookMenuOption implements MenuOption {
    private BookController bookController;

    public AddBookMenuOption(BookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter the author: ");
        String author = scanner.nextLine();
        System.out.print("Enter the year: ");
        int year = scanner.nextInt();
        System.out.print("Enter the quantity: ");
        int quantity = scanner.nextInt();

        try {
            if (bookController.isBookAvailable(title)) {
                System.out.println("This book already exists in the database.");
            } else {
                bookController.addNewBook(title, author, year, quantity);
                System.out.println("New book added to the database.");
            }
        } catch (SQLException e) {
            System.out.println("Error while interacting with the database: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Add a new book";
    }
}
