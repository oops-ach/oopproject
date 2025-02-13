package menu.options;

import menu.MenuOption;
import controllers.EmployeeController;
import java.util.Scanner;

public class SearchEmployeesByPositionMenuOption implements MenuOption {
	private final EmployeeController employeeController;

	public SearchEmployeesByPositionMenuOption(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter position to search (Manager/Librarian/Technician): ");
		String position = scanner.nextLine();

		employeeController.searchEmployeesByPosition(position);
	}

	@Override
	public String getDescription() {
		return "Search employees by position";
	}
}
