package com.example.MyApp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyApp.model.Employee;
import com.example.MyApp.exception.ResourceNotFoundException;
import com.example.MyApp.repository.EmployeeRepository;
import com.example.MyApp.service.SequenceGeneratorService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController 
{	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@GetMapping("/employees")
	public List <Employee> getAllemployees()
	{
		return employeeRepository.findAll();
	}
	
	 @GetMapping("/employees/{id}")
	    public ResponseEntity < Employee > getEmployeeById(@PathVariable(value = "id") Long employeeId)
	    throws ResourceNotFoundException 
	    {
	        Employee employee = employeeRepository.findById(employeeId)
	            .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
	        return ResponseEntity.ok().body(employee);
	    }
	 
	 
	 @PostMapping("/employees")
	 public Employee createEmployee(@Valid @RequestBody Employee employee) 
	 {
		 employee.setId(sequenceGeneratorService.generateSequence(Employee.SEQUENCE_NAME));
		 return employeeRepository.save(employee); 
	 }
	 
	 @PutMapping("/employees/{id}")
	 public ResponseEntity <Employee> updateEmployee (@PathVariable (value = "id") Long employeeId,
	 @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException
	 {
		 Employee employee = employeeRepository.findById(employeeId)
				 .orElseThrow(()->  new ResourceNotFoundException("Employee not found with this Id"));
		 employee.setEmailId(employeeDetails.getEmailId());
		 employee.setfirstName(employeeDetails.getFirstName());
		 employee.setlastName(employeeDetails.getLastName());
		 
		 final Employee updatedEmployee = employeeRepository.save(employee);
		 return ResponseEntity.ok(updatedEmployee); 
	 }
	
    @DeleteMapping("/employees/{id}")
    public Map <String, Boolean> deleteEmployee(@PathVariable(value = "id" ) Long employeeId) throws ResourceNotFoundException 
    {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(()-> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        employeeRepository.delete(employee);
        Map <String,Boolean> response = new HashMap <>();
        response.put("delete", Boolean.TRUE);
        return response;
    }
}
