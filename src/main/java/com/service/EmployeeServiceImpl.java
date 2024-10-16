package com.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EmployeeDao;
import com.dto.EmployeeDTO;
import com.entity.EmployeeEntity;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	
	@Autowired
	EmployeeDao employeeDao;

	@Override
	public EmployeeDTO authenticate(String emailId, String password) {
		Optional<EmployeeEntity> optional=employeeDao.findByEmailIdAndPassword(emailId,password);
		
		   EmployeeDTO employeeDTO=null;
		if(optional.isPresent()) {
			       //isPresent()---> if object is null then it return FALSE otherwise returns TRUE
			EmployeeEntity employeeEntity=optional.get();
			   //get()--->If a value is present, returns the value, otherwise throws NoSuchElementException
			
						employeeDTO=new EmployeeDTO();
			
			//now copy the data from employeeEntity to employeeDTO
						BeanUtils.copyProperties(employeeEntity, employeeDTO);
			                                      //source         //destination
						
						return employeeDTO;  //not_null
				}else {
					   
					   return employeeDTO;   //null
					}
		
		}

	@Override
	public void saveEmp(EmployeeEntity employeeEntity) {
		// TODO Auto-generated method stub
		employeeDao.save(employeeEntity);
	}

	@Override
	public List<EmployeeEntity> findAllEmp() {
		List<EmployeeEntity> list=employeeDao.findAll();
		
		return list;
	}

	@Override
	public void deleteEmp(int employeeId) {
		
		employeeDao.deleteById(employeeId);
	}

	@Override
	public EmployeeEntity findEmployee(int employeeId) {
		
		//EmployeeEntity employeeEntity=employeeDao.findById(employeeId).get();
		
		Optional<EmployeeEntity> optional=employeeDao.findById(employeeId);
		if(optional.isPresent()) {
			EmployeeEntity employeeEntity=optional.get();
			return employeeEntity;
		}
		
		return null;
	}

	@Override
	public void updateEmp(EmployeeEntity employeeEntity) {
		employeeDao.save(employeeEntity);
		
	}
	
	
	
	
	
}
