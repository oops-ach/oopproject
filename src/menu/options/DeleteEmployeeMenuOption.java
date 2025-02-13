package menu.options;

import menu.MenuOption;
import controllers.EmployeeController;
import java.util.Scanner;

public class DeleteEmployeeMenuOption implements MenuOption {
	private final EmployeeController employeeController;

	public DeleteEmployeeMenuOption(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}

	@Override
	public void execute() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter employee ID to delete: ");
		int employeeId = scanner.nextInt();

		employeeController.deleteEmployee(employeeId);
	}

	@Override
	public String getDescription() {
		return "Delete an employee";
	}
}
