package com.employee.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.model.Employee;


public interface EmployeeDao extends JpaRepository<Employee,Long>{
	public Employee findEmployeeByemail(String email);
	public Employee findByEmployeeName(String employeeName);
}
