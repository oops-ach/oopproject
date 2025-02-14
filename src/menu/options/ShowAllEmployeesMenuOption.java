package menu.options;

import menu.MenuOption;
import controllers.EmployeeController;

public class ShowAllEmployeesMenuOption implements MenuOption {
	private final EmployeeController employeeController;

	public ShowAllEmployeesMenuOption(EmployeeController employeeController) {
		this.employeeController = employeeController;
	}

	@Override
	public void execute() {
		employeeController.showAllEmployees();
	}

	@Override
	public String getDescription() {
		return "Show all employees";
	}
}

