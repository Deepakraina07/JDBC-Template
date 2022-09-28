package com.samco.repository;

import java.util.List;


import com.samco.model.Employee;


public interface EmployeeRepository {

	int save(Employee employee);
	
	int update(Employee employee);
	
	Employee findById(int id);
	
	int deleteById(int id);
	
	List<Employee> findAll();
	
	int deleteAll();

	List<Employee> findByName(String name);
	
}
