package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto;
import com.employee.dto.LoginDto;
import com.employee.model.Employee;



public interface EmployeeService {
	public String createEmployee(EmployeeDto employee);
	public String login(LoginDto login);
	public void updateEmployee(EmployeeDto employee,Employee employees);
	public Employee findByEmployeeName(String employeeName);
	public List<EmployeeDto> getAllEmployee() throws Exception;
	public Employee getEmployeeById(int id) throws Exception;
	public Employee findByEmail(String email);
	//public void updateEmployee(EmployeeDto employee, Employee employees);

}
