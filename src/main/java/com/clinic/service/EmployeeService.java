package com.clinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.clinic.model.Employee;
import com.clinic.repository.EmployeeRepository;

@Service
public class EmployeeService  {
	

	@Autowired
	EmployeeRepository emprepository;
	
	public List<Employee> getEmployee() {		
		return emprepository.findAll();

	}	
	public Employee getEmployeebyId( @PathVariable("id") int id) {
		return emprepository.findOne(id);

	}
	/*public Employee createemp(@RequestBody Employee emp) {
		emprepository.save(emp);
		return emprepository.getOne(emp.getEmpId());
		
	}*/
	
	public List<Employee> updateemp(@RequestBody Employee emp, @PathVariable("id") int uid) {
		emprepository.save(emp);
		return emprepository.findAll();	

	}
	public List<Employee> deleteemp(@PathVariable("id") int id) {
		emprepository.delete(id);
		return emprepository.findAll();
	}
	
	public List<Employee> getEmployeeBycId(int cid) {		
		return emprepository.findByCompanyId(cid);
	}
	public Employee createemp(Employee user) {
		emprepository.save(user);
		return emprepository.getOne(user.getEmpId());
	}
}
