package com.employee.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.dto.EmployeeDto;
import com.employee.dto.LoginDto;
import com.employee.model.Employee;
import com.employee.service.EmployeeService;



@RestController
@RequestMapping
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public ResponseEntity<String> createEmploee(@RequestBody EmployeeDto employee)throws Exception{
		//if(employee)
		return ResponseEntity.ok().body(this.employeeService.createEmployee(employee));
		
	}
	//@PostMapping("/login")
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody LoginDto login)throws Exception{
		
		return ResponseEntity.ok().body(this.employeeService.login(login));
	}
	
	@RequestMapping(value = "/update",method = RequestMethod.PUT)
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employee){
		String employeeName = employee.getEmployeeName();
		System.out.println("employee name is:"+employeeName);
		Employee employees = this.employeeService.findByEmployeeName(employeeName);
		this.employeeService.updateEmployee(employee,employees);
		return new ResponseEntity<>(HttpStatus.OK);	//return ResponseEntity.ok().body(this.employeeService.updateEmployee(employee));
	}
	
	@RequestMapping(value = "/listAllEmployee", method = RequestMethod.GET)
	public ResponseEntity<?> listAllEmployee() throws Exception{
		List<EmployeeDto> listAllEmployee;
		listAllEmployee = this.employeeService.getAllEmployee();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value ="/getEmployee/{employeeName}",method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeByName(@PathVariable ("employeeName") String employeeName) throws Exception{
			Employee employee = this.employeeService.findByEmployeeName(employeeName);
			EmployeeDto dto = new EmployeeDto();
			if(employee == null) {
				throw new Exception("no user exist");
			}else {
			
			BeanUtils.copyProperties(employee, dto);
			}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getEmployees/{email}",method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeByMail(@PathVariable("email") String email) throws Exception {
		Employee employee = this.employeeService.findByEmail(email);
		EmployeeDto dto = new EmployeeDto();
		if(employee == null) {
			throw new Exception("No user is found with this email");
		}else {
			BeanUtils.copyProperties(employee, dto);
		}
		return new ResponseEntity<>(dto,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getEmployee/{employeeId}",method = RequestMethod.GET)
	public ResponseEntity<?> getEmployeeById(@PathVariable("employeeId") int employeeId) throws Exception{
		
		return null;
	}
	public EmployeeService getEmployeeService() {
		return employeeService;
	}
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello Returned";
	}
	
	
}
