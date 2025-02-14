package models.workers;

import models.Employee;

public class Technician extends Employee {
	public Technician(int id, String name, String surname, double salary) {
		super(id, name, surname, "Technician", salary);
	}

	@Override
	public void work() {
		System.out.println(name + " is maintaining and repairing library equipment.");
	}
}
