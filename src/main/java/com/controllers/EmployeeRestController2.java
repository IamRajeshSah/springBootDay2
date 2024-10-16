package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dto.EmployeeDTO;
import com.entity.EmployeeEntity;
import com.service.EmployeeService;

@RequestMapping("v2")
@RestController
public class EmployeeRestController2 {

	
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping("/employees")//all employees data>>  localhost:666/v2/employee
	public List<EmployeeEntity> findallEmployees() {
		List<EmployeeEntity> listOfEmp=employeeService.findAllEmp();
		return listOfEmp;
	}
	
	//fetch particular employee ..  localhost:666/v2/employees/2
	
	@GetMapping("/employees/{employeeId}")//particular employees data
	public EmployeeEntity findaEmployee(@PathVariable int employeeId) {
		
		EmployeeEntity employee=employeeService.findEmployee(employeeId);
		if(employee !=null) {
		return employee;
		}
		else {
			
			return null;
		}
	}
	
	@PostMapping("/employees")
	public String registration(@RequestBody EmployeeEntity employeeEntity) {
			employeeService.saveEmp(employeeEntity);
			 
			   
			   return "registered successfully";	
		   }
	
	@PutMapping("/employees/{employeeId}")
	public String updateEmp(@RequestBody EmployeeEntity employeeEntity,@PathVariable int employeeId) {
		
		employeeEntity.setEmployeeId(employeeId);
			employeeService.updateEmp(employeeEntity);
			 
			   
			   return "updated successfully";	
		   }
	
	@DeleteMapping("/employees/{employeeId}")
	public String deleteEmp(@PathVariable int employeeId) {
			employeeService.deleteEmp(employeeId);
			 
			   
			   return "deleted successfully";	
		   }
		
			
	
	
}
