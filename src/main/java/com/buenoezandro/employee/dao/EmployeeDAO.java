package com.buenoezandro.employee.dao;

import java.util.List;

import com.buenoezandro.employee.model.Employee;

public interface EmployeeDAO {

	int save(Employee employee);

	int update(Employee employee, int id);

	int delete(int id);

	List<Employee> getAll();

	Employee getById(int id);

}