package menu.options;

import menu.MenuOption;
import controllers.BookController;
import java.util.Scanner;

public class FilterBooksMenuOption implements MenuOption {
    private BookController bookController;

    public FilterBooksMenuOption(BookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter status (available/taken): ");
        String status = scanner.nextLine();

        bookController.filterBooksByStatus(status);
    }

    @Override
    public String getDescription() {
        return "Filter books by status (available/taken)";
    }
}
