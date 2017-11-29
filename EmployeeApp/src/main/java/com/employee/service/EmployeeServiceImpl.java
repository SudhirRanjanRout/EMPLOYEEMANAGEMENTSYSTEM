package com.employee.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.employee.dao.EmployeeDao;
import com.employee.dto.EmployeeDto;
import com.employee.dto.LoginDto;
import com.employee.model.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {
@Autowired
private EmployeeDao dao;
	@Override
	public String createEmployee(EmployeeDto employee) {
		// TODO Auto-generated method stub
		if(employee != null) {
			
		
		Employee employees = new Employee();
		BeanUtils.copyProperties(employee, employees);
		// employee.setEmployeeId(employee.getEmployeeId());
		//employees.setEmail(employee.getEmail());
		//employees.setAddress(employee.getAddress());
		//employees.setEmployeeName(employee.getEmployeeName());
		//employees.setMobileNo(employee.getMobileNo());
		//employees.setSalary(employee.getSalary());
		employees.setDateOfBirth(new Date());
		employees.setEnabled(true);
		//employees.setPassword(hashPassword(employee.getPassword()));
		 
		 dao.save(employees);
		return "employee has been added successfully";
		}
		return null;
	}
//	@Override
//	public String createEmployee(EmployeeDto employee) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public String login(LoginDto login) {
		// TODO Auto-generated method stub
		if(login != null) {
			Employee employee = this.dao.findEmployeeByemail(login.getEmail());
			if(employee != null) {
				if(login.getPassword().equals(employee.getPassword()) ) {
					System.out.println("Login successful");
				}else {
					System.out.println("password mismatch");
				}
			}else {
				System.out.println("There is no account available");
			}
		}
		
		return null;
	}
	@Override
	public void updateEmployee(EmployeeDto employee,Employee employees) {
		// TODO Auto-generated method stub
		
		BeanUtils.copyProperties(employee, employees);
		employees.setDateOfBirth(new Date());
		this.dao.save(employees);
	}
	@Override
	public Employee findByEmployeeName(String employeeName) {
		// TODO Auto-generated method stub
		return this.dao.findByEmployeeName(employeeName);
	}
	
	@Override
	public List<EmployeeDto> getAllEmployee() throws Exception {
		// TODO Auto-generated method stub
		List<Employee> employee = this.dao.findAll();
		System.out.println("List all the employees:"+employee);
		List<EmployeeDto> employeeDto = new ArrayList<>();
		employee.forEach(emp ->{
			EmployeeDto dto = new EmployeeDto();
			BeanUtils.copyProperties(emp, dto);
			employeeDto.add(dto);
		});
		if(employeeDto == null)
			throw new Exception("No Employee is available");
		else
		return  employeeDto;
	}
	@Override
	public Employee getEmployeeById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*private String hashPassword(String plainTextPassWord){
		String hashPassword = BCrypt.hashpw(plainTextPassWord, BCrypt.gensalt());
		System.out.println(hashPassword);
		return hashPassword;
	}*/
	@Override
	public Employee findByEmail(String email) {
		// TODO Auto-generated method stub
		return dao.findEmployeeByemail(email);
	}
	
	

}
