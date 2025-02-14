
package controllers;

import services.EmployeeService;
import java.sql.SQLException;

public class EmployeeController {
	private final EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public void showAllEmployees() {
		try {
			employeeService.getAllEmployees().forEach(System.out::println);
		} catch (SQLException e) {
			System.out.println("Error fetching employees: " + e.getMessage());
		}
	}

	public void addEmployee(String name, String surname, String position, double salary) {
		try {
			employeeService.addEmployee(name, surname, position, salary);
			System.out.println("Employee added successfully.");
		} catch (SQLException e) {
			System.out.println("Error adding employee: " + e.getMessage());
		}
	}

	public void deleteEmployee(int employeeId) {
		try {
			employeeService.deleteEmployee(employeeId);
			System.out.println("Employee deleted successfully.");
		} catch (SQLException e) {
			System.out.println("Error deleting employee: " + e.getMessage());
		}
	}

	public void searchEmployeesByPosition(String position) {
		try {
			var employees = employeeService.getEmployeesByPosition(position);
			if (employees.isEmpty()) {
				System.out.println("No employees found for position: " + position);
			} else {
				employees.forEach(System.out::println);
			}
		} catch (SQLException e) {
			System.out.println("Error searching employees: " + e.getMessage());
		}
	}
}
