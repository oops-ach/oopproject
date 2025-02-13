package models.workers;

import models.Employee;

public class Manager extends Employee {
	public Manager(int id, String name, String surname, double salary) {
		super(id, name, surname, "Manager", salary);
	}

	@Override
	public void work() {
		System.out.println(name + " is managing the library staff.");
	}
}
