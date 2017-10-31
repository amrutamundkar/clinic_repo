package com.clinic.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clinic.model.Employee;
import com.clinic.service.EmployeeService;




@RestController
@RequestMapping(value = "clinic")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value="{cid}/employees")
	public List<Employee> getEmployeeBycId( @PathVariable("cid") int cid) {
		return employeeService.getEmployeeBycId(cid);

	}	

	@GetMapping(value="/employees")
	public List<Employee> getEmployee() {
		return employeeService.getEmployee();

	}	
	@GetMapping(value="{companyId}/employees/{id}")
	public Employee getEmployeebyId( @PathVariable("id") int id) {
		return employeeService.getEmployeebyId(id);

	}

	@PostMapping(value="/employees")
	public Employee createEmplyoee(@RequestBody Employee user) {
		return employeeService.createemp(user);
		
	}

	@PutMapping(value="{companyId}/employees/{id}")
	public List<Employee> updateEmploye(@RequestBody Employee user, @PathVariable("id") int id) {
		return employeeService.updateemp(user,id);		

	}

	@DeleteMapping(value="/employees/{id}")
	public List<Employee> deleteEmployee(@PathVariable("id") int id) {
		return employeeService.deleteemp(id);
	}

}
