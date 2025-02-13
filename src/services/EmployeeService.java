package services;

import models.Employee;
import repositories.interfaces.IEmployeeRepository;
import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
	private final IEmployeeRepository employeeRepository;

	public EmployeeService(IEmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void addEmployee(String name, String surname, String position, double salary) throws SQLException {
		if (name == null || name.trim().isEmpty() ||
				surname == null || surname.trim().isEmpty() ||
				position == null || position.trim().isEmpty() ||
				salary < 0) {
			throw new SQLException("Invalid employee details! Name, surname, position must be filled, salary must be >= 0.");
		}
		employeeRepository.addEmployee(name, surname, position, salary);
	}

	public void deleteEmployee(int employeeId) throws SQLException {
		if (employeeId <= 0) {
			throw new SQLException("Invalid Employee ID.");
		}
		employeeRepository.deleteEmployee(employeeId);
	}

	public List<Employee> getEmployeesByPosition(String position) throws SQLException {
		if (position == null || position.trim().isEmpty()) {
			throw new SQLException("Position cannot be empty.");
		}
		return employeeRepository.getEmployeesByPosition(position);
	}

	public List<Employee> getAllEmployees() throws SQLException {
		return employeeRepository.getAllEmployees();
	}
}
