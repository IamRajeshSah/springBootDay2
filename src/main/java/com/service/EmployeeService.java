package com.service;

import java.util.List;

import com.dto.EmployeeDTO;
import com.entity.EmployeeEntity;


public interface EmployeeService {

	EmployeeDTO authenticate(String emailId, String password);

	void saveEmp(EmployeeEntity employeeEntity);

	List<EmployeeEntity> findAllEmp();

	void deleteEmp(int employeeId);

	EmployeeEntity findEmployee(int employeeId);
	
	void updateEmp(EmployeeEntity employeeEntity);


}
