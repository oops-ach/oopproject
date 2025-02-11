import controllers.BookController;
import controllers.UserController;
import data.PostgreDB;
import data.interfaces.IDB;
import menu.Menu;
import menu.MenuOption;
import menu.options.*;
import repositories.BookRepository;
import repositories.UserRepository;

import java.util.Scanner;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "4584", "kitaphana");
        BookRepository bookRepository = new BookRepository(db);
        UserRepository userRepository = new UserRepository(db);

        BookController bookController = new BookController(bookRepository, userRepository);
        UserController userController = new UserController(userRepository);

        List<MenuOption> menuOptions = List.of(
                new ShowBooksMenuOption(bookController),
                new AddBookMenuOption(bookController),
                new DeleteBookMenuOption(bookController),
                new TakeBookMenuOption(bookController,userController),
                new ReturnBookMenuOption(bookController,userController),
                new SearchBooksMenuOption(bookController), 
                new FilterBooksMenuOption(bookController), 
                new ShowUsersMenuOption(userController),
                new DeleteUserMenuOption(userController)
        );

        Menu menu = new Menu(menuOptions);

        while (true) {
            menu.display();
            System.out.print("Choose an option: ");
            int choice = getIntInput(scanner);
            menu.executeOption(choice);
        }
    }

    private static int getIntInput(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
