package com.emirates.employeePoc.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.emirates.employeePoc.config.ExportExcelViewConfig;
import com.emirates.employeePoc.model.Employee;
import com.emirates.employeePoc.service.EmployeeService;

/**
* <h1>Employee Management</h1>
* The Employee Management implements an application that
* performs all CRUD operations, search operation, 
* bulk upload via excel sheet
* download list in excel sheet of current Employees.
* <p>
* <b>Note:</b> This EmployeeController class provides all rest services
* that will be consumed by SPA on angular 4.0.
*
* @author  Paaks
* @version 1.0
* @since   2017-09-29
*/

@RestController
@RequestMapping("/emirates/employee/")
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	/**
	 * Get Restservice to get All employee list.
	 * @return List<Employee>
	 */
	@GetMapping("list")
	List<Employee> findAll() {
		return service.getAllEmployees();
	}
	
	/**
	 * Get Restservice to get particular employee by ID.
	 * @param empId
	 * @return Employee
	 */
	@GetMapping(value = "{id}")
	Employee findById(@PathVariable("id") String empId) {
		return service.getEmployeeById(empId);
	}
	
	/**
	 * Post Restservice to create an employee with all details.
	 * @param employee
	 * @return Employee
	 */
	@PostMapping("create")
	Employee create(@RequestBody @Valid Employee employee) {
		return service.createEmployee(employee);
	}
	
	/**
	 * Put Restservice to update an existing employee with all details.
	 * @param employee
	 * @return Employee
	 */
	@PutMapping("update/{id}")
	Employee update(@RequestBody @Valid Employee employee) {
		return service.updateEmployee(employee);
	}
	
	/**
	 * Delete Restservice to delete an existing employee with id.
	 * @param empId
	 * @return List<Employee>
	 */
	@DeleteMapping("delete/{id}")
	List<Employee> delete(@PathVariable("id") String empId) {
		service.deleteEmployee(empId);
		return findAll();
	}
	/**
	 * Post service to create bulk employees by importing excel file.
	 * @param excelfile
	 * @return List<Employee>
	 * @throws Exception
	 */
	@PostMapping("upload")
	List<Employee> upload(@RequestParam("file") MultipartFile excelfile) throws Exception {
		service.createByUpload(excelfile);
		return findAll();
	}
	
	/**
	 * Get service to export existing employees in excel file.
	 * @return ModelAndView
	 */
	@GetMapping("download")
    public ModelAndView getMyData(){
		
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("employeeList", service.getAllEmployees());
        
        return new ModelAndView(new ExportExcelViewConfig(), model);
    }
	/**
	 * Get service to fetch current server environment details.
	 * @param request
	 * @return Map
	 * @throws MalformedURLException
	 */
	@GetMapping("serverDetails")
	public Map<String, String> getServerDetails(HttpServletRequest request) throws MalformedURLException{
		
		Map<String, String> serverMap = new HashMap<String, String>(3);
		
		return getServerMap(serverMap,request);
		
	}
	
	/**
	 * Function populates current server details.
	 * @param serverMap
	 * @param request
	 * @return Map
	 * @throws MalformedURLException
	 */
	private Map<String, String> getServerMap(Map<String, String> serverMap, HttpServletRequest request) throws MalformedURLException {
		
		URL url = new URL(request.getRequestURL().toString());
		
		serverMap.put("host", url.getHost());
		serverMap.put("scheme", url.getProtocol());
		serverMap.put("port", Integer.toString(url.getPort()));
	   
		return serverMap;

	}
	}
