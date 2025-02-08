package menu.options;

import menu.MenuOption;
import controllers.BookController;
import java.util.Scanner;

public class SearchBooksMenuOption implements MenuOption {
    private BookController bookController;

    public SearchBooksMenuOption(BookController bookController) {
        this.bookController = bookController;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title to search: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name to search: ");
        String author = scanner.nextLine();
        System.out.print("Enter category to search: ");
        String category = scanner.nextLine();

        bookController.searchBooks(title, author, category);
    }

    @Override
    public String getDescription() {
        return "Search books by title, author, and category";
    }
}
