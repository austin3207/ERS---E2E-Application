package com.revature.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.revature.model.Employee;
import com.revature.util.DBConnection;

public class EmployeeDAO {
	 private List<Employee> employees = new ArrayList<Employee>(Arrays.asList(new Employee("abc", "Steve Rogers", true),
			new Employee("def", "Tony", true), new Employee("ghi", "Carol Danvers", false)));
	 
	 private List<Employee> employeesDB = new ArrayList<Employee>();

	private static EmployeeDAO employeeDAO = null;

	private EmployeeDAO() {
	}

	public static EmployeeDAO instance() {
		if (employeeDAO == null) {
			employeeDAO = new EmployeeDAO();
		}
		return employeeDAO;
	}

	public Optional<Employee> getEmployeeByUsername(String userName) throws Exception {
		setEmployeesDB();
		return employeesDB.stream().filter(employee -> employee.getUsername().matches(userName)).findAny();
	}
	
	public Iterable<String> getAllNames() throws Exception{
		setEmployeesDB();
		return employeesDB.stream().map(employee -> employee.getUsername()).collect(Collectors.toList());
	}

	
	public Employee addEmployee(Employee employee) {
		employees.add(employee);
		return employee;
	}
	public void setEmployeesDB() throws Exception {
		
		try {
			employeesDB = DBConnection.findAllEmployees();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			employeesDB = employees;
			e.printStackTrace();
		}
	}
}
