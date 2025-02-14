package repositories;

import repositories.interfaces.IEmployeeRepository;
import models.*;
import data.interfaces.IDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements IEmployeeRepository {
	private final IDB db;

	public EmployeeRepository(IDB db) {
		this.db = db;
	}

	@Override
	public void addEmployee(String name, String surname, String position, double salary) throws SQLException {
		String query = "INSERT INTO employee (name, surname, position, salary) VALUES (?, ?, ?, ?)";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, name);
			pstmt.setString(2, surname);
			pstmt.setString(3, position);
			pstmt.setDouble(4, salary);
			pstmt.executeUpdate();
		}
	}

	@Override
	public void deleteEmployee(int employeeId) throws SQLException {
		String query = "DELETE FROM employee WHERE id = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setInt(1, employeeId);
			pstmt.executeUpdate();
		}
	}

	@Override
	public List<Employee> getEmployeesByPosition(String position) throws SQLException {
		List<Employee> employees = new ArrayList<>();
		String query = "SELECT * FROM employee WHERE position = ?";
		try (Connection conn = db.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(query)) {
			pstmt.setString(1, position);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				employees.add(new Employee(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("surname"),
						rs.getString("position"),
						rs.getDouble("salary")
				));
			}
		}
		return employees;
	}

	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> employees = new ArrayList<>();
		String query = "SELECT * FROM employee";
		try (Connection conn = db.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(query)) {
			while (rs.next()) {
				employees.add(new Employee(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("surname"),
						rs.getString("position"),
						rs.getDouble("salary")
				));
			}
		}
		return employees;
	}
}
