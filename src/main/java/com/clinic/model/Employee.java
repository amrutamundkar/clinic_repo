package com.clinic.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name="emp_id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int empId;
	@Column(name="emp_name")
	private String empName;
	
	
	
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	
}	
	
	
	
	
	
	
	
	
	
	


