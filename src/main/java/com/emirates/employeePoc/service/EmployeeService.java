package com.emirates.employeePoc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.emirates.employeePoc.model.Employee;

/**
* <h1>Employee Management</h1>
* The Employee Management implements an application that
* performs all CRUD operations, search operation, 
* bulk upload via excel sheet
* download list in excel sheet of current Employees.
* <p>
* <b>Note:</b> This EmployeeService class performs as service layer.
*
* @author  Paaks
* @version 1.0
* @since   2017-09-29
*/
public interface EmployeeService {
	
	/**
	 * Service retrieves all existing employee as a list.
	 * @return List<Employee>
	 */
	List<Employee> getAllEmployees();
	
	/**
	 * Service get a particular employee by ID.
	 * @param empId
	 * @return Employee
	 */
    Employee getEmployeeById(String empId);
    
    /**
     * Service creates an employee with all details.
     * @param employee
     * @return Employee
     */
    Employee createEmployee(Employee employee);
    
    /**
     * Service update an existing employee with all details.
     * @param employee
     * @return Employee
     */
    Employee updateEmployee(Employee employee);
    
    /**
     * Service deletes an existing employee with id.
     * @param empId
     */
    void deleteEmployee(String empId);
    
    /**
     * Service to create bulk employees by importing excel file.
     * @param excelfile
     * @throws Exception
     */
    void createByUpload(MultipartFile excelfile) throws Exception;
}
