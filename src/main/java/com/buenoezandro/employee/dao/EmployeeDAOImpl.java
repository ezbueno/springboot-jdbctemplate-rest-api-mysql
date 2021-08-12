package com.buenoezandro.employee.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.buenoezandro.employee.model.Employee;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	private final JdbcTemplate jdbcTemplate;

	@Transactional
	@Override
	public int save(Employee employee) {
		var insertQuery = "INSERT INTO tbl_employees(name, email, department) VALUES(?, ?, ?)";
		var params = new Object[] { employee.getName(), employee.getEmail(), employee.getDepartment() };
		return this.jdbcTemplate.update(insertQuery, params);
	}

	@Transactional
	@Override
	public int update(Employee employee, int id) {
		var updateQuery = "UPDATE tbl_employees SET name= ?, email = ?, department = ? WHERE id = ?";
		var params = new Object[] { employee.getName(), employee.getEmail(), employee.getDepartment(), id };
		return this.jdbcTemplate.update(updateQuery, params);
	}

	@Transactional
	@Override
	public int delete(int id) {
		var deleteQuery = "DELETE FROM tbl_employees WHERE id = ?";
		return this.jdbcTemplate.update(deleteQuery, id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Employee> getAll() {
		var selectQuery = "SELECT * FROM tbl_employees";
		return this.jdbcTemplate.query(selectQuery, new BeanPropertyRowMapper<Employee>(Employee.class));
	}

	@Transactional(readOnly = true)
	@Override
	public Employee getById(int id) {
		var selectQuery = "SELECT * FROM tbl_employees WHERE id = ?";
		return this.jdbcTemplate.queryForObject(selectQuery, new BeanPropertyRowMapper<Employee>(Employee.class), id);
	}

}