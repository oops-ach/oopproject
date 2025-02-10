package menu.options;

import menu.MenuOption;
import controllers.BookController;

import java.sql.SQLException;
import java.util.Scanner;
import controllers.UserController;

import java.util.Scanner;

public class TakeBookMenuOption implements MenuOption {
    private BookController bookController;
    private UserController userController;

    public TakeBookMenuOption(BookController bookController, UserController userController) {
        this.bookController = bookController;
        this.userController = userController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter the book title to take: ");
        String title = scanner.nextLine();

        try {
            bookController.takeBook(name, surname, title);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Take a book";
    }
}
