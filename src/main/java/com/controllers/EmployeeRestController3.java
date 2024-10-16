package com.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RequestMapping("v3")
@RestController
public class EmployeeRestController3 {

	
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping("/employees")//all employees data>>  localhost:666/v3/employees
	public ResponseEntity<List<EmployeeEntity>> findallEmployees() {
		List<EmployeeEntity> listOfEmp=employeeService.findAllEmp();
		return new ResponseEntity<List<EmployeeEntity>>(listOfEmp,HttpStatus.OK);
	}
	
	//fetch particular employee ..  localhost:666/v3/employees/2
	
	@GetMapping("/employees/{employeeId}")//particular employees data
	public ResponseEntity<?> findaEmployee(@PathVariable int employeeId) {
		
		EmployeeEntity employee=employeeService.findEmployee(employeeId);
		if(employee !=null) {
		return new ResponseEntity<EmployeeEntity>(employee, HttpStatus.FOUND);
		}
		else {
			
			return new ResponseEntity<String>("Employee Not Found!", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/employees")
	public  ResponseEntity<String> registration(@RequestBody EmployeeEntity employeeEntity) {
		employeeService.saveEmp(employeeEntity);
		return new ResponseEntity<String>("Registered successfully!", HttpStatus.CREATED);
		}
	
	@PutMapping("/employees/{employeeId}")
	public ResponseEntity<String> updateEmp(@RequestBody EmployeeEntity employeeEntity,@PathVariable int employeeId) {
		
		employeeEntity.setEmployeeId(employeeId);
		employeeService.updateEmp(employeeEntity);   
		return new ResponseEntity<String>("Employee updated successfully!", HttpStatus.CREATED);
		}
	
	@DeleteMapping("/employees/{employeeId}")
	public ResponseEntity<String> deleteEmp(@PathVariable int employeeId) {
			employeeService.deleteEmp(employeeId);
			return new ResponseEntity<String>("Employee deleted successfully!", HttpStatus.OK);
		   }
	
}
