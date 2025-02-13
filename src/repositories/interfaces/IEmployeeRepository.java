package repositories.interfaces;
import models.Employee;
import java.sql.SQLException;
import java.util.List;

public interface IEmployeeRepository {
	void addEmployee(String name, String surname, String position, double salary) throws SQLException;
	void deleteEmployee(int employeeId) throws SQLException;
	List<Employee> getEmployeesByPosition(String position) throws SQLException;
	List<Employee> getAllEmployees() throws SQLException;
}
