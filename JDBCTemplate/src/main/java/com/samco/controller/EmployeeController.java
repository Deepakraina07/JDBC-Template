package com.samco.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.samco.model.Employee;
import com.samco.repository.EmployeeRepository;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeRepository employeeRepository;

	@PostMapping("/addEmployee")
	public ResponseEntity<String> createEmployee(@RequestBody Employee employee) {
		try {
			employeeRepository.save(new Employee(employee.getId(), employee.getName(), employee.getEmail(),
					employee.getContact_no(), employee.getCity()));
			return new ResponseEntity<>("Employee was created successfully.", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>("Employee was not Created ", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getEmployee")
	public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam(required = false) String name) {
		try {
			List<Employee> employee = new ArrayList<Employee>();

			if (name == null)
				employeeRepository.findAll().forEach(employee::add);
			else
				employeeRepository.findByName(name).forEach(employee::add);
			if (employee.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employee, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/updateEmployee/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee) {
		Employee _employee = employeeRepository.findById(id);

		if (_employee != null) {
			_employee.setId(id);
			_employee.setName(employee.getName());
			_employee.setEmail(employee.getEmail());
			_employee.setContact_no(employee.getContact_no());
			_employee.setCity(employee.getCity());

			employeeRepository.update(_employee);
			return new ResponseEntity<>("Employee was updated successfully.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Cannot find Employee with id=" + id, HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteAllEmployee() {
		try {
			int numRows = employeeRepository.deleteAll();
			return new ResponseEntity<>("Deleted" + numRows + "Employee Successfully ", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot delete Tutorial", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteByEmployeeId(@PathVariable("id") int id) {
		try {
			int result = employeeRepository.deleteById(id);
			if (result == 0) {
				return new ResponseEntity<>("Cannot Find the id= " + id, HttpStatus.OK);
			}
			return new ResponseEntity<>("Employee Deleted Successfully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Cannot Delete Employee", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
