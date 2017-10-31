package com.clinic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.clinic.model.Employee;



public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	@Query(value = "SELECT e FROM Employee e WHERE c_id = ?1")
	  List<Employee> findByCompanyId(int cid);

	@Query("select e from Employee e")
	List<Employee> findallemp();
	
	
	
	
}
