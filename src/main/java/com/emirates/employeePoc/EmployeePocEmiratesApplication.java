package com.emirates.employeePoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
* <h1>Employee Management</h1>
* The Employee Management implements an application that
* performs all CRUD operations, search operation, 
* bulk upload via excel sheet
* download list in excel sheet of current Employees.
* <p>
* <b>Note:</b> This EmployeePocEmiratesApplication class starts up Spring boot and
* auto configures its dependencies. 
*
* @author  Paaks
* @version 1.0
* @since   2017-09-29
*/
@SpringBootApplication
public class EmployeePocEmiratesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeePocEmiratesApplication.class, args);
	}
}
