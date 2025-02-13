import controllers.BookController;
import controllers.UserController;
import controllers.EmployeeController;
import repositories.BookRepository;
import repositories.UserRepository;
import repositories.EmployeeRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.IUserRepository;
import repositories.interfaces.IEmployeeRepository;
import services.BookService;
import services.UserService;
import services.EmployeeService;
import data.PostgreDB;
import data.interfaces.IDB;
import menu.Menu;
import menu.MenuOption;
import menu.options.*;

import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		IDB db = new PostgreDB("jdbc:postgresql://localhost:5432", "postgres", "135790", "llibr2");

		BookRepository bookRepository = new BookRepository(db);
		UserRepository userRepository = new UserRepository(db);
		EmployeeRepository employeeRepository = new EmployeeRepository(db);

		BookService bookService = new BookService(bookRepository);
		UserService userService = new UserService(userRepository);
		EmployeeService employeeService = new EmployeeService(employeeRepository);

		BookController bookController = new BookController(bookService);
		UserController userController = new UserController(userService);
		EmployeeController employeeController = new EmployeeController(employeeService);

		System.out.println("Welcome to the Library System!\n");

		List<MenuOption> menuOptions = List.of(
				new ShowBooksMenuOption(bookController),
				new AddBookMenuOption(bookController),
				new DeleteBookMenuOption(bookController),
				new TakeBookMenuOption(bookController),
				new ReturnBookMenuOption(bookController),
				new SearchBooksMenuOption(bookController),
				new FilterBooksMenuOption(bookController),
				new ShowUsersMenuOption(userController),
				new DeleteUserMenuOption(userController),
				new AddEmployeeMenuOption(employeeController),
				new DeleteEmployeeMenuOption(employeeController),
				new SearchEmployeesByPositionMenuOption(employeeController),
				new ShowAllEmployeesMenuOption(employeeController)
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
				System.out.println("Invalid input! Please try again. Enter a number: ");
			}
		}
	}
}
