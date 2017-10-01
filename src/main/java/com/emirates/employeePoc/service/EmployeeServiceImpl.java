package com.emirates.employeePoc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emirates.employeePoc.model.Employee;
import com.emirates.employeePoc.repository.EmployeeRepository;
/**
* <h1>Employee Management</h1>
* The Employee Management implements an application that
* performs all CRUD operations, search operation, 
* bulk upload via excel sheet
* download list in excel sheet of current Employees.
* <p>
* <b>Note:</b> This EmployeeServiceImpl class performs as service layer.
*
* @author  Paaks
* @version 1.0
* @since   2017-09-29
*/
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	/**
	 * Service retrieves all existing employee as list.
	 */
	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	/**
	 * Service get particular employee by ID.
	 */
	@Override
	public Employee getEmployeeById(String empId) {
		return employeeRepository.findOne(empId.toUpperCase());
	}
	/**
	 * Service creates an employee with all details.
	 */
	@Override
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	/**
	 * Service update an existing employee with all details.
	 */
	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	/**
	 * Service deletes an existing employee with id.
	 */
	@Override
	public void deleteEmployee(String empId) {
		employeeRepository.delete(empId.toUpperCase());
	}
	
	/**
	 * This service to create bulk employees by importing excel file.
	 */
	@Override
	public void createByUpload(MultipartFile excelfile) throws Exception {
		List<Employee> employees = readExcelContent(excelfile);
		employeeRepository.save(employees);
	}
	/**
	 * Function reads imported excel file content and construct Employee list.
	 * @param excelfile
	 * @return List<Employee>
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	private List<Employee> readExcelContent(MultipartFile excelfile) throws Exception {

		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = null;

		int i = 1;
		String fileType = null;
		
		try {
			
			fileType = excelfile.getOriginalFilename().split("\\.")[1];
			
			if(fileType.equalsIgnoreCase("xls")){
				
				HSSFWorkbook workbook  = new HSSFWorkbook(excelfile.getInputStream());
				HSSFSheet worksheet = workbook.getSheetAt(0);
				
				while (i <= worksheet.getLastRowNum() && worksheet.getLastRowNum() > 0) {
					employee = new Employee();
					HSSFRow row = worksheet.getRow(i++);
					
					employee.setId(null != row.getCell(0).getStringCellValue() ? row.getCell(0).getStringCellValue() : "");
					employee.setFirstName(null != row.getCell(1).getStringCellValue() ? row.getCell(1).getStringCellValue() : "");
					employee.setLastName(null != row.getCell(2).getStringCellValue() ? row.getCell(2).getStringCellValue() : "");
					employee.setEmailId(null != row.getCell(3).getStringCellValue() ? row.getCell(3).getStringCellValue() : "");
					if(row.getCell(4).getCellType() == HSSFCell.CELL_TYPE_NUMERIC){
						employee.setContact(row.getCell(4).getNumericCellValue()+"");
					}
					else{
						employee.setContact(row.getCell(4).getStringCellValue());
					}
					employee.setDept(null != row.getCell(5).getStringCellValue() ? row.getCell(5).getStringCellValue() : "");
					employee.setDesignation(null != row.getCell(6).getStringCellValue() ? row.getCell(6).getStringCellValue() : "");

					employees.add(employee);
				}
				workbook.close();
			}
			else if(fileType.equalsIgnoreCase("xlsx")){
				
				XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
				XSSFSheet worksheet = workbook.getSheetAt(0);
				
				while (i <= worksheet.getLastRowNum() && worksheet.getLastRowNum() > 0) {
					employee = new Employee();
					XSSFRow row = worksheet.getRow(i++);

					employee.setId(null != row.getCell(0).getStringCellValue() ? row.getCell(0).getStringCellValue() : "");
					employee.setFirstName(null != row.getCell(1).getStringCellValue() ? row.getCell(1).getStringCellValue() : "");
					employee.setLastName(null != row.getCell(2).getStringCellValue() ? row.getCell(2).getStringCellValue() : "");
					employee.setEmailId(null != row.getCell(3).getStringCellValue() ? row.getCell(3).getStringCellValue() : "");
					if(row.getCell(4).getCellType() == XSSFCell.CELL_TYPE_NUMERIC){
						employee.setContact(row.getCell(4).getNumericCellValue()+"");
					}
					else{
						employee.setContact(row.getCell(4).getStringCellValue());
					}
					employee.setDept(null != row.getCell(5).getStringCellValue() ? row.getCell(5).getStringCellValue() : "");
					employee.setDesignation(null != row.getCell(6).getStringCellValue() ? row.getCell(6).getStringCellValue() : "");

					employees.add(employee);
				}
				workbook.close();
			}
			else{
				throw new Exception("Wrong file was uploaded. Please Choose .XLS or .XLSX");
			}
						
		} catch (Exception e) {
			throw new Exception("Wrong file was uploaded. Please Choose .XLS or .XLSX");
		}
		return employees;
	}
}
