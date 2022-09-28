package com.samco.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.samco.model.Employee;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(Employee employee) {
		return jdbcTemplate.update("INSERT INTO EMPLOYEE(id,name,email,contact_no,city)VALUES(?,?,?,?,?)",
		new Object[] {employee.getId(),employee.getName(),employee.getEmail(),employee.getContact_no(),employee.getCity()});
	}

	@Override
	public int update(Employee employee) {
		return jdbcTemplate.update("UPDATE EMPLOYEE SET id=?,name=?,email=?,contact_no=?,city=?",
		new Object[] {employee.getId(),employee.getName(),employee.getEmail(),employee.getContact_no(),employee.getCity()});
	}

	@Override
	public Employee findById(int id) {
		try {
			Employee employee = jdbcTemplate.queryForObject("SELECT * FROM EMPLOYEE WHERE ID=?", 
			BeanPropertyRowMapper.newInstance(Employee.class),id);
			
			return employee;
		}
		catch(IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM EMPLOYEE WHERE ID=?",id);
	}

	@Override
	public List<Employee> findAll() {
		return jdbcTemplate.query("SELECT * FROM EMPLOYEE", BeanPropertyRowMapper.newInstance(Employee.class));
	}

	@Override
	public int deleteAll() {
		return jdbcTemplate.update("DELETE * FROM EMPLOYEE");
	}

	
	@Override
	  public List<Employee> findByName(String name) {
	    String q = "SELECT * FROM EMPLOYEE WHERE name LIKE '%" + name + "%'";

	    return jdbcTemplate.query(q, BeanPropertyRowMapper.newInstance(Employee.class));
	  }
}
