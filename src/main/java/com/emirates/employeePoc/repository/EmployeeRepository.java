package com.emirates.employeePoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.emirates.employeePoc.model.Employee;
/**
* <h1>Employee Management</h1>
* The Employee Management implements an application that
* performs all CRUD operations, search operation, 
* bulk upload via excel sheet
* download list in excel sheet of current Employees.
* <p>
* <b>Note:</b> This EmployeeRepository interface performs database layer via MongoRepository.
*
* @author  Paaks
* @version 1.0
* @since   2017-09-29
*/
public interface EmployeeRepository extends MongoRepository<Employee, String>{

}
