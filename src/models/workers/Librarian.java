package models.workers;

import models.Employee;

public class Librarian extends Employee {
	public Librarian(int id, String name, String surname, double salary) {
		super(id, name, surname, "Librarian", salary);
	}

	@Override
	public void work() {
		System.out.println(name + " is helping visitors and organizing books.");
	}
}
