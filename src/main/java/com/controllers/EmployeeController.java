package com.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dto.EmployeeDTO;
import com.entity.EmployeeEntity;
import com.service.EmployeeService;
import java.util.List;

@Controller
public class EmployeeController {

	
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping({"/login" , "/"}) //localhost:666
	public String callLoginPage() {
		
		
		return "login";//login.jsp
	}
	
	@PostMapping("/userValidation")
	public String authenticateEmp(@RequestParam String emailId,@RequestParam String password, Model model) {
		EmployeeDTO	employeeDTO=employeeService.authenticate(emailId,password);
		           //employeeDTO---->null or  not_null
		      
		   if(employeeDTO!=null) {
			   //not_null(means user is a valid user)
			   
			   
			   model.addAttribute("employeeDTO",employeeDTO);
			   return "congratulation";	 
		   }else {
			 //null(means user is not valid user  or wrong_credential)
			 
			   
			   model.addAttribute("message","Wrong credential!! Re-Try please");
			   return "login";	
		   }	
		
	}
	@GetMapping("/signup")
	public String callSignupPage() {
		
		return "signup";
	}
	
	@PostMapping("/registration")
	public String registration(@ModelAttribute EmployeeEntity employeeEntity, Model model) {
			employeeService.saveEmp(employeeEntity);
			 
			   model.addAttribute("message","Register Sucessfully");
			   return "login";	
		   }	
	@GetMapping("/showAll")
	public String findallEmployees(Model model) {
		List<EmployeeEntity> listOfEmp=employeeService.findAllEmp();
		model.addAttribute("listOfEmp",listOfEmp);
		System.out.println(listOfEmp+"===================================");
		
		return "showAll";//go to showAll.jsp
	}
	
	@GetMapping("/deleteEmployee")
	public String delEmp(@RequestParam int employeeId) {
		
		employeeService.deleteEmp(employeeId);
		return "redirect:showAll";
	}
		
	
	@GetMapping("/editEmployee")
	public String showEditForm(@RequestParam int employeeId,Model model) {
		
		EmployeeEntity emp=employeeService.findEmployee(employeeId);
		model.addAttribute("emp",emp);
		return "edit";
	}
	
	
	@PostMapping("/updateEmployee")
	public String update(@ModelAttribute EmployeeEntity employeeEntity, Model model) {
			employeeService.saveEmp(employeeEntity);
			 
			   
			return "redirect:showAll";
		   }	
	
}
