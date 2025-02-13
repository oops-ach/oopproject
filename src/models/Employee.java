package models;

public class Employee {
	protected int id;
	protected String name;
	protected String surname;
	protected String position;
	protected double salary;

	public Employee(int id, String name, String surname, String position, double salary) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.position = position;
		this.salary = salary;
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public String getPosition() {
		return position;
	}

	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}


	public void work(){
		System.out.println(name + " is working as "+position);
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", position='" + position + '\'' +
				", salary=" + salary +
				'}';
	}
}
