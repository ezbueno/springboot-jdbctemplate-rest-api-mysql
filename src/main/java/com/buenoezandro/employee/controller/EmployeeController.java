package com.buenoezandro.employee.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.buenoezandro.employee.dao.EmployeeDAO;
import com.buenoezandro.employee.model.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

	private final EmployeeDAO employeeDAO;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> getEmployees() {
		return this.employeeDAO.getAll();
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Employee getEmployeeById(@PathVariable int id) {
		return this.employeeDAO.getById(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public String save(@RequestBody Employee employee) {
		return this.employeeDAO.save(employee) + " No. of rows saved to the database";
	}

	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public String update(@RequestBody Employee employee, @PathVariable int id) {
		return this.employeeDAO.update(employee, id) + " No. of rows updated to the database";
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String deleteEmployeeById(@PathVariable int id) {
		return this.employeeDAO.delete(id) + " No. of rows deleted from the database";
	}

}