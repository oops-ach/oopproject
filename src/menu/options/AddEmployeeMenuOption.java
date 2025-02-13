package menu.options;

import menu.MenuOption;
import controllers.EmployeeController;
import java.util.Scanner;

public class AddEmployeeMenuOption implements MenuOption {
	private final EmployeeController employeeController;

	public AddEmployeeMenuOption(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter employee name: ");
		String name = scanner.nextLine();

		System.out.print("Enter employee surname: ");
		String surname = scanner.nextLine();

		System.out.print("Enter employee position (Manager/Librarian/Technician): ");
		String position = scanner.nextLine();

		System.out.print("Enter employee salary: ");
		double salary = scanner.nextDouble();

		employeeController.addEmployee(name, surname, position, salary);
	}

	@Override
	public String getDescription() {
		return "Add a new employee";
	}
}
